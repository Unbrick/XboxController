package com.unbrick.xbox.XBDM.threads.rpc;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.unbrick.xbox.RPC.XDRPCArgumentInfo;
import com.unbrick.xbox.XBDM.threads.BaseXBDMThread;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

import static com.unbrick.xbox.XBDM.HexUtils.formatHexDump;

public class RPCCallByOrdinalThread extends BaseXBDMThread {

    private String moduleName;
    private ArrayList<XDRPCArgumentInfo> args = new ArrayList<>();
    private int ordinal = 0x0;

    private RPCCallByOrdinalThread(InetSocketAddress inetSocketAddress, String command, int length) {
        super(inetSocketAddress, command, length);
    }

    private RPCCallByOrdinalThread(InetSocketAddress inetSocketAddress, String command) {
        super(inetSocketAddress,command);
    }

    public RPCCallByOrdinalThread(InetSocketAddress inetSocketAddress, String moduleName, int ordinal, ArrayList<XDRPCArgumentInfo> params) {
        super(inetSocketAddress);
        this.moduleName = moduleName;
        this.ordinal = ordinal;
        this.args = params;
    }

    private byte[] construct_package_1(String moduleName, ArrayList<XDRPCArgumentInfo> params) {
        String cmd_1 = "rpc title version=4 buf_size=";
        String cmd_2 = " processor=5 thread=\r\n";

        // The buffer always needs 72 bytes even without params, idk why
        int buf_size = 72;
        for (XDRPCArgumentInfo _arg : params) {
            int size = _arg.size();
            if (_arg.isString()) {
                size = size * 2;
                while (size % 16 != 0) {
                    size++;
                }
            }
            buf_size = buf_size + size;
        }

        for (buf_size = buf_size + moduleName.length(); buf_size % 8 != 0; buf_size++);
        return (cmd_1 + buf_size + cmd_2).getBytes();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private byte[] construct_package_2(String module_name, long buf_addr, int ordinal, ArrayList<XDRPCArgumentInfo> params) throws IOException {
        /* the first buff address is calculated by the difference in bytes between the start of the package and the begin of the module name
         * In this case, the preable including the first_buf_addr and the ordinal makes up 64 bytes (0x40)
         * Additionally four parameters (8 bytes per param) need to be appended and 8 bytes for the second buf_addr -> 40 (0x28)
         *
         *
         *      00000037  00 00 00 00 00 00 00 00  00 00 00 00 00 00 00 00   ........ ........  << standard preamble
                00000047  00 00 00 00 00 00 00 00  00 00 00 00 00 00 00 00   ........ ........	<< standard preamble
                00000057  00 00 00 00 00 00 00 05  00 00 00 00 00 00 00 00   ........ ........  << arguments  +  8x 00
                00000067  00 00 00 00 3a 13 9d d8  00 00 00 00 00 00 02 90   ....:... ........  << first_buf_addr + ordinal addr
                00000077  00 00 00 00 00 00 00 00  00 00 00 00 00 00 00 00   ........ ........  << arg 0 + arg 1
                00000087  00 00 00 00 00 00 00 02  00 00 00 00 3a 13 9d e0   ........ ....:...  << arg 2 + second_buf_addr
                00000097  00 00 00 00 00 00 00 00 (x) 78 61 6d 2e 78 65 78 00   ........ xam.xex.  << arg 4 + module to call
                000000A7  (x) 00 58 00 4e 00 4f 00 54  00 49 00 46 00 59 00 00   .X.N.O.T .I.F.Y..  << string argument

                first_buf_addr = buf_addr + preamble (64 bytes / 0x40) + arguments (4 * 8 bytes -> 40 bytes / 0x28)  + sizeof(new_buf_addr) (8 bytes / 0x8)
                second_buf_addr = first_buf_addr + sizeof(module) rounded up to the next full 8 bytes (sizeof(module) + fill bytes (0x00) % 8 == 0)
            * */
        long _first_buf_addr = buf_addr + 0x48;
        int _number_of_params = params.size();

        for (XDRPCArgumentInfo _arg : params) {
            // every argument except strings requires 8 bit value or reservation before the address
            if (!_arg.isString()) {
                _first_buf_addr = _first_buf_addr + _arg.size();
            }
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // 32x 0x00
        byte[] preamble = ByteBuffer.allocate(32).array();
        outputStream.write(preamble);

        // number of parameters passed
        byte[] parameter_size = ByteBuffer.allocate(8).putLong(_number_of_params).array();
        outputStream.write(parameter_size);

        // 16x 0x00
        byte[] stuff_in_between = ByteBuffer.allocate(8).array();
        outputStream.write(stuff_in_between);

        byte[] first_buf_addr = ByteBuffer.allocate(8).putLong(_first_buf_addr).array();
        outputStream.write(first_buf_addr);

        byte[] _ordinal = ByteBuffer.allocate(8).putLong(ordinal).array();
        outputStream.write(_ordinal);

        long _second_buf_addr = _first_buf_addr;
        for (_second_buf_addr = _second_buf_addr + module_name.length(); _second_buf_addr % 8 != 0; _second_buf_addr++);

        ByteArrayOutputStream floatDoubleStream = new ByteArrayOutputStream();
        ByteArrayOutputStream stringStream = new ByteArrayOutputStream();

        for (XDRPCArgumentInfo _arg : params) {
            if (_arg.isString()) {
                // Increase size to match next bigger 8 byte digit
                int size = _arg.size() * 2;
                while (size % 16 != 0) {
                    size++;
                }
                // string arguments need to be appended at the end
                byte[] stringArgument = ByteBuffer.allocate(size).put(((String) _arg.get()).getBytes(StandardCharsets.UTF_16BE)).array();
                stringStream.write(stringArgument);
            } else if (_arg.isInt()) {
                // integer arguments can be written directly
                byte[] intArgument = ByteBuffer.allocate(8).putLong((Integer) _arg.get()).array();
                outputStream.write(intArgument);
            } else if (_arg.isFloat()) {
                // for float values a placeholder of 8 bytes needs to be placed
                byte[] floatPlaceholder = ByteBuffer.allocate(8).array();
                outputStream.write(floatPlaceholder);
                //afterwards the float value is written after the buf_addr
                byte[] floatValue = ByteBuffer.allocate(8).putFloat((float)_arg.get()).array();
                floatDoubleStream.write(floatValue);
            } else if (_arg.isDouble()) {
                // see float handling, same here
                byte[] doublePlaceholder = ByteBuffer.allocate(8).array();
                outputStream.write(doublePlaceholder);
                byte[] doubleValue = ByteBuffer.allocate(8).putDouble((double)_arg.get()).array();
                floatDoubleStream.write(doubleValue);
            } else if (_arg.isShort()) {
                // short can be written directly
                byte[] shortValue = ByteBuffer.allocate(8).putShort((short)_arg.get()).array();
                outputStream.write(shortValue);
            } else if (_arg.isByte()) {
                // byte can be written directly but bytebuffer does not support placing a byte
                byte[] byteValue = ByteBuffer.allocate(8).array();
                byteValue[byteValue.length - 1] = (byte) _arg.get();
                outputStream.write(byteValue);
            } else if (_arg.isBool()) {
                // bool can be written directly but also not supported by bytebuffer
                byte[] boolValue = ByteBuffer.allocate(8).array();
                boolValue[boolValue.length - 1] = (byte) ((boolean) _arg.get() ? 0x1 : 0x0);
                outputStream.write(boolValue);
            } else if (_arg.isLong()) {
                // long can be written directly
                byte[] longValue = ByteBuffer.allocate(8).putLong((Long) _arg.get()).array();
                outputStream.write(longValue);
            }
            //TODO handle structs...
        }

        // the last argument needs to be after the second_buf_addr
        byte[] second_buf_addr = ByteBuffer.allocate(8).putLong(_second_buf_addr).array();

        // copy the last 8 bytes (the last argument)
        byte[] last_argument = Arrays.copyOfRange(outputStream.toByteArray(), outputStream.toByteArray().length - 8, outputStream.toByteArray().length);

        ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream();
        outputStream2.write(Arrays.copyOfRange(outputStream.toByteArray(), 0, outputStream.toByteArray().length - 8));
        outputStream2.write(second_buf_addr);
        outputStream2.write(last_argument);

        outputStream = outputStream2;

        // write the second_buf_addr to the address where the last argument is
        //outputStream.write(second_buf_addr, outputStream.toByteArray().length - 9, second_buf_addr.length);

        // add the last argument to the stuff
        //outputStream.write(last_argument);


        long size_of_module_name = _second_buf_addr - _first_buf_addr;
        byte[] _module_name = ByteBuffer.allocate((int) size_of_module_name).put((module_name).getBytes(StandardCharsets.US_ASCII)).array();
        outputStream.write(_module_name);

        // add float and bool arguments
        outputStream.write(floatDoubleStream.toByteArray());

        // add string arguments
        outputStream.write(stringStream.toByteArray());

        return outputStream.toByteArray();
    }

    private String read_resp(BufferedReader bufferedReader) throws IOException {
        char[] buffer = new char[length];
        int read = 0;
        StringBuilder sb = new StringBuilder();
        while ((read = bufferedReader.read(buffer, 0, buffer.length)) > 0) {
            sb.append(buffer, 0, read);

            if (read != 16)
                break;
        }
        return sb.toString();
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void run() {
        try {
            preRun();
            dataOutputStream.flush();

            //Spawn a debug thread
            byte[] spawn_debug_thread = construct_package_1(moduleName, args);

            System.out.println("Calling spawn_debug_thread: ");
            OutputStreamWriter streamWriter = new OutputStreamWriter(System.out);
            streamWriter.write("\r\n" + formatHexDump(spawn_debug_thread, 0, spawn_debug_thread.length));
            streamWriter.flush();
            for (byte _byte : spawn_debug_thread)
                dataOutputStream.write(_byte);

            dataOutputStream.flush();

            // Read the response with the corresponding thread address
            String result = read_resp(bufferedReader);

            System.out.println("Socket response: ");
            System.out.print("\r\n" + formatHexDump(result.getBytes(), 0, result.getBytes().length));

            long buf_addr = Long.parseLong(result.substring(result.indexOf("buf_addr=") + 9, result.length() - 2).trim(), 16);
            try {
                byte[] run_rpc = construct_package_2(moduleName, buf_addr, ordinal, args);
                System.out.println("Calling run_rpc for module " + moduleName + " with ordinal: 0x" + Long.toHexString(ordinal));
                streamWriter.write("\r\n" + formatHexDump(run_rpc, 0, run_rpc.length));
                streamWriter.flush();

                for (byte _byte : run_rpc)
                    dataOutputStream.write(_byte);
            } catch (Exception e) {
                e.printStackTrace();
            }

            dataOutputStream.flush();
            postRun();
            dataOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

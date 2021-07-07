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

import static com.unbrick.xbox.XBDM.HexUtils.formatHexDump;

@SuppressWarnings("rawtypes")
public class RPCCallByAddressThread extends BaseXBDMThread {
    private ArrayList<XDRPCArgumentInfo> args = new ArrayList<>();
    private long address = 0x0;

    private RPCCallByAddressThread(InetSocketAddress inetSocketAddress, String command, int length) {
        super(inetSocketAddress, command, length);
    }

    private RPCCallByAddressThread(InetSocketAddress inetSocketAddress, String command) {
        super(inetSocketAddress,command);
    }

    public RPCCallByAddressThread(InetSocketAddress inetSocketAddress, long address, ArrayList<XDRPCArgumentInfo> params) {
        super(inetSocketAddress);
        this.address = address;
        this.args = params;
    }

    private byte[] construct_package_1() {
        String cmd_1 = "rpc title version=4 buf_size=";
        String cmd_2 = " processor=5 thread=\r\n";

        // The buffer always needs 72 bytes even without params, idk why
        int buf_size = 72;
        for (XDRPCArgumentInfo _arg : args) {
            int size = _arg.size();
            if (_arg.isString()) {
                while (size % 8 != 0) {
                    size++;
                }
            }
            buf_size = buf_size + size;
        }
        return (cmd_1 + buf_size + cmd_2).getBytes();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private byte[] construct_package_2(long buf_addr, long function_address, ArrayList<XDRPCArgumentInfo> params) throws IOException {
        // the buff address is calculated by the difference in bytes between the start of the package and the buff address
        // In case of 4 integer arguments it would be +0x40 (64 bytes) for the preamble
            /*
            *   00000036  00 00 00 00 00 00 00 00 | 00 00 00 00 00 00 00 00   ........ ........  << preamble | preamble
                00000046  00 00 00 00 00 00 00 00 | 00 00 00 00 00 00 00 00   ........ ........  << preamble | preamble
                00000056  00 00 00 00 00 00 00 05 | 00 00 00 00 00 00 00 00   ........ ........  << preamble | preamble
                00000066  00 00 00 00 00 00 00 00 | 00 00 00 00 82 40 15 e0   ........ .....@..  << preamble | addr_to_call
                00000076  00 00 00 00 00 00 00 00 | 00 00 00 00 00 00 00 01   ........ ........  << var 0 (0x0) | var 1 (0x1)
                00000086  00 00 00 00 00 00 00 02 | 00 00 00 00 00 00 00 03   ........ ........  << var 2 (0x2) | var 3 (0x3)
                00000096  00 00 00 00 3a 19 60 d8 | 70 61 72 74 79 5f 63 6f   ....:.`. party_co  << new_buf_addr | first string arguments

                new_buf_addr = buf_addr + 64 bytes (0x40) + 4 * 8 bytes (int arguments) (40 bytes / 0x28)  + new_buf_addr (8 bytes / 0x8)
            * */
        long _buf_addr = buf_addr + 0x48;
        int _number_of_params = params.size();

        for (XDRPCArgumentInfo _arg : params) {
            // every argument except strings requires 8 bit value or reservation before the address
            if (!_arg.isString()) {
                _buf_addr = _buf_addr + _arg.size();
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
        byte[] stuff_in_between = ByteBuffer.allocate(16).array();
        outputStream.write(stuff_in_between);

        byte[] _function_address = ByteBuffer.allocate(8).putLong(function_address).putInt(0,0).array();
        outputStream.write(_function_address);

        ByteArrayOutputStream floatDoubleStream = new ByteArrayOutputStream();
        ByteArrayOutputStream stringStream = new ByteArrayOutputStream();
        for (XDRPCArgumentInfo _arg : params) {
            if (_arg.isString()) {
                // Increase size to match next bigger 8 byte digit
                int size = _arg.size();
                while (size % 8 != 0) {
                    size++;
                }
                // string arguments need to be appended at the end
                byte[] stringArgument = ByteBuffer.allocate(size).put(((String) _arg.get()).getBytes(StandardCharsets.US_ASCII)).array();
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

        byte[] _new_buf_addr = ByteBuffer.allocate(8).putLong(_buf_addr).array();
        outputStream.write(_new_buf_addr);

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
            byte[] spawn_debug_thread = construct_package_1();

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

            try {
                long buf_addr = Long.parseLong(result.substring(result.indexOf("buf_addr=") + 9, result.length() - 2).trim(), 16);
                byte[] run_rpc = construct_package_2(buf_addr, address, args);


                System.out.println("Calling run_rpc with addr: 0x" + Long.toHexString(address));
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

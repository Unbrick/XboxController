package com.unbrick.xbox.XBDM;

import com.unbrick.xbox.Fragments.XboxCommands;
import com.unbrick.xbox.Objects.Console;
import com.unbrick.xbox.XBDM.listener.OnConsoleFetchedListener;
import com.unbrick.xbox.XBDM.listener.OnListFetchedListener;
import com.unbrick.xbox.XBDM.listener.OnResultListener;
import com.unbrick.xbox.Xbox.Game;
import com.unbrick.xbox.Xbox.XboxSocket;

import java.util.concurrent.ExecutorService;

import static com.unbrick.xbox.Helper.Helper.stringToHex;
import static com.unbrick.xbox.XBDM.HexUtils.hex2decimal;
import static com.unbrick.xbox.Xbox.xNotify.toHex;

/**
 * Created by Admin on 04.12.2016.
 */

public class XBDM extends RpcProtocol {
    public XBDM(String ip, int port) {
        super(ip, port);
    }

    public void getConsole(OnConsoleFetchedListener onConsoleFetchedListener) {
        Console console = Console.getConsole();
        writeAndListen(XboxCommands.getCpuKey, o -> console.setCpukey(((String) o).substring(21).trim()));
        writeAndListen(XboxCommands.getDashboardVersion, o -> console.setDashboard(String.valueOf(o)));
        writeAndListen(XboxCommands.getCpuTemp, o -> console.setCpuTemp(hex2decimal(o)));
        writeAndListen(XboxCommands.getRamTemp, o -> console.setRamTemp(hex2decimal(o)));
        writeAndListen(XboxCommands.getGpuTemp, o -> console.setGpuTemp(hex2decimal(o)));
        writeAndListen(XboxCommands.getMotherboardTemp, o -> console.setMotherboardTemp(hex2decimal(o)));
        writeAndListen(XboxCommands.getBoardName, o -> console.setBoardName(o));
        writeAndListen(XboxCommands.getDebugName, o -> {
            console.setDebugName(o);
            onConsoleFetchedListener.fetched(console);
        });

    }

    public void magicbootTitle(Game game) {
        String xexpath = game.getDirpath() + "default.xex";
        write("magicboot title=\"" + xexpath + "\" directory=\"" + game.getDirpath() + "\"");
    }

    public void getFileInfo(String filename, OnResultListener onResultListener) {
        writeAndListen("getfileattributes name=\"" + filename + "\"", onResultListener);
    }

    public void magicbootTitleMP(Game game) {
        String xexpath = game.getDirpath() + "default_mp.xex";
        write("magicboot title=\"" + xexpath + "\" directory=\"" + game.getDirpath() + "\"");
    }

    public void getMem(String offset, OnResultListener onResultListener) {
        writeAndListen("GETMEM ADDR=0x" + offset + " LENGTH=0x00001000", onResultListener);
    }

    public void clearGT() {
        setMem("c035261c".toUpperCase(), "000000000000000000000000000000000000000000000000000000000000000000");
    }

    public void dvdeject() {
        write("dvdeject");
    }

    public void drivelist(OnListFetchedListener onListFetchedListener) {
        writeAndListenMultiline("drivelist", onListFetchedListener);
    }

    /*
     * Drive: HDD:\
     * Dir: Games\
     * */
    public void dirList(OnListFetchedListener onListFetchedListener, String drive, String dir) {
        writeAndListenMultiline("dirlist name=\"" + drive + dir + "\"", onListFetchedListener);
    }

    public void shutdown() {
        write("shutdown");
    }

    public void scareUser() {
        write("khoungdm");
    }

    public void rebootToDash() {
        write("magicboot");
    }

    public void stop() {
        write("stop");
    }

    public void go() {
        write("go");
    }

    public void setMem(String offset, String data) {
        write("setmem addr=0x" + offset + " data=" + data);
    }

    public void setMem(int offset, int data) {
        write("setmem addr=0x" + Integer.toHexString(offset) + " data=" + Integer.toHexString(data));
    }

    public void getRuningTitleId(OnResultListener rl) {
        writeAndListen("consolefeatures ver=2 type=16 params=\"A\\0\\A\\0\\\"", rl);
    }

    public void reboot() {
        write("magicboot cold");
    }

    public void xNotify(String message) {
        write("consolefeatures ver=2 type=12 params=\"A\\0\\A\\2\\2/5\\" + toHex(message) + "\\1\\34\\\"");
    }

    public void xNotify(String hexMessage, int type) {
        write("consolefeatures ver=2 type=12 params=\"A\\0\\A\\2\\2/8\\" + hexMessage + "\\1\\" + type + "\\\"");
    }

    public void setGamertag(String gamertag) {
        clearGT();
        setMem("c035261c".toUpperCase(), "00" + stringToHex(gamertag) + "0000");
    }

    public void plainCommandXbdm(String command) {
        write(command);
    }

    public void plainCommandWithResultXbdm(String command, OnResultListener onResultListener) {
        writeAndListen(command, onResultListener);
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

}
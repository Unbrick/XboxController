package t_r_y.c_a_t_c_h.me.Xbox;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import com.tramsun.libs.prefcompat.Pref;

import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import t_r_y.c_a_t_c_h.me.Fragments.XboxCommands;
import t_r_y.c_a_t_c_h.me.Objects.Console;

import static t_r_y.c_a_t_c_h.me.Helper.Helper.stringToHex;

/**
 * Created by Admin on 03.12.2016.
 */

public class XboxSocket {
    private static XboxSocket instance;
    private String ipAddress;
    private XBDM xbdm;
    private JRPC2 jrpc2;
    private boolean canConnect;


    private XboxSocket(String ip) {
        this.ipAddress = ip;
        jrpc2 = new JRPC2(ip, 1409);
        xbdm = new XBDM(ip, 730);
    }

    public static XboxSocket getInstance() {
        if (instance == null) {
            instance = new XboxSocket(Pref.getString("ip"));
        }
        return instance;
    }

    public static XboxSocket getInstance(String ipAddress) {
        if (instance == null) {
            instance = new XboxSocket(ipAddress);
        }
        return instance;
    }

    public static void canConnect(String ip, OnNetworkScanListener rl) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                rl.finished(available(ip, 1409));
                return null;
            }
        }.execute();
    }

    private static boolean available(String ip, int port) {
        System.out.println("--------------Testing port " + port);
        Socket s = null;
        try {
            s = new Socket(ip, port);
            s.setSoTimeout(1000);
            s.close();
            System.out.println(ip + "--------------Port " + port + " is open");
            return true;
        } catch (IOException e) {
            System.out.println(ip + "--------------Port " + port + " is closed");
            return false;
        } finally {
            if (s != null) {
                try {
                    s.close();
                } catch (IOException e) {
                    throw new RuntimeException("You should handle this error.", e);
                }
            }
        }
    }

    public void magicbootTitle(Game game){
        String xexpath = game.getDirpath()+"default.xex";
        xbdm.write("magicboot title=\""+xexpath+"\" directory=\""+game.getDirpath()+"\"");
    }

    public void magicbootTitleMP(Game game){
        String xexpath = game.getDirpath()+"default_mp.xex";
        xbdm.write("magicboot title=\""+xexpath+"\" directory=\""+game.getDirpath()+"\"");
    }

    public void getGamertagsBlackOpsII(OnGamertagsFetchedListener onGamertagsFetchedListener) {
        ArrayList<String> gamertags = new ArrayList<>();
        for (String offset : XboxCommands.BlackOpsGamertagOffsets.bo2zmOffsets) {
            getMem(offset, o -> {
                String gamertag = (String) o;
                if (gamertag != null && gamertag.length() > 0) {
                    gamertags.add(gamertag);
                }
            });

        }
        onGamertagsFetchedListener.fetched(gamertags);
    }

    public void getGamertagsBlackOpsIII(OnGamertagsFetchedListener onGamertagsFetchedListener) {
        ArrayList<String> gamertags = new ArrayList<>();
        for (String offset : XboxCommands.BlackOpsGamertagOffsets.bo3zmOffsets) {
            getMem(offset, o -> {
                String gamertag = (String) o;
                if (gamertag != null && gamertag.length() > 0) {
                    gamertags.add(gamertag);
                }
            });
        }
        onGamertagsFetchedListener.fetched(gamertags);
    }

    public void sendMessageKillfeedBlackOpsII(String message) {
        String finalString = "A\\8242FB70\\T\\0\\A\\3\\1\\-1\\1\\1\\7/22\\4F2022" + String.format("%x", new BigInteger(1, message.getBytes())) + "22\\";
        jrpc2.write(finalString + finalString);
    }

    public void sendMessageKillfeedBlackOpsIII(String message) {
        String finalString = "consolefeatures ver=2 type=0 as=0 params=\"A\\82665968\\A\\3\\1\\0\\1\\0\\7/27\\3c2022" + String.format("%x", new BigInteger(1, message.getBytes())) + "22\\\"";
        xbdm.write(finalString);
    }

    private void getMem(String offset, OnResultListener onResultListener) {
        xbdm.writeAndListen("GETMEMEX ADDR=0x" + offset + " LENGTH=0x00001000", onResultListener);
    }

    public void clearGT() {
        setMem("c035261c".toUpperCase(), "000000000000000000000000000000000000000000000000000000000000000000");
    }

    public void dvdeject() {
        xbdm.write("dvdeject");
    }

    public void drivelist(OnListFetchedListener onListFetchedListener) {
        xbdm.writeAndListenMultiline("drivelist", onListFetchedListener);
    }

    /*
    * Drive: HDD:\
    * Dir: Games\
    * */
    public void dirList(OnListFetchedListener onListFetchedListener, String drive, String dir){
        xbdm.writeAndListenMultiline("dirlist name=\""+drive+dir+"\"", onListFetchedListener);
    }

//    public void gameList(String drive,String path){
//        ArrayList<String> directories = new ArrayList<>();
//        ArrayList<String> files = new ArrayList<>();
//    }
//
//    public String scanDir(String drive, String path, OnResultListener onResultListener){
//        ArrayList<String> dirList = new ArrayList<>();
//        dirList(lines -> {
//            for (String line : lines){
//                if (line.contains("directory\r\n")){
//                    dirList.add(line.substring(7,line.indexOf("\"",8)));
//                }else{
//                    if (line.contains("default.xex")){
//                        onResultListener.result(new Game(drive+path,drive+path+));
//                    } else if(line.contains("default_mp.xex")){
//
//                    }
//                }
//            }
//        },drive,path);
//    }

    public void shutdown(){
        xbdm.write("shutdown");
    }

    public void scareUser() {
        xbdm.write("khoungdm");
    }

    public void rebootToDash() {
        xbdm.write("magicboot");
    }

    public void stop(){
        xbdm.write("stop");
    }

    public void go(){
        xbdm.write("go");
    }

    public void setMem(String offset, String data) {
        xbdm.write("setmem addr=0x" + offset + " data=" + data);
    }

    public void getRuningTitleId(OnResultListener rl) {
        xbdm.writeAndListen("consolefeatures ver=2 type=16 params=\"A\\0\\A\\0\\\"", rl);
    }

    public void reboot() {
        xbdm.write("magicboot cold");
    }

    public void xNotify(String message) {
        xbdm.write("consolefeatures ver=2 type=12 params=\"A\\0\\A\\2\\2/5\\" + toHex(message) + "\\1\\34\\\"");
    }

    private static String toHex(String arg) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            return String.format("%x", new BigInteger(1, arg.getBytes(StandardCharsets.US_ASCII)));
        else
            return String.format("%x", new BigInteger(1, arg.getBytes(Charset.defaultCharset())));
    }

    public void xNotify(String hexMessage,int type) {
        xbdm.write("consolefeatures ver=2 type=12 params=\"A\\0\\A\\2\\2/8\\"+hexMessage+"\\1\\"+type+"\\\"");
    }

    public void setGamertag(String gamertag) {
        clearGT();
        setMem("c035261c".toUpperCase(), "00" + stringToHex(gamertag) + "0000");
    }

    public void connect(OnConnectionEstablishedListener onConnectionEstablishedListener) {
        OnXboxReachableListener onXboxReachableListener = new OnXboxReachableListener() {
            @Override
            public void reachable(){
                initConnection(new OnConnectionEstablishedListener() {
                    @Override
                    public void established() {
                        Log.d("XboxSocket","Connection established");
                        onConnectionEstablishedListener.established();
                    }

                    @Override
                    public void failed(Exception e) {
                        Log.d("XboxSocket","Failed connecting");
                        onConnectionEstablishedListener.failed(e);
                    }
                });
            }

            @Override
            public void notreachable() {
                onConnectionEstablishedListener.failed(new Exception("Not reachable"));
            }
        };
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                if (available(ipAddress, 730) && available(ipAddress, 1409)) {
                    Log.d("XboxSocket", "Console available");
                    onXboxReachableListener.reachable();
                } else {
                    Log.d("XboxSocket", "Console NOT available");
                    onXboxReachableListener.notreachable();
                }
                return null;
            }
        }.execute();
    }

    public void initConnection(OnConnectionEstablishedListener onConnectionEstablishedListener) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Log.d("XboxSocket", "InitConnection");
                    xbdm.connect();
                    jrpc2.connect();
                    System.out.println("Xbox available and connected");
                    onConnectionEstablishedListener.established();
                } catch (Exception e) {
                    onConnectionEstablishedListener.failed(e);
                }
                return null;
            }
        }.execute();
    }

    public void plainCommandXbdm(String command) {
        xbdm.write(command);
    }

    public void plainCommandWithResultXbdm(String command, OnResultListener onResultListener) {
        xbdm.writeAndListen(command, onResultListener);
    }

    public String plainCommandWithStringResultXbdm(String command) {
        return xbdm.writeAndListenforStrings(command);
    }

    public void plainCommandJRPC2(String command) {
        jrpc2.write(command);
    }

    public void plainCommandWithResultJRPC2(String command, OnResultListener onResultListener) {
        jrpc2.writeAndListen(command, onResultListener::result);
    }

    public void getConsole(OnConsoleFetchedListener onConsoleFetchedListener) {
        Console console = Console.getConsole();
        xbdm.writeAndListen(XboxCommands.getCpuKey, o -> console.setCpukey(((String) o).substring(21, ((String) o).length()).trim()));
        xbdm.writeAndListen(XboxCommands.getDashboardVersion, o -> console.setDashboard(((String) o).substring(4, ((String) o).length()).trim()));
        xbdm.writeAndListen(XboxCommands.getCpuTemp, o -> console.setCpuTemp(hex2decimal(((String) o).substring(4, ((String) o).length()).trim())));
        xbdm.writeAndListen(XboxCommands.getRamTemp, o -> console.setRamTemp(hex2decimal(((String) o).substring(4, ((String) o).length()).trim())));
        xbdm.writeAndListen(XboxCommands.getGpuTemp, o -> console.setGpuTemp(hex2decimal(((String) o).substring(4, ((String) o).length()).trim())));
        xbdm.writeAndListen(XboxCommands.getMotherboardTemp, o -> console.setMotherboardTemp(hex2decimal(((String) o).substring(4, ((String) o).length()).trim())));
        xbdm.writeAndListen(XboxCommands.getBoardName, o -> console.setBoardName(((String) o).substring(4, ((String) o).length()).trim()));
        xbdm.writeAndListen(XboxCommands.getDebugName, o -> {
            console.setDebugName(((String) o).substring(4, ((String) o).length()).trim());
            onConsoleFetchedListener.fetched(console);
        });

    }

    public int hex2decimal(String s) {
        String digits = "0123456789ABCDEF";
        s = s.toUpperCase();
        int val = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int d = digits.indexOf(c);
            val = 16 * val + d;
        }
        return val;
    }

    public interface OnListFetchedListener {
        void fetched(ArrayList<String> lines);
    }

    public interface OnXboxReachableListener {
        void reachable();

        void notreachable();
    }

    public interface OnConnectionEstablishedListener {
        void established();

        void failed(Exception e);
    }

    public interface OnResultListener {
        void result(Object o);
    }

    public interface OnConsoleFetchedListener {
        void fetched(Console console);
    }

    public interface OnGamertagsFetchedListener {
        void fetched(ArrayList<String> gamertags);
    }

    public interface OnNetworkScanListener {
        void finished(boolean isXbox);
    }
}

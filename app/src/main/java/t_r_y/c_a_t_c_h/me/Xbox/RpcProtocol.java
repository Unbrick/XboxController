package t_r_y.c_a_t_c_h.me.Xbox;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Admin on 03.12.2016.
 */

class RpcProtocol {
    private Socket socket;
    private Pattern p = Pattern.compile("([A-Za-z])\\w{3,15}");
    private InetSocketAddress inetSocketAddress;
    private BufferedReader bufferedReader;
    private DataOutputStream dataOutputStream;
    private String jrpc2lineFinish = "Good\r\n";
    private String xbdmLineFinish = "\r\n";
    private String lineFinish = null;

    RpcProtocol(String ip, int port) {
        inetSocketAddress = new InetSocketAddress(ip,port);
        socket = new Socket();
        if (this instanceof XBDM)
            lineFinish = xbdmLineFinish;
        else if (this instanceof JRPC2)
            lineFinish = jrpc2lineFinish;
    }

    public void connect() throws ConnectException{
        try {
            int timeout = 5000;
            socket.connect(inetSocketAddress, timeout);
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new ConnectException("Could not connect, "+e.getLocalizedMessage());
        }
    }

    public void write(String s){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    dataOutputStream.flush();
                    System.out.println("Writing to socket: "+s);
                    dataOutputStream.writeBytes(s+lineFinish);
                    System.out.println("Socket answer: "+bufferedReader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    public void writeAndListen(String s, XboxSocket.OnResultListener onResultListener){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    System.out.println("Writing to socket: "+s);
                    dataOutputStream.flush();
                    dataOutputStream.writeBytes(s+lineFinish);
                    char[] buffer = new char[1024];
                    int read = 0;
                    StringBuilder sb = new StringBuilder();
                    while ((read = bufferedReader.read(buffer, 0, buffer.length)) > 0) {
                        sb.append(buffer, 0, read);

                        if (read !=16)
                            break;
                    }
                    System.out.println("Socket answer: "+sb.toString());
                    onResultListener.result(sb.toString());
                }catch(IOException e){
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    public void writeAndListenMultiline(String s, XboxSocket.OnListFetchedListener onListFetchedListener){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    System.out.println("Writing to socket: "+s);
                    dataOutputStream.flush();
                    dataOutputStream.writeBytes(s+lineFinish);
                    char[] buffer = new char[1024];
                    int read = 0;
                    StringBuilder sb = new StringBuilder();
                    while ((read = bufferedReader.read(buffer, 0, buffer.length)) > 0) {
                        sb.append(buffer, 0, read);

                        if (sb.toString().contains("\r\n.")||sb.toString().contains("denied"))
                            break;
                    }
                    System.out.println("Socket answer: "+sb.toString());
                    ArrayList<String> lines = new ArrayList<>();
                    Collections.addAll(lines, sb.toString().split("\\r?\\n"));
                    onListFetchedListener.fetched(lines);
                }catch(IOException e){
                    e.printStackTrace();
                    return null;
                }
                return null;
            }
        }.execute();
    }

    public String writeAndListenforStrings(String s){
        try {
            dataOutputStream.flush();
            dataOutputStream.writeBytes(s+lineFinish);
            char[] buffer = new char[1024];
            int read = 0;
            StringBuilder sb = new StringBuilder();
            while ((read = bufferedReader.read(buffer, 0, buffer.length)) > 0) {
                sb.append(buffer, 0, read);
                if (sb.toString().length() > 50) {
                    String stringToMatch = sb.toString().substring(50, sb.toString().length()).trim();
                    Matcher m = p.matcher(stringToMatch);
                    if (m.find()) {
                        System.out.println(m.group());
                        if (!m.group().equals("binary")){
                            return m.group();
                        }
                        break;
                    }
                }

            }

            return sb.toString();
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }
}

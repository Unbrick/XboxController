package com.unbrick.xbox.XBDM.threads;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;

public abstract class BaseXBDMThread extends Thread{
    protected int length;
    private InetSocketAddress socketAddress;
    private Socket socket = new Socket();
    protected DataOutputStream dataOutputStream;
    protected BufferedReader bufferedReader;
    protected byte[] bytes;
    private String lineFinish = "\r\n";

    public BaseXBDMThread(InetSocketAddress socketAddress, String command, int length) {
        this.bytes = Arrays.copyOf((command + lineFinish).getBytes(), (command + lineFinish).getBytes().length);
        this.length = length;
        this.socketAddress = socketAddress;
    }

    public BaseXBDMThread(InetSocketAddress socketAddress, String command) {
        this(socketAddress, command, 1024);
    }

    public BaseXBDMThread(InetSocketAddress inetSocketAddress) {this(inetSocketAddress, null, 1024);}

    public void preRun() throws ConnectException {
        try {
            int timeout = 5000;
            socket.connect(socketAddress, timeout);
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new ConnectException("Could not connect, " + e.getLocalizedMessage());
        }
    }

    public void postRun() {
        try {
            dataOutputStream.writeBytes("BYE" + lineFinish);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected String sanitizeOutput(String input) {
        input = input.replaceAll("200- ", "");
        input = input.replaceAll("201- connected\r\n", "");
        input = input.replaceAll("202- memory data follows\r\n", "");
        input = input.replaceAll("202- multiline response follows\r\n", "");
        input = input.replaceAll("203- binary response follows\r\n", "");
        input = input.replaceAll("204- send binary data\r\n", "");
        input = input.replaceAll("205- connection dedicated\r\n", "");
        input = input.replaceAll("\r\n", "").trim();
        return input;
    }
}

package com.unbrick.xbox.XBDM.threads;

import java.io.IOException;
import java.net.InetSocketAddress;

import static com.unbrick.xbox.XBDM.HexUtils.formatHexDump;

public class SingleLineWriteXBDMThread extends BaseXBDMThread {

    public SingleLineWriteXBDMThread(InetSocketAddress socketAddress, String command, int length) {
        super(socketAddress, command, length);
    }

    public SingleLineWriteXBDMThread(InetSocketAddress socketAddress, String command) {
        super(socketAddress, command);
    }

    @Override
    public void run() {
        try {
            preRun();
            dataOutputStream.flush();
            System.out.println("Writing to socket: ");
            System.out.println(formatHexDump(bytes, 0, bytes.length));
            dataOutputStream.write(bytes);
            System.out.println("Socket answer: " + bufferedReader.readLine());
            postRun();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

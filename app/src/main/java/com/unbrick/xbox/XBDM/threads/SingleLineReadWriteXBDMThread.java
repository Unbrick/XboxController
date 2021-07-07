package com.unbrick.xbox.XBDM.threads;

import com.unbrick.xbox.XBDM.listener.OnResultListener;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Arrays;

public class SingleLineReadWriteXBDMThread extends BaseXBDMThread{
    OnResultListener onResultListener = null;

    public SingleLineReadWriteXBDMThread(InetSocketAddress inetSocketAddress, String command, int length, OnResultListener onResultListener) {
        super(inetSocketAddress,command, length);
        this.onResultListener = onResultListener;
    }

    public SingleLineReadWriteXBDMThread(InetSocketAddress inetSocketAddress, String command, int length) {
        super(inetSocketAddress,command, length);
    }

    public SingleLineReadWriteXBDMThread(InetSocketAddress inetSocketAddress, String command) {
        super(inetSocketAddress,command);
    }

    @Override
    public void run() {
        try {
            preRun();
            System.out.println("Writing to socket: " + Arrays.toString(bytes));
            dataOutputStream.flush();
            dataOutputStream.write(bytes);
            char[] buffer = new char[length];
            int read = 0;
            StringBuilder sb = new StringBuilder();
            while ((read = bufferedReader.read(buffer, 0, buffer.length)) > 0) {
                sb.append(buffer, 0, read);

                if (read != 16)
                    break;
            }
            String result = sanitizeOutput(sb.toString());
            System.out.println("Socket answer: " + result);
            postRun();

            if (onResultListener != null)
                onResultListener.result(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

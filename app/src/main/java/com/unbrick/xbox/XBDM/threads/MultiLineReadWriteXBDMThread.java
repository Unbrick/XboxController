package com.unbrick.xbox.XBDM.threads;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.unbrick.xbox.XBDM.listener.OnListFetchedListener;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MultiLineReadWriteXBDMThread extends BaseXBDMThread {
    OnListFetchedListener mOnListFechedListner;

    public MultiLineReadWriteXBDMThread(InetSocketAddress inetSocketAddress, String command, int length, OnListFetchedListener onListFetchedListener) {
        super(inetSocketAddress, command, length);
        this.mOnListFechedListner = onListFetchedListener;
    }

    public MultiLineReadWriteXBDMThread(InetSocketAddress inetSocketAddress, String command, int length) {
        super(inetSocketAddress, command, length);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void run() {
        try {
            preRun();
            System.out.println("Writing to socket: " + Arrays.toString(bytes));
            dataOutputStream.flush();
            dataOutputStream.write(bytes);
            char[] buffer = new char[1024];
            int read = 0;
            StringBuilder sb = new StringBuilder();
            while ((read = bufferedReader.read(buffer, 0, buffer.length)) > 0) {
                sb.append(buffer, 0, read);

                if (sb.toString().contains("\r\n.") || sb.toString().contains("denied"))
                    break;
            }
            System.out.println("Socket answer: " + sb.toString());
            ArrayList<String> lines = new ArrayList<>();
            Collections.addAll(lines, sb.toString().split("\\r?\\n"));
            lines.removeIf(s -> s.equals("202- multiline response follows\r\n"));
            mOnListFechedListner.fetched(lines);
            postRun();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.unbrick.xbox.Xbox;

import android.util.Log;

import com.tramsun.libs.prefcompat.Pref;

import java.io.IOException;
import java.net.Socket;

import com.unbrick.xbox.XBDM.XBDM;
import com.unbrick.xbox.XBDM.listener.OnConnectionEstablishedListener;

/**
 * Created by Admin on 03.12.2016.
 */

public class XboxSocket {
    private static XboxSocket instance;
    private String ipAddress;
    public XBDM xbdm;

    private XboxSocket(String ip) {
        this.ipAddress = ip;
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

    private static boolean available(String ip, int port) {
        System.out.println("--------------Testing port " + port);
        Socket s = null;
        try {
            s = new Socket(ip, port);
            s.setSoTimeout(1000);
            s.close();
            System.out.println(ip + "\r\n--------------Port " + port + " is open");
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

    public void connect(OnConnectionEstablishedListener onConnectionEstablishedListener) {
        new Thread(() -> {
            if (available(ipAddress, 730)) {
                Log.d("XboxSocket", "Console available");
                onConnectionEstablishedListener.established();
            } else {
                Log.d("XboxSocket", "Console NOT available");
                onConnectionEstablishedListener.failed(new Exception());
            }
        }).start();
    }











}

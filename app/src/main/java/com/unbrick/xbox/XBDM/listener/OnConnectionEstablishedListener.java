package com.unbrick.xbox.XBDM.listener;

public interface OnConnectionEstablishedListener {
    void established();

    void failed(Exception e);
}
package com.unbrick.xbox.XBDM;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.unbrick.xbox.RPC.XDRPCArgumentInfo;
import com.unbrick.xbox.XBDM.listener.OnListFetchedListener;
import com.unbrick.xbox.XBDM.listener.OnResultListener;
import com.unbrick.xbox.XBDM.threads.MultiLineReadWriteXBDMThread;
import com.unbrick.xbox.XBDM.threads.SingleLineReadWriteXBDMThread;
import com.unbrick.xbox.XBDM.threads.SingleLineWriteXBDMThread;
import com.unbrick.xbox.XBDM.threads.rpc.RPCCallByAddressThread;
import com.unbrick.xbox.XBDM.threads.rpc.RPCCallByOrdinalThread;

/**
 * Created by Admin on 03.12.2016.
 */

class RpcProtocol {
    private InetSocketAddress inetSocketAddress;
    ExecutorService mExecutor = (ExecutorService) Executors.newSingleThreadExecutor();

    RpcProtocol(String ip, int port) {
        inetSocketAddress = new InetSocketAddress(ip, port);
    }

    @SuppressWarnings("rawtypes")
    public void callAddress(long address, ArrayList<XDRPCArgumentInfo> params) {
        mExecutor.submit(new RPCCallByAddressThread(inetSocketAddress, address, params));
    }

    public void callOrdinal(String module, int ordinal, ArrayList<XDRPCArgumentInfo> params) {
        mExecutor.submit(new RPCCallByOrdinalThread(inetSocketAddress, module, ordinal, params));
    }

    public void write(String s) {
        mExecutor.submit(new SingleLineWriteXBDMThread(inetSocketAddress, s));
    }

    public void writeAndListen(String s, OnResultListener onResultListener) {
        mExecutor.submit(new SingleLineReadWriteXBDMThread(inetSocketAddress, s, 1024, onResultListener));
    }

    public void writeAndListenMultiline(String s, OnListFetchedListener onListFetchedListener) {
        mExecutor.submit(new MultiLineReadWriteXBDMThread(inetSocketAddress, s, 1024, onListFetchedListener));
    }
}

package t_r_y.c_a_t_c_h.me.AsyncTasks;

import android.app.Activity;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.Socket;

import t_r_y.c_a_t_c_h.me.Interfaces.OnNetworkScanListener;

/**
 * Created by Admin on 10.09.2016.
 */
public class AsyncTaskScanNetwork extends AsyncTask<Void,Void,Boolean> {

    private OnNetworkScanListener onsl;
    private String ip;
    private Activity activity;

    public AsyncTaskScanNetwork(Activity activity,String ip, OnNetworkScanListener onsl){
        this.onsl = onsl;
        this.ip = ip;
        this.activity = activity;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {

        if (!available(ip,730) && !available(ip,1409)){
            onsl.finished(false);
            return null;
        }else if (available(ip,1409))
            onsl.finished(true);

        return null;
    }

    private static boolean available(String ip,int port) {
        System.out.println("--------------Testing port " + port);
        Socket s = null;
        try {
            s = new Socket(ip, port);
            s.setSoTimeout(100);

            s.close();

            // If the code makes it this far without an exception it means
            // something is using the port and has responded.
            System.out.println(ip+"--------------Port " + port + " is open");
            return true;
        } catch (IOException e) {
            System.out.println(ip+"--------------Port " + port + " is closed");
            return false;
        } finally {
            if( s != null){
                try {
                    s.close();
                } catch (IOException e) {
                    throw new RuntimeException("You should handle this error." , e);
                }
            }
        }
    }
}

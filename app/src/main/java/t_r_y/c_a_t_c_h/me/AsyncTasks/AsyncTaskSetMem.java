package t_r_y.c_a_t_c_h.me.AsyncTasks;

import android.os.AsyncTask;

import com.tramsun.libs.prefcompat.Pref;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Admin on 07.09.2016.
 */
public class AsyncTaskSetMem extends AsyncTask<String,Void,Void> {
    @Override
    protected Void doInBackground(String... strings) {
        String offset = strings[0];
        String data = strings[1];
        Socket socket = null;
        try {
            socket = new Socket(Pref.getString("ip"), 730);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.writeBytes("setmem addr=0x"+offset+" data="+data+"\r\n");
            System.out.println(in.readLine());
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}

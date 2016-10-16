package t_r_y.c_a_t_c_h.me.AsyncTasks;

import android.os.AsyncTask;

import com.tramsun.libs.prefcompat.Pref;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Admin on 11.09.2016.
 */
public class AsyncTaskPlainCommandJRPC2 extends AsyncTask<Void,Void,Void> {

    ArrayList<String> commands = new ArrayList<>();
    String command;
    String good = "Good";
    String lineFinish = "\r\n";

    public AsyncTaskPlainCommandJRPC2(String command){
        this.command = command;
    }

    public AsyncTaskPlainCommandJRPC2(String...strings){
        Collections.addAll(commands, strings);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Socket socket = null;
        try {
            socket = new Socket(Pref.getString("ip"), 1409);
            socket.setSoTimeout(1000);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            if (command != null){
                out.writeBytes(command+lineFinish+command+lineFinish+good+lineFinish);
            }else{
                for (String command : commands){
                    out.writeBytes(command+lineFinish+command+lineFinish+good+lineFinish);
                }
            }

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

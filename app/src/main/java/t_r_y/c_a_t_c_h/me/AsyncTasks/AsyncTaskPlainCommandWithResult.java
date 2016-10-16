package t_r_y.c_a_t_c_h.me.AsyncTasks;

import android.os.AsyncTask;

import com.tramsun.libs.prefcompat.Pref;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import t_r_y.c_a_t_c_h.me.Interfaces.ResultListener;

/**
 * Created by Admin on 07.09.2016.
 */
public class AsyncTaskPlainCommandWithResult extends AsyncTask<String,Void,Void> {

    private ResultListener resultListener;

    public AsyncTaskPlainCommandWithResult(ResultListener resultListener){
        this.resultListener = resultListener;
    }

    @Override
    protected Void doInBackground(String... strings) {
        Socket socket = null;
        try {
            System.out.println(strings[0]);
            System.out.println(Pref.getString("ip"));
            socket = new Socket(Pref.getString("ip"), 730);
            socket.setSoTimeout(1000);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.writeBytes(strings[0]+"\r\n");
            char[] buffer = new char[1024];
            int read = 0;
            StringBuilder sb = new StringBuilder();

            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                sb.append(buffer, 0, read);

                if (read !=16)
                    break;
            }
            reader.close();

            String str = sb.toString().trim();
            System.out.println(str);
            resultListener.onResult(sb.toString().substring(21,sb.toString().length()));
            return null;
        } catch (Exception e) {
            try {
                System.out.println(strings[0]);
                System.out.println(Pref.getString("ip"));
                socket = new Socket(Pref.getString("ip"), 730);
                socket.setSoTimeout(1000);
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out.writeBytes(strings[0]+"\r\n");
                char[] buffer = new char[1024];
                int read = 0;
                StringBuilder sb = new StringBuilder();

                while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                    sb.append(buffer, 0, read);

                    if (read >16)
                        break;
                }
                reader.close();

                String str = sb.toString().trim();
                System.out.println(str);
                resultListener.onResult(sb.toString().substring(21,sb.toString().length()));
            }catch(Exception e1){
                resultListener.onResult("");
                e1.printStackTrace();
            }
            e.printStackTrace();
            return null;
        } finally{
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

package t_r_y.c_a_t_c_h.me.AsyncTasks;

import android.os.AsyncTask;

import com.tramsun.libs.prefcompat.Pref;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import t_r_y.c_a_t_c_h.me.Objects.Console;

/**
 * Created by Admin on 07.09.2016.
 */
public class AsyncTaskCycleTray extends AsyncTask<String,Void,Void> {
    @Override
    protected Void doInBackground(String... strings) {
        Socket socket = null;
        Console console = Console.getConsole();
        try {
            socket = new Socket(Pref.getString("ip"), 730);
            socket.setSoTimeout(5000);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            if (console.isTrayOpen()){
                //Close tray
                out.writeBytes("consolefeatures ver=2 type=1 system module=\"xboxkrnl.exe\" ord=41 as=0 params=\"A\\0\\A\\2\\7/16\\8B620000000000000000000000000000\\8\\0\\\""+"\r\n");
                console.setTrayOpen(false);
                toggleTray(out,reader);
            }else{
                //Open tray
                out.writeBytes("consolefeatures ver=2 type=1 system module=\"xboxkrnl.exe\" ord=41 as=0 params=\"A\\0\\A\\2\\7/16\\8B600000000000000000000000000000\\8\\0\\\""+"\r\n");
                console.setTrayOpen(true);
                toggleTray(out,reader);
            }

            reader.close();
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

    private boolean toggleTray(DataOutputStream out, BufferedReader reader) throws IOException{
        out.writeBytes("consolefeatures ver=2 type=1 system module=\"xboxkrnl.exe\" ord=41 as=0 params=\"A\\0\\A\\3\\7/16\\0A000000000000000000000000000000\\1\\-2119391008\\8\\0\\\""+"\r\n");
        char[] buffer = new char[1024];
        int read = 0;
        StringBuilder sb = new StringBuilder();

        while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
            sb.append(buffer, 0, read);
            System.out.println(sb.toString());

            if (read !=16)
                break;
        }
        String response = sb.toString();

        String buff_addr = response.substring(response.indexOf("=")+1,response.length()).trim();

        System.out.println(buff_addr);



        out.writeBytes("consolefeatures buf_addr=0x"+buff_addr+"\r\n");
        buffer = new char[1024];
        read = 0;
        sb = new StringBuilder();

        while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
            sb.append(buffer, 0, read);
            System.out.println(sb.toString());

            if (read !=16)
                break;
        }

        response = sb.toString();

        int state = Integer.valueOf(response.substring(4,response.length()).trim());
        return state == 1;
    }

}

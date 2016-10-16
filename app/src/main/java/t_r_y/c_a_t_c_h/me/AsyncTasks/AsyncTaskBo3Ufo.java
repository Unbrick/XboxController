package t_r_y.c_a_t_c_h.me.AsyncTasks;

import android.os.AsyncTask;

import com.tramsun.libs.prefcompat.Pref;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Admin on 12.09.2016.
 */
public class AsyncTaskBo3Ufo extends AsyncTask<Void,Void,Void> {

    String good = "Good";
    String lineFinish = "\r\n";
    private boolean enabled;

    public AsyncTaskBo3Ufo(boolean enabled){
        this.enabled = enabled;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Socket socketxbdm = null;
        Socket socketjrpc2 = null;
        try {
            socketxbdm = new Socket(Pref.getString("ip"), 730);
            socketxbdm.setSoTimeout(1000);
            DataOutputStream outxbdm = new DataOutputStream(socketxbdm.getOutputStream());
            BufferedReader readerxbdm = new BufferedReader(new InputStreamReader(socketxbdm.getInputStream()));

            socketjrpc2 = new Socket(Pref.getString("ip"), 1409);
            socketjrpc2.setSoTimeout(1000);
            DataOutputStream outjrpc2 = new DataOutputStream(socketjrpc2.getOutputStream());
            BufferedReader readerjrpc2 = new BufferedReader(new InputStreamReader(socketjrpc2.getInputStream()));

            if (enabled){
                xbdm("setmem addr=0x8451bdd3 data=01",outxbdm,readerxbdm);
                xbdm("setmem addr=0x8451beba data=00",outxbdm,readerxbdm);

                jrpc2("consolefeatures ver=2 type=0 as=0 params=\"A\\82665968\\A\\3\\1\\0\\1\\0\\7/28\\3C20225E3755464F204D6F6465203A205E31456E61626C696E672122\\\"",outjrpc2,readerjrpc2);
                jrpc2("consolefeatures buf_addr=0x79052F38",outjrpc2,readerjrpc2);
                jrpc2("consolefeatures ver=2 type=0 as=0 params=\"A\\82665968\\A\\3\\1\\0\\1\\0\\7/27\\3C20225E3755464F204D6F6465203A205E31456E61626C65642122\\\"",outjrpc2,readerjrpc2);
                jrpc2("consolefeatures buf_addr=0x79052F38",outjrpc2,readerjrpc2);

                xbdm("setmem addr=0x8451bdd3 data=02",outxbdm,readerxbdm);
            }else{
                xbdm("setmem addr=0x8451bdd3 data=01",outxbdm,readerxbdm);
                xbdm("setmem addr=0x8451beba data=80",outxbdm,readerxbdm);

                jrpc2("consolefeatures ver=2 type=0 as=0 params=\"A\\82665968\\A\\3\\1\\0\\1\\0\\7/29\\3C20225E3755464F204D6F6465203A205E3144697361626C696E672122\\\"",outjrpc2,readerjrpc2);

                xbdm("setmem addr=0x8451bdd3 data=00",outxbdm,readerxbdm);

                jrpc2("consolefeatures ver=2 type=0 as=0 params=\"A\\82665968\\A\\3\\1\\0\\1\\0\\7/28\\3C20225E3755464F204D6F6465203A205E3144697361626C65642122\\\"",outjrpc2,readerjrpc2);
            }

            readerjrpc2.close();
            readerjrpc2.close();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socketxbdm != null) {
                try {
                    socketxbdm.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socketjrpc2 != null) {
                try {
                    socketjrpc2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private void jrpc2(String command, DataOutputStream out, BufferedReader reader) throws IOException{
        out.writeBytes(command+lineFinish+command+lineFinish+good+lineFinish);
        char[] buffer = new char[1024];
        int read = 0;
        StringBuilder sb = new StringBuilder();
        while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
            sb.append(buffer, 0, read);

            if (read != 16)
                break;
        }
        System.out.println(sb.toString());
    }

    private void xbdm(String command, DataOutputStream out, BufferedReader reader) throws IOException{
        out.writeBytes(command+lineFinish);
        char[] buffer = new char[1024];
        int read = 0;
        StringBuilder sb = new StringBuilder();
        while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
            sb.append(buffer, 0, read);

            if (read != 16)
                break;
        }
        System.out.println(sb.toString());
    }

}

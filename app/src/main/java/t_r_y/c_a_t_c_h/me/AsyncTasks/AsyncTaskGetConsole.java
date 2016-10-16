package t_r_y.c_a_t_c_h.me.AsyncTasks;

import android.os.AsyncTask;

import com.tramsun.libs.prefcompat.Pref;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import t_r_y.c_a_t_c_h.me.Interfaces.OnConsoleFetched;
import t_r_y.c_a_t_c_h.me.Objects.Console;

/**
 * Created by Admin on 10.09.2016.
 */
public class AsyncTaskGetConsole extends AsyncTask<Void,Void,Void> {

    private OnConsoleFetched resultListener;

    public AsyncTaskGetConsole(OnConsoleFetched rl){
        this.resultListener = rl;
    }

    public AsyncTaskGetConsole(){
        this.resultListener = null;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Socket socket = null;
        String cpuKey = null;
        String dashboard = null;
        String cpuTemp;
        Console console = Console.getConsole();
        try {
            socket = new Socket(Pref.getString("ip"), 730);
            socket.setSoTimeout(1000);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.writeBytes("consolefeatures ver=2 type=10 params=\"A\\0\\A\\0\\\""+"\r\n"); //CPUkey
            char[] buffer = new char[1024];
            int read = 0;
            StringBuilder sb = new StringBuilder();
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                sb.append(buffer, 0, read);

                if (read !=16)
                    break;
            }
            cpuKey = sb.toString();
            console.setCpukey(cpuKey.substring(21,cpuKey.length()).trim());

            out.writeBytes("consolefeatures ver=2 type=13 params=\"A\\0\\A\\0\\\""+"\r\n"); //Dashboard
            buffer = new char[1024];
            read = 0;
            sb = new StringBuilder();
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                sb.append(buffer, 0, read);

                if (read !=16)
                    break;
            }
            dashboard = sb.toString();
            console.setDashboard(dashboard.substring(4,dashboard.length()).trim());

            out.writeBytes("consolefeatures ver=2 type=15 params=\"A\\0\\A\\1\\1\\0\\\""+"\r\n"); //CPUTemp
            buffer = new char[1024];
            read = 0;
            sb = new StringBuilder();
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                sb.append(buffer, 0, read);

                if (read !=16)
                    break;
            }
            cpuTemp = sb.toString();
            console.setCpuTemp(hex2decimal(cpuTemp.substring(4,cpuTemp.length()).trim()));


            out.writeBytes("consolefeatures ver=2 type=15 params=\"A\\0\\A\\1\\1\\2\\\""+"\r\n"); //RamTemp
            buffer = new char[1024];
            read = 0;
            sb = new StringBuilder();
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                sb.append(buffer, 0, read);

                if (read !=16)
                    break;
            }
            String ramTemp = sb.toString();
            console.setRamTemp(hex2decimal(ramTemp.substring(4,ramTemp.length()).trim()));

            out.writeBytes("consolefeatures ver=2 type=15 params=\"A\\0\\A\\1\\1\\1\\\""+"\r\n"); //gpuTemp
            buffer = new char[1024];
            read = 0;
            sb = new StringBuilder();
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                sb.append(buffer, 0, read);

                if (read !=16)
                    break;
            }
            String gpuTemp = sb.toString();
            console.setGpuTemp(hex2decimal(gpuTemp.substring(4,gpuTemp.length()).trim()));


            out.writeBytes("consolefeatures ver=2 type=15 params=\"A\\0\\A\\1\\1\\3\\\""+"\r\n"); //motherboardTemp
            buffer = new char[1024];
            read = 0;
            sb = new StringBuilder();
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                sb.append(buffer, 0, read);

                if (read !=16)
                    break;
            }
            String motherboardTemp = sb.toString();
            console.setMotherboardTemp(hex2decimal(motherboardTemp.substring(4,motherboardTemp.length()).trim()));

            out.writeBytes("consolefeatures ver=2 type=17 params=\"A\\0\\A\\0\\\""+"\r\n"); //boardName
            buffer = new char[1024];
            read = 0;
            sb = new StringBuilder();
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                sb.append(buffer, 0, read);

                if (read !=16)
                    break;
            }
            String boardName = sb.toString();
            console.setBoardName(boardName.substring(4,boardName.length()).trim());

            out.writeBytes("DBGNAME"+"\r\n"); //debugName
            buffer = new char[1024];
            read = 0;
            sb = new StringBuilder();
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                sb.append(buffer, 0, read);

                if (read !=16)
                    break;
            }
            String debugName = sb.toString();
            console.setDebugName(debugName.substring(4,debugName.length()).trim());

            reader.close();

            if (resultListener != null)
                resultListener.finished(console);

            return null;
        }catch(Exception e){
            if (resultListener != null)
                resultListener.finished(null);
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

    public static int hex2decimal(String s) {
        String digits = "0123456789ABCDEF";
        s = s.toUpperCase();
        int val = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int d = digits.indexOf(c);
            val = 16*val + d;
        }
        return val;
    }
}

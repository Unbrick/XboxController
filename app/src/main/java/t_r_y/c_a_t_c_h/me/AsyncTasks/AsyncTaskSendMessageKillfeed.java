package t_r_y.c_a_t_c_h.me.AsyncTasks;

import android.os.AsyncTask;

import com.tramsun.libs.prefcompat.Pref;

import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;

/**
 * Created by Admin on 07.09.2016.
 */
public class AsyncTaskSendMessageKillfeed extends AsyncTask<String,Void,Void> {

    public static final int BO2_MP = 1;
    public static final int BO2_ZM = 2;
    public static final int BO3_MP = 3;
    public static final int BO3_ZM = 4;
    private int game;
    private String message;

    public AsyncTaskSendMessageKillfeed(int game,String message){
        this.game = game;
        this.message = message;
    }

    @Override
    protected Void doInBackground(String... strings) {
        Socket socket = null;
        String finalString = null;
        DataOutputStream out = null;
        String hexString = String.format("%x", new BigInteger(1, message.getBytes()));
        String good = "Good\r\n";
        try {
            switch (game){
                case BO2_MP:
                    socket = getBo2Socket();
                    out = new DataOutputStream(socket.getOutputStream());
                    hexString = hexString.toUpperCase();
                    finalString = "A\\8242FB70\\T\\0\\A\\3\\1\\-1\\1\\1\\7/22\\4F2022"+hexString+"22\\"+"\r\n";
                    out.writeBytes(finalString+finalString+ good);
                    break;
                case BO2_ZM:
                    socket = getBo2Socket();
                    out = new DataOutputStream(socket.getOutputStream());
                    finalString = "A\\8242FB70\\T\\0\\A\\3\\1\\-1\\1\\1\\7/22\\4F2022"+hexString+"22\\" + "\r\n";
                    out.writeBytes(finalString+finalString+ good);
                    break;
                case BO3_MP:
                    break;
                case BO3_ZM:
                    socket = getBo3Socket();
                    out = new DataOutputStream(socket.getOutputStream());
                    finalString = "consolefeatures ver=2 type=0 as=0 params=\"A\\82665968\\A\\3\\1\\0\\1\\0\\7/27\\3c2022"+hexString+"22\\\"" + "\r\n";
                    out.writeBytes(finalString);
                    break;
            }

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

    private Socket getBo2Socket() throws IOException{
        Socket socket = new Socket(Pref.getString("ip"), 1409);
        socket.setSoTimeout(1000);
        return socket;
    }

    private Socket getBo3Socket() throws IOException{
        Socket socket = new Socket(Pref.getString("ip"), 730);
        socket.setSoTimeout(1000);
        return socket;
    }
}

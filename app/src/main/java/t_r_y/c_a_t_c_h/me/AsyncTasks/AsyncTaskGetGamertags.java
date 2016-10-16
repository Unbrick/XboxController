package t_r_y.c_a_t_c_h.me.AsyncTasks;

import android.os.AsyncTask;

import com.tramsun.libs.prefcompat.Pref;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import t_r_y.c_a_t_c_h.me.Interfaces.OnGamertagsFetchedListener;

/**
 * Created by Admin on 12.09.2016.
 */
public class AsyncTaskGetGamertags extends AsyncTask<Void,Void,Void> {

    public static final int BO2_MP = 1;
    public static final int BO2_ZM = 2;
    public static final int BO3_MP = 3;
    public static final int BO3_ZM = 4;
    private Pattern p = Pattern.compile("([A-Za-z])\\w{3,15}");
    ArrayList<String> offsets = new ArrayList<>();
    private String[] bo2zmOffsets = new String[]{
            "83556000",
            "8355c000",
            "83561000",
            "83567000"
    };
    private String[] bo3zmOffsets = new String[]{
            "8451b000",
            "84522000",
            "84528000",
            "8452e000"
    };
    private OnGamertagsFetchedListener onGamertagsFetchedListener;
    ArrayList<String> gamertags = new ArrayList<>();


    public AsyncTaskGetGamertags(int game, OnGamertagsFetchedListener onGamertagsFetchedListener){
        this.onGamertagsFetchedListener = onGamertagsFetchedListener;
        switch (game){
            case BO2_MP:
                break;
            case BO2_ZM:
                Collections.addAll(offsets, bo2zmOffsets);
                break;
            case BO3_MP:
                break;
            case BO3_ZM:
                Collections.addAll(offsets, bo3zmOffsets);
                break;
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Socket socket = new Socket(Pref.getString("ip"), 730);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            socket.setSoTimeout(1000);

            for (String offset : offsets) {
                out.writeBytes("GETMEMEX ADDR=0x" + offset + " LENGTH=0x00001000" + "\r\n");
                System.out.println("GETMEMEX ADDR=0x" + offset + " LENGTH=0x00001000");
                char[] buffer = new char[1024];
                int read = 0;
                StringBuilder sb = new StringBuilder();
                while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                    sb.append(buffer, 0, read);
                    if (sb.toString().length() > 50) {
                        String stringToMatch = sb.toString().substring(50, sb.toString().length()).trim();
                        Matcher m = p.matcher(stringToMatch);
                        if (m.find()) {
                            System.out.println(m.group());
                            if (!m.group().equals("binary")){
                                gamertags.add(m.group());
                            }
                            break;
                        }
                    }

                }
            }


            reader.close();
            socket.close();

            onGamertagsFetchedListener.fetched(gamertags);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            onGamertagsFetchedListener.fetched(gamertags);
            return null;
        }

    }
}

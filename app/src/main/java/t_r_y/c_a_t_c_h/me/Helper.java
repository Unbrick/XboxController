package t_r_y.c_a_t_c_h.me;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.androidadvance.topsnackbar.TSnackbar;

import java.math.BigInteger;

import t_r_y.c_a_t_c_h.me.AsyncTasks.AsyncTaskCycleTray;
import t_r_y.c_a_t_c_h.me.AsyncTasks.AsyncTaskPlainCommand;
import t_r_y.c_a_t_c_h.me.AsyncTasks.AsyncTaskPlainCommandWithResult;
import t_r_y.c_a_t_c_h.me.AsyncTasks.AsyncTaskSetMem;
import t_r_y.c_a_t_c_h.me.Interfaces.ResultListener;

/**
 * Created by Admin on 07.09.2016.
 */
public class Helper {

    public static void getCpuKey(ResultListener resultListener){
        new AsyncTaskPlainCommandWithResult(resultListener).execute("consolefeatures ver=2 type=10 params=\"A\\0\\A\\0\\\"");
    }

    public static void getRunningTitle(ResultListener resultListener){
        new AsyncTaskPlainCommandWithResult(resultListener).execute("consolefeatures ver=2 type=16 params=\"A\\0\\A\\0\\\"");
    }

    public static void getDashboardVersion(ResultListener resultListener){
        new AsyncTaskPlainCommandWithResult(resultListener).execute("consolefeatures ver=2 type=13 params=\"A\\0\\A\\0\\\"");
    }

    public static void xNotify(String message){
        new AsyncTaskPlainCommand().execute("consolefeatures ver=2 type=12 params=\"A\\0\\A\\2\\2/5\\"+String.format("%x", new BigInteger(1, message.getBytes()))+"\\1\\34\\\"");

    }

    public static void reboot(){
        new AsyncTaskPlainCommand().execute("magicboot cold");
    }

    public static void cycleTray(){
        new AsyncTaskCycleTray().execute();
    }

    public static void setGT(String gamertag){
        new AsyncTaskSetMem().execute("c035261c".toUpperCase(),"000000000000000000000000000000000000000000000000000000000000000000");
        new AsyncTaskSetMem().execute("c035261c".toUpperCase(),"00"+stringToHex(gamertag)+"0000");
    }

    public static void clearGT(){
        new AsyncTaskSetMem().execute("c035261c".toUpperCase(),"000000000000000000000000000000000000000000000000000000000000000000");
    }

    public static String stringToHex(String string){
        return String.format("%x", new BigInteger(1, string.getBytes()));
    }

    public static void makeSnackbar(Activity activity,String message){
        TSnackbar snackbar = TSnackbar.make(activity.findViewById(R.id.snackbarContainer), message, TSnackbar.LENGTH_LONG);
        snackbar.setIconLeft(R.drawable.xbox, 24);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(Color.WHITE);
        snackbar.show();
    }

    public static void makeSnackbarError(Activity activity,String message){
        TSnackbar snackbar = TSnackbar.make(activity.findViewById(R.id.snackbarContainer), message, TSnackbar.LENGTH_LONG);
        snackbar.setIconLeft(R.drawable.xbox, 24);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(Color.WHITE);
        TextView textView = (TextView) snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
        textView.setTextColor(Color.RED);
        snackbar.show();
    }

}

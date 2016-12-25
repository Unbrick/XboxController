package t_r_y.c_a_t_c_h.me.Helper;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.androidadvance.topsnackbar.TSnackbar;

import java.math.BigInteger;

import t_r_y.c_a_t_c_h.me.R;

/**
 * Created by Admin on 07.09.2016.
 */
public class Helper {

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

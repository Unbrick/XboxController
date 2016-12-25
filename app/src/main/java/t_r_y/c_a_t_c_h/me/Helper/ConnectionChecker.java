package t_r_y.c_a_t_c_h.me.Helper;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;

/**
 * Created by Admin on 13.09.2016.
 */
public class ConnectionChecker {

    private Activity mainActivity;
    private Handler mHandler;
    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            Log.d(getClass().getSimpleName(), "Issued");
            //TODO check connection

        }
    };


    public ConnectionChecker(Activity mainActivity) {
        this.mainActivity = mainActivity;
        mHandler = new Handler();
    }

    public ConnectionChecker runChecker() {
        mStatusChecker.run();
        return this;
    }

    public ConnectionChecker stopChecker() {
        mHandler.removeCallbacks(mStatusChecker);
        return this;
    }
}

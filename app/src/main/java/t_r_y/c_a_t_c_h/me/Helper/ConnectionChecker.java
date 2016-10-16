package t_r_y.c_a_t_c_h.me.Helper;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;

import t_r_y.c_a_t_c_h.me.AsyncTasks.AsyncTaskPlainCommandWithResult;
import t_r_y.c_a_t_c_h.me.Interfaces.ResultListener;

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
            new AsyncTaskPlainCommandWithResult(new ResultListener() {
                @Override
                public void onResult(String result) {
                    if (result.equals("")) {
                        Helper.makeSnackbarError(mainActivity, "Connection interrupted!");
                        stopChecker();
                    } else {
                        mHandler.postDelayed(mStatusChecker, 5000);
                    }
                }
            }).execute(" ");

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

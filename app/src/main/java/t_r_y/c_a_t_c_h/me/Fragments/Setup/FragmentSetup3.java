package t_r_y.c_a_t_c_h.me.Fragments.Setup;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tramsun.libs.prefcompat.Pref;

import t_r_y.c_a_t_c_h.me.AsyncTasks.AsyncTaskGetConsole;
import t_r_y.c_a_t_c_h.me.Interfaces.OnConsoleFetched;
import t_r_y.c_a_t_c_h.me.Objects.Console;
import t_r_y.c_a_t_c_h.me.R;

/**
 * Created by Admin on 10.09.2016.
 */
public class FragmentSetup3 extends Fragment{

    private static android.widget.TextView tvSetupCpuKey;
    private static android.widget.TextView tvSetupDashboard;
    private static Button btnStart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setup_3,container,false);
        this.tvSetupDashboard = (TextView) view.findViewById(R.id.tvSetupDashboard);
        this.tvSetupCpuKey = (TextView) view.findViewById(R.id.tvSetupCpuKey);
        btnStart = (Button) view.findViewById(R.id.btnSetupStart);
        btnStart.setOnClickListener(view1 -> {
            Pref.putBoolean("setup",true);
            getActivity().finish();
        });

        return view;
    }

    public static void IPValidated(final Activity activity){
        new AsyncTaskGetConsole(console -> {
            if (console != null){
                activity.runOnUiThread(() -> {
                    tvSetupDashboard.setText(console.getDashboard());
                    tvSetupCpuKey.setText(console.getCpukey());
                    btnStart.setVisibility(View.VISIBLE);
                });
            }
        }).execute();
    }


}

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

import butterknife.BindView;
import butterknife.ButterKnife;
import t_r_y.c_a_t_c_h.me.Fragments.Xbox.FragmentXbox;
import t_r_y.c_a_t_c_h.me.Xbox.XboxSocket;
import t_r_y.c_a_t_c_h.me.R;

/**
 * Created by Admin on 10.09.2016.
 */
public class FragmentSetup3 extends Fragment{

    @BindView(R.id.tvSetupCpuKey)
    TextView tvSetupCpuKey;
    @BindView(R.id.tvSetupDashboard)
    TextView tvSetupDashboard;
    @BindView(R.id.btnSetupStart)
    Button btnStart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setup_3,container,false);
        ButterKnife.bind(this,view);

        btnStart.setOnClickListener(view1 -> {
            Pref.putBoolean("setup",true);
            FragmentXbox.setConnected(true);
            getActivity().finish();
        });

        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser)
            validated();
    }

    public void validated(){
        XboxSocket.getInstance().getConsole(console -> {
            if (console != null) {
                getActivity().runOnUiThread(() -> {
                    tvSetupDashboard.setText(console.getDashboard());
                    tvSetupCpuKey.setText(console.getCpukey());
                    btnStart.setVisibility(View.VISIBLE);
                });
            }
        });
    }


}

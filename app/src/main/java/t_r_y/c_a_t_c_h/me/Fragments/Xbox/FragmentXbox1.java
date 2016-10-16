package t_r_y.c_a_t_c_h.me.Fragments.Xbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import t_r_y.c_a_t_c_h.me.AsyncTasks.AsyncTaskGetConsole;
import t_r_y.c_a_t_c_h.me.Helper;
import t_r_y.c_a_t_c_h.me.R;

/**
 * Created by Admin on 07.09.2016.
 */
public class FragmentXbox1 extends Fragment {
    @BindView(R.id.tvSetupCpuKey)
    TextView tvSetupCpuKey;
    @BindView(R.id.tvDashboard)
    TextView tvDashboard;
    @BindView(R.id.tvCpuTemp)
    TextView tvCpuTemp;
    @BindView(R.id.tvGPUTemp)
    TextView tvGPUTemp;
    @BindView(R.id.tvRAMTemp)
    TextView tvRAMTemp;
    @BindView(R.id.tvMotherboardTemp)
    TextView tvMotherboardTemp;
    @BindView(R.id.tvBoardName)
    TextView tvBoardName;
    @BindView(R.id.tvConsoleName)
    TextView tvConsoleName;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_xbox, container, false);
        ButterKnife.bind(this, view);

        swiperefresh.setOnRefreshListener(() -> update());
        update();
        return view;
    }

    private void update() {
        new AsyncTaskGetConsole(console -> {
            if (getActivity() != null)
                getActivity().runOnUiThread(() -> {
                    if (swiperefresh.isRefreshing())
                        swiperefresh.setRefreshing(false);

                    if (console == null) {
                        Helper.makeSnackbarError(getActivity(), "Something went wrong, please try again!");
                    } else {
                        tvConsoleName.setText(console.getDebugName());
                        tvBoardName.setText(console.getBoardName());
                        tvMotherboardTemp.setText(String.valueOf(console.getMotherboardTemp()));
                        tvRAMTemp.setText(String.valueOf(console.getRamTemp()));
                        tvGPUTemp.setText(String.valueOf(console.getGpuTemp()));
                        tvCpuTemp.setText(String.valueOf(console.getCpuTemp()));
                        tvDashboard.setText(console.getDashboard());
                        tvSetupCpuKey.setText(console.getCpukey());
                    }
                });
        }).execute();
    }

}

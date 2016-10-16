package t_r_y.c_a_t_c_h.me.Fragments.Setup;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tramsun.libs.prefcompat.Pref;

import t_r_y.c_a_t_c_h.me.Activities.ActivitySetup;
import t_r_y.c_a_t_c_h.me.AsyncTasks.AsyncTaskScanNetwork;
import t_r_y.c_a_t_c_h.me.Interfaces.OnNetworkScanListener;
import t_r_y.c_a_t_c_h.me.R;

/**
 * Created by Admin on 10.09.2016.
 */
public class FragmentSetup2 extends Fragment implements View.OnClickListener {
    public static TextView getTvChosenIp() {
        return tvChosenIp;
    }

    private static android.widget.TextView tvChosenIp;
    private android.widget.Button btnValidate;
    private ProgressDialog pd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setup_2,container,false);
        this.btnValidate = (Button) view.findViewById(R.id.btnValidate);
        this.tvChosenIp = (TextView) view.findViewById(R.id.tvChosenIp);
        pd = new ProgressDialog(getActivity());
        pd.setIndeterminate(true);
        pd.setMessage("Please wait...");

        btnValidate.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        pd.show();
        new AsyncTaskScanNetwork(getActivity(),tvChosenIp.getText().toString(), new OnNetworkScanListener() {
            @Override
            public void finished(boolean isXbox) {
                Log.d(getClass().getSimpleName(),"isXbox:"+isXbox);
                if (isXbox){
                    getActivity().runOnUiThread(() -> {
                        pd.dismiss();
                        Pref.putString("ip",tvChosenIp.getText().toString());
                        FragmentSetup3.IPValidated(getActivity());
                        ActivitySetup.getViewPager().setScrollingEnabled(true);
                        ActivitySetup.getViewPager().setCurrentItem(ActivitySetup.getViewPager().getCurrentItem()+1,true);
                    });
                }else{
                    getActivity().runOnUiThread(() -> {
                        pd.dismiss();
                        new AlertDialog.Builder(getActivity()).setTitle("Failed!")
                                .setMessage("Connecting to your Xbox failed! Please ensure your Xbox has XBDM and JRPC2 installed and is turned on!")
                                .setPositiveButton("Aye", (dialogInterface, i) -> {
                                    dialogInterface.dismiss();
                                }).setNegativeButton("Re-enter IP", (dialogInterface, i) -> {
                                    dialogInterface.dismiss();
                                    ActivitySetup.getViewPager().setCurrentItem(ActivitySetup.getViewPager().getCurrentItem()-1,true);
                                }).show();
                    });
                }
            }
        }).execute();
    }
}

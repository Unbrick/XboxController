package t_r_y.c_a_t_c_h.me.Fragments.BlackOps2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gigamole.navigationtabstrip.NavigationTabStrip;

import butterknife.BindView;
import butterknife.ButterKnife;
import t_r_y.c_a_t_c_h.me.Adapter.BlackOps2Adapter;
import t_r_y.c_a_t_c_h.me.Helper.Helper;
import t_r_y.c_a_t_c_h.me.Activities.MainActivity;
import t_r_y.c_a_t_c_h.me.R;

/**
 * Created by Admin on 11.09.2016.
 */
public class BlackOps2BaseFragment extends Fragment {

    @BindView(R.id.mainNTS)
    NavigationTabStrip mainNTS;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpager, container, false);
        ButterKnife.bind(this, view);
        mainNTS.setTitles("OffHost", "Multiplayer", "Zombies");
        mainNTS.setStripType(NavigationTabStrip.StripType.LINE);
        mainNTS.setStripColor(Color.parseColor("#009688"));
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Black Ops II");
        viewPager.setAdapter(new BlackOps2Adapter(getChildFragmentManager()));
        mainNTS.setViewPager(viewPager);
        Helper.getRunningTitle(result -> {
            if (!result.contains("415608C3"))
                Helper.makeSnackbar(getActivity(),"Please start Black Ops II before using this functions, otherwise it could cause crashes!");

        });
        return view;
    }
}

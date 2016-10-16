package t_r_y.c_a_t_c_h.me.Fragments.BlackOps3;

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
import t_r_y.c_a_t_c_h.me.Adapter.BlackOps3Adapter;
import t_r_y.c_a_t_c_h.me.Helper;
import t_r_y.c_a_t_c_h.me.MainActivity;
import t_r_y.c_a_t_c_h.me.R;

/**
 * Created by Admin on 11.09.2016.
 */
public class BlackOps3BaseFragment extends Fragment {

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
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Black Ops III");
        viewPager.setAdapter(new BlackOps3Adapter(getChildFragmentManager()));
        mainNTS.setViewPager(viewPager);
        Helper.getRunningTitle(result -> {
            if (!result.contains("4156091D"))
                Helper.makeSnackbar(getActivity(),"Please start Black Ops III before using this functions, otherwise it could cause crashes!");
        });
        return view;
    }

}
package com.unbrick.xbox.Fragments.BlackOps3;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gigamole.navigationtabstrip.NavigationTabStrip;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.unbrick.xbox.Adapter.BlackOps3Adapter;
import com.unbrick.xbox.XBDM.listener.OnResultListener;
import com.unbrick.xbox.Xbox.XboxSocket;
import com.unbrick.xbox.Helper.Helper;
import com.unbrick.xbox.Activities.MainActivity;
import com.unbrick.xbox.R;

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
        ((MainActivity) requireActivity()).getSupportActionBar().setTitle("Black Ops III");
        viewPager.setAdapter(new BlackOps3Adapter(getChildFragmentManager()));
        mainNTS.setViewPager(viewPager);
        XboxSocket.getInstance().xbdm.getRuningTitleId((OnResultListener) o -> {
            if (!((String)o).contains("4156091D"))
                Helper.makeSnackbar(requireActivity(),"Please start Black Ops III before using this functions, otherwise it could cause crashes!");
        });
        return view;
    }

}

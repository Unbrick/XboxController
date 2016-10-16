package t_r_y.c_a_t_c_h.me.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gigamole.navigationtabstrip.NavigationTabStrip;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import t_r_y.c_a_t_c_h.me.Adapter.WeaponSelectorAdapter;
import t_r_y.c_a_t_c_h.me.Activities.MainActivity;
import t_r_y.c_a_t_c_h.me.R;

/**
 * Created by Admin on 12.09.2016.
 */
public class BaseFragmentWeaponSelector extends Fragment {

    @BindView(R.id.mainNTS)
    NavigationTabStrip mainNTS;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    public static BaseFragmentWeaponSelector newInstance(int game, String... titles) {
        Bundle args = new Bundle();
        ArrayList<String> tabnames = new ArrayList<>();
        Collections.addAll(tabnames, titles);
        args.putStringArrayList("tabs", tabnames);
        args.putInt("game", game);
        BaseFragmentWeaponSelector fragment = new BaseFragmentWeaponSelector();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpager, container, false);
        ButterKnife.bind(this, view);
        mainNTS.setTitles(getArguments().getStringArrayList("tabs").toArray(new String[0]));
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Weapons");
        mainNTS.setStripType(NavigationTabStrip.StripType.LINE);
        mainNTS.setStripColor(Color.parseColor("#009688"));
        viewPager.setAdapter(new WeaponSelectorAdapter(getChildFragmentManager(), getArguments().getInt("game")));
        viewPager.setId(R.id.viewPager);
        mainNTS.setViewPager(viewPager);
        return view;
    }
}

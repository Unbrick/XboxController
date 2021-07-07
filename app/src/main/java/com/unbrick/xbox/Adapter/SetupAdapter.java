package com.unbrick.xbox.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.unbrick.xbox.Fragments.Setup.FragmentSetup1;
import com.unbrick.xbox.Fragments.Setup.FragmentSetup2;
import com.unbrick.xbox.Fragments.Setup.FragmentSetup3;

/**
 * Created by Admin on 11.09.2016.
 */
public class SetupAdapter extends FragmentStatePagerAdapter {

    public SetupAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new FragmentSetup1();
            case 1:
                return new FragmentSetup2();
            case 2:
                return new FragmentSetup3();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
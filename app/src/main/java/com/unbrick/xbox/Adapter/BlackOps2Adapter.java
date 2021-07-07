package com.unbrick.xbox.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.unbrick.xbox.Fragments.BlackOps2.FragmentBlackOps2_1;
import com.unbrick.xbox.Fragments.BlackOps2.FragmentBlackOps2_2;
import com.unbrick.xbox.Fragments.BlackOps2.FragmentBlackOps2_3;

/**
 * Created by Admin on 11.09.2016.
 */
public class BlackOps2Adapter extends FragmentStatePagerAdapter {

    public BlackOps2Adapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new FragmentBlackOps2_1();
            case 1:
                return new FragmentBlackOps2_2();
            case 2:
                return new FragmentBlackOps2_3();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}

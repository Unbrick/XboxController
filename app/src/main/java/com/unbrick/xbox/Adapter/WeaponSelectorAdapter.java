package com.unbrick.xbox.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.unbrick.xbox.Fragments.FragmentGiveWeapon;
import com.unbrick.xbox.Helper.Constants;

/**
 * Created by Admin on 12.09.2016.
 */
public class WeaponSelectorAdapter extends FragmentStatePagerAdapter {

    private int GAME;

    public WeaponSelectorAdapter(FragmentManager fm, int GAME) {
        super(fm);
        this.GAME = GAME;
    }

    @Override
    public Fragment getItem(int position) {
        switch (GAME) {
            case Constants.BLACK_OPS_2_MP:
                break;
            case Constants.BLACK_OPS_2_ZM:
                switch (position) {
                    case 0:
                        return FragmentGiveWeapon.newInstance(Constants.BLACK_OPS_2_ZM_TRANZIT);
                    case 1:
                        return FragmentGiveWeapon.newInstance(Constants.BLACK_OPS_2_ZM_DIERISE);
                    case 2:
                        return FragmentGiveWeapon.newInstance(Constants.BLACK_OPS_2_ZM_MOTD);
                    case 3:
                        return FragmentGiveWeapon.newInstance(Constants.BLACK_OPS_2_ZM_BURIED);
                    case 4:
                        return FragmentGiveWeapon.newInstance(Constants.BLACK_OPS_2_ZM_ORIGINS);
                }
                break;
            case Constants.BLACK_OPS_3_MP:
                break;
            case Constants.BLACK_OPS_3_ZM:
                switch (position) {
                    case 0:
                        return FragmentGiveWeapon.newInstance(Constants.BLACK_OPS_3_ZM_SHADOWS);
                    case 1:
                        return FragmentGiveWeapon.newInstance(Constants.BLACK_OPS_3_ZM_GIANT);
                    case 2:
                        return FragmentGiveWeapon.newInstance(Constants.BLACK_OPS_3_ZM_EISENDRACHE);
                }
                break;
        }

        return null;
    }

    @Override
    public int getCount() {
        switch (GAME) {
            case Constants.BLACK_OPS_2_MP:
                break;
            case Constants.BLACK_OPS_2_ZM:
                return 5;
            case Constants.BLACK_OPS_3_MP:
                break;
            case Constants.BLACK_OPS_3_ZM:
                return 3;
        }
        return 3;
    }
}
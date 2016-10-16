package t_r_y.c_a_t_c_h.me.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import t_r_y.c_a_t_c_h.me.Fragments.Setup.FragmentSetup1;
import t_r_y.c_a_t_c_h.me.Fragments.Setup.FragmentSetup2;
import t_r_y.c_a_t_c_h.me.Fragments.Setup.FragmentSetup3;

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
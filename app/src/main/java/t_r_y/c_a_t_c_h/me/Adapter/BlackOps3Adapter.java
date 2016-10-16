package t_r_y.c_a_t_c_h.me.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import t_r_y.c_a_t_c_h.me.Fragments.BlackOps3.FragmentBlackOps3_1;
import t_r_y.c_a_t_c_h.me.Fragments.BlackOps3.FragmentBlackOps3_2;
import t_r_y.c_a_t_c_h.me.Fragments.BlackOps3.FragmentBlackOps3_3;

/**
 * Created by Admin on 11.09.2016.
 */
public class BlackOps3Adapter extends FragmentStatePagerAdapter {

    public BlackOps3Adapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new FragmentBlackOps3_1();
            case 1:
                return new FragmentBlackOps3_2();
            case 2:
                return new FragmentBlackOps3_3();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}

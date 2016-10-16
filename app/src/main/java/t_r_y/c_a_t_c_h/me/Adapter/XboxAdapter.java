package t_r_y.c_a_t_c_h.me.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import t_r_y.c_a_t_c_h.me.Fragments.Xbox.FragmentXbox1;
import t_r_y.c_a_t_c_h.me.Fragments.Xbox.FragmentXbox2;

/**
 * Created by Admin on 11.09.2016.
 */
public class XboxAdapter extends FragmentStatePagerAdapter {

    public XboxAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new FragmentXbox1();
            case 1:
                return new FragmentXbox2();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}

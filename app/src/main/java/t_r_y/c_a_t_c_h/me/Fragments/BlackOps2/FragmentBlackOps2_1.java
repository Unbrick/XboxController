package t_r_y.c_a_t_c_h.me.Fragments.BlackOps2;

import android.os.Bundle;
import android.preference.Preference;

import com.github.machinarius.preferencefragment.PreferenceFragment;

import t_r_y.c_a_t_c_h.me.AsyncTasks.AsyncTaskSetMem;
import t_r_y.c_a_t_c_h.me.BlackOps2XboxMods;
import t_r_y.c_a_t_c_h.me.Constants;
import t_r_y.c_a_t_c_h.me.Helper;
import t_r_y.c_a_t_c_h.me.R;

import static t_r_y.c_a_t_c_h.me.Constants.NOP;

/**
 * Created by Admin on 07.09.2016.
 */
public class FragmentBlackOps2_1 extends PreferenceFragment implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {

    @Override
    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        addPreferencesFromResource(R.xml.prefs_bo2_offhost);
        findPreference("bo2bypass").setOnPreferenceClickListener(this);
        findPreference("radar").setOnPreferenceChangeListener(this);
        findPreference("charms").setOnPreferenceChangeListener(this);
        findPreference("redBoxes").setOnPreferenceChangeListener(this);
        findPreference("laser").setOnPreferenceChangeListener(this);
        findPreference("recoil").setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        switch (preference.getKey()){
            case "bo2bypass":
                for (String offset : Constants.bypassOffsets)
                    new AsyncTaskSetMem().execute(offset,NOP);
                Helper.makeSnackbar(getActivity(),"Bypass successful!");
                break;
        }
        return true;
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        boolean b = (boolean) o;
        switch (preference.getKey()){
            case "recoil":
                BlackOps2XboxMods.OffHost.noRecoil(b);
                break;
            case "laser":
                BlackOps2XboxMods.OffHost.laser(b);
                break;
            case "redBoxes":
                BlackOps2XboxMods.OffHost.redBoxes(b);
                break;
            case "charms":
                BlackOps2XboxMods.OffHost.charms(b);
                break;
            case "radar":
                BlackOps2XboxMods.OffHost.radar(b);
                break;
        }
        return true;
    }
}

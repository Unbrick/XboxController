package t_r_y.c_a_t_c_h.me.Fragments.BlackOps3;

import android.os.Bundle;
import android.preference.Preference;

import com.github.machinarius.preferencefragment.PreferenceFragment;

import t_r_y.c_a_t_c_h.me.BlackOps3XboxMods;
import t_r_y.c_a_t_c_h.me.R;

/**
 * Created by Admin on 11.09.2016.
 */
public class FragmentBlackOps3_1 extends PreferenceFragment implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
    @Override
    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        addPreferencesFromResource(R.xml.prefs_bo3_offhost);
        findPreference("redbox").setOnPreferenceChangeListener(this);
        findPreference("uav").setOnPreferenceChangeListener(this);
        findPreference("charms").setOnPreferenceChangeListener(this);
        findPreference("recoil").setOnPreferenceChangeListener(this);
        findPreference("crosshair").setOnPreferenceChangeListener(this);
        findPreference("gunsway").setOnPreferenceChangeListener(this);

        findPreference("disabledvar").setOnPreferenceClickListener(this);
        findPreference("coldblooded").setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        boolean b = (boolean) o;
        switch (preference.getKey()){
            case "redbox":
                BlackOps3XboxMods.OffHost.redBoxes(b);
                break;
            case "uav":
                BlackOps3XboxMods.OffHost.uav(b);
                break;
            case "charms":
                BlackOps3XboxMods.OffHost.charms(b);
                break;
            case "wallrun":
                BlackOps3XboxMods.OffHost.wallRun(b);
                break;
            case "recoil":
                BlackOps3XboxMods.OffHost.noRecoil(b);
                break;
            case "crosshair":
                BlackOps3XboxMods.OffHost.smallCrosshair(b);
                break;
            case "gunsway":
                BlackOps3XboxMods.OffHost.noGunSway(b);
                break;
        }
        return true;
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        switch (preference.getKey()){
            case "disabledvar":
                BlackOps3XboxMods.OffHost.disableDvarAC();
                break;
            case "coldblooded":
                BlackOps3XboxMods.OffHost.removeColdBlooded();
                break;
        }
        return true;
    }
}

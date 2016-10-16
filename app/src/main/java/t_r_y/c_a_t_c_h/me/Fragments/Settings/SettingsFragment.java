package t_r_y.c_a_t_c_h.me.Fragments.Settings;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;

import com.github.machinarius.preferencefragment.PreferenceFragment;

import t_r_y.c_a_t_c_h.me.Activities.ActivitySetup;
import t_r_y.c_a_t_c_h.me.R;

/**
 * Created by Admin on 15.10.2016.
 */

public class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {

    @Override
    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        addPreferencesFromResource(R.xml.pref_settings);
        findPreference("pref_settings_resetup").setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        switch (preference.getKey()){
            case "pref_settings_resetup":
                startActivity(new Intent(getActivity(), ActivitySetup.class));
                break;
            default:
                break;
        }
        return true;
    }
}

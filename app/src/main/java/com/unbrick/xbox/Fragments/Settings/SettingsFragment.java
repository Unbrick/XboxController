package com.unbrick.xbox.Fragments.Settings;

import android.content.Intent;
import android.os.Bundle;
import androidx.preference.Preference;

import androidx.preference.PreferenceFragmentCompat;


import com.unbrick.xbox.Activities.ActivitySetup;
import com.unbrick.xbox.R;

/**
 * Created by Admin on 15.10.2016.
 */

public class SettingsFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceClickListener {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.pref_settings, rootKey);
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

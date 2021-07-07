package com.unbrick.xbox.Fragments.BlackOps3;

import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.unbrick.xbox.BlackOps3XboxMods;
import com.unbrick.xbox.R;

/**
 * Created by Admin on 11.09.2016.
 */
public class FragmentBlackOps3_2 extends PreferenceFragmentCompat implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.prefs_bo3_mp);
        findPreference("forcehost").setOnPreferenceClickListener(this);
        findPreference("ammo").setOnPreferenceClickListener(this);


        findPreference("god").setOnPreferenceChangeListener(this);
        findPreference("speed").setOnPreferenceChangeListener(this);
        findPreference("jump").setOnPreferenceChangeListener(this);
        findPreference("weapons").setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        switch (preference.getKey()){
            case "ammo":
                BlackOps3XboxMods.Multiplayer.unlimitedAmmo();
                break;
            case "forcehost":
                BlackOps3XboxMods.Multiplayer.forceHost();
                break;
        }
        return true;
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        boolean b = (boolean) o;
        switch (preference.getKey()){
            case "god":
                BlackOps3XboxMods.Multiplayer.godMode(b);
                break;
            case "speed":
                BlackOps3XboxMods.Multiplayer.increaseSpeed(b);
                break;
            case "jump":
                BlackOps3XboxMods.Multiplayer.increaseJumpHeight(b);
                break;
            case "weapons":
                break;
        }
        return true;
    }
}

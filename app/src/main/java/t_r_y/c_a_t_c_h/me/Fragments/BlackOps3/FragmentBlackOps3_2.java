package t_r_y.c_a_t_c_h.me.Fragments.BlackOps3;

import android.os.Bundle;
import android.preference.Preference;

import com.github.machinarius.preferencefragment.PreferenceFragment;

import t_r_y.c_a_t_c_h.me.BlackOps3XboxMods;
import t_r_y.c_a_t_c_h.me.R;

/**
 * Created by Admin on 11.09.2016.
 */
public class FragmentBlackOps3_2 extends PreferenceFragment implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {
    @Override
    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
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

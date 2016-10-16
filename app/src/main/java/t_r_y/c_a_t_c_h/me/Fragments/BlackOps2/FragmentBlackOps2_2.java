package t_r_y.c_a_t_c_h.me.Fragments.BlackOps2;

import android.os.Bundle;
import android.preference.Preference;

import com.github.machinarius.preferencefragment.PreferenceFragment;

import t_r_y.c_a_t_c_h.me.BlackOps2XboxMods;
import t_r_y.c_a_t_c_h.me.R;

/**
 * Created by Admin on 11.09.2016.
 */
public class FragmentBlackOps2_2 extends PreferenceFragment implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {

    @Override
    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        addPreferencesFromResource(R.xml.prefs_bo2_mp);
        findPreference("forcehost").setOnPreferenceChangeListener(this);
        findPreference("godmode").setOnPreferenceChangeListener(this);
        findPreference("ammo").setOnPreferenceChangeListener(this);
        findPreference("blur").setOnPreferenceChangeListener(this);
        findPreference("hud").setOnPreferenceChangeListener(this);
        findPreference("freeze").setOnPreferenceChangeListener(this);
        findPreference("gravity").setOnPreferenceChangeListener(this);
        findPreference("superjump").setOnPreferenceChangeListener(this);

        findPreference("team1").setOnPreferenceClickListener(this);
        findPreference("team2").setOnPreferenceClickListener(this);
        findPreference("killstreaks").setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        boolean b = (boolean) o;
        switch (preference.getKey()){
            case "forcehost":
                BlackOps2XboxMods.Multiplayer.forceHost(b);
                break;
            case "godmode":
                BlackOps2XboxMods.Multiplayer.godMode(b);
                break;
            case "ammo":
                BlackOps2XboxMods.Multiplayer.unlimitedAmmo(b);
                break;
            case "blur":
                BlackOps2XboxMods.Multiplayer.blur(b);
                break;
            case "speed":
                BlackOps2XboxMods.Multiplayer.fasterPlayerSpeed(b);
                break;
            case "hud":
                BlackOps2XboxMods.Multiplayer.hudHardcore(b);
                break;
            case "freeze":
                BlackOps2XboxMods.Multiplayer.freezePlacer(b);
                break;
            case "gravity":
                BlackOps2XboxMods.Multiplayer.lowGravity(b);
                break;
            case "superjump":
                BlackOps2XboxMods.Multiplayer.superJump(b);
                break;
        }
        return true;
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        switch (preference.getKey()){
            case "team1":
                BlackOps2XboxMods.Multiplayer.changeTeam(1);
                break;
            case "team2":
                BlackOps2XboxMods.Multiplayer.changeTeam(2);
                break;
            case "killstreaks":
                BlackOps2XboxMods.Multiplayer.giveKillstreaks();
                break;
        }
        return true;
    }
}

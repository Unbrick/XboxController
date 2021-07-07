package com.unbrick.xbox.Fragments.BlackOps2;

import android.os.Bundle;


import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;


import com.unbrick.xbox.RPC.XDRPCArgumentInfo;
import com.unbrick.xbox.Xbox.XboxSocket;
import com.unbrick.xbox.BlackOps2XboxMods;
import com.unbrick.xbox.Helper.Constants;
import com.unbrick.xbox.Helper.Helper;
import com.unbrick.xbox.R;

import java.util.ArrayList;

import static com.unbrick.xbox.Helper.Constants.NOP;

/**
 * Created by Admin on 07.09.2016.
 */
public class FragmentBlackOps2_1 extends PreferenceFragmentCompat implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {

    XboxSocket xboxSocket = XboxSocket.getInstance();

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.prefs_bo2_offhost);
        findPreference("forcehost").setOnPreferenceChangeListener(this);
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
                    xboxSocket.xbdm.setMem(offset,NOP);
                Helper.makeSnackbar(requireActivity(),"Bypass successful!");
                break;
        }
        return true;
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        boolean b = (boolean) o;
        switch (preference.getKey()){
            case "forcehost":
                BlackOps2XboxMods.OffHost.forceHost(b);

                break;
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

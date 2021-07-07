package com.unbrick.xbox.Fragments.BlackOps2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.github.javiersantos.bottomdialogs.BottomDialog;

import java.util.ArrayList;
import java.util.Objects;

import com.unbrick.xbox.BlackOps2XboxMods;
import com.unbrick.xbox.Fragments.BaseFragmentWeaponSelector;
import com.unbrick.xbox.Helper.Constants;
import com.unbrick.xbox.Helper.Helper;
import com.unbrick.xbox.R;
import com.unbrick.xbox.Xbox.XboxSocket;

import static androidx.core.content.ContextCompat.getSystemService;

/**
 * Created by Admin on 11.09.2016.
 */
public class FragmentBlackOps2_3 extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {


    interface OnUpdateFinishedListener {
        void finished(ArrayList<Player> players);
        void failed();
    }
    class Player {
        public Player(String gamertag, int slot) {
            this.gamertag = gamertag;
            this.slot = slot;
        }

        public String gamertag;
        public int slot;
    }

    private int currentPlayer = 0;


    private void update(OnUpdateFinishedListener mOnUpdateFinishedListener) {
        BlackOps2XboxMods.getGamertagsBlackOpsII(gamertags -> {
            if (gamertags != null){
                ArrayList<Player> mList = new ArrayList<>();
                for (int i = 0; i < gamertags.size(); i++) {
                    mList.add(new Player(gamertags.get(i), i));
                }
                mOnUpdateFinishedListener.finished(mList);
                return;
            }else{
                Helper.makeSnackbar(requireActivity(),"Connection interrupted, please try again!");
            }
            mOnUpdateFinishedListener.failed();

        });
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.prefs_bo2_zm);

        findPreference("god").setOnPreferenceChangeListener(this);
        findPreference("ammo").setOnPreferenceChangeListener(this);
        findPreference("target").setOnPreferenceChangeListener(this);
        findPreference("speed").setOnPreferenceChangeListener(this);

        findPreference("money").setOnPreferenceClickListener(this);
        findPreference("weapons").setOnPreferenceClickListener(this);
        findPreference("player").setOnPreferenceClickListener(this);
    }

    public void displayPlayerPicker() {
        update(new OnUpdateFinishedListener() {
            @Override
            public void finished(ArrayList<Player> players) {
                View customView = Objects.requireNonNull(getSystemService(requireContext(), LayoutInflater.class))
                        .inflate(R.layout.fragment_black_ops_all_zm, null);
                RadioGroup mRadioGroup = customView.findViewById(R.id.radioGroup);
                for (Player player : players) {
                    RadioButton mRadioButton = new RadioButton(requireContext());
                    mRadioButton.setTag(player.slot);
                    mRadioButton.setText(player.gamertag);
                    mRadioGroup.addView(mRadioButton);
                }

                requireActivity().runOnUiThread(() -> new BottomDialog.Builder(requireActivity())
                        .setTitle("Player selection")
                        .setContent("Choose a player")
                        .onPositive(bottomDialog -> {
                            FragmentBlackOps2_3.this.currentPlayer = Integer.parseInt((String) customView.findViewById(mRadioGroup.getCheckedRadioButtonId()).getTag());
                            bottomDialog.dismiss();
                        })
                        .setCustomView(customView)
                        .show());
            }

            @Override
            public void failed() {
                Helper.makeSnackbar(requireActivity(), "Failed updating players...");
            }
        });

    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        boolean b = (boolean) o;
        boolean allPlayers = this.currentPlayer == -1;

        if (allPlayers) {
            switch (preference.getKey()) {
                case "god":
                    for (int i = 0; i <= 3; i++) {
                        BlackOps2XboxMods.Zombies.godMode(i, b);
                    }
                    break;
                case "ammo":
                    for (int i = 0; i <= 3; i++) {
                        BlackOps2XboxMods.Zombies.unlimitedAmmo(i, b);
                    }
                    break;
            }
        } else {
            switch (preference.getKey()) {
                case "god":
                    BlackOps2XboxMods.Zombies.godMode(this.currentPlayer, b);
                    break;
                case "ammo":
                    BlackOps2XboxMods.Zombies.unlimitedAmmo(this.currentPlayer, b);
                    break;
                case "target":
                    BlackOps2XboxMods.Zombies.noTarget(this.currentPlayer, b);
                    break;
                case "speed":
                    BlackOps2XboxMods.Zombies.fasterPlayerSpeed(this.currentPlayer, b);
                    break;
            }
        }
        return true;
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        switch (preference.getKey()) {
            case "weapons":
                BaseFragmentWeaponSelector bfws = BaseFragmentWeaponSelector.newInstance(Constants.BLACK_OPS_2_ZM, "Tranzit", "Die Rise", "MOTD", "Buried", "Origins");
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, bfws)
                        .addToBackStack(null)
                        .commit();
                break;
            case "money":
                if (this.currentPlayer == -1) {
                    for (int i = 1; i <= 5; i++) {
                        BlackOps2XboxMods.Zombies.giveMoney(i);
                    }
                } else {
                    BlackOps2XboxMods.Zombies.giveMoney(this.currentPlayer);
                }
                break;
            case "player":
                displayPlayerPicker();
                return true;
        }

        return true;
    }
}

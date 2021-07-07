package com.unbrick.xbox.Fragments.BlackOps3;

import android.os.Bundle;
import android.os.Handler;
import androidx.preference.Preference;

import androidx.preference.PreferenceFragmentCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unbrick.xbox.Xbox.XboxSocket;
import com.unbrick.xbox.BlackOps3XboxMods;
import com.unbrick.xbox.Helper.Constants;
import com.unbrick.xbox.Fragments.BaseFragmentWeaponSelector;
import com.unbrick.xbox.Helper.Helper;
import com.unbrick.xbox.R;

/**
 * Created by Admin on 11.09.2016.
 */
public class FragmentBlackOps3_3 extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
    private android.widget.RadioGroup radioGroup;
    private SwipeRefreshLayout refreshLayout;
    private android.widget.RadioButton rbPlayer1;
    private android.widget.RadioButton rbPlayer2;
    private android.widget.RadioButton rbPlayer3;
    private android.widget.RadioButton rbPlayer4;

    @Override
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        View view = paramLayoutInflater.inflate(R.layout.fragment_black_ops_all_zm, paramViewGroup, false);
        this.rbPlayer4 = view.findViewById(R.id.rbPlayer4);
        this.rbPlayer3 = view.findViewById(R.id.rbPlayer3);
        this.rbPlayer2 = view.findViewById(R.id.rbPlayer2);
        this.rbPlayer1 = view.findViewById(R.id.rbPlayer1);
        this.radioGroup = view.findViewById(R.id.radioGroup);
        this.refreshLayout = view.findViewById(R.id.refreshLayout);
        radioGroup.check(R.id.rbPlayer1);
        refreshLayout.setOnRefreshListener(this::update);
        if (!refreshLayout.isRefreshing()){
            refreshLayout.post(() -> refreshLayout.setRefreshing(true));
        }
        new Handler().postDelayed(this::update,1000);
        return view;
    }

    private void update() {
        BlackOps3XboxMods.getGamertagsBlackOpsIII(gamertags -> {
            if (gamertags != null){
                getActivity().runOnUiThread(() -> {
                    refreshLayout.setRefreshing(false);
                    switch (gamertags.size()){
                        case 1:
                            rbPlayer1.setText(gamertags.get(0));
                            rbPlayer2.setVisibility(View.GONE);
                            rbPlayer3.setVisibility(View.GONE);
                            rbPlayer4.setVisibility(View.GONE);
                            break;
                        case 2:
                            rbPlayer1.setText(gamertags.get(0));
                            rbPlayer2.setText(gamertags.get(1));
                            rbPlayer3.setVisibility(View.GONE);
                            rbPlayer4.setVisibility(View.GONE);
                            break;
                        case 3:
                            rbPlayer1.setText(gamertags.get(0));
                            rbPlayer2.setText(gamertags.get(1));
                            rbPlayer3.setText(gamertags.get(2));
                            rbPlayer4.setVisibility(View.GONE);
                            break;
                        case 4:
                            rbPlayer1.setText(gamertags.get(0));
                            rbPlayer2.setText(gamertags.get(1));
                            rbPlayer3.setText(gamertags.get(2));
                            rbPlayer4.setText(gamertags.get(3));
                            break;
                    }
                });
            }else{
                Helper.makeSnackbar(getActivity(),"Connection interrupted, please try again!");
            }

        });
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_bo3_zm);

        findPreference("god").setOnPreferenceChangeListener(this);

        findPreference("money").setOnPreferenceClickListener(this);
        findPreference("ammo").setOnPreferenceClickListener(this);
        findPreference("weapons").setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        boolean b = (boolean) o;
        int player = 1;
        boolean allPlayers = false;
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.rbPlayer1:
                player = 1;
                break;
            case R.id.rbPlayer2:
                player = 2;
                break;
            case R.id.rbPlayer3:
                player = 3;
                break;
            case R.id.rbPlayer4:
                player = 4;
                break;
            case R.id.rbPlayerAll:
                allPlayers = true;
                break;
        }

        if (allPlayers) {
            switch (preference.getKey()) {
                case "god":
                    BlackOps3XboxMods.Zombies.godMode(1, b);
                    BlackOps3XboxMods.Zombies.godMode(2, b);
                    BlackOps3XboxMods.Zombies.godMode(3, b);
                    BlackOps3XboxMods.Zombies.godMode(4, b);
                    break;
            }
        } else {
            switch (preference.getKey()) {
                case "god":
                    BlackOps3XboxMods.Zombies.godMode(player, b);
                    break;
            }
        }
        return true;
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        int player = 1;
        boolean allPlayers = false;
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.rbPlayer1:
                player = 1;
                break;
            case R.id.rbPlayer2:
                player = 2;
                break;
            case R.id.rbPlayer3:
                player = 3;
                break;
            case R.id.rbPlayer4:
                player = 4;
                break;
            case R.id.rbPlayerAll:
                allPlayers = true;
                break;
        }
        if (allPlayers) {
            switch (preference.getKey()) {
                case "ammo":
                    BlackOps3XboxMods.Zombies.giveAmmo(1);
                    BlackOps3XboxMods.Zombies.giveAmmo(2);
                    BlackOps3XboxMods.Zombies.giveAmmo(3);
                    BlackOps3XboxMods.Zombies.giveAmmo(4);
                    break;
                case "money":
                    BlackOps3XboxMods.Zombies.giveMoney(1);
                    BlackOps3XboxMods.Zombies.giveMoney(2);
                    BlackOps3XboxMods.Zombies.giveMoney(3);
                    BlackOps3XboxMods.Zombies.giveMoney(4);
                    break;
            }

        } else {
            switch (preference.getKey()) {
                case "ammo":
                    BlackOps3XboxMods.Zombies.giveAmmo(player);
                    break;
                case "money":
                    BlackOps3XboxMods.Zombies.giveMoney(player);
                    break;
                case "weapons":
                    BaseFragmentWeaponSelector bfws = BaseFragmentWeaponSelector.newInstance(Constants.BLACK_OPS_3_ZM,"Shadows","The Giant","Eisendrache");
                    getActivity()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content,bfws)
                            .addToBackStack(null)
                            .commit();
                    break;
            }
        }
        return true;
    }
}

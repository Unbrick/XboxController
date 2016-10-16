package t_r_y.c_a_t_c_h.me.Fragments.BlackOps2;

import android.os.Bundle;
import android.os.Handler;
import android.preference.Preference;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.github.machinarius.preferencefragment.PreferenceFragment;

import t_r_y.c_a_t_c_h.me.AsyncTasks.AsyncTaskGetGamertags;
import t_r_y.c_a_t_c_h.me.BlackOps2XboxMods;
import t_r_y.c_a_t_c_h.me.Helper.Constants;
import t_r_y.c_a_t_c_h.me.Fragments.BaseFragmentWeaponSelector;
import t_r_y.c_a_t_c_h.me.Helper.Helper;
import t_r_y.c_a_t_c_h.me.R;

/**
 * Created by Admin on 11.09.2016.
 */
public class FragmentBlackOps2_3 extends PreferenceFragment implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {

    private android.widget.RadioGroup radioGroup;
    private android.support.v4.widget.SwipeRefreshLayout refreshLayout;
    private android.widget.RadioButton rbPlayer1;
    private android.widget.RadioButton rbPlayer2;
    private android.widget.RadioButton rbPlayer3;
    private android.widget.RadioButton rbPlayer4;

    @Override
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        View view = paramLayoutInflater.inflate(R.layout.fragment_black_ops_all_zm, paramViewGroup, false);
        this.rbPlayer4 = (RadioButton) view.findViewById(R.id.rbPlayer4);
        this.rbPlayer3 = (RadioButton) view.findViewById(R.id.rbPlayer3);
        this.rbPlayer2 = (RadioButton) view.findViewById(R.id.rbPlayer2);
        this.rbPlayer1 = (RadioButton) view.findViewById(R.id.rbPlayer1);
        this.refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refreshLayout);
        this.radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        radioGroup.check(R.id.rbPlayer1);
        refreshLayout.setOnRefreshListener(() -> update());
        if (!refreshLayout.isRefreshing()){
            refreshLayout.post(() -> refreshLayout.setRefreshing(true));
        }
        new Handler().postDelayed(() -> update(),1000);
        return view;
    }

    private void update() {
        new AsyncTaskGetGamertags(AsyncTaskGetGamertags.BO2_ZM, gamertags -> {
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

        }).execute();
    }

    @Override
    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        addPreferencesFromResource(R.xml.prefs_bo2_zm);

        findPreference("god").setOnPreferenceChangeListener(this);
        findPreference("ammo").setOnPreferenceChangeListener(this);
        findPreference("money").setOnPreferenceClickListener(this);
        findPreference("weapons").setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        boolean b = (boolean) o;
        boolean allPlayers = false;
        int player = 0;
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
                    BlackOps2XboxMods.Zombies.godMode(1, b);
                    BlackOps2XboxMods.Zombies.godMode(2, b);
                    BlackOps2XboxMods.Zombies.godMode(3, b);
                    BlackOps2XboxMods.Zombies.godMode(4, b);
                    break;
                case "ammo":
                    BlackOps2XboxMods.Zombies.unlimitedAmmo(1, b);
                    BlackOps2XboxMods.Zombies.unlimitedAmmo(2, b);
                    BlackOps2XboxMods.Zombies.unlimitedAmmo(3, b);
                    BlackOps2XboxMods.Zombies.unlimitedAmmo(4, b);
                    break;
            }
        } else {
            switch (preference.getKey()) {
                case "god":
                    BlackOps2XboxMods.Zombies.godMode(player, b);
                    break;
                case "ammo":
                    BlackOps2XboxMods.Zombies.unlimitedAmmo(player, b);
                    break;
            }
        }
        return true;
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        if (preference.getKey().equals("weapons")){
            BaseFragmentWeaponSelector bfws = BaseFragmentWeaponSelector.newInstance(Constants.BLACK_OPS_2_ZM,"Tranzit","Die Rise","MOTD","Buried","Origins");
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content,bfws)
                    .addToBackStack(null)
                    .commit();
        }else{
            int player = 0;
            boolean allPlayer = false;
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
                    allPlayer = true;
                    break;
            }

            if (allPlayer){
                BlackOps2XboxMods.Zombies.giveMoney(1);
                BlackOps2XboxMods.Zombies.giveMoney(2);
                BlackOps2XboxMods.Zombies.giveMoney(3);
                BlackOps2XboxMods.Zombies.giveMoney(4);
            } else{
                BlackOps2XboxMods.Zombies.giveMoney(player);
            }
        }

        return true;
    }
}

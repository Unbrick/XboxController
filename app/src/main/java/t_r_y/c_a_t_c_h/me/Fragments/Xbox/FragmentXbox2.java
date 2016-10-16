package t_r_y.c_a_t_c_h.me.Fragments.Xbox;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.Preference;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.github.machinarius.preferencefragment.PreferenceFragment;

import t_r_y.c_a_t_c_h.me.Helper;
import t_r_y.c_a_t_c_h.me.R;

/**
 * Created by Admin on 11.09.2016.
 */
public class FragmentXbox2 extends PreferenceFragment implements Preference.OnPreferenceClickListener {
    @Override
    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        addPreferencesFromResource(R.xml.prefs_xbox_actions);
        findPreference("xnotify").setOnPreferenceClickListener(this);
        findPreference("changegt").setOnPreferenceClickListener(this);
        findPreference("triggerTray").setOnPreferenceClickListener(this);
        findPreference("reboot").setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        switch (preference.getKey()){
            case "xnotify":
                altertDialogNotify();
                break;
            case "changegt":
                altertDialogChangeGT();
                break;
            case "triggerTray":
                Helper.cycleTray();
                break;
            case "reboot":
                alertDialogReboot();
                break;
        }
        return false;
    }

    private void altertDialogNotify(){
        final EditText input = new EditText(getActivity());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("xNotify");
        alertDialog.setMessage("Enter message to Notify here:");
        alertDialog.setView(input);
        alertDialog.setPositiveButton("Notify", (dialogInterface, i) -> {
            Helper.xNotify(input.getText().toString());
            dialogInterface.dismiss();
        }).show();
    }

    private void altertDialogChangeGT(){
        final EditText input = new EditText(getActivity());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("Change GT");
        alertDialog.setMessage("Enter new GT here:");
        alertDialog.setView(input);
        alertDialog.setPositiveButton("Change", (dialogInterface, i) -> {
            Helper.setGT(input.getText().toString());
            dialogInterface.dismiss();
        });
        alertDialog.setNegativeButton("Clear GT", (dialogInterface, i) -> {
            Helper.clearGT();
            dialogInterface.dismiss();
        }).show();
    }

    private void alertDialogReboot(){
        new AlertDialog.Builder(getActivity()).setTitle("Reboot")
                .setMessage("Do you really want to reboot your console?")
                .setPositiveButton("Yes", (dialogInterface, i) -> {
                    Helper.reboot();
                    dialogInterface.dismiss();
                })
                .setNegativeButton("No", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                })
                .show();
    }
}

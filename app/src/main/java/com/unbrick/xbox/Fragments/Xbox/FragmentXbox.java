package com.unbrick.xbox.Fragments.Xbox;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AlertDialog;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.ldoublem.loadingviewlib.LVEatBeans;
import com.tramsun.libs.prefcompat.Pref;
import com.unbrick.xbox.XBDM.listener.OnConnectionEstablishedListener;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;
import com.yarolegovich.lovelydialog.LovelyTextInputDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.unbrick.xbox.Activities.ActivitySetup;
import com.unbrick.xbox.Activities.MainActivity;
import com.unbrick.xbox.Xbox.GameScanner;
import com.unbrick.xbox.Xbox.XboxSocket;
import com.unbrick.xbox.Helper.Helper;
import com.unbrick.xbox.R;

/**
 * Created by Admin on 07.09.2016.
 */
public class FragmentXbox extends Fragment {
    private static boolean connected = false;
    @BindView(R.id.tvSetupCpuKey)
    TextView tvSetupCpuKey;
    @BindView(R.id.tvDashboard)
    TextView tvDashboard;
    @BindView(R.id.tvCpuTemp)
    TextView tvCpuTemp;
    @BindView(R.id.tvGPUTemp)
    TextView tvGPUTemp;
    @BindView(R.id.tvRAMTemp)
    TextView tvRAMTemp;
    @BindView(R.id.tvMotherboardTemp)
    TextView tvMotherboardTemp;
    @BindView(R.id.tvBoardName)
    TextView tvBoardName;
    @BindView(R.id.tvConsoleName)
    TextView tvConsoleName;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;
    @BindView(R.id.fabChangeGT)
    FloatingActionButton fabChangeGT;
    @BindView(R.id.fabNotify)
    FloatingActionButton fabNotify;
    @BindView(R.id.fabRebootConsole)
    FloatingActionButton fabReboot;
    @BindView(R.id.floatingActionMenu)
    FloatingActionMenu floatingActionMenu;
    @BindView(R.id.tvConsoleNameHeader)
    TextView tvConsoleNameHeader;
    XboxSocket xboxSocket = XboxSocket.getInstance();

    public static void setConnected(boolean connected) {
        FragmentXbox.connected = connected;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_xbox, container, false);
        ButterKnife.bind(this, view);

        swiperefresh.setOnRefreshListener(() -> update(null));
        fabChangeGT.setOnClickListener(v -> {
            floatingActionMenu.close(true);
            altertDialogChangeGT();
        });
        fabNotify.setOnClickListener(v -> {
            floatingActionMenu.close(true);
            altertDialogNotify();
        });
        fabReboot.setOnClickListener(v -> {
            floatingActionMenu.close(true);
            alertDialogReboot();
        });
        if (Pref.getBoolean("setup")){
            if (!connected){
                connect();
            }else{
                update(null);
            }
        }

        return view;
    }

    public void connect(){
        AlertDialog dialog = showConnectionDialog();
        xboxSocket.connect(new OnConnectionEstablishedListener() {
            @Override
            public void established() {
                connected = true;
                dialog.dismiss();
                update(dialog);
            }

            @Override
            public void failed(Exception e) {
                dialog.dismiss();
                showErrorDialog();
                e.printStackTrace();
            }
        });
    }

    private void update(AlertDialog dialog) {
        xboxSocket.xbdm.getConsole(console -> {
            if (getActivity() != null)
                getActivity().runOnUiThread(() -> {
                    if (swiperefresh.isRefreshing())
                        swiperefresh.setRefreshing(false);

                    if (console == null) {
                        if (dialog != null)
                            dialog.dismiss();
                        showErrorDialog();
                        Helper.makeSnackbarError(getActivity(), "Something went wrong, please try again!");
                    } else {
                        new Handler().postDelayed(() -> {
                            if (dialog != null)
                                dialog.dismiss();
                        },500);
                        tvConsoleName.setText(console.getDebugName());
                        tvBoardName.setText(console.getBoardName());
                        tvMotherboardTemp.setText(String.valueOf(console.getMotherboardTemp()));
                        tvRAMTemp.setText(String.valueOf(console.getRamTemp()));
                        tvGPUTemp.setText(String.valueOf(console.getGpuTemp()));
                        tvCpuTemp.setText(String.valueOf(console.getCpuTemp()));
                        tvDashboard.setText(console.getDashboard());
                        tvSetupCpuKey.setText(console.getCpukey());
                        tvConsoleNameHeader.setText(console.getDebugName());
                        new GameScanner().baseSearch();
                    }
                });
        });
    }

    private AlertDialog showConnectionDialog() {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.dialog_connecting, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(),R.style.MaterialBaseTheme_AlertDialog);
        builder.setView(dialoglayout);
        builder.setCancelable(false);
        ((LVEatBeans)dialoglayout.findViewById(R.id.lvPacManAnimation)).startAnim();
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics()));
        return dialog;
    }

    private void showErrorDialog() {
        getActivity().runOnUiThread(() -> new LovelyStandardDialog(getContext())
                .setTopColorRes(R.color.md_red_400)
                .setIcon(R.drawable.ic_reboot_console)
                .setTitle("Failed to connect to your console!")
                .setMessage("Please ensure that your console is turned on and has XBDM and JRPC2 installed!")
                .setPositiveButton("Try again", v -> connect())
                .setNegativeButton("Setup a new Xbox", v -> startActivity(new Intent(getActivity(), ActivitySetup.class).putExtra("backAllowed",true)))
                .show());
    }

    private void altertDialogNotify() {
        new LovelyTextInputDialog(getContext())
                .setTopColorRes(R.color.md_green_400)
                .setTitle("xNotify")
                .setMessage("Please enter text to make a notification")
                .setIcon(R.drawable.ic_xnotify)
                .setConfirmButton(android.R.string.ok, text -> xboxSocket.xbdm.xNotify(text))
                .show();
    }

    private void altertDialogChangeGT() {
        new LovelyTextInputDialog(getContext())
                .setTopColorRes(R.color.md_green_400)
                .setTitle("Gamertag changer")
                .setMessage("Please enter your new GT")
                .setIcon(R.drawable.ic_change_gamertag)
                .setConfirmButton(android.R.string.ok, text -> xboxSocket.xbdm.setGamertag(text))
                .show();
    }

    private void alertDialogReboot() {
        new LovelyStandardDialog(getContext())
                .setTopColorRes(R.color.md_green_400)
                .setIcon(R.drawable.ic_reboot_console)
                .setTitle("Reboot")
                .setMessage("How do you want to reboot?")
                .setPositiveButton("Cold", v -> xboxSocket.xbdm.reboot())
                .setNegativeButton("Warm", v -> xboxSocket.xbdm.rebootToDash())
                .show();
    }

}

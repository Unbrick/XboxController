package com.unbrick.xbox.Helper;

import androidx.appcompat.widget.Toolbar;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import com.unbrick.xbox.Activities.MainActivity;
import com.unbrick.xbox.Fragments.BlackOps2.BlackOps2BaseFragment;
import com.unbrick.xbox.Fragments.BlackOps3.BlackOps3BaseFragment;
import com.unbrick.xbox.Fragments.FragmentGames;
import com.unbrick.xbox.Fragments.Settings.SettingsFragment;
import com.unbrick.xbox.Fragments.Xbox.FragmentXbox;
import com.unbrick.xbox.R;

/**
 * Created by Admin on 13.09.2016.
 */


public class DrawerCreator implements Drawer.OnDrawerItemClickListener {
    private ProfileDrawerItem pdi;
    private AccountHeader headerResult;
    private static Drawer drawer;
    private static MainActivity mainActivity;
    private Toolbar toolbar;
    private static PrimaryDrawerItem itemGames;

    public DrawerCreator(final MainActivity mainActivity, Toolbar toolbar) {
        this.mainActivity = mainActivity;
        this.toolbar = toolbar;

//       XboxSocket.getInstance().getConsole(console -> mainActivity.runOnUiThread(() -> {
//            if (console != null){
//                pdi.withName(console.getDebugName()).withEmail(console.getBoardName()+ " with dashboard "+console.getDashboard());
//                headerResult.updateProfile(pdi);
//            }else Helper.makeSnackbarError(mainActivity, "Something went wrong, please try again!");
//        }));
    }

    public static void enableGameEntry(){
        mainActivity.runOnUiThread(() -> {
            itemGames.withEnabled(true);
            drawer.updateItem(itemGames);
        });
    }

    public DrawerCreator setupDrawer() {

        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("XBOX Mods");
        PrimaryDrawerItem itemBO2 = new PrimaryDrawerItem().withIdentifier(2).withName("Black Ops II");
        PrimaryDrawerItem itemBO3 = new PrimaryDrawerItem().withIdentifier(3).withName("Black Ops III");
        PrimaryDrawerItem itemGTAV = new PrimaryDrawerItem().withIdentifier(4).withName("GTA V");
        itemGames = new PrimaryDrawerItem().withIdentifier(5).withName("Games").withEnabled(false);

        PrimaryDrawerItem footer = new PrimaryDrawerItem().withIdentifier(10).withName("Settings");

        pdi = new ProfileDrawerItem().withIcon(mainActivity.getResources().getDrawable(R.drawable.black));

        headerResult = new AccountHeaderBuilder()
                .withActivity(mainActivity)
                .withHeaderBackground(R.drawable.drawer_header)
                .addProfiles(pdi)
                .withSelectionListEnabledForSingleProfile(false)
                .build();

        drawer = new DrawerBuilder()
                .withActivity(mainActivity)
                .withAccountHeader(headerResult)
                .withToolbar(toolbar)
                .addDrawerItems(item1, itemGames, new DividerDrawerItem(), itemBO2,itemBO3)
                .addStickyDrawerItems(footer)
                .withOnDrawerItemClickListener(this)
                .build();

        return this;
    }

    @Override
    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
        switch ((int)drawerItem.getIdentifier()){
            case 1:
                mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.content,new FragmentXbox()).commit();
                drawer.closeDrawer();
                break;
            case 2:
                mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.content, new BlackOps2BaseFragment()).commit();
                drawer.closeDrawer();
                break;
            case 3:
                mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.content, new BlackOps3BaseFragment()).commit();
                drawer.closeDrawer();
                break;
            case 5:
                mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.content, new FragmentGames()).commit();
                drawer.closeDrawer();
                break;
            case 10:
                mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.content, new SettingsFragment()).commit();
                drawer.closeDrawer();
                break;
        }
        return true;
    }

}

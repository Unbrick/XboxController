package t_r_y.c_a_t_c_h.me;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import t_r_y.c_a_t_c_h.me.AsyncTasks.AsyncTaskGetConsole;
import t_r_y.c_a_t_c_h.me.Fragments.BlackOps2.BlackOps2BaseFragment;
import t_r_y.c_a_t_c_h.me.Fragments.BlackOps3.BlackOps3BaseFragment;
import t_r_y.c_a_t_c_h.me.Fragments.Settings.SettingsFragment;
import t_r_y.c_a_t_c_h.me.Fragments.Xbox.FragmentXboxParent;

/**
 * Created by Admin on 13.09.2016.
 */


public class DrawerCreator implements Drawer.OnDrawerItemClickListener {
    private ProfileDrawerItem pdi;
    private AccountHeader headerResult;
    private Drawer drawer;
    private MainActivity mainActivity;
    private Toolbar toolbar;

    public DrawerCreator(final MainActivity mainActivity, Toolbar toolbar) {
        this.mainActivity = mainActivity;
        this.toolbar = toolbar;

        new AsyncTaskGetConsole(console -> mainActivity.runOnUiThread(() -> {
            if (console != null){
                pdi.withName(console.getDebugName()).withEmail(console.getBoardName()+ " with dashboard "+console.getDashboard());
                headerResult.updateProfile(pdi);
            }else Helper.makeSnackbarError(mainActivity, "Something went wrong, please try again!");
        })).execute();
    }

    public DrawerCreator setupDrawer() {

        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("XBOX Mods");
        PrimaryDrawerItem itemBO2 = new PrimaryDrawerItem().withIdentifier(2).withName("Black Ops II");
        PrimaryDrawerItem itemBO3 = new PrimaryDrawerItem().withIdentifier(3).withName("Black Ops III");
        PrimaryDrawerItem itemGTAV = new PrimaryDrawerItem().withIdentifier(4).withName("GTA V");

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
                .addDrawerItems(item1, new DividerDrawerItem(), itemBO2,itemBO3)
                .addStickyDrawerItems(footer)
                .withOnDrawerItemClickListener(this)
                .build();

        return this;
    }

    @Override
    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
        switch ((int)drawerItem.getIdentifier()){
            case 1:
                mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.content,new FragmentXboxParent()).commit();
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
            case 10:
                mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.content, new SettingsFragment()).commit();
                drawer.closeDrawer();
                break;
        }
        return true;
    }

}

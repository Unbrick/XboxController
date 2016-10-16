package t_r_y.c_a_t_c_h.me.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.tramsun.libs.prefcompat.Pref;

import butterknife.BindView;
import butterknife.ButterKnife;
import t_r_y.c_a_t_c_h.me.Helper.ConnectionChecker;
import t_r_y.c_a_t_c_h.me.Helper.DrawerCreator;
import t_r_y.c_a_t_c_h.me.Fragments.Xbox.FragmentXboxParent;
import t_r_y.c_a_t_c_h.me.R;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    private ConnectionChecker connectionChecker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(myToolbar);
        Pref.init(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.content, new FragmentXboxParent()).commit();

        if (!Pref.getBoolean("setup", false)) {
            startActivity(new Intent(MainActivity.this, ActivitySetup.class));
        }

        getSupportActionBar().setTitle("XBOX");

        connectionChecker = new ConnectionChecker(this).runChecker();
        new DrawerCreator(this, myToolbar).setupDrawer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        connectionChecker.stopChecker();
    }
}

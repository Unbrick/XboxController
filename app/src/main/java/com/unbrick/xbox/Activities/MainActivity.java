package com.unbrick.xbox.Activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.tramsun.libs.prefcompat.Pref;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.unbrick.xbox.Fragments.Xbox.FragmentXbox;
import com.unbrick.xbox.Helper.ConnectionChecker;
import com.unbrick.xbox.Helper.DrawerCreator;
import com.unbrick.xbox.R;

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

        getSupportFragmentManager().beginTransaction().replace(R.id.content, new FragmentXbox()).commit();

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

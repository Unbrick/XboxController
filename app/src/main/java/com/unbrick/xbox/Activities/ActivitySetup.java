package com.unbrick.xbox.Activities;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.androidadvance.topsnackbar.TSnackbar;
import com.badoualy.stepperindicator.StepperIndicator;

import com.unbrick.xbox.Adapter.SetupAdapter;
import com.unbrick.xbox.Helper.NonSwipeableViewPager;
import com.unbrick.xbox.R;

/**
 * Created by Admin on 10.09.2016.
 */
public class ActivitySetup extends AppCompatActivity {

    private int backPressedCount = 0;
    private boolean backAllowed = false;

    public static NonSwipeableViewPager getViewPager() {
        return viewPager;
    }

    private static NonSwipeableViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        backAllowed = getIntent().getBooleanExtra("backAllowed",false);
        StepperIndicator indicator = (StepperIndicator) findViewById(R.id.stepperIndicator);
        viewPager = (NonSwipeableViewPager) findViewById(R.id.pagerSetup);
        viewPager.setAdapter(new SetupAdapter(getSupportFragmentManager()));
        indicator.setViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
        if (!backAllowed){
            if (backPressedCount == 0){
                TSnackbar.make(findViewById(R.id.snackbarContainer),"Press again to exit",TSnackbar.LENGTH_LONG);
                backPressedCount++;
            }else if(backPressedCount == 1){
                backPressedCount = 0;
                System.exit(0);
            }
        }
    }

}


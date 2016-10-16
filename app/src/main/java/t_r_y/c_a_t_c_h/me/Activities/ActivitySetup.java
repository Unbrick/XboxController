package t_r_y.c_a_t_c_h.me.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.androidadvance.topsnackbar.TSnackbar;
import com.badoualy.stepperindicator.StepperIndicator;

import t_r_y.c_a_t_c_h.me.Adapter.SetupAdapter;
import t_r_y.c_a_t_c_h.me.Helper.NonSwipeableViewPager;
import t_r_y.c_a_t_c_h.me.R;

/**
 * Created by Admin on 10.09.2016.
 */
public class ActivitySetup extends AppCompatActivity {

    private int backPressedCount = 0;

    public static NonSwipeableViewPager getViewPager() {
        return viewPager;
    }

    private static NonSwipeableViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        StepperIndicator indicator = (StepperIndicator) findViewById(R.id.stepperIndicator);
        viewPager = (NonSwipeableViewPager) findViewById(R.id.pagerSetup);
        viewPager.setAdapter(new SetupAdapter(getSupportFragmentManager()));
        indicator.setViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
        if (backPressedCount == 0){
            TSnackbar.make(findViewById(R.id.snackbarContainer),"Press again to exit",TSnackbar.LENGTH_LONG);
            backPressedCount++;
        }else if(backPressedCount == 1){
            backPressedCount = 0;
            System.exit(0);
        }
    }

}


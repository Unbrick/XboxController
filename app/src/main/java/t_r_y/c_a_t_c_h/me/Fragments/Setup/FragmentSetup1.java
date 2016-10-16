package t_r_y.c_a_t_c_h.me.Fragments.Setup;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

import t_r_y.c_a_t_c_h.me.Activities.ActivitySetup;
import t_r_y.c_a_t_c_h.me.R;

/**
 * Created by Admin on 10.09.2016.
 */
public class FragmentSetup1 extends Fragment implements View.OnClickListener {

    private static final Pattern PATTERN = Pattern.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

    private Button btnScan;
    private EditText etIP1;
    private EditText etIP2;
    private EditText etIP3;
    private EditText etIP4;
    private Button btnProceed;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setup_1,container,false);
        this.btnProceed = (Button) view.findViewById(R.id.btnProceed);
        this.etIP4 = (EditText) view.findViewById(R.id.etIP4);
        this.etIP3 = (EditText) view.findViewById(R.id.etIP3);
        this.etIP2 = (EditText) view.findViewById(R.id.etIP2);
        this.etIP1 = (EditText) view.findViewById(R.id.etIP1);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            etIP1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            etIP2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            etIP3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            etIP4.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }

        etIP1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
            if (editable.length() == 3){
                etIP2.requestFocus();
            }
            }
        });
        etIP2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 3){
                    etIP3.requestFocus();
                }
            }
        });
        etIP3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 3){
                    etIP4.requestFocus();
                }
            }
        });

        etIP4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 3){
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                }
            }
        });

        btnProceed.setOnClickListener(this);

        ActivitySetup.getViewPager().setScrollingEnabled(false);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnProceed:
                proceed(etIP1.getText().toString()+"."+etIP2.getText().toString()+"."+etIP3.getText().toString()+"."+etIP4.getText().toString());
                break;
        }
    }

    private void proceed(String ip){
        if (PATTERN.matcher(ip).matches()){
            FragmentSetup2.getTvChosenIp().setText(ip);
            ActivitySetup.getViewPager().setCurrentItem(ActivitySetup.getViewPager().getCurrentItem()+1,true);
        }else{
            Toast.makeText(getActivity(), "Please enter a valid IP", Toast.LENGTH_LONG).show();
        }
    }

}

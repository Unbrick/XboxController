package com.unbrick.xbox.Fragments.Setup;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tramsun.libs.prefcompat.Pref;

import java.util.Objects;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.unbrick.xbox.Activities.ActivitySetup;
import com.unbrick.xbox.R;
import com.unbrick.xbox.XBDM.listener.OnConnectionEstablishedListener;
import com.unbrick.xbox.Xbox.XboxSocket;

/**
 * Created by Admin on 10.09.2016.
 */
public class FragmentSetup1 extends Fragment implements View.OnClickListener {

    private static final Pattern PATTERN = Pattern.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

    @BindView(R.id.etIP1)
    EditText etIP1;
    @BindView(R.id.etIP2)
    EditText etIP2;
    @BindView(R.id.etIP3)
    EditText etIP3;
    @BindView(R.id.etIP4)
    EditText etIP4;
    @BindView(R.id.btnProceed)
    Button btnProceed;

    ProgressDialog pd;


    private class CustomTextWatcher implements TextWatcher{

        private EditText editText;

        CustomTextWatcher(EditText nextEditText){
            this.editText = nextEditText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (editText != null && s.length() == 3){
                editText.requestFocus();
            } else if (editText == null) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            }
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setup_1,container,false);
        ButterKnife.bind(this,view);

        pd = new ProgressDialog(getActivity());
        pd.setIndeterminate(true);
        pd.setMessage("Please wait...");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            etIP1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            etIP2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            etIP3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            etIP4.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }

        etIP1.addTextChangedListener(new CustomTextWatcher(etIP2));
        etIP2.addTextChangedListener(new CustomTextWatcher(etIP3));
        etIP3.addTextChangedListener(new CustomTextWatcher(etIP4));
        etIP4.addTextChangedListener(new CustomTextWatcher(null));

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
            pd.show();
            Pref.putString("ip",ip);
            XboxSocket.getInstance().connect(new OnConnectionEstablishedListener() {
                @Override
                public void established() {
                    requireActivity().runOnUiThread(() -> {
                        pd.dismiss();
                        ActivitySetup.getViewPager().setCurrentItem(ActivitySetup.getViewPager().getCurrentItem()+1,true);
                    });
                }

                @Override
                public void failed(Exception e) {
                    e.printStackTrace();
                    showErrorDialog();
                }
            });
        }else{
            Toast.makeText(getActivity(), "Please enter a valid IP", Toast.LENGTH_LONG).show();
        }
    }

    private void showErrorDialog() {
        getActivity().runOnUiThread(() -> {
            new AlertDialog.Builder(getActivity()).setTitle("Failed!")
                    .setMessage("Connecting to your Xbox failed! Please ensure your Xbox has XBDM and JRPC2 installed and is turned on!")
                    .setPositiveButton("Aye", (dialogInterface, i) -> {
                        dialogInterface.dismiss();
                    }).setNegativeButton("Re-enter IP", (dialogInterface, i) -> {
                dialogInterface.dismiss();
                ActivitySetup.getViewPager().setCurrentItem(ActivitySetup.getViewPager().getCurrentItem()-1,true);
            }).show();
            pd.dismiss();
        });
    }

}

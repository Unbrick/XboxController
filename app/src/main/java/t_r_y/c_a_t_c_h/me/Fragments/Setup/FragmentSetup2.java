package t_r_y.c_a_t_c_h.me.Fragments.Setup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.tramsun.libs.prefcompat.Pref;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import t_r_y.c_a_t_c_h.me.Activities.ActivitySetup;
import t_r_y.c_a_t_c_h.me.Xbox.GameScanner;
import t_r_y.c_a_t_c_h.me.Xbox.XboxSocket;
import t_r_y.c_a_t_c_h.me.R;

/**
 * Created by Admin on 10.09.2016.
 */
public class FragmentSetup2 extends Fragment{

    @BindView(R.id.tvChosenIp)
    TextView tvChosenIp;
    @BindView(R.id.btnValidate)
    Button btnValidate;
    @BindView(R.id.spinnerDrive)
    Spinner driveSpinner;
    @BindView(R.id.spinnerDirectory)
    Spinner directorySpinner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setup_2,container,false);
        ButterKnife.bind(this,view);

        tvChosenIp.setText(Pref.getString("ip"));

        btnValidate.setOnClickListener(v -> {
            String drive = (String) driveSpinner.getSelectedItem();
            String dir = (String)  directorySpinner.getSelectedItem();
            dir = dir+"\\";
            Pref.putString("gamedrive",drive);
            Pref.putString("gamedir",dir);
            new GameScanner(getContext()).baseSearch();
            ActivitySetup.getViewPager().setScrollingEnabled(true);
            ActivitySetup.getViewPager().setCurrentItem(ActivitySetup.getViewPager().getCurrentItem()+1,true);
        });



        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser)
            fetch();
    }

    private void fetch() {
        Log.d("FragmentSetup2","fetch started");
        XboxSocket.getInstance().drivelist(lines -> {
            getActivity().runOnUiThread(() -> {
                lines.remove(0); //200- connected
                lines.remove(0); //200- connected
                lines.remove(lines.size()-1);   //final dot
                for (int i = 0; i<lines.size();i++){
                    String newLine = lines.get(i).replace("drivename=\"","");
                    newLine = newLine.replace("\"","");
                    newLine = newLine + ":\\";
                    lines.set(i,newLine);
                }
                ArrayAdapter<String> driveadapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, lines);
                driveSpinner.setAdapter(driveadapter);
                driveSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String selected = driveadapter.getItem(position);
                        XboxSocket.getInstance().dirList(lines1 -> {
                            getActivity().runOnUiThread(() -> {
                                lines1.remove(0); //200- connected
                                lines1.remove(0); //200- connected
                                lines1.remove(lines1.size()-1);   //final dot
                                ArrayList<String> dirList = new ArrayList<String>();
                                for (String s : lines1){
                                    if (s.contains("directory")){
                                        String line = s;
                                        line = line.substring(line.indexOf("\"")+1,line.indexOf("\"",line.indexOf("\"")+2));
                                        dirList.add(line);
                                    }

                                }

                                if (dirList.size()==0){
                                    dirList.add("No directories here!");
                                }

                                ArrayAdapter<String> driveadapter1 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, dirList);
                                directorySpinner.setAdapter(driveadapter1);
                            });

                        },selected,"");
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {}
                });
            });
        });
    }
}

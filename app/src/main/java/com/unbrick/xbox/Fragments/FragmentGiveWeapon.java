package com.unbrick.xbox.Fragments;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.unbrick.xbox.BlackOps2XboxMods;
import com.unbrick.xbox.BlackOps3XboxMods;
import com.unbrick.xbox.Helper.Constants;
import com.unbrick.xbox.R;

/**
 * Created by Admin on 12.09.2016.
 */
public class FragmentGiveWeapon extends Fragment{
    private int map;


    public static FragmentGiveWeapon newInstance(int map) {
        Bundle args = new Bundle();
        args.putInt("map",map);
        FragmentGiveWeapon fragment = new FragmentGiveWeapon();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listview,container,false);
        ListView listView = (ListView) view.findViewById(R.id.listView);
        map = getArguments().getInt("map");


        final ArrayAdapter<String> weaponAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1);

        switch (map){
            case Constants.BLACK_OPS_2_ZM_TRANZIT:
                for (String weapon : Constants.weaponsmobTranzit.keySet())
                    weaponAdapter.add(weapon);
                break;
            case Constants.BLACK_OPS_2_ZM_MOTD:
                for (String weapon : Constants.weaponsmobOTD.keySet())
                    weaponAdapter.add(weapon);
                break;
            case Constants.BLACK_OPS_2_ZM_DIERISE:
                for (String weapon : Constants.weaponsdieRise.keySet())
                    weaponAdapter.add(weapon);
                break;
            case Constants.BLACK_OPS_2_ZM_BURIED:
                for (String weapon : Constants.weaponsBuried.keySet())
                    weaponAdapter.add(weapon);
                break;
            case Constants.BLACK_OPS_2_ZM_ORIGINS:
                for (String weapon : Constants.weaponsOrigins.keySet())
                    weaponAdapter.add(weapon);
                break;
            case Constants.BLACK_OPS_3_ZM_SHADOWS:
                for (String weapon : Constants.weaponsShadowsOfEvil.keySet())
                    weaponAdapter.add(weapon);
                break;
            case Constants.BLACK_OPS_3_ZM_EISENDRACHE:
                for (String weapon : Constants.weaponsEisendrache.keySet())
                    weaponAdapter.add(weapon);
                break;
            case Constants.BLACK_OPS_3_ZM_GIANT:
                for (String weapon : Constants.weaponsTheGiant.keySet())
                    weaponAdapter.add(weapon);
                break;
        }

        listView.setAdapter(weaponAdapter);
        listView.setClickable(true);
        listView.setOnItemClickListener((arg0, arg1, position, arg3) -> {
            switch (map){
                case Constants.BLACK_OPS_2_ZM_TRANZIT:
                    giveWeaponBo2Zm(Constants.weaponsmobTranzit.get(weaponAdapter.getItem(position)));
                    break;
                case Constants.BLACK_OPS_2_ZM_MOTD:
                    giveWeaponBo2Zm(Constants.weaponsmobOTD.get(weaponAdapter.getItem(position)));
                    break;
                case Constants.BLACK_OPS_2_ZM_DIERISE:
                    giveWeaponBo2Zm(Constants.weaponsdieRise.get(weaponAdapter.getItem(position)));
                    break;
                case Constants.BLACK_OPS_2_ZM_BURIED:
                    giveWeaponBo2Zm(Constants.weaponsBuried.get(weaponAdapter.getItem(position)));
                    break;
                case Constants.BLACK_OPS_2_ZM_ORIGINS:
                    giveWeaponBo2Zm(Constants.weaponsOrigins.get(weaponAdapter.getItem(position)));
                    break;
                case Constants.BLACK_OPS_3_ZM_SHADOWS:
                    giveWeaponBo3Zm(Constants.weaponsShadowsOfEvil.get(weaponAdapter.getItem(position)));
                    break;
                case Constants.BLACK_OPS_3_ZM_EISENDRACHE:
                    giveWeaponBo3Zm(Constants.weaponsEisendrache.get(weaponAdapter.getItem(position)));
                    break;
                case Constants.BLACK_OPS_3_ZM_GIANT:
                    giveWeaponBo3Zm(Constants.weaponsTheGiant.get(weaponAdapter.getItem(position)));
                    break;
            }

        });
        return view;
    }

    private void giveWeaponBo2Zm(int weapon) {
        BlackOps2XboxMods.Zombies.giveWeapon(weapon,1);
    }

    private void giveWeaponBo3Zm(int weapon) {
        BlackOps3XboxMods.Zombies.giveWeapon(weapon,1,true);
    }

}

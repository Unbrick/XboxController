package com.unbrick.xbox.Fragments;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

import com.squareup.picasso.Picasso;
import com.unbrick.xbox.Adapter.GameCoverFlowAdapter;
import com.unbrick.xbox.Helper.Helper;
import com.unbrick.xbox.R;
import com.unbrick.xbox.Xbox.Game;
import com.unbrick.xbox.Xbox.GameScanner;
import com.unbrick.xbox.Xbox.XboxSocket;

/**
 * Created by Admin on 09.12.2016.
 */

public class FragmentGames extends Fragment {

    @BindView(R.id.coverflow)
    FeatureCoverFlow coverFlow;
    @BindView(R.id.gameName)
    TextView gamename;
    @BindView(R.id.btnStartGame)
    Button btnStartGame;
    @BindView(R.id.btnStartGameMP)
    Button btnStartGameMP;
    @BindView(R.id.tvTitleID)
    TextView tvTitleId;
    @BindView(R.id.tvGameDir)
    TextView tvGameDir;
    public GameCoverFlowAdapter gameCoverFlowAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_games, container, false);
        ButterKnife.bind(this, view);

        if (GameScanner.getGames().size() == 0){
            Helper.makeSnackbarError(requireActivity(),"Games not found!");
        }

        gameCoverFlowAdapter = new GameCoverFlowAdapter(getContext(), GameScanner.getGames());
        coverFlow.setAdapter(gameCoverFlowAdapter);


        btnStartGame.setOnClickListener(v -> {
            Game item = gameCoverFlowAdapter.getItem(coverFlow.getScrollPosition());
            XboxSocket.getInstance().xbdm.magicbootTitle(item);
        });

        btnStartGameMP.setOnClickListener(v -> {
            Game item = gameCoverFlowAdapter.getItem(coverFlow.getScrollPosition());
            XboxSocket.getInstance().xbdm.magicbootTitleMP(item);
        });

        coverFlow.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                Game item = gameCoverFlowAdapter.getItem(position);
                gamename.setText(item.getName());
                tvTitleId.setText(item.getTitleID());
                tvGameDir.setText(item.getDirpath());
                if (!item.isDefault_xex()){
                    btnStartGame.setVisibility(View.GONE);
                } else if (item.isDefault_xex()){
                    btnStartGame.setVisibility(View.VISIBLE);
                }
                if (!item.isDefault_mp_xex()){
                    btnStartGameMP.setVisibility(View.GONE);
                }else if (item.isDefault_mp_xex()){
                    btnStartGameMP.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onScrolling() {
                //TODO CoverFlow began scrolling
            }
        });

        coverFlow.setOnItemClickListener((parent, view1, position, id) -> {
            if (coverFlow.getScrollPosition() == position) {
                XboxSocket.getInstance().xbdm.magicbootTitle(gameCoverFlowAdapter.getItem(position));
            } else {
                coverFlow.scrollToPosition(position);
            }
        });

        GameScanner.addListener(mGame -> {
            gameCoverFlowAdapter.notifyDataSetChanged();
            Picasso.with(requireContext())
                    .load(mGame.getCoverUrl())
                    .fetch();
        });

        return view;


    }
}

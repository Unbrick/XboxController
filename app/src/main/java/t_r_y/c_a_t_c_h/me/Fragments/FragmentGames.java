package t_r_y.c_a_t_c_h.me.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;
import t_r_y.c_a_t_c_h.me.Adapter.GameCoverFlowAdapter;
import t_r_y.c_a_t_c_h.me.Helper.Helper;
import t_r_y.c_a_t_c_h.me.R;
import t_r_y.c_a_t_c_h.me.Xbox.Game;
import t_r_y.c_a_t_c_h.me.Xbox.GameScanner;
import t_r_y.c_a_t_c_h.me.Xbox.XboxSocket;

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

        if (GameScanner.getGames() == null){
            Helper.makeSnackbarError(getActivity(),"Games not found!");
        }

        gameCoverFlowAdapter = new GameCoverFlowAdapter(getContext(), GameScanner.getGames());
        coverFlow.setAdapter(gameCoverFlowAdapter);


        btnStartGame.setOnClickListener(v -> {
            Game item = gameCoverFlowAdapter.getItem(coverFlow.getScrollPosition());
            XboxSocket.getInstance().magicbootTitle(item);
        });

        btnStartGameMP.setOnClickListener(v -> {
            Game item = gameCoverFlowAdapter.getItem(coverFlow.getScrollPosition());
            XboxSocket.getInstance().magicbootTitleMP(item);
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

        return view;

    }
}

package t_r_y.c_a_t_c_h.me.Xbox;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.squareup.picasso.Picasso;
import com.tramsun.libs.prefcompat.Pref;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import t_r_y.c_a_t_c_h.me.Activities.MainActivity;
import t_r_y.c_a_t_c_h.me.Adapter.GameCoverFlowAdapter;
import t_r_y.c_a_t_c_h.me.Fragments.FragmentGames;
import t_r_y.c_a_t_c_h.me.Helper.DrawerCreator;
import t_r_y.c_a_t_c_h.me.XboxUnityApi.Responses.GameInfoResponse.GamePhotoIdResponse;
import t_r_y.c_a_t_c_h.me.XboxUnityApi.Responses.GameListResponse.GameListResponse;
import t_r_y.c_a_t_c_h.me.XboxUnityApi.Responses.GameListResponse.Item;
import t_r_y.c_a_t_c_h.me.XboxUnityApi.XboxUnityProvider;

/**
 * Created by Admin on 08.12.2016.
 */

public class GameScanner {

    private static boolean scanned = false;
    private static ArrayList<Game> games = new ArrayList<>();
    private XboxSocket socket;
    private XboxSocket.OnResultListener masterResultListener;
    private int gamesCounter = 0;
    private int gamesScanned = 0;
    private String drive;
    private String basePath;
    private MainActivity activity;
    private Context mContext;

    public GameScanner(Context context) {
        this.mContext = context;
        this.drive = Pref.getString("gamedrive");
        this.basePath = Pref.getString("gamedir");
        this.socket = XboxSocket.getInstance();
    }

    public GameScanner(MainActivity activity, XboxSocket.OnResultListener onResultListener) {
        this.drive = Pref.getString("gamedrive");
        this.basePath = Pref.getString("gamedir");
        this.socket = XboxSocket.getInstance();
        this.masterResultListener = onResultListener;
        this.activity = activity;
    }

    public static boolean isScanned() {
        return scanned;
    }

    public static ArrayList<Game> getGames() {
        return games;
    }

    public void baseSearch(){
        if (!isScanned()){
            socket.dirList(lines -> {
                lines.add("\"L\"I\"N\"E\"E\"N\"D\"I\"N\"G\"");
                gamesCounter = lines.size()-2; //200- multiline response + "."
                for (String directory : lines){
                    if (!directory.contains("response") && directory.length()>10){
                        Log.d("GAMESCANNER",directory);
                        verify(basePath + directory.substring(
                                directory.indexOf("\"")+1,
                                directory.indexOf("\"",directory.indexOf("\"")+2)) + "\\",
                                o -> {if (o != null) games.add((Game)o);},directory);

                    }
                }
            },drive,basePath);
        }
    }

    private void verify(String directory, XboxSocket.OnResultListener onResultListener, String name) {
        socket.dirList(lines -> {
            Game game = null;
            gamesScanned++;
            String nameFinal = name.substring(name.indexOf("\"")+1,name.indexOf("\"",name.indexOf("\"")+2));
            for (String line : lines){
                System.out.println(line);
                if (!line.contains("denied")){
                    if (line.contains("default.xex") || line.contains("Default.xex")){
                        game = new Game(drive+directory,nameFinal);
                        game.setDefault_xex(true);
                    }
                    if (line.contains("default_mp.xex") || line.contains("Default_mp.xex")){
                        if (game!=null){
                            game.setDefault_mp_xex(true);
                        } else{
                            game = new Game(drive+directory,nameFinal);
                            game.setDefault_mp_xex(true);
                        }
                    }
                }
            }
            if (gamesScanned==gamesCounter){
                if (masterResultListener != null)
                    masterResultListener.result(games);
                scanned = true;
                Log.d("GAMESCANNER","FINISHED");
                DrawerCreator.enableGameEntry();
                fetchTitles();
            }
            onResultListener.result(game);
        },drive,directory);
    }

    private void fetchTitles() {
        for (Game game : games){
            String gameName = game.getName();
            if (gameName.contains(" SP") || gameName.contains(" MP")){
                gameName = gameName.replace(" SP","").replace(" MP","");
            }
            if (gameName.contains("COD ")){
                gameName = gameName.replace("COD ","COD: ");
            }
            if (gameName.contains("Sequel")){
                gameName = gameName.replace("Borderlands","Borderlands:");
            }
            if (gameName.equalsIgnoreCase("Minecraft")){
                gameName = "Minecraft: Xbox 360 Edition";
            }
            if (gameName.contains("Call of Duty")){
                gameName = gameName.replace("Call of Duty","COD:");
            }
            getTitleId(gameName,game);
        }
    }

    private void getTitleId(String gameName,Game mGame) {
        XboxUnityProvider.getService().getGames(gameName.trim().replace(" ","+")).enqueue(new Callback<GameListResponse>() {
            @Override
            public void onResponse(Call<GameListResponse> call, Response<GameListResponse> response) {
                if (response.body().getItems() != null){
                    for (Item item : response.body().getItems()){
                        if (item.getName() != null && !item.getName().equalsIgnoreCase("Bundle")){
                            getCover(mGame,item);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<GameListResponse> call, Throwable t) {}
        });
    }

    private void getCover(Game mGame,Item item) {
        XboxUnityProvider.getService().getCoverInfo(item.getTitleID(),"0","0","0").enqueue(new Callback<GamePhotoIdResponse>() {
            @Override
            public void onResponse(Call<GamePhotoIdResponse> call, Response<GamePhotoIdResponse> response) {
                if (response.body().getCovers().size() != 0){
                    mGame.setCoverUrl("http://xboxunity.net/Resources/Lib/Cover.php?size=front&cid="+response.body().getCovers().get(0).getCoverID());
                    mGame.setTitleID(item.getTitleID());
                    Picasso.with(activity == null ? mContext : activity)
                            .load("http://xboxunity.net/Resources/Lib/Cover.php?size=front&cid="+response.body().getCovers().get(0).getCoverID())
                            .fetch(new com.squareup.picasso.Callback() {
                                @Override
                                public void onSuccess() {
                                    if (games.get(games.size()-1) == mGame){
                                        notifyGameFragment();
                                    }
                                }

                                @Override
                                public void onError() {

                                }
                            });
                }else{
                    if (games.get(games.size()) == mGame){
                        notifyGameFragment();
                    }
                }
            }

            @Override
            public void onFailure(Call<GamePhotoIdResponse> call, Throwable t) {}
        });
    }

    private void notifyGameFragment() {
        if (activity != null){
            for (Fragment fragment : activity.getSupportFragmentManager().getFragments()){
                if (fragment != null && fragment instanceof FragmentGames){
                    ((FragmentGames)fragment).gameCoverFlowAdapter = new GameCoverFlowAdapter(activity.getApplicationContext(),games);
                    ((FragmentGames)fragment).gameCoverFlowAdapter.notifyDataSetChanged();
                    Log.d("GAMESCANNER","NOTIFIED FRAGMENTGAMES");
                }
            }
        }
    }
}

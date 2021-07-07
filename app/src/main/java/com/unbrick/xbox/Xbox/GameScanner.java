package com.unbrick.xbox.Xbox;

import android.content.Context;

import androidx.fragment.app.Fragment;

import android.util.Log;

import com.squareup.picasso.Picasso;
import com.tramsun.libs.prefcompat.Pref;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.unbrick.xbox.Activities.MainActivity;
import com.unbrick.xbox.Adapter.GameCoverFlowAdapter;
import com.unbrick.xbox.Fragments.FragmentGames;
import com.unbrick.xbox.Helper.DrawerCreator;
import com.unbrick.xbox.XboxUnityApi.Responses.GameInfoResponse.GamePhotoIdResponse;
import com.unbrick.xbox.XboxUnityApi.Responses.GameListResponse.GameListResponse;
import com.unbrick.xbox.XboxUnityApi.Responses.GameListResponse.Item;
import com.unbrick.xbox.XboxUnityApi.XboxUnityProvider;

/**
 * Created by Admin on 08.12.2016.
 */

public class GameScanner {

    private static ArrayList<Game> games = new ArrayList<>();
    private XboxSocket socket;
    private String drive;
    private String basePath;
    private static OnGameFoundListener mOnGameFoundListener;

    public GameScanner() {
        this.drive = Pref.getString("gamedrive");
        this.basePath = Pref.getString("gamedir");
        this.socket = XboxSocket.getInstance();
    }

    public static ArrayList<Game> getGames() {
        return games;
    }

    public interface OnGameFoundListener {
        void gameFound(Game mGame);
    }

    public static void addListener(OnGameFoundListener mOnGameFoundListener) {
        GameScanner.mOnGameFoundListener = mOnGameFoundListener;
    }

    public void baseSearch() {
        games.clear();
        new Thread(() -> socket.xbdm.dirList(lines -> {
            lines.remove(0); // 201 - connected
            lines.remove(0); // 200- multiline response
            lines.remove(lines.size() - 1); // "."

            for (String directory : lines) {
                Log.d("GAMESCANNER", directory);

                String baseDirectory = directory.substring(directory.indexOf("name=\"") + 6, directory.indexOf("\" sizehi="));
                String path = drive + basePath + baseDirectory + "\\";
                verify(path, baseDirectory);
            }
        }, drive, basePath)).start();
    }

    private void verify(String directory, String gameName) {
        String defaultXex = directory + "default.xex";
        String defaultMpXex = directory + "default_mp.xex";
        AtomicReference<Game> mGame = new AtomicReference<>();
        socket.xbdm.getFileInfo(defaultXex, o -> {
            if (((String)o).equals("402- file not found")) return;
            mGame.set(new Game(directory, gameName));
            mGame.get().setDefault_xex(true);

            socket.xbdm.getFileInfo(defaultMpXex, o2 -> {
                if (!((String)o2).equals("402- file not found")) {
                    mGame.get().setDefault_mp_xex(true);
                }

                games.add(mGame.get());
                DrawerCreator.enableGameEntry();
                if (mOnGameFoundListener != null)
                    mOnGameFoundListener.gameFound(mGame.get());
                fetchTitles(mGame.get());
            });
        });
    }

    private void fetchTitles(Game mGame) {
        String gameName = mGame.getName();
        if (gameName.contains(" SP") || gameName.contains(" MP")) {
            gameName = gameName.replace(" SP", "").replace(" MP", "");
        }
        if (gameName.contains("COD ")) {
            gameName = gameName.replace("COD ", "COD: ");
        }
        if (gameName.contains("Sequel")) {
            gameName = gameName.replace("Borderlands", "Borderlands:");
        }
        if (gameName.equalsIgnoreCase("Minecraft")) {
            gameName = "Minecraft: Xbox 360 Edition";
        }
        if (gameName.contains("Call of Duty")) {
            gameName = gameName.replace("Call of Duty", "COD:");
        }
        getTitleId(gameName, mGame);
    }

    private void getTitleId(String gameName, Game mGame) {
        XboxUnityProvider.getService().getGames(gameName.trim().replace(" ", "+"), 0).enqueue(new Callback<GameListResponse>() {
            @Override
            public void onResponse(Call<GameListResponse> call, Response<GameListResponse> response) {
                assert response.body() != null;
                if (response.body().getItems() != null) {
                    for (Item item : response.body().getItems()) {
                        if (item.getName() != null && !item.getName().equalsIgnoreCase("Bundle")) {
                            getCover(mGame, item);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<GameListResponse> call, Throwable t) {
            }
        });
    }

    private void getCover(Game mGame, Item item) {
        XboxUnityProvider.getService().getCoverInfo(item.getTitleID(), "0", "0", "0").enqueue(new Callback<GamePhotoIdResponse>() {
            @Override
            public void onResponse(Call<GamePhotoIdResponse> call, Response<GamePhotoIdResponse> response) {
                assert response.body() != null;
                if (response.body().getCovers().size() != 0) {
                    mGame.setCoverUrl("http://xboxunity.net/Resources/Lib/Cover.php?size=front&cid=" + response.body().getCovers().get(0).getCoverID());
                    mGame.setTitleID(item.getTitleID());
                    if (mOnGameFoundListener != null)
                        mOnGameFoundListener.gameFound(mGame);
                }
            }

            @Override
            public void onFailure(Call<GamePhotoIdResponse> call, Throwable t) {
            }
        });
    }
}

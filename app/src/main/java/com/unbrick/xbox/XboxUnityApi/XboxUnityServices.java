package com.unbrick.xbox.XboxUnityApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import com.unbrick.xbox.XboxUnityApi.Responses.GameInfoResponse.GamePhotoIdResponse;
import com.unbrick.xbox.XboxUnityApi.Responses.GameListResponse.GameListResponse;

/**
 * Created by Admin on 10.12.2016.
 */

public interface XboxUnityServices {
//    http://xboxunity.net/Resources/Lib/TitleList.php?search=Black+Ops+II
    @GET("TitleList.php")
    Call<GameListResponse> getGames(@Query(value ="search",encoded = true) String gamename,
                                    @Query(value= "category", encoded = true) int category);

    @GET("CoverInfo.php")
    Call<GamePhotoIdResponse> getCoverInfo(@Query(value = "titleid",encoded = true) String titleid,
                                           @Query(value = "sort",encoded = true) String null0,
                                           @Query(value = "direction",encoded = true) String null1,
                                           @Query(value = "catrgory",encoded = true) String null2
    );
}

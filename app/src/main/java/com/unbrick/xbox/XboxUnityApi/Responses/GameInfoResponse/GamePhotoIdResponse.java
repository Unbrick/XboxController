package com.unbrick.xbox.XboxUnityApi.Responses.GameInfoResponse;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GamePhotoIdResponse {

    @SerializedName("Covers")
    @Expose
    private List<Cover> covers = null;
    @SerializedName("CoversCount")
    @Expose
    private Integer coversCount;

    /**
     * 
     * @return
     *     The covers
     */
    public List<Cover> getCovers() {
        return covers;
    }

    /**
     * 
     * @param covers
     *     The Covers
     */
    public void setCovers(List<Cover> covers) {
        this.covers = covers;
    }

    /**
     * 
     * @return
     *     The coversCount
     */
    public Integer getCoversCount() {
        return coversCount;
    }

    /**
     * 
     * @param coversCount
     *     The CoversCount
     */
    public void setCoversCount(Integer coversCount) {
        this.coversCount = coversCount;
    }

}

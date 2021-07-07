
package com.unbrick.xbox.XboxUnityApi.Responses.GameListResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("TitleID")
    @Expose
    private String titleID;
    @SerializedName("HBTitleID")
    @Expose
    private String hBTitleID;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("LinkEnabled")
    @Expose
    private String linkEnabled;
    @SerializedName("TitleType")
    @Expose
    private String titleType;
    @SerializedName("Covers")
    @Expose
    private String covers;
    @SerializedName("Updates")
    @Expose
    private String updates;
    @SerializedName("MediaIDCount")
    @Expose
    private String mediaIDCount;
    @SerializedName("UserCount")
    @Expose
    private String userCount;
    @SerializedName("NewestContent")
    @Expose
    private String newestContent;

    /**
     * 
     * @return
     *     The titleID
     */
    public String getTitleID() {
        return titleID;
    }

    /**
     * 
     * @param titleID
     *     The TitleID
     */
    public void setTitleID(String titleID) {
        this.titleID = titleID;
    }

    /**
     * 
     * @return
     *     The hBTitleID
     */
    public String getHBTitleID() {
        return hBTitleID;
    }

    /**
     * 
     * @param hBTitleID
     *     The HBTitleID
     */
    public void setHBTitleID(String hBTitleID) {
        this.hBTitleID = hBTitleID;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The linkEnabled
     */
    public String getLinkEnabled() {
        return linkEnabled;
    }

    /**
     * 
     * @param linkEnabled
     *     The LinkEnabled
     */
    public void setLinkEnabled(String linkEnabled) {
        this.linkEnabled = linkEnabled;
    }

    /**
     * 
     * @return
     *     The titleType
     */
    public String getTitleType() {
        return titleType;
    }

    /**
     * 
     * @param titleType
     *     The TitleType
     */
    public void setTitleType(String titleType) {
        this.titleType = titleType;
    }

    /**
     * 
     * @return
     *     The covers
     */
    public String getCovers() {
        return covers;
    }

    /**
     * 
     * @param covers
     *     The Covers
     */
    public void setCovers(String covers) {
        this.covers = covers;
    }

    /**
     * 
     * @return
     *     The updates
     */
    public String getUpdates() {
        return updates;
    }

    /**
     * 
     * @param updates
     *     The Updates
     */
    public void setUpdates(String updates) {
        this.updates = updates;
    }

    /**
     * 
     * @return
     *     The mediaIDCount
     */
    public String getMediaIDCount() {
        return mediaIDCount;
    }

    /**
     * 
     * @param mediaIDCount
     *     The MediaIDCount
     */
    public void setMediaIDCount(String mediaIDCount) {
        this.mediaIDCount = mediaIDCount;
    }

    /**
     * 
     * @return
     *     The userCount
     */
    public String getUserCount() {
        return userCount;
    }

    /**
     * 
     * @param userCount
     *     The UserCount
     */
    public void setUserCount(String userCount) {
        this.userCount = userCount;
    }

    /**
     * 
     * @return
     *     The newestContent
     */
    public String getNewestContent() {
        return newestContent;
    }

    /**
     * 
     * @param newestContent
     *     The NewestContent
     */
    public void setNewestContent(String newestContent) {
        this.newestContent = newestContent;
    }

}

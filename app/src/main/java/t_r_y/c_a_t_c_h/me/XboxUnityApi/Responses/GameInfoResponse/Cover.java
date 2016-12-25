package t_r_y.c_a_t_c_h.me.XboxUnityApi.Responses.GameInfoResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cover {

    @SerializedName("CoverID")
    @Expose
    private String coverID;
    @SerializedName("Rating")
    @Expose
    private Object rating;
    @SerializedName("Official")
    @Expose
    private String official;
    @SerializedName("Username")
    @Expose
    private String username;
    @SerializedName("FileSize")
    @Expose
    private String fileSize;
    @SerializedName("UploadedDate")
    @Expose
    private String uploadedDate;
    @SerializedName("NoRate")
    @Expose
    private Boolean noRate;

    /**
     * 
     * @return
     *     The coverID
     */
    public String getCoverID() {
        return coverID;
    }

    /**
     * 
     * @param coverID
     *     The CoverID
     */
    public void setCoverID(String coverID) {
        this.coverID = coverID;
    }

    /**
     * 
     * @return
     *     The rating
     */
    public Object getRating() {
        return rating;
    }

    /**
     * 
     * @param rating
     *     The Rating
     */
    public void setRating(Object rating) {
        this.rating = rating;
    }

    /**
     * 
     * @return
     *     The official
     */
    public String getOfficial() {
        return official;
    }

    /**
     * 
     * @param official
     *     The Official
     */
    public void setOfficial(String official) {
        this.official = official;
    }

    /**
     * 
     * @return
     *     The username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 
     * @param username
     *     The Username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 
     * @return
     *     The fileSize
     */
    public String getFileSize() {
        return fileSize;
    }

    /**
     * 
     * @param fileSize
     *     The FileSize
     */
    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * 
     * @return
     *     The uploadedDate
     */
    public String getUploadedDate() {
        return uploadedDate;
    }

    /**
     * 
     * @param uploadedDate
     *     The UploadedDate
     */
    public void setUploadedDate(String uploadedDate) {
        this.uploadedDate = uploadedDate;
    }

    /**
     * 
     * @return
     *     The noRate
     */
    public Boolean getNoRate() {
        return noRate;
    }

    /**
     * 
     * @param noRate
     *     The NoRate
     */
    public void setNoRate(Boolean noRate) {
        this.noRate = noRate;
    }

}

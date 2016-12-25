package t_r_y.c_a_t_c_h.me.Xbox;

/**
 * Created by Admin on 08.12.2016.
 */

public class Game {

    private String dirpath;
    private boolean default_xex;
    private boolean default_mp_xex;
    private String coverUrl;
    private String titleID;
    private String name;

    public Game(String dirpath, String name) {
        this.dirpath = dirpath;
        this.name = name;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getTitleID() {
        return titleID;
    }

    public void setTitleID(String titleID) {
        this.titleID = titleID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirpath() {
        return dirpath;
    }

    public void setDirpath(String dirpath) {
        this.dirpath = dirpath;
    }

    public boolean isDefault_mp_xex() {
        return default_mp_xex;
    }

    public void setDefault_mp_xex(boolean default_mp_xex) {
        this.default_mp_xex = default_mp_xex;
    }

    public boolean isDefault_xex() {
        return default_xex;
    }

    public void setDefault_xex(boolean default_xex) {
        this.default_xex = default_xex;
    }
}

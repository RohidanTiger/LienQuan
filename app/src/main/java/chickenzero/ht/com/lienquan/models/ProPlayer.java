package chickenzero.ht.com.lienquan.models;

import java.io.Serializable;

/**
 * Created by QuyDV on 5/10/17.
 */

public class ProPlayer implements Serializable{
    private String id;
    private String name;
    private String youtube_chanel;
    private String youtube_list;
    private String profile_image;

    public ProPlayer() {
    }

    public ProPlayer(String id, String name, String youtube_chanel, String youtube_list, String profile) {
        this.id = id;
        this.name = name;
        this.youtube_chanel = youtube_chanel;
        this.youtube_list = youtube_list;
        this.profile_image = profile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYoutube_chanel() {
        return youtube_chanel;
    }

    public void setYoutube_chanel(String youtube_chanel) {
        this.youtube_chanel = youtube_chanel;
    }

    public String getYoutube_list() {
        return youtube_list;
    }

    public void setYoutube_list(String youtube_list) {
        this.youtube_list = youtube_list;
    }

    public String getProfile() {
        return profile_image;
    }

    public void setProfile(String profile) {
        this.profile_image = profile;
    }
}

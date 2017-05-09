package chickenzero.ht.com.lienquan.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by QuyDV on 5/8/17.
 */

public class LeagueItem {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("youtube")
    private String youtube;

    public LeagueItem(String id, String name, String youtube) {
        this.id = id;
        this.name = name;
        this.youtube = youtube;
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

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

}

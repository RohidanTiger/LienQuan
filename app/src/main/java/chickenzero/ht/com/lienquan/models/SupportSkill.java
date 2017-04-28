package chickenzero.ht.com.lienquan.models;

/**
 * Created by QuyDV on 4/28/17.
 */

public class SupportSkill {
    private String id;
    private String name;
    private String description;
    private String youtube;

    public SupportSkill() {
    }

    public SupportSkill(String id, String name, String description, String youtube) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }
}

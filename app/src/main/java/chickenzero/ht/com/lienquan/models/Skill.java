package chickenzero.ht.com.lienquan.models;

/**
 * Created by QuyDV on 4/12/17.
 */

public class Skill {
    private String id;
    private String name;
    private String desc;
    private String mana;
    private String youtube;

    public Skill() {
    }

    public Skill(String id, String name, String desc, String mana, String youtube) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.mana = mana;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getMana() {
        return mana;
    }

    public void setMana(String mana) {
        this.mana = mana;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }
}

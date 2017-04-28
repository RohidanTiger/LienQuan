package chickenzero.ht.com.lienquan.models;

import io.realm.RealmObject;

/**
 * Created by QuyDV on 4/11/17.
 */

public class Item extends RealmObject{
    private int id;
    private String name;
    private String desc;
    private int level;
    private int type;

    public Item() {
    }

    public Item(int id, String name, String desc, int level, int type) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.level = level;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

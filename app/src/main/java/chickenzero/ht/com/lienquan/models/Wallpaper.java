package chickenzero.ht.com.lienquan.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by quydv on 2/24/18.
 */

public class Wallpaper implements Serializable {
    private String name;

    private ArrayList<String> urls;

    public Wallpaper() {
    }

    public Wallpaper(String name, ArrayList<String> urls) {
        this.name = name;
        this.urls = urls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getUrls() {
        return urls;
    }

    public void setUrls(ArrayList<String> urls) {
        this.urls = urls;
    }
}


package chickenzero.ht.com.lienquan.models;

/**
 * Created by QuyDV on 5/22/17.
 */

public class FreeHero {
    private String icon_hero;
    private String content_hero;

    public FreeHero() {
    }

    public FreeHero(String icon_hero, String content_hero) {
        this.icon_hero = icon_hero;
        this.content_hero = content_hero;
    }

    public String getIcon_hero() {
        return icon_hero;
    }

    public void setIcon_hero(String icon_hero) {
        this.icon_hero = icon_hero;
    }

    public String getContent_hero() {
        return content_hero;
    }

    public void setContent_hero(String content_hero) {
        this.content_hero = content_hero;
    }
}

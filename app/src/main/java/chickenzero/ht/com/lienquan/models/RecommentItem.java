package chickenzero.ht.com.lienquan.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by quydv on 9/4/18.
 */

public class RecommentItem extends RealmObject {
    private String id;
    private String title1;
    private String title2;
    private String title3;
    private String listItem1;
    private String listItem2;
    private String listItem3;

    public RecommentItem(){
    }

    public RecommentItem(String id, String title1, String title2, String title3, String listItem1,
                         String listItem2, String listItem3) {
        this.id = id;
        this.title1 = title1;
        this.title2 = title2;
        this.title3 = title3;
        this.listItem1 = listItem1;
        this.listItem2 = listItem2;
        this.listItem3 = listItem3;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getTitle3() {
        return title3;
    }

    public void setTitle3(String title3) {
        this.title3 = title3;
    }

    public String getListItem1() {
        return listItem1;
    }

    public void setListItem1(String listItem1) {
        this.listItem1 = listItem1;
    }

    public String getListItem2() {
        return listItem2;
    }

    public void setListItem2(String listItem2) {
        this.listItem2 = listItem2;
    }

    public String getListItem3() {
        return listItem3;
    }

    public void setListItem3(String listItem3) {
        this.listItem3 = listItem3;
    }
}

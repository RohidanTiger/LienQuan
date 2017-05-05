package chickenzero.ht.com.lienquan.config;

/**
 * Created by android.dev on 5/3/15.
 */
public class Contants {


    public static String PREFERENCES_FILENAME = "SOS_PREF";
    public static String NETWORK_ERROR = "Lỗi kết nối tới server. Xin vui lòng thử lại";
    public static String DEVELOPER_KEY = "AIzaSyD-47Uhd_ssBQqjRe3jIhTjBY9MkPmAOfM";

    public static String NAME_IMAGE_CAPTURE = "tempPhoto";
    public static String NUMBER_FORMAT = "#,###,###,###";
    public static String NUMBER_FORMAT2 = "#,###,###,###";
    public static long ONE_MILLION = 1000000;


    public static final float SIZE_GRID_HOME = 6.0f;
    public static final int PAGE_SIZE = 10;

    public static final int HIDE_KEY_BOARD_TIME = 100;

    public static final int DELAY_HIDE_KEY_BOARD_TIME = 30;

    public static final int IMAGE_SIZE_AVATAR_MAX = 300;

    public static final int IMAGE_SIZE_SEARCH = 300;

    public static final int NUMBER_QUESTION = 30;
    public static final int NUMBER_QUESTION_MOTO = 20;

    public static final int EXAM_TIME = 20 * 60 * 1000;
    public static final int MOTO_EXAM_TIME = 15 * 60 * 1000;

    // TAB IDENTIFY CONFIG
    public static final String TAB_HOME = "TAB_HOME";
    // Image Size
    public static final int SIZE_SMALL = 512;
    public static final int SIZE_MEDIUM = 1024;
    public static final int SIZE_LARGE = 1280;

    public static final int NUMBER_SKILL = 4;

    public static String getRole(int r){
        String role = "";
        switch (r){
            case 1:{
                role = "Đấu Sĩ";
                break;
            }
            case 2:{
                role = "Pháp Sư";
                break;
            }
            case 3:{
                role = "Trợ Thủ";
                break;
            }
            case 4:{
                role = "Đỡ Đòn";
                break;
            }
            case 5:{
                role = "Sát Thủ";
                break;
            }
            case 6:{
                role = "Xạ Thủ";
                break;
            }
        }
        return role;
    }
}


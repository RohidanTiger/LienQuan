package chickenzero.ht.com.lienquan.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by QuyDV on 5/4/17.
 */

public class PrefConfig {
    public static final String PREFS_REMEMBER_MENU_POSITION = "__Remeber_Menu_Position";
    static final PrefConfig sInstance = new PrefConfig();
    private SecurePreferences mSecurePref;

    public static PrefConfig getInstance() {
        return sInstance;
    }

    private SecurePreferences getSharedPreferences(Context context) {
        if (mSecurePref == null) mSecurePref = new SecurePreferences(context);
        return mSecurePref;
    }

    public void setRememberMenuPosition(Context context, int position) {
        SharedPreferences prefs = getSharedPreferences(context);
        SharedPreferences.Editor edt = prefs.edit();
        edt.putInt(PREFS_REMEMBER_MENU_POSITION, position);
        edt.commit();
    }


    public int getRememberMenuPosition(Context context){
        SharedPreferences prefs = getSharedPreferences(context);
        return prefs.getInt(PREFS_REMEMBER_MENU_POSITION,0);
    }
}

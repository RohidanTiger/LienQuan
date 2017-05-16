package chickenzero.ht.com.lienquan;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.google.firebase.FirebaseApp;

import io.realm.Realm;


/**
 * Created by DANGLV on 31/05/2016.
 */
public class SCApplication extends MultiDexApplication {
    private static SCApplication mContext;

    public static final String TAG = SCApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        FirebaseApp.initializeApp(this);
    }

    public static SCApplication getContext(){
        return mContext;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        mContext = this;
        MultiDex.install(base);
    }
}

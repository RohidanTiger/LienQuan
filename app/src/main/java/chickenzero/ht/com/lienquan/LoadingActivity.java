package chickenzero.ht.com.lienquan;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

import chickenzero.ht.com.lienquan.models.Hero;
import chickenzero.ht.com.lienquan.models.HeroDetail;
import chickenzero.ht.com.lienquan.models.Item;
import chickenzero.ht.com.lienquan.utils.Logger;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by QuyDV on 4/14/17.
 */

public class LoadingActivity extends AppCompatActivity {
    public SCApplication mApplication;
    private Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = SCApplication.getContext();
        setContentView(R.layout.activity_loading);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        Realm.deleteRealm(realmConfiguration);
        realm = Realm.getInstance(realmConfiguration);
        loadAllHeroFromFile();
        loadAllItemFromFile();
        loadAllHeroDetailFromFile();
        Logger.d("LoadData","finish");

        // Start MainActivtiy
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoadingActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private void loadAllHeroFromFile(){
        // Use streams if you are worried about the size of the JSON whether it was persisted on disk
        // or received from the network.
        InputStream stream = null;
        try {
            stream = getAssets().open("LienQuan/Heroes.json");
            // Open a transaction to store items into the realm
            realm.beginTransaction();
            try {
                realm.createAllFromJson(Hero.class, stream);
                realm.commitTransaction();
            } catch (IOException e) {
                // Remember to cancel the transaction if anything goes wrong.
                realm.cancelTransaction();
            } finally {
                if (stream != null) {
                    stream.close();
                }
            }
        } catch (IOException e) {
            Logger.d("Error","file not found");
        }
    }

    private void loadAllItemFromFile(){
        InputStream stream = null;
        try {
            stream = getAssets().open("LienQuan/Items/items.json");
            // Open a transaction to store items into the realm
            realm.beginTransaction();
            try {
                realm.createAllFromJson(Item.class, stream);
                realm.commitTransaction();
            } catch (IOException e) {
                // Remember to cancel the transaction if anything goes wrong.
                realm.cancelTransaction();
            } finally {
                if (stream != null) {
                    stream.close();
                }
            }
        } catch (IOException e) {
            Logger.d("Error","file not found");
        }
    }

    private void loadAllHeroDetailFromFile(){
        InputStream stream = null;
        try {
            stream = getAssets().open("LienQuan/Heroes/Heroes.txt");
            // Open a transaction to store items into the realm
            realm.beginTransaction();
            try {
                realm.createAllFromJson(HeroDetail.class, stream);
                realm.commitTransaction();
            } catch (IOException e) {
                // Remember to cancel the transaction if anything goes wrong.
                realm.cancelTransaction();
            } finally {
                if (stream != null) {
                    stream.close();
                }
            }
        } catch (IOException e) {
            Logger.d("Error","file not found");
        }
    }
}

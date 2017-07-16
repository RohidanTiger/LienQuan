package chickenzero.ht.com.lienquan.service;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import chickenzero.ht.com.lienquan.MainActivity;
import chickenzero.ht.com.lienquan.models.FreeHero;

/**
 * Created by QuyDV on 5/22/17.
 */

public class FreeHeroRequest extends AsyncTaskLoader<List<FreeHero>> {
    private MainActivity mContext;
    private String mUrl;
    private List<FreeHero> listHero = new ArrayList<>();

    public FreeHeroRequest(MainActivity context, String url) {
        super(context);
        this.mContext = context;
        mUrl = url;
    }

    @Override
    public void onStartLoading() {
        if (takeContentChanged()) {
            forceLoad();
        }
    }

    @Override
    public List<FreeHero> loadInBackground() {
        Document doc = null;
        if(listHero != null) listHero.clear();

        try {
            doc = Jsoup.connect(mUrl).userAgent("Mozilla").ignoreContentType(true).timeout(10000).get();
            Log.i("DataData",doc.toString());
            Elements heroIcon = doc.getElementsByClass("tabs-champ").first().getElementsByAttribute("src");
            Elements heroContent = doc.getElementsByClass("cont-heros").first().getElementsByAttribute("srcset");

            for(int i = 0; i < heroIcon.size(); i++){
                Element element1 = heroIcon.get(i);
                Element element2 = heroContent.get(i*2);

                FreeHero freeHero = new FreeHero();
                freeHero.setIcon_hero(element1.attr("src"));
                freeHero.setContent_hero(element2.attr("srcset"));
                listHero.add(freeHero);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listHero;
    }

    @Override
    protected void onStopLoading() {
        mContext.hideLoading();
        super.onStopLoading();
    }
}

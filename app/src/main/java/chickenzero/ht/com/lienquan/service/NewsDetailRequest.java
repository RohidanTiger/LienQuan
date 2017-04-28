package chickenzero.ht.com.lienquan.service;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by QuyDV on 4/24/17.
 */

public class NewsDetailRequest extends AsyncTaskLoader<String> {
    private Context mContext;
    private String mUrl;

    public NewsDetailRequest(Context context, String url) {
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
    public String loadInBackground() {
        Document doc = null;

        try {
            doc = Jsoup.connect(mUrl).userAgent("Mozilla").ignoreContentType(true).timeout(10000).get();
            Elements content = doc.getElementsByClass("bx-detail");
            return content.outerHtml();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}

package chickenzero.ht.com.lienquan.views.fragments;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.webkit.WebView;

import butterknife.BindView;
import chickenzero.ht.com.lienquan.BaseFragment;
import chickenzero.ht.com.lienquan.R;
import chickenzero.ht.com.lienquan.service.NewsDetailRequest;

/**
 * Created by QuyDV on 4/24/17.
 */

public class NewsDetailFragment extends BaseFragment {
    public static String URL_DETAIL = "url-detail";
    public static String URL_POSITION = "url-position";
    @BindView(R.id.webview_detail)
    WebView webViewDetail;

    private String url;
    private int position;
    String str1 = "<html><head><style>.title{color:red;} .type{color:#038000;}a{color:#285afd; text-decoration:" +
            "none;pointer-events: none;cursor: default;}</style></head><body>";
    private String str2 = "</body></html>";
    private LoaderManager.LoaderCallbacks<String> newsListener = new LoaderManager.LoaderCallbacks<String>(){

        @Override
        public Loader<String> onCreateLoader(int id, Bundle args) {
            return new NewsDetailRequest(getContext(),url);
        }

        @Override
        public void onLoadFinished(Loader<String> loader, String data) {
            String str = str1.concat(data).concat(str2);
            webViewDetail.loadDataWithBaseURL(null, str, "text/html", "utf-8", null);
        }

        @Override
        public void onLoaderReset(Loader<String> loader) {

        }
    };

    @Override
    protected int getViewContent() {
        return R.layout.fragment_news_detail;
    }

    @Override
    protected void initUI() {
        url = getArguments().getString(URL_DETAIL);
        position = getArguments().getInt(URL_POSITION);
        context.getSupportLoaderManager().initLoader(position, null, newsListener).forceLoad();
    }
}

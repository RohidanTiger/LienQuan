package chickenzero.ht.com.lienquan.views.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Method;

import butterknife.BindView;
import chickenzero.ht.com.lienquan.BaseDialog;
import chickenzero.ht.com.lienquan.R;
import chickenzero.ht.com.lienquan.models.SupportSkill;

/**
 * Created by QuyDV on 4/28/17.
 */

public class VideoPreviewDialog extends BaseDialog {

    @BindView(R.id.txt_title)
    TextView txtTitle;

    @BindView(R.id.txt_content)
    TextView txtContent;

    @BindView(R.id.webview_video)
    WebView webViewVideo;

    private SupportSkill skill;

    public VideoPreviewDialog(Context context, SupportSkill id) {
        super(context);
        this.skill = id;
    }

    @Override
    protected int getViewContent() {
        return R.layout.dialog_play_video;
    }

    @Override
    protected void initUI() {
        txtTitle.setText(skill.getName());
        txtContent.setText(skill.getDescription());

        int weith = mActivity.getWindow().getDecorView().getWidth();
        String frameVideo = "<html><body>Video From YouTube<br><iframe width=\"360\" height=\"220\" " +
                "src=\"https://www.youtube.com/embed/"+skill.getYoutube()+"\" frameborder=\"0\" allowfullscreen></iframe></body></html>";

        webViewVideo.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        WebSettings webSettings = webViewVideo.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webViewVideo.loadData(frameVideo, "text/html", "utf-8");
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        try {
            Method method = android.webkit.WebView.class.getMethod("destroy");
            method.invoke(webViewVideo);
        } catch (Exception e) {
        }
    }



    @Override
    protected void onStop() {
        webViewVideo.onPause();
        super.onStop();
    }

    public void onPause(){
        try {
            Method method = WebView.class.getMethod("onPause");
            method.invoke(webViewVideo);
        } catch (Exception e) {
        }
    }

    public void onResume() {
        webViewVideo.onResume();
    }
}

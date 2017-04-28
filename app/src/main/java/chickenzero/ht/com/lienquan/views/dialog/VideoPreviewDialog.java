package chickenzero.ht.com.lienquan.views.dialog;

import android.content.Context;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

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
        webViewVideo.getSettings().setJavaScriptEnabled(true);
        webViewVideo.getSettings().setPluginState(WebSettings.PluginState.ON);
        webViewVideo.loadUrl("http://www.youtube.com/embed/" + skill.getYoutube() + "?autoplay=0&vq=small");
        webViewVideo.setWebChromeClient(new WebChromeClient());
    }
}

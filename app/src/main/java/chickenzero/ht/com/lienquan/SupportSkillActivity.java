package chickenzero.ht.com.lienquan;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import chickenzero.ht.com.lienquan.config.Contants;
import chickenzero.ht.com.lienquan.models.SupportSkill;

/**
 * Created by QuyDV on 5/17/17.
 */

public class SupportSkillActivity extends YouTubeBaseActivity {
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    public static String SUPPORT_SKILL = "support-skill";
    private SupportSkill mSupportSkill;

    // YouTube player view
    private YouTubePlayerView youTubeView;

    private TextView mTextViewTitle;
    private TextView mTextViewContent;

    YouTubePlayer.OnInitializedListener listener = new YouTubePlayer.OnInitializedListener() {
        @Override
        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
            youTubePlayer.loadVideo(mSupportSkill.getYoutube());
            youTubePlayer.setFullscreen(false);
            youTubePlayer.pause();
        }

        @Override
        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
            if (youTubeInitializationResult.isUserRecoverableError()) {
                youTubeInitializationResult.getErrorDialog(SupportSkillActivity.this, RECOVERY_DIALOG_REQUEST).show();
            } else {
                String errorMessage = String.format(getString(R.string.app_name), youTubeInitializationResult.toString());
                Toast.makeText(SupportSkillActivity.this, errorMessage, Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_support_skill);

        youTubeView = (YouTubePlayerView) findViewById(R.id.video);
        mTextViewTitle = (TextView) findViewById(R.id.txt_title) ;
        mTextViewContent = (TextView) findViewById(R.id.txt_content);
        mSupportSkill = (SupportSkill) getIntent().getSerializableExtra(SUPPORT_SKILL);

        mTextViewTitle.setText(mSupportSkill.getName());
        mTextViewContent.setText(mSupportSkill.getDescription());

        // Initializing video player with developer key
        youTubeView.initialize(Contants.DEVELOPER_KEY, listener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Contants.DEVELOPER_KEY, listener);
        }
    }

    private YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtube_view);
    }
}

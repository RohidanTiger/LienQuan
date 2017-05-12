package chickenzero.ht.com.lienquan;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import chickenzero.ht.com.lienquan.config.Contants;

/**
 * Created by QuyDV on 4/20/17.
 */

public class YoutubePlayerActivity extends YouTubeBaseActivity /*implements
        YouTubePlayer.OnInitializedListener*/{
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    public static String YOUTUBE_ID = "youtube-id";
    private String videoID;

    // YouTube player view
    private YouTubePlayerView youTubeView;

    YouTubePlayer.OnInitializedListener listener = new YouTubePlayer.OnInitializedListener() {
        @Override
        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
            youTubePlayer.loadVideo(videoID);
            youTubePlayer.setFullscreen(true);
        }

        @Override
        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
            if (youTubeInitializationResult.isUserRecoverableError()) {
                youTubeInitializationResult.getErrorDialog(YoutubePlayerActivity.this, RECOVERY_DIALOG_REQUEST).show();
            } else {
                String errorMessage = String.format(getString(R.string.app_name), youTubeInitializationResult.toString());
                Toast.makeText(YoutubePlayerActivity.this, errorMessage, Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_youtube_player);

        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        videoID = getIntent().getStringExtra(YOUTUBE_ID);

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

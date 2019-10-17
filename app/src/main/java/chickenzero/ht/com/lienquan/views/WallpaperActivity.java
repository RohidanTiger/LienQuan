package chickenzero.ht.com.lienquan.views;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.github.chrisbanes.photoview.PhotoView;

import chickenzero.ht.com.lienquan.R;
import chickenzero.ht.com.lienquan.utils.Utils;

/**
 * Created by quydv on 2/25/18.
 */

public class WallpaperActivity extends AppCompatActivity {

    public static String url_image = "url_image";

    public static final String KEY_THUMBNAIL_INIT_TOP_POSITION = "KEY_THUMBNAIL_INIT_TOP_POSITION";
    public static final String KEY_THUMBNAIL_INIT_LEFT_POSITION = "KEY_THUMBNAIL_INIT_LEFT_POSITION";
    public static final String KEY_THUMBNAIL_INIT_WIDTH = "KEY_THUMBNAIL_INIT_WIDTH";
    public static final String KEY_THUMBNAIL_INIT_HEIGHT = "KEY_THUMBNAIL_INIT_HEIGHT";

    private ImageView mTransitionImage;

    private PhotoView mPhotoView;

    private String mUrlImage;

    private Button btnZoom;

    private View mainContainer;

    private Utils utils;

    private OnLoadFinishListener listener = new OnLoadFinishListener() {
        @Override
        public void onFinish() {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mPhotoView.setScale(3.0f);
                }
            }, 1000);
        }
    };;


    @Override
    @SuppressWarnings("deprecation")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_image_slider);

        mPhotoView = (PhotoView) findViewById(R.id.image);

        mTransitionImage = (ImageView) findViewById(R.id.img_logo);

        btnZoom = (Button) findViewById(R.id.btn_zoom);

        mainContainer = findViewById(R.id.main_container);

        utils = new Utils(this);

        mUrlImage = getIntent().getExtras().getString(url_image);

//        if(savedInstanceState == null){
//            // We entered activity for the first time.
//            // Initialize Image view that will be transitioned
//            initializeTransitionView();
//        } else {
//            // Activity is retrieved. Main container is invisible. Make it visible
//            mainContainer.setAlpha(1.0f);
//        }
//
//        mEnterScreenAnimations = new EnterScreenAnimations(mTransitionImage, mPhotoView, mainContainer);
//        mExitScreenAnimations = new ExitScreenAnimations(mTransitionImage, mPhotoView, mainContainer);

//        initializeEnlargedImageAndRunAnimation(savedInstanceState);

        Glide.with(WallpaperActivity.this).load(mUrlImage).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model,
                                       Target<GlideDrawable> target, boolean isFirstResource) {
                mTransitionImage.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource,
                                           String model, Target<GlideDrawable> target,
                                           boolean isFromMemoryCache, boolean isFirstResource) {
                mTransitionImage.setVisibility(View.GONE);

                listener.onFinish();

                return false;
            }
        }).into(mPhotoView);

        btnZoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Bitmap bitmap = getCroppedBitmap(((GlideBitmapDrawable) mPhotoView.getDrawable()).getBitmap());
                    utils.setAsWallpaper(bitmap);
                }catch (Exception e){
                    Log.i("WallpaperActivity" , e.toString());
                }

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // prevent leaking activity if image was not loaded yet
        //Glide.with(this).pauseRequests();
    }

    private Bitmap getCroppedBitmap(Bitmap bitmapimg) {
        Matrix matrix = new Matrix();
        mPhotoView.getDisplayMatrix(matrix);

        float[] values = new float[9];
        matrix.getValues(values);

        float scale = mPhotoView.getScale();
        float transitionX = Math.abs(values[Matrix.MTRANS_X]);
        float transitionY = Math.abs(values[Matrix.MTRANS_Y]);

        int cropX = (int) (transitionX / scale);
        int cropY = (int) (transitionY / scale);
        int newWidth = (int) (mPhotoView.getWidth() / scale);
        int newHeight = (int) (mPhotoView.getHeight() / scale);

        return Bitmap.createBitmap(bitmapimg, cropX, 0, newWidth, bitmapimg.getHeight(), null, false);
    }

    public interface OnLoadFinishListener {
        void onFinish();
    }
}

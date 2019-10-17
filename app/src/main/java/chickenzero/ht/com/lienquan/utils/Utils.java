package chickenzero.ht.com.lienquan.utils;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

/**
 * Created by QuyDV on 6/3/17.
 */

public class Utils {
    private String TAG = Utils.class.getSimpleName();
    private Context _context;

    // constructor
    public Utils(Context context) {
        this._context = context;
    }

    /*
     * getting screen width
     */
    @SuppressWarnings("deprecation")
    public int getScreenWidth() {
        int columnWidth;
        WindowManager wm = (WindowManager) _context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        final Point point = new Point();
        try {
            display.getSize(point);
        } catch (NoSuchMethodError ignore) {
            // Older device
            point.x = display.getWidth();
            point.y = display.getHeight();
        }
        columnWidth = point.x;
        return columnWidth;
    }

    /*
 * getting screen heigh
 */
    @SuppressWarnings("deprecation")
    public int getScreenHeigh() {
        int columnHeigh;
        WindowManager wm = (WindowManager) _context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        final Point point = new Point();
        try {
            display.getSize(point);
        } catch (NoSuchMethodError ignore) {
            // Older device
            point.x = display.getWidth();
            point.y = display.getHeight();
        }
        columnHeigh = point.y;
        return columnHeigh;
    }


    public void saveImageToSDCard(Bitmap bitmap) {
        File myDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "wallpaper");

        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Wallpaper-" + n + ".jpg";
        File file = new File(myDir, fname);
        if (file.exists())
            file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            Log.d(TAG, "Wallpaper saved to: " + file.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
//            Toast.makeText(_context,
//                    "",
//                    Toast.LENGTH_SHORT).show();
        }
    }

    public void setAsWallpaper(Bitmap bitmap) {
        try {
            WallpaperManager wm = WallpaperManager.getInstance(_context);

            wm.setBitmap(bitmap);
            Toast.makeText(_context,
                    "Wallpapaer updated!",
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(_context,
                    "Wallpapaer updated fail!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
package worldline.ssm.rd.ux.wltwitter.async;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import worldline.ssm.rd.ux.wltwitter.helpers.TwitterHelper;
import worldline.ssm.rd.ux.wltwitter.utils.ImageMemoryCache;

/**
 * Created by Matthieu on 17/12/2015.
 */
public class RetrieveTwitterPic extends AsyncTask<String, Integer, Bitmap> {
    private ImageView mImageView;
    private ImageMemoryCache mMemoryCache;

    public RetrieveTwitterPic(ImageView mImageView, ImageMemoryCache memoryCache) {
        this.mImageView = mImageView;
        this.mMemoryCache = memoryCache;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        if(params.length > 0) {
            Bitmap bitmap = null;
            try {
                bitmap = TwitterHelper.getTwitterUserImage(params[0]);

                if(null != mMemoryCache) {
                    mMemoryCache.addBitmapToMemoryCache(params[0], bitmap);
                }
            } catch (Exception e) {
                Log.e("AsynncPic", e.getMessage());
            }
            return bitmap;
        } else {
            return null;
        }
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        mImageView.setImageBitmap(bitmap);
    }
}

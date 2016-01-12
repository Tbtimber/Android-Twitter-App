package worldline.ssm.rd.ux.wltwitter.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by Matthieu on 17/12/2015.
 */
public class ImageMemoryCache {
    private LruCache<String, Bitmap> mMemoryCache;

    public void addBitmapToMemoryCache(String imgUrlKey, Bitmap bitmap) {
        if(mMemoryCache.get(imgUrlKey) != null) {
            mMemoryCache.put(imgUrlKey, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String imgUrlKey) {
        Bitmap bitmap = mMemoryCache.get(imgUrlKey);
        if(bitmap == null) {
            return null;
        } else {
            return bitmap;
        }
    }

    public ImageMemoryCache(int maxCacheSize) {
        mMemoryCache = new LruCache<String, Bitmap>(maxCacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return (value.getRowBytes() * value.getHeight())/1024;
            }
        };
    }
}

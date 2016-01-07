package worldline.ssm.rd.ux.wltwitter.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Matthieu on 07/01/2016.
 */
public class WLTwitterDatabaseProvider extends ContentProvider {
    public SQLiteOpenHelper mDBHelper;
    public UriMatcher mUriMatcher;

    private static final int TWEET_CORRECT_URI_CODE = 42;


    @Override
    public boolean onCreate() {
        mDBHelper = new WLTwitterDataBaseHelper(getContext());
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(WLTwitterDatabaseContract.CONTENT_PROVIDER_TWEETS_AUTHORITY,
                WLTwitterDatabaseContract.TABLE_TWEETS, TWEET_CORRECT_URI_CODE);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        if(mUriMatcher.match(uri) == TWEET_CORRECT_URI_CODE) {
            return WLTwitterDatabaseContract.TWEETS_CONTENT_TYPE;
        }
        throw new IllegalArgumentException("Unknowm Uri: "+ uri);
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}

package worldline.ssm.rd.ux.wltwitter.database;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Matthieu on 07/01/2016.
 */
public class WLTwitterDatabaseContract implements BaseColumns {
    //Field name
    public static final String DATE_CREATED = "dateCreated";
    public static final String TEXT = "tweetText";
    public static final String USER_NAME = "userName";
    public static final String USER_ALIAS = "userAlias";
    public static final String USER_IMAGE_URL = "userImageUrl";
    public static final String DATE_CREATED_TIMESTAMP = "dateCreatedTimeStamp";

    //Table name
    public static final String TABLE_TWEETS = "tweets";

    //Table script creation
    private static final String TABLE_GENERIC_CREATE_SCRIPT_PREFIX = "CREATE TABLE IF NOT EXISTS ";
    private static final String TABLE_IMAGE_CREATE_SCRIPT_SUFFIX = "(" + _ID + " INTEGER PRIMARY KEY, " +
            DATE_CREATED + " TEXT NOT NULL, " +
            DATE_CREATED_TIMESTAMP + " INTEGER, " +
            TEXT + " TEXT NOT NULL, " +
            USER_NAME + " TEXT NOT NULL, " +
            USER_ALIAS + " TEXT NOT NULL, " +
            USER_IMAGE_URL + " TEXT NOT NULL)";

    public static final String TABLE_TWEET_CREATE_SCRIPT = TABLE_GENERIC_CREATE_SCRIPT_PREFIX + TABLE_TWEETS + TABLE_IMAGE_CREATE_SCRIPT_SUFFIX;


    public static final String[] PROJECTION_FULL = new String[]{
            _ID,
            DATE_CREATED,
            DATE_CREATED_TIMESTAMP,
            TEXT,
            USER_NAME,
            USER_ALIAS,
            USER_IMAGE_URL
    };
    public static final String SELECTION_BY_ID = _ID + "=?";
    public static final String SELECTION_BY_CREATION_DATE = DATE_CREATED + "=?";
    public static final String SELECTION_BY_CREATION_DATE_TIMESTAMP = DATE_CREATED_TIMESTAMP + "=?";
    public static final String SELECTION_BY_USER_NAME = USER_NAME + "=?";

    public static final String ORDER_BY_DATE_CREATED_TIMESTAMP_DESCENDING = DATE_CREATED_TIMESTAMP + " DESC";

    public static final String CONTENT_PROVIDER_TWEETS_AUTHORITY = "worldline.ssm.ux.rd.TweetAuthority";
    public static final Uri TWEETS_URI = Uri.parse("content://"+ CONTENT_PROVIDER_TWEETS_AUTHORITY + "/" + TABLE_TWEETS);
    public static final String TWEETS_CONTENT_TYPE = "vnd.android.cursor.dir/vnd.wltwitter.tweets";

}

package worldline.ssm.rd.ux.wltwitter.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import worldline.ssm.rd.ux.wltwitter.database.WLTwitterDatabaseContract;

/**
 * Created by Matthieu on 07/01/2016.
 */
public class WLTwitterDataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "tweets.db";
    private static final int DATABASE_VERSION = 1;

    public WLTwitterDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(WLTwitterDatabaseContract.TABLE_TWEET_CREATE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop the Current tweets table
        db.execSQL("DROP TABLE IF EXISTS " + WLTwitterDatabaseContract.TABLE_TWEETS);

        //Recreate the db
        onCreate(db);
    }
}

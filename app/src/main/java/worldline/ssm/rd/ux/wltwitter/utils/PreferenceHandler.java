package worldline.ssm.rd.ux.wltwitter.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.Context;
import worldline.ssm.rd.ux.wltwitter.R;
import worldline.ssm.rd.ux.wltwitter.WLTwitterApplication;

/**
 * Created by Matthieu on 07/12/2015.
 */
public class PreferenceHandler {
    private static final String PREFERENCE_FILE_KEY = "worldline.ssm.rd.ux.wltwitter.PREFERENCE_FILE_KEY";

    public static void addPref(String key, String value) {
        Context context = WLTwitterApplication.getContext();
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit(); //Get editor
        //Create/Change preferences parameter
        editor.putString(key, value);
        editor.commit(); //Apply changes to sharedpreference
    }
    public static String getPref(String key) {
        Context context = WLTwitterApplication.getContext();
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        return prefs.getString(key, null);
    }
    public static void clearPref() {
        Context context = WLTwitterApplication.getContext();
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit(); //Get editor
        //Create/Change preferences parameter
        editor.clear();
        editor.commit(); //Apply changes to sharedpreference
    }
}

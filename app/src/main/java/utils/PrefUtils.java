package utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 桑健 on 2016/6/10.
 */
public class PrefUtils {
    public static final String PREF_NAME = "config";

    public static boolean getBoolean(Context ctx, String key, boolean defaultValue) {
        SharedPreferences preferences = ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return  preferences.getBoolean(key,defaultValue);
    }

    public static void setBoolean(Context ctx, String key, boolean value) {
        SharedPreferences preferences = ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        preferences.edit().putBoolean(key, value).commit();
    }
}

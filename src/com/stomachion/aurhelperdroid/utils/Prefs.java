package com.stomachion.aurhelperdroid.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * User: Pedro Veloso
 */
public class Prefs {

    /**
     * @param ctx app context
     * @return True if this is the first time the app is initialized, False otherwise
     */
    public static boolean getIsFirstTime(Context ctx) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(ctx);
        return settings.getBoolean(Constants.PREF_FIRST_TIME, true);
    }

    public static void setIsFirstTime(Context ctx, boolean newValue) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(Constants.PREF_FIRST_TIME, newValue);
        editor.commit();
    }
}

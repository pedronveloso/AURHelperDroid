package com.stomachion.aurhelperdroid.utils;

import android.util.Log;

/**
 * User: Pedro Veloso
 */
public final class CommonUtils {

    /**
     * @param message Message to display
     * @param type    [Log.Error, Log.Warn, ...]
     */
    public static void debugFunc(String message, int type) {
        // errors must always be displayed
        if (type == Log.ERROR) {
            Log.e(Constants.LOG_TAG, message);
        } else if (Constants.DEBUG_ACTIVE) {
            switch (type) {
                case Log.DEBUG:
                    Log.d(Constants.LOG_TAG, message);
                    break;
                case Log.INFO:
                    Log.i(Constants.LOG_TAG, message);
                    break;
                case Log.VERBOSE:
                    Log.v(Constants.LOG_TAG, message);
                    break;
                case Log.WARN:
                    Log.w(Constants.LOG_TAG, message);
                    break;
                default:
                    Log.v(Constants.LOG_TAG, message);
                    break;
            }
        }
    }

}

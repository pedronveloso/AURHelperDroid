package com.stomachion.aurhelperdroid.utils;

/**
 * Constants - Provide constants used app-wise
 * User: Pedro Veloso
 */
public final class Constants {

    public static final String LOG_TAG = "AURHelperD";
    //switch to True during Development builds
    public static final boolean DEBUG_ACTIVE = true;

    //web urls
    public static final String URL_NEWS_FEED = "https://aur.archlinux.org/rss/";

    // return result types
    public static final int ALL_OK = 1;
    public static final int ERROR_NO_INTERNET = 2;
    public static final int ERROR_OTHER = 3;

    //Intent EXTRAS
    public static final String EXTRA_WEBPAGE_URL = "EXTRA_WEBPAGE_URL";
    public static final String EXTRA_TITLE = "EXTRA_TITLE";

    // Preferences
    public static final String PREF_FIRST_TIME = "FIRST_TIME";
}

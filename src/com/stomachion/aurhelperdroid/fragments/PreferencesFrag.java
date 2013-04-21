package com.stomachion.aurhelperdroid.fragments;

import android.app.Activity;
import com.stomachion.aurhelperdroid.activities.PreferencesActivity;
import com.stomachion.aurhelperdroid.fallbacksupport.ActivityHostFragment;

/**
 * User: Pedro Veloso
 * Preferences Screen. Will show independent Activity on pre-honeycomb devices
 */
public class PreferencesFrag extends ActivityHostFragment {

    @Override
    protected Class<? extends Activity> getActivityClass() {
        return PreferencesActivity.class;
    }
}
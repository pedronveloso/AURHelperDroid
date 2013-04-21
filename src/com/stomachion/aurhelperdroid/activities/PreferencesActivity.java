package com.stomachion.aurhelperdroid.activities;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import com.stomachion.aurhelperdroid.R;

/**
 * User: Pedro Veloso
 */
public class PreferencesActivity extends PreferenceActivity {

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }
}

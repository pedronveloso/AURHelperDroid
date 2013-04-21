package com.stomachion.aurhelperdroid.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.stomachion.aurhelperdroid.R;

/**
 * User: Pedro Veloso
 */
public class AboutFrag extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.about_screen, container, false);
        return v;
    }
}

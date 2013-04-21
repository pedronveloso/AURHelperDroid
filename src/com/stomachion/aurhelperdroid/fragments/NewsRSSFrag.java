package com.stomachion.aurhelperdroid.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.stomachion.aurhelperdroid.R;
import com.stomachion.aurhelperdroid.adapters.NewsAdapter;
import com.stomachion.aurhelperdroid.logic.NewsItem;
import com.stomachion.aurhelperdroid.network.InternetState;
import com.stomachion.aurhelperdroid.parser.MainRssParser;
import com.stomachion.aurhelperdroid.utils.CommonUtils;
import com.stomachion.aurhelperdroid.utils.Constants;

import java.util.ArrayList;

/**
 * User: Pedro Veloso
 */
public class NewsRSSFrag extends Fragment {

    private ArrayList<NewsItem> mNewsItems;
    private NewsAdapter mNewsAdapter;
    private View mParentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.news_screen, container, false);
        mParentView = v;
        new ProcessFeedFetchOnBackground().execute();
        return v;
    }


    /**
     * AsyncTask fetches the data and parses the data on a separate thread, not blocking the UI
     */
    private final class ProcessFeedFetchOnBackground extends AsyncTask<Void, Void, Integer> {

        protected Integer doInBackground(Void... ignored) {
            if (InternetState.isConnectedToInternet(getActivity())) {
                try {
                    mNewsItems = MainRssParser.parseURL(Constants.URL_NEWS_FEED);

                    return Constants.ALL_OK;
                } catch (Exception e) {
                    CommonUtils.debugFunc("Could not obtain feed from web. E.: " + e.getMessage(), Log.ERROR);
                    e.printStackTrace();
                    return Constants.ERROR_OTHER;
                }
            } else
                return Constants.ERROR_NO_INTERNET;
        }

        protected void onPostExecute(Integer result) {
            CommonUtils.debugFunc("AsyncTask result: " + result, Log.DEBUG);

            ListView listView = (ListView) mParentView.findViewById(R.id.lv_list);


            switch (result) {
                case Constants.ALL_OK:
                    drawList();
                    break;

                case Constants.ERROR_NO_INTERNET:
                    break;

                case Constants.ERROR_OTHER:
                    break;
            }
        }
    }

    /**
     * Draws the listview with news elements
     */
    private void drawList() {
        if (mNewsItems != null && mNewsItems.size() > 0) {
            ListView listView = (ListView) mParentView.findViewById(R.id.lv_list);
            // if there are more then 10 items enable Android's Fast Scroll functionality
            if (mNewsItems.size() > 10) {
                listView.setFastScrollEnabled(true);
            }

            mNewsAdapter = new NewsAdapter(getActivity(), mNewsItems);
            listView.setAdapter(mNewsAdapter);
        }
    }
}

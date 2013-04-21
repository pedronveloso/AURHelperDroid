package com.stomachion.aurhelperdroid.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.stomachion.aurhelperdroid.R;
import com.stomachion.aurhelperdroid.logic.NewsItem;
import com.stomachion.aurhelperdroid.utils.CommonUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * User: Pedro Veloso
 */
public final class NewsAdapter extends BaseAdapter {

    private final LayoutInflater mInflater;
    private ArrayList<NewsItem> mNewsItems = null;
    private final SimpleDateFormat mFormatter;


    @SuppressWarnings("unchecked")
    public NewsAdapter(Context context, ArrayList<NewsItem> newsItems) {
        this.mNewsItems = (ArrayList<NewsItem>) newsItems.clone();
        mInflater = LayoutInflater.from(context);
        //formatter set at class level because we will be reusing it a lot
        mFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    }

    public int getCount() {
        return mNewsItems.size();
    }

    public NewsItem getItem(int position) {
        return mNewsItems.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        NewsItemsViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.news_elem, null, false);
            holder = new NewsItemsViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.description = (TextView) convertView.findViewById(R.id.tv_description);
            holder.date = (TextView) convertView.findViewById(R.id.tv_date);
            holder.author = (TextView) convertView.findViewById(R.id.tv_author);
            convertView.setTag(holder);
        } else {
            //recycle existing views
            holder = (NewsItemsViewHolder) convertView.getTag();
        }

        holder.title.setText(mNewsItems.get(position).getTitle());
        holder.description.setText(mNewsItems.get(position).getDescription());
        CommonUtils.debugFunc("Author: " + mNewsItems.get(position).getAuthor(), Log.DEBUG);
        holder.author.setText(mNewsItems.get(position).getAuthor());
        holder.date.setText(formatDate(mNewsItems.get(position).getPublishDate()));

        return convertView;
    }


    /**
     * Formats date to “31/12/2012 12:55”
     *
     * @param date date to format
     * @return formatted date
     */
    private String formatDate(GregorianCalendar date) {
        if (date == null) {
            return "";
        }
        return mFormatter.format(date.getTime());
    }

    final class NewsItemsViewHolder {
        TextView title;
        TextView description;
        TextView date;
        TextView author;
    }
}

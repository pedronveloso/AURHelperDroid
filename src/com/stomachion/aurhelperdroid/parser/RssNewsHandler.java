package com.stomachion.aurhelperdroid.parser;

import android.util.Log;
import com.stomachion.aurhelperdroid.logic.NewsItem;
import com.stomachion.aurhelperdroid.utils.CommonUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * User: Pedro Veloso
 */
public final class RssNewsHandler extends DefaultHandler {

    private StringBuilder mStrBuilder;

    // fill this list while parsing
    private ArrayList<NewsItem> newsItems;

    private NewsItem currentItem;

    // Parsing intermediary state flags
    private boolean parsingTitle;
    private boolean parsingURL;
    private boolean parsingPublishDate;
    private boolean parsingDescription;
    private boolean parsingAuthor;

    public RssNewsHandler() {
        newsItems = new ArrayList<NewsItem>();
    }

    public ArrayList<NewsItem> getItems() {
        return newsItems;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("item".equals(qName)) {
            currentItem = new NewsItem();
        } else if ("title".equals(qName)) {
            mStrBuilder = new StringBuilder("");
            parsingTitle = true;
        } else if ("link".equals(qName)) {
            parsingURL = true;
        } else if ("description".equals(qName)) {
            mStrBuilder = new StringBuilder("");
            parsingDescription = true;
        } else if ("author".equals(qName)) {
            mStrBuilder = new StringBuilder("");
            parsingAuthor = true;
        } else if ("pubDate".equals(qName)) {
            parsingPublishDate = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("item".equals(qName)) {
            newsItems.add(currentItem);
            currentItem = null;
        } else if ("title".equals(qName)) {
            //should check this because there is also the title outside the Item
            if (currentItem != null) {
                currentItem.setTitle(mStrBuilder.toString());
            }
            parsingTitle = false;

        } else if ("link".equals(qName)) {
            parsingURL = false;
        } else if ("description".equals(qName)) {
            if (currentItem != null) {
                currentItem.setDescription(mStrBuilder.toString());
            }
            parsingDescription = false;
        } else if ("author".equals(qName)) {
            parsingAuthor = false;
        } else if ("pubDate".equals(qName)) {
            parsingPublishDate = false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // if is not currentItem it is because we caught an element that validated outside of the <item>, so ignore it
        if (currentItem != null) {
            if (parsingTitle) {

                mStrBuilder.append(new String(ch, start, length));
            } else if (parsingURL) {
                currentItem.setUrl(new String(ch, start, length));
                parsingURL = false;
            } else if (parsingDescription) {
                mStrBuilder.append(new String(ch, start, length));
            } else if (parsingPublishDate) {
                parseFormattedDate(new String(ch, start, length));
                parsingPublishDate = false;
            } else if (parsingAuthor) {
                currentItem.setAuthor(new String(ch, start, length));
                parsingAuthor = false;
            }
        }
    }

    /**
     * Treats and adds the publish date on RSS item
     *
     * @param formattedDate date formatted like this : "Sun, 21 Apr 2013 16:05:44 +0100"
     */
    private void parseFormattedDate(String formattedDate) {
        formattedDate = formattedDate.substring(5, 22);
        Date dateAux;
        // use Locale.US because months are named in their format
        DateFormat formatterDate = new SimpleDateFormat("dd MMM yyyy HH:mm", Locale.US);
        try {
            dateAux = formatterDate.parse(formattedDate);
            GregorianCalendar tmpDate = new GregorianCalendar();
            tmpDate.setTimeInMillis(dateAux.getTime());
            currentItem.setPublishDate(tmpDate);
        } catch (ParseException e) {
            CommonUtils.debugFunc("Failed to parse date. E.: " + e.getMessage(), Log.ERROR);
        }
    }
}

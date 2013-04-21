package com.stomachion.aurhelperdroid.parser;

import com.stomachion.aurhelperdroid.logic.NewsItem;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;

/**
 * User: Pedro Veloso
 */
public final class MainRssParser {

    public static ArrayList<NewsItem> parseURL(String urlToParse) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        // SAXparser handler
        RssNewsHandler handler = new RssNewsHandler();
        saxParser.parse(urlToParse, handler);
        return handler.getItems();
    }
}

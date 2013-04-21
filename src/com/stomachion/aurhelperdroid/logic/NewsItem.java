package com.stomachion.aurhelperdroid.logic;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * User: Pedro Veloso
 * <p/>
 * This item defines the logic structure of a single item of the elements from the News feed
 */
public final class NewsItem implements Serializable {

    private String title, description, url, author;
    private GregorianCalendar publishDate = null;

    //empty constructor, to add data with Sets latter
    public NewsItem() {
        title = "";
        description = "";
        url = null;
        author = "";
    }

    // Getters and Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public GregorianCalendar getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(GregorianCalendar publishDate) {
        this.publishDate = publishDate;
    }
}

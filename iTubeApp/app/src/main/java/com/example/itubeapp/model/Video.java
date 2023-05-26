package com.example.itubeapp.model;

import android.database.Cursor;

import java.io.Serializable;

public class Video implements Serializable {
    private String id;
    private String url;

    public Video(String id, String url) {
        this.url = url;
        this.id = id;//UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
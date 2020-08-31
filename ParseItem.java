package com.example.appbar;

public class ParseItem {
    private String imgurl;
    private String title;

    public ParseItem() {
    }

    public ParseItem(String imgurl, String title) {
        this.imgurl = imgurl;
        this.title = title;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
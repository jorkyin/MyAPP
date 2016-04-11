package com.jorkyin.myapp.xml;

/**
 * Created by wuhuifang on 2016/4/6.
 */
public class WebURL {
    private int mID;
    private String mUrl;
    private String mContent;

    public String getContent() {return mContent;}

    public void setContent(String mContent) {
        this.mContent = mContent;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public int getID() {
        return mID;
    }

    public void setID(int mID) {
        this.mID = mID;
    }
}

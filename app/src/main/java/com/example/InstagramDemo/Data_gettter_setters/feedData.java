package com.example.InstagramDemo.Data_gettter_setters;

import android.content.Context;

/**
 * Created by DELL on 2/24/2018.
 */

public class feedData {
    private String feedTitle;
    private String feedDescription;
    private long feedId;
    private String feedImagePath;
    Context context;

    public feedData(String feedDescription, long feedId, String feedImagePath) {
        this.feedDescription = feedDescription;
        this.feedId = feedId;
        this.feedImagePath = feedImagePath;
    }

    public feedData(long feedId, String feedImagePath) {
        this.feedId = feedId;
        this.feedImagePath = feedImagePath;
    }

    public feedData() {
    }

    public feedData(Context context, String feedDescription, String feedImagePath) {
        this.feedDescription = feedDescription;
        this.feedImagePath = feedImagePath;
        this.context=context;
     // Toast.makeText(context, this.getFeedImagePath(), Toast.LENGTH_SHORT).show();
    }

    public String getFeedImagePath() {

        return feedImagePath;
    }

    public void setFeedImagePath(String feedImagePath) {
        this.feedImagePath = feedImagePath;
    }

    public long getFeedId() {
        return feedId;
    }

    public void setFeedId(long feedId) {
        this.feedId = feedId;
    }


    public String getFeedDescription() {

        return feedDescription;
    }

    public void setFeedDescription(String feedDescription) {
        this.feedDescription = feedDescription;
    }
}

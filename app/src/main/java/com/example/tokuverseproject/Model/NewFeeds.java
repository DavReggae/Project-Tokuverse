package com.example.tokuverseproject.Model;

import com.google.gson.annotations.SerializedName;

public class NewFeeds {
    @SerializedName("id")
    String id;
    @SerializedName("user_id")
    String user_id;
    @SerializedName("content")
    String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate_post() {
        return date_post;
    }

    public void setDate_post(String date_post) {
        this.date_post = date_post;
    }

    public String getLike_count() {
        return like_count;
    }

    public void setLike_count(String like_count) {
        this.like_count = like_count;
    }

    @SerializedName("date_post")
    String date_post;
    @SerializedName("like_count")
    String like_count;
}

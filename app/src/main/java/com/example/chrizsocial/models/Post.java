package com.example.chrizsocial.models;

import android.net.Uri;

import java.util.ArrayList;

public class Post {

    public String text = "";
    public String creator = "";
    public Long timestamp ;
    public String userImageOfCreator ;
    public String userNameOfCreator = "";

    public Post(String textPost,String creator, String userNameOfCreator, String userImageOfCreator, Long timestamp){
        this.userImageOfCreator = userImageOfCreator;
        this.userNameOfCreator = userNameOfCreator;
        this.creator =creator;
        this.text = textPost;
        this.timestamp = timestamp;
    }
    public Post()
    {

    }

    public String getTextPost() {
        return text;
    }

    public void setTextPost(String textPost) {
        this.text = textPost;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }


    public String getUserImageOfCreator() {
        return userImageOfCreator;
    }

    public void setUserImageOfCreator(String userImageOfCreator) {
        this.userImageOfCreator = userImageOfCreator;
    }

    public String getUserNameOfCreator() {
        return userNameOfCreator;
    }

    public void setUserNameOfCreator(String userNameOfCreator) {
        this.userNameOfCreator = userNameOfCreator;
    }
}


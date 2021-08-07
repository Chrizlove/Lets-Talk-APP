package com.example.chrizsocial.models;

import android.net.Uri;

public class user {
    private String userId;
    private String userName;
    private Uri userImage;

    public user(String userId, String userName, Uri userImage) {
        this.userId = userId;
        this.userName = userName;
        this.userImage = userImage;
    }

    public user() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Uri getUserImage() {
        return userImage;
    }

    public void setUserImage(Uri userImage) {
        this.userImage = userImage;
    }

    @Override
    public String toString() {
        return "user{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userImage=" + userImage +
                '}';
    }
}

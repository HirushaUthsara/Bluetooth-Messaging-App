package com.example.bluetooth_messaging_app;

import android.graphics.Bitmap;

public class Contact {

    // model class for the contact
    private String id,username;
    private int profile_pic;

    // default constructor

    public Contact() {
    }
    // parametric constructor
    public Contact(String id,String username,int profile_pic) {
        this.id = id;
        this.username = username;
        this.profile_pic = profile_pic;
    }

    // getters and setters for attributes

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(int profile_pic) {
        this.profile_pic = profile_pic;
    }
}

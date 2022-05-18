package com.example.bluetooth_messaging_app;

import android.graphics.Bitmap;

public class Contact {

    // model class for the contact
    private int id;
    private String username;
    private Bitmap profile_pic;

    // default constructor

    public Contact() {
    }
    // parametric constructor
    public Contact(int id,String username,Bitmap profile_pic) {
        this.id = id;
        this.username = username;
        this.profile_pic = profile_pic;
    }

    // getters and setters for attributes

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Bitmap getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(Bitmap profile_pic) {
        this.profile_pic = profile_pic;
    }
}

package com.example.bluetooth_messaging_app.Models;

public class Users {
    int profilepicture;
    String username,userID,lastmessage;

    public Users(String userID, int profilepicture, String username, String lastmessage) {
        this.userID = userID;
        this.profilepicture = profilepicture;
        this.username = username;
        this.lastmessage = lastmessage;
    }


    public int getProfilepicture() {
        return profilepicture;
    }

    public void setProfilepicture(int profilepicture) {
        this.profilepicture = profilepicture;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getLastmessage() {
        return lastmessage;
    }

    public void setLastmessage(String lastmessage) {
        this.lastmessage = lastmessage;
    }
}

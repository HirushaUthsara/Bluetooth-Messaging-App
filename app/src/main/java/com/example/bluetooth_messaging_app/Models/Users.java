package com.example.bluetooth_messaging_app.Models;

public class Users {
    String profilepicture,username,mail,password,userID,lastmessage;

    public Users(String profilepicture, String username, String mail, String password, String userID, String lastmessage) {
        this.profilepicture = profilepicture;
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.userID = userID;
        this.lastmessage = lastmessage;
    }

    public Users(String profilepicture, String username, String lastmessage) {
        this.profilepicture = profilepicture;
        this.username = username;
        this.lastmessage = lastmessage;
    }


    public String getProfilepicture() {
        return profilepicture;
    }

    public void setProfilepicture(String profilepicture) {
        this.profilepicture = profilepicture;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

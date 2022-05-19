package com.example.bluetooth_messaging_app;

import androidx.annotation.Nullable;

public class Message {

    // model class for the messages
    private int id;
    private long time;
    private String senderId;
    private String receiverId;
    private String content;

    // default constructor
    public Message() {
    }

    // parametric constructor
    public Message(int id, long time, String senderId, String receiverId, String content) {
        this.id = id;
        this.time = time;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
    }

    public Message(long time, String senderId, String receiverId, String content) {
        this.time = time;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
    }

    // getters and setters for attributes

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void sendMessage(){
        allcomponents al = new allcomponents();
        al.getReceiving_msg();
    }
}

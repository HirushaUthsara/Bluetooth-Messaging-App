package com.example.bluetooth_messaging_app;

public class Message {

    // model class for the messages
    private int id;
    private long time;
    private int senderId;
    private int receiverId;
    private String content;

    // default constructor
    public Message() {
    }

    // parametric constructor
    public Message(int id,long time,int senderId,int receiverId, String content) {
        this.id = id;
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

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}

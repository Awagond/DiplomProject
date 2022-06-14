package com.example.cabelpc.Model;

import com.google.firebase.database.ServerValue;

public class Comment {
    private String content, phone, image, username;
    private  Object timestamp;

    public Comment() {
    }

    public Comment(String content, String phone, String image, String username) {
        this.content = content;
        this.phone = phone;
        this.image = image;
        this.username = username;
        this.timestamp = ServerValue.TIMESTAMP;
    }

    public Comment(String content, String phone, String image, String username, Object timestamp) {
        this.content = content;
        this.phone = phone;
        this.image = image;
        this.username = username;
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Object getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Object timestamp) {
        this.timestamp = timestamp;
    }
}

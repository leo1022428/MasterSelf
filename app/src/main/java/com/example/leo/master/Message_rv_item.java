package com.example.leo.master;

public class Message_rv_item {
    String name;
    String content;
    int pictureID;
    String time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPictureID() {
        return pictureID;
    }

    public void setPictureID(int pictureID) {
        this.pictureID = pictureID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Message_rv_item(String name, String content, int pictureID, String time) {

        this.name = name;
        this.content = content;
        this.pictureID = pictureID;
        this.time = time;
    }
}

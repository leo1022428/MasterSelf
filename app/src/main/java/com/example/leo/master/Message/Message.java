package com.example.leo.master.Message;


//message recyclerview 用到的屬性
public class Message {
    //對方姓名
    private String name;
    //message內容
    private String content;
    //對方圖片
    private int pictureID;
    //訊息時間
    private String time;
    //訊息的種類(發出的訊息為0,收到的訊息為1)
    private int message_type;

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

    public Message(String name, String content, int pictureID, String time) {

        this.name = name;
        this.content = content;
        this.pictureID = pictureID;
        this.time = time;
    }

    public int getMessage_type() {
        return message_type;
    }

    public void setMessage_type(int message_type) {
        this.message_type = message_type;
    }

    public Message(String name, String content, String time, int message_type) {
        this.name = name;
        this.content = content;
        this.time = time;
        this.message_type = message_type;

    }
}

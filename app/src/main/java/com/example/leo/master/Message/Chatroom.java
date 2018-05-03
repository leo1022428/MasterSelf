package com.example.leo.master.Message;

public class Chatroom {
    String user_id_a, user_id_b;
    int chatroom_id;

    public String getUser_id_a() {
        return user_id_a;
    }

    public void setUser_id_a(String user_id_a) {
        this.user_id_a = user_id_a;
    }

    public String getUser_id_b() {
        return user_id_b;
    }

    public void setUser_id_b(String user_id_b) {
        this.user_id_b = user_id_b;
    }

    public int getChatroom_id() {
        return chatroom_id;
    }

    public void setChatroom_id(int chatroom_id) {
        this.chatroom_id = chatroom_id;
    }

    public Chatroom(String user_id_a, String user_id_b, int chatroom_id) {

        this.user_id_a = user_id_a;
        this.user_id_b = user_id_b;
        this.chatroom_id = chatroom_id;
    }
}

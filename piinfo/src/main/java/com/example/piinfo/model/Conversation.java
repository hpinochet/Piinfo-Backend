package com.example.piinfo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "Conversation")
public class Conversation {

    @Id
    private String id;

    private String date;

    private String time;

    private String user_conversation;

    private String bot_conversation;

    public Conversation(String id, String date, String time, String user_conversation, String bot_conversation) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.user_conversation = user_conversation;
        this.bot_conversation = bot_conversation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUser_conversation() {
        return user_conversation;
    }

    public void setUser_conversation(String user_conversation) {
        this.user_conversation = user_conversation;
    }

    public String getBot_conversation() {
        return bot_conversation;
    }

    public void setBot_conversation(String bot_conversation) {
        this.bot_conversation = bot_conversation;
    }
}

package com.example.piinfo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Reminder")
public class Reminder {

    @Id
    private String id;

    private String reminder_description;

    private String date;

    private String time;

    private Integer done;

    public Reminder(String reminder_description, String date, String time, Integer done) {
        this.reminder_description = reminder_description;
        this.date = date;
        this.time = time;
        this.done = done;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReminder_description() {
        return reminder_description;
    }

    public void setReminder_description(String reminder_description) {
        this.reminder_description = reminder_description;
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

    public Integer getDone() {
        return done;
    }

    public void setDone(Integer done) {
        this.done = done;
    }
}

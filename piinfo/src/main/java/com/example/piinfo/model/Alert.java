package com.example.piinfo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Alert")
public class Alert {

    @Id
    private String id;

    private String alert_description;

    private String date;

    private String time;

    public Alert(String alert_description, String date, String time) {
        this.alert_description = alert_description;
        this.date = date;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlert_description() {
        return alert_description;
    }

    public void setAlert_description(String alert_description) {
        this.alert_description = alert_description;
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
}

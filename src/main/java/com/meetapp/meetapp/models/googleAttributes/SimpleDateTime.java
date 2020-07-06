package com.meetapp.meetapp.models.googleAttributes;

public class SimpleDateTime {

    private String dateTime;

    public SimpleDateTime(String dateTimeString){
        this.dateTime = dateTimeString;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}

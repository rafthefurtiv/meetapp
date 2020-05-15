package com.meetapp.meetapp.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Event  extends com.google.api.services.calendar.model.Event {


    int userId;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}

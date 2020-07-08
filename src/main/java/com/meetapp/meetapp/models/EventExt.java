package com.meetapp.meetapp.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class EventExt {

    @Id
    ObjectId id;

    String eventId;

    String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }


}

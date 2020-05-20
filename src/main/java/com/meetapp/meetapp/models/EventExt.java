package com.meetapp.meetapp.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class EventExt {

    @Id
    ObjectId id;

    int EventId;

    public int getEventId() {
        return EventId;
    }

    public void setEventId(int eventId) {
        this.EventId = eventId;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }


}

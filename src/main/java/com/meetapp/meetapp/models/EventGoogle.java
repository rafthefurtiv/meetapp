package com.meetapp.meetapp.models;

import com.meetapp.meetapp.models.googleAttributes.Creator;
import com.meetapp.meetapp.models.googleAttributes.SimpleDateTime;

public class EventGoogle {

    SimpleDateTime start;
    SimpleDateTime end;
    Creator creator;
    String summary;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public SimpleDateTime getStart() {
        return start;
    }

    public SimpleDateTime getEnd() {
        return end;
    }

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public void setStart(SimpleDateTime start) {
        this.start = start;
    }

    public void setEnd(SimpleDateTime end) {
        this.end = end;
    }
}

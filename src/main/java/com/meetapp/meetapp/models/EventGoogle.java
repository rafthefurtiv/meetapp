package com.meetapp.meetapp.models;

import com.meetapp.meetapp.models.googleAttributes.Attendee;
import com.meetapp.meetapp.models.googleAttributes.Creator;
import com.meetapp.meetapp.models.googleAttributes.SimpleDateTime;

import java.util.List;

public class EventGoogle {

    SimpleDateTime start;
    SimpleDateTime end;
    Creator creator;
    String summary;
    String description;
    List<Attendee> attendees;

    public List<Attendee> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<Attendee> attendees) {
        this.attendees = attendees;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

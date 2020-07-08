package com.meetapp.meetapp.repositories;


import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.meetapp.meetapp.models.EventExt;

public interface EventRepository {

    Event getEventByUserId(Integer userId);

    EventExt getEventExtByEventId(Integer eventId);

    Calendar getGoogleCalendarByEmail(String email);

    Event getGoogleEventsByEmail(String email);

    Event setGoogleEventsByEmail(String email, String jsonEvent);

    EventExt saveEventExtById(EventExt eventExt);
}

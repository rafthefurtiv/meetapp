package com.meetapp.meetapp.repositories;


import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.meetapp.meetapp.models.EventExt;

import java.util.List;

public interface EventRepository {

    Event getEventByUserId(Integer userId);

    EventExt getEventExtByEventId(String eventId);

    List<EventExt> getEventsExtByEmail(String email);

    List<String> getGoogleEvetsByListId(List<String> ids, String email);

    String getGoogleEventById(String id, String email);

    Calendar getGoogleCalendarByEmail(String email);

    Event getGoogleEventsByEmail(String email);

    Event setGoogleEventsByEmail(String email, String jsonEvent);

    EventExt saveEventExtById(EventExt eventExt);
}

package com.meetapp.meetapp.repositories;

import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.meetapp.meetapp.models.EventExt;
import com.meetapp.meetapp.models.User;
import com.meetapp.meetapp.services.EventService;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@Repository
public class EventRepositoryImpl implements EventRepository {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private EventService eventService;

    @Override
    public Event getEventByUserId(Integer userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        return mongoTemplate.findOne(query, Event.class);
    }

    @Override
    public EventExt getEventExtByEventId(Integer eventId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("eventId").is(eventId));
        return mongoTemplate.findOne(query, EventExt.class);
    }

    @Override
    public Calendar getGoogleCalendarByEmail(String email) {
        String res = eventService.getGoogleCalendarRestService(email);
        return null;
    }

    @Override
    public Event getGoogleEventsByEmail(String email) {
        String res = eventService.getGoogleEventsRestService(email);
        return null;
    }

    @Override
    public Event setGoogleEventsByEmail(String email) {
        String res = eventService.setGoogleEventsRestService(email);
        return null;
    }

    @Override
    public EventExt saveEventExtById(EventExt eventExt) {
        EventExt tempEventExt = getEventExtByEventId(eventExt.getEventId());
        if(tempEventExt != null){
            eventExt.setId(tempEventExt.getId());
        }
        mongoTemplate.save(eventExt);
        return eventExt;
    }


}

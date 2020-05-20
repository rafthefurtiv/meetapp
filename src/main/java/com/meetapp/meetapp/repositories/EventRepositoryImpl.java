package com.meetapp.meetapp.repositories;

import com.google.api.services.calendar.model.Event;
import com.meetapp.meetapp.models.EventExt;
import com.meetapp.meetapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class EventRepositoryImpl implements EventRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

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
}

package com.meetapp.meetapp.repositories;

import com.meetapp.meetapp.models.Event;
import com.meetapp.meetapp.models.User;

public interface EventRepository {

    Event getEventByUserId(Integer userId);
}

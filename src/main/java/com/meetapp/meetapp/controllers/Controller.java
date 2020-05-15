package com.meetapp.meetapp.controllers;

import com.meetapp.meetapp.models.Event;
import com.meetapp.meetapp.models.User;
import com.meetapp.meetapp.repositories.EventRepository;
import com.meetapp.meetapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mokup")
public class Controller {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EventRepository eventRepository;

    @GetMapping("/user")
    public User model(@RequestParam(value = "name", defaultValue = "World") String name) {
        User user = userRepository.getUserByUserId(1);
        return user;
    }

    @GetMapping("/event")
    public Event getEvent() {
        Event event = eventRepository.getEventByUserId(1);
        return event;
    }

}

package com.meetapp.meetapp.controllers;

import com.google.api.services.calendar.model.Event;
import com.meetapp.meetapp.models.EventExt;
import com.meetapp.meetapp.models.User;
import com.meetapp.meetapp.repositories.EventRepository;
import com.meetapp.meetapp.repositories.UserRepository;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EventRepository eventRepository;

    @GetMapping("/get")
    public ResponseEntity model(@RequestParam(value = "name", defaultValue = "World") String name) {
        EventExt event = eventRepository.getEventExtByEventId(1);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

}

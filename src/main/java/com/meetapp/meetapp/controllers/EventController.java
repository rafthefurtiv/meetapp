package com.meetapp.meetapp.controllers;

import com.google.api.services.calendar.model.Event;
import com.meetapp.meetapp.models.EventExt;
import com.meetapp.meetapp.models.User;
import com.meetapp.meetapp.repositories.EventRepository;
import com.meetapp.meetapp.repositories.UserRepository;
import com.meetapp.meetapp.services.EventService;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@RestController
@CrossOrigin
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @GetMapping("/get")
    public ResponseEntity model(@RequestParam("eventId") Integer eventId) {
        EventExt event = eventRepository.getEventExtByEventId(eventId);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity model(@RequestBody EventExt eventExt) {
        EventExt event = eventRepository.saveEventExtById(eventExt);
        EventService ff = new EventService();
        ff.callPost();
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

}

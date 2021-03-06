package com.meetapp.meetapp.controllers;

import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.meetapp.meetapp.MeetappApplication;
import com.meetapp.meetapp.models.EventExt;
import com.meetapp.meetapp.models.User;
import com.meetapp.meetapp.repositories.EventRepository;
import com.meetapp.meetapp.repositories.UserRepository;
import com.meetapp.meetapp.services.EventService;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @GetMapping("/getEventExt")
    public ResponseEntity getEventExt(@RequestParam("eventExtId") String eventExtId) {
        MeetappApplication.logger.info(new Object(){}.getClass().getName() + " - "+ new Object(){}.getClass().getEnclosingMethod().getName());
        EventExt event = eventRepository.getEventExtByEventId(eventExtId);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @GetMapping("/getEventsExt")
    public ResponseEntity getEventExtByEmail(@RequestParam("email") String email) {
        MeetappApplication.logger.info(new Object(){}.getClass().getName() + " - "+ new Object(){}.getClass().getEnclosingMethod().getName());
        List<EventExt> events = eventRepository.getEventsExtByEmail(email);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/getEventsByEmail")
    public ResponseEntity getEventsByEmail(@RequestParam("email") String email) {
        MeetappApplication.logger.info(new Object(){}.getClass().getName() + " - "+ new Object(){}.getClass().getEnclosingMethod().getName());
        List<EventExt> events = eventRepository.getEventsExtByEmail(email);
        List<String> ids = new ArrayList<>();
        events.stream().map(EventExt::getEventId).forEach(s -> ids.add(s));
        List<String> eventsJson = eventRepository.getGoogleEvetsByListId(ids, email);

        return new ResponseEntity<>(eventsJson, HttpStatus.OK);
    }

    @GetMapping("/getAppEventsByEmail")
    public ResponseEntity getAppEventsByEmail(@RequestParam("email") String email) {
        MeetappApplication.logger.info(new Object(){}.getClass().getName() + " - "+ new Object(){}.getClass().getEnclosingMethod().getName());
        List<EventExt> eventsExt = eventRepository.getEventsExtByEmail(email);

        List<EventExt> events = eventRepository.getEventsExtByEmail(email);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/getCalendar")
    public ResponseEntity getGoogleCalendarByEmail(@RequestParam("email") String email) {
        MeetappApplication.logger.info(new Object(){}.getClass().getName() + " - "+ new Object(){}.getClass().getEnclosingMethod().getName());
        Calendar calendar = eventRepository.getGoogleCalendarByEmail(email);
        return new ResponseEntity<>(calendar, HttpStatus.OK);
    }

    @GetMapping("/getEvents")
    public ResponseEntity getGoogleEventsByEmail(@RequestParam("email") String email) {
        MeetappApplication.logger.info(new Object(){}.getClass().getName() + " - "+ new Object(){}.getClass().getEnclosingMethod().getName());
        Event event = eventRepository.getGoogleEventsByEmail(email);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @GetMapping("/setEventMocked")
    public ResponseEntity setGoogleEventsByEmail(@RequestParam("email") String email) {

        MeetappApplication.logger.info(new Object(){}.getClass().getName() + " - "+ new Object(){}.getClass().getEnclosingMethod().getName());
        Event event = eventRepository.setGoogleEventsByEmail(email, null);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PostMapping("/setEvent")
    public ResponseEntity setGoogleEventsByEmail(@RequestBody String email,  @RequestBody String jsonEvent) {
        MeetappApplication.logger.info(new Object(){}.getClass().getName() + " - "+ new Object(){}.getClass().getEnclosingMethod().getName());
        Event event = eventRepository.setGoogleEventsByEmail(email, jsonEvent);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity model(@RequestBody EventExt eventExt) {
        MeetappApplication.logger.info(new Object(){}.getClass().getName() + " - "+ new Object(){}.getClass().getEnclosingMethod().getName());
        EventExt event = eventRepository.saveEventExtById(eventExt);
        EventService ff = new EventService();
        ff.callPost();
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

}

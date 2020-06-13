package com.meetapp.meetapp.services;

import com.google.api.services.calendar.model.Calendar;
import com.google.api.services.calendar.model.Event;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EventService {

    String email = "rafthefurtiv@gmail.com";

    //String googleEventInsertUrl = "https://www.googleapis.com/calendar/v3/calendars/rafthefurtiv@gmail.com/events";
    String googleEventInsertUrl = "https://www.googleapis.com/calendar/v3/calendars/"+ email + "/events";
    String tokenUrl = "https://oauth2.googleapis.com/token";

    RestTemplate restTemplate = new RestTemplate();

    public void callPost(){
        ResponseEntity<Calendar> res = restTemplate.getForEntity(googleEventInsertUrl, Calendar.class);

        res.getBody().getTimeZone();
    }


    public void getEvents(){
        EventService eveService = new EventService();
        eveService.getEvents();
    }


}

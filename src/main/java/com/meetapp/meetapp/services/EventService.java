package com.meetapp.meetapp.services;

//import com.google.api.services.calendar.model.Calendar;
//import com.google.api.services.calendar.model.Event;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.services.calendar.model.Event;
import com.google.gson.Gson;
import com.meetapp.meetapp.repositories.EventRepository;
import com.meetapp.meetapp.repositories.EventRepositoryImpl;
import com.meetapp.meetapp.repositories.TokenRepository;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.Calendar.Events;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Service
public class EventService {


    //String googleEventInsertUrl = "https://www.googleapis.com/calendar/v3/calendars/rafthefurtiv@gmail.com/events";
    //String googleEventInsertUrl = "https://www.googleapis.com/calendar/v3/calendars/"+ email + "/events";
    //String tokenUrl = "https://oauth2.googleapis.com/token";

    //RestTemplate restTemplate = new RestTemplate();

    @Autowired
    TokenRepository tokenRepository;

    public void callPost(){
    }

    public String getGoogleCalendarRestService(String email){
        // get token by Email

        String tokenString = null;
        String result = null;

        final CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet request = new HttpGet("https://www.googleapis.com/calendar/v3/calendars/"+email+"");

        tokenString = tokenRepository.getTokenByUserEmail(email).getToken();

        request.addHeader("Authorization", "Bearer " + tokenString);

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();

            if (entity != null) {
                // return it as a String
                result = EntityUtils.toString(entity);
                System.out.println(result);
            }
            else{
                System.out.println("Result not arrived.");
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


    public String getGoogleEventsRestService(String email){
        // get token by Email

        String tokenString = null;
        String result = null;

        final CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet request = new HttpGet("https://www.googleapis.com/calendar/v3/calendars/"+email+"/events");

        tokenString = tokenRepository.getTokenByUserEmail(email).getToken();

        request.addHeader("Authorization", "Bearer " + tokenString);

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();

            if (entity != null) {
                // return it as a String
                result = EntityUtils.toString(entity);
                System.out.println(result);
            }
            else{
                System.out.println("Result not arrived.");
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson g = new Gson();
        com.google.api.services.calendar.model.Events eve = g.fromJson(result, com.google.api.services.calendar.model.Events.class);

        return result;
    }


    public void getEvents(){
        EventService eveService = new EventService();
        eveService.getEvents();
    }


}

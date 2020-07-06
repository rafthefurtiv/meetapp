package com.meetapp.meetapp.services;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import com.meetapp.meetapp.models.EventGoogle;
import com.meetapp.meetapp.models.googleAttributes.Attendee;
import com.meetapp.meetapp.models.googleAttributes.Creator;
import com.meetapp.meetapp.models.googleAttributes.SimpleDateTime;
import com.meetapp.meetapp.repositories.TokenRepository;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {


    //String googleEventInsertUrl = "https://www.googleapis.com/calendar/v3/calendars/rafthefurtiv@gmail.com/events";
    //String googleEventInsertUrl = "https://www.googleapis.com/calendar/v3/calendars/"+ email + "/events";
    //String tokenUrl = "https://oauth2.googleapis.com/token";

    //RestTemplate restTemplate = new RestTemplate();

    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    Gson g;

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

        //HttpGet request = new HttpGet("https://www.googleapis.com/calendar/v3/calendars/"+email+"/events");
        HttpGet request = new HttpGet("https://www.googleapis.com/calendar/v3/calendars/"+email+"/events/4g0vbt728m67vbbg7sotnbaaq2");

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

        //Gson g = new Gson();
        //Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
        //om.google.api.services.calendar.model.Events eve = g.fromJson(result, com.google.api.services.calendar.model.Events.class);
        //Object gsonContent = g.fromJson( result, Object.class);

        Event rvr2 = g.fromJson( result, Event.class);


        JsonObject gsonContent = g.fromJson( result, JsonObject.class);

        List< LinkedTreeMap<String, Object>> eventList = g.fromJson(gsonContent.get("items"), List.class);



        LinkedTreeMap<String, Object> eve = eventList.get(0);

        Event event = getEventFromTreeMap(eve);

        int a = 3;

/*                JSONObject jsonObject = new JSONObject(jsonString);
        int age = jsonObject.getInt("age");*/

        return result;
    }



    public String setGoogleEventsRestService(String email){

        String tokenString = null;
        String result = null;

        final CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost request = new HttpPost("https://www.googleapis.com/calendar/v3/calendars/"+email+"/events");


        EventGoogle eveGoogle = getMockedEvent(email);

/*        String debugString = "{\n" +
                "  \"end\": {\n" +
                "    \"dateTime\": \"2020-01-01T00:01:01.000Z\"\n" +
                "  },\n" +
                "  \"start\": {\n" +
                "    \"dateTime\": \"2020-01-01T00:00:01.000Z\"\n" +
                "  },\n" +
                "  \"creator\": {\n" +
                "    \"email\": \"rafthefurtiv@gmail.com\"\n" +
                "  },\n" +
                "  \"summary\": \"Prova Raffaele\"\n" +
                "}";*/

        StringEntity entity = null;
        try {
            String eventString = g.toJsonTree(eveGoogle, EventGoogle.class).toString();
            entity = new StringEntity(eventString);
            System.out.println("Parsed event: " + eventString);
            //entity = new StringEntity(debugString);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        request.setEntity(entity);

        tokenString = tokenRepository.getTokenByUserEmail(email).getToken();

        request.addHeader("Authorization", "Bearer " + tokenString);
        request.addHeader("Accept", "application/json");
        request.addHeader("Content-Type", "application/json");

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            response.getStatusLine().getStatusCode();
            System.out.println(response.toString());

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return result;
    }


    public void getEvents(){
        EventService eveService = new EventService();
        eveService.getEvents();
    }


    public Event getEventFromTreeMap(LinkedTreeMap<String, Object> eventMap){

        Event event = new Event();

        for(LinkedTreeMap.Entry<String,Object> entry : eventMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            DateTime tempDateTime;
            if(key.compareTo("created") == 0 || key.compareTo("updated") == 0){
                tempDateTime = new DateTime(value.toString());
                event.set(key, tempDateTime);
            } else{
                event.set(key, value);
            }

        }

        return event;
    }

    public EventGoogle getMockedEvent(String email){
        EventGoogle eveGoogle = new EventGoogle();

        // MOCK
        eveGoogle.setStart(new SimpleDateTime("2020-01-01T00:02:01.000Z"));
        eveGoogle.setEnd(new SimpleDateTime("2020-01-01T00:03:01.000Z"));
        eveGoogle.setCreator(new Creator(email));
        eveGoogle.setSummary("Prova Raffaele");
        eveGoogle.setDescription("Prova descrizione");

        Attendee firstAttendee = new Attendee();
        firstAttendee.setEmail("bileragger@gmail.com");
        firstAttendee.setDisplayName("SIMONE ESPOSITO");
        firstAttendee.setResponseStatus("needsAction");

        List<Attendee> listAttendees = new ArrayList<Attendee>();
        listAttendees.add(firstAttendee);

        eveGoogle.setAttendees(listAttendees);

        return eveGoogle;
    }


    public EventGoogle getEventFromJson(String jsonString){
        EventGoogle eveGoogle = new EventGoogle();

/*        eveGoogle.setStart(new SimpleDateTime("2020-01-01T00:02:01.000Z"));
        eveGoogle.setEnd(new SimpleDateTime("2020-01-01T00:03:01.000Z"));
        eveGoogle.setCreator(new Creator(email));
        eveGoogle.setSummary("Prova Raffaele");
        eveGoogle.setDescription("Prova descrizione");*/

        return eveGoogle;
    }



}

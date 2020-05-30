package com.meetapp.meetapp.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Token {

    @Id
    ObjectId id;

    String userEmail;

    String token;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

package com.meetapp.meetapp.models;

import org.springframework.data.annotation.Id;

public class User {

    @Id
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

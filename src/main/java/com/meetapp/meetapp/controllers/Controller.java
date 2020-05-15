package com.meetapp.meetapp.controllers;

import com.meetapp.meetapp.models.Model;
import com.meetapp.meetapp.models.User;
import com.meetapp.meetapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controller")
public class Controller {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/example")
    public User model(@RequestParam(value = "name", defaultValue = "World") String name) {
        Model temp = new Model();
        temp.setAttribute(1);
        User user = userRepository.getUserByUserId(1);
        return user;
    }

}

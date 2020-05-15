package com.meetapp.meetapp.controllers;

import com.meetapp.meetapp.models.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controller")
public class Controller {

    @GetMapping("/example")
    public Model model(@RequestParam(value = "name", defaultValue = "World") String name) {
        Model temp = new Model();
        temp.setAttribute(1);
        return temp;
    }

}

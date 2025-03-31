package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {
    @GetMapping("/hello")
    public String hello(){
        return "HELLO THERE!";
    }


    @GetMapping("/greeting")
    public String greeting(){
        return "Greetings!";
    }
}                                                                                                                                                                                                                       
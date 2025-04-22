package com.jfloresdev.java.ws.api_books_v1.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping
    public String demo(){
        return "Demo v1- Ideav2";
    }

    @GetMapping("/v2")
    public String demo2(){
        return "Demov2";
    }




}

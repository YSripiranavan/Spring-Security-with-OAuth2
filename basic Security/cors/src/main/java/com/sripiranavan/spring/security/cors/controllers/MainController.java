package com.sripiranavan.spring.security.cors.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @GetMapping("/")
    public String mainPage(){
        return "main.html";
    }

    @PostMapping("/test")
    @ResponseBody
//    @CrossOrigin("*")
    public String test(){
        return "TEST!";
    }
}

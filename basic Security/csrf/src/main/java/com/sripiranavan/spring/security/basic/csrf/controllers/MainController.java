package com.sripiranavan.spring.security.basic.csrf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @GetMapping
    public String mainPage(){
        return "main.html";
    }

    @PostMapping("/change")
    public String change(){
        System.out.println(":(");
        return "main.html";
    }
}

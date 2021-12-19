package com.sripiranavan.spring.security.configure.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello";
    }

    @GetMapping("/ciao")
    public String sayCiao(){
        return "Ciao";
    }

    @PostMapping("/hola")
    public String sayHola(){
        return "Hola";
    }

    @GetMapping("/a")
    public String a(){
        return "A";
    }

    @GetMapping("/a/b")
    public String ab(){
        return "AB";
    }

    @GetMapping("/a/b/c")
    public String abc(){
        return "ABC";
    }

    @GetMapping("/b/c")
    public String bc(){
        return "BC";
    }

    @GetMapping("/c/{name}")
    public String c(@PathVariable String name){
        return "Hello "+name;
    }
}

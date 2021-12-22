package com.sripiranavan.spring.security.webflux.reactive.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class DemoController {

    @GetMapping("/demo")
    public Mono<String> demo(Mono<Authentication> ma){
//        here spring inject the authentication object
//        return Mono.just("Sripiranavan");
        return ma.map(a -> "Hello "+a.getName());
    }

    @GetMapping("/demo1")
    @PreAuthorize("hasAuthority('read')")
    public Mono<String> demo1(){
//        here anyway we can use the authentication object
        Mono<Authentication> ma = ReactiveSecurityContextHolder.getContext()
                .map(sc -> sc.getAuthentication());

        return ma.map(a -> "Hello from "+a.getName());
    }
}

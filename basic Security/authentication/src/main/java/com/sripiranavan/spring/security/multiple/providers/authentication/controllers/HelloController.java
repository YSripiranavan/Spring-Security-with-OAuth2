package com.sripiranavan.spring.security.multiple.providers.authentication.controllers;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.concurrent.DelegatingSecurityContextRunnable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class HelloController {
    @GetMapping("/hello")
//    @Async
    public String hello(){
//        Create a thread
        Runnable r = () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            System.out.println(authentication);
        };
//        Alternative for the MODE_INHERITABLETHREADLOCAL
//        DelegatingSecurityContextRunnable dr = new DelegatingSecurityContextRunnable(r);

        ExecutorService service = Executors.newSingleThreadExecutor();
//        Alternative for dr
//        service.submit(dr);
        DelegatingSecurityContextExecutorService dService = new DelegatingSecurityContextExecutorService(service);
        dService.submit(r);
        dService.shutdown();
        return "Hello!";
    }
}

package com.sripiranavan.spring.security.demomethodas.controller;

import com.sripiranavan.spring.security.demomethodas.service.ProductService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{username}")
    public List<String> findProductsForUser(@PathVariable String username){
        return productService.findProductsForUser(username);
    }

    @GetMapping("/test1")
    public String test1(){
        return productService.test1();
    }

    @GetMapping("/test2")
    public String test2(){
        return productService.test2();
    }

    @GetMapping("/test3")
    public String test3(){
        return productService.test3();
    }

    @GetMapping("/test4")
    public List<String> test4(){
        List<String> list = new ArrayList<>();
        list.add("piranavan");
        list.add("sri");
        list.add("bill");
        list.add("john");
//        List<String> immutableList = List.of("piranavan","sri","bill","john");  //this is immutable so can't filter the collection
        return productService.test4(list);
    }

    @GetMapping("/test5")
    public List<String> test5(){
        return productService.test5();
    }
}

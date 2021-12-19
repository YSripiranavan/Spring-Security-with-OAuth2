package com.sripiranavan.spring.security.demomethodas.service;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    /**
     *
     * @PreAuthorize -> the authorization rules are validated before calling the protected method
     * @PostAuthorize -> the method is called,  and only then the aspect validates the authorization rules
     *
     * @PreFilter -> the method need to have the parameter of type Collection or array
     *             -> the aspects intercepts the method call and validated the values inside the collection
     *
     * @PostFilter -> returned value need to be a Collection or an array
     *              -> the aspect applies the authorization rules and return only the values that comply
     */
//    @PreAuthorize("hasAuthority('write')")
    @PreAuthorize("#username == authentication.name")
    public List<String> findProductsForUser(String username){
        return List.of("Beer","Chocolate");
    }

    @PreAuthorize("hasAuthority('write')")
    public String test2(){
        System.out.println("@PreAuthorize run");
        return "Test2";
    }

    @PostAuthorize("hasAuthority('write')")
    public String test1(){
        System.out.println("@PostAuthorize run");
        return "Test1";
    }

    @PostAuthorize("returnObject == authentication.name")
    public String test3(){
        System.out.println("@PostAuthorize run 2");
//        Select some data from database do the query
        return "sri";
    }

    @PreFilter("filterObject != authentication.name")
    public List<String> test4(List<String> list){
        System.out.println(list);
        return list;
    }

    @PostFilter("filterObject != authentication.name")
    public List<String> test5(){
        List<String> list = new ArrayList<>();
        list.add("piranavan");
        list.add("sri");
        list.add("bill");
        list.add("john");

        return list;
    }
}

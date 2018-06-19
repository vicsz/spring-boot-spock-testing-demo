package com.example.springbootspocktestingdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GreetingController {

    @Autowired
    SalutationService salutationService;


    @RequestMapping("/")
    public String hello(){
        return "helloworld";
    }

    @RequestMapping("/greeting")
    public Map<String, Object> greeting(String value){

        Map<String, Object> map = new HashMap<>();
        map.put("message", salutationService.getSalutation() + " " + value);
        return map;

    }
}

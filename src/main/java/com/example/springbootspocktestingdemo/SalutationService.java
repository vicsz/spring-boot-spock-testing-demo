package com.example.springbootspocktestingdemo;

import org.springframework.stereotype.Component;

@Component
public class SalutationService {

    public String getSalutation(){
        return "Hello";
    }
}

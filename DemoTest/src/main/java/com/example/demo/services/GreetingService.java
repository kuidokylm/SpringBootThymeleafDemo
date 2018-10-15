package com.example.demo.services;

import org.springframework.stereotype.Service;

//https://spring.io/guides/gs/testing-web/

@Service
public class GreetingService {
	
	public String greet() {
        return "Hello World";
    }

}

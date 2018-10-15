package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.services.GreetingService;

//testide controller
//https://spring.io/guides/gs/testing-web/
@ComponentScan("com.example.demo.services")
@Controller
public class TestController {
	
	@Autowired
	private final GreetingService service;
	
	public TestController(GreetingService service) {
        this.service = service;
    }
	
	@GetMapping(value= {"/test"})
	@ResponseBody
    public String greeting() {
        return "Hello World";
    }
	
	
	
	@RequestMapping("/greetingtest")
    public @ResponseBody String greetingtest() {
        return service.greet();
    }

}

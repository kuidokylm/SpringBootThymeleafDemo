package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.controllers.TestController;

//https://spring.io/guides/gs/testing-web/

//The @SpringBootTest annotation tells Spring Boot to go and look for a main configuration class 
//(one with @SpringBootApplication for instance), and use that to start a Spring application context
@RunWith(SpringRunner.class)
@SpringBootTest  
public class DemoTestApplicationTests {


	
	@Autowired
    private TestController controller;
	
	
	@Test
	public void contextLoads() throws Exception {		
		assertThat(controller).isNotNull();
	}

}

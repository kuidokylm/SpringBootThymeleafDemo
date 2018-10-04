package com.example.demo;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

//same as @Configuration @EnableAutoConfiguration @ComponentScan
//scanBasePackages tuleb lisada siis, kui package ei alga projekti juurtasandist
// https://stackoverflow.com/questions/34367316/spring-boot-autowired-does-not-work-classes-in-different-package
@SpringBootApplication(scanBasePackages = {"com.example.repository","com.example.demo"})
public class DemoTestApplication {

	

	//https://spring.io/guides/gs/rest-service/
	// Eclipse Run As ->  Spring Boot App
	// Sirviku URL http://localhost:8080/default
	public static void main(String[] args) {  //main rakendus
		//SpringApplication.run(DemoTestApplication.class, args);
				
		SpringApplication app = new SpringApplication(DemoTestApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}
	
//	@Bean
//    public Java8TimeDialect java8TimeDialect() {
//        return new Java8TimeDialect();
//    }
}

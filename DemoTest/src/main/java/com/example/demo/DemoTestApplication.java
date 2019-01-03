package com.example.demo;

import javax.faces.webapp.FacesServlet;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;


//same as @Configuration @EnableAutoConfiguration @ComponentScan
//scanBasePackages tuleb lisada siis, kui package ei alga projekti juurtasandist
// https://stackoverflow.com/questions/34367316/spring-boot-autowired-does-not-work-classes-in-different-package

@SpringBootApplication(scanBasePackages = {"com.example.repository","com.example.demo","com.example.jsf.beans"})
public class DemoTestApplication {

	
	// Customization klass, seal määratakse port
	//https://spring.io/guides/gs/rest-service/
	// Eclipse Run As ->  Spring Boot App
	// Sirviku URL http://localhost:8083/default
	public static void main(String[] args) {  //main rakendus
		//SpringApplication.run(DemoTestApplication.class, args);
				
		SpringApplication app = new SpringApplication(DemoTestApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}
	

	// registreerime, et päringud .xhtml failidele lähevad JSF servlettidele töötlemiseks  
	@Bean
    public ServletRegistrationBean<FacesServlet> servletRegistrationBean() {
        FacesServlet servlet = new FacesServlet();
        ServletRegistrationBean<FacesServlet> servletRegistrationBean =
          new ServletRegistrationBean<FacesServlet>(servlet, "*.xhtml");
          //new ServletRegistrationBean(servlet, "*.jsf");
        servletRegistrationBean.setLoadOnStartup(1);
        return servletRegistrationBean;
    }	
	
}

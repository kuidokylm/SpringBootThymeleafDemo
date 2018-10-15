package com.example.demo;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controllers.TestController;

//https://spring.io/guides/gs/testing-web/
// ei tõmba veebi serverit käima
@RunWith(SpringRunner.class)
//@ComponentScan("com.example.demo.services")
@WebMvcTest(TestController.class) //ainult seal kontrollerist
public class WebLayerTest {
	
	@Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
    	//andDo(print()) näitab Request ja Response sisu
        this.mockMvc.perform(get("/test")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("Hello World")));

    }

}

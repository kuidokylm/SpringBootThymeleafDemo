package com.example.jsf.beans;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;

@Named("helloWorld")
@SessionScoped
public class HelloWorld {
	
	private String s1 = "Hello World!! Töötab";

    public String getS1() {
        System.out.println(s1);
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

}

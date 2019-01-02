package com.example.jsf.beans;

import java.io.Serializable;

//import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;


//@ManagedBean(name = "primeBean", eager = true)  //see ei tööta
@Named("primeBean")
@SessionScoped  //@ViewScoped
public class PrimeBean implements Serializable {

	private static final long serialVersionUID = 3860730229822198456L;
	private String firstName="Kuido";
	private String lastName="Külm";
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}

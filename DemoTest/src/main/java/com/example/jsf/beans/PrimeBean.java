package com.example.jsf.beans;

import java.io.Serializable;
import java.util.List;

//import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.Country;
import com.example.repository.CountryJdbcRepository;


//@ManagedBean(name = "primeBean", eager = true)  //see ei tööta
@Named("primeBean")
@SessionScoped  //@ViewScoped
public class PrimeBean implements Serializable {

	@Autowired
	CountryJdbcRepository repo;
	
	private static final long serialVersionUID = 3860730229822198456L;
	private String firstName="Kuido";
	private String lastName="Külm";
	
	private List<Country> riigid;
	
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
	
	public List<Country> getRiigid() {
		if (this.riigid == null)
		{
			this.riigid=repo.findAllCountry();
		}
		return this.riigid;
		//return repo.findAllCountry();
	}
	
}

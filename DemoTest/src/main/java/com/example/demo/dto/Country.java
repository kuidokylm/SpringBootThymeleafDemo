package com.example.demo.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Country { //@Table(name = "Country") //tabeli nimi on sama mis klassi nimi

	@Id
	@NotBlank(message="Riigi nimi peab olema")
	private String countryname;  //Riigi nimi
	@NotBlank(message="Valuuta tähis peab olema")
	private String currency;  //valuuta tähis
	
	public Country() {
    }	
	public Country(String name, String currency) {
        this.countryname = name;
        this.currency = currency;
    }
	
	public String getCountryname() {
		return countryname;
	}
	public void setCountryname(String name) {
		this.countryname = name;
	}

	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
}

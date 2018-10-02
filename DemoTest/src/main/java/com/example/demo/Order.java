package com.example.demo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.demo.dto.Orders;

public class Order extends Orders {
	
	@NotNull	
    private String barcode;
	
	@NotNull
	@NotBlank(message="Toote nimi peab olema")
    private String name;
	
	@NotNull	
	@Size(min=2, message="Eesnimi vähemalt 2 tähte")
    private String firstname;
	
	@NotNull
	@NotBlank(message="Perekonnanimi peab olema")
    private String lastname;
	
	@NotBlank(message="Valuuta tähis peab olema")
	private String currency;  //valuuta tähis


	public String getFirstname() {
		return this.firstname;
	}
	public void setFirstname(String firstName) {
		this.firstname = firstName;
	}
	public String getLastname() {
		return this.lastname;
	}
	public void setLastname(String lastName) {
		this.lastname = lastName;
	}

	public String getBarcode() {
		return this.barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	
}

package com.example.demo.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// https://spring.io/guides/gs/accessing-data-jpa/
// https://dzone.com/articles/spring-boot-and-spring-jdbc-with-h2

@Entity
public class Client {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@NotNull	
    private String secnumber;
	
	@NotNull	
	@Size(min=2, message="Eesnimi vähemalt 2 tähte")
    private String firstname;
	
	@NotNull
	@NotBlank(message="Perekonnanimi peab olema")
    private String lastname;
	@NotNull
	@Size(min=3, message="Telefon vähemalt 3 numbrit")
    private String phone;
    @NotNull
    @NotBlank(message="Riik on määramata")
    private String countryname;
    
    @NotNull    
    private String address;
    
    //default konstruktor peab olema
	public Client() { }	
    
    public Client(String secnumber,String firstName, String lastName, String phone, String countryname, String address, Long id) {
    	this.secnumber=secnumber.trim();
        this.firstname = firstName.trim();
        this.lastname = lastName.trim();
        this.phone=phone.trim();
        this.countryname=countryname.trim();
        this.address=address.trim();
        this.id=id;
    }
    
    public Long getId()
    {
    	return this.id;
    }

    public void setId(Long id)
    {
    	this.id=id;
    }    
    
	public String getSecnumber() {
		return this.secnumber;
	}
	public void setSecnumber(String secnumber) {
		this.secnumber = secnumber;
	}
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
	public String getPhone() {
		return this.phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCountryname() {
		return this.countryname;
	}
	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}
	public String getAddress() {
		return this.address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}

package com.example.demo.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;


@Entity
public class Product {

	
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@NotNull	
    private String barcode;
	
	@NotNull
	@NotBlank(message="Nimi peab olema")
    private String name;
	
	@NotNull
    private String description;

	
	@NotNull(message="Kuupäev peab olema")	
	@DateTimeFormat(pattern = "dd.MM.yyyy") 
	@PastOrPresent(message = "Kuupäev ei saa olla tulevikus")
    private Date releasedate;
	
	@PositiveOrZero(message = "Kauba hind ei tohi negatiivne olla")
	@Max(value=999999999,message="Kauba hind nii kõrge ei saa ka nüüd olla")
	@NumberFormat(pattern="#0.00")
    private float baseprice;
    
	public Product() { }  //default konstruktor peab olema
	
	
    public Product(String barcode,String name, String description, Date releasedate, float baseprice, Long id) {
    	this.barcode=barcode.trim();
        this.name=name.trim();
        this.description = description.trim();
        this.releasedate=releasedate;
        this.baseprice=baseprice;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getReleasedate() {
		return releasedate;
	}

	public void setReleasedate(String releasedate) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		Date aeg= formatter.parse(releasedate);
		this.releasedate = aeg;
	}
		
	public void setReleasedate(Date releasedate) {
		this.releasedate = releasedate;
	}

	public float getBaseprice() {
		return baseprice;
	}

	public void setBaseprice(float baseprice) {
		this.baseprice = baseprice;
	}
	
	public void setBaseprice(String baseprice) {
		
		this.baseprice = Float.parseFloat(baseprice.replace(',', '.'));
	}

}

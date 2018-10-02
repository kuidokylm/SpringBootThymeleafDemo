package com.example.demo.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class Orders {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	@NotNull(message="Toote Id peab olema")	
	private Long productid;
	@NotNull(message="Kliendi ID peab olema")	
	private Long clientid;
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	private Date transactiondate;
	
	@NotNull(message="Kogus peab olema")
	@NumberFormat(pattern="#0.00")
	@Max(value=999999999,message="Kogus nüüd nii suur ei saa ka nüüd olla")
	private float amount;
	
	@Max(value=999999999,message="Hind nii kõrge ei saa ka nüüd olla")
	private float cpprice; //converted produce price
	
	@Min(value=0,message="Valuuta kurss ei saa olla negatiivne")
	private float currencyrate; //valuuta kurss
	
	
	public Long getId()
    {
    	return this.id;
    }
	
	public void setId( Long id)
    {
    	this.id=id;
    }


	public Long getProductid() {
		return productid;
	}


	public void setProductid(Long productid) {
		this.productid = productid;
	}


	public Long getClientid() {
		return this.clientid;
	}


	public void setClientid(Long clientid) {
		this.clientid = clientid;
	}


	public Date getTransactiondate() {
		return transactiondate;
	}


	public void setTransactiondate(Date transactiondate) {
		this.transactiondate = transactiondate;
	}


	public float getAmount() {
		return amount;
	}


	public void setAmount(float amount) {
		this.amount = amount;
	}

	public float getCpprice() {
		return cpprice;
	}

	public void setCpprice(float cpprice) {
		this.cpprice = cpprice;
	}

	public float getCurrencyrate() {
		return currencyrate;
	}

	public void setCurrencyrate(float currencyrate) {
		this.currencyrate = currencyrate;
	}
	
}

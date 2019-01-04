package com.example.jsf.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
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
	private Country selectedCountry;
	
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
		if (this.riigid == null) //et tabelis sorteerimine töötaks
		{
			this.riigid=repo.findAllCountry();
		}
		return this.riigid;
	}
	
	public void onRowSelect(SelectEvent event) {
		String riik=((Country) event.getObject()).getCountryname();
        FacesMessage msg = new FacesMessage("Valiti riik", riik);
        System.out.println("Valiti: "+riik);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	public Country getSelectedCountry() {
		return selectedCountry;
	}
	public void setSelectedCountry(Country selectedCountry) {
		this.selectedCountry = selectedCountry;
	}
	
}

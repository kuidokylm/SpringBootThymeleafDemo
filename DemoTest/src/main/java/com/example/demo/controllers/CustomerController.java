package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Content;
import com.example.demo.Utils;
import com.example.demo.dto.Client;
import com.example.demo.dto.Country;
import com.example.repository.CountryJdbcRepository;

@Controller
public class CustomerController {

	@Autowired
	CountryJdbcRepository repo;

	@GetMapping(value= {"/customers"})  
	public String customers(Model model,@RequestParam(value = "op", required = true, defaultValue = "sor") String[] Op
			,@RequestParam(value = "order", required = true, defaultValue = "ASC") String[] Asc
			,@RequestParam(value = "id", required = true, defaultValue = "0") Long[] Id	
			,@RequestParam(value = "sisu", required = false, defaultValue = "") String[] Sisu
			,@RequestParam(value = "sort", required = true, defaultValue = "lastname") String[] Sort) {
		List<Country> states= repo.findAllCountry();
		model.addAttribute("countries", states);
		Content kontent = new Content("");
        Client client= new Client();
        client.setId(0L); //defaultValue = "0"
        client.setCountryname("Eesti"); 
        List<Client> clients=new ArrayList<Client>();
        if (Sisu.length > 0)
        {
        	kontent.setContent(Sisu[0]);
        	clients=repo.selectAllClients(Sisu[0]);
        	model.addAttribute("sisu", Sisu[0]);
        }
        else
        {
        	clients=repo.selectAllClients();
        	model.addAttribute("sisu", "");
        }
        String orde="ASC";
        if (Asc[0].contentEquals("ASC"))
        {
        	orde="DESC";
        }
		if (Op[0].contentEquals("sor") ) //tabeli sorteerimine
		{
			clients=Utils.sortClients(clients, Sort[0], orde);			
		}		
		if (Op[0].contentEquals("sel") ) //tabelist kliendi valik
		{
			if (Id[0] > 0L)
			{
				client=repo.findByIdClient(Id[0]);
			}			
		}		
		model.addAttribute("kontent", kontent);
        model.addAttribute("clients", clients);
		model.addAttribute("client", client);
		model.addAttribute("op", "sor");  //sorteerimine
		model.addAttribute("order", orde);
		model.addAttribute("id", Id[0]);
		model.addAttribute("geturl","customers"); //klientide tabelist valimise operatsioon
        return "customers";  //customers.html
	}

	
    @PostMapping(value= {"/customers"}) //@ModelAttribute is bound to the incoming form content
    public String customerspost(@Valid @ModelAttribute("client") Client klient,
    		//@ModelAttribute("kontent") Content kontent, //mitme ModelAttribute korral ei hakka BindingResult View vastu töötama
    		BindingResult bindingResult, 
    		@RequestParam(value = "sisu", required = false, defaultValue = "") String[] Sisu,
    		Model model,@RequestParam(value = "op", required = true, defaultValue = "sor") String[] Op
			,@RequestParam(value = "id", required = true, defaultValue = "0") Long[] Id) {
		List<Country> states= repo.findAllCountry();
		model.addAttribute("countries", states);		
		model.addAttribute("op", "sor"); //sorteerimine
		model.addAttribute("geturl","customers"); //klientide tabelist valimise operatsioon
		model.addAttribute("id", Id[0]);
		Content kontent = new Content(""); 
		List<Client> clients=new ArrayList<Client>();
    	if (bindingResult.hasErrors()) {
    		System.out.println("BINDING RESULT ERROR customerspost");
    		String err=Utils.veateade(bindingResult.getFieldErrors());
    	    if (!err.equals(""))
    	    {
    	    	model.addAttribute("viga", err);
    	    }
    	    
            if (Sisu.length > 0)
            {
            	kontent.setContent(Sisu[0]);
            	clients=repo.selectAllClients(Sisu[0]);
            	model.addAttribute("sisu", Sisu[0]);
            }
            else
            {
            	clients= repo.selectAllClients();
            	model.addAttribute("sisu", "");
            }
            model.addAttribute("kontent", kontent);
    	    model.addAttribute("clients", clients);
    		return "customers";
    	}
        Client client= new Client();
        client.setId(klient.getId()); //defaultValue = "0"
        client.setCountryname(klient.getCountryname()); 
        client.setAddress(klient.getAddress());
        client.setFirstname(klient.getFirstname());
        client.setLastname(klient.getLastname());
        client.setSecnumber(klient.getSecnumber());
        client.setPhone(klient.getPhone());
		if (Op[0].contentEquals("sel") ) //kliendi lisamine või muutmine
		{
			try
			{
				int succ=0; //0-ebaõnnestumine
				if (klient.getId().equals(0L)) //lisame uue kliendi
				{
					succ=repo.insertClient(client);
				}
				else //muudame andmeid
				{
					succ=repo.updateClient(client);
				}
				client=new Client();
				client.setId(0L);
			}
			catch (Exception ex)
			{
				System.out.println("BINDING RESULT ERROR customerspost:"+ex.getMessage());
				model.addAttribute("viga", ex.getMessage());
			}
			clients= repo.selectAllClients();
		}    	
		model.addAttribute("kontent", kontent);
        model.addAttribute("clients", clients);
		model.addAttribute("client", client);
        return "customers";    	
    }	
	
    
    @PostMapping(value= {"/customers/otsi"}) //@ModelAttribute is bound to the incoming form content
    public String customerspostotsi(@ModelAttribute("kontent") Content kontent,
    		BindingResult bindingResult, 
    		//HttpServletRequest request,
    		Model model,@RequestParam(value = "op", required = true, defaultValue = "sor") String[] Op
			,@RequestParam(value = "id", required = true, defaultValue = "0") Long[] Id) {
		List<Country> states= repo.findAllCountry();
		model.addAttribute("countries", states);
		model.addAttribute("op", "sor"); //sorteerimine
		model.addAttribute("geturl","customers"); //klientide tabelist valimise operatsioon
		model.addAttribute("id", Id[0]);
		Client client= new Client();
//		String vastus="customers";
//		System.out.println (request.getRequestURI());
//		if (request.getRequestURI().toLowerCase().equals("/neworder")) //neworder)
//		{						
//			vastus="neworder";
//		}		
		
		List<Client> clients=new ArrayList<Client>();
        client.setId(0L); //defaultValue = "0"
        client.setCountryname("Eesti");        
        model.addAttribute("sisu", kontent.getContent());
    	if (bindingResult.hasErrors()) {
    		System.out.println("BINDING RESULT ERROR customerspost");
    		String err=Utils.veateade(bindingResult.getFieldErrors());
    	    if (!err.equals(""))
    	    {
    	    	model.addAttribute("viga", err);
    	    }
    	    model.addAttribute("client", client);
    	    model.addAttribute("kontent", kontent);
    	    clients= repo.selectAllClients();
    	    model.addAttribute("clients", clients);
    		return "customers";
    	}
        
		if (kontent.getContent().contentEquals("")) //kliendi otsimine
		{
			clients= repo.selectAllClients();				
		}
		else
		{
			clients= repo.selectAllClients(kontent.getContent());
		}	
		model.addAttribute("kontent", kontent);
        model.addAttribute("clients", clients);
		model.addAttribute("client", client);
        return "customers";    	
    }	
    

}

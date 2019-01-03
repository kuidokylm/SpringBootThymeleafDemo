package com.example.demo.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Eclipse Run As Spring Boot App

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.repository.CountryJdbcRepository;

@Controller
public class DefaultController {

	@Autowired
	CountryJdbcRepository repo;
	
	@GetMapping(value= {"/","/default"})  // käivitamiseks kas http://localhost:8080 või http://localhost:8080/default
	public String vaikimisi(Model model,@RequestParam(value = "op", required = true, defaultValue = "0") String[] Op
			,@RequestParam(value = "id", required = true, defaultValue = "0") Long[] Id) {
		try
		{
			model.addAttribute("op", Op[0]);
			model.addAttribute("id", Id[0]);
			model.addAttribute("order", "ASC");
		}
		catch (Exception ex)
		{
			model.addAttribute("viga", ex.getMessage()); //kui mingi viga, siis /fragments/viga.html näitab sea,kui muutuja viga on seatud
		}
        return "default";  //default.html
    }

	
	@GetMapping(value= {"/primefaces"})  
	public String primefaces(HttpServletRequest request,
            HttpServletResponse httpServletResponse) {
		//return "redirect:" + request.getRequestURL().append(".xhtml").toString();  //ei tööta
        return "primefacesspringboot";  
    }

}

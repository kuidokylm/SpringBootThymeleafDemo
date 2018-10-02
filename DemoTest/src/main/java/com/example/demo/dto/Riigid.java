package com.example.demo.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.repository.CountryJdbcRepository;



// http://zetcode.com/springboot/repository/

@RestController 
public class Riigid {
	
	@Autowired
	CountryJdbcRepository repo;
	
	
	@RequestMapping(value= {"/riik"}, method = RequestMethod.GET, produces = {"application/JSON"})  //tagastab puhta JSON-i
	@ResponseBody  //@RestController combines @Controller and @ResponseBody, two annotations that results in web requests returning data rather than a view.
    public Country riik(@RequestParam(value = "name", required = true, defaultValue = "Eesti") String[] Name) {      

		//CountryJdbcRepository repo = new CountryJdbcRepository();
		Country riik=repo.findByNameCountry(Name[0]);
		if (riik == null)
		{
			riik=new Country(Name[0],"EUR");
		}
		return riik;
    }

	@RequestMapping(value= {"/riigid"}, method = RequestMethod.GET, produces = {"application/JSON"})  //tagastab puhta JSON-i
	@ResponseBody  //@RestController combines @Controller and @ResponseBody, two annotations that results in web requests returning data rather than a view.
    public List<Country> riigid() {      

		return repo.selectAllCountry();
    }
	
	
}

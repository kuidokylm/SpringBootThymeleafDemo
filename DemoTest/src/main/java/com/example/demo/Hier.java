package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

//https://springframework.guru/spring-requestmapping-annotation/
//Testimiseks

@RestController  //shorthand for @Controller and @ResponseBody rolled together
@RequestMapping("/hier")
public class Hier {

	//vaikemeetod http://localhost:8080/hier päringule
	@RequestMapping(method = RequestMethod.GET, produces = {"application/JSON"})
	//@ResponseBody
    public String hier(@RequestHeader HttpHeaders headers) {       
		//return "hier: "+headers.getFirst(HttpHeaders.USER_AGENT);
		return headers.getFirst(HttpHeaders.USER_AGENT);
    }
	
	
	@RequestMapping(value= {"/h2"}, method = RequestMethod.GET, produces = {"application/JSON"})  //tagastab puhta JSON-i
	@ResponseBody  //@RestController combines @Controller and @ResponseBody, two annotations that results in web requests returning data rather than a view.
    public String hier2(@RequestHeader HttpHeaders headers) {       
		return "hier/h2: "+headers.getFirst(HttpHeaders.USER_AGENT);
    }

	@RequestMapping(value= {"/h4"}, method = RequestMethod.GET)  
    public String hier4(@RequestHeader HttpHeaders headers) {       
		return "hier/h4: "+headers.getFirst(HttpHeaders.USER_AGENT);
    }
	
	@RequestMapping(value="/h2/*", method = RequestMethod.GET)  //@RequestParam binds the value of the query string parameter name into 
    public String hier3(@RequestHeader HttpHeaders headers) {       
		return "hier/h2/*: "+headers.getFirst(HttpHeaders.USER_AGENT);
    }

	@RequestMapping(value="/h1", method = RequestMethod.GET)  //required = true  parameeter name peab olema, kui pole võetakse defaultValue
    public String hier1(@RequestHeader HttpHeaders headers,@RequestParam(value = "name", required = true, defaultValue = "Jaan") String[] Name) {       
		return "hier1/: "+Name[0]+" - "+headers.getFirst(HttpHeaders.USER_AGENT);
    }
	
	// Päring tuleb siia  
	// http://localhost:8080/hier/h5?name=Kuido
	@RequestMapping(value= {"/h5"}, method = RequestMethod.GET, produces = {"application/JSON"}, params = {"name=Kuido"})  //tagastab puhta JSON-i
	@ResponseBody
    public String hier51(@RequestHeader HttpHeaders headers,@RequestParam(value = "name") String[] Name) {       
		return "hier/h5_1: "+Name[0];
    }

	//Päringud tulevad siia  
	// http://localhost:8080/hier/h5
	// http://localhost:8080/hier/h5?name=Jaak
	@RequestMapping(value= {"/h5"}, method = RequestMethod.GET, produces = {"application/JSON"})  //tagastab puhta JSON-i
	@ResponseBody   
    public String hier52(@RequestHeader HttpHeaders headers,@RequestParam(value = "name", required = true, defaultValue = "Jaan") String[] Name) {       
		return "hier/h5_2: "+Name[0];
    }

	//Päringud tulevad siia  
	// http://localhost:8080/hier/h6/Jaak
	// http://localhost:8080/hier/h6/Juuli
	//  http://localhost:8080/hier/h6/Juuli?name=Kuusk  //kui name defaultValue on olemas, läheb ta kasutusse
	@RequestMapping(value= {"/h6/{nimi}"}, method = RequestMethod.GET, produces = {"application/JSON"})  //tagastab puhta JSON-i
	@ResponseBody   
    public String hier6(@PathVariable String nimi,@RequestHeader HttpHeaders headers,@RequestParam(value = "name", required = true, defaultValue = "Kask") String[] Name) {       
		return "hier/h6: "+nimi+" "+Name[0];
    }

	
	// http://localhost:8080/hier/h7?pnimi=Kuusk&enimi=Margus
	// http://localhost:8080/hier/h8?pnimi=Kuusk&enimi=Margus
	@RequestMapping(value= {"/h7","/h8"}, method = {RequestMethod.GET})  //required = true  parameeter name peab olema, kui pole võetakse defaultValue
    public String hier7(@RequestParam(value = "enimi", required = true, defaultValue = "Jaan") String[] Eesnimi
    		,@RequestParam(value = "pnimi", required = true, defaultValue = "Tamm") String[] Perekonnanimi) {       
		return "hier7_8/: "+Eesnimi[0]+" - "+Perekonnanimi[0];
    }

	// http://localhost:8080/hier/h9/
	// http://localhost:8080/hier/h9/Jaak
	// http://localhost:8080/hier/h9/Mati
	// http://localhost:8080/hier/h9/Kalle/Malle
	// http://localhost:8080/hier/h9/Kalle/Malle/Kulle
	@RequestMapping(value= {"/h9/**"}, method = {RequestMethod.GET})  //required = true  parameeter name peab olema, kui pole võetakse defaultValue
    public String hier9(HttpServletRequest request) {  
		//String Url = (String)request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		String Url = request.getRequestURI();
		return "hier9/: "+Url;
    }

	@RequestMapping(value= {"/h10"}, method = {RequestMethod.GET})  //required = true  parameeter name peab olema, kui pole võetakse defaultValue
    public Map<String, Object> hier10(HttpServletRequest request) {  
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", request.getAttribute("javax.servlet.error.status_code"));
        map.put("reason", request.getAttribute("javax.servlet.error.message"));
        map.put("SessionId", request.getRequestedSessionId());
        map.put("ServletPath", request.getServletPath());
        return map;
    }
	
}

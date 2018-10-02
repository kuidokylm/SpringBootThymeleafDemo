package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Utils;
import com.example.demo.dto.Product;
import com.example.repository.CountryJdbcRepository;

@Controller
public class ProductsController {

	@Autowired
	CountryJdbcRepository repo;

	@GetMapping(value= {"/products"})  
	public String customers(Model model,@RequestParam(value = "op", required = true, defaultValue = "sor") String[] Op
			,@RequestParam(value = "order", required = true, defaultValue = "ASC") String[] Asc
			,@RequestParam(value = "id", required = true, defaultValue = "0") Long[] Id
			,@RequestParam(value = "sort", required = true, defaultValue = "name") String[] Sort) {
        Product product= new Product();
        product.setId(0L); //defaultValue = "0"   
        product.setBaseprice(1.0f);
        Calendar cal = Calendar.getInstance();
        product.setReleasedate(cal.getTime());
        List<Product> products= repo.selectAllProducts();
        String orde="ASC";
        if (Asc[0].contentEquals("ASC"))
        {
        	orde="DESC";
        }
		if (Op[0].equals("sor") ) //tabeli sorteerimine
		{
			products=Utils.sortProducts(products, Sort[0], orde);
		}		
		if (Op[0].equals("sel") ) //tabelist toote valik
		{
			if (Id[0] > 0L)
			{
				product=repo.findByIdProduct(Id[0]);
			}			
		}		
        model.addAttribute("products", products);
		model.addAttribute("product", product);
		model.addAttribute("op", "sor"); //sorteerimine
		model.addAttribute("order", orde);
		model.addAttribute("id", Id[0]);
		model.addAttribute("geturl","products"); //klientide tabelist valimise operatsioon
        return "products";  //products.html
	}

    @PostMapping(value= {"/products"}) //@ModelAttribute is bound to the incoming form content
    public String productspost(@Valid @ModelAttribute("product") Product toode, BindingResult bindingResult, 
    		Model model,@RequestParam(value = "op", required = true, defaultValue = "0") String[] Op
			,@RequestParam(value = "id", required = true, defaultValue = "0") Long[] Id) {
		List<Product> products=new ArrayList<Product>();
        Product product= new Product();
        product.setId(toode.getId()); //defaultValue = "0"
        product.setName(toode.getName()); 
        product.setBarcode(toode.getBarcode());
        product.setBaseprice(toode.getBaseprice());
        product.setDescription(toode.getDescription());
        product.setReleasedate(toode.getReleasedate());
		model.addAttribute("op", "sor"); //sorteerimine
		model.addAttribute("id", Id[0]);
		model.addAttribute("geturl","products"); //toodete tabelist valimise operatsioon

		if (bindingResult.hasErrors()) {
    		System.out.println("BINDING RESULT ERROR productspost");
    		String err=Utils.veateade(bindingResult.getFieldErrors());
    	    if (!err.equals(""))
    	    {
    	    	model.addAttribute("viga", err);
    	    }
    	    products= repo.selectAllProducts();
    	    model.addAttribute("products", products);
    		return "products";
    	}
		if (Op[0].equals("sel") ) //toote lisamine või muutmine
		{
			try
			{
				int succ=0; //0-ebaõnnestumine
				if (toode.getId().equals(0L)) //lisame uue kliendi
				{
					succ=repo.insertProduct(product);
				}
				else //muudame andmeid
				{					
					succ=repo.updateProduct(product);
				}
				product=new Product();
				product.setId(0L);
				product.setBaseprice(1.0f);
		        Calendar cal = Calendar.getInstance();
		        product.setReleasedate(cal.getTime());
			}
			catch (Exception ex)
			{
				System.out.println("BINDING RESULT ERROR customerspost:"+ex.getMessage());
				model.addAttribute("viga", ex.getMessage());
			}
		}    			
		products= repo.selectAllProducts();
        model.addAttribute("products", products);
		model.addAttribute("product", product);
        return "products";  //products.html  	
    }	

	
}

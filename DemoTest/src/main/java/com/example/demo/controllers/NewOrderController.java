package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Content;
import com.example.demo.Order;
import com.example.demo.Utils;
import com.example.demo.dto.Client;
import com.example.demo.dto.Orders;
import com.example.demo.dto.Product;
import com.example.repository.CountryJdbcRepository;

@Controller
public class NewOrderController {

	@Autowired
	CountryJdbcRepository repo;

	@GetMapping(value= {"/neworder"})  
	public String customers(Model model,@RequestParam(value = "op", required = true, defaultValue = "0") String[] Op
			,@RequestParam(value = "order", required = true, defaultValue = "ASC") String[] Asc
			,@RequestParam(value = "id", required = true, defaultValue = "0") Long[] Id
			,@RequestParam(value = "tid", required = true, defaultValue = "0") Long[] Tid		
			,@RequestParam(value = "sisu", required = false, defaultValue = "") String[] Sisu
			,@RequestParam(value = "sort", required = true, defaultValue = "lastname") String[] Sort) {
		List<Product> products=new ArrayList<Product>(); 
		Product product=new Product(); 
		product.setId(0L);
		model.addAttribute("products", products);	
		Content kontent = new Content("");
		model.addAttribute("kontent", kontent);
		Orders tellimus = new Orders();
		tellimus.setClientid(0L);
		tellimus.setProductid(0L);
		tellimus.setCurrencyrate(1.0f);
		Client client = new  Client();
		if (Id[0] > 0)  //mingi klient on valitud
		{
			client=repo.findByIdClient(Id[0]);
			String valuuta=repo.findCountryCurrency(client.getCountryname());
			float rat = Utils.getCurrencyRate(valuuta);
			tellimus.setCurrencyrate(rat);
		}
		
		List<Client> clients = new ArrayList<Client>();
        String orde="DESC";
        model.addAttribute("op", Op[0]);
        if (Asc[0].contentEquals("DESC"))
        {
        	orde="ASC";
        }
		if (Op[0].contentEquals("0") ) //esimene samm, kliendi sorteerimine
		{			
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
			clients=Utils.sortClients(clients, Sort[0], orde);
		}
        
		if (Op[0].contentEquals("1") ) //tabelist kliendi valik
		{
			products=repo.selectAllProducts();
			model.addAttribute("op", "2");
		}
		if (Op[0].contentEquals("2") ) //toote sorteerimine
		{
			products=repo.selectAllProducts();
			products=Utils.sortProducts(products, Sort[0], orde);
			if (Id[0] > 0) //klient on valitud
			{				
				tellimus.setClientid(client.getId());
//				String valuuta=repo.findCountryCurrency(client.getCountryname());
//				float rat = Utils.getCurrencyRate(valuuta);
//				tellimus.setCurrency(valuuta);
//				tellimus.setCurrencyrate(rat);
			}			
		}
		if (Op[0].contentEquals("3") ) //toote valik
		{
			float rate=1.0f;
			String valuuta="EUR";
			if (Id[0] > 0)
			{
				//Client client=repo.findByIdClient(Id[0]);
				tellimus.setClientid(Id[0]);
				valuuta=repo.findCountryCurrency(client.getCountryname());
				rate = Utils.getCurrencyRate(valuuta);
				tellimus.setCurrencyrate(rate);				
			}
			if (Tid[0] > 0)
			{				
				product=repo.findByIdProduct(Tid[0]);
				tellimus.setProductid(Tid[0]);
				tellimus.setCpprice(product.getBaseprice()/rate);
				tellimus.setAmount(1.0f);				
		        Calendar cal = Calendar.getInstance();
		        tellimus.setTransactiondate(cal.getTime());
		        model.addAttribute("valuuta",valuuta);
			}	
			
			model.addAttribute("op", "4"); //järgmine samm
		}
		model.addAttribute("product", product);
		model.addAttribute("client",client);
		model.addAttribute("products", products);
        model.addAttribute("clients", clients);
        model.addAttribute("id", Id[0]);
		model.addAttribute("order", orde);
		model.addAttribute("tellimus", tellimus);
		model.addAttribute("geturl","neworder"); //klientide tabelist valimise operatsioon
        return "neworder";  //neworder.html
	}

	
    @PostMapping(value= {"/neworder"}) //@ModelAttribute is bound to the incoming form content
    public String neworderpost(@Valid @ModelAttribute("tellimus") Orders order, BindingResult bindingResult, 
    		Model model,@RequestParam(value = "op", required = true, defaultValue = "0") int[] Op
    		,@RequestParam(value = "tid", required = false, defaultValue = "0") Long[] Tid
			,@RequestParam(value = "id", required = true, defaultValue = "0") Long[] Id) {
		List<Product> products=new ArrayList<Product>();
		List<Client> clients = new ArrayList<Client>();
    	if (bindingResult.hasErrors()) {
    		System.out.println("BINDING RESULT ERROR neworderpost");
    		String err=Utils.veateade(bindingResult.getFieldErrors());
    	    if (!err.equals(""))
    	    {
    	    	model.addAttribute("viga", err);
    	    }
    	    Product product=new Product(); 
    		product.setId(0L);
    		if (Tid[0] > 0)
			{				
				product=repo.findByIdProduct(Tid[0]);
				model.addAttribute("product", product);
			}
    		Content kontent = new Content("");
    		model.addAttribute("kontent", kontent);
    	    products= repo.selectAllProducts();
    	    model.addAttribute("products", products);    	    
			clients= repo.selectAllClients();			
			model.addAttribute("clients", clients);
			//model.addAttribute("tellimus", order);
			Client client = repo.findByIdClient(order.getClientid());
			model.addAttribute("client", client);
			model.addAttribute("op", Op[0]);
			model.addAttribute("id", Id[0]);
			model.addAttribute("geturl","neworder"); //klientide tabelist valimise operatsioon
			model.addAttribute("order", "ASC");			
    		return "neworder";
    	}

		if (Op[0]==0 ) //kliendi valik
		{
			try
			{
				clients= repo.selectAllClients();	
			}
			catch (Exception ex)
			{
				System.out.println("BINDING RESULT ERROR neworderpost:"+ex.getMessage());
				model.addAttribute("viga", ex.getMessage());
			}
		}

		if (Op[0]==4 ) //Tellimuse tegemine
		{
			try
			{
				Orders ord = new Orders();
				ord.setAmount(order.getAmount());
				ord.setClientid(order.getClientid());
				
//				System.out.println("Klient ID "+order.getClientid());
//				System.out.println("Kurss "+order.getCurrencyrate());
				
				ord.setProductid(order.getProductid());
				String ff= String.format("%.2f", order.getAmount()*order.getCpprice());
				ord.setCpprice(Float.parseFloat(ff.replace(',','.')));
				ord.setTransactiondate(order.getTransactiondate());
				ord.setCurrencyrate(order.getCurrencyrate());
				int succ = repo.insertOrder(ord);	
				
				Client client = repo.findByIdClient(order.getClientid());
				
		        List<Order> orders= repo.selectAllClientOrders(order.getClientid());
		        String orde="ASC";
		        model.addAttribute("client", client);
		        model.addAttribute("orders", orders);
				model.addAttribute("op", "sor"); //sorteerimine
				model.addAttribute("order", orde);
				model.addAttribute("id", order.getClientid());
				model.addAttribute("geturl","orders"); //tellimuste tabelist valimise operatsioon
		        return "orders";  //orders.html
				
			}
			catch (Exception ex)
			{
				System.out.println("BINDING RESULT ERROR neworderpost:"+ex.getMessage());
				model.addAttribute("viga", ex.getMessage());
			}
		}    	
		
		Orders tellimus = new Orders();
		tellimus.setClientid(0L);
		tellimus.setProductid(0L);
		tellimus.setCurrencyrate(order.getCurrencyrate());
		model.addAttribute("tellimus", tellimus);		
		model.addAttribute("clients", clients);
		products=repo.selectAllProducts();
        model.addAttribute("products", products);
		model.addAttribute("op", 0);
		model.addAttribute("id", Id[0]);
		model.addAttribute("geturl","neworder"); //klientide tabelist valimise operatsioon
		model.addAttribute("order", "ASC");
        return "neworder";    	
    }	

    
    @PostMapping(value= {"/neworder/otsi"}) //@ModelAttribute is bound to the incoming form content
    public String neworderpostotsi(@ModelAttribute("kontent") Content kontent, BindingResult bindingResult,     		
    		Model model,@RequestParam(value = "op", required = true, defaultValue = "0") int[] Op
			,@RequestParam(value = "id", required = true, defaultValue = "0") Long[] Id) {
		List<Product> products=new ArrayList<Product>();
		List<Client> clients = new ArrayList<Client>();
		Orders tellimus = new Orders();
		tellimus.setClientid(0L);
		tellimus.setProductid(0L);
		tellimus.setCurrencyrate(1.0f);
		model.addAttribute("sisu", kontent.getContent());
    	if (bindingResult.hasErrors()) {
    		System.out.println("BINDING RESULT ERROR neworderpostotsi");
    		String err=Utils.veateade(bindingResult.getFieldErrors());
    	    if (!err.equals(""))
    	    {
    	    	model.addAttribute("viga", err);
    	    }
    	    products= repo.selectAllProducts();
    	    model.addAttribute("products", products);    	    
			clients= repo.selectAllClients();			
			model.addAttribute("clients", clients);
			model.addAttribute("tellimus", tellimus);
			model.addAttribute("op", 0);
			model.addAttribute("id", Id[0]);
			model.addAttribute("geturl","neworder"); //klientide tabelist valimise operatsioon
			model.addAttribute("order", "ASC");			
    		return "neworder";
    	}
        
		if (Op[0]==0 ) //kliendi valik
		{
			try
			{				
				clients= repo.selectAllClients(kontent.getContent());	
			}
			catch (Exception ex)
			{
				System.out.println("BINDING RESULT ERROR neworderpostotsi:"+ex.getMessage());
				model.addAttribute("viga", ex.getMessage());
			}
		}
		
		model.addAttribute("tellimus", tellimus);		
		model.addAttribute("clients", clients);
		products=repo.selectAllProducts();
        model.addAttribute("products", products);
		model.addAttribute("op", 0);
		model.addAttribute("id", Id[0]);
		model.addAttribute("geturl","neworder"); //klientide tabelist valimise operatsioon
		model.addAttribute("order", "ASC");
        return "neworder";    	
    }	
    
	
}

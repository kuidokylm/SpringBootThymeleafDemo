package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Order;
import com.example.demo.Utils;
import com.example.demo.dto.Client;
import com.example.repository.CountryJdbcRepository;

@Controller
public class OrdersController {

	@Autowired
	CountryJdbcRepository repo;

	@GetMapping(value= {"/orders"})  
	public String orders(Model model,@RequestParam(value = "op", required = true, defaultValue = "sor") String[] Op
			,@RequestParam(value = "order", required = true, defaultValue = "ASC") String[] Asc
			,@RequestParam(value = "id", required = true, defaultValue = "0") Long[] Id
			,@RequestParam(value = "sort", required = true, defaultValue = "transactiondate") String[] Sort) { 
		List<Order> orders = new ArrayList<Order>();
		if (Id[0] == 0)
		{
			orders= repo.selectAllOrders();
		}
		else
		{
			orders= repo.selectAllClientOrders(Id[0]);
			Client client = repo.findByIdClient(Id[0]);
			model.addAttribute("client", client);
		}		
        String orde="ASC";
        if (Asc[0].contentEquals("ASC"))
        {
        	orde="DESC";
        }
		if (Op[0].equals("sor") ) //tabeli sorteerimine
		{
			orders=Utils.sortOrders(orders, Sort[0], orde);
		}		
        model.addAttribute("orders", orders);
		model.addAttribute("op", "sor"); //sorteerimine
		model.addAttribute("order", orde);
		model.addAttribute("id", Id[0]);
		model.addAttribute("geturl","orders"); //tellimuste tabelist valimise operatsioon
        return "orders";  //orders.html
	}

	
}

package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.validation.FieldError;

import com.example.demo.dto.Client;
import com.example.demo.dto.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
	
	
	public static String veateade(List<FieldError> errors)
	{
    	ArrayList<String> vead = new ArrayList<String>(Arrays.asList("Size", "NotNull","NotBlank","PastOrPresent","PositiveOrZero","Max")); 
    	StringBuilder sb= new StringBuilder("");
	    for (FieldError error : errors ) {
	    	//@Size(min=2, message="Eesnimi vähemalt 2 tähte")
	    	//seda laadi vead ilmuvad html koodis th:if="${#fields.hasErrors('firstname')}" th:errors="*{firstname}"
	    	if (!vead.contains(error.getCode()))  
	    	{
	    		sb.append(error.getField()+" "+error.getDefaultMessage()+" "+error.getCode());
	    	}
	        System.out.println (error.getObjectName() + " - " + error.getDefaultMessage());    	       
	    }
	    return sb.toString().trim();

	}
	
	public static List<Client> sortClients(List<Client> kliendid, String field, String orde){
		
		List<Client> clients = new ArrayList<Client>();
		clients.addAll(kliendid);
		
		if (field.contentEquals("firstname"))
		{
			if (orde.contentEquals("ASC"))
			{
				clients.sort((o1, o2) -> o1.getFirstname().compareTo(o2.getFirstname()));
			}
			else
			{
				clients.sort((o1, o2) -> o2.getFirstname().compareTo(o1.getFirstname()));
			}			
		}
		if (field.contentEquals("lastname"))
		{
			if (orde.contentEquals("ASC"))
			{
				clients.sort((o1, o2) -> o1.getLastname().compareTo(o2.getLastname()));
			}
			else
			{
				clients.sort((o1, o2) -> o2.getLastname().compareTo(o1.getLastname()));
			}			
		}
		if (field.contentEquals("phone"))
		{
			if (orde.contentEquals("ASC"))
			{
				clients.sort((o1, o2) -> o1.getPhone().compareTo(o2.getPhone()));
			}
			else
			{
				clients.sort((o1, o2) -> o2.getPhone().compareTo(o1.getPhone()));
			}			
		}
		if (field.contentEquals("address"))
		{
			if (orde.contentEquals("ASC"))
			{
				clients.sort((o1, o2) -> o1.getAddress().compareTo(o2.getAddress()));
			}
			else
			{
				clients.sort((o1, o2) -> o2.getAddress().compareTo(o1.getAddress()));
			}			
		}
		if (field.contentEquals("secnumber"))
		{
			if (orde.contentEquals("ASC"))
			{
				clients.sort((o1, o2) -> o1.getSecnumber().compareTo(o2.getSecnumber()));
			}
			else
			{
				clients.sort((o1, o2) -> o2.getSecnumber().compareTo(o1.getSecnumber()));
			}			
		}
		if (field.contentEquals("countryname"))
		{
			if (orde.contentEquals("ASC"))
			{
				clients.sort((o1, o2) -> o1.getCountryname().compareTo(o2.getCountryname()));
			}
			else
			{
				clients.sort((o1, o2) -> o2.getCountryname().compareTo(o1.getCountryname()));
			}			
		}
		if (field.contentEquals("id"))
		{
			if (orde.contentEquals("ASC"))
			{
				clients.sort((o1, o2) -> o1.getId().compareTo(o2.getId()));
			}
			else
			{
				clients.sort((o1, o2) -> o2.getId().compareTo(o1.getId()));
			}			
		}

		return clients;
	}

	public static List<Product> sortProducts(List<Product> tooted, String field, String orde){
	
		List<Product> products = new ArrayList<Product>();
		products.addAll(tooted);
		if (field.contentEquals("name"))
		{
			if (orde.contentEquals("ASC"))
			{
				products.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
			}
			else
			{
				products.sort((o1, o2) -> o2.getName().compareTo(o1.getName()));
			}			
		}
		if (field.contentEquals("barcode"))
		{
			if (orde.contentEquals("ASC"))
			{
				products.sort((o1, o2) -> o1.getBarcode().compareTo(o2.getBarcode()));
			}
			else
			{
				products.sort((o1, o2) -> o2.getBarcode().compareTo(o1.getBarcode()));
			}			
		}
		if (field.contentEquals("baseprice"))
		{
			if (orde.contentEquals("ASC"))
			{
				Collections.sort(products, new Comparator<Product>() {
		            //@Override
		            public int compare(Product o1, Product o2) {
		                return Float.compare(o1.getBaseprice(), o2.getBaseprice());
		            }
		        });
			}
			else
			{
				Collections.sort(products, new Comparator<Product>() {
		            //@Override
		            public int compare(Product o1, Product o2) {
		                return Float.compare(o2.getBaseprice(), o1.getBaseprice());
		            }
		        });
				
			}			
		}
		if (field.contentEquals("releasedate"))
		{
			if (orde.contentEquals("ASC"))
			{
				products.sort((o1, o2) -> o1.getReleasedate().compareTo(o2.getReleasedate()));
			}
			else
			{
				products.sort((o1, o2) -> o2.getReleasedate().compareTo(o1.getReleasedate()));
			}			
		}
		if (field.contentEquals("id"))
		{
			if (orde.contentEquals("ASC"))
			{
				products.sort((o1, o2) -> o1.getId().compareTo(o2.getId()));
			}
			else
			{
				products.sort((o1, o2) -> o2.getId().compareTo(o1.getId()));
			}			
		}
		return products;
	}

	public static List<Order> sortOrders(List<Order> tellimised, String field, String orde){
		
		List<Order> orders = new ArrayList<Order>();
		orders.addAll(tellimised);
		
		if (field.contentEquals("name"))
		{
			if (orde.contentEquals("ASC"))
			{
				orders.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
			}
			else
			{
				orders.sort((o1, o2) -> o2.getName().compareTo(o1.getName()));
			}			
		}
		
		if (field.contentEquals("lastname"))
		{
			if (orde.contentEquals("ASC"))
			{
				orders.sort((o1, o2) -> o1.getLastname().compareTo(o2.getLastname()));
			}
			else
			{
				orders.sort((o1, o2) -> o2.getLastname().compareTo(o1.getLastname()));
			}			
		}

		if (field.contentEquals("firstname"))
		{
			if (orde.contentEquals("ASC"))
			{
				orders.sort((o1, o2) -> o1.getFirstname().compareTo(o2.getFirstname()));
			}
			else
			{
				orders.sort((o1, o2) -> o2.getFirstname().compareTo(o1.getFirstname()));
			}			
		}
		if (field.contentEquals("currency"))
		{
			if (orde.contentEquals("ASC"))
			{
				orders.sort((o1, o2) -> o1.getCurrency().compareTo(o2.getCurrency()));
			}
			else
			{
				orders.sort((o1, o2) -> o2.getCurrency().compareTo(o1.getCurrency()));
			}			
		}
		
		if (field.contentEquals("barcode"))
		{
			if (orde.contentEquals("ASC"))
			{
				orders.sort((o1, o2) -> o1.getBarcode().compareTo(o2.getBarcode()));
			}
			else
			{
				orders.sort((o1, o2) -> o2.getBarcode().compareTo(o1.getBarcode()));
			}			
		}
		if (field.contentEquals("cpprice"))
		{
			if (orde.contentEquals("ASC"))
			{
				Collections.sort(orders, new Comparator<Order>() {
		            public int compare(Order o1, Order o2) {
		                return Float.compare(o1.getCpprice(), o2.getCpprice());
		            }
		        });
			}
			else
			{
				Collections.sort(orders, new Comparator<Order>() {
		            //@Override
		            public int compare(Order o1, Order o2) {
		                return Float.compare(o2.getCpprice(), o1.getCpprice());
		            }
		        });
				
			}			
		}

		if (field.contentEquals("currencyrate"))
		{
			if (orde.contentEquals("ASC"))
			{
				Collections.sort(orders, new Comparator<Order>() {
		            public int compare(Order o1, Order o2) {
		                return Float.compare(o1.getCurrencyrate(), o2.getCurrencyrate());
		            }
		        });
			}
			else
			{
				Collections.sort(orders, new Comparator<Order>() {
		            //@Override
		            public int compare(Order o1, Order o2) {
		                return Float.compare(o2.getCurrencyrate(), o1.getCurrencyrate());
		            }
		        });				
			}			
		}		
		
		if (field.contentEquals("amount"))
		{
			if (orde.contentEquals("ASC"))
			{
				Collections.sort(orders, new Comparator<Order>() {
		            //@Override
		            public int compare(Order o1, Order o2) {
		                return Float.compare(o1.getAmount(), o2.getAmount());
		            }
		        });
			}
			else
			{
				Collections.sort(orders, new Comparator<Order>() {
		            //@Override
		            public int compare(Order o1, Order o2) {
		                return Float.compare(o2.getAmount(), o1.getAmount());
		            }
		        });
				
			}			
		}
		
		if (field.contentEquals("transactiondate"))
		{
			if (orde.contentEquals("ASC"))
			{
				orders.sort((o1, o2) -> o1.getTransactiondate().compareTo(o2.getTransactiondate()));
			}
			else
			{
				orders.sort((o1, o2) -> o2.getTransactiondate().compareTo(o1.getTransactiondate()));
			}			
		}
		if (field.contentEquals("id"))
		{
			if (orde.contentEquals("ASC"))
			{
				orders.sort((o1, o2) -> o1.getId().compareTo(o2.getId()));
			}
			else
			{
				orders.sort((o1, o2) -> o2.getId().compareTo(o1.getId()));
			}			
		}
		return orders;
	}
	
	
	public static float getCurrencyRate(String currency)
	{
		if (currency.contentEquals("EUR"))
		{
			return 1.0f;
		}
		else
		{
			float rate=1.0f;
			String https_url="https://free.currencyconverterapi.com/api/v6/convert?q="+currency+"_EUR&compact=y";
			URL url;
		      try {

			     url = new URL(https_url);
			     HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
					
			     con.setRequestMethod("GET");

					//add request header
					con.setRequestProperty("User-Agent", "Demo test");

					int responseCode = con.getResponseCode();
					
					System.out.println("\nSending 'GET' request to URL : " + url);
					System.out.println("Response Code : " + responseCode);

					BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
					String inputLine;
					StringBuffer response = new StringBuffer();

					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					in.close();
					System.out.println(response.toString());
					String jupp=response.toString();  //{"PLN_EUR":{"val":0.23401}}
					jupp=jupp.substring(jupp.indexOf(":{")+1,jupp.length()-1);  //:{"val":0.23401}
					System.out.println(currency+" Valuuta kurss: "+jupp);
					ObjectMapper mapper = new ObjectMapper();
					RateValue staff = mapper.readValue(jupp, RateValue.class);
					System.out.println(staff.getVal());
					rate=staff.getVal();
					
		      } catch (MalformedURLException e) {
		    	  System.out.println("MalformedURLException: "+e.getMessage());
		      } catch (IOException e) {
		    	  System.out.println("IOException: "+e.getMessage());
		      }
		      return rate;
		}
	}
}

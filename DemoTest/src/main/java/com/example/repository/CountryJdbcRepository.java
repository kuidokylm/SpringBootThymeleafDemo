package com.example.repository;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.Order;
import com.example.demo.dto.Client;
import com.example.demo.dto.Country;
import com.example.demo.dto.Orders;
import com.example.demo.dto.Product;

// https://dzone.com/articles/spring-boot-and-spring-jdbc-with-h2
// https://www.concretepage.com/spring-boot/spring-boot-jdbc-example

@Repository
public class CountryJdbcRepository {

	@Autowired
    JdbcTemplate jdbcTemplate;
	
public CountryJdbcRepository()
{
	
}
	
public Country findByNameCountry(String name) {
	
    return jdbcTemplate.queryForObject("SELECT countryname, currency FROM country WHERE countryname=?", new Object[] {name},
	  new BeanPropertyRowMapper<Country>(Country.class));	
}

public List<Country> findAllCountry() {   
   String sql = "SELECT countryname, currency FROM country ORDER BY countryname";
   RowMapper<Country> rowMapper = new CountryRowMapper();
   return this.jdbcTemplate.query(sql, rowMapper);
	
}

public List<Country> selectAllCountry() {   
	   String sql = "SELECT countryname, currency FROM country ORDER BY countryname";
	   RowMapper<Country> rowMapper = new BeanPropertyRowMapper<Country>(Country.class);
	   return this.jdbcTemplate.query(sql, rowMapper);		
	}

public List<Client> selectAllClients() {   
	   String sql = "SELECT id, address,firstname, lastname, phone, secnumber, countryname FROM client ORDER BY lastname";
	   RowMapper<Client> rowMapper = new BeanPropertyRowMapper<Client>(Client.class);
	   return this.jdbcTemplate.query(sql, rowMapper);		
	}

public List<Client> selectAllClients(String nimi) {
	   nimi=nimi.replace("%", "");
	   if (nimi == "")
	   {
		   return selectAllClients();
	   }
	   else
	   {
		   nimi="%"+nimi+"%";
		   String sql = "SELECT id, address,firstname, lastname, phone, secnumber, countryname FROM client "
		   		+ "WHERE firstname LIKE '"+nimi+"' OR lastname LIKE '"+nimi+"' OR address LIKE '"+nimi+"' OR phone LIKE '"+nimi+
		   		"' OR secnumber LIKE '"+nimi+"' OR countryname LIKE '"+nimi+"' ORDER BY lastname";
		   RowMapper<Client> rowMapper = new BeanPropertyRowMapper<Client>(Client.class);
		   return this.jdbcTemplate.query(sql, rowMapper);
	   }
	}


public Client findByIdClient(Long id) {   
	   return jdbcTemplate.queryForObject("SELECT id, address,firstname, lastname, phone, secnumber, countryname FROM client WHERE id=?", new Object[] {id},
				  new BeanPropertyRowMapper<Client>(Client.class));
	}

public int updateClient(Client client) {
    return jdbcTemplate.update("update client set address = ?, countryname = ?, firstname = ?, lastname = ?, phone = ?, secnumber = ? where id = ?",
        new Object[] {
            client.getAddress(), client.getCountryname() ,client.getFirstname(), client.getLastname(), client.getPhone(),
            client.getSecnumber(),client.getId()
        });        
	}

public int insertClient(Client client) {
	
	String ma=(String)jdbcTemplate.queryForObject("SELECT MAX(id) FROM client", String.class);
	if (ma == null) //üldse esimene 
	{
		ma="0";
	}
	Long max=Long.valueOf(ma)+1L;
    return jdbcTemplate.update("insert into client (address, countryname , firstname , lastname , phone , secnumber,id) values(?, ?, ?, ?, ?, ?,?)",
    		
        new Object[] {
        		client.getAddress(), client.getCountryname() ,client.getFirstname(), client.getLastname(), client.getPhone(),client.getSecnumber(),max
        });
}

public String findCountryCurrency(String name) {
	
	String valuuta=(String)jdbcTemplate.queryForObject("SELECT currency FROM country WHERE countryname = ?", new Object[] {name}, String.class);
    return valuuta;	
}


public List<Product> selectAllProducts() {   
	   String sql = "SELECT id,name,barcode, description, releasedate, baseprice FROM product ORDER BY name";
	   RowMapper<Product> rowMapper = new BeanPropertyRowMapper<Product>(Product.class);
	   return this.jdbcTemplate.query(sql, rowMapper);		
	}


public Product findByIdProduct(Long id) {	
    return jdbcTemplate.queryForObject("SELECT id,name,barcode, description, releasedate, baseprice FROM product WHERE id=?", new Object[] {id},
	  new BeanPropertyRowMapper<Product>(Product.class));	
}


public int updateProduct(Product product) {
	
    return jdbcTemplate.update("update product set barcode = ?, name = ?, description = ?, baseprice = ? where id = ?",
        new Object[] {
            product.getBarcode(), product.getName(), product.getDescription(),
            product.getBaseprice(), product.getId()
        });        
	}

public int insertProduct(Product product) {
	
	String ma=(String)jdbcTemplate.queryForObject("SELECT MAX(id) FROM product", String.class);
	if (ma == null) //üldse esimene 
	{
		ma="0";
	}
	Long max=Long.valueOf(ma)+1L;
    return jdbcTemplate.update("insert into product (barcode, description , name , baseprice , releasedate,id) values(?, ?, ?, ?, ?, ?)",
    		
        new Object[] {
        		product.getBarcode(), product.getDescription() ,product.getName(), product.getBaseprice(),product.getReleasedate(),max
        });
}


public int insertOrder(Orders order) {
	
	String ma=(String)jdbcTemplate.queryForObject("SELECT MAX(id) FROM orders", String.class);
	if (ma == null) //üldse esimene 
	{
		ma="0";
	}
	Long max=Long.valueOf(ma)+1L;
    return jdbcTemplate.update("insert into orders (amount, cpprice , clientid , productid , transactiondate,id, currencyrate) values(?, ?, ?, ?, ?, ?, ?)",
    		
        new Object[] {
        		order.getAmount() ,order.getCpprice(), order.getClientid(), order.getProductid(), order.getTransactiondate(),max, order.getCurrencyrate()
        });
}


public List<Order> selectAllOrders() {   
	   String sql = "SELECT orders.id, orders.amount, orders.clientid, orders.productid, orders.transactiondate, orders.cpprice,"
	   		+ "product.name, product.barcode, client.firstname, client.lastname, country.currency, orders.currencyrate FROM orders INNER JOIN product "
	   		+ "ON (orders.productid = product.id) INNER JOIN client ON (orders.clientid = client.id) "
	   		+ "INNER JOIN country ON (client.countryname = country.countryname) ORDER BY orders.transactiondate DESC";
	   RowMapper<Order> rowMapper = new BeanPropertyRowMapper<Order>(Order.class);
	   return this.jdbcTemplate.query(sql, rowMapper);		
}

public List<Order> selectAllClientOrders(Long id) {   
	   String sql = "SELECT orders.id, orders.amount, orders.clientid, orders.productid, orders.transactiondate, orders.cpprice,"
	   		+ "product.name, product.barcode, client.firstname, client.lastname, country.currency, orders.currencyrate FROM orders INNER JOIN product "
	   		+ "ON (orders.productid = product.id) INNER JOIN client ON (orders.clientid = client.id) "
	   		+ "INNER JOIN country ON (client.countryname = country.countryname) WHERE orders.clientid = ? ORDER BY orders.transactiondate DESC";
	   RowMapper<Order> rowMapper = new BeanPropertyRowMapper<Order>(Order.class);
	   return this.jdbcTemplate.query(sql, new Object[] {id}, rowMapper);		
}

}

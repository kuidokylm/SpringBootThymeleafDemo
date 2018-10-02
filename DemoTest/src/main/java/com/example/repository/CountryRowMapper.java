package com.example.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.dto.Country;

public class CountryRowMapper implements RowMapper<Country> {
   @Override
   public Country mapRow(ResultSet row, int rowNum) throws SQLException {
	   Country co = new Country();
	   co.setCurrency(row.getString("currency"));
	   co.setCountryname(row.getString("countryname"));
	   return co;
   }
}

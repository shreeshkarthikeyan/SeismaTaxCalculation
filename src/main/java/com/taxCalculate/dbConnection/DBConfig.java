package com.taxCalculate.dbConnection;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

@Service
public class DBConfig {
	
	@Bean
	DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("org.h2.Driver");
		driverManagerDataSource.setUrl("jdbc:h2:tcp://localhost/~/taxCalculate");
		driverManagerDataSource.setUsername("sa");
		driverManagerDataSource.setPassword("");
		return driverManagerDataSource;
		
	}

}

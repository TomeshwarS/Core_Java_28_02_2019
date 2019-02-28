package com.ojs.mysql.connection;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class MySqlConnection {
	
	
	@Autowired
	Environment environment;
	
	

	
	
	
	public  Connection getMysqlConnection() {
		Connection con = null;
		try {

			Class.forName(environment.getProperty("spring.datasource.driver-class-name")); 
			con = DriverManager.getConnection(environment.getProperty("spring.datasource.url"),environment.getProperty("spring.datasource.username"),environment.getProperty("spring.datasource.password"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

}

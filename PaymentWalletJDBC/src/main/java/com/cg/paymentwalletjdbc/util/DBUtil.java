package com.cg.paymentwalletjdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import oracle.jdbc.driver.OracleDriver;

public class DBUtil {
	public static Connection getConnection() {
		Connection connection=null;
	OracleDriver driver = new OracleDriver();
	try {
		DriverManager.registerDriver(driver);
		connection  = DriverManager.getConnection("jdbc:oracle:thin:@10.109.2.187:1521:XE","system","Capgemini123");
		System.out.println(connection);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return connection;
	
	}
}

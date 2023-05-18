package com.qmik.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public static Connection getConnection(String type, String url, String user, String pw) {
		DriverLoader.load(type);
		
		try {
			return DriverManager.getConnection(url, user, pw);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

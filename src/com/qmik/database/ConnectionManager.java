package com.qmik.database;

import java.sql.*;

public class ConnectionManager {
	
	public static Connection getConnection(String type, String url, String user, String pw) {
		DriverLoader.load(type);
		
		try {
			return DriverManager.getConnection(url, user, pw);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void close(AutoCloseable... resources) {
		for (AutoCloseable resource : resources) {
			try {
				resource.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}

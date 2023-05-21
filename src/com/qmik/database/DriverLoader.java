package com.qmik.database;

public class DriverLoader {

	public static void load(String type) {
		switch (type) {
		case "mysql":
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case "postgresql":
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			break;
		}
	}

}

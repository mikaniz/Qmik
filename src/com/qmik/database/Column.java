package com.qmik.database;

import java.sql.Types;

public class Column {
	
	private String name;
	private String type;
	
	public Column(String name, int type) {
		setName(name);
		setType(type);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		if (name == null || "".equals(name.trim())) return;
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(int type) {
		if (type == Types.INTEGER) this.type = "Integer";
		else if (type == Types.FLOAT || type == Types.DOUBLE || type == Types.DECIMAL) this.type = "Double";
		else if (type == Types.VARCHAR || type == Types.CHAR || type == Types.LONGVARCHAR) this.type = "String";
	}
	
}

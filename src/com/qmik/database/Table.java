package com.qmik.database;

import java.util.ArrayList;
import java.util.List;

public class Table {
	
	private String name;
	private List<Column> columns;
	
	public Table(String name) {
		setName(name);
		columns = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		if (name == null || "".equals(name.trim())) return;
		this.name = name;
	}
	
	public List<Column> getColumns() {
		return columns;
	}
	
	public void addColumn(Column column) {
		columns.add(column);
	}
	
}

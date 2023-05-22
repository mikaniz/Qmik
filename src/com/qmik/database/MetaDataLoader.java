package com.qmik.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MetaDataLoader {
	
	public static List<Table> load(Database database) {
		List<Table> tables = new ArrayList<>();
		
		Connection connection = null;
		ResultSet tableSet = null;
		ResultSet columnSet = null;
		
		try {
			connection = ConnectionManager.getConnection(database.getType(), database.getUrl(), database.getUser(), database.getPassword());
			DatabaseMetaData metaData = connection.getMetaData();
			
			tableSet = metaData.getTables(database.getCatalog(), "PUBLIC", null, new String[] { "TABLE", "IGNORE_DELETED" });
			while (tableSet.next()) {
				Table table = new Table(tableSet.getString("TABLE_NAME"));
				tables.add(table);
				
				columnSet = metaData.getColumns(database.getCatalog(), null, table.getName(), null);
				while (columnSet.next()) table.addColumn(new Column(columnSet.getString("COLUMN_NAME"), columnSet.getInt("DATA_TYPE")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(columnSet, tableSet, connection);
		}
		
		return tables;
	}
	
}

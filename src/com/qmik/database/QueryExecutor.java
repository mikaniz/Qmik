package com.qmik.database;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qmik.query.Condition;
import com.qmik.query.SelectQuery;

public class QueryExecutor {
	
	public static Map<String, Object> execute(SelectQuery query) {
		Map<String, Object> result = new HashMap<>();
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = ConnectionManager.getConnection(query.driver, query.url, query.user, query.password);
			statement = connection.prepareStatement(query.toString());
			
			List<Condition<?>> conditions = query.getConditions();
			int length = conditions.size();
			for (int i = 0; i < length; i++) statement.setObject(i + 1, conditions.get(i).getValue());
			
			resultSet = statement.executeQuery();
			if (!resultSet.next()) return null;
			
			ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				String column = metaData.getColumnName(i);
				result.put(column, resultSet.getObject(column));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(resultSet, statement, connection);
		}
		
		return result;
	}
	
}

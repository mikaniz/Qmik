package com.qmik.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.qmik.database.QueryExecutor;
import com.qmik.model.Model;

public class SelectQuery implements ConditionQuery {
	
	public String driver;
	public String url;
	public String user;
	public String password;
	public String table;
	
	List<String> conditionType;
	List<Condition<?>> conditions;
	
	public SelectQuery(Class<? extends Model> model) {
		try {
			driver = (String) model.getField("DRIVER").get("DRIVER");
			url = (String) model.getField("URL").get("URL");
			user = (String) model.getField("USER").get("USER");
			password = (String) model.getField("PASSWORD").get("PASSWORD");
			table = (String) model.getField("TABLE").get("TABLE");
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		conditionType = new ArrayList<>();
		conditions = new ArrayList<>();
	}
	
	public SelectQuery where(Condition<?> condition) {
		conditionType.add(0, "where");
		conditions.add(condition);
		return this;
	}
	
	public SelectQuery and(Condition<?> condition) {
		conditionType.add("and");
		conditions.add(condition);
		return this;
	}
	
	public SelectQuery or(Condition<?> condition) {
		conditionType.add("or");
		conditions.add(condition);
		return this;
	}
	
	public Map<String, Object> execute() {
		return QueryExecutor.execute(this);
	}
	
	@Override
	public List<Condition<?>> getConditions() {
		return conditions;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("select * from ").append(table);
		
		int length = conditions.size();
		for (int i = 0; i < length; i++) builder.append(" ").append(conditionType.get(i)).append(" ").append(conditions.get(i));
		
		return builder.toString();
	}
	
}

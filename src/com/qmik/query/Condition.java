package com.qmik.query;

public class Condition<T> {
	
	public final static String EQUAL = "=";
	public final static String LESS_THAN = "<";
	public final static String GREATER_THAN = ">";
	
	private String name;
	private String condition;
	private T value;
	
	public Condition(String name, String condition, T value) {
		setName(name);
		setCondition(condition);
		setValue(value);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCondition() {
		return condition;
	}
	
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	public T getValue() {
		return value;
	}
	
	public void setValue(T value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append(name).append(condition).append(value).toString();
	}
	
}

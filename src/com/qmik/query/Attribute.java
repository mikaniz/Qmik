package com.qmik.query;

public class Attribute<T> {
	
	String name;
	T value;
	
	public Attribute(String name) {
		setName(name);
	}
	
	public Condition<T> equal(T value) {
		return new Condition<>(name, Condition.EQUAL, value);
	}
	
	public Condition<T> lessThan(T value) {
		return new Condition<>(name, Condition.LESS_THAN, value);
	}
	
	public Condition<T> greaterThan(T value) {
		return new Condition<>(name, Condition.GREATER_THAN, value);
	}
	
	public Condition<T> is(T value) {
		return new Condition<>(name, Condition.EQUAL, value);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		if (name == null || "".equals(name.trim())) return;
		this.name = name;
	}
	
}

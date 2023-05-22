package com.qmik.database;

public class Database {
	
	private String type;
	private String id;
	private String url;
	private String catalog;
	private String user;
	private String password;
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		if (type == null || "".equals(type.trim())) return;
		this.type = type;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		if (id == null || "".equals(id.trim())) return;
		this.id = id;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		System.out.println(url);
		if (url == null || !url.trim().startsWith("jdbc:")) return;
		this.url = url;
		
		setType(url.split(":")[1]);
		setCatalog(url.split("/")[3]);
	}
	
	public String getCatalog() {
		return catalog;
	}
	
	public void setCatalog(String catalog) {
		if (catalog == null || "".equals(catalog.trim())) return;
		this.catalog = catalog;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		if (user == null || "".equals(user.trim())) return;
		this.user = user;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		if (password == null || "".equals(password.trim())) return;
		this.password = password;
	}
	
}

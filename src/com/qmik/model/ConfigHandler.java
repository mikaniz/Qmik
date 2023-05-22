package com.qmik.model;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.qmik.database.Database;

public class ConfigHandler extends DefaultHandler {
	
	private List<Database> databases;
	private Database database;
	private String value;
	
	public ConfigHandler() {
		databases = new ArrayList<>();
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equals("database")) {
			database = new Database();
			database.setId(attributes.getValue("id"));
			System.out.println(database);
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("url")) database.setUrl(value);
		else if (qName.equals("user")) database.setUser(value);
		else if (qName.equals("password")) database.setPassword(value);
		else if (qName.equals("database")) {
			System.out.println(database);
			
			if (database.getType() != null
				&& database.getId() != null
				&& database.getUrl() != null
				&& database.getCatalog() != null
				&& database.getUser() != null
				&& database.getPassword() != null) databases.add(database);
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		value = new String(ch, start, length);
	}
	
	public List<Database> getDatabases() {
		return databases;
	}
	
}

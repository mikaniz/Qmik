package com.qmik.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.qmik.database.Column;
import com.qmik.database.Database;
import com.qmik.database.MetaDataLoader;
import com.qmik.database.Table;

public class ModelGenerator {
	
	public static void generate(String basePath) throws IOException {
		File file = new File("${project.basedir}/../qmik-config.xml");
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		try {
			SAXParser parser = factory.newSAXParser();
			ConfigHandler handler = new ConfigHandler();
			parser.parse(file, handler);
			
			List<Database> databases = handler.getDatabases();
			for (Database database : databases) {
				List<Table> tables = MetaDataLoader.load(database);
				for (Table table : tables) generate(basePath, basePath.replaceAll("[/]", "."), database, table);
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}
	
	public static void generate(String basePath, String basePackage, Database database, Table table) {
		File qmikFolder = new File(new StringBuilder().append("src/main/java/").append(basePath).append("/qmik").toString());
		if (!qmikFolder.exists()) qmikFolder.mkdir();
		
		File modelFolder = new File(qmikFolder, database.getId());
		if (!modelFolder.exists()) modelFolder.mkdir();
		
		File model = new File(modelFolder, new StringBuilder().append(table.getName().toUpperCase()).append(".java").toString());

		StringBuilder builder = new StringBuilder();
		builder.append("package ").append(basePackage).append(".qmik.").append(database.getId()).append(";\n\n")
				.append("import com.qmik.model.Model;\n")
				.append("import com.qmik.query.Attribute;\n\n")
				.append("public class ").append(table.getName().toUpperCase()).append(" implements Model {\n\n")
				.append("\tpublic final static String DRIVER = \"").append(database.getType()).append("\";\n")
				.append("\tpublic final static String URL = \"").append(database.getUrl()).append("\";\n")
				.append("\tpublic final static String USER = \"").append(database.getUser()).append("\";\n")
				.append("\tpublic final static String PASSWORD = \"").append(database.getPassword()).append("\";\n")
				.append("\tpublic final static String TABLE = \"").append(table.getName()).append("\";\n\n");
		
		for (Column column : table.getColumns()) {
			builder.append("\tpublic static Attribute<").append(column.getType()).append("> ").append(column.getName())
					.append(" = new Attribute<>(\"").append(column.getName()).append("\");\n");
		}
		
		builder.append("\n}");
		
		try (FileOutputStream fileOutputStream = new FileOutputStream(model)) {
			fileOutputStream.write(builder.toString().getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

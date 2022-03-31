package com.qa.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;

import groovyjarjarantlr4.v4.parse.ANTLRParser.sync_return;

public class DBUtility {

	Connection ops_conn;
	Connection marvel_conn;
	PreparedStatement prestatement = null;
	private ConfigReader configReader;
	Properties prop;
	String opsdbURL;
	String opsdbUserName;
	String opsdbPassword;
	
	String marveldbURL;
	String marveldbUserName;
	String marveldbPassword;

	Logger log = Logger.getLogger(DBUtility.class);

	public DBUtility() {
		try {
			log.info("Capturing the Config Properties");
			configReader = new ConfigReader();
			prop = configReader.init_prop();
			
			opsdbURL = prop.getProperty("opsdbURL");
			opsdbUserName = prop.getProperty("opsdbUserName");
			opsdbPassword = prop.getProperty("opsdbPassword");
			ops_conn = DriverManager.getConnection(opsdbURL, opsdbUserName, opsdbPassword);
			
			marveldbURL = prop.getProperty("marveldbURL");
			marveldbUserName = prop.getProperty("marveldbUserName");
			marveldbPassword = prop.getProperty("marveldbPassword");
			marvel_conn = DriverManager.getConnection(marveldbURL, marveldbUserName, marveldbPassword);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getOPSDBValue(String param, String query, String colName) {
		String value = null;
		try {
			prestatement = ops_conn.prepareStatement(query);
			prestatement.setString(1, param);

			ResultSet result = prestatement.executeQuery();
			while (result.next())
				value = result.getString(colName);
				//System.out.println(result.getString(1));
				
		} catch (Exception e) {
			e.printStackTrace();
		}

		return value;
	}
	
	public String getOPSDBValue(String param1, String param2, String query, String colName) {
		String value = null;
		try {
			prestatement = ops_conn.prepareStatement(query);
			prestatement.setString(1, param1);
			prestatement.setString(2, param2);

			ResultSet result = prestatement.executeQuery();
			while (result.next())
				value = result.getString(colName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return value;
	}
	
	public String getOPSDBValue(String param1, int param2, String query, String colName) {
		String value = null;
		try {
			prestatement = ops_conn.prepareStatement(query);
			prestatement.setString(1, param1);
			prestatement.setInt(2, param2);

			ResultSet result = prestatement.executeQuery();
			while (result.next())
				value = result.getString(colName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return value;
	}
	
	public String getMarevlDBValue(String param, String query, String colName) {
		String value = null;
		try {
			prestatement = marvel_conn.prepareStatement(query);
			prestatement.setString(1, param);

			ResultSet result = prestatement.executeQuery();
			while (result.next())
				value = result.getString(colName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return value;
	}
	
	public void executeRequiredOpsDBQuery(String query, String param) {
		try {
			prestatement = ops_conn.prepareStatement(query);
			prestatement.setString(1, param);

			boolean result = prestatement.execute();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void executeRequiredOpsDBQuery(String query, String param1, String param2) {
		try {
			prestatement = ops_conn.prepareStatement(query);
			prestatement.setString(1, param1);
			prestatement.setString(2, param2);

			boolean result = prestatement.execute();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void executeRequiredOpsDBQuery(String query, String param1, String param2, String param3) {
		try {
			prestatement = ops_conn.prepareStatement(query);
			prestatement.setString(1, param1);
			prestatement.setString(2, param2);
			prestatement.setString(3, param3);

			boolean result = prestatement.execute();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}

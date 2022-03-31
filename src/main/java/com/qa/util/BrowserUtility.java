package com.qa.util;

import java.util.Properties;

public class BrowserUtility {
	
	public Browser getBrowser(Properties prop) {
		String browserName = prop.getProperty("browser");
		if(browserName == null || browserName.equalsIgnoreCase("chrome"))
			return Browser.CHROME;
		else if(browserName.equalsIgnoreCase("firefox"))
			return Browser.FIREFOX;
		else if(browserName.equalsIgnoreCase("edge"))
			return Browser.EDGE;
		else 
			throw new RuntimeException("Browser Name Key value in Configuration.properties is not matched : " + browserName);
	}
}

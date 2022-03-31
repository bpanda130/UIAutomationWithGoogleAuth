package com.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private Properties prop;

	/**
	 * 
	 * @return This method is used to load the Property from config properties file depends on Environment
	 */
	public Properties init_prop() {
		FileInputStream ip = null;
		prop = new Properties();

		String env = System.getProperty("env");
		if (env == null) {
			try {
				ip = new FileInputStream(Constants.CONFIG_FILEPATH);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			try {
				switch (env) {
				case "qa":
					ip = new FileInputStream(Constants.QA_CONFIG_FILEPATH);
					break;
				case "stage":
					ip = new FileInputStream(Constants.STAGE_CONFIG_FILEPATH);
					break;
				case "dev":
					ip = new FileInputStream(Constants.DEV_CONFIG_FILEPATH);
					break;
				default:
					break;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}
		try {
			prop.load(ip);
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

}

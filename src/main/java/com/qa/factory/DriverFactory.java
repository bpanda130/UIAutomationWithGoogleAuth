package com.qa.factory;

import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v95.log.Log;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.util.Browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverFactory {

	public WebDriver driver;
	public static String highlight = "true";
	private static Browser browserName;
	private OptionsManager optionsManager;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	public static DevTools chromeDevTools;

	/**
	 * This method is used to initialize the threadlocal driver on the basis of
	 * given browser
	 * 
	 * @param broserName
	 * @return this will return tlDriver
	 */
	public WebDriver init_driver(Properties prop, Browser broserName) {
		highlight = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);

/*		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver(optionsManager.getChromeOptions());

		DevTools chromeDevTools = driver.getDevTools();
		chromeDevTools.createSession();
		chromeDevTools.send(Log.enable());

		chromeDevTools.addListener(Log.entryAdded(),
				logEntry ->
				{
					System.out.println(logEntry.getText());
					System.out.println(logEntry.getSource());
					System.out.println("---------------");
				});
		tlDriver.set(driver);*/

		switch (broserName) {
		case FIREFOX:
			WebDriverManager.firefoxdriver().setup();
			//tlDriver.set(new FirefoxDriver());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;
		case CHROME:
			WebDriverManager.chromedriver().setup();
			//tlDriver.set(new ChromeDriver());
			//ChromeDriver chromeDriver = new ChromeDriver(optionsManager.getChromeOptions());

			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		case EDGE:
			WebDriverManager.edgedriver().setup();
			tlDriver.set(new EdgeDriver());
			break;
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		//getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		return getDriver();
	}

	/**
	 * this is used to get the driver with threadLocal
	 * 
	 * @return
	 */
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

}

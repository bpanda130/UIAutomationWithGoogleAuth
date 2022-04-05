package appHooks;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.qa.factory.DriverFactory;
import com.qa.util.Browser;
import com.qa.util.BrowserUtility;
import com.qa.util.ConfigReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v95.log.Log;


public class ApplicationHooks {
	
	private DriverFactory driverFactory;
	private WebDriver driver;
	private ConfigReader configReader;
	Properties prop;
	BrowserUtility browserUtil;
	Logger log = Logger.getLogger(ApplicationHooks.class);
	
	@Before(order = 0)
	public void getProperty() {
		log.info("Capturing the Config Properties");
		configReader = new ConfigReader();
		prop = configReader.init_prop();
	}
	
	@Before(order = 1)
	public void launchBrowser() {
		log.info("Driver Intialization for required Browser");
		browserUtil = new BrowserUtility();
		Browser browser = browserUtil.getBrowser(prop);
		driverFactory = new DriverFactory();
		driver = driverFactory.init_driver(prop, browser);
	}
	
	@After(order = 0)
	public void quitBrowser() {
		driver.quit();
	}
	
	@After(order = 1)
	public void tearDown(Scenario scenario) {
		if(scenario.isFailed() || !scenario.isFailed()) {
			log.error("Capturing Screenshot for Failed Test cases.");
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenshotName);
		}
	}
}

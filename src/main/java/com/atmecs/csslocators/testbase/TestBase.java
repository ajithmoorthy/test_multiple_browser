package com.atmecs.csslocators.testbase;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.atmecs.csslocators.constants.FileConstants;
import com.atmecs.csslocators.extentreport.Extent;
import com.atmecs.csslocators.logreports.LogReporter;
import com.atmecs.csslocators.utils.PropertiesReader;
/*this class will act as the base class for the test it will provide the browser based on the user choice */
/**
 * @author ajith.periyasamy
 * This Class is extend from the Extent class and class contains the browser selection method.
 * baseSetup method is used to select the script run in the Selenium grid or user system.
 */
public class TestBase extends Extent{
	protected Properties prop=null;
	PropertiesReader propertyReader = new PropertiesReader();
	LogReporter log=new LogReporter();
	/**
	 * This baseSetup method will provide the option to choose the browser driver to run the script.
	 *  The configuration details is read from the config.properties file.
	 *  and this browser details passed to the switch case and based on the configuration details script is running in different browsers. 
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("deprecation")
	@BeforeTest
	@Parameters("Name")
	public void baseSetup(String browser) throws IOException, InstantiationException, IllegalAccessException {
		prop = propertyReader.KeyValueLoader(FileConstants.CONFIG1_PATH);
		String[] chooser=prop.getProperty("webdrivername").split(",");
			switch (browser) 
			{
			case "CHROME":
				System.setProperty("webdriver.chrome.driver", FileConstants.CHROME_DRIVER_PATH);
				ChromeOptions chromeoptions = new ChromeOptions();
				chromeoptions.addArguments("--disable-notifications");
				chromeoptions.addArguments("disable-geolocation");
				driver = new ChromeDriver(chromeoptions);
				break;
			case "FIREFOX":
				System.setProperty("webdriver.gecko.driver", FileConstants.FIREFOX_DRIVER_PATH);
				FirefoxOptions fire = new FirefoxOptions();
				fire.addPreference("dom.webnotifications.enabled", false);
				driver = new FirefoxDriver(fire);
				break;
			case "IE":
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, prop.getProperty("url"));
				capabilities.setCapability("requireWindowFocus", true);
				capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
				System.setProperty("webdriver.ie.driver", FileConstants.IE_DRIVER_PATH);
				driver=new InternetExplorerDriver(capabilities);
				break;
			case "EDGE":
				System.setProperty("webdriver.edge.driver", FileConstants.EDGE_DRIVER_PATH);
				driver = new EdgeDriver();
				break;
			}
//			driver.get(prop.getProperty("url"));
//			log.logReportMessage("url is loaded");
		}
	}

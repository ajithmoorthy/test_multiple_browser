package com.atmecs.csslocators.testscripts;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import com.atmecs.csslocators.constants.FileConstants;
import com.atmecs.csslocators.helper.SeleniumHelper;
import com.atmecs.csslocators.logreports.LogReporter;
import com.atmecs.csslocators.testbase.TestBase;
import com.atmecs.csslocators.utils.PropertiesReader;

public class Printer  extends TestBase{
	
	LogReporter log=new LogReporter();
	PropertiesReader propread=new PropertiesReader();
	SeleniumHelper help=new SeleniumHelper();
	@Test
	public void printbrowser() throws IOException
	{
		Properties prop1=propread.KeyValueLoader(FileConstants.LOCATOR_PATH);
		driver.get(prop.getProperty("url"));
		help.clickElement(driver, prop1.getProperty("loc.input.date"));
		help.clickElement(driver, prop1.getProperty("loc.date.from"));
		driver.close();
	}
}

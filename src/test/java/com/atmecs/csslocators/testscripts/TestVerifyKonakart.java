package com.atmecs.csslocators.testscripts;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import com.atmecs.csslocators.constants.FileConstants;
import com.atmecs.csslocators.helper.SeleniumHelper;
import com.atmecs.csslocators.logreports.LogReporter;
import com.atmecs.csslocators.testbase.TestBase;
import com.atmecs.csslocators.utils.PropertiesReader;

public class TestVerifyKonakart extends TestBase
{
	LogReporter log=new LogReporter();
	PropertiesReader propread=new PropertiesReader();
	SeleniumHelper help=new SeleniumHelper();
	@Test(priority=0)
	public void verifyKonaKart() throws IOException 
	{
		Properties prop1=propread.KeyValueLoader(FileConstants.LOCATOR_PATH);
		driver.get(prop.getProperty("url2"));
		log.logReportMessage("url is loaded");
		logger=extentObject.startTest("konakart test");
		help.clickElement(driver, prop1.getProperty("loc.img.hero"));
		help.clickElement(driver,  prop1.getProperty("loc.tab.reviews"));
		driver.close();
	}

}

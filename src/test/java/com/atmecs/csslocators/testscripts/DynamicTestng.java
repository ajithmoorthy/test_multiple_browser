package com.atmecs.csslocators.testscripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlSuite.ParallelMode;
import org.testng.xml.XmlTest;

import com.atmecs.csslocators.constants.FileConstants;
import com.atmecs.csslocators.utils.PropertiesReader;

public class DynamicTestng {
	PropertiesReader propread=new PropertiesReader();
	public List<XmlSuite> suiteXml() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException 
	{
		Properties props = propread.KeyValueLoader(FileConstants.CONFIG_PATH);

		List<String> names = new ArrayList<String>();
		String[] arr = props.getProperty("webdrivername").split(",");
		for (String name : arr)
		{
			names.add(name);
		}
		XmlSuite xmlSuite = new XmlSuite();
		xmlSuite.setName("mysuite");
		xmlSuite.setParallel(ParallelMode.TESTS);
		xmlSuite.setThreadCount(5);
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		for (int count=0; count<names.size(); count++) 
		{
			XmlTest xmlTest1 = new XmlTest(xmlSuite);
			Map<String, String> parameter1 = new HashMap<String, String>();
			parameter1.put("Name", names.get(count));
			xmlTest1.setParameters(parameter1);
			xmlTest1.setName("Test validate " +names.get(count));
			XmlClass myClass = new XmlClass(TestVerifyKonakart.class);
			List<XmlClass> xmlClassList1 = new ArrayList<XmlClass>();
			xmlClassList1.add(myClass);
			xmlTest1.setXmlClasses(xmlClassList1);
		}
		suites.add(xmlSuite);
		return suites;
	}
	@Test
	public void xmlsuite() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		List<XmlSuite> suites = suiteXml();
		TestNG testng = new TestNG();
		testng.setXmlSuites(suites);
		testng.run();
	}
}

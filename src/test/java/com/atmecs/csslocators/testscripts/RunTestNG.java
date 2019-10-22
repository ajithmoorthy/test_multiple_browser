package com.atmecs.csslocators.testscripts;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlSuite.ParallelMode;
import org.testng.xml.XmlTest;

public class RunTestNG {

		@SuppressWarnings("deprecation")
		@Test
		public void runTestFile() {
		XmlSuite xmlSuite = new XmlSuite();
		xmlSuite.setName("mysuite");
		xmlSuite.setVerbose(1);

		XmlTest xmlTest = new XmlTest(xmlSuite);
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put("Name", "CHROME");
		xmlTest.setParameters(parameter);
		xmlTest.setName("Test Search Item and validate");
		xmlSuite.setParallel("tests");
		xmlSuite.setThreadCount(5);

		XmlClass konakartValidateClass = new XmlClass(TestVerifyKonakart.class);
		//XmlClass redbusValidateClass = new XmlClass(TestVerifyRedbus.class);
		List<XmlClass> xmlClassList = new ArrayList<XmlClass>();
		xmlClassList.add(konakartValidateClass);
		//xmlClassList.add(redbusValidateClass);
		
		xmlTest.setXmlClasses(xmlClassList);
		TestNG testng = new TestNG();
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(xmlSuite);
		testng.setXmlSuites(suites);
		testng.run();
		}
}

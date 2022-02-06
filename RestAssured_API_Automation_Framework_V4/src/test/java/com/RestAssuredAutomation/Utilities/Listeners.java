package com.RestAssuredAutomation.Utilities;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class Listeners extends TestListenerAdapter {

public void onTestSart (ITestResult tr) {
		
		System.out.println("Test Started :" +tr.getName());
	}

	
	public void onTestSuccess (ITestResult tr) {
		
		System.out.println("Test Passed :" +tr.getName());
	}
	
	public void onTestFailure (ITestResult tr) {
		
		System.out.println("Test Failed :" +tr.getName());
	}
	
	public void onTestSkipped (ITestResult tr) {
		System.out.println("Test Skipped :" +tr.getName());
	}	
	
}

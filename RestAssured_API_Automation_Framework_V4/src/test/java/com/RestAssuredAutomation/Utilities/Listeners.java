package com.RestAssuredAutomation.Utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter {

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;

	public void onStart(ITestContext testContext) {

		
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/"); // specify Location
			
		htmlReporter.config().setDocumentTitle("Automation Report"); // Title of report
		htmlReporter.config().setReportName("Rest API Automation Testing Report"); // name of the report
		htmlReporter.config().setTheme(Theme.DARK);
		

		extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Rajasekar");

		
	}

	public void onTestSuccess(ITestResult tr) {
		test = extent.createTest(tr.getName()); // create new entry in the report
		test.log(Status.PASS, "Test Case Passed is "+tr.getName()); 
																							

	}

	public void onTestFailure(ITestResult tr)

	{

		test = extent.createTest(tr.getName()); // create new entry in the report
		test.log(Status.FAIL, "The Test Case Failed is: "+tr.getName()); 
		test.log(Status.FAIL, "The Test Case Failed is:"+tr.getThrowable());


		
	}

	public void onTestSkipped(ITestResult tr) {
		test = extent.createTest(tr.getName()); // create new entry in the report
		test.log(Status.SKIP, "The Test Case Skipped is:"+tr.getName());

	}

	public void onFinish(ITestContext testContext) {
		extent.flush();
	}
	
}

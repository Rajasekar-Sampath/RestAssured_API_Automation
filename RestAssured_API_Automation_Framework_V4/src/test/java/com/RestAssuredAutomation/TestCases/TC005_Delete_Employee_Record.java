package com.RestAssuredAutomation.TestCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.RestAssuredAutomation.Base.BaseClass;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC005_Delete_Employee_Record extends BaseClass {
	
	@BeforeClass
	void DeleteEmpRecord() {
		
		logger.info("**********Started TC005_Delete_Employee_Record**********");
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		
		httpRequest = RestAssured.given();
		
		response = httpRequest.request(Method.GET,"/employees");
		
		
		// First get the JsonPath object instance from the Response Interface
		JsonPath jsonPathEvaluator = response.jsonPath();
		
		//Capture ID
		String empID = jsonPathEvaluator.get("[0].id");
		
		response= httpRequest.request(Method.DELETE,"/delete/"+empID); //pass ID to Delete Record
		
		
	}
	
	@Test
	void checkResponseBody() {
		
		
		logger.info("*********Catching the ResponseBody************");
		
		String responseBody = response.getBody().asString();
		logger.info("*********The Status Code is:" +responseBody);
		Assert.assertEquals(responseBody.contains("successfully! deleted Records"),true);
	}
	
	@Test
	void checkStatusCode() {

		logger.info("*******Checking Status Code********");
		int statuscode = response.getStatusCode(); // Getting statuscode
		logger.info("=======The StatusCode is:" + statuscode); // 200
		Assert.assertEquals(statuscode, 200);
	}
	
	@Test
	void checkResponseTime() {

		logger.info("*******Checking Response Time********");
		long responsetime = response.getTime(); // Getting Responsecode
		logger.info("The responseTime is:" + responsetime);

		if (responsetime > 6000)
			logger.warn("The response time is greater than 2000");

		Assert.assertTrue(responsetime < 6000);
	}

	@Test
	void checkStatusLine() {

		logger.info("*********Checking Status Line**********");

		String statusline = response.getStatusLine();
		logger.info("The statusline is:" + statusline);
		Assert.assertEquals(statusline, "HTTP/1.1 200 Ok");
	}

	@Test
	void checkContentType() {

		logger.info("********Checking Content-Type*********");

		String ContentType = response.header("Content-Type");
		logger.info("The ContentType is:" + ContentType);
		Assert.assertEquals(ContentType, "text/html; charset=UTF-8");
	}

	@Test
	void checkServerType() {

		logger.info("*******Checking Server Type*********");

		String servertype = response.header("Server");
		logger.info("The serverType is:" + servertype);
		Assert.assertEquals(servertype, "nginx/1.14.1");
	}

	@Test
	void checkContentEncoding() {

		logger.info("*******Checking Content Encoding*********");

		String contentencoding = response.header("Content-Encoding");
		logger.info("The ContentEncoding is:" + contentencoding);
		Assert.assertEquals(contentencoding, "gzip");
	}
	
	@AfterClass
	void TearDown() {

		logger.info("***********Finished TC005_Delete_Employee_Record*************");
	}
	
	
	

}

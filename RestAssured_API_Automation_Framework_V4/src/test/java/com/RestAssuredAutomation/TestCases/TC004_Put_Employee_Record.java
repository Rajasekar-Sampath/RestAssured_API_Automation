package com.RestAssuredAutomation.TestCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.RestAssuredAutomation.Base.BaseClass;
import com.RestAssuredAutomation.Utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC004_Put_Employee_Record extends BaseClass{
	
	String empName = RestUtils.empName();
	String empsal = RestUtils.empSal();
	String empAge = RestUtils.empAge();
	
	@BeforeClass
	void updateEmpRecord() throws InterruptedException {
		
		logger.info("***********Started TC003_Put_Employee_Record*************");
		
		RestAssured.baseURI ="http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		
		//JSONObject is a class that represents simple JSON. We can add Key-Value pairs using PUT method.
		//{"name":"JohnX","salary":"20000","age":"25"};
		JSONObject requestParams = new JSONObject();
		
		requestParams.put("name",empName);
		requestParams.put("salary", empsal);
		requestParams.put("age", empAge);
		
		//Add a header states that the request body is a JSON
		httpRequest.header("Content-Type","application/json");
		
		 //Add the JSON to the body of the request
		httpRequest.body(requestParams.toJSONString());
		
		response = httpRequest.request(Method.PUT,"/update/"+empID);
		
		Thread.sleep(5000);
		
}
	
	@Test
	void getResponseBody() {

		logger.info("*******Getting ResponseBody********");
		String responseBody = response.getBody().asString();
		logger.info("++++++++The responseBody is:" + responseBody);
		Assert.assertEquals(responseBody.contains(empName),true);
		Assert.assertEquals(responseBody.contains(empsal),true);
		Assert.assertEquals(responseBody.contains(empAge),true);
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

		logger.info("***********Finished TC003_Put_Employee_Record*************");
	}
	

}

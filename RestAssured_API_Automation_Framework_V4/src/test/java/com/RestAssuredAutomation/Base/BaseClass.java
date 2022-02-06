package com.RestAssuredAutomation.Base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClass {
	
	public RequestSpecification httpRequest;
    public Response response;
    public Logger logger;
    public String empID= "51838"; // Hard coded - Input for get details of Single Employee & Update Employee
    
    
    @BeforeClass
    public void setup() {
    	
    	logger = Logger.getLogger("RestAssured_API_Automation_Framework_V4");
    	PropertyConfigurator.configure("log4j.properties");
    	logger.setLevel(Level.DEBUG);
    	
    	
    	
    }
    
    
    
	

}

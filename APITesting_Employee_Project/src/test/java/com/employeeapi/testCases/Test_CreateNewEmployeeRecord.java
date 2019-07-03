package com.employeeapi.testCases;

import org.json.simple.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import junit.framework.Assert;

public class Test_CreateNewEmployeeRecord extends TestBase {

	String empName = RestUtils.empName();
	String empSalary = RestUtils.empSal();
	String empAge = RestUtils.empAge();

	@BeforeClass
	void createlEmployees() throws InterruptedException {
		logger.info("****************Started Test_CreateNewEmployeeRecord*****************");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		// Request paylaod sending along with post request
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", empName);
		requestParams.put("salary", empSalary);
		requestParams.put("age", empAge);
		httpRequest.header("Content-Type", "application/json");

		httpRequest.body(requestParams.toJSONString()); // attach above data to
														// the request
		// Response object
		response = httpRequest.request(Method.POST, "/create");
		Thread.sleep(3000);
	}

	@Test
	void checkResponseBody() {
		logger.info("****************Checking Response Body*****************");
		String responseBody = response.getBody().asString();
		logger.info("Response Body ==> " + responseBody);
		Assert.assertNotNull(responseBody);
		String name = response.jsonPath().get("name");
		Assert.assertEquals(name, empName);
		String age = response.jsonPath().get("age");
		Assert.assertEquals(age, empAge);
		String salary = response.jsonPath().get("salary");
		Assert.assertEquals(salary, empSalary);
	}

	@Test
	void checkStatusCode() {
		logger.info("****************Checking Status Code*****************");
		int responseCode = response.getStatusCode();
		logger.info("Status Code is ==> " + responseCode);
		Assert.assertEquals(responseCode, 200);
	}

	@Test
	void checkResponseTime() {
		logger.info("****************Checking Response Time*****************");
		long responseTime = response.getTime();
		logger.info("Response Time is ==> " + responseTime);
		if (responseTime > 2000)
			logger.warn("Content Length is greater than 2000");
		Assert.assertTrue(responseTime < 2000);
	}

	@Test
	void checkStatusLine() {
		logger.info("****************Checking Status Line*****************");
		String statusLine = response.getStatusLine();
		logger.info("Status Line is ==> " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

	@Test
	void checkContentType() {
		logger.info("****************Checking Content Type*****************");
		String contentType = response.header("Content-Type");
		logger.info("Content Type is ==>" + contentType);
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
	}

	@Test
	void checkServerType() {
		logger.info("****************Checking Server Type*****************");
		String serverType = response.header("Server");
		logger.info("Server Type is ==>" + serverType);
		Assert.assertEquals(serverType, "Apache");
	}

	//@Test
	void checkContentEncoding() {
		logger.info("****************Checking Content Encoding*****************");
		String contentEncoding = response.header("Content-Encoding");
		logger.info("Content Encoding is ==>" + contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
	}
	
	@AfterClass
	void tearDown(){
		logger.info("****************Finished Test_CreateNewEmployeeRecord*****************");
	}
}

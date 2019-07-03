package com.employeeapi.testCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import junit.framework.Assert;

public class Test_GetAllEmployees extends TestBase {

	@BeforeClass
	void getAllEmployees() throws InterruptedException {
		logger.info("****************Started Test_GetAllEmployees*****************");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employees");
	}

	@Test
	void checkResponseBody() {
		logger.info("****************Checking Response Body*****************");
		String responseBody = response.getBody().asString();
		logger.info("Response Body==>" + responseBody);
		Assert.assertNotNull(responseBody);
	}

	@Test
	void checkStatusCode() {
		logger.info("****************Checking Status Code*****************");
		int responseCode = response.getStatusCode();
		logger.info("Status Code is ==>" + responseCode);
		Assert.assertEquals(responseCode, 200);
	}

	@Test
	void checkResponseTime() {
		logger.info("****************Checking Response Time*****************");
		long responseTime = response.getTime();
		logger.info("Response Time is ==>" + responseTime);
		if(responseTime>2000)
			logger.warn("Content Length is greater than 2000");
		Assert.assertTrue(responseTime < 2000);
	}
	
	@Test
	void checkStatusLine() {
		logger.info("****************Checking Status Line*****************");
		String statusLine = response.getStatusLine();
		logger.info("Status Line is ==>" + statusLine);
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
	
	@Test
	void checkContentEncoding() {
		logger.info("****************Checking Content Encoding*****************");
		String contentEncoding = response.header("Content-Encoding");
		logger.info("Content Encoding is ==>" + contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
	}
	
	//@Test
	void checkContentLength() {
		logger.info("****************Checking Content Length*****************");
		String contentLength = response.header("Content-Length");
		logger.info("Content Length is ==>" + contentLength);
		if(Integer.parseInt(contentLength)<100)
			logger.warn("Content Length is less than 100");
		Assert.assertTrue(Integer.parseInt(contentLength)>100);	
	}
	
	@Test
	void checkCookies() {
		logger.info("****************Checking Cookies*****************");
		String cookie = response.getCookie("PHPSESSID");
		
	}
	
	@AfterClass
	void tearDown(){
		logger.info("****************Finished Test_GetAllEmployees*****************");
	}
}

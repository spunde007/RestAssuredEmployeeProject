package com.employeeapi.testCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class Test_DeleteEmployeeRecord extends TestBase {
	@BeforeClass
	void getEmployee() throws InterruptedException {
		logger.info("****************Started Test_DeleteEmployeeRecord*****************");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employees");

		JsonPath jsonPathEvaluator = response.jsonPath();
		String empID = jsonPathEvaluator.get("[0].id");
		response = httpRequest.request(Method.DELETE, "/delete/" + empID);
	}

	@Test
	void checkResponseBody() {
		logger.info("****************Checking Response Body*****************");
		String responseBody = response.getBody().asString();
		logger.info("Response Body==>" + responseBody);
		Assert.assertEquals(responseBody.contains("successfully! deleted Records"), true);
	}

	@Test
	void checkStatusCode() {
		logger.info("****************Checking Status Code*****************");
		int responseCode = response.getStatusCode();
		logger.info("Status Code is ==>" + responseCode);
		Assert.assertEquals(responseCode, 200);
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

	@AfterClass
	void tearDown() {
		logger.info("****************Finished Test_DeleteEmployeeRecord*****************");
	}
}

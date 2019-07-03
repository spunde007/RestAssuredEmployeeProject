package com.employeeapi.utilities;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter {

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;

	public void onStart(ITestContext testContext) {
		//htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Reports/myReport2.html");
		//htmlReporter.config().setDocumentTitle("Rest API Testing Report");
		//htmlReporter.config().setReportName("Functional Testing");
		//htmlReporter.config().setTheme(Theme.STANDARD);

		//extent = new ExtentReports();
		//extent.attachReporter(htmlReporter);
		//extent.setSystemInfo("Project Name", "Employee Database API");
		//extent.setSystemInfo("Host name", "localhost");
		//extent.setSystemInfo("Environment", "QA");
		//extent.setSystemInfo("user", "Sameer");
	}

	public void onTestSuccess(ITestResult result) {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Reports/"+result.getMethod()+".html");
		htmlReporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		test = extent.createTest(result.getName());
		test.log(Status.PASS, "Test Case Passed Is: " + result.getName());
		extent.flush();
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "Test Case Failed Is: " + result.getName());
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test Case Skipped Is: " + result.getName());
	}

	public void onFinish(ITestResult result) {
		//extent.flush();
	}
}

package in.biencaps.erp.api.testcases;

import java.io.File;

import org.apache.logging.log4j.*;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.markuputils.*;
import com.aventstack.extentreports.reporter.*;
import com.aventstack.extentreports.reporter.configuration.Theme;

import in.biencaps.erp.api.endpoints.*;
import in.biencaps.erp.api.utilities.*;
import io.restassured.*;
import io.restassured.config.*;
import io.restassured.response.Response;

public class BaseTest {
	public static final Logger log = LogManager.getLogger(BaseTest.class);

	public ExtentSparkReporter sparkReporter;
	public static ExtentReports extent;
	public static ExtentTest test;

	@BeforeSuite
	public void startBackendServer() {
		RestAssured.config = RestAssuredConfig.config().sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation());

		RestAssured.baseURI = APIEndpoints.baseURL;
		log.info("Connected to backend" + "\n");
	}

	@BeforeClass
	public void extent_Report_Setup() {
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator + "Reports"
				+ File.separator + Constants.extentReportHTMLFileName);
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		sparkReporter.config().setTheme(Theme.STANDARD);
		extent.setSystemInfo("OS", Constants.extentReportOS);
		extent.setSystemInfo("Tester", Constants.extentReportTester);
		sparkReporter.config().setDocumentTitle(Constants.extentReportDocumentTitle);
		sparkReporter.config().setReportName(Constants.extentReportName);
	}

	@AfterMethod
	public void test_Method_Exection_Result(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			test.log(Status.FAIL,
					MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
		} else {
			test.log(Status.PASS,
					MarkupHelper.createLabel(result.getName() + " - Test Case Passed", ExtentColor.GREEN));
		}
	}

	@AfterSuite
	public void flush_Extent_Report() {
		extent.flush();
	}

	public static void test_Method_Logs(String message, String endpoint, Response response) {
		test.log(Status.INFO, "API endpoint for " + message + " is => " + endpoint);
		test.log(Status.INFO, "Status code for " + message + " is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for " + message + " is => " + response.getBody().asPrettyString());
	}

	public static void test_Method_Logs(String message, String endpoint, String requestPayload, Response response) {
		test.log(Status.INFO, "API endpoint for " + message + " is => " + endpoint);
		test.log(Status.INFO, "Request payload for " + message + " is => " + requestPayload);
		test.log(Status.INFO, "Status code for " + message + " is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for " + message + " is => " + response.getBody().asPrettyString());
	}

	public static void test_Method_Logs_With_Path_Parameter(String message, String endpoint, String pathParameter,
			Response response) {
		test.log(Status.INFO, "API endpoint for " + message + " is => " + endpoint);
		test.log(Status.INFO, "Path parameter for " + message + " is => " + pathParameter);
		test.log(Status.INFO, "Status code for " + message + " is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for " + message + " is => " + response.getBody().asPrettyString());
	}

	public static void test_Method_Logs_With_Path_Parameter(String message, String endpoint, int pathParameter,
			Response response) {
		test.log(Status.INFO, "API endpoint for " + message + " is => " + endpoint);
		test.log(Status.INFO, "Path parameter for " + message + " is => " + pathParameter);
		test.log(Status.INFO, "Status code for " + message + " is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for " + message + " is => " + response.getBody().asPrettyString());
	}

	public static void test_Method_Logs_With_Query_Parameter(String message, String endpoint, String queryParameter,
			Response response) {
		test.log(Status.INFO, "API endpoint for " + message + " is => " + endpoint);
		test.log(Status.INFO, "Query parameter for " + message + " is => " + queryParameter);
		test.log(Status.INFO, "Status code for " + message + " is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for " + message + " is => " + response.getBody().asPrettyString());
	}

	public static void test_Method_Logs_With_Query_Parameter(String message, String endpoint, int queryParameter,
			Response response) {
		test.log(Status.INFO, "API endpoint for " + message + " is => " + endpoint);
		test.log(Status.INFO, "Query parameter for " + message + " is => " + queryParameter);
		test.log(Status.INFO, "Status code for " + message + " is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for " + message + " is => " + response.getBody().asPrettyString());
	}
}

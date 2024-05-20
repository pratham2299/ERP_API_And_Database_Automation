package in.task_erp_api.testcases;

import java.io.File;

import org.apache.logging.log4j.*;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.markuputils.*;
import com.aventstack.extentreports.reporter.*;
import com.aventstack.extentreports.reporter.configuration.Theme;

import in.task_erp_api.endpoints.*;
import in.task_erp_api.utilities.*;
import io.restassured.*;
import io.restassured.config.*;

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

//	@BeforeMethod
//	public void create_Extent_Report_For_Each_Test_Method(Method testMethod) {
//		test = extent.createTest(testMethod.getName());
//	}

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
}

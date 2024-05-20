package in.task_erp_api.utilities;

import java.lang.reflect.*;

import org.apache.logging.log4j.*;
import org.testng.*;
import org.testng.annotations.*;

public class SuiteListener implements ITestListener, IAnnotationTransformer {
	// This is logger API dependency code. To print messages in seperate file.
	// So that you can check all execution logs anytime. Logs stores in Logs folder
	public static final Logger log = LogManager.getLogger(SuiteListener.class);

	public void onTestStart(ITestResult result) {
		log.info("Test Started: " + result.getName() + "\n");
	}

	public void onTestSuccess(ITestResult result) {
		log.info("Test Passed: " + result.getName() + "\n");
	}

	public void onTestSkipped(ITestResult result) {
		log.info("Test Skipped: " + result.getName() + "\n");
	}

	public void transform(ITestAnnotation annotation, Method testMethod) {
		annotation.setRetryAnalyzer(RetryAnalyzer.class);
	}
}

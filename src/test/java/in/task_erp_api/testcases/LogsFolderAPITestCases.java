package in.task_erp_api.testcases;

import org.apache.logging.log4j.*;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import in.task_erp_api.bodyValidations.*;
import in.task_erp_api.endpoints.*;
import in.task_erp_api.responses.*;
import io.restassured.response.*;

public class LogsFolderAPITestCases {
	public static final Logger log = LogManager.getLogger(LogsFolderAPITestCases.class);

	private Response response;

	@Test(priority = 1)
	public void verifyGetAllUserLogsWithoutAuthorization() {
		response = Responses.getRequestWithoutAuthorization(APIEndpoints.getAllUserLogsEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 2)
	public void verifyGetAllUserLogsWithAuthorization(ITestContext context) {
//		String authToken = (String) context.getAttribute("authToken");

		response = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken, APIEndpoints.getAllUserLogsEndpoint);

		BodyValidation.responseValidation(response, 200);
	}
}

package in.biencaps.erp.api.testcases;

import org.apache.logging.log4j.*;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import in.biencaps.erp.api.bodyValidations.*;
import in.biencaps.erp.api.endpoints.*;
import in.biencaps.erp.api.responses.*;
import io.restassured.response.*;

public class LogsFolderAPITestCases extends BaseTest {
	public static final Logger log = LogManager.getLogger(LogsFolderAPITestCases.class);

	@Test(priority = 1)
	public void verify_Get_All_User_Logs_Without_Authorization() {
		test = BaseTest.extent.createTest("Get all user logs without authorization");

		Response response = Responses.getRequestWithoutAuthorization(APIEndpoints.getAllUserLogsEndpoint);

		BodyValidation.response401Validation(response);
		test.log(Status.INFO, "Status code for get all user logs is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for get all user logs is => " + response.getBody().asPrettyString());
	}

	@Test(priority = 2)
	public void verify_Get_All_User_Logs_With_Authorization() {
		test = BaseTest.extent.createTest("Get all user logs with authorization");

		Response response = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllUserLogsEndpoint);

		BodyValidation.responseValidation(response, 200);
		test.log(Status.INFO, "Status code for get all user logs is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for get all user logs is => " + response.getBody().asPrettyString());
	}
}

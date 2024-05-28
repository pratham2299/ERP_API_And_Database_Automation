package in.biencaps.erp.api.testcases;

import org.apache.logging.log4j.*;

import org.testng.annotations.Test;

import in.biencaps.erp.api.bodyValidations.*;
import in.biencaps.erp.api.endpoints.*;
import in.biencaps.erp.api.payloads.*;
import in.biencaps.erp.api.responses.*;
import in.biencaps.erp.api.utilities.*;
import io.restassured.response.*;

public class LoginEmployeeAPITestCases extends BaseTest {
	public static final Logger log = LogManager.getLogger(LoginEmployeeAPITestCases.class);

	public static String authToken;

	@Test(priority = 1, dataProvider = "TestDataForLoginEmployee", dataProviderClass = DataProviderForLoginEmployee.class)
	public void verify_Login_Employee(String userId, String password) {
		test = BaseTest.extent.createTest("Login employee by giving valid data");

		String requestPayload = LoginFolderPayloads.loginEmployeePayload(userId, password);

		Response response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.loginEmployeeEndpoint);

		BaseTest.test_Method_Logs("login employee", APIEndpoints.loginEmployeeEndpoint, response);

		if (!userId.equalsIgnoreCase(Constants.adminUserId)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else if (!password.equalsIgnoreCase(Constants.adminPassword)) {
			BodyValidation.response400Validation(response);
		} else {
			BodyValidation.responseValidation(response, 200);

			authToken = response.jsonPath().getString("token");
			log.info("Admin Token is => " + authToken + "\n");
		}
	}

	public static String verify_Login_Employee_By_Giving_Valid_Data(String userId, String password) {
		String requestPayload = LoginFolderPayloads.loginEmployeePayload(userId, password);

		Response response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.loginEmployeeEndpoint);

		BodyValidation.responseValidation(response, 200);

		String authToken = response.jsonPath().getString("token");
		log.info("Employee Token is => " + authToken);

		return authToken;
	}
}

package in.task_erp_api.testcases;

import org.apache.logging.log4j.*;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import in.task_erp_api.bodyValidations.*;
import in.task_erp_api.endpoints.*;
import in.task_erp_api.payloads.*;
import in.task_erp_api.responses.*;
import io.restassured.response.*;

public class LoginEmployeeAPITestCases extends BaseTest {
	public static final Logger log = LogManager.getLogger(LoginEmployeeAPITestCases.class);

	public Response response;
	public static String authToken;

	public static String password = "Pass@123";

	@Test(priority = 1)
	public void verify_Login_Employee_By_Giving_Valid_Data() {
		test = BaseTest.extent.createTest("Login employee by giving valid data");

		String requestPayload = LoginFolderPayloads.loginEmployeePayload("INC004", password);

		response = Responses.postRequestWithoutAuthorization(requestPayload, APIEndpoints.loginEmployeeEndpoint);

		BodyValidation.responseValidation(response, 200);
		test.log(Status.INFO, "Status code for login employee is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for login employee is => " + response.getBody().asPrettyString());

		authToken = response.jsonPath().getString("token");
		log.info("Token is => " + authToken + "\n");
	}

	@Test(priority = 2)
	public void verify_Login_Employee_By_Giving_Invalid_LoginId() {
		test = BaseTest.extent.createTest("Login employee by giving invalid login Id");

		String requestPayload = LoginFolderPayloads.loginEmployeePayload("INC0120", "Pass@123");

		response = Responses.postRequestWithoutAuthorization(requestPayload, APIEndpoints.loginEmployeeEndpoint);

		BodyValidation.responseValidation(response, "Not Found", 404);
		test.log(Status.INFO, "Status code for login employee is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for login employee is => " + response.getBody().asPrettyString());
	}

	@Test(priority = 3)
	public void verify_Login_Employee_By_Giving_Invalid_Password() {
		test = BaseTest.extent.createTest("Login employee by giving invalid password");

		String requestPayload = LoginFolderPayloads.loginEmployeePayload("INC012", "Pass@12345");

		response = Responses.postRequestWithoutAuthorization(requestPayload, APIEndpoints.loginEmployeeEndpoint);

		BodyValidation.response400Validation(response);
		test.log(Status.INFO, "Status code for login employee is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for login employee is => " + response.getBody().asPrettyString());
	}
}

package in.task_erp_api.testcases;

import java.util.*;

import org.apache.logging.log4j.*;
import static org.testng.Assert.*;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import in.task_erp_api.bodyValidations.*;
import in.task_erp_api.endpoints.*;
import in.task_erp_api.payloads.*;
import in.task_erp_api.responses.*;
import in.task_erp_api.utilities.*;
import io.restassured.response.*;

public class RegularTaskFolderAPITestCases extends BaseTest {
	public static final Logger log = LogManager.getLogger(RegularTaskFolderAPITestCases.class);
	public static int newCreatedRegularTaskId;
	public static String newCreatedRegularTask;

	public static List<Integer> regularTaskIds;
	public static List<String> regularTasks;

	private Response response;

	@Test(priority = 1)
	public void verify_Add_Regular_Task_Without_Authorization() {
		test = BaseTest.extent.createTest("Add regular task without authorization");

		String requestPayload = RegularTaskFolderPayloads.addRegularTaskPayload("Closure Mail", 26);

		Response response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.addRegularTaskEndpoint);

		BodyValidation.response401Validation(response);
		test.log(Status.INFO, "API Endpoint for add regular task is => " + APIEndpoints.addRegularTaskEndpoint);
		test.log(Status.INFO, "Status code for add regular task is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for add regular task is => " + response.getBody().asPrettyString());
	}

	@Test(priority = 2)
	public void verify_Get_All_Regular_Task_For_Employee_Without_Authorization() {
		test = BaseTest.extent.createTest("Get all regular tasks without authorization");

		Response response = Responses.getRequestWithoutAuthorization(APIEndpoints.getAllRegularTaskEndpoint);

		BodyValidation.response401Validation(response);
		test.log(Status.INFO, "API Endpoint for get all regular tasks is => " + APIEndpoints.getAllRegularTaskEndpoint);
		test.log(Status.INFO, "Status code for get all regular tasks is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for get all regular tasks is => " + response.getBody().asPrettyString());
	}

	@Test(priority = 3)
	public void verify_Update_Regular_Task_Without_Authorization() {
		test = BaseTest.extent.createTest("Update regular task without authorization");

		String requestPayload = RegularTaskFolderPayloads.giveRegularTaskPayloadForUpdateRegularTask(10,
				"Closure Mail");

		Response response = Responses.putRequestWithoutAuthorization(requestPayload,
				APIEndpoints.updateRegularTaskEndpoint);

		BodyValidation.response401Validation(response);
		test.log(Status.INFO, "API Endpoint for update regular task is => " + APIEndpoints.updateRegularTaskEndpoint);
		test.log(Status.INFO, "Status code for update regular task is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for update regular task is => " + response.getBody().asPrettyString());
	}

	@Test(priority = 4)
	public void verify_Delete_Regular_Task_Without_Authorization() {
		test = BaseTest.extent.createTest("Delete regular task without authorization");

		Response response = Responses
				.deleteRequestWithoutAuthorizationAndPathParameter(APIEndpoints.deleteRegularTaskEndpoint, 40);

		BodyValidation.response401Validation(response);
		test.log(Status.INFO, "API Endpoint for delete regular task is => " + APIEndpoints.deleteRegularTaskEndpoint);
		test.log(Status.INFO, "Status code for delete regular task is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for delete regular task is => " + response.getBody().asPrettyString());
	}

	@Test(priority = 5)
	public void verify_Get_All_Regular_Task_With_Authorization() {
		verify_Get_All_Regular_Tasks_API_With_Authorization("after added new regular task");
	}

	@Test(priority = 6, dataProvider = "TestDataForAddRegularTask", dataProviderClass = DataProvidersForRegularTaskFolder.class)
	public void verify_Add_Regular_Task_With_Authorization(String regularTaskName, int employeeId) {
		test = BaseTest.extent.createTest("Add regular task with valid and invalid data and with authorization");

		String requestPayload = RegularTaskFolderPayloads.addRegularTaskPayload(regularTaskName, employeeId);

		Response response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.addRegularTaskEndpoint);
		test.log(Status.INFO, "API Endpoint for add regular task is => " + APIEndpoints.addRegularTaskEndpoint);
		test.log(Status.INFO, "Request payload for add regular task is => " + requestPayload);
		test.log(Status.INFO, "Status code for add regular task is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for add regular task is => " + response.getBody().asPrettyString());

		if (regularTaskName.isBlank()) {
			BodyValidation.response400Validation(response);
		} else if (employeeId != 26 && EmployeeFolderAPITestCases.employeeIds.contains(employeeId)) {
			BodyValidation.responseValidation(response, "Unauthorized", 401);
		} else if (regularTasks.contains(regularTaskName)) {
			BodyValidation.responseValidation(response, "Conflict", 409);
		} else if (EmployeeFolderAPITestCases.employeeIds.contains(employeeId) == false) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			BodyValidation.responseValidation(response, 201);

			verify_Get_All_Regular_Tasks_API_With_Authorization("after added new regular task");

			assertEquals(newCreatedRegularTask, regularTaskName);
		}
	}

	@Test(priority = 7, dataProvider = "TestDataForUpdateRegularTask", dataProviderClass = DataProvidersForRegularTaskFolder.class)
	public void verify_Update_Regular_Task_With_Authorization(int regularTaskId, String regularTaskName) {
		test = BaseTest.extent.createTest("Update regular task with valid and invalid data and with authorization");

		String requestPayload = RegularTaskFolderPayloads.updateRegularTaskPayload(regularTaskId, regularTaskName);

		Response response = Responses.putRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.updateRegularTaskEndpoint);
		test.log(Status.INFO, "API Endpoint for update regular task is => " + APIEndpoints.updateRegularTaskEndpoint);
		test.log(Status.INFO, "Request payload for update regular task is => " + requestPayload);
		test.log(Status.INFO, "Status code for update regular task is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for update regular task is => " + response.getBody().asPrettyString());

		if (regularTaskName.isBlank()) {
			BodyValidation.response400Validation(response);
		} else if (response.getStatusCode() == 401) {
			BodyValidation.responseValidation(response, "Unauthorized", 401);
		} else if (regularTasks.contains(regularTaskName)) {
			BodyValidation.responseValidation(response, "Conflict", 409);
		} else if (!regularTaskIds.contains(regularTaskId)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			BodyValidation.responseValidation(response, 200);

			verify_Get_All_Regular_Tasks_API_With_Authorization("after updated new regular task");

			assertEquals(newCreatedRegularTask, regularTaskName);
		}
	}

	@Test(priority = 8, dataProvider = "TestDataForDeleteRegularTask", dataProviderClass = DataProvidersForRegularTaskFolder.class)
	public void verify_Delete_Single_Regular_Task_With_Authorization(int regularTaskId) {
		test = BaseTest.extent.createTest("Delete regular task with valid and invalid data and with authorization");

		Response response = Responses.deleteRequestWithAuthorizationAndPathParameter(
				LoginEmployeeAPITestCases.authToken, APIEndpoints.deleteRegularTaskEndpoint, regularTaskId);

		String responseBody = response.getBody().asPrettyString();
		test.log(Status.INFO, "API Endpoint for delete regular task is => " + APIEndpoints.updateRegularTaskEndpoint);
		test.log(Status.INFO, "Status code for delete regular task is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for delete regular task is => " + response.getBody().asPrettyString());

		if (responseBody.equals("[]")) {
			BodyValidation.response204Validation(response);
		} else if (response.getStatusCode() == 401) {
			BodyValidation.responseValidation(response, "Unauthorized", 401);
		} else if (response.getStatusCode() == 403) {
			BodyValidation.responseValidation(response, "Forbidden", 403);
		} else if (!regularTaskIds.contains(regularTaskId)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			BodyValidation.responseValidation(response, 200);

			verify_Get_All_Regular_Tasks_API_With_Authorization("after deleted new regular task");
		}
	}

	public void verify_Get_All_Regular_Tasks_API_With_Authorization(String message) {
		test = BaseTest.extent.createTest("Get all regular tasks with authorization");

		response = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllRegularTaskEndpoint);

		BodyValidation.responseValidation(response, 200);
		test.log(Status.INFO, "API Endpoint for get all regular tasks is => " + APIEndpoints.updateRegularTaskEndpoint);
		test.log(Status.INFO, "Status code for get all regular tasks is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for get all regular tasks is => " + response.getBody().asPrettyString());

		regularTaskIds = response.jsonPath().getList("regularTaskId");
		log.info("List of regular task Ids " + message + " are: " + regularTaskIds);

		regularTasks = response.jsonPath().getList("regularTaskName");
		log.info("List of regular tasks " + message + " are: " + regularTasks + "\n");

		newCreatedRegularTaskId = response.jsonPath().getInt("max { it.regularTaskId }.regularTaskId");
		log.info("New regular task Id " + message + " is: " + newCreatedRegularTaskId);

		newCreatedRegularTask = response.jsonPath()
				.getString("find { it.regularTaskId == " + newCreatedRegularTaskId + " }.regularTaskName");
		log.info("New regular task Id " + message + " is: " + newCreatedRegularTask);
	}
}

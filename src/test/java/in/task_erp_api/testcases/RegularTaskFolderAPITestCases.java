package in.task_erp_api.testcases;

import java.util.*;

import org.apache.logging.log4j.*;
import static org.testng.Assert.*;

import org.testng.annotations.Test;

import in.task_erp_api.bodyValidations.*;
import in.task_erp_api.endpoints.*;
import in.task_erp_api.payloads.*;
import in.task_erp_api.responses.*;
import in.task_erp_api.utilities.*;
import io.restassured.response.*;

public class RegularTaskFolderAPITestCases {
	public static final Logger log = LogManager.getLogger(RegularTaskFolderAPITestCases.class);
	public static int newCreatedRegularTaskId;
	public static String newCreatedRegularTask;

	public static List<Integer> regularTaskIds;
	public List<String> regularTasks;

	private Response response;

	@Test(priority = 1)
	public void verifyAddRegularTaskWithoutAuthorization() {
		String requestPayload = RegularTaskFolderPayloads.giveRegularTaskPayloadForAddRegularTask("Closure Mail", 26);

		response = Responses.postRequestWithoutAuthorization(requestPayload, APIEndpoints.addRegularTaskEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 2)
	public void verifyGetAllRegularTaskForEmployeeWithoutAuthorization() {
		response = Responses.getRequestWithoutAuthorization(APIEndpoints.getAllRegularTaskEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 3)
	public void verifyUpdateRegularTaskWithoutAuthorization() {
		String requestPayload = RegularTaskFolderPayloads.giveRegularTaskPayloadForUpdateRegularTask(10,
				"Closure Mail");

		response = Responses.putRequestWithoutAuthorization(requestPayload, APIEndpoints.updateRegularTaskEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 4)
	public void deleteRegularTaskWithoutAuthorization() {
		response = Responses.deleteRequestWithoutAuthorizationAndPathParameter(APIEndpoints.deleteRegularTaskEndpoint,
				40);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 5, dataProvider = "TestDataForAddRegularTask", dataProviderClass = DataProvidersForRegularTaskFolder.class)
	public void verifyAddRegularTaskWithAuthorization(String regularTaskNameInput, int employeeIdInput) {
		String requestPayload = RegularTaskFolderPayloads.giveRegularTaskPayloadForAddRegularTask(regularTaskNameInput,
				employeeIdInput);

		response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.addRegularTaskEndpoint);

		if (regularTaskNameInput.equalsIgnoreCase("")) {
			BodyValidation.response400Validation(response);
		} else if (employeeIdInput != 26 && EmployeeFolderAPITestCases.employeeIds.contains(employeeIdInput)) {
			BodyValidation.responseValidation(response, "Unauthorized", 401);
		} else if (EmployeeFolderAPITestCases.employeeIds.contains(employeeIdInput) == false) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			BodyValidation.responseValidation(response, 201);

			verifyGetAllRegularTasksAPIWithAuthorization("after added new regular task");

			assertEquals(newCreatedRegularTask, regularTaskNameInput, "Invalid new created regular task value");

//			int newCreatedRegularTaskEmployeeId = response.jsonPath()
//					.getInt("find { it.regularTaskId == " + newCreatedRegularTaskId + " }.employee.empId");
//			log.info("New created Regular Task employee Id is: " + newCreatedRegularTaskEmployeeId);
//			assertEquals(newCreatedRegularTaskEmployeeId, employeeIdInput,
//					"Invalid new created regular task employee Id");
		}
	}

	@Test(priority = 6)
	public void verifyGetAllRegularTaskWithAuthorization() {
		verifyGetAllRegularTasksAPIWithAuthorization("after added new regular task");
	}

	@Test(priority = 7, dataProvider = "TestDataForUpdateRegularTask", dataProviderClass = DataProvidersForRegularTaskFolder.class)
	public void verifyUpdateRegularTaskWithAuthorization(int regularTaskIdInput, String regularTaskNameInput) {
		String requestPayload = RegularTaskFolderPayloads.giveRegularTaskPayloadForUpdateRegularTask(regularTaskIdInput,
				regularTaskNameInput);

		response = Responses.putRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.updateRegularTaskEndpoint);

		if (regularTaskNameInput.equals("")) {
			BodyValidation.response400Validation(response);
		} else if (response.getStatusCode() == 401) {
			BodyValidation.responseValidation(response, "Unauthorized", 401);
		} else if (!regularTaskIds.contains(regularTaskIdInput)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			BodyValidation.responseValidation(response, 200);

			verifyGetAllRegularTasksAPIWithAuthorization("after updated new regular task");

			assertEquals(newCreatedRegularTask, regularTaskNameInput);
		}
	}

	@Test(priority = 8, dataProvider = "TestDataForDeleteRegularTask", dataProviderClass = DataProvidersForRegularTaskFolder.class)
	public void verifyDeleteSingleRegularTaskWithAuthorization(int regularTaskIdInput) {
		response = Responses.deleteRequestWithAuthorizationAndPathParameter(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.deleteRegularTaskEndpoint, regularTaskIdInput);

		if (response.getBody().asPrettyString().equals("[]")) {
			BodyValidation.response204Validation(response);
		} else if (response.getStatusCode() == 401) {
			BodyValidation.responseValidation(response, "Unauthorized", 401);
		} else if (response.getStatusCode() == 403) {
			BodyValidation.responseValidation(response, "Forbidden", 403);
		} else if (!regularTaskIds.contains(regularTaskIdInput)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			BodyValidation.responseValidation(response, 200);

			verifyGetAllRegularTasksAPIWithAuthorization("after deleted new regular task");
		}
	}

	public void verifyGetAllRegularTasksAPIWithAuthorization(String message) {
		response = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllRegularTaskEndpoint);

		BodyValidation.responseValidation(response, 200);

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

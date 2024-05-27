package in.biencaps.erp.api.testcases;

import java.util.*;

import org.apache.logging.log4j.*;

import org.testng.annotations.*;

import com.aventstack.extentreports.*;
import com.fasterxml.jackson.core.JsonProcessingException;

import in.biencaps.erp.api.bodyValidations.*;
import in.biencaps.erp.api.endpoints.*;
import in.biencaps.erp.api.payloads.*;
import in.biencaps.erp.api.responses.*;
import in.biencaps.erp.api.utilities.*;
import io.restassured.response.*;
import static org.testng.Assert.*;

public class StatusFolderAPITestCases extends BaseTest {
	public static final Logger log = LogManager.getLogger(StatusFolderAPITestCases.class);
	public static int newCreatedStatusId;
	private int newCreatedStatusLevel;
	public static String newCreatedStatus;
	private String newCreatedStatusColor;
	private String newCreatedStatusColorCode;

	public static List<Integer> statusIds;
	public static List<Integer> statusLevels;
	public static List<String> statuses;
	public static List<String> statusColors;
	public static List<String> statusColorCodes;

	protected Response response;

	@Test(priority = 1)
	public void verify_Add_Status_Without_Authorization() {
		test = BaseTest.extent.createTest("Add status without authorization");

		String requestPayload = StatusFolderPayloads.addStatusPayload("Cancel", 8, "Yellow", "#123");

		Response response = Responses.postRequestWithoutAuthorization(requestPayload, APIEndpoints.addStatusEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("add status", APIEndpoints.addStatusEndpoint, response);
	}

	@Test(priority = 2)
	public void verify_Get_All_Status_Without_Authorization() {
		test = BaseTest.extent.createTest("Get All statuses without authorization");

		Response response = Responses.getRequestWithoutAuthorization(APIEndpoints.getAllStatusesEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("get all statuses", APIEndpoints.getAllStatusesEndpoint, response);
	}

	@Test(priority = 3)
	public void verify_Update_Status_Without_Authorization() throws Throwable {
		test = BaseTest.extent.createTest("Update status without authorization");

		Response getStatusResponse = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllStatusesEndpoint);

		String requestPayload = StatusFolderPayloads.updateStatusWithMaxIdPayload(
				getStatusResponse.getBody().asPrettyString(), 9, "Cancel", 10, "Yellow", "#4321");

		Response response = Responses.putRequestWithoutAuthorization(requestPayload, APIEndpoints.updateStatusEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("update status", APIEndpoints.updateStatusEndpoint, response);
	}

	@Test(priority = 4)
	public void verify_Delete_Status_Without_Authorization() {
		test = BaseTest.extent.createTest("Delete status without authorization");

		Response response = Responses.deleteRequestWithoutAuthorizationAndQueryParameter("statusName", "Cancel",
				APIEndpoints.deleteStatusEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("delete status", APIEndpoints.deleteStatusEndpoint, response);
	}

	@Test(priority = 5)
	public void verify_Get_All_Status_With_Authorization() {
		verify_Get_All_Statuses_API_With_Authorization("before add new status");
	}

	@Test(priority = 6, dataProvider = "TestDataForAddStatus", dataProviderClass = DataProvidersForStatusFolder.class, enabled = false)
	public void verify_Add_Status_With_Authorization(String statusName, int statusLevel, String statusColor,
			String statusColorCode) throws JsonProcessingException {
		String requestPayload = StatusFolderPayloads.addStatusPayload(statusName, statusLevel, statusColor,
				statusColorCode);
		test = BaseTest.extent.createTest("Add status with valid and invalid data and with authorization");

		Response response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.addStatusEndpoint);

		BaseTest.test_Method_Logs("add status", APIEndpoints.addStatusEndpoint, requestPayload, response);

		if (statusName.isBlank() || statusColor.isBlank() || statusColorCode.isBlank()) {
			BodyValidation.response400Validation(response);
		} else if (statuses.contains(statusName) || statusLevels.contains(statusLevel)
				|| statusColors.contains(statusColor) || statusColorCodes.contains(statusColorCode)) {
			BodyValidation.responseValidation(response, "Conflict", 409);
		} else {
			BodyValidation.responseValidation(response, 201);

			verify_Get_All_Statuses_API_With_Authorization("after added new status");

			verify_New_Created_Status_Details("after added new status");

			assertEquals(newCreatedStatus, statusName);

			assertEquals(newCreatedStatusLevel, statusLevel);

			assertEquals(newCreatedStatusColor, statusColor);

			assertEquals(newCreatedStatusColorCode, statusColorCode);
		}
	}

	@Test(priority = 7, dataProvider = "TestDataForUpdateStatus", dataProviderClass = DataProvidersForStatusFolder.class, enabled = false)
	public void verify_Update_Status_With_Authorization(int statusId, String statusName, int statusLevel,
			String statusColor, String statusColorCode) throws Throwable {
		test = BaseTest.extent.createTest("Update status with valid and invalid data and with authorization");

		Response getStatusResponse = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllStatusesEndpoint);

		String requestPayload = StatusFolderPayloads.updateStatusWithMaxIdPayload(
				getStatusResponse.getBody().asPrettyString(), statusId, statusName, statusLevel, statusColor,
				statusColorCode);

		Response response = Responses.putRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.updateStatusEndpoint);

		String responseBody = response.getBody().asPrettyString();

		BaseTest.test_Method_Logs("update status", APIEndpoints.updateStatusEndpoint, requestPayload, response);

		if (statusName.isBlank() || statusColor.isBlank() || statusColorCode.isBlank()) {
			BodyValidation.response400Validation(response);
		} else if (!statusIds.contains(statusId)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else if (statuses.contains(statusName) || statusLevels.contains(statusLevel)
				|| statusColors.contains(statusColor) || statusColorCodes.contains(statusColorCode)) {
			BodyValidation.responseValidation(response, "Conflict", 409);
		} else {
			int contentLength = responseBody.length();
			BodyValidation.responseValidation(response, 200, String.valueOf(contentLength));
			assertEquals(responseBody, "Status Updated Successfully");

			verify_Get_All_Statuses_API_With_Authorization("after updated new status");

			verify_New_Created_Status_Details("after updated new status");
			assertEquals(newCreatedStatus, statusName);

			assertEquals(newCreatedStatusLevel, statusLevel);

			assertEquals(newCreatedStatusColor, statusColor);

			assertEquals(newCreatedStatusColorCode, statusColorCode);
		}
	}

	@Test(priority = 8, dataProvider = "TestDataForDeleteStatus", dataProviderClass = DataProvidersForStatusFolder.class, enabled = false)
	public void verify_Delete_Single_Status_With_Authorization(String statusName) {
		test = BaseTest.extent.createTest("Delete status with valid and invalid data and with authorization");

		Response response = Responses.deleteRequestWithAuthorizationAndQueryParameter("statusName", statusName,
				LoginEmployeeAPITestCases.authToken, APIEndpoints.deleteStatusEndpoint);

		String responseBody = response.getBody().asPrettyString();

		BaseTest.test_Method_Logs_With_Query_Parameter("delete status", APIEndpoints.deleteStatusEndpoint, statusName,
				response);

		if (response.getBody().asPrettyString().equals("[]")) {
			BodyValidation.response204Validation(response);
		} else if (response.getStatusCode() == 403) {
			BodyValidation.responseValidation(response, "Forbidden", 403);
		} else if (!statuses.contains(statusName)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			int contentLength = responseBody.length();
			BodyValidation.responseValidation(response, 200, String.valueOf(contentLength));
			assertEquals(responseBody, "Status Deleted Successfully");

			verify_Get_All_Statuses_API_With_Authorization("after deleted new status");
		}
	}

	public void verify_New_Created_Status_Details(String message) {
		newCreatedStatusId = response.jsonPath().getInt("max { it.statusId }.statusId");
		log.info("New created status Id " + message + " is => " + newCreatedStatusId);

		newCreatedStatus = response.jsonPath().getString("find { it.statusId == " + newCreatedStatusId + " }.status");
		log.info("New created status " + message + " is => " + newCreatedStatus);

		newCreatedStatusLevel = response.jsonPath()
				.getInt("find { it.statusId == " + newCreatedStatusId + " }.statusLevel");
		log.info("New created status level " + message + " is => " + newCreatedStatusLevel);

		newCreatedStatusColor = response.jsonPath()
				.getString("find { it.statusId == " + newCreatedStatusId + " }.statusColor");
		log.info("New created status color " + message + " is => " + newCreatedStatusColor);

		newCreatedStatusColorCode = response.jsonPath()
				.getString("find { it.statusId == " + newCreatedStatusId + " }.statusColorCode");
		log.info("New created status color code " + message + " is => " + newCreatedStatusColorCode + "\n");
	}

	public void verify_Get_All_Statuses_API_With_Authorization(String message) {
		test = BaseTest.extent.createTest("Get All statues with authorization");

		response = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllStatusesEndpoint);

		BodyValidation.responseValidation(response, 200);
		test.log(Status.INFO, "API Endpoint for get all statuses is => " + APIEndpoints.getAllStatusesEndpoint);
		test.log(Status.INFO, "Status code for get all statuses is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for get all statuses is => " + response.getBody().asPrettyString());

		statusIds = response.jsonPath().getList("statusId");
		log.info("List of status Ids " + message + " are => " + statusIds);

		statuses = response.jsonPath().getList("status");
		log.info("List of status names " + message + " are => " + statuses);

		statusLevels = response.jsonPath().getList("statusLevel");
		log.info("List of status levels " + message + " are => " + statusLevels);

		statusColors = response.jsonPath().getList("statusColor");
		log.info("List of status colors " + message + " are => " + statusColors);

		statusColorCodes = response.jsonPath().getList("statusColorCode");
		log.info("List of status colors " + message + " are => " + statusColorCodes + "\n");
	}
}

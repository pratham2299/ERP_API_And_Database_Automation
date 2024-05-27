package in.biencaps.erp.api.testcases;

import org.apache.logging.log4j.*;
import org.testng.annotations.*;

import in.biencaps.erp.api.bodyValidations.*;
import in.biencaps.erp.api.endpoints.*;
import in.biencaps.erp.api.payloads.*;
import in.biencaps.erp.api.responses.*;
import in.biencaps.erp.api.utilities.*;
import io.restassured.response.*;

import static org.testng.Assert.*;

import java.util.*;

public class PriorityFolderAPITestCases extends BaseTest {
	public static final Logger log = LogManager.getLogger(PriorityFolderAPITestCases.class);
	public static int newCreatedPriorityId;
	private int newCreatedPriorityLevel;
	public static String newCreatedPriority;
	private String newCreatedPriorityColor;
	private String newCreatedPriorityColorCode;

	public static List<Integer> priorityIds;
	public static List<Integer> priorityLevels;
	public static List<String> priorities;
	public static List<String> priorityColors;
	public static List<String> priorityColorCodes;

	public Response response;

	@Test(priority = 1)
	public void verify_Add_Priority_Without_Authorization() {
		test = BaseTest.extent.createTest("Add priority without authorization");

		String requestPayload = PriorityFolderPayloads.addPriorityPayload("Priority", 10, "Yellow", "#123");

		Response response = Responses.postRequestWithoutAuthorization(requestPayload, APIEndpoints.addPriorityEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("add priority", APIEndpoints.addPriorityEndpoint, response);
	}

	@Test(priority = 2)
	public void verify_Get_All_Priority_Without_Authorization() {
		test = BaseTest.extent.createTest("Get All priorities without authorization");

		Response response = Responses.getRequestWithoutAuthorization(APIEndpoints.getAllPrioritiesEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("get all priorities", APIEndpoints.getAllPrioritiesEndpoint, response);
	}

	@Test(priority = 3)
	public void verify_Update_Priority_Without_Authorization() throws Throwable {
		test = BaseTest.extent.createTest("Update priority without authorization");

		Response getPriorityResponse = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllPrioritiesEndpoint);

		String requestPayload = PriorityFolderPayloads.updatePriorityWithMaxIdPayload(
				getPriorityResponse.getBody().asPrettyString(), 4, "Priority", 10, "Yellow", "#123");

		Response response = Responses.putRequestWithoutAuthorization(requestPayload,
				APIEndpoints.updatePriorityEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("update priority", APIEndpoints.updatePriorityEndpoint, response);
	}

	@Test(priority = 4)
	public void verify_Delete_Single_Priority_Without_Authorization() {
		test = BaseTest.extent.createTest("Delete priority without authorization");

		Response response = Responses.deleteRequestWithoutAuthorizationAndQueryParameter("priorityName", "Priority",
				APIEndpoints.deletePriorityEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("delete priority", APIEndpoints.deletePriorityEndpoint, response);
	}

	@Test(priority = 5)
	public void verify_Get_All_Priorities_With_Authorization() {
		verify_Get_All_Priorities_API_With_Authorization("before add new priority");
	}

	@Test(priority = 6, dataProvider = "TestDataForAddPriority", dataProviderClass = DataProvidersForPriorityFolder.class, enabled = false)
	public void verify_Add_Priority_With_Authorization(String priorityName, int priorityLevel, String priorityColor,
			String priorityColorCode) {
		test = BaseTest.extent.createTest("Add priority with valid and invalid data and with authorization");

		String requestPayload = PriorityFolderPayloads.addPriorityPayload(priorityName, priorityLevel, priorityColor,
				priorityColorCode);

		Response response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.addPriorityEndpoint);

		BaseTest.test_Method_Logs("add priority", APIEndpoints.addPriorityEndpoint, requestPayload, response);

		if (priorityName.isBlank() || priorityColor.isBlank() || priorityColorCode.isBlank()) {
			BodyValidation.response400Validation(response);
		} else if (priorities.contains(priorityName) || priorityLevels.contains(priorityLevel)
				|| priorityColors.contains(priorityColor) || priorityColorCodes.contains(priorityColorCode)) {
			BodyValidation.responseValidation(response, "Unprocessable Entity", 422);
		} else {
			BodyValidation.responseValidation(response, 201);

			verify_Get_All_Priorities_API_With_Authorization("after added new priority");

			verify_New_Created_Priority_Details("after added new priority");

			assertEquals(newCreatedPriority, priorityName);

			assertEquals(newCreatedPriorityLevel, priorityLevel);

			assertEquals(newCreatedPriorityColor, priorityColor);

			assertEquals(newCreatedPriorityColorCode, priorityColorCode);
		}
	}

	@Test(priority = 7, dataProvider = "TestDataForUpdatePriority", dataProviderClass = DataProvidersForPriorityFolder.class, enabled = false)
	public void verify_Update_Priority_With_Authorization(int priorityId, String priorityName, int priorityLevel,
			String priorityColor, String priorityColorCode) throws Throwable {
		test = BaseTest.extent.createTest("Update priority with valid and invalid data and with authorization");

		Response getPriorityResponse = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllPrioritiesEndpoint);

		String requestPayload = PriorityFolderPayloads.updatePriorityWithMaxIdPayload(
				getPriorityResponse.getBody().asPrettyString(), priorityId, priorityName, priorityLevel, priorityColor,
				priorityColorCode);

		Response response = Responses.putRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.updatePriorityEndpoint);

		String responseBody = response.getBody().asPrettyString();
		BaseTest.test_Method_Logs("update priority", APIEndpoints.updatePriorityEndpoint, requestPayload, response);

		if (priorityName.isBlank() || priorityColor.isBlank() || priorityColorCode.isBlank()) {
			BodyValidation.response400Validation(response);
		} else if (!priorityIds.contains(priorityId)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else if (priorities.contains(priorityName) || priorityLevels.contains(priorityLevel)
				|| priorityColors.contains(priorityColor) || priorityColorCodes.contains(priorityColorCode)) {
			BodyValidation.responseValidation(response, "Conflict", 409);
		} else {
			int contentLength = responseBody.length();
			BodyValidation.responseValidation(response, 200, String.valueOf(contentLength));
			assertEquals(responseBody, "Priority Updated Successfully");

			verify_Get_All_Priorities_API_With_Authorization("after updated new priority");

			verify_New_Created_Priority_Details("after updated new priority");

			assertEquals(newCreatedPriority, priorityName);

			assertEquals(newCreatedPriorityLevel, priorityLevel);

			assertEquals(newCreatedPriorityColor, priorityColor);

			assertEquals(newCreatedPriorityColorCode, priorityColorCode);
		}
	}

	@Test(priority = 8, dataProvider = "TestDataForDeletePriority", dataProviderClass = DataProvidersForPriorityFolder.class, enabled = false)
	public void verify_Delete_Single_Priority_With_Authorization(String priorityName) {
		test = BaseTest.extent.createTest("Delete priority with valid and invalid data and with authorization");

		Response response = Responses.deleteRequestWithAuthorizationAndQueryParameter("priorityName", priorityName,
				LoginEmployeeAPITestCases.authToken, APIEndpoints.deletePriorityEndpoint);

		String responseBody = response.getBody().asPrettyString();
		BaseTest.test_Method_Logs_With_Query_Parameter("delete priority", APIEndpoints.deletePriorityEndpoint,
				priorityName, response);

		if (response.getBody().asPrettyString().equals("[]")) {
			BodyValidation.response204Validation(response);
		} else if (response.getStatusCode() == 403) {
			BodyValidation.responseValidation(response, "Forbidden", 403);
		} else if (!priorities.contains(priorityName)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			int contentLength = responseBody.length();
			BodyValidation.responseValidation(response, 200, String.valueOf(contentLength));
			assertEquals(responseBody, "Priority Deleted Successfully");

			verify_Get_All_Priorities_API_With_Authorization("after deleted new priority");
		}
	}

	public void verify_New_Created_Priority_Details(String message) {
		newCreatedPriorityId = response.jsonPath().getInt("max { it.priorityId }.priorityId");
		log.info("New created priority Id " + message + " is => " + newCreatedPriorityId);

		newCreatedPriority = response.jsonPath()
				.getString("find { it.priorityId == " + newCreatedPriorityId + " }.priority");
		log.info("New created priority " + message + " is => " + newCreatedPriority);

		newCreatedPriorityLevel = response.jsonPath()
				.getInt("find { it.priorityId == " + newCreatedPriorityId + " }.priorityLevel");
		log.info("New created priority level " + message + " is => " + newCreatedPriorityLevel);

		newCreatedPriorityColor = response.jsonPath()
				.getString("find { it.priorityId == " + newCreatedPriorityId + " }.priorityColor");
		log.info("New created priority color " + message + " is => " + newCreatedPriorityColor);

		newCreatedPriorityColorCode = response.jsonPath()
				.getString("find { it.priorityId == " + newCreatedPriorityId + " }.priorityColorCode");
		log.info("New created priority color code " + message + " is => " + newCreatedPriorityColorCode + "\n");
	}

	public void verify_Get_All_Priorities_API_With_Authorization(String message) {
		test = BaseTest.extent.createTest("Get All priorities with authorization");

		response = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllPrioritiesEndpoint);

		BodyValidation.responseValidation(response, 200);

		BaseTest.test_Method_Logs("get all priorities", APIEndpoints.getAllPrioritiesEndpoint, response);

		priorityIds = response.jsonPath().getList("priorityId");
		log.info("List of priority Ids " + message + " are => " + priorityIds);

		priorities = response.jsonPath().getList("priority");
		log.info("List of priority names " + message + " are => " + priorities);

		priorityLevels = response.jsonPath().getList("priorityLevel");
		log.info("List of priority levels " + message + " are => " + priorityLevels);

		priorityColors = response.jsonPath().getList("priorityColor");
		log.info("List of priority colors " + message + " are => " + priorityColors);

		priorityColorCodes = response.jsonPath().getList("priorityColorCode");
		log.info("List of priority color codes " + message + " are => " + priorityColorCodes + "\n");
	}
}

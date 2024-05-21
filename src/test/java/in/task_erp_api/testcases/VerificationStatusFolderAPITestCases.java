package in.task_erp_api.testcases;

import static org.testng.Assert.*;

import java.util.*;

import org.apache.logging.log4j.*;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import in.task_erp_api.bodyValidations.*;
import in.task_erp_api.endpoints.*;
import in.task_erp_api.payloads.*;
import in.task_erp_api.responses.*;
import in.task_erp_api.utilities.*;
import io.restassured.response.Response;

public class VerificationStatusFolderAPITestCases extends BaseTest {
	public static final Logger log = LogManager.getLogger(VerificationStatusFolderAPITestCases.class);

	public static int newCreatedVerificationStatusId;
	public static String newCreatedVerificationStatus;
	public static int newCreatedVerificationLevel;
	public static String newCreatedVerificationStatusColor;
	public static String newCreatedVerificationStatusColorCode;

	public static List<Integer> verificationStatusIds;
	public static List<String> verificationStatuses;
	public static List<Integer> verificationLevels;
	public static List<String> verificationColors;
	public static List<String> verificationColorCodes;

	public Response response;

	@Test(priority = 1)
	public void verify_Add_Verification_Status_Without_Authorization() {
		test = BaseTest.extent.createTest("Add verification status without authorization");

		String requestPayload = VerificationFolderPayloads.addVerificationPayload("Verified", 10, "Red", "#123");

		Response response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.addVerificationStatusEndpoint);

		BodyValidation.response401Validation(response);
		test.log(Status.INFO, "Status code for add verification status is: " + response.getStatusCode());
		test.log(Status.INFO, "Response for add verification status is: " + response.getBody().asPrettyString());
	}

	@Test(priority = 2)
	public void verify_Get_All_Verification_Statuses_Without_Authorization() {
		test = BaseTest.extent.createTest("Get all verification statuses without authorization");

		Response response = Responses.getRequestWithoutAuthorization(APIEndpoints.getAllVerificationStatusesEndpoint);

		BodyValidation.response401Validation(response);
		test.log(Status.INFO, "Status code for get all verification statuses is: " + response.getStatusCode());
		test.log(Status.INFO, "Response for get all verification statuses is: " + response.getBody().asPrettyString());
	}

	@Test(priority = 3)
	public void verify_Update_Verification_Status_Without_Authorization() throws Throwable {
		test = BaseTest.extent.createTest("Update verification status without authorization");

		Response getVerificationsResponse = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllVerificationStatusesEndpoint);

		String requestPayload = VerificationFolderPayloads.updateVerificationWithMaxIdPayload(
				getVerificationsResponse.getBody().asPrettyString(), 5, "Verified", 10, "Red", "#123");

		Response response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.updateVerificationStatusEndpoint);

		BodyValidation.response401Validation(response);
		test.log(Status.INFO, "Status code for update verification status is: " + response.getStatusCode());
		test.log(Status.INFO, "Response for update verification status is: " + response.getBody().asPrettyString());
	}

	@Test(priority = 4)
	public void verify_Delete_Verification_Status_Without_Authorization() {
		test = BaseTest.extent.createTest("Delete verification status without authorization");

		Response response = Responses.getRequestWithoutAuthorizationAndOneQueryParameter(
				APIEndpoints.deleteVerificationStatusEndpoint, "id", "8");

		BodyValidation.response401Validation(response);
		test.log(Status.INFO, "Status code for delete verification status is: " + response.getStatusCode());
		test.log(Status.INFO, "Response for delete verification status is: " + response.getBody().asPrettyString());
	}

	@Test(priority = 5)
	public void verify_Get_All_Verification_Statuses_With_Authorization() {
		verify_Get_All_Verification_Statuses_API_With_Authorization("before add new verification status");
	}

	@Test(priority = 6, dataProvider = "TestDataForAddVerificationStatus", dataProviderClass = DataProvidersForVerificationStatusFolder.class)
	public void verify_Add_Verification_Status_With_Authorization(String verificationStatus, int verificationLevel,
			String verificationColor, String verificationColorCode) {
		test = BaseTest.extent.createTest("Add verification status with valid and invalid data and with authorization");

		String requestPayload = VerificationFolderPayloads.addVerificationPayload(verificationStatus, verificationLevel,
				verificationColor, verificationColorCode);

		Response response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.addVerificationStatusEndpoint);
		test.log(Status.INFO, "Request payload for add verification status is: " + requestPayload);
		test.log(Status.INFO, "Status code for add verification status is: " + response.getStatusCode());
		test.log(Status.INFO, "Response for add verification status is: " + response.getBody().asPrettyString());

		if (verificationStatus.equalsIgnoreCase("") || verificationColor.equalsIgnoreCase("")
				|| verificationColorCode.equalsIgnoreCase("")) {
			BodyValidation.response400Validation(response);
		} else if (verificationStatuses.contains(verificationStatus) || verificationLevels.contains(verificationLevel)
				|| verificationColors.contains(verificationColor)
				|| verificationColorCodes.contains(verificationColorCode)) {
			BodyValidation.responseValidation(response, "Conflict", 409);
		} else {
			BodyValidation.responseValidation(response, 200);

			verify_Get_All_Verification_Statuses_API_With_Authorization("after added new verification status");

			verifyNewCreatedVerificationStatusDetails("after added new verification status");

			assertEquals(newCreatedVerificationStatus, verificationStatus);

			assertEquals(newCreatedVerificationStatusColor, verificationColor);

			assertEquals(newCreatedVerificationStatusColorCode, verificationColorCode);
		}
	}

	@Test(priority = 7, dataProvider = "TestDataForUpdateVerificationStatus", dataProviderClass = DataProvidersForVerificationStatusFolder.class)
	public void verify_Update_Verification_Status_With_Authorization(int verificationStatusId,
			String verificationStatus, int verificationLevel, String verificationColor, String verificationColorCode)
			throws Throwable {
		test = BaseTest.extent
				.createTest("Update verification status with valid and invalid data and with authorization");

		Response getVerificationsResponse = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllVerificationStatusesEndpoint);

		String requestPayload = VerificationFolderPayloads.updateVerificationWithMaxIdPayload(
				getVerificationsResponse.getBody().asPrettyString(), verificationStatusId, verificationStatus,
				verificationLevel, verificationColor, verificationColorCode);

		Response response = Responses.putRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.updateVerificationStatusEndpoint);
		test.log(Status.INFO, "Request payload for update verification status is: " + requestPayload);
		test.log(Status.INFO, "Status code for update verification status is: " + response.getStatusCode());
		test.log(Status.INFO, "Response for update verification status is: " + response.getBody().asPrettyString());

		if (verificationStatus.equalsIgnoreCase("") || verificationColor.equalsIgnoreCase("")
				|| verificationColorCode.equalsIgnoreCase("")) {
			BodyValidation.response400Validation(response);
		} else if (verificationStatusIds.contains(verificationStatusId) == false) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else if (verificationStatuses.contains(verificationStatus) || verificationLevels.contains(verificationLevel)
				|| verificationColors.contains(verificationColor)
				|| verificationColorCodes.contains(verificationColorCode)) {
			BodyValidation.responseValidation(response, "Conflict", 409);
		} else {
			BodyValidation.responseValidation(response, 200);

			verify_Get_All_Verification_Statuses_API_With_Authorization("after updated new verification status");

			verifyNewCreatedVerificationStatusDetails("after updated new verification status");

			assertEquals(newCreatedVerificationStatus, verificationStatus);

			assertEquals(newCreatedVerificationStatusColor, verificationColor);

			assertEquals(newCreatedVerificationStatusColorCode, verificationColorCode);
		}
	}

	@Test(priority = 8, dataProvider = "TestDataForDeleteVerificationStatus", dataProviderClass = DataProvidersForVerificationStatusFolder.class)
	public void verify_Delete_Single_Verification_Status_With_Authorization(int verificationStatusId) {
		test = BaseTest.extent
				.createTest("Delete verification status with valid and invalid data and with authorization");

		Response response = Responses.deleteRequestWithAuthorizationAndQueryParameter("id", verificationStatusId,
				LoginEmployeeAPITestCases.authToken, APIEndpoints.deleteVerificationStatusEndpoint);

		if (response.getBody().asPrettyString().equals("[]")) {
			BodyValidation.response204Validation(response);
		} else if (verificationStatusIds.contains(verificationStatusId) == false) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else if (response.getStatusCode() == 403) {
			BodyValidation.responseValidation(response, "Forbidden", 403);
		} else {
			BodyValidation.responseValidation(response, 200);

			verify_Get_All_Verification_Statuses_API_With_Authorization("after deleted new verification status");
		}
	}

	public void verifyNewCreatedVerificationStatusDetails(String message) {
		newCreatedVerificationStatusId = response.jsonPath()
				.getInt("max { it.verificationStatusId }.verificationStatusId");
		log.info("New created verification status Id " + message + " is: " + newCreatedVerificationStatusId);

		newCreatedVerificationStatus = response.jsonPath().getString(
				"find { it.verificationStatusId == " + newCreatedVerificationStatusId + " }.verificationStatus");
		log.info("New created verification status " + message + " is: " + newCreatedVerificationStatus);

		newCreatedVerificationLevel = response.jsonPath()
				.getInt("find { it.verificationStatusId == " + newCreatedVerificationStatusId + " }.verificationLevel");
		log.info("New created verification status level " + message + " is: " + newCreatedVerificationLevel);

		newCreatedVerificationStatusColor = response.jsonPath().getString(
				"find { it.verificationStatusId == " + newCreatedVerificationStatusId + " }.verificationColor");
		log.info("New created verification status color " + message + " is: " + newCreatedVerificationStatusColor);

		newCreatedVerificationStatusColorCode = response.jsonPath().getString(
				"find { it.verificationStatusId == " + newCreatedVerificationStatusId + " }.verificationColorCode");
		log.info("New created verification status color code " + message + " is: "
				+ newCreatedVerificationStatusColorCode + "\n");
	}

	public void verify_Get_All_Verification_Statuses_API_With_Authorization(String message) {
		test = BaseTest.extent.createTest("Get all verification statuses with authorization");

		response = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllVerificationStatusesEndpoint);

		BodyValidation.responseValidation(response, 200);
		test.log(Status.INFO, "Status code for get all verification statuses is: " + response.getStatusCode());
		test.log(Status.INFO, "Response for get all verification statuses is: " + response.getBody().asPrettyString());

		verificationStatusIds = response.jsonPath().getList("verificationStatusId");
		log.info("List of verification status Ids " + message + " are: " + verificationStatusIds);

		verificationStatuses = response.jsonPath().getList("verificationStatus");
		log.info("List of verification status names " + message + " are: " + verificationStatuses);

		verificationLevels = response.jsonPath().getList("verificationLevel");
		log.info("List of verification status levels " + message + " are: " + verificationLevels);

		verificationColors = response.jsonPath().getList("verificationColor");
		log.info("List of verification status colors " + message + " are: " + verificationColors);

		verificationColorCodes = response.jsonPath().getList("verificationColorCode");
		log.info("List of verification status color codes " + message + " are: " + verificationColorCodes + "\n");
	}
}

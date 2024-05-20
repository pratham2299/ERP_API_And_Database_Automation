package in.task_erp_api.testcases;

import static org.testng.Assert.*;

import java.util.*;

import org.apache.logging.log4j.*;
import org.testng.annotations.Test;

import in.task_erp_api.bodyValidations.*;
import in.task_erp_api.endpoints.*;
import in.task_erp_api.payloads.*;
import in.task_erp_api.responses.*;
import in.task_erp_api.utilities.*;
import io.restassured.response.Response;

public class VerificationStatusFolderAPITestCases {
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
	public void verifyAddVerificationWithoutAuthorization() {
		String requestPayload = VerificationFolderPayloads
				.giveVerificationStatusPayloadForAddVerificationStatus("Verified", 10, "Red", "#123");

		response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.addVerificationStatusEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 2)
	public void verifyGetAllVerificationesWithoutAuthorization() {
		response = Responses.getRequestWithoutAuthorization(APIEndpoints.getAllVerificationStatusesEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 3)
	public void verifyUpdateVerificationWithoutAuthorization() {
		String requestPayload = VerificationFolderPayloads.giveVerificationStatusPayloadForUpdateVerificationStatus(5,
				"Verified", 10, "Red", "#123");

		response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.updateVerificationStatusEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 4)
	public void verifyDeleteVerificationWithoutAuthorization() {
		response = Responses.getRequestWithoutAuthorizationAndOneQueryParameter(
				APIEndpoints.deleteVerificationStatusEndpoint, "id", "8");

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 5)
	public void verifyGetAllStatusWithAuthorization() {
		response = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllVerificationStatusesEndpoint);

		System.out.println(response.getBody().asPrettyString());

		BodyValidation.responseValidation(response, 200);

		verificationStatusIds = response.jsonPath().getList("verificationStatusId");
		log.info("List of verification status Ids before new verification status add are: " + verificationStatusIds);

		verificationStatuses = response.jsonPath().getList("verificationStatus");
		log.info("List of verification status names before new verification status add are: " + verificationStatuses);

		verificationLevels = response.jsonPath().getList("verificationLevel");
		log.info("List of verification status levels before new verification status add are: " + verificationLevels);

		verificationColors = response.jsonPath().getList("verificationColor");
		log.info("List of verification status colors before new verification status add are: " + verificationColors);

		verificationColorCodes = response.jsonPath().getList("verificationColorCode");
		log.info("List of verification status color codes before new verification status add are: "
				+ verificationColorCodes + "\n");

	}

	@Test(priority = 6, dataProvider = "TestDataForAddVerificationStatus", dataProviderClass = DataProvidersForVerificationStatusFolder.class)
	public void verifyAddVerificationStatusWithAuthorization(String verificationStatusInput, int verificationLevelInput,
			String verificationColorInput, String verificationColorCodeInput) {
		String requestPayload = VerificationFolderPayloads.giveVerificationStatusPayloadForAddVerificationStatus(
				verificationStatusInput, verificationLevelInput, verificationColorInput, verificationColorCodeInput);

		response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.addVerificationStatusEndpoint);

		System.out.println(response.getBody().asPrettyString());

		if (verificationStatusInput.equalsIgnoreCase("") || verificationColorInput.equalsIgnoreCase("")
				|| verificationColorCodeInput.equalsIgnoreCase("")) {
			BodyValidation.response400Validation(response);
		} else if (verificationStatuses.contains(verificationStatusInput)
				|| verificationLevels.contains(verificationLevelInput)
				|| verificationColors.contains(verificationColorInput)
				|| verificationColorCodes.contains(verificationColorCodeInput)) {
			BodyValidation.responseValidation(response, "Conflict", 409);
		} else {
			BodyValidation.responseValidation(response, 200);

			log.info("\nAfter added new verification status get all verification status API response is: ");
			verifyGetAllVerificationStatusesAPIWithAuthorization("after added new verification status");

			verifyNewCreatedVerificationStatusDetails("after added new verification status");

			assertEquals(newCreatedVerificationStatus, verificationStatusInput);

			assertEquals(newCreatedVerificationStatusColor, verificationColorInput);

			assertEquals(newCreatedVerificationStatusColorCode, verificationColorCodeInput);
		}
	}

	@Test(priority = 7, dataProvider = "TestDataForUpdateVerificationStatus", dataProviderClass = DataProvidersForVerificationStatusFolder.class, enabled = false)
	public void verifyUpdateVerificationStatusWithAuthorization(int verificationStatusIdInput,
			String verificationStatusInput, int verificationLevelInput, String verificationColorInput,
			String verificationColorCodeInput) {
		String requestPayload = VerificationFolderPayloads.giveVerificationStatusPayloadForUpdateVerificationStatus(
				verificationStatusIdInput, verificationStatusInput, verificationLevelInput, verificationColorInput,
				verificationColorCodeInput);

		response = Responses.putRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.updateVerificationStatusEndpoint);

		System.out.println(response.getBody().asPrettyString());

		if (verificationStatusInput.equalsIgnoreCase("") || verificationColorInput.equalsIgnoreCase("")
				|| verificationColorCodeInput.equalsIgnoreCase("")) {
			BodyValidation.response400Validation(response);
		} else if (verificationStatusIds.contains(verificationStatusIdInput) == false) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else if (verificationStatuses.contains(verificationStatusInput)
				|| verificationLevels.contains(verificationLevelInput)
				|| verificationColors.contains(verificationColorInput)
				|| verificationColorCodes.contains(verificationColorCodeInput)) {
			BodyValidation.responseValidation(response, "Conflict", 409);
		} else {
			BodyValidation.responseValidation(response, 200);

			log.info("\nAfter updated new verification status get all verification status API response is: ");
			verifyGetAllVerificationStatusesAPIWithAuthorization("after updated new verification status");

			verifyNewCreatedVerificationStatusDetails("after updated new verification status");

			assertEquals(newCreatedVerificationStatus, verificationStatusInput);

			assertEquals(newCreatedVerificationStatusColor, verificationColorInput);

			assertEquals(newCreatedVerificationStatusColorCode, verificationColorCodeInput);
		}
	}

	@Test(priority = 8, dataProvider = "TestDataForDeleteVerificationStatus", dataProviderClass = DataProvidersForVerificationStatusFolder.class)
	public void verifyDeleteSingleVerificationStatusWithAuthorization(int verificationStatusIdInput) {
		response = Responses.deleteRequestWithAuthorizationAndQueryParameter("id", verificationStatusIdInput,
				LoginEmployeeAPITestCases.authToken, APIEndpoints.deleteVerificationStatusEndpoint);

		System.out.println(response.getBody().asPrettyString());

		if (response.getBody().asPrettyString().equals("[]")) {
			BodyValidation.response204Validation(response);
		} else if (verificationStatusIds.contains(verificationStatusIdInput) == false) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else if (response.getStatusCode() == 403) {
			BodyValidation.responseValidation(response, "Forbidden", 403);
		} else {
			BodyValidation.responseValidation(response, 200);

			log.info("\nAfter deleted new verification status get all verification status API response is: ");
			verifyGetAllVerificationStatusesAPIWithAuthorization("after deleted new verification status");
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

	public void verifyGetAllVerificationStatusesAPIWithAuthorization(String message) {
		response = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllVerificationStatusesEndpoint);

		BodyValidation.responseValidation(response, 200);

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

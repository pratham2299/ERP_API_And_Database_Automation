package in.biencaps.erp.api.testcases;

import static org.testng.Assert.*;

import java.util.*;

import org.apache.logging.log4j.*;
import org.testng.annotations.Test;

import in.biencaps.erp.api.bodyValidations.*;
import in.biencaps.erp.api.endpoints.*;
import in.biencaps.erp.api.payloads.*;
import in.biencaps.erp.api.responses.*;
import in.biencaps.erp.api.utilities.*;
import io.restassured.response.Response;

public class VerificationFolderAPITestCases extends BaseTest {
	public static final Logger log = LogManager.getLogger(VerificationFolderAPITestCases.class);

	public static int newCreatedVerificationId;
	public static String newCreatedVerification;
	public static int newCreatedVerificationLevel;
	public static String newCreatedVerificationColor;
	public static String newCreatedVerificationColorCode;

	public static List<Integer> verificationIds;
	public static List<String> verifications;
	public static List<Integer> verificationLevels;
	public static List<String> verificationColors;
	public static List<String> verificationColorCodes;

	public Response response;

	@Test(priority = 1)
	public void verify_Add_Verification_Without_Authorization() {
		test = BaseTest.extent.createTest("Add verification status without authorization");

		String requestPayload = VerificationFolderPayloads.addVerificationPayload("Verified", 10, "Red", "#123");

		Response response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.addVerificationEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("add verification", APIEndpoints.addVerificationEndpoint, response);
	}

	@Test(priority = 2)
	public void verify_Get_All_Verificationes_Without_Authorization() {
		test = BaseTest.extent.createTest("Get all verification statuses without authorization");

		Response response = Responses.getRequestWithoutAuthorization(APIEndpoints.getAllVerificationsEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("get all verifications", APIEndpoints.getAllVerificationsEndpoint, response);
	}

	@Test(priority = 3)
	public void verify_Update_Verification_Without_Authorization() throws Throwable {
		test = BaseTest.extent.createTest("Update verification status without authorization");

		Response getVerificationsResponse = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllVerificationsEndpoint);

		String requestPayload = VerificationFolderPayloads.updateVerificationWithMaxIdPayload(
				getVerificationsResponse.getBody().asPrettyString(), 5, "Verified", 10, "Red", "#123");

		Response response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.updateVerificationEndpoint);

		BodyValidation.response401Validation(response);
		BaseTest.test_Method_Logs("update verification", APIEndpoints.updateVerificationEndpoint, response);
	}

	@Test(priority = 4)
	public void verify_Delete_Verification_Without_Authorization() {
		test = BaseTest.extent.createTest("Delete verification status without authorization");

		Response response = Responses
				.getRequestWithoutAuthorizationAndOneQueryParameter(APIEndpoints.deleteVerificationEndpoint, "id", "8");

		BodyValidation.response401Validation(response);
		BaseTest.test_Method_Logs("delete verification", APIEndpoints.deleteVerificationEndpoint, response);
	}

	@Test(priority = 5)
	public void verify_Get_All_Verificationes_With_Authorization() {
		verify_Get_All_Verifications_API_With_Authorization("before add new verification status");
	}

	@Test(priority = 6, dataProvider = "TestDataForAddVerification", dataProviderClass = DataProvidersForVerificationFolder.class)
	public void verify_Add_Verification_With_Authorization(String Verification, int verificationLevel,
			String verificationColor, String verificationColorCode) {
		test = BaseTest.extent.createTest("Add verification status with valid and invalid data and with authorization");

		String requestPayload = VerificationFolderPayloads.addVerificationPayload(Verification, verificationLevel,
				verificationColor, verificationColorCode);

		Response response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.addVerificationEndpoint);

		BaseTest.test_Method_Logs("add verification", APIEndpoints.addVerificationEndpoint, requestPayload, response);

		if (Verification.isBlank() || verificationColor.isBlank() || verificationColorCode.isBlank()) {
			BodyValidation.response400Validation(response);
		} else if (verifications.contains(Verification) || verificationLevels.contains(verificationLevel)
				|| verificationColors.contains(verificationColor)
				|| verificationColorCodes.contains(verificationColorCode)) {
			BodyValidation.responseValidation(response, "Conflict", 409);
		} else {
			BodyValidation.responseValidation(response, 201);

			verify_Get_All_Verifications_API_With_Authorization("after added new verification status");

			verifynewCreatedVerificationDetails("after added new verification status");

			assertEquals(newCreatedVerification, Verification);

			assertEquals(newCreatedVerificationColor, verificationColor);

			assertEquals(newCreatedVerificationColorCode, verificationColorCode);
		}
	}

	@Test(priority = 7, dataProvider = "TestDataForUpdateVerification", dataProviderClass = DataProvidersForVerificationFolder.class)
	public void verify_Update_Verification_With_Authorization(int VerificationId, String Verification,
			int verificationLevel, String verificationColor, String verificationColorCode) throws Throwable {
		test = BaseTest.extent
				.createTest("Update verification status with valid and invalid data and with authorization");

		Response getVerificationsResponse = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllVerificationsEndpoint);

		String requestPayload = VerificationFolderPayloads.updateVerificationWithMaxIdPayload(
				getVerificationsResponse.getBody().asPrettyString(), VerificationId, Verification, verificationLevel,
				verificationColor, verificationColorCode);

		Response response = Responses.putRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.updateVerificationEndpoint);

		String responseBody = response.getBody().asPrettyString();
		BaseTest.test_Method_Logs("update verification", APIEndpoints.updateVerificationEndpoint, requestPayload,
				response);

		if (Verification.isBlank() || verificationColor.isBlank() || verificationColorCode.isBlank()) {
			BodyValidation.response400Validation(response);
		} else if (verificationIds.contains(VerificationId) == false) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else if (verifications.contains(Verification) || verificationLevels.contains(verificationLevel)
				|| verificationColors.contains(verificationColor)
				|| verificationColorCodes.contains(verificationColorCode)) {
			BodyValidation.responseValidation(response, "Conflict", 409);
		} else {
			int contentLength = responseBody.length();
			BodyValidation.responseValidation(response, 200, String.valueOf(contentLength));
			assertEquals(responseBody, "Verification Status Updated Successfully");

			verify_Get_All_Verifications_API_With_Authorization("after updated new verification status");

			verifynewCreatedVerificationDetails("after updated new verification status");

			assertEquals(newCreatedVerification, Verification);

			assertEquals(newCreatedVerificationColor, verificationColor);

			assertEquals(newCreatedVerificationColorCode, verificationColorCode);
		}
	}

	@Test(priority = 8, dataProvider = "TestDataForDeleteVerification", dataProviderClass = DataProvidersForVerificationFolder.class)
	public void verify_Delete_Single_Verification_With_Authorization(int verificationId) {
		test = BaseTest.extent
				.createTest("Delete verification status with valid and invalid data and with authorization");

		Response response = Responses.deleteRequestWithAuthorizationAndQueryParameter("id", verificationId,
				LoginEmployeeAPITestCases.authToken, APIEndpoints.deleteVerificationEndpoint);

		String responseBody = response.getBody().asPrettyString();
		BaseTest.test_Method_Logs_With_Query_Parameter("delete verification", APIEndpoints.deleteVerificationEndpoint,
				verificationId, response);

		if (responseBody.equals("[]")) {
			BodyValidation.response204Validation(response);
		} else if (verificationIds.contains(verificationId) == false) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else if (response.getStatusCode() == 403) {
			BodyValidation.responseValidation(response, "Forbidden", 403);
		} else {
			int contentLength = responseBody.length();
			BodyValidation.responseValidation(response, 200, String.valueOf(contentLength));
			assertEquals(responseBody, "Verification Status Deleted Successfully");

			verify_Get_All_Verifications_API_With_Authorization("after deleted new verification status");
		}
	}

	public void verifynewCreatedVerificationDetails(String message) {
		newCreatedVerificationId = response.jsonPath().getInt("max { it.verificationStatusId }.verificationStatusId");
		log.info("New created verification status Id " + message + " is => " + newCreatedVerificationId);

		newCreatedVerification = response.jsonPath()
				.getString("find { it.verificationStatusId == " + newCreatedVerificationId + " }.verificationStatus");
		log.info("New created verification status " + message + " is => " + newCreatedVerification);

		newCreatedVerificationLevel = response.jsonPath()
				.getInt("find { it.verificationStatusId == " + newCreatedVerificationId + " }.verificationLevel");
		log.info("New created verification status level " + message + " is => " + newCreatedVerificationLevel);

		newCreatedVerificationColor = response.jsonPath()
				.getString("find { it.verificationStatusId == " + newCreatedVerificationId + " }.verificationColor");
		log.info("New created verification status color " + message + " is => " + newCreatedVerificationColor);

		newCreatedVerificationColorCode = response.jsonPath()
				.getString("find { it.verificationStatusId == " + newCreatedVerificationId + " }.verificationColorCode");
		log.info("New created verification status color code " + message + " is => " + newCreatedVerificationColorCode
				+ "\n");
	}

	public void verify_Get_All_Verifications_API_With_Authorization(String message) {
		test = BaseTest.extent.createTest("Get all verification statuses with authorization");

		response = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllVerificationsEndpoint);

		BodyValidation.responseValidation(response, 200);

		BaseTest.test_Method_Logs("get all verifications", APIEndpoints.getAllVerificationsEndpoint, response);

		verificationIds = response.jsonPath().getList("verificationStatusId");
		log.info("List of verification status Ids " + message + " are => " + verificationIds);

		verifications = response.jsonPath().getList("verificationStatus");
		log.info("List of verification status names " + message + " are => " + verifications);

		verificationLevels = response.jsonPath().getList("verificationLevel");
		log.info("List of verification status levels " + message + " are => " + verificationLevels);

		verificationColors = response.jsonPath().getList("verificationColor");
		log.info("List of verification status colors " + message + " are => " + verificationColors);

		verificationColorCodes = response.jsonPath().getList("verificationColorCode");
		log.info("List of verification status color codes " + message + " are => " + verificationColorCodes + "\n");
	}
}

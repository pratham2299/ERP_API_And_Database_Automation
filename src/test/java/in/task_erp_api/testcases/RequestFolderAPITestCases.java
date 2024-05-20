package in.task_erp_api.testcases;

import java.util.*;

import org.apache.logging.log4j.*;
import org.testng.annotations.Test;

import in.task_erp_api.bodyValidations.*;
import in.task_erp_api.endpoints.*;
import in.task_erp_api.payloads.*;
import in.task_erp_api.responses.*;
import in.task_erp_api.utilities.*;

import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;

public class RequestFolderAPITestCases {
	public static final Logger log = LogManager.getLogger(RequestFolderAPITestCases.class);

	public static List<Integer> requestIds;
	public static List<String> requestStatuses;

	public Response response;

	@Test(priority = 1)
	public void verifyGetAllRequestsStatusWithoutAuthorization() {
		response = Responses.getRequestWithoutAuthorization(APIEndpoints.getAllRequestStatusEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 2)
	public void verifyGetAllRequestsAnalyticWithoutAuthorization() {
		String requestPayload = RequestFolderPayloads.giveRolePayloadForGetAllRequestsAnalytic(26);
		response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.getAllRequestAnalyticsEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 3)
	public void verifyGetAllRequestsWithoutAuthorization() {
		response = Responses.getRequestWithoutAuthorizationAndOneQueryParameter(APIEndpoints.getAllRequestsEndpoint,
				"status", "Pending");

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 4)
	public void verifyGetAllRequestsFromAnotherEmployeeForSingleEmployeeWithoutAuthorization() {
		String requestPayload = RequestFolderPayloads
				.giveRolePayloadForGetAllRequestsFromAnotherEmployeeForSingleEmployee(26);
		response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.getAllRequestsFromAnotherEmployeeForSingleEmployeeEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 5)
	public void verifyGetAllMySentRequestsWithoutAuthorization() {
		String requestPayload = RequestFolderPayloads.giveRolePayloadForGetAllMySentRequests("BIE018", "Pending");
		response = Responses.postRequestWithoutAuthorization(requestPayload, APIEndpoints.getAllMySentRequestsEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 6)
	public void verifyUpdateRequestStatusWithoutAuthorization() {
		String requestPayload = RequestFolderPayloads.giveRolePayloadForUpdateRequestStatus(1130, 26, 2);
		response = Responses.putRequestWithoutAuthorization(requestPayload, APIEndpoints.updateRequestStatusEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 8)
	public void verifyGetAllRequestsStatusWithAuthorization() {
		response = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllRequestStatusEndpoint);

		BodyValidation.responseValidation(response, 200);

		requestStatuses = response.jsonPath().getList("requestStatus");
		log.info("List of request statuses are: " + requestStatuses);

		response.then().body("[0].statusId", equalTo(2)).body("[0].requestStatus", equalTo("Accepted"))

				.body("[1].statusId", equalTo(1)).body("[1].requestStatus", equalTo("Pending"))

				.body("[2].statusId", equalTo(3)).body("[2].requestStatus", equalTo("Rejected"));
	}

	@Test(priority = 9, dataProvider = "TestDataForGetAllRequestsAnalytic", dataProviderClass = DataProvidersForRequestFolder.class)
	public void verifyGetAllRequestsAnalyticWithAuthorization(int employeeIdInput) {
		String requestPayload = RequestFolderPayloads.giveRolePayloadForGetAllRequestsAnalytic(employeeIdInput);
		response = Responses.postRequestWithAuthorization(requestPayload,
				EmployeeFolderAPITestCases.authTokenOfSuperAdmin, APIEndpoints.getAllRequestAnalyticsEndpoint);

		if (response.getBody().asPrettyString().equalsIgnoreCase("")) {
			BodyValidation.response204Validation(response);
		} else if (!EmployeeFolderAPITestCases.employeeIds.contains(employeeIdInput)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			BodyValidation.responseValidation(response, 200);
		}
	}

	@Test(priority = 10, dataProvider = "TestDataForGetAllRequests", dataProviderClass = DataProvidersForRequestFolder.class)
	public void verifyGetAllRequestsWithAuthorization(String requestStatusInput) {
		response = Responses.getRequestWithAuthorizationAndOneQueryParameter(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllRequestsEndpoint, "status", requestStatusInput);

		if (response.getBody().asPrettyString().equalsIgnoreCase("")) {
			BodyValidation.response204Validation(response);
		} else if (!requestStatuses.contains(requestStatusInput)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			BodyValidation.responseValidation(response, 200);
		}
	}

	@Test(priority = 11, dataProvider = "TestDataForGetAllRequestsFromAnotherEmployeeForSingleEmployee", dataProviderClass = DataProvidersForRequestFolder.class)
	public void verifyGetAllRequestsFromAnotherEmployeeForSingleEmployeeWithAuthorization(int employeeIdInput) {
		String requestPayload = RequestFolderPayloads
				.giveRolePayloadForGetAllRequestsFromAnotherEmployeeForSingleEmployee(employeeIdInput);
		response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllRequestsFromAnotherEmployeeForSingleEmployeeEndpoint);

		if (response.getBody().asPrettyString().equalsIgnoreCase("")) {
			BodyValidation.response204Validation(response);
		} else if (!EmployeeFolderAPITestCases.employeeIds.contains(employeeIdInput)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			BodyValidation.responseValidation(response, 200);
		}
	}

	@Test(priority = 12, dataProvider = "TestDataForGetAllMySentRequests", dataProviderClass = DataProvidersForRequestFolder.class)
	public void verifyGetAllMySentRequestsWithAuthorization(String userIdInput, String requestStatusInput) {
		String requestPayload = RequestFolderPayloads.giveRolePayloadForGetAllMySentRequests(userIdInput,
				requestStatusInput);
		response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllMySentRequestsEndpoint);

		if (response.getBody().asPrettyString().equalsIgnoreCase("")) {
			BodyValidation.response204Validation(response);
		} else if (!requestStatuses.contains(requestStatusInput)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else if (!EmployeeFolderAPITestCases.userIds.contains(userIdInput)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			BodyValidation.responseValidation(response, 200);
		}
	}
}

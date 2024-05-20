package in.task_erp_api.testcases;

import java.util.*;

import org.apache.logging.log4j.*;
import org.apache.logging.log4j.Logger;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

import in.task_erp_api.bodyValidations.*;
import in.task_erp_api.endpoints.*;
import in.task_erp_api.payloads.*;
import in.task_erp_api.responses.*;
import in.task_erp_api.utilities.*;
import io.restassured.response.Response;

public class NotificationFolderAPITestCases {
	public static final Logger log = LogManager.getLogger(NotificationFolderAPITestCases.class);

	public static List<String> notificationReadStatuses;
	public static List<String> notificationIds;

	private Response response;

	private int falseCount = 0;

	@Test(priority = 1)
	public void verifyGetAllMyNotificationsWithoutAuthorization() {
		response = Responses.getRequestWithoutAuthorization(APIEndpoints.getMyNotificationsEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 2)
	public void verifyGetUnreadNotificationCountWithoutAuthorization() {
		response = Responses.getRequestWithoutAuthorizationAndOneQueryParameter(
				APIEndpoints.getUnreadNotificationCountEndpoint, "userId", "BIE018");

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 3)
	public void verifyUpdateNotificationReadStatusWithoutAuthorization() {
		String requestPayload = NotificationFolderPayloads
				.giveNotificationPayloadForUpdateNotificationReadStatus("2446", "BIE018");

		response = Responses.putRequestWithoutAuthorization(requestPayload,
				APIEndpoints.updateNotificationReadStatusEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 4)
	public void verifyGetAllMyNotificationsWithAuthorization() {
		response = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getMyNotificationsEndpoint);

		BodyValidation.responseValidation(response, 200);

		notificationIds = response.jsonPath().getList("notificationId");
		log.info("List of notification Ids are: " + notificationIds);

		notificationReadStatuses = response.jsonPath().getList("notificationReadStatus");
		log.info("List of notification read statuses are: " + notificationReadStatuses + "\n");

		for (String value : notificationReadStatuses) {
			if (value != null && value.equalsIgnoreCase("false")) {
				falseCount++;
			}
		}

		System.out.println(falseCount);
	}

	@Test(priority = 5, dataProvider = "TestDataForGetUnreadNotificationCount", dataProviderClass = DataProvidersForNotificationFolder.class)
	public void verifyGetUnreadNotificationCountWithAuthorization(String userIdInput) {
		response = Responses.getRequestWithAuthorizationAndOneQueryParameter(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getUnreadNotificationCountEndpoint, "userId", userIdInput);

		if (!EmployeeFolderAPITestCases.userIds.contains(userIdInput)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			BodyValidation.responseValidation(response, 200);

			int unreadNotificationCountFromResponse = response.jsonPath().getInt("notificationUnreadCount");
			log.info("Unread notification count from response is: " + unreadNotificationCountFromResponse + "\n");
			assertEquals(unreadNotificationCountFromResponse, falseCount);
		}
	}

	@Test(priority = 6, dataProvider = "TestDataForUpdateNotificationReadStatus", dataProviderClass = DataProvidersForNotificationFolder.class)
	public void verifyUpdateNotificationReadStatusWithAuthorization(String notificationIdInput, String userIdInput) {
		String requestPayload = NotificationFolderPayloads
				.giveNotificationPayloadForUpdateNotificationReadStatus(notificationIdInput, userIdInput);

		response = Responses.putRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.updateNotificationReadStatusEndpoint);

		String responseBody = response.getBody().asPrettyString();

		if (!notificationIds.contains(notificationIdInput)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			int contentLength = responseBody.length();

			assertEquals(responseBody, "Updated Read status");
			BodyValidation.responseValidation(response, 200, String.valueOf(contentLength));
		}

	}
}

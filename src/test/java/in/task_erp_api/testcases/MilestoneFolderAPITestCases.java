package in.task_erp_api.testcases;

import java.util.*;

import org.apache.logging.log4j.*;

import org.testng.annotations.*;

import in.task_erp_api.bodyValidations.*;
import in.task_erp_api.endpoints.*;
import in.task_erp_api.payloads.*;
import in.task_erp_api.responses.*;
import in.task_erp_api.utilities.*;
import io.restassured.response.*;

public class MilestoneFolderAPITestCases {
	public static final Logger log = LogManager.getLogger(MilestoneFolderAPITestCases.class);
	public static int newCreatedMilestoneId;
	public static String newCreatedMilestoneName;

	public static List<String> milestoneNames;
	public static List<Integer> milestoneIds;

	private Response response;

	@Test(priority = 1)
	public void verifyGetAllMilestonesWithoutAuthorization() {
		String requestPayload = MilestoneFolderPayloads.givemilestonePayloadForAddmilestone(1);

		response = Responses.postRequestWithoutAuthorization(requestPayload, APIEndpoints.getAllMilestonesEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 5, dataProvider = "TestDataForGetAllMilestones", dataProviderClass = DataProvidersForMilestoneFolder.class)
	public void verifyGetAllMilestonesWithAuthorization(int projectIdInput) {
		String requestPayload = MilestoneFolderPayloads.givemilestonePayloadForAddmilestone(projectIdInput);

		response = Responses.postRequestWithAuthorization(requestPayload,
				EmployeeFolderAPITestCases.authTokenOfSuperAdmin, APIEndpoints.getAllMilestonesEndpoint);

		System.out.println(response.getBody().asPrettyString());

		if (response.getBody().asPrettyString().equalsIgnoreCase("[]")) {
			BodyValidation.responseValidation(response, 200);
		} else if (!ProjectFolderAPITestCases.projectIds.contains(projectIdInput)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			BodyValidation.responseValidation(response, 200);

			milestoneIds = response.jsonPath().getList("milestoneId");
			log.info("List of milestone Ids before new milestone add are: " + milestoneIds);

			milestoneNames = response.jsonPath().getList("milestoneName");
			log.info("List of milestone names before new milestone add are: " + milestoneNames);
		}
	}
}

package in.task_erp_api.testcases;

import java.util.*;

import org.apache.logging.log4j.*;
import org.testng.annotations.*;

import com.github.javafaker.Faker;

import in.task_erp_api.bodyValidations.*;
import in.task_erp_api.endpoints.*;
import in.task_erp_api.payloads.*;
import in.task_erp_api.responses.*;
import in.task_erp_api.utilities.*;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class ProjectFolderAPITestCases {
	public static final Logger log = LogManager.getLogger(ProjectFolderAPITestCases.class);

	public static List<Integer> projectIds;
	public static List<String> projectNames;
	public static List<Integer> projectManagerEmployeeIds;
	public static List<Integer> projectStatusIds;

	public static int newCreatedProjectId;

	public Response response;

	@Test(priority = 1)
	public void verifyAddProjectWithoutAuthorization() {
		String requestPayload = "{\r\n" + "  \"projectName\": \"ERP - Phase 3\",\r\n"
				+ "  \"projectStartDate\": \"2023-12-01\",\r\n" + "  \"projectEndDate\": \"2023-12-31\",\r\n"
				+ "  \"projectManager\": {\r\n" + "    \"empId\": 6\r\n" + "  },\r\n" + "  \"projectStatus\": {\r\n"
				+ "    \"projectStatusId\": 1\r\n" + "  },\r\n" + "  \"projectPriority\": {\r\n"
				+ "    \"priorityId\": 1\r\n" + "  },\r\n" + "  \"projectDepartments\": [\r\n" + "    {\r\n"
				+ "      \"departmentId\": 1\r\n" + "    },\r\n" + "    {\r\n" + "      \"departmentId\": 40\r\n"
				+ "    }\r\n" + "  ],\r\n" + "  \"projectEmployees\": [\r\n" + "    {\r\n" + "      \"empId\": 6\r\n"
				+ "    },\r\n" + "    {\r\n" + "      \"empId\": 7\r\n" + "    }\r\n" + "  ]\r\n" + "}\r\n" + "";

		response = Responses.postRequestWithoutAuthorization(requestPayload, APIEndpoints.addProjectEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 2)
	public void verifyGetAllProjectsWithoutAuthorization() {
		response = Responses.getRequestWithoutAuthorization(APIEndpoints.addProjectEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 3)
	public void verifyGetAllProjectsForAnUserIdWithoutAuthorization() {
		response = Responses.getRequestWithoutAuthorizationAndPathParameter(
				APIEndpoints.getAllProjectForAnUserIdEndpoint, "BIE014");

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 4)
	public void verifyGetAllProjectsAssigneeWithoutAuthorization() {
		String requestPayload = "{\r\n" + "    \"department\" : [1,2]\r\n" + "}";
		response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.getAllProjectAssigneeEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 5)
	public void verifyGetAllProjectManagersWithoutAuthorization() {
		response = Responses.getRequestWithoutAuthorization(APIEndpoints.getAllProjectManagersEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 6)
	public void verifyGetAllProjectStatusWithoutAuthorization() {
		response = Responses.getRequestWithoutAuthorization(APIEndpoints.getAllProjectManagersEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 7)
	public void verifyGetAllProjectByDepartmentWithoutAuthorization() {
		response = Responses.getRequestWithoutAuthorizationAndOneQueryParameter(
				APIEndpoints.getAllProjectByDepartmentEndpoint, "deptName", "Testing");

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 8)
	public void verifyGetSingleProjectByProjectIdWithoutAuthorization() {
		String requestPayload = ProjectFolderPayloads.giveProjectPayloadForGetSingleProjectByProjectId(2);
		response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.getSingleProjectByProjectIdEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 9)
	public void verifyUpdateProjectWithoutAuthorization() {
		String payload = "{\r\n" + "    \"projectId\" : 6,\r\n" + "    \"projectName\" : \"Food Delivery App\",\r\n"
				+ "    \"projectStartDate\" : \"2023/11/15\",\r\n" + "    \"projectEndDate\" : \"2024/02/15\",\r\n"
				+ "    \"projectManager\" : {\r\n" + "        \"empId\" : 14\r\n" + "    },\r\n"
				+ "    \"projectDepartments\" : [\r\n" + "        {\r\n" + "            \"departmentId\" : 1\r\n"
				+ "        },\r\n" + "        {\r\n" + "            \"departmentId\" : 2\r\n" + "        },\r\n"
				+ "        {\r\n" + "            \"departmentId\" : 3\r\n" + "        },\r\n" + "        {\r\n"
				+ "            \"departmentId\" : 4\r\n" + "        },\r\n" + "        {\r\n"
				+ "            \"departmentId\" : 10\r\n" + "        }\r\n" + "    ],\r\n"
				+ "    \"projectEmployees\" : [\r\n" + "        {\r\n" + "            \"empId\" : 6\r\n"
				+ "        },\r\n" + "        {\r\n" + "            \"empId\" : 7\r\n" + "        },\r\n"
				+ "        {\r\n" + "            \"empId\" : 8\r\n" + "        },\r\n" + "        {\r\n"
				+ "            \"empId\" : 14\r\n" + "        },\r\n" + "        {\r\n"
				+ "            \"empId\" : 19\r\n" + "        },\r\n" + "        {\r\n"
				+ "            \"empId\" : 20\r\n" + "        }\r\n" + "    ],\r\n" + "    \"projectPriority\" : {\r\n"
				+ "\r\n" + "        \"priorityId\" : 2\r\n" + "    },\r\n" + "    \"projectStatus\" : {\r\n" + "\r\n"
				+ "        \"projectStatusId\" : 2\r\n" + "    }\r\n" + "}\r\n" + "";

		response = Responses.putRequestWithoutAuthorization(payload, APIEndpoints.updateProjectEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 10)
	public void verifyGetAllProjectsWithAuthorization() {
		response = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllProjectsEndpoint);

		projectIds = response.jsonPath().getList("projectId");
		log.info("List of project Ids are: " + projectIds + "\n");

		projectNames = response.jsonPath().getList("projectName");
		log.info("List of project names are: " + projectNames);

		newCreatedProjectId = response.jsonPath().getInt("max { it.projectId }.projectId");
		log.info("New created project Id is: " + newCreatedProjectId);

		BodyValidation.responseValidation(response, 200);
	}

	@Test(priority = 12, dataProvider = "TestDataForGetAllProjectsForAnUserId", dataProviderClass = DataProvidersForProjectFolder.class)
	public void verifyGetAllProjectsForAnUserIdWithAuthorization(String userIdInput) {
		if (EmployeeFolderAPITestCases.userIds != null) {
			response = Responses.getRequestWithAuthorizationAndPathParameter(LoginEmployeeAPITestCases.authToken,
					APIEndpoints.getAllProjectForAnUserIdEndpoint, userIdInput);

			if (response.getBody().asPrettyString().equalsIgnoreCase("[]")) {
				BodyValidation.responseValidation(response, 200);
			} else if (EmployeeFolderAPITestCases.userIds.contains(userIdInput) == false) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else {
				BodyValidation.responseValidation(response, 200);
			}
		} else {
			log.info("User Ids are null");
		}
	}

	@Test(priority = 13)
	public void verifyGetAllProjectManagersWithAuthorization() {
		response = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllProjectManagersEndpoint);

		BodyValidation.responseValidation(response, 200);

		response.then().body("[0].empId", equalTo(14)).body("[0].UserId", equalTo("BIE004"))
				.body("[0].empFullName", equalTo("Sachin Patil"))

				.body("[1].empId", equalTo(19)).body("[1].UserId", equalTo("BIE012"))
				.body("[1].empFullName", equalTo("Vishal Lohbande"))

				.body("[2].empId", equalTo(42)).body("[2].UserId", equalTo("BIE025"))
				.body("[2].empFullName", equalTo("Test Super Admin"))

				.body("[3].empId", equalTo(8)).body("[3].UserId", equalTo("BIE003"))
				.body("[3].empFullName", equalTo("Pramod Bansode"))

				.body("[4].empId", equalTo(21)).body("[4].UserId", equalTo("BIE006"))
				.body("[4].empFullName", equalTo("Akshay Jagtap"))

				.body("[5].empId", equalTo(40)).body("[5].UserId", equalTo("BIE023"))
				.body("[5].empFullName", equalTo("Test Admin"));

		projectManagerEmployeeIds = response.jsonPath().getList("empId");
		log.info("List of project manager employee Ids are: " + projectManagerEmployeeIds);
	}

	@Test(priority = 14)
	public void verifyGetAllProjectStatusWithAuthorization() {
		response = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllProjectStatusEndpoint);

		BodyValidation.responseValidation(response, 200);

		response.then().body("[0].projectStatusId", equalTo(1)).body("[0].projectStatus", equalTo("Upcoming"))

				.body("[1].projectStatusId", equalTo(2)).body("[1].projectStatus", equalTo("Ongoing"))

				.body("[2].projectStatusId", equalTo(3)).body("[2].projectStatus", equalTo("Completed"));

		projectStatusIds = response.jsonPath().getList("projectStatusId");
		log.info("List of project status Ids are: " + projectStatusIds);
	}

	@Test(priority = 15, dataProvider = "TestDataForGetAllProjectsByDepartment", dataProviderClass = DataProvidersForProjectFolder.class)
	public void verifyGetAllProjectByDepartmentWithAuthorization(String departmentNameInput) {
		response = Responses.getRequestWithAuthorizationAndOneQueryParameter(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllProjectByDepartmentEndpoint, "deptName", departmentNameInput);

		if (response.getBody().asPrettyString().equalsIgnoreCase("[]")) {
			BodyValidation.responseValidation(response, 200);
		} else {
			BodyValidation.responseValidation(response, 200);
		}
	}

	@Test(priority = 16)
	public void verifyGetAllProjectsAssigneeWithAuthorization() {
		int randomIndexForDepartmentId = new Random().nextInt(DepartmentFolderAPITestCases.departmentIds.size());
		int randomDepartmentId = DepartmentFolderAPITestCases.departmentIds.get(randomIndexForDepartmentId);

		int[] departmentPayloads = { new Faker().number().numberBetween(50, 100), randomDepartmentId };
		int randomIndexForDepartmentIdForPayload = new Random().nextInt(departmentPayloads.length);
		int randomDepartmentIdForPayload = departmentPayloads[randomIndexForDepartmentIdForPayload];

		String requestPayload = "{\r\n" + "    \"department\" : [" + randomDepartmentIdForPayload + "]\r\n" + "}";
		response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllProjectAssigneeEndpoint);

		if (DepartmentFolderAPITestCases.departmentIds.contains(randomDepartmentIdForPayload) == false) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			BodyValidation.responseValidation(response, 200);
		}
	}

	@Test(priority = 17, dataProvider = "TestDataForAddProject", dataProviderClass = DataProvidersForProjectFolder.class, enabled = false)
	public void verifyAddProjectWithAuthorization(String projectName, String projectStartDate, String projectEndDate,
			int projectManagerEmployeeId, int projectStatusId, int projectPriorityId, int projectDepartmentId1,
			int projectDepartmentId2, int projectEmployeeId1, int projectEmployeeId2) {
		if (projectStatusIds != null && PriorityFolderAPITestCases.priorityIds != null
				&& DepartmentFolderAPITestCases.departmentIds != null
				&& EmployeeFolderAPITestCases.employeeIds != null) {
			String requestPayload = ProjectFolderPayloads.giveProjectPayloadForAddProject(projectName, projectStartDate,
					projectEndDate, projectManagerEmployeeId, projectStatusId, projectPriorityId, projectDepartmentId1,
					projectDepartmentId2, projectEmployeeId1, projectEmployeeId2);

			response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
					APIEndpoints.addProjectEndpoint);

			if (projectName.equalsIgnoreCase("") || projectStartDate.equalsIgnoreCase("")
					|| projectEndDate.equalsIgnoreCase("")) {
				BodyValidation.response400Validation(response);
			} else if (projectNames.contains(projectName)) {
				BodyValidation.responseValidation(response, "Conflict", 409);
			} else if (projectManagerEmployeeIds.contains(projectManagerEmployeeId) == false) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (projectStatusIds.contains(projectStatusId) == false) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (PriorityFolderAPITestCases.priorityIds.contains(projectPriorityId) == false) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (DepartmentFolderAPITestCases.departmentIds.contains(projectDepartmentId1) == false
					|| DepartmentFolderAPITestCases.departmentIds.contains(projectDepartmentId2) == false) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (EmployeeFolderAPITestCases.employeeIds.contains(projectEmployeeId1) == false
					|| EmployeeFolderAPITestCases.employeeIds.contains(projectEmployeeId2) == false) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else {
				BodyValidation.responseValidation(response, 201);

				projectIds = response.jsonPath().getList("projectId");
				log.info("List of project Ids are: " + projectIds);

				newCreatedProjectId = response.jsonPath().getInt("max { it.projectId }.projectId");
				log.info("New created project Id is: " + newCreatedProjectId + "\n");
			}
		}
	}

	@Test(priority = 18, dataProvider = "TestDataForUpdateProject", dataProviderClass = DataProvidersForProjectFolder.class, enabled = false)
	public void verifyUpdateProjectWithAuthorization(int projectId, String projectName, String projectStartDate,
			String projectEndDate, int projectManagerEmployeeId, int projectStatusId, int projectPriorityId,
			int projectDepartmentId1, int projectDepartmentId2, int projectEmployeeId1, int projectEmployeeId2) {
		if (projectIds != null && projectStatusIds != null && PriorityFolderAPITestCases.priorityIds != null
				&& DepartmentFolderAPITestCases.departmentIds != null
				&& EmployeeFolderAPITestCases.employeeIds != null) {
			String requestPayload = ProjectFolderPayloads.giveProjectPayloadForUpdateProject(projectId, projectName,
					projectStartDate, projectEndDate, projectManagerEmployeeId, projectStatusId, projectPriorityId,
					projectDepartmentId1, projectDepartmentId2, projectEmployeeId1, projectEmployeeId2);

			response = Responses.putRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
					APIEndpoints.updateProjectEndpoint);

			if (projectName.equalsIgnoreCase("") || projectStartDate.equalsIgnoreCase("")
					|| projectEndDate.equalsIgnoreCase("")) {
				BodyValidation.response400Validation(response);
			} else if (projectIds.contains(projectId) == false) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (projectNames.contains(projectName)) {
				BodyValidation.responseValidation(response, "Conflict", 409);
			} else if (projectManagerEmployeeIds.contains(projectManagerEmployeeId) == false) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (projectStatusIds.contains(projectStatusId) == false) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (PriorityFolderAPITestCases.priorityIds.contains(projectPriorityId) == false) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (DepartmentFolderAPITestCases.departmentIds.contains(projectDepartmentId1) == false
					|| DepartmentFolderAPITestCases.departmentIds.contains(projectDepartmentId2) == false) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (EmployeeFolderAPITestCases.employeeIds.contains(projectEmployeeId1) == false
					|| EmployeeFolderAPITestCases.employeeIds.contains(projectEmployeeId2) == false) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else {
				BodyValidation.responseValidation(response, 200);
			}
		}
	}
}

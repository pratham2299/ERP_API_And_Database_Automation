package in.biencaps.erp.api.testcases;

import java.util.*;

import org.apache.logging.log4j.*;
import org.testng.annotations.*;

import com.aventstack.extentreports.Status;
import com.github.javafaker.Faker;

import in.biencaps.erp.api.bodyValidations.*;
import in.biencaps.erp.api.endpoints.*;
import in.biencaps.erp.api.payloads.*;
import in.biencaps.erp.api.responses.*;
import in.biencaps.erp.api.utilities.*;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class ProjectFolderAPITestCases extends BaseTest {
	public static final Logger log = LogManager.getLogger(ProjectFolderAPITestCases.class);

	public static List<Integer> projectIds;
	public static List<String> projectNames;
	public static List<Integer> projectManagerEmployeeIds;
	public static List<Integer> projectStatusIds;

	public static int newCreatedProjectId;
	public static String newCreatedProjectName;
	private static String newCreatedProjectStartDate;
	private static String newCreatedProjectEndDate;
	private static int newCreatedProjectManagerEmployeeId;
	private static int newCreatedProjectStatusId;
	private static int newCreatedProjectPriorityId;
	private static int newCreatedProjectDepartmentId1;
	private static int newCreatedProjectDepartmentId2;
	private static int newCreatedProjectEmployeeId1;
	private static int newCreatedProjectEmployeeId2;

	public Response response;

	@Test(priority = 1)
	public void verify_Add_Project_Without_Authorization() {
		test = BaseTest.extent.createTest("Add project without authorization");

		String requestPayload = ProjectFolderPayloads.addProjectPayload("ERP", "2024-05-20", "2024-05-30", 1, 1, 2, 1,
				2, 6, 4);

		Response response = Responses.postRequestWithoutAuthorization(requestPayload, APIEndpoints.addProjectEndpoint);

		BodyValidation.response401Validation(response);
		BaseTest.test_Method_Logs("add project", APIEndpoints.addProjectEndpoint, response);
	}

	@Test(priority = 2)
	public void verify_Get_All_Projects_Without_Authorization() {
		Response response = Responses.getRequestWithoutAuthorization(APIEndpoints.getAllProjectsEndpoint);

		BodyValidation.response401Validation(response);
		BaseTest.test_Method_Logs("get all projects", APIEndpoints.getAllProjectsEndpoint, response);
	}

	@Test(priority = 3)
	public void verify_Get_All_Projects_For_An_User_Id_Without_Authorization() {
		Response response = Responses.postRequestWithoutAuthorization(APIEndpoints.getAllProjectForAnUserIdEndpoint,
				"INC012");

		BodyValidation.response401Validation(response);
		BaseTest.test_Method_Logs("get all projects for an user Id", APIEndpoints.getAllProjectForAnUserIdEndpoint,
				response);
	}

	@Test(priority = 4)
	public void verify_Get_All_Projects_Assignee_Without_Authorization() {
		String requestPayload = ProjectFolderPayloads.getAllProjectAssigneePayload(Arrays.asList(1));
		Response response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.getAllProjectAssigneeEndpoint);

		BodyValidation.response401Validation(response);
		BaseTest.test_Method_Logs("get all projects assignee", APIEndpoints.getAllProjectAssigneeEndpoint, response);
	}

	@Test(priority = 5)
	public void verify_Get_All_Project_Managers_Without_Authorization() {
		Response response = Responses.getRequestWithoutAuthorization(APIEndpoints.getAllProjectManagersEndpoint);

		BodyValidation.response401Validation(response);
		BaseTest.test_Method_Logs("get all projects managers", APIEndpoints.getAllProjectManagersEndpoint, response);
	}

	@Test(priority = 6)
	public void verify_Get_All_Project_Status_Without_Authorization() {
		Response response = Responses.getRequestWithoutAuthorization(APIEndpoints.getAllProjectStatusEndpoint);

		BodyValidation.response401Validation(response);
		BaseTest.test_Method_Logs("get all projects status", APIEndpoints.getAllProjectStatusEndpoint, response);
	}

	@Test(priority = 7)
	public void verify_Get_All_Project_By_Department_Without_Authorization() {
		Response response = Responses.getRequestWithoutAuthorizationAndOneQueryParameter(
				APIEndpoints.getAllProjectByDepartmentEndpoint, "deptName", "QA");

		BodyValidation.response401Validation(response);
		BaseTest.test_Method_Logs("get all projects by department", APIEndpoints.getAllProjectByDepartmentEndpoint,
				response);
	}

	@Test(priority = 8)
	public void verify_Get_Single_Project_By_Project_Id_Without_Authorization() {
		String requestPayload = ProjectFolderPayloads.getSingleProjectByProjectIdPayload(2);
		Response response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.getSingleProjectByProjectIdEndpoint);

		BodyValidation.response401Validation(response);
		BaseTest.test_Method_Logs("get single project by project Id", APIEndpoints.getSingleProjectByProjectIdEndpoint,
				response);
	}

	@Test(priority = 9)
	public void verify_Get_All_Tasks_For_Project_Without_Authorization() {
		String requestPayload = ProjectFolderPayloads.getAllTasksForProjectPayload(1);
		Response response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.getAllTasksForProjectEndpoint);

		BodyValidation.response401Validation(response);
		BaseTest.test_Method_Logs("get all tasks for project", APIEndpoints.getAllTasksForProjectEndpoint, response);
	}

	@Test(priority = 10)
	public void verify_Get_All_Tasks_For_Project_Search_Without_Authorization() {
		String requestPayload = ProjectFolderPayloads.getAllTasksForProjectSearchPayload(1, "ERP");
		Response response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.getAllTasksForProjectSearchEndpoint);

		BodyValidation.response401Validation(response);
		BaseTest.test_Method_Logs("get all tasks for project search", APIEndpoints.getAllTasksForProjectSearchEndpoint,
				response);
	}

	@Test(priority = 11)
	public void verify_Update_Project_Without_Authorization() {
		String requestPayload = ProjectFolderPayloads.updateProjectPayload(2, "ERP", "2024-05-20", "2024-05-30", 1, 1,
				2, 1, 2, 6, 4);

		Response response = Responses.putRequestWithoutAuthorization(requestPayload,
				APIEndpoints.updateProjectEndpoint);

		BodyValidation.response401Validation(response);
		BaseTest.test_Method_Logs("update project", APIEndpoints.updateProjectEndpoint, response);
	}

	@Test(priority = 12)
	public void verify_Delete_Project_Without_Authorization() {
		String requestPayload = ProjectFolderPayloads.deleteProjectPayload(10, "Pass@123");

		Response response = Responses.putRequestWithoutAuthorization(requestPayload,
				APIEndpoints.deleteProjectEndpoint);

		BodyValidation.response401Validation(response);
		BaseTest.test_Method_Logs("delete project", APIEndpoints.deleteProjectEndpoint, response);
	}

	@Test(priority = 10)
	public void verify_Get_All_Projects_With_Authorization() {
		verify_Get_All_Projects_API_With_Authorization("before add project");
	}

	@Test(priority = 12, dataProvider = "TestDataForGetAllProjectsForAnUserId", dataProviderClass = DataProvidersForProjectFolder.class)
	public void verify_Get_All_Projects_For_An_User_Id_With_Authorization(String userId) {
		if (EmployeeFolderAPITestCases.userIds != null) {
			String requestPayload = ProjectFolderPayloads.getAllProjectsForAnUserIdPayload(userId);

			Response response = Responses.postRequestWithAuthorization(requestPayload,
					LoginEmployeeAPITestCases.authToken, APIEndpoints.getAllProjectForAnUserIdEndpoint);

			String responseBody = response.getBody().asPrettyString();
			BaseTest.test_Method_Logs("get all projects for an user Id", APIEndpoints.getAllProjectForAnUserIdEndpoint,
					response);

			if (responseBody.equalsIgnoreCase("[]")) {
				BodyValidation.responseValidation(response, 200);
			} else if (!EmployeeFolderAPITestCases.userIds.contains(userId)) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else {
				BodyValidation.responseValidation(response, 200);
			}
		} else {
			log.info("User Ids are null");
		}
	}

	@Test(priority = 13)
	public void verify_Get_All_Project_Managers_WithAuthorization() {
		Response response = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllProjectManagersEndpoint);

		BodyValidation.responseValidation(response, 200);

		response.then().body("[0].empId", equalTo(7)).body("[0].UserId", equalTo("INC001"))
				.body("[0].empFullName", equalTo("Shashikant Bagal"))

				.body("[1].empId", equalTo(6)).body("[1].UserId", equalTo("INC003"))
				.body("[1].empFullName", equalTo("Pramod Bansode"))

				.body("[2].empId", equalTo(1)).body("[2].UserId", equalTo("INC004"))
				.body("[2].empFullName", equalTo("Sachin Patil"))

				.body("[3].empId", equalTo(9)).body("[3].UserId", equalTo("INC005"))
				.body("[3].empFullName", equalTo("Akshay Jagtap"));

		projectManagerEmployeeIds = response.jsonPath().getList("empId");
		log.info("List of project manager employee Ids are: " + projectManagerEmployeeIds);
	}

	@Test(priority = 14)
	public void verify_Get_All_Project_Status_With_Authorization() {
		Response response = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllProjectStatusEndpoint);

		BodyValidation.responseValidation(response, 200);

		response.then().body("[0].projectStatusId", equalTo(1)).body("[0].projectStatus", equalTo("Upcoming"))

				.body("[1].projectStatusId", equalTo(2)).body("[1].projectStatus", equalTo("Ongoing"))

				.body("[2].projectStatusId", equalTo(3)).body("[2].projectStatus", equalTo("Completed"));

		projectStatusIds = response.jsonPath().getList("projectStatusId");
		log.info("List of project status Ids are: " + projectStatusIds);
	}

	@Test(priority = 15, dataProvider = "TestDataForGetAllProjectsByDepartment", dataProviderClass = DataProvidersForProjectFolder.class)
	public void verify_Get_All_Project_By_Department_With_Authorization(String departmentName) {
		Response response = Responses.getRequestWithAuthorizationAndOneQueryParameter(
				LoginEmployeeAPITestCases.authToken, APIEndpoints.getAllProjectByDepartmentEndpoint, "deptName",
				departmentName);

		if (response.getBody().asPrettyString().equalsIgnoreCase("[]")) {
			BodyValidation.responseValidation(response, 200);
		} else {
			BodyValidation.responseValidation(response, 200);
		}
	}

	@Test(priority = 16, enabled = false)
	public void verify_Get_All_Projects_Assignee_With_Authorization() {
		int randomIndexForDepartmentId = new Random().nextInt(DepartmentFolderAPITestCases.departmentIds.size());
		int randomDepartmentId = DepartmentFolderAPITestCases.departmentIds.get(randomIndexForDepartmentId);

		int[] departmentPayloads = { new Faker().number().numberBetween(50, 100), randomDepartmentId };
		int randomIndexForDepartmentIdForPayload = new Random().nextInt(departmentPayloads.length);
		int randomDepartmentIdForPayload = departmentPayloads[randomIndexForDepartmentIdForPayload];

		String requestPayload = "{\r\n" + "    \"department\" : [" + randomDepartmentIdForPayload + "]\r\n" + "}";
		Response response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllProjectAssigneeEndpoint);

		if (!DepartmentFolderAPITestCases.departmentIds.contains(randomDepartmentIdForPayload)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			BodyValidation.responseValidation(response, 200);
		}
	}

	@Test(priority = 17, dataProvider = "TestDataForGetAllTasksForProject", dataProviderClass = DataProvidersForProjectFolder.class)
	public void verify_Get_All_Tasks_For_Project_With_Authorization(int projectId) {
		String requestPayload = ProjectFolderPayloads.getAllTasksForProjectPayload(projectId);

		Response response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllTasksForProjectEndpoint);

		String responseBody = response.getBody().asPrettyString();
		BaseTest.test_Method_Logs("get all tasks for project", APIEndpoints.getAllTasksForProjectEndpoint,
				requestPayload, response);

		if (!projectIds.contains(projectId)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else if (responseBody.equalsIgnoreCase("[]")) {
			BodyValidation.responseValidation(response, "OK", 200);
		} else {
			BodyValidation.responseValidation(response, 200);

			List<Integer> projectIdsFromResponse = response.jsonPath().getList("projectEntity.projectId");
			assertTrue(projectIdsFromResponse.contains(projectId));
		}
	}

	@Test(priority = 17, dataProvider = "TestDataForAddProject", dataProviderClass = DataProvidersForProjectFolder.class)
	public void verify_Add_Project_With_Authorization(String projectName, String projectStartDate,
			String projectEndDate, int projectManagerEmployeeId, int projectStatusId, int projectPriorityId,
			int projectDepartmentId1, int projectDepartmentId2, int projectEmployeeId1, int projectEmployeeId2) {
		if (projectStatusIds != null && PriorityFolderAPITestCases.priorityIds != null
				&& DepartmentFolderAPITestCases.departmentIds != null
				&& EmployeeFolderAPITestCases.employeeIds != null) {
			String requestPayload = ProjectFolderPayloads.addProjectPayload(projectName, projectStartDate,
					projectEndDate, projectManagerEmployeeId, projectStatusId, projectPriorityId, projectDepartmentId1,
					projectDepartmentId2, projectEmployeeId1, projectEmployeeId2);

			Response response = Responses.postRequestWithAuthorization(requestPayload,
					LoginEmployeeAPITestCases.authToken, APIEndpoints.addProjectEndpoint);

			String responseBody = response.getBody().asPrettyString();

			BaseTest.test_Method_Logs("add project", APIEndpoints.addProjectEndpoint, requestPayload, response);

			if (projectName.isBlank() || projectStartDate.isBlank() || projectEndDate.isBlank()) {
				BodyValidation.response400Validation(response);
			} else if (!projectManagerEmployeeIds.contains(projectManagerEmployeeId)) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (!projectStatusIds.contains(projectStatusId)) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (!PriorityFolderAPITestCases.priorityIds.contains(projectPriorityId)) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (!DepartmentFolderAPITestCases.departmentIds.contains(projectDepartmentId1)
					|| !DepartmentFolderAPITestCases.departmentIds.contains(projectDepartmentId2)) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (!EmployeeFolderAPITestCases.employeeIds.contains(projectEmployeeId1)
					|| !EmployeeFolderAPITestCases.employeeIds.contains(projectEmployeeId2)) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (projectNames.contains(projectName)) {
				BodyValidation.responseValidation(response, "Conflict", 409);
			} else {
				int contentLength = responseBody.length();
				BodyValidation.responseValidation(response, 201, String.valueOf(contentLength));
				assertEquals(responseBody, "Project Added Successfully");

				verify_Get_All_Projects_API_With_Authorization("after added new project");

				verify_New_Created_Project_Details("after added new project");

				assertEquals(newCreatedProjectName, projectName);
				assertEquals(newCreatedProjectStartDate, projectStartDate);
				assertEquals(newCreatedProjectEndDate, projectEndDate);
				assertEquals(newCreatedProjectManagerEmployeeId, projectManagerEmployeeId);
				assertEquals(newCreatedProjectStatusId, projectStatusId);
				assertEquals(newCreatedProjectPriorityId, projectPriorityId);
				assertEquals(newCreatedProjectDepartmentId1, projectDepartmentId1);
				assertEquals(newCreatedProjectDepartmentId2, projectDepartmentId2);
				assertEquals(newCreatedProjectEmployeeId1, projectEmployeeId1);
				assertEquals(newCreatedProjectEmployeeId2, projectEmployeeId2);
			}
		}
	}

	@Test(priority = 18, dataProvider = "TestDataForUpdateProject", dataProviderClass = DataProvidersForProjectFolder.class)
	public void verify_Update_Project_With_Authorization(int projectId, String projectName, String projectStartDate,
			String projectEndDate, int projectManagerEmployeeId, int projectStatusId, int projectPriorityId,
			int projectDepartmentId1, int projectDepartmentId2, int projectEmployeeId1, int projectEmployeeId2) {
		if (projectIds != null) {
			String requestPayload = ProjectFolderPayloads.updateProjectPayload(projectId, projectName, projectStartDate,
					projectEndDate, projectManagerEmployeeId, projectStatusId, projectPriorityId, projectDepartmentId1,
					projectDepartmentId2, projectEmployeeId1, projectEmployeeId2);

			Response response = Responses.putRequestWithAuthorization(requestPayload,
					LoginEmployeeAPITestCases.authToken, APIEndpoints.updateProjectEndpoint);

			String responseBody = response.getBody().asPrettyString();
			log.info("Response body of update project is: " + responseBody + "\n");

			BaseTest.test_Method_Logs("update project", APIEndpoints.updateProjectEndpoint, requestPayload, response);

			if (projectName.isBlank() || projectStartDate.isBlank() || projectEndDate.isBlank()) {
				BodyValidation.response400Validation(response);
			} else if (!projectIds.contains(projectId)) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (projectManagerEmployeeIds.contains(projectManagerEmployeeId) == false) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (!projectStatusIds.contains(projectStatusId)) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (PriorityFolderAPITestCases.priorityIds.contains(projectPriorityId) == false) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (!DepartmentFolderAPITestCases.departmentIds.contains(projectDepartmentId1)
					|| !DepartmentFolderAPITestCases.departmentIds.contains(projectDepartmentId2)) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (!EmployeeFolderAPITestCases.employeeIds.contains(projectEmployeeId1)
					|| !EmployeeFolderAPITestCases.employeeIds.contains(projectEmployeeId2)) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			}
//			else if (projectNames.contains(projectName)) {
//				BodyValidation.responseValidation(response, "Conflict", 409);
//			} 
			else {
				int contentLength = responseBody.length();
				BodyValidation.responseValidation(response, 200, String.valueOf(contentLength));
				assertEquals(responseBody, "Project Updated Successfully");

				verify_Get_All_Projects_API_With_Authorization("after updated new project");

				verify_New_Created_Project_Details("after updated new project");

				assertEquals(newCreatedProjectName, projectName);
				assertEquals(newCreatedProjectStartDate, projectStartDate);
				assertEquals(newCreatedProjectEndDate, projectEndDate);
				assertEquals(newCreatedProjectManagerEmployeeId, projectManagerEmployeeId);
				assertEquals(newCreatedProjectStatusId, projectStatusId);
				assertEquals(newCreatedProjectPriorityId, projectPriorityId);
				assertEquals(newCreatedProjectDepartmentId1, projectDepartmentId1);
				assertEquals(newCreatedProjectDepartmentId2, projectDepartmentId2);
				assertEquals(newCreatedProjectEmployeeId1, projectEmployeeId1);
				assertEquals(newCreatedProjectEmployeeId2, projectEmployeeId2);
			}
		}
	}

	@Test(priority = 19, dataProvider = "TestDataForDeleteProject", dataProviderClass = DataProvidersForProjectFolder.class)
	public void verify_Delete_Project_With_Authorization(int projectId, String password) {
		if (projectIds != null) {
			String requestPayload = ProjectFolderPayloads.deleteProjectPayload(projectId, password);

			Response response = Responses.putRequestWithAuthorization(requestPayload,
					LoginEmployeeAPITestCases.authToken, APIEndpoints.deleteProjectEndpoint);

			String responseBody = response.getBody().asPrettyString();
			log.info("Request payload for delete project is: " + requestPayload);
			log.info("Response body of delete project is: " + responseBody + "\n");

			BaseTest.test_Method_Logs("delete project", APIEndpoints.deleteProjectEndpoint, requestPayload, response);

			if (!password.equalsIgnoreCase(Constants.employeePassword)) {
				BodyValidation.responseValidation(response, "Unprocessable Entity", 422);
			} else if (!projectIds.contains(projectId)) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (response.getStatusCode() == 403) {
				BodyValidation.responseValidation(response, "Forbidden", 403);
			} else {
				int contentLength = responseBody.length();
				BodyValidation.responseValidation(response, 200, String.valueOf(contentLength));
				assertEquals(responseBody, "Project deleted successfully");

				verify_Get_All_Projects_API_With_Authorization("after deleted new project");
			}
		}
	}

	public void verify_New_Created_Project_Details(String message) {
		newCreatedProjectId = response.jsonPath().getInt("max { it.projectId }.projectId");
		log.info("New created project Id is: " + newCreatedProjectId);

		newCreatedProjectName = response.jsonPath()
				.getString("find { it.projectId == " + newCreatedProjectId + " }.projectName");
		log.info("New created project name " + message + " is => " + newCreatedProjectName);

		newCreatedProjectStartDate = response.jsonPath()
				.getString("find { it.projectId == " + newCreatedProjectId + " }.projectStartDate");
		log.info("New created project start date " + message + " is => " + newCreatedProjectStartDate);

		newCreatedProjectEndDate = response.jsonPath()
				.getString("find { it.projectId == " + newCreatedProjectId + " }.projectEndDate");
		log.info("New created project start date " + message + " is => " + newCreatedProjectEndDate);

		newCreatedProjectManagerEmployeeId = response.jsonPath()
				.getInt("find { it.projectId == " + newCreatedProjectId + " }.projectManager.empId");
		log.info("New created project manager employee Id " + message + " is => " + newCreatedProjectManagerEmployeeId);

		newCreatedProjectStatusId = response.jsonPath()
				.getInt("find { it.projectId == " + newCreatedProjectId + " }.projectStatus.projectStatusId");
		log.info("New created project status Id " + message + " is => " + newCreatedProjectStatusId);

		newCreatedProjectPriorityId = response.jsonPath()
				.getInt("find { it.projectId == " + newCreatedProjectId + " }.projectPriority.priorityId");
		log.info("New created project priority Id " + message + " is => " + newCreatedProjectPriorityId);

		newCreatedProjectDepartmentId1 = response.jsonPath()
				.getInt("find { it.projectId == " + newCreatedProjectId + " }.projectDepartments[0].departmentId");
		log.info("New created project department Id 1 " + message + " is => " + newCreatedProjectDepartmentId1);

		newCreatedProjectDepartmentId2 = response.jsonPath()
				.getInt("find { it.projectId == " + newCreatedProjectId + " }.projectDepartments[1].departmentId");
		log.info("New created project department Id 2 " + message + " is => " + newCreatedProjectDepartmentId2);

		newCreatedProjectEmployeeId1 = response.jsonPath()
				.getInt("find { it.projectId == " + newCreatedProjectId + " }.projectEmployees[0].empId");
		log.info("New created project employee Id 1 " + message + " is => " + newCreatedProjectEmployeeId1);

		newCreatedProjectEmployeeId2 = response.jsonPath()
				.getInt("find { it.projectId == " + newCreatedProjectId + " }.projectEmployees[1].empId");
		log.info("New created project employee Id 2 " + message + " is => " + newCreatedProjectEmployeeId2);
	}

	public void verify_Get_All_Projects_API_With_Authorization(String message) {
		test = BaseTest.extent.createTest("Get All projects with authorization");

		response = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllProjectsEndpoint);

		BodyValidation.responseValidation(response, 200);
		BaseTest.test_Method_Logs("get all projects", APIEndpoints.getAllProjectsEndpoint, response);

		projectIds = response.jsonPath().getList("projectId");
		log.info("List of project Ids are: " + projectIds);

		projectNames = response.jsonPath().getList("projectName");
		log.info("List of project names are: " + projectNames + "\n");
	}
}

package in.biencaps.erp.api.testcases;

import org.apache.logging.log4j.*;
import org.testng.annotations.Test;

import in.biencaps.erp.api.bodyValidations.*;
import in.biencaps.erp.api.endpoints.*;
import in.biencaps.erp.api.payloads.*;
import in.biencaps.erp.api.responses.*;
import in.biencaps.erp.api.utilities.*;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

import io.restassured.response.Response;

public class TaskFolderAPITestCases extends BaseTest {
	public static final Logger log = LogManager.getLogger(TaskFolderAPITestCases.class);

	public static List<Integer> taskIds;
	public static List<String> taskTitles;
	public static List<Integer> allEmployeesTaskIds;

	public static int newCreatedTaskId;
	public static String newCreatedTaskTitle;
	public Response response;

	@Test(priority = 1)
	public void verify_Add_Task_Without_Authorization() {
		test = BaseTest.extent.createTest("Add task without authorization");

		String requestPayload = TaskFolderPayloads.giveTaskPayloadForAddTask("Task 1", 26, "Do it urgent", "2024/05/27",
				4, 3, 1, 26, 1);

		Response response = Responses.postRequestWithoutAuthorization(requestPayload, APIEndpoints.addTaskEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("add task", APIEndpoints.addTaskEndpoint, response);
	}

	@Test(priority = 2)
	public void verify_Get_All_Tasks_For_Day_Without_Authorization() {
		test = BaseTest.extent.createTest("Get all tasks for day without authorization");

		String requestPayload = TaskFolderPayloads.giveTaskPayloadForGetAllTasksForDay(26, "2024/05/27");
		Response response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.getAllTasksForDayEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("get all tasks", APIEndpoints.getAllTasksForDayEndpoint, response);
	}

	@Test(priority = 3)
	public void verify_Search_Task_For_Month_Without_Authorization() {
		test = BaseTest.extent.createTest("Get search task for month without authorization");

		String requestPayload = TaskFolderPayloads.giveTaskPayloadForSearchTaskForMonth("low", 26, 2024, 2);
		Response response = Responses.postRequestWithoutAuthorization(requestPayload, APIEndpoints.searchTaskEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("get search task for month", APIEndpoints.searchTaskEndpoint, response);
	}

	@Test(priority = 4)
	public void verify_Search_Task_For_Week_Without_Authorization() {
		test = BaseTest.extent.createTest("Get search task for week without authorization");

		String requestPayload = TaskFolderPayloads.giveTaskPayloadForSearchTaskForWeek("low", 26, 2024, 2, 1);
		response = Responses.postRequestWithoutAuthorization(requestPayload, APIEndpoints.searchTaskEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("get search task for week", APIEndpoints.searchTaskEndpoint, response);
	}

	@Test(priority = 5)
	public void verify_Search_Task_For_Day_Without_Authorization() {
		test = BaseTest.extent.createTest("Get search task for day without authorization");

		String requestPayload = TaskFolderPayloads.giveTaskPayloadForSearchTaskForDay("low", 26, "2024-02-16");
		Response response = Responses.postRequestWithoutAuthorization(requestPayload, APIEndpoints.searchTaskEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("get search task for day", APIEndpoints.searchTaskEndpoint, response);
	}

	@Test(priority = 6)
	public void verify_Get_Single_Task_Without_Authorization() {
		test = BaseTest.extent.createTest("Get single task without authorization");

		Response response = Responses.getRequestWithoutAuthorizationAndPathParameter(APIEndpoints.getSingleTaskEndpoint,
				675);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("get single task", APIEndpoints.getSingleTaskEndpoint, response);
	}

	@Test(priority = 7)
	public void verify_Get_All_Tasks_For_Month_Without_Authorization() {
		test = BaseTest.extent.createTest("Get all tasks for month without authorization");

		String requestPayload = TaskFolderPayloads.giveTaskPayloadForGetAllTasksForMonth(2024, 2, 26);
		Response response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.getAllTasksForMonthEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("get all tasks for month", APIEndpoints.getAllTasksForMonthEndpoint, response);
	}

	@Test(priority = 8)
	public void verify_Get_All_Tasks_For_Week_Without_Authorization() {
		test = BaseTest.extent.createTest("Get all tasks for week without authorization");

		String requestPayload = TaskFolderPayloads.giveTaskPayloadForGetAllTasksForWeek(2024, 2, 1, 26);
		Response response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.getAllTasksForWeekEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("get all tasks for week", APIEndpoints.getAllTasksForWeekEndpoint, response);
	}

	@Test(priority = 9)
	public void verify_Get_Task_Info_For_Employee_By_Role_Without_Authorization() {
		test = BaseTest.extent.createTest("Get task info for employee by role without authorization");

		Response response = Responses.getRequestWithoutAuthorizationPathParameterAndOneQueryParameter(
				APIEndpoints.getAssignedTaskInfoByRoleEndpoint, "Team Lead", "date", "2024/02/16");

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("get task info for employee by role", APIEndpoints.getAssignedTaskInfoByRoleEndpoint,
				response);
	}

	@Test(priority = 10)
	public void verify_Get_All_Tasks_For_Day_By_Due_Date_Without_Authorization() {
		test = BaseTest.extent.createTest("Get all tasks for day by due date without authorization");

		String requestPayload = TaskFolderPayloads.giveTaskPayloadForGetAllTasksForDay(26, "2024/02/16");
		Response response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.getAllTasksForDayByDueDateEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("get all tasks for day by due date", APIEndpoints.getAllTasksForDayByDueDateEndpoint,
				response);
	}

	@Test(priority = 11)
	public void verify_Get_All_Tasks_For_Day_By_Priority_Without_Authorization() {
		test = BaseTest.extent.createTest("Get all tasks for day by priority without authorization");

		String requestPayload = TaskFolderPayloads.giveTaskPayloadForGetAllTasksForDay(26, "2024/02/16");
		Response response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.getAllTasksForDayByPriorityEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("get all tasks for day by priority", APIEndpoints.getAllTasksForDayByPriorityEndpoint,
				response);
	}

	@Test(priority = 12)
	public void verify_Get_All_Tasks_For_Day_By_Status_Without_Authorization() {
		test = BaseTest.extent.createTest("Get all tasks for day by status without authorization");

		String requestPayload = TaskFolderPayloads.giveTaskPayloadForGetAllTasksForDay(26, "2024/02/16");
		Response response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.getAllTasksForDayByStatusEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("get all tasks for day by status", APIEndpoints.getAllTasksForDayByStatusEndpoint,
				response);
	}

	@Test(priority = 13)
	public void verify_Get_Task_Info_For_Month_Without_Authorization() {
		Response response = given().queryParam("year", 2024).queryParam("month", 5).when()
				.get("/task/employee/INC004/get/by/month");

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 14)
	public void verifyDuplicateTaskWithoutAuthorization() {
		List<Integer> taskIds = DataGeneratorForAPI.generateRandomArrayValues(Arrays.asList(1850, 1860, 1870));

		String requestPayload = TaskFolderPayloads.giveTaskPayloadForDuplicateTask(taskIds, 26, "2024/02/16");
		Response response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.duplicateTaskEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 15)
	public void verifyUpdateTaskWithoutAuthorization() {
		List<Integer> tagIds = DataGeneratorForAPI.generateRandomArrayValues(Arrays.asList(1, 2, 3));

		String requestPayload = TaskFolderPayloads.giveTaskPayloadForUpdateTask(1, 26, 1, 4, 3, 4, 7,
				"https://www.gilab.com", "Comment", tagIds, "2024-02-28", "2024-02-29");
		Response response = Responses.putRequestWithoutAuthorization(requestPayload, APIEndpoints.updateTaskEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 16)
	public void verifyUpdateMultipleTaskWithoutAuthorization() {
		List<Integer> tagIds = DataGeneratorForAPI.generateRandomArrayValues(Arrays.asList(1, 2, 3));

		List<Integer> taskIds = DataGeneratorForAPI.generateRandomArrayValues(Arrays.asList(1850, 1860, 1870));

		String requestPayload = TaskFolderPayloads.giveTaskPayloadForUpdateMultipleTasks(taskIds, 26, 1, 4, 3, 4, 7,
				"https://www.gilab.com", "Comment", tagIds, "2024-02-28", "2024-02-29");
		Response response = Responses.putRequestWithoutAuthorization(requestPayload, APIEndpoints.updateTaskEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 17)
	public void verifyDeleteTaskWithoutAuthorization() {
		String requestPayload = TaskFolderPayloads.giveTaskPayloadForDeleteTask(699, 26);
		Response response = Responses.putRequestWithoutAuthorization(requestPayload, APIEndpoints.deleteTaskEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 18, dataProvider = "TestDataForAddSelfTask", dataProviderClass = DataProvidersForTaskFolder.class)
	public void verifyAddSelfTaskWithAuthorization(String taskTitleInput, int employeeIdInput, String taskCommentInput,
			String taskScheduleDateInput, int taskPriorityInput, int taskStatusIdInput, int taskProjectInput,
			int taskOwnerEmployeeIdInput, int taskTagsInput) {
		String requestPayload = TaskFolderPayloads.giveTaskPayloadForAddTask(taskTitleInput, employeeIdInput,
				taskCommentInput, taskScheduleDateInput, taskPriorityInput, taskStatusIdInput, taskProjectInput,
				taskOwnerEmployeeIdInput, taskTagsInput);

		response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.addTaskEndpoint);

		if (taskTitleInput.isBlank()) {
			BodyValidation.response400Validation(response);
		} else if (response.getStatusCode() == 400 || taskScheduleDateInput.isBlank()) {
			BodyValidation.response400Validation(response);
		} else if (PriorityFolderAPITestCases.priorityIds.contains(taskPriorityInput) == false) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else if (StatusFolderAPITestCases.statusIds.contains(taskStatusIdInput) == false) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else if (ProjectFolderAPITestCases.projectIds.contains(taskProjectInput) == false) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else if (DepartmentFolderAPITestCases.departmentIds.contains(taskTagsInput) == false) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else if (EmployeeFolderAPITestCases.employeeIds.contains(employeeIdInput) == false) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else if (EmployeeFolderAPITestCases.employeeIds.contains(taskOwnerEmployeeIdInput) == false) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			BodyValidation.responseValidation(response, 201);

			newCreatedTaskId = response.jsonPath().getInt("taskId[0]");
			log.info("New created Task Id after adding new task is: " + newCreatedTaskId);
		}
	}

	@Test(priority = 19, dataProvider = "TestDataForSearchTaskForMonth", dataProviderClass = DataProvidersForTaskFolder.class, enabled = false)
	public void verifySearchTaskForMonthWithAuthorization(String searchParameterInput, int employeeIdInput,
			int yearInput, int monthInput) {
		String requestPayload = TaskFolderPayloads.giveTaskPayloadForSearchTaskForMonth(searchParameterInput,
				employeeIdInput, yearInput, monthInput);
		response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.searchTaskEndpoint);

		if (response.getBody().asPrettyString().equalsIgnoreCase("{}")) {
			BodyValidation.responseValidation(response, 200);
		} else {
			BodyValidation.responseValidation(response, 200);

			int lastIndex = response.jsonPath().getList("$").size() - 1;

			if (StatusFolderAPITestCases.statuses.contains(searchParameterInput)) {
				for (int i = 0; i < lastIndex; i++) {
					String taskStatus = response.jsonPath().getString("[" + i + "]" + ".taskStatus");
					log.info("Task status from resposne is: " + taskStatus);
					assertEquals(taskStatus, searchParameterInput);
				}
			} else if (PriorityFolderAPITestCases.priorities.contains(searchParameterInput)) {
				for (int i = 0; i < lastIndex; i++) {
					String taskPriority = response.jsonPath().getString("[" + i + "]" + ".taskPriority");
					log.info("Task priority from resposne is: " + taskPriority);
					assertEquals(taskPriority, searchParameterInput);
				}
			} else if (taskTitles.contains(searchParameterInput)) {
				for (int i = 0; i < lastIndex; i++) {
					String taskTitle = response.jsonPath().getString("[" + i + "]" + ".taskTitle");
					log.info("Task title from resposne is: " + taskTitle + "\n");
					assertEquals(taskTitle, searchParameterInput);
				}
			}
		}
	}

	@Test(priority = 20, dataProvider = "TestDataForSearchTaskForWeek", dataProviderClass = DataProvidersForTaskFolder.class, enabled = false)
	public void verifySearchTaskForWeekWithAuthorization(String searchParameterInput, int employeeIdInput,
			int yearInput, int monthInput, int weekInput) {
		String requestPayload = TaskFolderPayloads.giveTaskPayloadForSearchTaskForWeek(searchParameterInput,
				employeeIdInput, yearInput, monthInput, weekInput);
		response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.searchTaskEndpoint);

		if (response.getBody().asPrettyString().equalsIgnoreCase("{}")) {
			BodyValidation.responseValidation(response, 200);
		} else {
			BodyValidation.responseValidation(response, 200);

			int lastIndex = response.jsonPath().getList("$").size() - 1;

			if (StatusFolderAPITestCases.statuses.contains(searchParameterInput)) {
				for (int i = 0; i < lastIndex; i++) {
					String taskStatus = response.jsonPath().getString("[" + i + "]" + ".taskStatus");
					log.info("Task status from resposne is: " + taskStatus);
					assertEquals(taskStatus, searchParameterInput);
				}
			} else if (PriorityFolderAPITestCases.priorities.contains(searchParameterInput)) {
				for (int i = 0; i < lastIndex; i++) {
					String taskPriority = response.jsonPath().getString("[" + i + "]" + ".taskPriority");
					log.info("Task priority from resposne is: " + taskPriority);
					assertEquals(taskPriority, searchParameterInput);
				}
			} else if (taskTitles.contains(searchParameterInput)) {
				for (int i = 0; i < lastIndex; i++) {
					String taskTitle = response.jsonPath().getString("[" + i + "]" + ".taskTitle");
					log.info("Task title from resposne is: " + taskTitle + "\n");
					assertEquals(taskTitle, searchParameterInput);
				}
			}
		}
	}

	@Test(priority = 21, dataProvider = "TestDataForSearchTaskForDay", dataProviderClass = DataProvidersForTaskFolder.class, enabled = false)
	public void verifySearchTaskForDayWithAuthorization(String searchParameterInput, int employeeIdInput,
			String dateInput) {
		String requestPayload = TaskFolderPayloads.giveTaskPayloadForSearchTaskForDay(searchParameterInput,
				employeeIdInput, dateInput);
		response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.searchTaskEndpoint);

		if (response.getBody().asPrettyString().equalsIgnoreCase("[]")) {
			BodyValidation.responseValidation(response, 200);
		} else if (searchParameterInput.isBlank()) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else if (dateInput.isBlank()) {
			BodyValidation.response400Validation(response);
		} else {
			BodyValidation.responseValidation(response, 200);

			int lastIndex = response.jsonPath().getList("$").size() - 1;

			if (StatusFolderAPITestCases.statuses.contains(searchParameterInput)) {
				for (int i = 0; i < lastIndex; i++) {
					String taskStatus = response.jsonPath().getString("[" + i + "]" + ".taskStatus");
					log.info("Task status from resposne is: " + taskStatus);
					assertEquals(taskStatus, searchParameterInput);
				}
			} else if (PriorityFolderAPITestCases.priorities.contains(searchParameterInput)) {
				for (int i = 0; i < lastIndex; i++) {
					String taskPriority = response.jsonPath().getString("[" + i + "]" + ".taskPriority");
					log.info("Task priority from resposne is: " + taskPriority);
					assertEquals(taskPriority, searchParameterInput);
				}
			} else if (taskTitles.contains(searchParameterInput)) {
				for (int i = 0; i < lastIndex; i++) {
					String taskTitle = response.jsonPath().getString("[" + i + "]" + ".taskTitle");
					log.info("Task title from resposne is: " + taskTitle + "\n");
					assertEquals(taskTitle, searchParameterInput);
				}
			}
		}
	}

	@Test(priority = 22, dataProvider = "TestDataForGetAllTasksForMonth", dataProviderClass = DataProvidersForTaskFolder.class)
	public void verifyGetAllTasksForMonthWithAuthorization(int yearInput, int monthInput, int employeeIdInput) {
		String requestPayload = TaskFolderPayloads.giveTaskPayloadForGetAllTasksForMonth(yearInput, monthInput,
				employeeIdInput);
		response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllTasksForMonthEndpoint);

		if (response.getBody().asPrettyString().equalsIgnoreCase("[]")) {
			BodyValidation.responseValidation(response, 200);
		} else {
			BodyValidation.responseValidation(response, 200);
		}
	}

	@Test(priority = 23, dataProvider = "TestDataForGetAllTasksForWeek", dataProviderClass = DataProvidersForTaskFolder.class)
	public void verifyGetAllTasksForWeekWithAuthorization(int yearInput, int monthInput, int weekInput,
			int employeeIdInput) {
		String requestPayload = TaskFolderPayloads.giveTaskPayloadForGetAllTasksForWeek(yearInput, monthInput,
				weekInput, employeeIdInput);
		response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllTasksForWeekEndpoint);

		if (response.getBody().asPrettyString().equalsIgnoreCase("[]")) {
			BodyValidation.responseValidation(response, 200);
		} else {
			BodyValidation.responseValidation(response, 200);
		}
	}

	@Test(priority = 24, dataProvider = "TestDataForGetTasksInfoForEmployeeByRole", dataProviderClass = DataProvidersForTaskFolder.class)
	public void verifyGetTaskInfoForEmployeeByRoleWithAuthorization(String roleInput, String dateInput) {
		response = Responses.getRequestWithAuthorizationPathParameterAndOneQueryParameter(
				LoginEmployeeAPITestCases.authToken, APIEndpoints.getAssignedTaskInfoByRoleEndpoint, roleInput, "date",
				dateInput);

		if (response.getBody().asPrettyString().equalsIgnoreCase("[]")) {
			BodyValidation.response204Validation(response);
		} else if (response.getStatusCode() == 400) {
			BodyValidation.response400Validation(response);
		} else if (RoleFolderAPITestCases.roles.contains(roleInput) == false) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			BodyValidation.responseValidation(response, 200);
		}
	}

	@Test(priority = 28, enabled = false)
	public void verifyGetAllTasksForDayWithAuthorizationForAllEmployee() {
		log.info("List of employee Ids are: " + EmployeeFolderAPITestCases.employeeIds);
		List<Integer> empIds = EmployeeFolderAPITestCases.employeeIds;

		allEmployeesTaskIds = new ArrayList<>();

		LocalDate startDate = LocalDate.of(2023, 12, 1);

		// Get the current date
		LocalDate currentDate = LocalDate.now();

		// Iterate from the start date to the current date
		while (!startDate.isAfter(currentDate)) {
			log.info("\n" + startDate);

			// Move to the next date
			startDate = startDate.plus(1, ChronoUnit.DAYS);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			for (int i = 0; i < empIds.size() - 1; i++) {
				int empId = empIds.get(i);

				String requestPayload = TaskFolderPayloads.giveTaskPayloadForGetAllTasksForDay(empId,
						startDate.format(formatter));
				response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
						APIEndpoints.getAllTasksForDayEndpoint);

				if (response.getBody().asPrettyString().equalsIgnoreCase("[]")) {
					BodyValidation.responseValidation(response, 200);
				} else {
					BodyValidation.responseValidation(response, 200);

					List<Integer> taskIds = response.jsonPath().getList("taskId");
					log.info("Task Ids from response are: " + taskIds);

					allEmployeesTaskIds.addAll(taskIds);
				}
			}
		}
	}

	@Test(priority = 29, dataProvider = "TestDataForGetAllTasksForDay", dataProviderClass = DataProvidersForTaskFolder.class)
	public void verifyGetAllTasksFordayWithAuthorization(int employeeIdInput, String dateInput) {
		String requestPayload = TaskFolderPayloads.giveTaskPayloadForGetAllTasksForDay(employeeIdInput, dateInput);
		response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllTasksForDayEndpoint);

		if (response.getBody().asPrettyString().equalsIgnoreCase("[]")) {
			BodyValidation.responseValidation(response, 200);
		} else if (dateInput.isBlank()) {
			BodyValidation.response400Validation(response);
		} else if (EmployeeFolderAPITestCases.employeeIds.contains(employeeIdInput) == false) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			BodyValidation.responseValidation(response, 200);

			taskIds = response.jsonPath().getList("taskId");
			log.info("List of task Ids after added new task are: " + taskIds);

			taskTitles = response.jsonPath().getList("taskTitle");
			log.info("List of task titles after added new task are: " + taskTitles);

		}
	}

	@Test(priority = 30, dataProvider = "TestDataForGetSingleTask", dataProviderClass = DataProvidersForTaskFolder.class, enabled = false)
	public void verifyGetSingleTaskWithAuthorization(int taskIdInput) {
		response = Responses.getRequestWithAuthorizationAndPathParameter(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getSingleTaskEndpoint, taskIdInput);

		if (response.getBody().asPrettyString().equalsIgnoreCase("[]")) {
			BodyValidation.responseValidation(response, 200);
		} else if (allEmployeesTaskIds.contains(taskIdInput) == false) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			BodyValidation.responseValidation(response, 200);
		}
	}

	@Test(priority = 31, dataProvider = "TestDataForUpdateSelfTask", dataProviderClass = DataProvidersForTaskFolder.class)
	public void verifyUpdateSelfTaskWithAuthorization(int taskIdInput, int employeeIdInput, int taskDoneInput,
			int taskVerificationStatusInput, int taskStatusInput, int taskPriorityInput, int taskProjectInput,
			String taskGitLinkInput, String taskCommentInput, List<Integer> taskTagIdsInput,
			String taskScheduleDateInput, String taskDueDateInput) {
		String requestPayload = TaskFolderPayloads.giveTaskPayloadForUpdateTask(taskIdInput, employeeIdInput,
				taskDoneInput, taskVerificationStatusInput, taskStatusInput, taskPriorityInput, taskProjectInput,
				taskGitLinkInput, taskCommentInput, taskTagIdsInput, taskScheduleDateInput, taskDueDateInput);
		response = Responses.putRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.updateTaskEndpoint);

		String responseBody = response.getBody().asPrettyString();

		log.info("List of task Ids for update self task are: " + taskIds);

		if (taskIds != null) {
			if (taskIds.contains(taskIdInput) == false) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (EmployeeFolderAPITestCases.employeeIds.contains(employeeIdInput) == false) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (!VerificationFolderAPITestCases.verificationIds.contains(taskVerificationStatusInput)) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (!PriorityFolderAPITestCases.priorityIds.contains(taskPriorityInput)) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (!StatusFolderAPITestCases.statusIds.contains(taskStatusInput)) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (!ProjectFolderAPITestCases.projectIds.contains(taskProjectInput)) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (response.getStatusCode() == 404) {
				for (int tagId : taskTagIdsInput) {
					if (!DepartmentFolderAPITestCases.departmentIds.contains(tagId)) {
						BodyValidation.responseValidation(response, "Not Found", 404);
					}
				}
			} else if (response.getStatusCode() == 400 || taskScheduleDateInput.isBlank()) {
				BodyValidation.response400Validation(response);
			} else if (response.getStatusCode() == 400 || taskDueDateInput.isBlank()) {
				BodyValidation.response400Validation(response);
			}
//			else if (response.getStatusCode() == 200 && VerificationStatusFolderAPITestCases.verificationStatusIds
//					.contains(taskVerificationStatusInput)) {
//				assertEquals(response.getBody().asPrettyString(), "Verification status updated successfully");
//				int contentLength = response.getBody().asPrettyString().length();
//				BodyValidation.responseValidation(response, 200, String.valueOf(contentLength));
//			} else if (response.getStatusCode() == 200
//					&& StatusFolderAPITestCases.statusIds.contains(taskStatusInput)) {
//				assertEquals(response.getBody().asPrettyString(), "Status updated Successfully");
//				int contentLength = response.getBody().asPrettyString().length();
//				BodyValidation.responseValidation(response, 200, String.valueOf(contentLength));
//			} 
			else {
				assertEquals(responseBody, "Task Updated SuccessFully");
				int contentLength = response.getBody().asPrettyString().length();
				BodyValidation.responseValidation(response, 200, String.valueOf(contentLength));
			}
		} else {
			log.info("Task Ids are null");
		}
	}

	@Test(priority = 32, dataProvider = "TestDataForUpdateSelfMultipleTask", dataProviderClass = DataProvidersForTaskFolder.class, enabled = false)
	public void verifyUpdateSelfMultipleTaskWithAuthorization(List<Integer> taskIdsInput, int employeeIdInput,
			int taskDoneInput, int taskVerificationStatusInput, int taskStatusInput, int taskPriorityInput,
			int taskProjectInput, String taskGitLinkInput, String taskCommentInput, List<Integer> taskTagIdsInput,
			String taskScheduleDateInput, String taskDueDateInput) {
		String requestPayload = TaskFolderPayloads.giveTaskPayloadForUpdateMultipleTasks(taskIdsInput, employeeIdInput,
				taskDoneInput, taskVerificationStatusInput, taskStatusInput, taskPriorityInput, taskProjectInput,
				taskGitLinkInput, taskCommentInput, taskTagIdsInput, taskScheduleDateInput, taskDueDateInput);

		response = Responses.putRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.updateMultipleTaskEndpoint);

		String responseBody = response.getBody().asPrettyString();
		System.out.println(responseBody);

		if (taskIds != null) {
			if (response.getStatusCode() == 404) {
				for (int taskId : taskIdsInput) {
					if (!taskIds.contains(taskId)) {
						BodyValidation.responseValidation(response, "Not Found", 404);
					}
				}
			} else if (EmployeeFolderAPITestCases.employeeIds.contains(employeeIdInput) == false) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (!VerificationFolderAPITestCases.verificationIds.contains(taskVerificationStatusInput)) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (!PriorityFolderAPITestCases.priorityIds.contains(taskPriorityInput)) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (!StatusFolderAPITestCases.statusIds.contains(taskStatusInput)) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (!ProjectFolderAPITestCases.projectIds.contains(taskProjectInput)) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (response.getStatusCode() == 404) {
				for (int tagId : taskTagIdsInput) {
					if (!DepartmentFolderAPITestCases.departmentIds.contains(tagId)) {
						BodyValidation.responseValidation(response, "Not Found", 404);
					}
				}
			} else if (response.getStatusCode() == 400 || taskScheduleDateInput.isBlank()) {
				BodyValidation.response400Validation(response);
			} else if (response.getStatusCode() == 400 || taskDueDateInput.isBlank()) {
				BodyValidation.response400Validation(response);
			}
//			else if (response.getStatusCode() == 200 && VerificationStatusFolderAPITestCases.verificationStatusIds
//					.contains(taskVerificationStatusInput)) {
//				assertEquals(response.getBody().asPrettyString(), "Verification status updated successfully");
//				int contentLength = response.getBody().asPrettyString().length();
//				BodyValidation.responseValidation(response, 200, String.valueOf(contentLength));
//			} else if (response.getStatusCode() == 200
//					&& StatusFolderAPITestCases.statusIds.contains(taskStatusInput)) {
//				assertEquals(response.getBody().asPrettyString(), "Status updated Successfully");
//				int contentLength = response.getBody().asPrettyString().length();
//				BodyValidation.responseValidation(response, 200, String.valueOf(contentLength));
//			} 
			else {
				assertEquals(responseBody, "Task Updated SuccessFully");
				int contentLength = response.getBody().asPrettyString().length();
				BodyValidation.responseValidation(response, 200, String.valueOf(contentLength));
			}
		} else {
			log.info("Task Ids are null");
		}
	}

	@Test(priority = 33, dataProvider = "TestDataForDuplicateSelfTask", dataProviderClass = DataProvidersForTaskFolder.class)
	public void verifyDuplicateSelfTaskWithAuthorization(List<Integer> taskIdsInput, int employeeIdInput,
			String taskScheduleDate) {
		if (taskIds != null) {
			String requestPayload = TaskFolderPayloads.giveTaskPayloadForDuplicateTask(taskIdsInput, employeeIdInput,
					taskScheduleDate);
			response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
					APIEndpoints.duplicateTaskEndpoint);

			String responseBody = response.getBody().asPrettyString();

			if (response.getBody().asPrettyString().equalsIgnoreCase("[]")) {
				BodyValidation.responseValidation(response, 200);
			} else if (taskScheduleDate.isBlank()) {
				BodyValidation.response400Validation(response);
			} else if (response.getStatusCode() == 404) {
				for (int taskId : taskIdsInput) {
					if (!taskIds.contains(taskId)) {
						BodyValidation.responseValidation(response, "Not Found", 404);
					}
				}
			} else if (response.getStatusCode() == 400 && !taskScheduleDate.isBlank()) {
				BodyValidation.response400Validation(response);
			} else if (!EmployeeFolderAPITestCases.employeeIds.contains(employeeIdInput)) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else {
				int contentLength = responseBody.length();
				assertEquals(responseBody, "Task Duplicate Successfully");
				BodyValidation.responseValidation(response, 200, String.valueOf(contentLength));
			}
		} else {
			log.info("Task Ids are null");
		}
	}

	@Test(priority = 35, dataProvider = "TestDataForDeleteSelfTask", dataProviderClass = DataProvidersForTaskFolder.class)
	public void verifyDeleteTaskWithAuthorization(int taskIdInput, int employeeIdInput) {
		if (taskIds != null) {
			String requestPayload = TaskFolderPayloads.giveTaskPayloadForDeleteTask(taskIdInput, employeeIdInput);
			response = Responses.putRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
					APIEndpoints.deleteTaskEndpoint);

			String responseBody = response.getBody().asPrettyString();
			System.out.println(responseBody);

			if (response.getBody().asPrettyString().equalsIgnoreCase("[]")) {
				BodyValidation.responseValidation(response, 200);
			} else if (taskIds.contains(taskIdInput) == false) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (EmployeeFolderAPITestCases.employeeIds.contains(employeeIdInput) == false) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else {
				int contentLength = responseBody.length();
				assertEquals(responseBody, "Task Deleted Successfully");
				BodyValidation.responseValidation(response, 200, String.valueOf(contentLength));
			}
		} else {
			log.info("Task Ids are null");
		}
	}

	@Test(priority = 36, dataProvider = "TestDataForAssignTask", dataProviderClass = DataProvidersForTaskFolder.class)
	public void verifyAssignTaskFromOneEmployeeToAnotherWithAuthorization(String taskTitleInput, int employeeIdInput,
			String taskCommentInput, String taskScheduleDateInput, int taskPriorityInput, int taskStatusIdInput,
			int taskProjectInput, int taskOwnerEmployeeIdInput, int taskTagsInput) {

		String requestPayload = TaskFolderPayloads.giveTaskPayloadForAddTask(taskTitleInput, employeeIdInput,
				taskCommentInput, taskScheduleDateInput, taskPriorityInput, taskStatusIdInput, taskProjectInput,
				taskOwnerEmployeeIdInput, taskTagsInput);

		response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.addTaskEndpoint);

		if (taskTitleInput.isBlank()) {
			BodyValidation.response400Validation(response);
		} else if (response.getStatusCode() == 400 || taskScheduleDateInput.isBlank()) {
			BodyValidation.response400Validation(response);
		} else if (PriorityFolderAPITestCases.priorityIds.contains(taskPriorityInput) == false) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else if (StatusFolderAPITestCases.statusIds.contains(taskStatusIdInput) == false) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else if (ProjectFolderAPITestCases.projectIds.contains(taskProjectInput) == false) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else if (DepartmentFolderAPITestCases.departmentIds.contains(taskTagsInput) == false) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else if (EmployeeFolderAPITestCases.employeeIds.contains(employeeIdInput) == false) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else if (EmployeeFolderAPITestCases.employeeIds.contains(taskOwnerEmployeeIdInput) == false) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			BodyValidation.responseValidation(response, 201);

			newCreatedTaskId = response.jsonPath().getInt("taskId[0]");
			log.info("New created Task Id after adding new task is: " + newCreatedTaskId);
		}
	}

}

package in.biencaps.erp.api.testcases;

import org.apache.logging.log4j.*;
import org.testng.annotations.Test;

import in.biencaps.erp.api.bodyValidations.*;
import in.biencaps.erp.api.endpoints.*;
import in.biencaps.erp.api.payloads.*;
import in.biencaps.erp.api.responses.*;
import in.biencaps.erp.api.utilities.*;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import java.util.*;

import io.restassured.response.Response;

public class TaskFolderAPITestCases extends BaseTest {
	public static final Logger log = LogManager.getLogger(TaskFolderAPITestCases.class);

	public static List<Integer> taskIds;
	public static List<String> taskTitles;
	public static List<String> taskScheduleDates;
	public static List<String> taskDueDates;
	public static List<String> taskOwners;
	public static List<String> taskStatuses;
	public static List<String> taskPriorities;
	public static List<String> taskProjects;
	public static List<String> taskVerifications;
	public static List<Integer> allEmployeesTaskIds;

	public static int newCreatedTaskId;
	public static String newCreatedTaskTitle;
	public static String newCreatedTaskScheduleDate;
	public static String newCreatedTaskDueDate;
	public static int newCreatedTaskOwner;
	public static int newCreatedTaskStatusId;
	public static int newCreatedTaskPriorityId;
	public static int newCreatedTaskProjectId;
	public static int newCreatedTaskVerificationId;
	public Response response;

	@Test(priority = 1)
	public void verify_Add_Task_Without_Authorization() {
		test = BaseTest.extent.createTest("Add task without authorization");

		String requestPayload = TaskFolderPayloads.addTaskPayload("Task 1", 26, "2024/05/27", 4, 3, 1, 26, 1);

		Response response = Responses.postRequestWithoutAuthorization(requestPayload, APIEndpoints.addTaskEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("add task", APIEndpoints.addTaskEndpoint, response);
	}

	@Test(priority = 2)
	public void verify_Get_All_Tasks_For_Day_Without_Authorization() {
		test = BaseTest.extent.createTest("Get all tasks for day without authorization");

		String requestPayload = TaskFolderPayloads.employeeIdAndDatePayload(26, "2024/05/27");
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

		String requestPayload = TaskFolderPayloads.employeeIdAndDatePayload(26, "2024/02/16");
		Response response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.getAllTasksForDayByDueDateEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("get all tasks for day by due date", APIEndpoints.getAllTasksForDayByDueDateEndpoint,
				response);
	}

	@Test(priority = 11)
	public void verify_Get_All_Tasks_For_Day_By_Priority_Without_Authorization() {
		test = BaseTest.extent.createTest("Get all tasks for day by priority without authorization");

		String requestPayload = TaskFolderPayloads.employeeIdAndDatePayload(26, "2024/02/16");
		Response response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.getAllTasksForDayByPriorityEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("get all tasks for day by priority", APIEndpoints.getAllTasksForDayByPriorityEndpoint,
				response);
	}

	@Test(priority = 12)
	public void verify_Get_All_Tasks_For_Day_By_Status_Without_Authorization() {
		test = BaseTest.extent.createTest("Get all tasks for day by status without authorization");

		String requestPayload = TaskFolderPayloads.employeeIdAndDatePayload(26, "2024/02/16");
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
	public void verify_Duplicate_Task_Without_Authorization() {
		test = BaseTest.extent.createTest("Duplicate task without authorization");

		List<Integer> taskIds = DataGeneratorForAPI.generateRandomArrayValues(Arrays.asList(1850, 1860, 1870));

		String requestPayload = TaskFolderPayloads.giveTaskPayloadForDuplicateTask(taskIds, 26, "2024/02/16");
		Response response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.duplicateTaskEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("duplicate task", APIEndpoints.duplicateTaskEndpoint, response);
	}

	@Test(priority = 15)
	public void verify_Update_Task_Without_Authorization() {
		test = BaseTest.extent.createTest("Update single task without authorization");

		List<Integer> tagIds = DataGeneratorForAPI.generateRandomArrayValues(Arrays.asList(1, 2, 3));

		String requestPayload = TaskFolderPayloads.giveTaskPayloadForUpdateTask(1, 26, 1, 4, 3, 4, 7,
				"https://www.gilab.com", "Comment", tagIds, "2024-02-28", "2024-02-29");
		Response response = Responses.putRequestWithoutAuthorization(requestPayload, APIEndpoints.updateTaskEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("update single task", APIEndpoints.updateTaskEndpoint, response);
	}

	@Test(priority = 16)
	public void verify_Update_Task_View_Without_Authorization() {
		test = BaseTest.extent.createTest("Update task view without authorization");

		String requestPayload = TaskFolderPayloads.employeeIdAndDatePayload(26, "2024/05/27");
		Response response = Responses.postRequestWithoutAuthorization(requestPayload, APIEndpoints.updateViewEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("update task view", APIEndpoints.updateViewEndpoint, response);
	}

	@Test(priority = 17)
	public void verify_Multiple_Task_Shift_Without_Authorization() {
		test = BaseTest.extent.createTest("Multiple task shift without authorization");

		String requestPayload = TaskFolderPayloads.multipleTasksShiftPayload(100, "2024-05-30");
		Response response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.shiftMultipleTasksEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("multiple task shift", APIEndpoints.shiftMultipleTasksEndpoint, response);
	}

	@Test(priority = 18)
	public void verify_Transfer_Task_Without_Authorization() {
		test = BaseTest.extent.createTest("Transfer task without authorization");

		String requestPayload = TaskFolderPayloads.transferTaskPayload(4, 100, "2024-05-30", "INC004");
		Response response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.transferTaskEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("transfer task", APIEndpoints.transferTaskEndpoint, response);
	}

	@Test(priority = 19, enabled = false)
	public void verify_Level_Month_Search() {
		test = BaseTest.extent.createTest("Level month search without authorization");

	}

	@Test(priority = 20, enabled = false)
	public void verify_Update_Task_Level_Without_Authorization() {
		test = BaseTest.extent.createTest("Update task level without authorization");

	}

	@Test(priority = 22)
	public void verify_Update_Multiple_Task_Without_Authorization() {
		test = BaseTest.extent.createTest("Update multiple task without authorization");

		List<Integer> tagIds = DataGeneratorForAPI.generateRandomArrayValues(Arrays.asList(1, 2, 3));

		List<Integer> taskIds = DataGeneratorForAPI.generateRandomArrayValues(Arrays.asList(1850, 1860, 1870));

		String requestPayload = TaskFolderPayloads.giveTaskPayloadForUpdateMultipleTasks(taskIds, 26, 1, 4, 3, 4, 7,
				"https://www.gilab.com", "Comment", tagIds, "2024-02-28", "2024-02-29");
		Response response = Responses.putRequestWithoutAuthorization(requestPayload,
				APIEndpoints.updateMultipleTaskEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("update multiple tasks", APIEndpoints.transferTaskEndpoint, response);
	}

	@Test(priority = 23)
	public void verify_Delete_Task_Without_Authorization() {
		test = BaseTest.extent.createTest("Delete task without authorization");

		String requestPayload = TaskFolderPayloads.giveTaskPayloadForDeleteTask(699, 26);
		Response response = Responses.putRequestWithoutAuthorization(requestPayload, APIEndpoints.deleteTaskEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("delete task", APIEndpoints.deleteTaskEndpoint, response);
	}

	@Test(priority = 24, dataProvider = "TestDataForAddSelfTask", dataProviderClass = DataProvidersForTaskFolder.class)
	public void verify_Add_Self_Task_With_Authorization(String taskTitle, int employeeId, String taskScheduleDate,
			int taskPriority, int taskStatusId, int taskProject, int taskOwnerEmployeeId, int taskTags) {
		test = BaseTest.extent.createTest("Add single task with valid and invalid data and with authorization");

		String requestPayload = TaskFolderPayloads.addTaskPayload(taskTitle, employeeId, taskScheduleDate, taskPriority,
				taskStatusId, taskProject, taskOwnerEmployeeId, taskTags);

		Response response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.addTaskEndpoint);

		BaseTest.test_Method_Logs("add single task", APIEndpoints.addTaskEndpoint, requestPayload, response);

		if (taskTitle.isBlank()) {
			BodyValidation.response400Validation(response);
		} else if (response.getStatusCode() == 400 || taskScheduleDate.isBlank()) {
			BodyValidation.response400Validation(response);
		} else if (!PriorityFolderAPITestCases.priorityIds.contains(taskPriority)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else if (!StatusFolderAPITestCases.statusIds.contains(taskStatusId)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else if (!ProjectFolderAPITestCases.projectIds.contains(taskProject)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else if (!DepartmentFolderAPITestCases.departmentIds.contains(taskTags)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else if (!EmployeeFolderAPITestCases.employeeIds.contains(employeeId)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else if (!EmployeeFolderAPITestCases.employeeIds.contains(taskOwnerEmployeeId)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			BodyValidation.responseValidation(response, 201);

			int taskIdFromResponse = response.jsonPath().getInt("taskId[0]");

			verify_Get_All_Tasks_For_Day_API_With_Authorization("after added new self task");

			verify_New_Created_Task_Details("after added new self task");

			assertEquals(newCreatedTaskId, taskIdFromResponse);
			assertEquals(newCreatedTaskTitle, taskTitle);
			assertEquals(newCreatedTaskScheduleDate, taskScheduleDate);
			assertEquals(newCreatedTaskDueDate, taskScheduleDate);
			assertEquals(newCreatedTaskOwner, taskOwnerEmployeeId);
			assertEquals(newCreatedTaskStatusId, taskStatusId);
			assertEquals(newCreatedTaskPriorityId, taskPriority);
			assertEquals(newCreatedTaskProjectId, taskProject);
		}
	}

	@Test(priority = 25, dataProvider = "TestDataForGetAllTasksForDay", dataProviderClass = DataProvidersForTaskFolder.class)
	public void verify_Get_All_Tasks_For_Day_With_Authorization(int employeeId, String date) {
		test = BaseTest.extent.createTest("Get all tasks for day with valid and invalid data and with authorization");

		String requestPayload = TaskFolderPayloads.employeeIdAndDatePayload(employeeId, date);
		Response response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllTasksForDayEndpoint);

		BaseTest.test_Method_Logs("get all tasks for day", APIEndpoints.getAllTasksForDayEndpoint, requestPayload,
				response);

		if (date.isBlank()) {
			BodyValidation.response400Validation(response);
		} else if (!EmployeeFolderAPITestCases.employeeIds.contains(employeeId)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			BodyValidation.responseValidation(response, 200);

			verify_New_Created_Task_Details("for get all tasks for day");

			assertEquals(newCreatedTaskScheduleDate, date);
		}
	}

	@Test(priority = 25, dataProvider = "TestDataForGetAllTasksForMonth", dataProviderClass = DataProvidersForTaskFolder.class)
	public void verify_Get_All_Tasks_For_Month_With_Authorization(int year, int month, int employeeId) {
		test = BaseTest.extent.createTest("Get all tasks for month with valid and invalid data and with authorization");

		String requestPayload = TaskFolderPayloads.giveTaskPayloadForGetAllTasksForMonth(year, month, employeeId);
		Response response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllTasksForMonthEndpoint);

		BaseTest.test_Method_Logs("get all tasks for month", APIEndpoints.getAllTasksForMonthEndpoint, requestPayload,
				response);

		if (!EmployeeFolderAPITestCases.employeeIds.contains(employeeId)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			BodyValidation.responseValidation(response, 200);

			List<String> taskIdsAsString = response.jsonPath().getList("" + newCreatedTaskScheduleDate + ".taskId");
			log.info("Task Ids from response are: " + taskIdsAsString);

			// Convert the strings to integers
			List<Integer> taskIdsFromResponse = new ArrayList<>();
			for (String taskIdStr : taskIdsAsString) {
				taskIdsFromResponse.add(Integer.parseInt(taskIdStr));
			}

			// Find the maximum task ID
			int maxTaskId = Integer.MIN_VALUE;
			for (int taskId : taskIdsFromResponse) {
				if (taskId > maxTaskId) {
					maxTaskId = taskId;
				}
			}

			System.out.println(maxTaskId);

			String newCreatedTaskTitle = response.jsonPath()
					.getString("find { it." + newCreatedTaskScheduleDate + ".taskId == " + maxTaskId + " }.taskTitle");
			log.info("Max task Id task title for get tasks for month API is: " + newCreatedTaskTitle + "\n");
		}
	}

	@Test(priority = 26, dataProvider = "TestDataForGetAllTasksForWeek", dataProviderClass = DataProvidersForTaskFolder.class)
	public void verify_Get_All_Tasks_For_Week_With_Authorization(int year, int month, int week, int employeeId) {
		test = BaseTest.extent.createTest("Get all tasks for week with valid and invalid data and with authorization");

		String requestPayload = TaskFolderPayloads.giveTaskPayloadForGetAllTasksForWeek(year, month, week, employeeId);
		Response response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllTasksForWeekEndpoint);

		BaseTest.test_Method_Logs("get all tasks for week", APIEndpoints.getAllTasksForWeekEndpoint, requestPayload,
				response);

		if (!EmployeeFolderAPITestCases.employeeIds.contains(employeeId)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			BodyValidation.responseValidation(response, 200);

			List<String> taskIdsAsString = response.jsonPath().getList("" + newCreatedTaskScheduleDate + ".taskId");
			log.info("Task Ids from response are: " + taskIdsAsString);

			// Convert the strings to integers
			List<Integer> taskIdsFromResponse = new ArrayList<>();
			for (String taskIdStr : taskIdsAsString) {
				taskIdsFromResponse.add(Integer.parseInt(taskIdStr));
			}

			// Find the maximum task ID
			int maxTaskId = Integer.MIN_VALUE;
			for (int taskId : taskIdsFromResponse) {
				if (taskId > maxTaskId) {
					maxTaskId = taskId;
				}
			}

			System.out.println(maxTaskId);

			String newCreatedTaskTitle = response.jsonPath()
					.getString("find { it." + newCreatedTaskScheduleDate + ".taskId == " + maxTaskId + " }.taskTitle");
			log.info("Max task Id task title for get tasks for week API is: " + newCreatedTaskTitle + "\n");
		}
	}

	@Test(priority = 27, dataProvider = "TestDataForGetTasksInfoForEmployeeByRole", dataProviderClass = DataProvidersForTaskFolder.class)
	public void verify_Get_Task_Info_For_Employee_By_Role_With_Authorization(String role, String date) {
		test = BaseTest.extent
				.createTest("get tasks info for employee by role with valid and invalid data and with authorization");

		Response response = Responses.getRequestWithAuthorizationPathParameterAndOneQueryParameter(
				LoginEmployeeAPITestCases.authToken, APIEndpoints.getAssignedTaskInfoByRoleEndpoint, role, "date",
				date);

		String responseBody = response.getBody().asPrettyString();

		BaseTest.test_Method_Logs_With_Path_Parameter("get tasks info for employee by role",
				APIEndpoints.getAssignedTaskInfoByRoleEndpoint, role, response);

		if (responseBody.equalsIgnoreCase("[]")) {
			BodyValidation.response204Validation(response);
		} else if (response.getStatusCode() == 400) {
			BodyValidation.response400Validation(response);
		} else if (!RoleFolderAPITestCases.roles.contains(role)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			BodyValidation.responseValidation(response, 200);
		}
	}

	@Test(priority = 28, dataProvider = "TestDataForGetSingleTask", dataProviderClass = DataProvidersForTaskFolder.class, enabled = false)
	public void verify_Get_Single_Task_With_Authorization(int taskId) {
		test = BaseTest.extent.createTest("Get single task with valid and invalid data and with authorization");

		Response response = Responses.getRequestWithAuthorizationAndPathParameter(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getSingleTaskEndpoint, taskId);

		String responseBody = response.getBody().asPrettyString();
		System.out.println(responseBody);

		if (responseBody.equalsIgnoreCase("[]")) {
			BodyValidation.responseValidation(response, 200);
		} else if (allEmployeesTaskIds.contains(taskId) == false) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			BodyValidation.responseValidation(response, 200);
		}
	}

	public void verify_New_Created_Task_Details(String message) {
		newCreatedTaskId = response.jsonPath().getInt("max { it.taskId }.taskId");
		log.info("New created task Id " + message + " is => " + newCreatedTaskId);

		newCreatedTaskTitle = response.jsonPath().getString("find { it.taskId == " + newCreatedTaskId + " }.taskTitle");
		log.info("New created task title " + message + " is => " + newCreatedTaskTitle);

		newCreatedTaskScheduleDate = response.jsonPath()
				.getString("find { it.taskId == " + newCreatedTaskId + " }.taskSchedule");
		log.info("New created task schedule date " + message + " is => " + newCreatedTaskScheduleDate);

		newCreatedTaskDueDate = response.jsonPath()
				.getString("find { it.taskId == " + newCreatedTaskId + " }.taskDueDate");
		log.info("New created task due date " + message + " is => " + newCreatedTaskDueDate);

		newCreatedTaskOwner = response.jsonPath()
				.getInt("find { it.taskId == " + newCreatedTaskId + " }.taskOwner.empId");
		log.info("New created task owner " + message + " is => " + newCreatedTaskOwner);

		newCreatedTaskStatusId = response.jsonPath()
				.getInt("find { it.taskId == " + newCreatedTaskId + " }.statusEntity.statusId");
		log.info("New created task status Id " + message + " is => " + newCreatedTaskStatusId);

		newCreatedTaskPriorityId = response.jsonPath()
				.getInt("find { it.taskId == " + newCreatedTaskId + " }.priorityEntity.priorityId");
		log.info("New created task priority Id " + message + " is => " + newCreatedTaskPriorityId);

		newCreatedTaskProjectId = response.jsonPath()
				.getInt("find { it.taskId == " + newCreatedTaskId + " }.projectEntity.projectId");
		log.info("New created task project Id " + message + " is => " + newCreatedTaskProjectId);
	}

	public void verify_Get_All_Tasks_For_Day_API_With_Authorization(String message) {
		int employeeId = 4;
		String currentDate = DataGeneratorForAPI.getCurrentDate("yyyy-MM-dd");

		String requestPayload = TaskFolderPayloads.employeeIdAndDatePayload(employeeId, currentDate);
		response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllTasksForDayEndpoint);

		BodyValidation.responseValidation(response, 200);

		taskIds = response.jsonPath().getList("taskId");
		log.info("List of task Ids " + message + " are: " + taskIds);

		taskTitles = response.jsonPath().getList("taskTitle");
		log.info("List of task titles " + message + " are: " + taskTitles);

		taskScheduleDates = response.jsonPath().getList("taskSchedule");
		log.info("List of task schedule dates " + message + " are: " + taskScheduleDates);

		taskDueDates = response.jsonPath().getList("taskDueDate");
		log.info("List of task due dates " + message + " are: " + taskDueDates);

		taskOwners = response.jsonPath().getList("taskOwner.empFullName");
		log.info("List of task owners " + message + " are: " + taskOwners);

		taskStatuses = response.jsonPath().getList("statusEntity.status");
		log.info("List of task statuses " + message + " are: " + taskStatuses);

		taskPriorities = response.jsonPath().getList("priorityEntity.priority");
		log.info("List of task priorities " + message + " are: " + taskPriorities);

		taskProjects = response.jsonPath().getList("projectEntity.projectName");
		log.info("List of task projects " + message + " are: " + taskProjects);

		taskVerifications = response.jsonPath().getList("task_verified.verificationStatus");
		log.info("List of task verifications " + message + " are: " + taskVerifications);
	}
}

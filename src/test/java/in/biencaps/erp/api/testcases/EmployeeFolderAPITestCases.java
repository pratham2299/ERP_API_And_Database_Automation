package in.biencaps.erp.api.testcases;

import static org.testng.Assert.*;

import java.util.*;
import java.util.regex.*;

import org.apache.logging.log4j.*;

import org.testng.annotations.*;

import com.aventstack.extentreports.*;

import in.biencaps.erp.api.bodyValidations.*;
import in.biencaps.erp.api.endpoints.*;
import in.biencaps.erp.api.payloads.*;
import in.biencaps.erp.api.responses.*;
import in.biencaps.erp.api.utilities.*;
import io.restassured.response.*;

public class EmployeeFolderAPITestCases extends BaseTest {
	public static final Logger log = LogManager.getLogger(EmployeeFolderAPITestCases.class);
	public static List<String> userIds;
	private static String maxValueOfUserId;
	public List<Integer> empIds;
	public List<Integer> empployeeIds;
	public static String newUserId;

	public static List<String> employeeFullNames;
	public static List<Integer> employeeIds;

	private Response response;
	private Random random = new Random();

	private Map<String, String> empIdToNameMap = new HashMap<>();
	private Map<String, String> empIdToStatusMap = new HashMap<>();

	@Test(priority = 1)
	public void verify_Add_Employee_Without_Authorization() {
		test = BaseTest.extent.createTest("Add employee without authorization");

		String requestPayload = EmployeeFolderPayloads.addEmployeePayload("John Doe", "INC020", "2024-05-24", "ACTIVE",
				3, 3, 5, 1, "john.doe@gmail.com", "9876543210", "Pune");

		Response response = Responses.postRequestWithoutAuthorization(requestPayload, APIEndpoints.addEmployeeEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("add employee", APIEndpoints.addEmployeeEndpoint, response);
	}

	@Test(priority = 2)
	public void verify_Get_Single_Employee_Without_Authorization() {
		test = BaseTest.extent.createTest("Get single employee without authorization");

		String requestPayload = EmployeeFolderPayloads.getSingleEmployeePayload("INC012");

		Response response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.getSingleEmployeeEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("get single employee", APIEndpoints.getSingleEmployeeEndpoint, response);
	}

	@Test(priority = 3)
	public void verify_Get_UserId_Without_Authorization() {
		test = BaseTest.extent.createTest("Get user Id without authorization");

		Response response = Responses.getRequestWithoutAuthorization(APIEndpoints.getUserIdEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("get user Id", APIEndpoints.getUserIdEndpoint, response);
	}

	@Test(priority = 5)
	public void verify_Get_All_Reporting_Authorities_Without_Authorization() {
		test = BaseTest.extent.createTest("Get reporting authority without authorization");

		Response response = Responses.getRequestWithoutAuthorizationAndTwoQueryParameter(
				APIEndpoints.getAllHigherAuthoritiesEndpoint, "role", 1, "department", "Testing");

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("get all reporting authorities", APIEndpoints.getAllHigherAuthoritiesEndpoint,
				response);
	}

	@Test(priority = 6)
	public void verify_Get_All_Assignees_Without_Authorization() {
		test = BaseTest.extent.createTest("Get all assignee without authorization");

		String requestPayload = EmployeeFolderPayloads.getAssigneePayload(3);

		Response response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.getAllAssigneesEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("get all assignees", APIEndpoints.getAllAssigneesEndpoint, response);
	}

	@Test(priority = 7)
	public void verify_Get_All_Employees_Without_Authorization() {
		test = BaseTest.extent.createTest("Get all employees without authorization");

		Response response = Responses.getRequestWithoutAuthorizationAndThreeQueryParameter(
				APIEndpoints.getAllEmployeesEndpoint, "page", 0, "size", 20, "key", "");

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("get all employees", APIEndpoints.getAllEmployeesEndpoint, response);
	}

	@Test(priority = 8)
	public void verify_Get_All_Active_Users_Info_Without_Authorization() {
		test = BaseTest.extent.createTest("Get all active users info without authorization");

		Response response = Responses.getRequestWithoutAuthorization(APIEndpoints.getAllActiveUsersInfoEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("get all active users info", APIEndpoints.getAllActiveUsersInfoEndpoint, response);
	}

	@Test(priority = 9)
	public void verify_Get_All_Employees_Ids_And_Names_Without_Authorization() {
		test = BaseTest.extent.createTest("Get all employees Id and Names without authorization");

		Response response = Responses.getRequestWithoutAuthorization(APIEndpoints.getAllEmployeesIdAndNameEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("get all employees Ids and names", APIEndpoints.getAllEmployeesIdAndNameEndpoint,
				response);
	}

	@Test(priority = 10)
	public void verify_Get_Assigned_Task_Info_By_Role_Without_Authorization() {
		test = BaseTest.extent.createTest("Get assigned task info by role without authorization");

		Response response = Responses.getRequestWithoutAuthorizationPathParameterAndOneQueryParameter(
				APIEndpoints.getAssignedTaskInfoByRoleEndpoint, "Team Lead", "date", "2024/02/15");

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("get assigned task info by role", APIEndpoints.getAssignedTaskInfoByRoleEndpoint,
				response);
	}

	@Test(priority = 11)
	public void verify_Get_Search_Employee_In_Level_Without_Authorization() {
		test = BaseTest.extent.createTest("Get search employee in level without authorization");

		String requestPayload = EmployeeFolderPayloads.giveEmployeePayloadForSearchEmployeeInLevel("Prat", "2024-03-13",
				"Team Lead");

		Response response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.getSearchEmployeeInLevelEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("get search employee in level", APIEndpoints.getSearchEmployeeInLevelEndpoint,
				response);
	}

	@Test(priority = 12)
	public void verify_Add_Token_For_Web_Without_Authorization() {
		test = BaseTest.extent.createTest("Add token for web without authorization");

		String requestPayload = EmployeeFolderPayloads.giveEmployeePayloadForAddToken("hpyn gyje jpsp ipqr", "BIE018");

		Response response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.addTokenForWebEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("add token for web", APIEndpoints.addTokenForWebEndpoint, response);
	}

	@Test(priority = 13)
	public void verify_Add_Token_For_Mobile_Without_Authorization() {
		test = BaseTest.extent.createTest("Add token for mobile without authorization");

		String requestPayload = EmployeeFolderPayloads.giveEmployeePayloadForAddToken("hpyn gyje jpsp ipqr", "BIE018");

		Response response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.addTokenForMobileEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("add token for mobile", APIEndpoints.addTokenForMobileEndpoint, response);
	}

	@Test(priority = 14)
	public void verify_Update_Employee_Without_Authorization() {
		test = BaseTest.extent.createTest("Update employee without authorization");

		String requestPayload = EmployeeFolderPayloads.updateEmployeePayload(20, "John Doe", "2024-05-24", "ACTIVE", 3,
				3, 5, "9876543210", "7894561230", "john@biencaps.com", "john.doe@gmail.com", "Narhe, Pune", "B+",
				"2000-05-01", "Pune");

		Response response = Responses.putRequestWithoutAuthorization(requestPayload,
				APIEndpoints.updateEmployeeEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("update employee", APIEndpoints.updateEmployeeEndpoint, response);
	}

	@Test(priority = 15)
	public void verify_Update_Password_Without_Authorization() {
		test = BaseTest.extent.createTest("Update password without authorization");

		String requestPayload = EmployeeFolderPayloads.updatePasswordPayload("BIE018", "Pass@123", "Pass@1234",
				"Pass@1234");

		Response response = Responses.putRequestWithoutAuthorization(requestPayload,
				APIEndpoints.updatePasswordEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("update password", APIEndpoints.updatePasswordEndpoint, response);
	}

	@Test(priority = 16)
	public void verify_Get_Task_Owners_Without_Authorization() {
		test = BaseTest.extent.createTest("Get task owners without authorization");

		String requestPayload = EmployeeFolderPayloads.getTaskOwnersPayload(2);

		Response response = Responses.putRequestWithoutAuthorization(requestPayload,
				APIEndpoints.getTaskOwnersEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("get task owners", APIEndpoints.getTaskOwnersEndpoint, response);
	}

	@Test(priority = 17, dataProvider = "TestDataForGetAllEmployees", dataProviderClass = DataProvidersForEmployeeFolder.class)
	public void verify_Get_All_Employee_With_Authorization(String authToken) {
		test = BaseTest.extent.createTest("Get all employees with authorization");

		response = Responses.getRequestWithAuthorizationAndThreeQueryParameter(authToken,
				APIEndpoints.getAllEmployeesEndpoint, "page", 0, "size", 20, "key", "");

		BaseTest.test_Method_Logs("get all employees", APIEndpoints.getAllEmployeesEndpoint, response);

		if (!authToken.equalsIgnoreCase(LoginEmployeeAPITestCases.authToken)) {
			BodyValidation.responseValidation(response, "Unauthorized", 401);
		} else {
			BodyValidation.responseValidation(response, 200);

			userIds = response.jsonPath().getList("data.userId");
			log.info("List of user Ids are: " + userIds);

			maxValueOfUserId = response.jsonPath().getString("data.max { it.userId }.userId");
			log.info("Max value of user Id is: " + maxValueOfUserId);

			// Parse the JSON response to get the employee data
			List<Map<String, Object>> employees = response.jsonPath().getList("data");

			employeeFullNames = response.jsonPath().getList("data.empFullName");

			employeeIds = response.jsonPath().getList("data.empId");

			// Iterate through the employees and extract empId and empFullName
			for (Map<String, ?> employee : employees) {
				String empId = String.valueOf(employee.get("empId"));
				String empFullName = String.valueOf(employee.get("empFullName"));

				empIdToNameMap.put(empId, empFullName);
			}

			// Iterate through the employees and extract empId and empFullName
			for (Map<String, ?> employee : employees) {
				String empId = String.valueOf(employee.get("empId"));
				String empStatus = String.valueOf(employee.get("empStatus"));

				empIdToStatusMap.put(empId, empStatus);
			}
		}
	}

	@Test(priority = 18, dependsOnMethods = "verify_Get_All_Employee_With_Authorization")
	public void verify_Get_Single_Employee_With_Authorization() {
		test = BaseTest.extent.createTest("Get single employee with authorization");

		int randomIndexForUserId = random.nextInt(userIds.size());
		String randomUserId = userIds.get(randomIndexForUserId);
		log.info("Random user Id for get single employee is: " + randomUserId);

		String requestPayload = EmployeeFolderPayloads.getSingleEmployeePayload(randomUserId);

		Response response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getSingleEmployeeEndpoint);
		test.log(Status.INFO, "API endpoint for get single employee is => " + APIEndpoints.getSingleEmployeeEndpoint);
		test.log(Status.INFO, "Status code for get single employee is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for add employee is => " + response.getBody().asPrettyString());

		BodyValidation.responseValidation(response, 200);

		String userIdFromResponse = response.jsonPath().getString("userId");
		log.info("User Id from response for get single employee  is: " + userIdFromResponse + "\n");

		assertEquals(userIdFromResponse, randomUserId);
		assertTrue(userIds.contains(userIdFromResponse));
	}

	@Test(priority = 19, dependsOnMethods = "verify_Get_All_Employee_With_Authorization")
	public void verify_Get_Single_Employee_By_Giving_Invalid_UserId() {
		test = BaseTest.extent.createTest("Get single employee with authorization");

		int numeric_part_of_user_id = DataGeneratorForAPI.generateFakeNumberWithRange(50, 100);
		String fakeUserId = "INC0" + String.valueOf(numeric_part_of_user_id);

		String requestPayload = EmployeeFolderPayloads.getSingleEmployeePayload(fakeUserId);

		Response response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getSingleEmployeeEndpoint);
		test.log(Status.INFO, "API endpoint for get single employee is => " + APIEndpoints.getSingleEmployeeEndpoint);
		test.log(Status.INFO, "Status code for get single employee is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for add employee is => " + response.getBody().asPrettyString());

		BodyValidation.responseValidation(response, "Not Found", 404);

		assertFalse(userIds.contains(fakeUserId));
	}

	@Test(priority = 20)
	public void verify_Get_Task_Owners_With_Authorization() {
		test = BaseTest.extent.createTest("Get task owners with authorization");

		int randomIndexForRoleLevel = random.nextInt(RoleFolderAPITestCases.roleLevels.size());
		int randomRoleLevel = RoleFolderAPITestCases.roleLevels.get(randomIndexForRoleLevel);
		log.info("Random role level for get reporting authority is: " + randomRoleLevel + "\n");

		String requestPayload = EmployeeFolderPayloads.getTaskOwnersPayload(randomRoleLevel);

		Response response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getTaskOwnersEndpoint);
		test.log(Status.INFO, "API endpoint for get task owners is => " + APIEndpoints.getTaskOwnersEndpoint);
		test.log(Status.INFO, "Status code for get task owners is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for get task owners is => " + response.getBody().asPrettyString());

		BodyValidation.responseValidation(response, 200);
	}

	@Test(priority = 21, dataProvider = "TestDataForAddEmployee", dataProviderClass = DataProvidersForEmployeeFolder.class, enabled = false)
	public void verify_Add_Employee_With_Authorization(String employeeFullName, String userId,
			String employeeJoiningDate, String employeeStatus, int departmentId, int designationId, int roleId,
			int reportingAuthorityEmpId, String employeePersonalEmail, String employeeMobileNumber1,
			String employeeOfficeLocation) {
		test = BaseTest.extent.createTest("Add employee with valid and invalid data and with authorization");

		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

		// Create a Pattern object
		Pattern pattern = Pattern.compile(emailRegex);

		// Create a matcher object
		Matcher matcher = pattern.matcher(employeePersonalEmail);

		if (DesignationFolderAPITestCases.designationIds != null && DepartmentFolderAPITestCases.departmentIds != null
				&& RoleFolderAPITestCases.roleIds != null) {
			String requestPayload = EmployeeFolderPayloads.addEmployeePayload(employeeFullName, userId,
					employeeJoiningDate, employeeStatus, departmentId, designationId, roleId, reportingAuthorityEmpId,
					employeePersonalEmail, employeeMobileNumber1, employeeOfficeLocation);

			Response response = Responses.postRequestWithAuthorization(requestPayload,
					LoginEmployeeAPITestCases.authToken, APIEndpoints.addEmployeeEndpoint);
			test.log(Status.INFO, "API endpoint for add employee is => " + APIEndpoints.addEmployeeEndpoint);
			test.log(Status.INFO, "Status code for add employee is => " + response.getStatusCode());
			test.log(Status.INFO, "Response for add employee is => " + response.getBody().asPrettyString());

			if (userId.isBlank() || employeeFullName.isBlank() || employeeStatus.isBlank()
					|| employeePersonalEmail.isBlank() || employeeMobileNumber1.isBlank()
					|| employeeJoiningDate.isBlank() || matcher.matches() == false
					|| employeeMobileNumber1.length() < 10 || employeeMobileNumber1.length() > 10) {
				BodyValidation.response400Validation(response);
			} else if (DesignationFolderAPITestCases.designationIds.contains(designationId) == false) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (DepartmentFolderAPITestCases.departmentIds.contains(departmentId) == false) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (RoleFolderAPITestCases.roleIds.contains(roleId) == false) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (userIds.contains(userId)) {
				BodyValidation.responseValidation(response, "Conflict", 409);
			} else {
				BodyValidation.responseValidation(response, 201);
			}
		} else {
			log.info("Designation Ids are null");
			log.info("Department Ids are null");
			log.info("Role Ids are null");
		}
	}

	@Test(priority = 22, dependsOnMethods = "verify_Get_All_Employee_With_Authorization")
	public void verify_Get_User_Id_With_Authorization() {
		test = BaseTest.extent.createTest("Get user Id with authorization");

		String numericPart = maxValueOfUserId.substring(3);

		int numeric_part = Integer.parseInt(numericPart);

		int new_numeric_part = numeric_part + 1;
		String newNumericPart = String.valueOf(new_numeric_part);

		newUserId = "INC0" + newNumericPart;
		log.info("New User Id for get user id is: " + newUserId + "\n");

		Response response = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getUserIdEndpoint);
		test.log(Status.INFO, "API endpoint for get user Id is => " + APIEndpoints.getUserIdEndpoint);
		test.log(Status.INFO, "Status code for get user Id is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for get user Id is => " + response.getBody().asPrettyString());

		String responseBody = response.getBody().asPrettyString();

		int responseBodyLength = response.getBody().asPrettyString().length();
		String contentLength = String.valueOf(responseBodyLength);

		assertEquals(responseBody, newUserId);

		BodyValidation.responseValidation(response, 200, contentLength);
	}

	@Test(priority = 23)
	public void verify_Get_All_Employee_Id_And_Names_With_Authorization() {
		test = BaseTest.extent.createTest("Get all employees Id and names with authorization");

		Response response = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllEmployeesIdAndNameEndpoint);
		test.log(Status.INFO, "API endpoint for get all employees Ids and names is => "
				+ APIEndpoints.getAllEmployeesIdAndNameEndpoint);
		test.log(Status.INFO, "Status code for get all employees Ids and names is => " + response.getStatusCode());
		test.log(Status.INFO,
				"Response for get all employees Ids and names is => " + response.getBody().asPrettyString());

		BodyValidation.responseValidation(response, 200);
		List<Integer> employeeIds = response.jsonPath().getList("empId");
		log.info("List of emp Ids are: " + employeeIds);

		List<String> employeeFullNames = response.jsonPath().getList("fullName");
		log.info("List of employee full names are: " + employeeFullNames);

		for (int employeeId : employeeIds) {
			for (Map.Entry<String, String> entry : empIdToNameMap.entrySet()) {
				if (entry.getKey().equalsIgnoreCase(String.valueOf(employeeId))) {
					assertEquals(String.valueOf(employeeId), entry.getKey());
				}
			}
		}

		for (String employeeFullName : employeeFullNames) {
			for (Map.Entry<String, String> entry : empIdToNameMap.entrySet()) {
				if (entry.getValue().equalsIgnoreCase(String.valueOf(employeeFullName))) {
					assertEquals(String.valueOf(employeeFullName), entry.getValue());
				}
			}
		}
	}

	@Test(priority = 24)
	public void verify_Get_Reporting_Authority_With_Authorization() {
		test = BaseTest.extent.createTest("Get reporting authority with authorization");

		if (DepartmentFolderAPITestCases.departments != null && RoleFolderAPITestCases.roleLevels != null) {
			int randomIndexForDepartment = random.nextInt(DepartmentFolderAPITestCases.departments.size());
			String randomDepartment = DepartmentFolderAPITestCases.departments.get(randomIndexForDepartment);
			log.info("Random department for get reporting authority is: " + randomDepartment);

			int randomIndexForRoleLevel = random.nextInt(RoleFolderAPITestCases.roleLevels.size());
			int randomRoleLevel = RoleFolderAPITestCases.roleLevels.get(randomIndexForRoleLevel);
			log.info("Random role level for get reporting authority is: " + randomRoleLevel + "\n");

			Response response = Responses.getRequestWithAuthorizationAndTwoQueryParameter(
					LoginEmployeeAPITestCases.authToken, APIEndpoints.getAllHigherAuthoritiesEndpoint, "role",
					randomRoleLevel, "department", randomDepartment);
			test.log(Status.INFO, "API endpoint for get all reporting authorities is => "
					+ APIEndpoints.getAllHigherAuthoritiesEndpoint);
			test.log(Status.INFO, "Status code for get all reporting authorities is => " + response.getStatusCode());
			test.log(Status.INFO,
					"Response for get all reporting authorities is => " + response.getBody().asPrettyString());

			BodyValidation.responseValidation(response, 200);

			assertTrue(DepartmentFolderAPITestCases.departments.contains(randomDepartment));
			assertTrue(RoleFolderAPITestCases.roleLevels.contains(randomRoleLevel));
		} else {
			log.info("Role levels are null");
			log.info("Departments are null");
		}
	}

	@Test(priority = 25)
	public void verify_Get_Reporting_Authority_By_Giving_Invalid_Department() {
		test = BaseTest.extent.createTest("Get reporting authority with authorization");

		if (DepartmentFolderAPITestCases.departments != null && RoleFolderAPITestCases.roleLevels != null) {
			int randomIndexForRoleLevel = random.nextInt(RoleFolderAPITestCases.roleLevels.size());
			int randomRoleLevel = RoleFolderAPITestCases.roleLevels.get(randomIndexForRoleLevel);
			log.info("Random role level for get reporting authority is: " + randomRoleLevel + "\n");

			String fakeDepartment = DataGeneratorForAPI.generateFakeDepartment();

			Response response = Responses.getRequestWithAuthorizationAndTwoQueryParameter(
					LoginEmployeeAPITestCases.authToken, APIEndpoints.getAllHigherAuthoritiesEndpoint, "role",
					randomRoleLevel, "department", fakeDepartment);
			test.log(Status.INFO, "API endpoint for get all reporting authorities is => "
					+ APIEndpoints.getAllHigherAuthoritiesEndpoint);
			test.log(Status.INFO, "Status code for get all reporting authorities is => " + response.getStatusCode());
			test.log(Status.INFO,
					"Response for get all reporting authorities is => " + response.getBody().asPrettyString());

			assertFalse(DepartmentFolderAPITestCases.departments.contains(fakeDepartment));

			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			log.info("Role levels are null");
			log.info("Departments are null");
		}
	}

	@Test(priority = 26)
	public void verify_Get_All_Assignee_With_Authorization_By_Giving_Valid_Role_Level() {
		test = BaseTest.extent.createTest("Get all assignee with authorization");

		int randomIndexForRoleLevel = random.nextInt(RoleFolderAPITestCases.roleLevels.size());
		int randomRoleLevel = RoleFolderAPITestCases.roleLevels.get(randomIndexForRoleLevel);
		log.info("Random role level for get assignee is: " + randomRoleLevel + "\n");

		String requestPayload = EmployeeFolderPayloads.getAssigneePayload(randomRoleLevel);

		Response response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllAssigneesEndpoint);
		test.log(Status.INFO, "API endpoint for get all assignees is => " + APIEndpoints.getAllAssigneesEndpoint);
		test.log(Status.INFO, "Status code for get all assignees is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for get all assignees is => " + response.getBody().asPrettyString());

		BodyValidation.responseValidation(response, 200);

		assertTrue(RoleFolderAPITestCases.roleLevels.contains(randomRoleLevel));
	}

	@Test(priority = 27, enabled = false)
	public void verify_Get_All_Active_Users_Info_With_Authorization() {
		test = BaseTest.extent.createTest("Get all active users info with authorization");

		Response response = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllActiveUsersInfoEndpoint);
		test.log(Status.INFO,
				"API endpoint for get all active users info is => " + APIEndpoints.getAllActiveUsersInfoEndpoint);
		test.log(Status.INFO, "Status code for get all active users info is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for get all active users info is => " + response.getBody().asPrettyString());

		BodyValidation.responseValidation(response, 200);

		List<String> employeeFullNames = new ArrayList<String>();
		for (int i = 0; i < 20; i++) {
			employeeFullNames.add(response.jsonPath().getString("" + i + ".empFullName"));
		}

		for (String employeeFullName : employeeFullNames) {
			for (Map.Entry<String, String> entry : empIdToStatusMap.entrySet()) {
				if (entry.getValue().equalsIgnoreCase(String.valueOf("ACTIVE"))) {
					assertEquals(String.valueOf(employeeFullName), entry.getKey());
				}
			}
		}
	}

	@Test(priority = 28, dataProvider = "TestDataForGetAssignedTaskInfoByRole", dataProviderClass = DataProvidersForEmployeeFolder.class)
	public void verify_Get_Assigned_Task_Info_By_Role_With_Authorization(String roleName, String date) {
		test = BaseTest.extent.createTest("Get assigned task info by role with authorization");

		Response response = Responses.getRequestWithAuthorizationPathParameterAndOneQueryParameter(
				LoginEmployeeAPITestCases.authToken, APIEndpoints.getAssignedTaskInfoByRoleEndpoint, roleName, "date",
				date);
		test.log(Status.INFO, "API endpoint for get assigned task info by role is => "
				+ APIEndpoints.getAssignedTaskInfoByRoleEndpoint);
		test.log(Status.INFO, "Path parameter for get assigned task info by role is => " + roleName);
		test.log(Status.INFO, "Query parameter for get assigned task info by role is => " + date);
		test.log(Status.INFO, "Status code for get assigned task info by role is => " + response.getStatusCode());
		test.log(Status.INFO,
				"Response for get assigned task info by role is => " + response.getBody().asPrettyString());

		if (response.getBody().asPrettyString().equalsIgnoreCase("[]")) {
			BodyValidation.responseValidation(response, 200);
		} else if (response.getStatusCode() == 400) {
			BodyValidation.response400Validation(response);
		} else if (!RoleFolderAPITestCases.roles.contains(roleName)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			BodyValidation.responseValidation(response, 200);

			assertTrue(RoleFolderAPITestCases.roles.contains(roleName));
		}
	}

	@Test(priority = 29, dataProvider = "TestDataForUpdateEmployee", dataProviderClass = DataProvidersForEmployeeFolder.class, enabled = false)
	public void verify_Update_Employee_With_Authorization(int employeeId, String employeeFullName,
			String employeeJoiningDate, String employeeStatus, int departmentId, int designationId, int roleId,
			String employeeMobileNumber1, String employeeMobileNumber2, String employeeEmailOfficial,
			String employeePersonalEmail, String employeeOfficeLocation, String employeeBloodGroup, String employeeDOB,
			String employeeAddress) {
		test = BaseTest.extent.createTest("Update employee with valid and invalid data with authorization");

		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

		// Create a Pattern object
		Pattern pattern = Pattern.compile(emailRegex);

		// Create a matcher object
		Matcher matcherForPersonalEmail = pattern.matcher(employeePersonalEmail);
		Matcher matcherForOfficialEmail = pattern.matcher(employeeEmailOfficial);

		if (DesignationFolderAPITestCases.designationIds != null || DepartmentFolderAPITestCases.departmentIds != null
				|| RoleFolderAPITestCases.roleIds != null) {

			String requestPayload = EmployeeFolderPayloads.updateEmployeePayload(employeeId, employeeFullName,
					employeeJoiningDate, employeeStatus, departmentId, designationId, roleId, employeeMobileNumber1,
					employeeMobileNumber2, employeeEmailOfficial, employeePersonalEmail, employeeOfficeLocation,
					employeeBloodGroup, employeeDOB, employeeAddress);

			Response response = Responses.putRequestWithAuthorization(requestPayload,
					LoginEmployeeAPITestCases.authToken, APIEndpoints.updateEmployeeEndpoint);

			String responseBody = response.getBody().asPrettyString();
			test.log(Status.INFO, "API endpoint for update employee is => " + APIEndpoints.updateEmployeeEndpoint);
			test.log(Status.INFO, "Request payload for update employee is => " + requestPayload);
			test.log(Status.INFO, "Status code for update employee is => " + response.getStatusCode());
			test.log(Status.INFO, "Response for update employee is => " + response.getBody().asPrettyString());

			if (employeeFullName.isBlank()) {
				BodyValidation.response400Validation(response);
			} else if (!employeeIds.contains(employeeId)) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (employeeStatus.isBlank()) {
				BodyValidation.response400Validation(response);
			} else if (employeeOfficeLocation.isBlank()) {
				BodyValidation.response400Validation(response);
			} else if (employeeJoiningDate.isBlank()) {
				BodyValidation.response400Validation(response);
			} else if (employeeEmailOfficial.isBlank() || matcherForOfficialEmail.matches() == false) {
				BodyValidation.response400Validation(response);
			} else if (employeePersonalEmail.isBlank() || matcherForPersonalEmail.matches() == false) {
				BodyValidation.response400Validation(response);
			} else if (employeeMobileNumber1.isBlank() || employeeMobileNumber1.length() < 10
					|| employeeMobileNumber1.length() > 10 || employeeMobileNumber2.length() < 10
					|| employeeMobileNumber2.length() > 10) {
				BodyValidation.responseValidation(response, "Unprocessable Entity", 422);
			} else if (!DesignationFolderAPITestCases.designationIds.contains(designationId)) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (!DepartmentFolderAPITestCases.departmentIds.contains(departmentId)) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (!RoleFolderAPITestCases.roleIds.contains(roleId)) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else {
				int contentLength = response.getBody().asPrettyString().length();
				BodyValidation.responseValidation(response, 200, String.valueOf(contentLength));
				assertEquals(responseBody, "Employee Updated Successfully");
			}
		} else {
			log.info("Designation Ids are null");
			log.info("Department Ids are null");
			log.info("Role Ids are null");
		}
	}

	@Test(priority = 30, dataProvider = "TestDataForUpdatePassword", dataProviderClass = DataProvidersForEmployeeFolder.class, enabled = false)
	public void verify_Update_Password_With_Authorization(String userId, String oldPassword, String newPassword,
			String confirmPassword) {
		test = BaseTest.extent.createTest("Update password with valid and invalid data and with authorization");

		String userPassword = Constants.adminPassword;

		String requestPayload = EmployeeFolderPayloads.updatePasswordPayload(userId, oldPassword, newPassword,
				confirmPassword);

		log.info("User password is: " + userPassword);
		log.info("Old password is: " + oldPassword);
		log.info("New password is: " + newPassword + "\n");

		Response response = Responses.putRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.updatePasswordEndpoint);

		String responseBody = response.getBody().asPrettyString();
		test.log(Status.INFO, "API endpoint for update password is => " + APIEndpoints.updatePasswordEndpoint);
		test.log(Status.INFO, "Request payload for update password is => " + requestPayload);
		test.log(Status.INFO, "Status code for update password is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for update password is => " + responseBody);

		if (userId.isBlank() || oldPassword.isBlank() || newPassword.isBlank() || confirmPassword.isBlank()) {
			BodyValidation.response400Validation(response);
		} else if (!confirmPassword.equalsIgnoreCase(newPassword) || !userPassword.equalsIgnoreCase(oldPassword)) {
			BodyValidation.responseValidation(response, "Unauthorized", 401);
		} else if (!userIds.contains(userId)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			BodyValidation.responseValidation(response, "OK", 200);

			userPassword = newPassword;
			log.info("Updated user password is: " + userPassword + "\n");
		}
	}

	@Test(priority = 31, dataProvider = "TestDataForGetEncryptedEmail", dataProviderClass = DataProvidersForEmployeeFolder.class)
	public void verify_Get_Encrypted_Email_With_Authorization(String userId) {
		test = BaseTest.extent.createTest("Get encrypted email with authorization");

		Response response = Responses.getRequestWithAuthorizationAndOneQueryParameter(
				LoginEmployeeAPITestCases.authToken, APIEndpoints.getEncryptedEmailEndpoint, "userId", userId);
		test.log(Status.INFO, "API endpoint for get encrypted email is => " + APIEndpoints.getEncryptedEmailEndpoint);
		test.log(Status.INFO, "Query parameter for get encrypted email is => " + userId);
		test.log(Status.INFO, "Status code for get encrypted email is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for get encrypted email is => " + response.getBody().asPrettyString());

		if (!userIds.contains(userId)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			BodyValidation.responseValidation(response, 200);
		}
	}

	@Test(priority = 32, dataProvider = "TestDataForSearchEmployeeInLevel", dataProviderClass = DataProvidersForEmployeeFolder.class)
	public void verify_Get_Search_Employee_In_Level_With_Authorization(String key, String role, String date) {
		test = BaseTest.extent.createTest("Get search employee in level with authorization");

		String requestPayload = EmployeeFolderPayloads.giveEmployeePayloadForSearchEmployeeInLevel(key, role, date);

		Response response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getSearchEmployeeInLevelEndpoint);
		test.log(Status.INFO,
				"API endpoint for get search employee in level is => " + APIEndpoints.getSearchEmployeeInLevelEndpoint);
		test.log(Status.INFO, "Request payload for get search employee in level is => " + requestPayload);
		test.log(Status.INFO, "Status code for get search employee in level is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for get search employee in level is => " + response.getBody().asPrettyString());

		if (date.isBlank()) {
			BodyValidation.response400Validation(response);
		} else if (response.getStatusCode() == 400 && date.isBlank() == false) {
			BodyValidation.response400Validation(response);
		} else if (!RoleFolderAPITestCases.roles.contains(role) || role.isBlank()) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			BodyValidation.responseValidation(response, 200);
		}
	}

	@Test(priority = 33, dataProvider = "TestDataForAddForgotPassword", dataProviderClass = DataProvidersForEmployeeFolder.class, enabled = false)
	public void verify_Add_Forgot_Password_With_Authorization(String userId) {
		test = BaseTest.extent.createTest("Add forgot password with valid and invalid data and with authorization");

		Response response = Responses.postRequestWithAuthorizationAndOneQueryParameter(
				LoginEmployeeAPITestCases.authToken, APIEndpoints.addForgotPasswordEndpoint, "userId", userId);
		test.log(Status.INFO, "API endpoint for add forgot password is => " + APIEndpoints.addForgotPasswordEndpoint);
		test.log(Status.INFO, "Query parameter for add forgot password is => " + userId);
		test.log(Status.INFO, "Status code for add forgot password is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for add forgot password is => " + response.getBody().asPrettyString());

		if (!userIds.contains(userId)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			BodyValidation.responseValidation(response, 200);
		}
	}

	@Test(priority = 34, dataProvider = "TestDataForAddToken", dataProviderClass = DataProvidersForEmployeeFolder.class)
	public void verify_Add_Token_For_Web_With_Authorization(String token, String userId) {
		test = BaseTest.extent.createTest("Add token for web with valid and invalid data and with authorization");

		String requestPayload = EmployeeFolderPayloads.giveEmployeePayloadForAddToken(token, userId);

		Response response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.addTokenForWebEndpoint);
		test.log(Status.INFO, "API endpoint for add token for web is => " + APIEndpoints.addTokenForWebEndpoint);
		test.log(Status.INFO, "Request payload for add token for web is => " + requestPayload);
		test.log(Status.INFO, "Status code for add token for web is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for add token for web is => " + response.getBody().asPrettyString());

		String responseBody = response.getBody().asPrettyString();

		if (token.isBlank()) {
			BodyValidation.response400Validation(response);
		} else if (userId.isBlank() || !userIds.contains(userId)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			assertEquals(responseBody, "token updated");
			int contentLength = responseBody.length();
			BodyValidation.responseValidation(response, 200, String.valueOf(contentLength));
		}
	}

	@Test(priority = 35, dataProvider = "TestDataForAddToken", dataProviderClass = DataProvidersForEmployeeFolder.class)
	public void verify_Add_Token_For_Mobile_With_Authorization(String token, String userId) {
		test = BaseTest.extent.createTest("Add token for mobile with valid and invalid data and with authorization");

		String requestPayload = EmployeeFolderPayloads.giveEmployeePayloadForAddToken(token, userId);

		Response response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.addTokenForMobileEndpoint);
		test.log(Status.INFO, "API endpoint for add token for mobile is => " + APIEndpoints.addTokenForMobileEndpoint);
		test.log(Status.INFO, "Request payload for add token for mobile is => " + requestPayload);
		test.log(Status.INFO, "Status code for add token for mobile is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for add token for mobile is => " + response.getBody().asPrettyString());

		String responseBody = response.getBody().asPrettyString();

		if (token.isBlank()) {
			BodyValidation.response400Validation(response);
		} else if (!userIds.contains(userId) || userId.isBlank()) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			assertEquals(responseBody, "token updated");
			int contentLength = responseBody.length();
			BodyValidation.responseValidation(response, 200, String.valueOf(contentLength));
		}
	}
}

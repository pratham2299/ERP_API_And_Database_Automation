package in.task_erp_api.testcases;

import static org.testng.Assert.*;

import java.util.*;
import java.util.regex.*;

import org.apache.logging.log4j.*;

import org.testng.ITestContext;
import org.testng.annotations.*;

import com.github.javafaker.Faker;

import in.task_erp_api.bodyValidations.*;
import in.task_erp_api.endpoints.*;
import in.task_erp_api.payloads.*;
import in.task_erp_api.responses.*;
import in.task_erp_api.utilities.*;
import io.restassured.response.*;

public class EmployeeFolderAPITestCases {
	public static final Logger log = LogManager.getLogger(EmployeeFolderAPITestCases.class);
	public static List<String> userIds;
	private static String maxValueOfUserId;
	public List<Integer> empIds;

	public static List<String> employeeFullNames;
	public static List<Integer> employeeIds;

	private Response response;
	private Faker faker = new Faker();
	private Random random = new Random();

	private List<Map<String, ?>> employees;
	private Map<String, String> empIdToNameMap = new HashMap<>();

	public static String authTokenOfSuperAdmin;

	@Test(priority = 1)
	public void verify_Add_Employee_Without_Authorization() {
		String requestPayload = "{\r\n" + "    \"userId\": \"BIE009\",\r\n"
				+ "    \"empFullName\": \"Vishal Lohbande\",\r\n" + "    \"empStatus\": \"ACTIVE\",\r\n"
				+ "    \"designation\" : {\r\n" + "        \"designationId\" : 8\r\n" + "    },\r\n"
				+ "    \"department\" : {\r\n" + "        \"departmentId\" : 2\r\n" + "    },\r\n"
				+ "    \"role\" : [\r\n" + "        {\r\n" + "\r\n" + "            \"roleId\" : 4\r\n" + "        }\r\n"
				+ "    ],\r\n" + "    \"reportingAuthorities\" : [\r\n" + "        {\r\n"
				+ "            \"empId\" : 8\r\n" + "        },\r\n" + "        {\r\n"
				+ "            \"empId\" : 14\r\n" + "        }\r\n" + "    ],\r\n"
				+ "    \"empEmailPersonal\" : \"vishal@gmail.com\",\r\n" + "    \"empMobile1\" : \"9503896148\",\r\n"
				+ "    \"empOfficeLocation\" : \"Pune\",\r\n" + "    \"empJoiningDate\" : \"2023/12/25\",\r\n"
				+ "    \"empMobile2\" : \"\",\r\n" + "    \"empDOB\" : \"\",\r\n" + "    \"eempBloodGroup\" : \"\",\r\n"
				+ "    \"empAddress\" : \"\"\r\n" + "}\r\n" + "";

		response = Responses.postRequestWithoutAuthorization(requestPayload, APIEndpoints.addEmployeeEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 2)
	public void verify_GetSingleEmployeeWithoutAuthorization() {
		String requestPayload = EmployeeFolderPayloads.giveEmployeePayloadForGetSingleEmployee("BIE018");

		response = Responses.postRequestWithoutAuthorization(requestPayload, APIEndpoints.getSingleEmployeeEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 3)
	public void verifyGetUserIdWithoutAuthorization() {
		response = Responses.getRequestWithoutAuthorization(APIEndpoints.getUserIdEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 4)
	public void verifyGetSearchEmployeeWithoutAuthorization() {
		response = Responses.getRequestWithoutAuthorizationAndOneQueryParameter(APIEndpoints.getUserIdEndpoint, "key",
				"Pratham");

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 5)
	public void verifyGetReportingAuthorityWithoutAuthorization() {
		response = Responses.getRequestWithoutAuthorizationAndTwoQueryParameter(
				APIEndpoints.getAllHigherAuthoritiesEndpoint, "role", 1, "department", "Testing");

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 6)
	public void verifyGetAllAssigneeWithoutAuthorization() {
		String requestPayload = EmployeeFolderPayloads.giveEmployeePayloadForGetAssignee(3);

		response = Responses.postRequestWithoutAuthorization(requestPayload, APIEndpoints.getAllAssigneesEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 7)
	public void verifyGetAllEmployeeWithoutAuthorization() {
		response = Responses.getRequestWithoutAuthorization(APIEndpoints.getAllEmployeesEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 8)
	public void verifyGetAllActiveUsersinfoWithoutAuthorization() {
		response = Responses.getRequestWithoutAuthorization(APIEndpoints.getAllActiveUsersInfoEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 9)
	public void verifyGetAllEmployeeIdAndNamesWithoutAuthorization() {
		response = Responses.getRequestWithoutAuthorization(APIEndpoints.getAllEmployeesIdAndNameEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 10)
	public void verifyGetAssignedTaskInfoByRoleWithoutAuthorization() {
		response = Responses.getRequestWithoutAuthorizationPathParameterAndOneQueryParameter(
				APIEndpoints.getAssignedTaskInfoByRoleEndpoint, "Team Lead", "date", "2024/02/15");

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 11)
	public void verifyGetSearchEmployeeInLevelWithoutAuthorization() {
		String requestPayload = EmployeeFolderPayloads.giveEmployeePayloadForSearchEmployeeInLevel("Prat", "2024-03-13",
				"Team Lead");

		response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.getSearchEmployeeInLevelEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 12)
	public void verifyAddTokenForWebWithoutAuthorization() {
		String requestPayload = EmployeeFolderPayloads.giveEmployeePayloadForAddToken("hpyn gyje jpsp ipqr", "BIE018");

		response = Responses.postRequestWithoutAuthorization(requestPayload, APIEndpoints.addTokenForWebEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 13)
	public void verifyAddTokenForMobileWithoutAuthorization() {
		String requestPayload = EmployeeFolderPayloads.giveEmployeePayloadForAddToken("hpyn gyje jpsp ipqr", "BIE018");

		response = Responses.postRequestWithoutAuthorization(requestPayload, APIEndpoints.addTokenForMobileEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 14)
	public void verifyUpdateEmployeeWithoutAuthorization() {
		String requestPayload = "{\r\n" + "    \"empId\" : 7,\r\n"
				+ "    \"empFullName\" : \"Prathamesh Dhasade\", \r\n"
				+ "    \"empMobile1\" : \"9834530434\",    			\r\n"
				+ "    \"status\" : \"ACTIVE\",        		\r\n"
				+ "    \"empMobile2\" : \"9850708611\",          				\r\n"
				+ "    \"empBloodGroup\" : \"B+\",      \r\n" + "    \"empOfficeLocation\" : \"Narhe, Pune\",   \r\n"
				+ "    \"empJoiningDate\" : \"2023/11/01\",      \r\n" + "    \"reportingAuthority\" : [\r\n"
				+ "        {\r\n" + "            \"empId\" : 14\r\n" + "        },\r\n" + "        {\r\n"
				+ "            \"empId\" : 8\r\n" + "        }\r\n" + "    ],                       \r\n"
				+ "    \"role\" : [\r\n" + "        {\r\n" + "            \"roleId\" : 4\r\n" + "        }\r\n"
				+ "    ],               \r\n" + "    \"designation\" : {\r\n" + "        \"designationId\" : 2\r\n"
				+ "    },       \r\n" + "    \"department\" : { \r\n" + "        \"departmentId\" : 10\r\n"
				+ "    },         \r\n" + "    \"empAddress\" : \"Bibwewadi, Pune\",       \r\n"
				+ "    \"empDOB\" : \"1999/08/22\",        \r\n"
				+ "    \"empEmailOfficial\" : \"prathamesh@biencaps.com\",     \r\n"
				+ "    \"empEmailPersonal\" : \"prathameshdhasade99@gmail.com\"\r\n" + "}\r\n" + "";

		response = Responses.putRequestWithoutAuthorization(requestPayload, APIEndpoints.updateEmployeeEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 15)
	public void verifyUpdatePasswordWithoutAuthorization() {
		String requestPayload = EmployeeFolderPayloads.giveEmployeePayloadForUpdatePassword("BIE018", "Pass@123",
				"Pass@1234", "Pass@1234");

		response = Responses.putRequestWithoutAuthorization(requestPayload, APIEndpoints.updatePasswordEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 16)
	public void verifyLoginSuperAdminByGivingValidData() {
		String requestPayload = LoginFolderPayloads.loginEmployee("BIE025", "Pass@123");

		response = Responses.postRequestWithoutAuthorization(requestPayload, APIEndpoints.loginEmployeeEndpoint);

		BodyValidation.responseValidation(response, 200);

		authTokenOfSuperAdmin = response.jsonPath().getString("token");
		log.info("Token of super admin for add employee is: " + authTokenOfSuperAdmin + "\n");
	}

	@Test(priority = 17, dataProvider = "TestDataForGetAllEmployees", dataProviderClass = DataProvidersForEmployeeFolder.class)
	public void verifyGetAllEmployeesWithAuthorization(String authTokenInput) {
		response = Responses.getRequestWithAuthorization(authTokenInput, APIEndpoints.getAllEmployeesEndpoint);

		if (authTokenInput.equalsIgnoreCase(LoginEmployeeAPITestCases.authToken)) {
			BodyValidation.responseValidation(response, "Unauthorized", 401);
		} else {
			BodyValidation.responseValidation(response, 200);

			userIds = response.jsonPath().getList("userId");
			log.info("List of user Ids are: " + userIds);

			maxValueOfUserId = response.jsonPath().getString("max { it.userId }.userId");
			log.info("Max value of user Id is: " + maxValueOfUserId);

			empIds = response.jsonPath().getList("empId");
			log.info("List of employee Ids are: " + empIds);

			employeeFullNames = response.jsonPath().getList("empFullName");
			log.info("List of employee full names are: " + employeeFullNames + "\n");

			// Extract the list of employees from the response
			employees = response.jsonPath().getList("$");
		}
	}

	@Test(priority = 18)
	public void verifyGetSingleEmployeeWithAuthorization() {
		if (userIds != null) {
			int randomIndexForUserId = random.nextInt(userIds.size());
			String randomUserId = userIds.get(randomIndexForUserId);
			log.info("Random user Id for get single employee is: " + randomUserId);

			String requestPayload = EmployeeFolderPayloads.giveEmployeePayloadForGetSingleEmployee(randomUserId);

			response = Responses.postRequestWithAuthorization(requestPayload, authTokenOfSuperAdmin,
					APIEndpoints.getSingleEmployeeEndpoint);

			BodyValidation.responseValidation(response, 200);

			String userIdFromResponse = response.jsonPath().getString("userId");
			log.info("User Id from response for get single employee  is: " + userIdFromResponse + "\n");

			assertEquals(userIdFromResponse, randomUserId, "User Id from response does not match with random user Id");
			assertTrue(userIds.contains(userIdFromResponse));
		} else {
			log.info("User Ids are null");
		}
	}

	@Test(priority = 19)
	public void verifyGetSingleEmployeeByGivingInvalidUserId() {
		if (userIds != null) {
			int numeric_part_of_user_id = faker.number().numberBetween(50, 100);
			String fakeUserId = "BIE0" + String.valueOf(numeric_part_of_user_id);

			String requestPayload = EmployeeFolderPayloads.giveEmployeePayloadForGetSingleEmployee(fakeUserId);

			response = Responses.postRequestWithAuthorization(requestPayload, authTokenOfSuperAdmin,
					APIEndpoints.getSingleEmployeeEndpoint);

			BodyValidation.responseValidation(response, "Not Found", 404);

			assertFalse(userIds.contains(fakeUserId));
		} else {
			log.info("User Ids are null");
		}
	}

	@Test(dependsOnMethods = "verifyLoginSuperAdminByGivingValidData", dataProvider = "TestDataForAddEmployee", dataProviderClass = DataProvidersForEmployeeFolder.class, enabled = false)
	public void verifyAddEmployeeWithAuthorization(String userId, String employeeFullName, String employeeStatus,
			int designationId, int departmentId, int roleId, String employeePersonalEmail, String employeeMobileNumber1,
			String employeeJoiningDate, String employeeMobileNumber2, String employeeDOB, String employeeBloodGroup,
			String employeeAddress) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

		// Create a Pattern object
		Pattern pattern = Pattern.compile(emailRegex);

		// Create a matcher object
		Matcher matcher = pattern.matcher(employeePersonalEmail);

		if (DesignationFolderAPITestCases.designationIds != null && DepartmentFolderAPITestCases.departmentIds != null
				&& RoleFolderAPITestCases.roleIds != null) {
			String requestPayload = "{\r\n" + "    \"userId\": \"" + userId + "\",\r\n" + "    \"empFullName\": \""
					+ employeeFullName + "\",\r\n" + "    \"empStatus\": \"" + employeeStatus + "\",\r\n"
					+ "    \"designation\" : {\r\n" + "        \"designationId\" : " + designationId + "\r\n"
					+ "    },\r\n" + "    \"department\" : {\r\n" + "        \"departmentId\" : " + departmentId
					+ "\r\n" + "    },\r\n" + "    \"role\" : [\r\n" + "        {\r\n" + "\r\n"
					+ "            \"roleId\" : " + roleId + "\r\n" + "        }\r\n" + "    ],\r\n"
					+ "    \"reportingAuthorities\" : [\r\n" + "        {\r\n" + "            \"empId\" : 8\r\n"
					+ "        },\r\n" + "        {\r\n" + "            \"empId\" : 14\r\n" + "        }\r\n"
					+ "    ],\r\n" + "    \"empEmailPersonal\" : \"" + employeePersonalEmail + "\",\r\n"
					+ "    \"empMobile1\" : \"" + employeeMobileNumber1 + "\",\r\n"
					+ "    \"empOfficeLocation\" : \"Pune\",\r\n" + "    \"empJoiningDate\" : \"" + employeeJoiningDate
					+ "\",\r\n" + "    \"empMobile2\" : \"" + employeeMobileNumber2 + "\",\r\n" + "    \"empDOB\" : \""
					+ employeeDOB + "\",\r\n" + "    \"eempBloodGroup\" : \"" + employeeBloodGroup + "\",\r\n"
					+ "    \"empAddress\" : \"" + employeeAddress + "\"\r\n" + "}\r\n" + "";

			response = Responses.postRequestWithAuthorization(requestPayload, authTokenOfSuperAdmin,
					APIEndpoints.addEmployeeEndpoint);

			if (userId.equalsIgnoreCase("") || employeeFullName.equalsIgnoreCase("")
					|| employeeStatus.equalsIgnoreCase("") || employeePersonalEmail.equalsIgnoreCase("")
					|| employeeMobileNumber1.equalsIgnoreCase("") || employeeJoiningDate.equalsIgnoreCase("")
					|| employeeMobileNumber2.equalsIgnoreCase("") || employeeDOB.equalsIgnoreCase("")
					|| employeeBloodGroup.equalsIgnoreCase("") || employeeAddress.equalsIgnoreCase("")
					|| matcher.matches() == false || employeeMobileNumber1.length() < 10
					|| employeeMobileNumber1.length() > 10 || employeeMobileNumber2.length() < 10
					|| employeeMobileNumber2.length() > 10) {
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

	@Test(priority = 20)
	public void verifyGetUserIdWithAuthorization() {
		if (userIds != null) {
			String numericPart = maxValueOfUserId.substring(3);

			int numeric_part = Integer.parseInt(numericPart);

			int new_numeric_part = numeric_part + 1;
			String newNumericPart = String.valueOf(new_numeric_part);

			String newUserId = "BIE0" + newNumericPart;
			log.info("New User Id for get user id is: " + newUserId + "\n");

			response = Responses.getRequestWithAuthorization(authTokenOfSuperAdmin, APIEndpoints.getUserIdEndpoint);

			int responseBodyLength = response.getBody().asPrettyString().length();
			String contentLength = String.valueOf(responseBodyLength);

			assertEquals(response.getBody().asPrettyString(), newUserId, "Invalid response text");

			BodyValidation.responseValidation(response, 200, contentLength);
		} else {
			log.info("User Ids are null");
		}
	}

	@Test(priority = 21)
	public void verifyGetAllEmployeeIdAndNamesWithAuthorization(ITestContext context) {
		if (employees != null) {
			response = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
					APIEndpoints.getAllEmployeesIdAndNameEndpoint);

			BodyValidation.responseValidation(response, 200);
			employeeIds = response.jsonPath().getList("empId");
			log.info("List of emp Ids are: " + employeeIds);

			employeeFullNames = response.jsonPath().getList("fullName");
			log.info("List of employee full names are: " + employeeFullNames);

			// Iterate through the employees and extract empId and empFullName
			for (Map<String, ?> employee : employees) {
				String empId = String.valueOf(employee.get("empId"));
				String empFullName = String.valueOf(employee.get("empFullName"));

				empIdToNameMap.put(empId, empFullName);
			}

			for (Map.Entry<String, String> entry : empIdToNameMap.entrySet()) {
				if (entry.getValue().equalsIgnoreCase("Prathamesh Dhasade")) {
					log.info("Current logged in employee Id is: " + entry.getKey());
					log.info("Current logged in employee Full Name is: " + entry.getValue());
					context.setAttribute("currentEmployeeId", entry.getKey());
					break;
				}
			}

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
		} else {
			log.info("Employees are null");
		}
	}

	@Test(priority = 22)
	public void verifyGetSearchEmployeeWithAuthorization() {
		if (employeeFullNames != null) {
			int randomIndexForEmployeeName = random.nextInt(employeeFullNames.size());
			String randomEmployeeName = employeeFullNames.get(randomIndexForEmployeeName);
			log.info("Random employee name for search employee is: " + randomEmployeeName);

			response = Responses.getRequestWithAuthorizationAndOneQueryParameter(authTokenOfSuperAdmin,
					APIEndpoints.getSearchEmployeeEndpoint, "key", randomEmployeeName);

			BodyValidation.responseValidation(response, 200);

			String employeeFullNameFromResponse = response.jsonPath().getString("[0].empFullName");
			log.info("Employee full name from response for search employee is: " + employeeFullNameFromResponse + "\n");

			assertEquals(employeeFullNameFromResponse, randomEmployeeName,
					"Employee full name from response does not match with random employee full name");
			assertTrue(employeeFullNames.contains(randomEmployeeName));
		} else {
			log.info("List of employee full names are null");
		}
	}

	@Test(priority = 23)
	public void verifyGetSearchEmployeeByGivingInvalidEmployeeName() {
		String fakeEmployeeName = faker.name().lastName();

		if (employeeFullNames != null) {
			response = Responses.getRequestWithAuthorizationAndOneQueryParameter(LoginEmployeeAPITestCases.authToken,
					APIEndpoints.getSearchEmployeeEndpoint, "key", fakeEmployeeName);

			BodyValidation.responseValidation(response, 200);

			assertFalse(employeeFullNames.contains(fakeEmployeeName));

		} else {
			log.info("List of employee full names are null");
		}
	}

	@Test(priority = 24)
	public void verifyGetReportingAuthorityWithAuthorization() {
		if (DepartmentFolderAPITestCases.departments != null && RoleFolderAPITestCases.roleLevels != null) {
			int randomIndexForDepartment = random.nextInt(DepartmentFolderAPITestCases.departments.size());
			String randomDepartment = DepartmentFolderAPITestCases.departments.get(randomIndexForDepartment);
			log.info("Random department for get reporting authority is: " + randomDepartment);

			int randomIndexForRoleLevel = random.nextInt(RoleFolderAPITestCases.roleLevels.size());
			int randomRoleLevel = RoleFolderAPITestCases.roleLevels.get(randomIndexForRoleLevel);
			log.info("Random department for get reporting authority is: " + randomRoleLevel + "\n");

			response = Responses.getRequestWithAuthorizationAndTwoQueryParameter(LoginEmployeeAPITestCases.authToken,
					APIEndpoints.getAllHigherAuthoritiesEndpoint, "role", randomRoleLevel, "department",
					randomDepartment);

			BodyValidation.responseValidation(response, 200);

			assertTrue(DepartmentFolderAPITestCases.departments.contains(randomDepartment));
			assertTrue(RoleFolderAPITestCases.roleLevels.contains(randomRoleLevel));
		} else {
			log.info("Role levels are null");
			log.info("Departments are null");
		}
	}

	@Test(priority = 25)
	public void verifyGetReportingAuthorityByGivingInvalidDepartment() {
		if (DepartmentFolderAPITestCases.departments != null && RoleFolderAPITestCases.roleLevels != null) {
			int randomIndexForRoleLevel = random.nextInt(RoleFolderAPITestCases.roleLevels.size());
			int randomRoleLevel = RoleFolderAPITestCases.roleLevels.get(randomIndexForRoleLevel);
			log.info("Random role level for get reporting authority is: " + randomRoleLevel + "\n");

			String fakeDepartment = faker.country().name();

			response = Responses.getRequestWithAuthorizationAndTwoQueryParameter(LoginEmployeeAPITestCases.authToken,
					APIEndpoints.getAllHigherAuthoritiesEndpoint, "role", randomRoleLevel, "department",
					fakeDepartment);

			assertEquals(DepartmentFolderAPITestCases.departments.contains(fakeDepartment), false,
					"Department name is not contain in department name list");

			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			log.info("Role levels are null");
			log.info("Departments are null");
		}
	}

	@Test(priority = 26)
	public void verifyGetAllAssigneeWithAuthorizationByGivingValidRoleLevel() {
		if (RoleFolderAPITestCases.roleLevels != null) {
			int randomIndexForRoleLevel = random.nextInt(RoleFolderAPITestCases.roleLevels.size());
			int randomRoleLevel = RoleFolderAPITestCases.roleLevels.get(randomIndexForRoleLevel);
			log.info("Random role level for get assignee  is: " + randomRoleLevel + "\n");

			String requestPayload = EmployeeFolderPayloads.giveEmployeePayloadForGetAssignee(randomRoleLevel);

			response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
					APIEndpoints.getAllAssigneesEndpoint);

			BodyValidation.responseValidation(response, 200);

			assertEquals(RoleFolderAPITestCases.roleLevels.contains(randomRoleLevel), true,
					"Random role level does not contain in roleLevels list");
		} else {
			log.info("Role Ids are null");
		}
	}

	@Test(priority = 27, enabled = false)
	public void verifyGetAllActiveUsersinfoWithAuthorization() {
		response = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllActiveUsersInfoEndpoint);

		BodyValidation.responseValidation(response, 200);

		List<String> userIds = response.jsonPath().getList("userId");
		log.info("List of user Id for get all active users info are: " + userIds);

		List<String> employeeFullNames = response.jsonPath().getList("empFullName");
		log.info("List of employee full name for get all active users info is: " + employeeFullNames + "\n");
	}

	@Test(priority = 28, dataProvider = "TestDataForGetAssignedTaskInfoByRole", dataProviderClass = DataProvidersForEmployeeFolder.class)
	public void verifyGetAssignedTaskInfoByRoleWithAuthorization(String roleNameInput, String dateFormatInput) {
		if (RoleFolderAPITestCases.roles != null) {
			response = Responses.getRequestWithAuthorizationPathParameterAndOneQueryParameter(
					LoginEmployeeAPITestCases.authToken, APIEndpoints.getAssignedTaskInfoByRoleEndpoint, roleNameInput,
					"date", dateFormatInput);

			if (response.getBody().asPrettyString().equalsIgnoreCase("[]")) {
				BodyValidation.responseValidation(response, 200);
			} else if (response.getStatusCode() == 400) {
				BodyValidation.response400Validation(response);
			} else if (RoleFolderAPITestCases.roles.contains(roleNameInput) == false) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else {
				BodyValidation.responseValidation(response, 200);

				List<String> employeeNames = response.jsonPath().getList("employeeName");
				log.info("List of employee names from response are: " + employeeNames + "\n");

				assertTrue(RoleFolderAPITestCases.roles.contains(roleNameInput));
			}
		} else {
			log.info("Roles are null");
		}
	}

	@Test(priority = 29, dataProvider = "TestDataForUpdateEmployee", dataProviderClass = DataProvidersForEmployeeFolder.class, enabled = false)
	public void verifyUpdateEmployeeWithAuthorization(int employeeId, String employeeFullName,
			String employeeMobileNumber1, String employeeStatus, String employeeMobileNumber2,
			String employeeBloodGroup, String employeeOfficeLocation, String employeeJoiningDate, int roleId,
			int designationId, int departmentId, String employeeAddress, String employeeDOB,
			String employeeEmailOfficial, String employeePersonalEmail) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

		// Create a Pattern object
		Pattern pattern = Pattern.compile(emailRegex);

		// Create a matcher object
		Matcher matcherForPersonalEmail = pattern.matcher(employeePersonalEmail);
		Matcher matcherForOfficialEmail = pattern.matcher(employeeEmailOfficial);

		if (DesignationFolderAPITestCases.designationIds != null || DepartmentFolderAPITestCases.departmentIds != null
				|| RoleFolderAPITestCases.roleIds != null) {

			String requestPayload = EmployeeFolderPayloads.giveEmployeePayloadForUpdateEmployee(employeeId,
					employeeFullName, employeeMobileNumber1, employeeStatus, employeeMobileNumber2, employeeBloodGroup,
					employeeOfficeLocation, employeeJoiningDate, roleId, designationId, departmentId, employeeAddress,
					employeeDOB, employeeEmailOfficial, employeePersonalEmail);

			response = Responses.putRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
					APIEndpoints.updateEmployeeEndpoint);

			String responseBody = response.getBody().asPrettyString();

			if (employeeFullName.equalsIgnoreCase("")) {
				BodyValidation.response400Validation(response);
			} else if (!employeeIds.contains(employeeId)) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else if (employeeStatus.equalsIgnoreCase("")) {
				BodyValidation.response400Validation(response);
			} else if (employeeOfficeLocation.equalsIgnoreCase("")) {
				BodyValidation.response400Validation(response);
			} else if (employeeJoiningDate.equalsIgnoreCase("")) {
				BodyValidation.response400Validation(response);
			} else if (employeeEmailOfficial.equalsIgnoreCase("") || matcherForOfficialEmail.matches() == false) {
				BodyValidation.response400Validation(response);
			} else if (employeePersonalEmail.equalsIgnoreCase("") || matcherForPersonalEmail.matches() == false) {
				BodyValidation.response400Validation(response);
			} else if (employeeMobileNumber1.equalsIgnoreCase("") || employeeMobileNumber1.length() < 10
					|| employeeMobileNumber1.length() > 10 || employeeMobileNumber2.length() < 10
					|| employeeMobileNumber2.length() > 10) {
				BodyValidation.response400Validation(response);
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

	@Test(priority = 30, dataProvider = "TestDataForUpdatePassword", dataProviderClass = DataProvidersForEmployeeFolder.class)
	public void verifyUpdatePasswordWithAuthorization(String userId, String oldPassword, String newPassword,
			String confirmPassword) {
		String userPassword = LoginEmployeeAPITestCases.password;

		String requestPayload = EmployeeFolderPayloads.giveEmployeePayloadForUpdatePassword(userId, oldPassword,
				newPassword, confirmPassword);
		log.info("User password is: " + userPassword);
		log.info("Old password is: " + oldPassword);
		log.info("New password is: " + newPassword + "\n");

		if (userIds != null) {
			response = Responses.putRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
					APIEndpoints.updatePasswordEndpoint);

			if (userId.equalsIgnoreCase("") || oldPassword.equalsIgnoreCase("") || newPassword.equalsIgnoreCase("")
					|| confirmPassword.equalsIgnoreCase("")) {
				BodyValidation.response400Validation(response);
			} else if (confirmPassword.equalsIgnoreCase(newPassword) == false
					|| userPassword.equalsIgnoreCase(oldPassword) == false) {
				BodyValidation.responseValidation(response, "Unauthorized", 401);
			} else if (userIds.contains(userId) == false) {
				BodyValidation.responseValidation(response, "Not Found", 404);
			} else {
				BodyValidation.responseValidation(response, "OK", 200);

				userPassword = newPassword;
				log.info("Updated user password is: " + userPassword + "\n");
			}
		} else {
			log.info("User Ids are null\n");
		}
	}

	@Test(priority = 31, dataProvider = "TestDataForGetEncryptedEmail", dataProviderClass = DataProvidersForEmployeeFolder.class)
	public void verifyGetEncryptedEmailWithAuthorization(String userIdInput) {
		log.info("User Id for get encrypted email using data provider is: " + userIdInput + "\n");

		response = Responses.getRequestWithAuthorizationAndOneQueryParameter(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getEncryptedEmailEndpoint, "userId", userIdInput);

		if (userIds.contains(userIdInput) == false) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			BodyValidation.responseValidation(response, 200);
		}
	}

	@Test(priority = 32, dataProvider = "TestDataForSearchEmployeeInLevel", dataProviderClass = DataProvidersForEmployeeFolder.class)
	public void verifyGetSearchEmployeeInLevelWithAuthorization(String keyInput, String roleInput, String dateInput) {
		String requestPayload = EmployeeFolderPayloads.giveEmployeePayloadForSearchEmployeeInLevel(keyInput, roleInput,
				dateInput);

		response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getSearchEmployeeInLevelEndpoint);

		if (dateInput.equalsIgnoreCase("")) {
			BodyValidation.response400Validation(response);
		} else if (response.getStatusCode() == 400 && dateInput.equalsIgnoreCase("") == false) {
			BodyValidation.response400Validation(response);
		} else if (!RoleFolderAPITestCases.roles.contains(roleInput) || roleInput.equalsIgnoreCase("")) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			BodyValidation.responseValidation(response, 200);
		}
	}

	@Test(priority = 33, dataProvider = "TestDataForAddForgotPassword", dataProviderClass = DataProvidersForEmployeeFolder.class, enabled = false)
	public void verifyAddForgotPasswordWithAuthorization(String userIdInput) {
		response = Responses.postRequestWithAuthorizationAndOneQueryParameter(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.addForgotPasswordEndpoint, "userId", userIdInput);

		if (userIds.contains(userIdInput) == false) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			BodyValidation.responseValidation(response, 200);
		}
	}

	@Test(priority = 34, dataProvider = "TestDataForAddToken", dataProviderClass = DataProvidersForEmployeeFolder.class)
	public void verifyAddTokenForWebWithAuthorization(String tokenInput, String userIdInput) {
		String requestPayload = EmployeeFolderPayloads.giveEmployeePayloadForAddToken(tokenInput, userIdInput);

		response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.addTokenForWebEndpoint);

		String responseBody = response.getBody().asPrettyString();
		System.out.println(responseBody);

		if (tokenInput.equalsIgnoreCase("")) {
			BodyValidation.response400Validation(response);
		} else if (userIdInput.equalsIgnoreCase("") || !userIds.contains(userIdInput)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			assertEquals(responseBody, "token updated");
			int contentLength = responseBody.length();
			BodyValidation.responseValidation(response, 200, String.valueOf(contentLength));
		}
	}

	@Test(priority = 35, dataProvider = "TestDataForAddToken", dataProviderClass = DataProvidersForEmployeeFolder.class)
	public void verifyAddTokenForMobileWithAuthorization(String tokenInput, String userIdInput) {
		String requestPayload = EmployeeFolderPayloads.giveEmployeePayloadForAddToken(tokenInput, userIdInput);

		response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.addTokenForMobileEndpoint);

		String responseBody = response.getBody().asPrettyString();
		System.out.println(responseBody);

		if (tokenInput.equalsIgnoreCase("")) {
			BodyValidation.response400Validation(response);
		} else if (userIds.contains(userIdInput) == false || userIdInput.equalsIgnoreCase("")) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			assertEquals(responseBody, "token updated");
			int contentLength = responseBody.length();
			BodyValidation.responseValidation(response, 200, String.valueOf(contentLength));
		}
	}
}

package in.biencaps.erp.api.testcases;

import static org.testng.Assert.*;

import java.util.*;

import org.apache.logging.log4j.*;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.github.javafaker.Faker;

import in.biencaps.erp.api.bodyValidations.*;
import in.biencaps.erp.api.endpoints.*;
import in.biencaps.erp.api.payloads.*;
import in.biencaps.erp.api.responses.*;
import in.biencaps.erp.api.utilities.*;
import io.restassured.response.*;

public class DepartmentFolderAPITestCases extends BaseTest {
	public static final Logger log = LogManager.getLogger(DepartmentFolderAPITestCases.class);
	public static int newCreatedDepartmentId;
	public static String newCreatedDepartment;
	public static int newCreatedDepartmentLevel;
	public static String newCreatedDepartmentColor;
	public static String newCreatedDepartmentColorCode;

	public static List<String> departments;
	public static List<Integer> departmentIds;
	public static List<Integer> departmentLevels;
	public static List<String> departmentColors;
	public static List<String> departmentColorCodes;

	private Response response;
	private Faker faker = new Faker();
	private Random random = new Random();

	@Test(priority = 1)
	public void verify_Add_Department_Without_Authorization() {
		test = BaseTest.extent.createTest("Add department without authorization");

		String requestPayload = DepartmentFolderPayloads.addDepartmentPayload("Testing", 10, "Red", "#FF0000");

		Response response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.addDepartmentEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("add department", APIEndpoints.addDepartmentEndpoint, response);
	}

	@Test(priority = 2)
	public void verify_Get_User_Id_Without_Authorization() {
		test = BaseTest.extent.createTest("Get user Id without authorization");

		Response response = Responses.getRequestWithoutAuthorization(APIEndpoints.getUserIdEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("get user Id", APIEndpoints.getUserIdEndpoint, response);
	}

	@Test(priority = 3)
	public void verify_Get_All_Departments_Without_Authorization() {
		test = BaseTest.extent.createTest("Get all departments without authorization");

		Response response = Responses.getRequestWithoutAuthorization(APIEndpoints.getAllDepartmentsEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("get all departments", APIEndpoints.getAllDepartmentsEndpoint, response);
	}

	@Test(priority = 4)
	public void verify_Get_Employees_By_Department_Name_Without_Authorization() {
		test = BaseTest.extent.createTest("Get all employees by department name without authorization");

		Response response = Responses.getRequestWithoutAuthorizationAndPathParameter(
				APIEndpoints.getAllEmployeesByDepartmentNameEndpoint, "Testing");

		BodyValidation.response401Validation(response);
		test.log(Status.INFO, "API endpoint for get all employees by department name is => "
				+ APIEndpoints.getAllEmployeesByDepartmentNameEndpoint);
		test.log(Status.INFO, "Status code for get all employees by department name is => " + response.getStatusCode());
		test.log(Status.INFO,
				"Response for get all employees by department name is => " + response.getBody().asPrettyString());
	}

	@Test(priority = 5)
	public void verify_Get_All_Employees_By_Designation_Name_Without_Authorization() {
		test = BaseTest.extent.createTest("Get all employees by designation name without authorization");

		Response response = Responses.getRequestWithoutAuthorizationAndOneQueryParameter(
				APIEndpoints.getAllEmployeesByDesignationNameEndpoint, "designationName", "QA");

		BodyValidation.response401Validation(response);
		test.log(Status.INFO, "API endpoint for get all employees by designation name is => "
				+ APIEndpoints.getAllEmployeesByDesignationNameEndpoint);
		test.log(Status.INFO,
				"Status code for get all employees by designation name is => " + response.getStatusCode());
		test.log(Status.INFO,
				"Response for get all employees by designation name is => " + response.getBody().asPrettyString());
	}

	@Test(priority = 6)
	public void verify_Get_All_Assignee_Without_Authorization() {
		test = BaseTest.extent.createTest("Get all assignees without authorization");

		String requestPayload = DepartmentFolderPayloads.giveDepartmentPayloadForGetAssignee(3);

		Response response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.getAllAssigneesEndpoint);

		BodyValidation.response401Validation(response);
		test.log(Status.INFO, "API endpoint for get all assignees is => " + APIEndpoints.getAllAssigneesEndpoint);
		test.log(Status.INFO, "Status code for get all assignees is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for get all assignees is => " + response.getBody().asPrettyString());
	}

	@Test(priority = 7)
	public void verify_Get_Higher_Authority_Without_Authorization() {
		test = BaseTest.extent.createTest("Get higher authority without authorization");

		Response response = Responses.getRequestWithoutAuthorizationAndTwoQueryParameter(
				APIEndpoints.getAllHigherAuthoritiesEndpoint, "role", 1, "department", "Testing");

		BodyValidation.response401Validation(response);
		test.log(Status.INFO,
				"API endpoint for get higher authority is => " + APIEndpoints.getAllHigherAuthoritiesEndpoint);
		test.log(Status.INFO, "Status code for get higher authority is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for get higher authority is => " + response.getBody().asPrettyString());
	}

	@Test(priority = 8)
	public void verify_Update_Department_Without_Authorization() throws Throwable {
		test = BaseTest.extent.createTest("Update department without authorization");

		Response getDepartmentResposne = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllDepartmentsEndpoint);

		String requestPayload = DepartmentFolderPayloads.updateDepartmentWithMaxIdPayload(
				getDepartmentResposne.getBody().asPrettyString(), 4, "Testing", 10, "Red", "#FF0000");

		Response response = Responses.putRequestWithoutAuthorization(requestPayload,
				APIEndpoints.updateDepartmentEndpoint);

		BodyValidation.response401Validation(response);
		test.log(Status.INFO, "API endpoint for update department is => " + APIEndpoints.updateDepartmentEndpoint);
		test.log(Status.INFO, "Status code for update department is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for update department is => " + response.getBody().asPrettyString());
	}

	@Test(priority = 9)
	public void verify_Delete_Department_Without_Authorization() {
		test = BaseTest.extent.createTest("Delete department without authorization");

		Response response = Responses.deleteRequestWithoutAuthorizationAndQueryParameter("id", "8",
				APIEndpoints.deleteDepartmentEndpoint);

		BodyValidation.response401Validation(response);
		test.log(Status.INFO, "API endpoint for delete department is => " + APIEndpoints.deleteDepartmentEndpoint);
		test.log(Status.INFO, "Status code for delete department is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for delete department is => " + response.getBody().asPrettyString());
	}

	@Test(priority = 10)
	public void verify_Get_All_Departments_With_Authorization() {
		verify_Get_All_Departments_API_With_Authorization("before add new department");
	}

	@Test(priority = 11, dataProvider = "TestDataForAddDepartment", dataProviderClass = DataProvidersForDepartmentFolder.class, enabled = false)
	public void verify_Add_Department_With_Authorization(String departmentName, int departmentLevel,
			String departmentColor, String departmentColorCode) {
		test = BaseTest.extent.createTest("Add department with valid and invalid data and with authorization");

		String requestPayload = DepartmentFolderPayloads.addDepartmentPayload(departmentName, departmentLevel,
				departmentColor, departmentColorCode);

		Response response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.addDepartmentEndpoint);
		test.log(Status.INFO, "API endpoint for add department is => " + APIEndpoints.addDepartmentEndpoint);
		test.log(Status.INFO, "Request payload for add department is => " + requestPayload);
		test.log(Status.INFO, "Status code for add department is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for add department is => " + response.getBody().asPrettyString());

		if (departmentName.isBlank() || departmentColor.isBlank() || departmentColorCode.isBlank()) {
			BodyValidation.response400Validation(response);
		} else if (departments.contains(departmentName) || departmentLevels.contains(departmentLevel)
				|| departmentColors.contains(departmentColor) || departmentColorCodes.contains(departmentColorCode)) {
			BodyValidation.responseValidation(response, "Conflict", 409);
		} else {
			BodyValidation.responseValidation(response, 200);

			verify_Get_All_Departments_API_With_Authorization("after added new department");

			verify_New_Created_Department_Details("after added new department");

			assertEquals(newCreatedDepartment, departmentName);
			assertEquals(newCreatedDepartmentLevel, departmentLevel);
			assertEquals(newCreatedDepartmentColor, departmentColor);
			assertEquals(newCreatedDepartmentColorCode, departmentColorCode);
		}
	}

	@Test(priority = 12, dataProvider = "TestDataForUpdateDepartment", dataProviderClass = DataProvidersForDepartmentFolder.class, enabled = false)
	public void verifyUpdateDepartmentWithAuthorization(int departmentId, String departmentName, int departmentLevel,
			String departmentColor, String departmentColorCode) throws Throwable {
		test = BaseTest.extent.createTest("Update department with valid and invalid data and with authorization");

		Response getDepartmentResponse = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllDepartmentsEndpoint);

		String requestPayload = DepartmentFolderPayloads.updateDepartmentWithMaxIdPayload(
				getDepartmentResponse.getBody().asPrettyString(), departmentId, departmentName, departmentLevel,
				departmentColor, departmentColorCode);

		Response response = Responses.putRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.updateDepartmentEndpoint);

		String responseBody = response.getBody().asPrettyString();
		test.log(Status.INFO, "API endpoint for update department is => " + APIEndpoints.updateDepartmentEndpoint);
		test.log(Status.INFO, "Request payload for update department is => " + requestPayload);
		test.log(Status.INFO, "Status code for update department is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for update department is => " + response.getBody().asPrettyString());

		if (departmentName.isBlank() || departmentColor.isBlank() || departmentColorCode.isBlank()) {
			BodyValidation.response400Validation(response);
		} else if (!departmentIds.contains(departmentId)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else if (departments.contains(departmentName) || departmentLevels.contains(departmentLevel)
				|| departmentColors.contains(departmentColor) || departmentColorCodes.contains(departmentColorCode)) {
			BodyValidation.responseValidation(response, "Conflict", 409);
		} else {
			int contentLength = responseBody.length();
			BodyValidation.responseValidation(response, 200, String.valueOf(contentLength));
			assertEquals(responseBody, "Updated Successfully");

			verify_Get_All_Departments_API_With_Authorization("after updated new department");

			verify_New_Created_Department_Details("after updated new department");

			assertEquals(newCreatedDepartment, departmentName);
			assertEquals(newCreatedDepartmentLevel, departmentLevel);
			assertEquals(newCreatedDepartmentColor, departmentColor);
			assertEquals(newCreatedDepartmentColorCode, departmentColorCode);
		}
	}

	@Test(priority = 13, dataProvider = "TestDataForDeleteDepartment", dataProviderClass = DataProvidersForDepartmentFolder.class, enabled = false)
	public void verifyDeleteSingleDepartmentWithAuthorization(int departmentId) {
		test = BaseTest.extent.createTest("Delete department with valid and invalid data and with authorization");

		Response response = Responses.deleteRequestWithAuthorizationAndQueryParameter("id", departmentId,
				LoginEmployeeAPITestCases.authToken, APIEndpoints.deleteDepartmentEndpoint);

		String responseBody = response.getBody().asPrettyString();
		test.log(Status.INFO, "API endpoint for delete department is => " + APIEndpoints.deleteDepartmentEndpoint);
		test.log(Status.INFO, "Request payload for delete department is => " + departmentId);
		test.log(Status.INFO, "Status code for delete department is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for delete department is => " + response.getBody().asPrettyString());

		if (responseBody.equals("[]")) {
			BodyValidation.response204Validation(response);
		} else if (response.getStatusCode() == 403) {
			BodyValidation.responseValidation(response, "Forbidden", 403);
		} else if (!departmentIds.contains(departmentId)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			int contentLength = responseBody.length();
			BodyValidation.responseValidation(response, 200, String.valueOf(contentLength));
			assertEquals(responseBody, "Department Deleted Successfully");

			verify_Get_All_Departments_API_With_Authorization("after deleted new department");
		}
	}

	@Test(priority = 14)
	public void verify_Get_User_Id_With_Authorization() {
		test = BaseTest.extent.createTest("Get user id with authorization");

		// Calling get all employees API
		Response getEmployeesResponse = Responses.getRequestWithAuthorizationAndThreeQueryParameter(
				LoginEmployeeAPITestCases.authToken, APIEndpoints.getAllEmployeesEndpoint, "page", 0, "size", 20, "key",
				"");

		String maxValueOfUserId = getEmployeesResponse.jsonPath().getString("data.max { it.userId }.userId");
		log.info("Max value of user Id is => " + maxValueOfUserId);

		String numericPart = maxValueOfUserId.substring(3);

		int numeric_part = Integer.parseInt(numericPart);

		int new_numeric_part = numeric_part + 1;
		String newNumericPart = String.valueOf(new_numeric_part);

		String newUserId = "";

		if (Constants.environment.equalsIgnoreCase("test")) {
			newUserId = "INC0" + newNumericPart;
			log.info("New User Id for get user id is => " + newUserId + "\n");
		} else {
			newUserId = "BIE0" + newNumericPart;
			log.info("New User Id for get user id is => " + newUserId + "\n");
		}

		// Calling get user Id API
		Response response = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getUserIdEndpoint);

		int responseBodyLength = response.getBody().asPrettyString().length();
		String contentLength = String.valueOf(responseBodyLength);

		assertEquals(response.getBody().asPrettyString(), newUserId, "Invalid response text");

		BodyValidation.responseValidation(response, 200, contentLength);
		test.log(Status.INFO, "API endpoint for get user Id is => " + APIEndpoints.getUserIdEndpoint);
		test.log(Status.INFO, "Status code for get user Id is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for get user Id is => " + response.getBody().asPrettyString());
	}

	@Test(priority = 15)
	public void verify_Get_Employees_By_Department_Name_With_Authorization() {
		if (departments != null) {
			int randomIndexForDepartment = random.nextInt(departments.size());
			String newCreatedDepartment = departments.get(randomIndexForDepartment);
			log.info(
					"Random department name for get all employees by department is  => " + newCreatedDepartment + "\n");

			Response response = Responses.getRequestWithAuthorizationAndPathParameter(
					LoginEmployeeAPITestCases.authToken, APIEndpoints.getAllEmployeesByDepartmentNameEndpoint,
					newCreatedDepartment);
			test.log(Status.INFO, "API endpoint for get all employees by department name is => "
					+ APIEndpoints.getAllEmployeesByDepartmentNameEndpoint);
			test.log(Status.INFO,
					"Path parameter for get all employees by department name is => " + newCreatedDepartment);
			test.log(Status.INFO,
					"Status code for get all employees by department name is => " + response.getStatusCode());
			test.log(Status.INFO,
					"Response for get all employees by department name is => " + response.getBody().asPrettyString());

			assertTrue(departments.contains(newCreatedDepartment));

			if (response.getBody().asPrettyString().isBlank()) {
				BodyValidation.response204Validation(response);
			} else {
				BodyValidation.responseValidation(response, 200);
			}
		} else {
			log.info("Departments are null");
		}
	}

	@Test(priority = 16)
	public void verify_Get_Employees_By_Department_By_Giving_Invalid_Department() {
		String fakeDepartmentName = DataGeneratorForAPI.generateFakeDepartment();

		if (departments != null) {
			Response response = Responses.getRequestWithAuthorizationAndPathParameter(
					LoginEmployeeAPITestCases.authToken, APIEndpoints.getAllEmployeesByDepartmentNameEndpoint,
					fakeDepartmentName);
			test.log(Status.INFO, "API endpoint for get all employees by department name is => "
					+ APIEndpoints.getAllEmployeesByDepartmentNameEndpoint);
			test.log(Status.INFO,
					"Path parameter for get all employees by department name is => " + newCreatedDepartment);
			test.log(Status.INFO,
					"Status code for get all employees by department name is => " + response.getStatusCode());
			test.log(Status.INFO,
					"Response for get all employees by department name is => " + response.getBody().asPrettyString());

			assertFalse(departments.contains(fakeDepartmentName));

			BodyValidation.response204Validation(response);
		} else {
			log.info("Departments are null");
		}
	}

	@Test(priority = 17)
	public void verify_Get_All_Employees_By_Designation_With_Authorization() {
		if (DesignationFolderAPITestCases.designations != null) {
			int randomIndexForDesignationName = random.nextInt(DesignationFolderAPITestCases.designations.size());
			String randomDesignationName = DesignationFolderAPITestCases.designations
					.get(randomIndexForDesignationName);
			log.info("Random designation name for get all employees by designation is => " + randomDesignationName
					+ "\n");

			Response response = Responses.getRequestWithAuthorizationAndOneQueryParameter(
					LoginEmployeeAPITestCases.authToken, APIEndpoints.getAllEmployeesByDesignationNameEndpoint,
					"designationName", randomDesignationName);
			test.log(Status.INFO, "API endpoint for get all employees by designation name is => "
					+ APIEndpoints.getAllEmployeesByDesignationNameEndpoint);
			test.log(Status.INFO,
					"Query parameter for get all employees by department name is => " + randomDesignationName);
			test.log(Status.INFO,
					"Status code for get all employees by designation name is => " + response.getStatusCode());
			test.log(Status.INFO,
					"Response for get all employees by designation name is => " + response.getBody().asPrettyString());

			if (response.getBody().asPrettyString().isBlank()) {
				BodyValidation.response204Validation(response);
			} else {
				BodyValidation.responseValidation(response, 200);
			}
		} else {
			log.info("Designations are null");
		}
	}

	@Test(priority = 18)
	public void verify_Get_All_Employees_By_Designation_By_Giving_Invalid_Designation() {
		String fakeDesignation = DataGeneratorForAPI.generateFakeDesignation();

		if (DesignationFolderAPITestCases.designations != null) {
			Response response = Responses.getRequestWithAuthorizationAndOneQueryParameter(
					LoginEmployeeAPITestCases.authToken, APIEndpoints.getAllEmployeesByDesignationNameEndpoint,
					"designationName", fakeDesignation);
			test.log(Status.INFO, "API endpoint for get all employees by designation name is => "
					+ APIEndpoints.getAllEmployeesByDesignationNameEndpoint);
			test.log(Status.INFO, "Query parameter for get all employees by department name is => " + fakeDesignation);
			test.log(Status.INFO,
					"Status code for get all employees by designation name is => " + response.getStatusCode());
			test.log(Status.INFO,
					"Response for get all employees by designation name is => " + response.getBody().asPrettyString());

			assertFalse(DesignationFolderAPITestCases.designations.contains(fakeDesignation));

			BodyValidation.response204Validation(response);
		} else {
			log.info("Designations are null");
		}
	}

	@Test(priority = 19)
	public void verify_Get_All_Assignee_With_Authorization_By_Giving_Valid_Role_Level() {
		if (RoleFolderAPITestCases.roleIds != null) {
			int randomIndexForRoleId = random.nextInt(RoleFolderAPITestCases.roleIds.size());
			int randomRoleId = RoleFolderAPITestCases.roleIds.get(randomIndexForRoleId);
			log.info("Random role Id for get all assignee is => " + randomRoleId + "\n");

			String requestPayload = DepartmentFolderPayloads.giveDepartmentPayloadForGetAssignee(randomRoleId);

			Response response = Responses.postRequestWithAuthorization(requestPayload,
					LoginEmployeeAPITestCases.authToken, APIEndpoints.getAllAssigneesEndpoint);
			test.log(Status.INFO, "API endpoint for get all assignees is => " + APIEndpoints.getAllAssigneesEndpoint);
			test.log(Status.INFO, "Status code for get all assignees is => " + response.getStatusCode());
			test.log(Status.INFO, "Response for get all assignees is => " + response.getBody().asPrettyString());

			BodyValidation.responseValidation(response, 200);
		} else {
			log.info("Role Ids are null");
		}
	}

	@Test(priority = 20)
	public void verify_Get_Higher_Authority_With_Authorization() {
		if (RoleFolderAPITestCases.roleLevels != null && departments != null) {
			int randomIndexForRoleLevel = random.nextInt(RoleFolderAPITestCases.roleLevels.size());
			int randomRoleLevel = RoleFolderAPITestCases.roleLevels.get(randomIndexForRoleLevel);
			log.info("Random role level for get all higher authority is => " + randomRoleLevel);

			int randomIndexForDepartmentName = random.nextInt(departments.size());
			String newCreatedDepartmentName = departments.get(randomIndexForDepartmentName);
			log.info("New created department name for get all higher authority is => " + newCreatedDepartmentName
					+ "\n");

			Response response = Responses.getRequestWithAuthorizationAndTwoQueryParameter(
					LoginEmployeeAPITestCases.authToken, APIEndpoints.getAllHigherAuthoritiesEndpoint, "role",
					randomRoleLevel, "department", newCreatedDepartmentName);
			test.log(Status.INFO,
					"API endpoint for get higher authority is => " + APIEndpoints.getAllHigherAuthoritiesEndpoint);
			test.log(Status.INFO, "Status code for get higher authority is => " + response.getStatusCode());
			test.log(Status.INFO, "Response for get higher authority is => " + response.getBody().asPrettyString());

			BodyValidation.responseValidation(response, 200);
		} else {
			log.info("Role Levels are null");
			log.info("Departments are null");
		}
	}

	@Test(priority = 21)
	public void verify_Get_Higher_Authority_By_Giving_Invalid_Department() {
		if (departments != null && RoleFolderAPITestCases.roleLevels != null) {
			int randomIndexForRoleLevel = random.nextInt(RoleFolderAPITestCases.roleLevels.size());
			int randomRoleLevel = RoleFolderAPITestCases.roleLevels.get(randomIndexForRoleLevel);
			log.info("Random role level for get all higher authority is => " + randomRoleLevel + "\n");

			String fakeDepartment = faker.country().name();

			Response response = Responses.getRequestWithAuthorizationAndTwoQueryParameter(
					LoginEmployeeAPITestCases.authToken, APIEndpoints.getAllHigherAuthoritiesEndpoint, "role",
					randomRoleLevel, "department", fakeDepartment);
			test.log(Status.INFO,
					"API endpoint for get higher authority is => " + APIEndpoints.getAllHigherAuthoritiesEndpoint);
			test.log(Status.INFO, "Status code for get higher authority is => " + response.getStatusCode());
			test.log(Status.INFO, "Response for get higher authority is => " + response.getBody().asPrettyString());

			assertFalse(departments.contains(fakeDepartment));

			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			log.info("Role levels are null");
			log.info("Departments are null");
		}
	}

	public void verify_New_Created_Department_Details(String message) {
		newCreatedDepartmentId = response.jsonPath().getInt("max { it.departmentId }.departmentId");
		log.info("New created department Id " + message + " is => " + newCreatedDepartmentId);

		newCreatedDepartment = response.jsonPath()
				.getString("find { it.departmentId == " + newCreatedDepartmentId + " }.departmentName");
		log.info("New created department " + message + " is => " + newCreatedDepartment);

		newCreatedDepartmentLevel = response.jsonPath()
				.getInt("find { it.departmentId == " + newCreatedDepartmentId + " }.departmentLevel");
		log.info("New created department level " + message + " is => " + newCreatedDepartmentLevel);

		newCreatedDepartmentColor = response.jsonPath()
				.getString("find { it.departmentId == " + newCreatedDepartmentId + " }.departmentColor");
		log.info("New created department color " + message + " is => " + newCreatedDepartmentColor);

		newCreatedDepartmentColorCode = response.jsonPath()
				.getString("find { it.departmentId == " + newCreatedDepartmentId + " }.departmentColorCode");
		log.info("New created department color code " + message + " is => " + newCreatedDepartmentColorCode + "\n");
	}

	public void verify_Get_All_Departments_API_With_Authorization(String message) {
		test = BaseTest.extent.createTest("Get all departments with authorization");

		response = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllDepartmentsEndpoint);

		BodyValidation.responseValidation(response, 200);

		BaseTest.test_Method_Logs("get all departments", APIEndpoints.getAllDepartmentsEndpoint, response);

		departmentIds = response.jsonPath().getList("departmentId");
		log.info("List of department Ids " + message + " are =>" + departmentIds);

		departments = response.jsonPath().getList("departmentName");
		log.info("List of department names " + message + " are =>" + departments);

		departmentLevels = response.jsonPath().getList("departmentLevel");
		log.info("List of department levels " + message + " are =>" + departmentLevels);

		departmentColors = response.jsonPath().getList("departmentColor");
		log.info("List of department colors " + message + " are =>" + departmentColors);

		departmentColorCodes = response.jsonPath().getList("departmentColorCode");
		log.info("List of department color codes " + message + " are =>" + departmentColorCodes + "\n");
	}
}

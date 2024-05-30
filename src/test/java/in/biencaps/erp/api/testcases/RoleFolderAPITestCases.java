package in.biencaps.erp.api.testcases;

import java.util.*;

import org.apache.logging.log4j.*;
import org.testng.annotations.Test;

import in.biencaps.erp.api.bodyValidations.*;
import in.biencaps.erp.api.endpoints.*;
import in.biencaps.erp.api.payloads.*;
import in.biencaps.erp.api.responses.*;
import in.biencaps.erp.api.utilities.*;
import io.restassured.response.*;

import static org.testng.Assert.*;
import static org.hamcrest.Matchers.*;

public class RoleFolderAPITestCases extends BaseTest {
	public static final Logger log = LogManager.getLogger(RoleFolderAPITestCases.class);
	public static int newCreatedRoleId;
	private int newCreatedRoleLevel;
	public String newCreatedRole;
	private String newCreatedRoleName;

	public static List<Integer> roleIds;
	public static List<String> roles;
	public static List<Integer> roleLevels;

	public Response response;

	@Test(priority = 1)
	public void verify_Add_Role_Without_Authorization() {
		test = BaseTest.extent.createTest("Add role without authorization");

		String requestPayload = RoleFolderPayloads.addRolePayload("Role", 10);

		Response response = Responses.postRequestWithoutAuthorization(requestPayload, APIEndpoints.addRoleEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("add role", APIEndpoints.addRoleEndpoint, response);
	}

	@Test(priority = 2)
	public void verify_Get_All_Roles_Without_Authorization() {
		test = BaseTest.extent.createTest("Get all roles without authorization");

		Response response = Responses.getRequestWithoutAuthorization(APIEndpoints.getAllRolesEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("get all roles", APIEndpoints.getAllRolesEndpoint, response);
	}

	@Test(priority = 3)
	public void verify_Update_Role_Without_Authorization() throws Throwable {
		test = BaseTest.extent.createTest("Update role without authorization");

		String requestPayload = RoleFolderPayloads.updateRoleWithMaxIdPayload(24, "Role", 5);

		Response response = Responses.putRequestWithoutAuthorization(requestPayload, APIEndpoints.updateRoleEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("update role", APIEndpoints.updateRoleEndpoint, response);
	}

	@Test(priority = 4)
	public void verify_Delete_Role_Without_Authorization() {
		test = BaseTest.extent.createTest("Delete role without authorization");

		String requestPayload = RoleFolderPayloads.deleteRolePayload(24);

		Response response = Responses.deleteRequestWithoutAuthorizationAndPayload(requestPayload,
				APIEndpoints.deleteRoleEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("delete role", APIEndpoints.deleteRoleEndpoint, response);
	}

	@Test(priority = 5)
	public void verify_Get_Role_By_Level_Without_Authorization() {
		Response response = Responses
				.getRequestWithoutAuthorizationAndPathParameter(APIEndpoints.getAllRolesByLevelEndpoint, 1);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("get role by level", APIEndpoints.getAllRolesByLevelEndpoint, response);
	}

	@Test(priority = 6)
	public void verify_Get_All_Roles_With_Authorization() {
		verify_Get_All_Roles_API_With_Authorization("before add new role");
	}

	@Test(priority = 7, dataProvider = "TestDataForAddRole", dataProviderClass = DataProvidersForRoleFolder.class, enabled = false)
	public void verify_Add_Role_With_Authorization(String roleNameInput, int roleLevelInput) {
		test = BaseTest.extent.createTest("Add role with valid and invalid data and with authorization");

		String requestPayload = RoleFolderPayloads.addRolePayload(roleNameInput, roleLevelInput);

		Response response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.addRoleEndpoint);

		BaseTest.test_Method_Logs("add role", APIEndpoints.addRoleEndpoint, requestPayload, response);

		if (roleNameInput.equalsIgnoreCase("")) {
			BodyValidation.response400Validation(response);
		} else if (roles.contains(roleNameInput) || roleLevels.contains(roleLevelInput)) {
			BodyValidation.responseValidation(response, "Unprocessable Entity", 422);
		} else {
			BodyValidation.responseValidation(response, 201);

			verify_Get_All_Roles_API_With_Authorization("after added new role");

			verify_New_Created_Role_Details("after added new role");

			assertEquals(newCreatedRole, "ROLE_" + roleNameInput.toUpperCase());

			assertEquals(newCreatedRoleName, roleNameInput);

			assertEquals(newCreatedRoleLevel, roleLevelInput);
		}
	}

	@Test(priority = 8, dataProvider = "TestDataForUpdateRole", dataProviderClass = DataProvidersForRoleFolder.class, enabled = false)
	public void verify_Update_Role_With_Authorization(int roleIdInput, String roleNameInput, int roleLevelInput)
			throws Throwable {
		test = BaseTest.extent.createTest("Update role with valid and invalid data and with authorization");

		String requestPayload = RoleFolderPayloads.updateRoleWithMaxIdPayload(roleIdInput, roleNameInput,
				roleLevelInput);

		Response response = Responses.putRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.updateRoleEndpoint);

		String responseBody = response.getBody().asPrettyString();
		BaseTest.test_Method_Logs("update role", APIEndpoints.updateRoleEndpoint, requestPayload, response);

		if (roleNameInput.isBlank()) {
			BodyValidation.response400Validation(response);
		} else if (roles.contains(roleNameInput) || roleLevels.contains(roleLevelInput)) {
			BodyValidation.responseValidation(response, "Conflict", 409);
		} else if (!roleIds.contains(roleIdInput)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			int contentLength = responseBody.length();
			BodyValidation.responseValidation(response, 200, String.valueOf(contentLength));
			assertEquals(responseBody, "Role update successfully");

			verify_Get_All_Roles_API_With_Authorization("after updated new role");

			verify_New_Created_Role_Details("after updated new role");

			assertEquals(newCreatedRole, "ROLE_" + roleNameInput.toUpperCase());

			assertEquals(newCreatedRoleName, roleNameInput);

			assertEquals(newCreatedRoleLevel, roleLevelInput);
		}
	}

	@Test(priority = 9, dataProvider = "TestDataForDeleteRole", dataProviderClass = DataProvidersForRoleFolder.class, enabled = false)
	public void verify_Delete_Role_With_Authorization(int roleIdInput) {
		test = BaseTest.extent.createTest("Delete role with valid and invalid data and with authorization");

		String requestPayload = RoleFolderPayloads.deleteRolePayload(roleIdInput);

		Response response = Responses.deleteRequestWithAuthorizationAndPayload(requestPayload,
				LoginEmployeeAPITestCases.authToken, APIEndpoints.deleteRoleEndpoint);

		String responseBody = response.getBody().asPrettyString();

		BaseTest.test_Method_Logs("delete role", APIEndpoints.deleteRoleEndpoint, requestPayload, response);

		if (response.getBody().asPrettyString().equals("[]")) {
			BodyValidation.response204Validation(response);
		} else if (response.getStatusCode() == 403) {
			BodyValidation.responseValidation(response, "Forbidden", 403);
		} else if (!roleIds.contains(roleIdInput)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			int contentLength = responseBody.length();
			BodyValidation.responseValidation(response, 200, String.valueOf(contentLength));
			assertEquals(responseBody, "Role Deleted Successfully");

			verify_Get_All_Roles_API_With_Authorization("after deleted new role");
		}
	}

	@Test(priority = 10, dataProvider = "TestDataForGetRoleByLevel", dataProviderClass = DataProvidersForRoleFolder.class)
	public void verify_Get_Role_By_Level_With_Authorization(int roleLevelInput) {
		test = BaseTest.extent.createTest("Get role by level with valid and invalid data and with authorization");

		Response response = Responses.getRequestWithAuthorizationAndPathParameter(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllRolesByLevelEndpoint, roleLevelInput);

		BaseTest.test_Method_Logs_With_Path_Parameter("get role by level", APIEndpoints.getAllRolesByLevelEndpoint,
				roleLevelInput, response);

		if (!roleLevels.contains(roleLevelInput)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			BodyValidation.responseValidation(response, 200);

			response.then().body("roleId", hasItem(greaterThanOrEqualTo(roleLevelInput))).body("roleLevel",
					everyItem(greaterThanOrEqualTo(roleLevelInput)));

			if (roleLevelInput == 1) {
				response.then().body("[0].roleLevel", equalTo(roleLevelInput))
						.body("[1].roleLevel", equalTo(roleLevelInput + 1))
						.body("[2].roleLevel", equalTo(roleLevelInput + 2))
						.body("[3].roleLevel", equalTo(roleLevelInput + 3))
						.body("[4].roleLevel", equalTo(roleLevelInput + 4));
			} else if (roleLevelInput == 2) {
				response.then().body("[0].roleLevel", equalTo(roleLevelInput))
						.body("[1].roleLevel", equalTo(roleLevelInput + 1))
						.body("[2].roleLevel", equalTo(roleLevelInput + 2))
						.body("[3].roleLevel", equalTo(roleLevelInput + 3));
			} else if (roleLevelInput == 3) {
				response.then().body("[0].roleLevel", equalTo(roleLevelInput))
						.body("[1].roleLevel", equalTo(roleLevelInput + 1))
						.body("[2].roleLevel", equalTo(roleLevelInput + 2));
			} else if (roleLevelInput == 4) {
				response.then().body("[0].roleLevel", equalTo(roleLevelInput)).body("[1].roleLevel",
						equalTo(roleLevelInput + 1));
			} else {
				response.then().body("[0].roleLevel", equalTo(roleLevelInput));
			}
		}
	}

	public void verify_New_Created_Role_Details(String message) {
		newCreatedRoleId = response.jsonPath().getInt("max { it.roleId }.roleId");
		log.info("New created role Id " + message + " is: " + newCreatedRoleId);

		newCreatedRole = response.jsonPath().getString("find { it.roleId == " + newCreatedRoleId + " }.role");
		log.info("New created role " + message + " is: " + newCreatedRole);

		newCreatedRoleName = response.jsonPath().getString("find { it.roleId == " + newCreatedRoleId + " }.roleName");
		log.info("New created role name " + message + " is: " + newCreatedRoleName);

		newCreatedRoleLevel = response.jsonPath().getInt("find { it.roleId == " + newCreatedRoleId + " }.roleLevel");
		log.info("New created role level " + message + " is: " + newCreatedRoleLevel + "\n");
	}

	public void verify_Get_All_Roles_API_With_Authorization(String message) {
		test = BaseTest.extent.createTest("Get all roles with authorization");

		response = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllRolesEndpoint);

		BodyValidation.responseValidation(response, 200);

		BaseTest.test_Method_Logs("get all roles", APIEndpoints.getAllRolesEndpoint, response);

		roleIds = response.jsonPath().getList("roleId");
		log.info("List of role Ids " + message + " are: " + roleIds);

		roles = response.jsonPath().getList("roleName");
		log.info("List of roles " + message + " are: " + roles);

		roleLevels = response.jsonPath().getList("roleLevel");
		log.info("List of role Levels " + message + " are: " + roleLevels + "\n");
	}
}

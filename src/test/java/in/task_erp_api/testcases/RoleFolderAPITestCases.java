package in.task_erp_api.testcases;

import java.util.*;

import org.apache.logging.log4j.*;
import org.testng.annotations.Test;

import in.task_erp_api.bodyValidations.*;
import in.task_erp_api.endpoints.*;
import in.task_erp_api.payloads.*;
import in.task_erp_api.responses.*;
import in.task_erp_api.utilities.*;
import io.restassured.response.*;

import static org.testng.Assert.*;
import static org.hamcrest.Matchers.*;

public class RoleFolderAPITestCases {
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
	public void verifyAddRoleWithoutAuthorization() {
		String requestPayload = RoleFolderPayloads.giveRolePayloadForAddRole("Role", 10);

		response = Responses.postRequestWithoutAuthorization(requestPayload, APIEndpoints.addRoleEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 2)
	public void verifyGetAllRoleWithoutAuthorization() {
		response = Responses.getRequestWithoutAuthorization(APIEndpoints.getAllRolesEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 3)
	public void verifyUpdateRoleWithoutAuthorization() {
		String requestPayload = RoleFolderPayloads.giveRolePayloadForUpdateRole(24, "Role", 5);

		response = Responses.putRequestWithoutAuthorization(requestPayload, APIEndpoints.updateRoleEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 4)
	public void deleteSingleRoleWithoutAuthorization() {
		String requestPayload = RoleFolderPayloads.giveRolePayloadForDeleteRole(24);

		response = Responses.deleteRequestWithoutAuthorizationAndPayload(requestPayload,
				APIEndpoints.deleteRoleEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 5)
	public void verifyGetRoleByLevelWithoutAuthorization() {
		response = Responses.getRequestWithoutAuthorizationAndPathParameter(APIEndpoints.getAllRolesByLevelEndpoint, 1);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 6)
	public void verifyGetAllRoleWithAuthorization() {
		response = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllRolesEndpoint);

		BodyValidation.responseValidation(response, 200);

		roleIds = response.jsonPath().getList("roleId");
		log.info("List of role Ids before new role add are: " + roleIds);

		roles = response.jsonPath().getList("roleName");
		log.info("List of roles before new role add are: " + roles);

		roleLevels = response.jsonPath().getList("roleLevel");
		log.info("List of role Levels before new role add are: " + roleLevels + "\n");
	}

	@Test(priority = 7, dataProvider = "TestDataForAddRole", dataProviderClass = DataProvidersForRoleFolder.class, enabled = false)
	public void verifyAddRoleWithAuthorization(String roleNameInput, int roleLevelInput) {
		String requestPayload = RoleFolderPayloads.giveRolePayloadForAddRole(roleNameInput, roleLevelInput);

		response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.addRoleEndpoint);

		System.out.println(response.getBody().asPrettyString());

		if (roleNameInput.equalsIgnoreCase("")) {
			BodyValidation.response400Validation(response);
		} else if (roles.contains(roleNameInput) || roleLevels.contains(roleLevelInput)) {
			BodyValidation.responseValidation(response, "Unprocessable Entity", 422);
		} else {
			BodyValidation.responseValidation(response, 201);

			verifyGetAllRoleAPIWithAuthorization("after added new role");

			newCreatedRoleId = response.jsonPath().getInt("max { it.roleId }.roleId");
			log.info("New created Role Id after added new role is: " + newCreatedRoleId);

			newCreatedRole = response.jsonPath().getString("find { it.roleId == " + newCreatedRoleId + " }.role");
			log.info("New created Role after added new role is: " + newCreatedRole);
			assertEquals(newCreatedRole, "ROLE_" + roleNameInput.toUpperCase());

			newCreatedRoleName = response.jsonPath()
					.getString("find { it.roleId == " + newCreatedRoleId + " }.roleName");
			log.info("New created Role Name after added new role is: " + newCreatedRoleName);
			assertEquals(newCreatedRoleName, roleNameInput);

			newCreatedRoleLevel = response.jsonPath()
					.getInt("find { it.roleId == " + newCreatedRoleId + " }.roleLevel");
			log.info("New created Role Level after added new role is: " + newCreatedRoleLevel + "\n");
			assertEquals(newCreatedRoleLevel, roleLevelInput);
		}
	}

	@Test(priority = 8, dataProvider = "TestDataForUpdateRole", dataProviderClass = DataProvidersForRoleFolder.class, enabled = false)
	public void verifyUpdateRoleWithAuthorization(int roleIdInput, String roleNameInput, int roleLevelInput) {
		String requestPayload = RoleFolderPayloads.giveRolePayloadForUpdateRole(roleIdInput, roleNameInput,
				roleLevelInput);

		response = Responses.putRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.updateRoleEndpoint);

		String responseBody = response.getBody().asPrettyString();
		System.out.println(response.getBody().asPrettyString());

		if (roleNameInput.equalsIgnoreCase("")) {
			BodyValidation.response400Validation(response);
		} else if (roles.contains(roleNameInput) || roleLevels.contains(roleLevelInput)) {
			BodyValidation.responseValidation(response, "Conflict", 409);
		} else if (!roleIds.contains(roleIdInput)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			int contentLength = responseBody.length();
			BodyValidation.responseValidation(response, 200, String.valueOf(contentLength));
			assertEquals(responseBody, "Role update successfully");

			verifyGetAllRoleAPIWithAuthorization("after updated new role");

			newCreatedRoleId = response.jsonPath().getInt("max { it.roleId }.roleId");
			log.info("New created Role Id after updated new role is: " + newCreatedRoleId);

			newCreatedRole = response.jsonPath().getString("find { it.roleId == " + newCreatedRoleId + " }.role");
			log.info("New created Role after updated new role is: " + newCreatedRole);
			assertEquals(newCreatedRole, "ROLE_" + roleNameInput.toUpperCase());

			newCreatedRoleName = response.jsonPath()
					.getString("find { it.roleId == " + newCreatedRoleId + " }.roleName");
			log.info("New created Role Name after updated new role is: " + newCreatedRoleName);
			assertEquals(newCreatedRoleName, roleNameInput);

			newCreatedRoleLevel = response.jsonPath()
					.getInt("find { it.roleId == " + newCreatedRoleId + " }.roleLevel");
			log.info("New created Role Level after updated new role is: " + newCreatedRoleLevel + "\n");
			assertEquals(newCreatedRoleLevel, roleLevelInput);
		}
	}

	@Test(priority = 9, dataProvider = "TestDataForDeleteRole", dataProviderClass = DataProvidersForRoleFolder.class, enabled = false)
	public void verifyDeleteSingleRoleWithAuthorization(int roleIdInput) {
		if (roleIds != null) {
			String requestPayload = RoleFolderPayloads.giveRolePayloadForDeleteRole(roleIdInput);

			response = Responses.deleteRequestWithAuthorizationAndPayload(requestPayload,
					LoginEmployeeAPITestCases.authToken, APIEndpoints.deleteRoleEndpoint);

			String responseBody = response.getBody().asPrettyString();

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

				verifyGetAllRoleAPIWithAuthorization("after deleted new role");
			}
		} else {
			log.info("Role Ids are null");
		}
	}

	@Test(priority = 10, dataProvider = "TestDataForGetRoleByLevel", dataProviderClass = DataProvidersForRoleFolder.class)
	public void verifyGetRoleByLevelWithAuthorization(int roleLevelInput) {
		if (roleLevels != null) {
			response = Responses.getRequestWithAuthorizationAndPathParameter(LoginEmployeeAPITestCases.authToken,
					APIEndpoints.getAllRolesByLevelEndpoint, roleLevelInput);

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
		} else {
			log.info("Role levels are null");
		}
	}

	public void verifyGetAllRoleAPIWithAuthorization(String message) {
		response = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllRolesEndpoint);

		BodyValidation.responseValidation(response, 200);

		roleIds = response.jsonPath().getList("roleId");
		log.info("List of role Ids " + message + " are: " + roleIds);

		roles = response.jsonPath().getList("roleName");
		log.info("List of roles " + message + " are: " + roles);

		roleLevels = response.jsonPath().getList("roleLevel");
		log.info("List of role Levels " + message + " are: " + roleLevels + "\n");
	}
}

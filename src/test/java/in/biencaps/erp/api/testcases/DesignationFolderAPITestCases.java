package in.biencaps.erp.api.testcases;

import java.util.*;

import org.apache.logging.log4j.*;
import static org.testng.Assert.*;
import org.testng.annotations.*;

import in.biencaps.erp.api.bodyValidations.*;
import in.biencaps.erp.api.endpoints.*;
import in.biencaps.erp.api.payloads.*;
import in.biencaps.erp.api.responses.*;
import in.biencaps.erp.api.utilities.*;
import io.restassured.response.Response;

public class DesignationFolderAPITestCases extends BaseTest {
	public static final Logger log = LogManager.getLogger(DesignationFolderAPITestCases.class);
	public static int newCreatedDesignationId;
	public static String newCreatedDesignation;

	public static List<String> designations;
	public static List<Integer> designationIds;

	private Response response;

	@Test(priority = 1)
	public void verify_Add_Designation_Without_Authorization() {
		test = BaseTest.extent.createTest("Add designation without authorization");

		String requestPayload = DesignationFolderPayloads.addDesignationPayload("Designation", 3);

		Response response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.addDesignationEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("add designation", APIEndpoints.addDesignationEndpoint, response);
	}

	@Test(priority = 2)
	public void verify_Get_All_Designation_By_Department_Without_Authorization() {
		test = BaseTest.extent.createTest("Get all designations by department without authorization");

		String requestPayload = DesignationFolderPayloads.getAllDesignationsByDepartmentPayload(10);

		Response response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.getAllEmployeesByDesignationNameEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("get all designations by department",
				APIEndpoints.getAllEmployeesByDesignationNameEndpoint, response);
	}

	@Test(priority = 3)
	public void verify_Get_All_Designation_Without_Authorization() {
		test = BaseTest.extent.createTest("Get all designations without authorization");

		Response response = Responses.getRequestWithoutAuthorization(APIEndpoints.getAllDesignationsEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("get all designations", APIEndpoints.getAllDesignationsEndpoint, response);
	}

	@Test(priority = 4)
	public void verify_Update_Designation_Without_Authorization() throws Throwable {
		test = BaseTest.extent.createTest("Update designation without authorization");

		Response getDesignationResponse = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllDesignationsEndpoint);

		String requestPayload = DesignationFolderPayloads.updateDesignationWithMaxIdPayload(
				getDesignationResponse.getBody().asPrettyString(), 10, "Designation", 3, "QA", 2, "Blue", "#0000FF");

		Response response = Responses.putRequestWithoutAuthorization(requestPayload,
				APIEndpoints.updateDesignationEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("update designation", APIEndpoints.updateDesignationEndpoint, response);
	}

	@Test(priority = 5)
	public void verify_Delete_Designation_Without_Authorization() {
		test = BaseTest.extent.createTest("Delete designation without authorization");

		Response response = Responses.deleteRequestWithoutAuthorizationAndQueryParameter("designation", "Designation",
				APIEndpoints.deleteDesignationEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("delete designation", APIEndpoints.deleteDesignationEndpoint, response);
	}

	@Test(priority = 6)
	public void verify_Get_All_Designation_With_Authorization() {
		verify_Get_All_Designation_API_With_Authorization("before add new designation");
	}

	@Test(priority = 7)
	public void verify_Add_Designation_With_Employee_Authorization() {
		test = BaseTest.extent.createTest("Add designation with employee authorization");

		String authToken = LoginEmployeeAPITestCases
				.verify_Login_Employee_By_Giving_Valid_Data(Constants.employeeUserId, Constants.employeePassword);

		String requestPayload = DesignationFolderPayloads.addDesignationPayload("Designation", 3);

		Response response = Responses.postRequestWithAuthorization(requestPayload, authToken,
				APIEndpoints.addDesignationEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("add designation with employee authorization", APIEndpoints.addDesignationEndpoint,
				response);
	}

	@Test(priority = 8, dataProvider = "TestDataForAddDesignation", dataProviderClass = DataProvidersForDesignationFolder.class, enabled = false)
	public void verify_Add_Designation_With_Admin_Authorization(String designationName, int departmentId) {
		test = BaseTest.extent.createTest("Add designation with valid and invalid data and with authorization");

		String requestPayload = DesignationFolderPayloads.addDesignationPayload(designationName, departmentId);

		Response response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.addDesignationEndpoint);

		BaseTest.test_Method_Logs("add designation with admin authorization", APIEndpoints.addDesignationEndpoint,
				requestPayload, response);

		Response getAllDepartmentsResponse = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllDepartmentsEndpoint);

		List<Integer> departmentIds = getAllDepartmentsResponse.jsonPath().getList("departmentId");

		if (designationName.isBlank()) {
			BodyValidation.response400Validation(response);
		} else if (!departmentIds.contains(departmentId)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else if (designations.contains(designationName)) {
			BodyValidation.responseValidation(response, "Conflict", 409);
		} else {
			BodyValidation.responseValidation(response, 201);

			verify_Get_All_Designation_API_With_Authorization("after added new designation");

			assertEquals(newCreatedDesignation, designationName);
		}
	}

	@Test(priority = 9, dataProvider = "TestDataForGetAllDesignationByDepartment", dataProviderClass = DataProvidersForDesignationFolder.class, enabled = false)
	public void verify_Get_All_Designation_By_Department_With_Admin_Authorization(int departmentId) {
		test = BaseTest.extent
				.createTest("Get all designations by department with valid and invalid data and with authorization");

		String requestPayload = DesignationFolderPayloads.getAllDesignationsByDepartmentPayload(departmentId);

		Response response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllDesignationByDepartmentEndpoint);

		BaseTest.test_Method_Logs("get all designations by department with admin authorization",
				APIEndpoints.addDesignationEndpoint, requestPayload, response);

		if (response.getStatusCode() == 404) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			BodyValidation.responseValidation(response, 200);
		}
	}

	@Test(priority = 10)
	public void verify_Update_Designation_With_Employee_Authorization() throws Throwable {
		test = BaseTest.extent.createTest("Update designation with employee authorization");

		String authToken = LoginEmployeeAPITestCases
				.verify_Login_Employee_By_Giving_Valid_Data(Constants.employeeUserId, Constants.employeePassword);

		Response getDesignationResponse = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllDesignationsEndpoint);

		String requestPayload = DesignationFolderPayloads.updateDesignationWithMaxIdPayload(
				getDesignationResponse.getBody().asPrettyString(), 10, "Designation", 3, "QA", 2, "Blue", "#0000FF");

		Response response = Responses.putRequestWithAuthorization(requestPayload, authToken,
				APIEndpoints.updateDesignationEndpoint);

		BaseTest.test_Method_Logs("update designation with employee authorization",
				APIEndpoints.updateDesignationEndpoint, response);
	}

	@Test(priority = 11, dataProvider = "TestDataForUpdateDesignation", dataProviderClass = DataProvidersForDesignationFolder.class, enabled = false)
	public void verify_Update_Designation_With_Admin_Authorization(int designationId, String designationName,
			int departmentId, String departmentName, int departmentLevel, String departmentColor,
			String departmentColorCode) throws Throwable {
		test = BaseTest.extent.createTest("Update designation with valid and invalid data and with authorization");

		Response getDesignationResponse = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllDesignationsEndpoint);

		String requestPayload = DesignationFolderPayloads.updateDesignationWithMaxIdPayload(
				getDesignationResponse.getBody().asPrettyString(), designationId, designationName, departmentId,
				departmentName, departmentLevel, departmentColor, departmentColorCode);

		Response response = Responses.putRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.updateDesignationEndpoint);

		String responseBody = response.getBody().asPrettyString();
		BaseTest.test_Method_Logs("update designation with admin authorization", APIEndpoints.updateDesignationEndpoint,
				requestPayload, response);

		Response getAllDepartmentsResponse = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllDepartmentsEndpoint);

		List<Integer> departmentIds = getAllDepartmentsResponse.jsonPath().getList("departmentId");

		if (designationName.isBlank()) {
			BodyValidation.response400Validation(response);
		} else if (!designationIds.contains(designationId)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else if (designations.contains(designationName)) {
			BodyValidation.responseValidation(response, "Conflict", 409);
		} else if (!departmentIds.contains(departmentId)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			int contentLength = responseBody.length();
			BodyValidation.responseValidation(response, 200, String.valueOf(contentLength));
			assertEquals(responseBody, "Designation Updated Successfully");

			verify_Get_All_Designation_API_With_Authorization("after updated new designation");

			assertEquals(newCreatedDesignation, designationName);
		}
	}

	@Test(priority = 12)
	public void verify_Delete_Designation_With_Employee_Authorization() {
		test = BaseTest.extent.createTest("Delete designation with employee authorization");

		String authToken = LoginEmployeeAPITestCases
				.verify_Login_Employee_By_Giving_Valid_Data(Constants.employeeUserId, Constants.employeePassword);

		Response response = Responses.deleteRequestWithAuthorizationAndQueryParameter("designation", "Designation",
				authToken, APIEndpoints.deleteDesignationEndpoint);

		BodyValidation.response401Validation(response);

		BaseTest.test_Method_Logs("delete designation with employee authorization",
				APIEndpoints.deleteDesignationEndpoint, response);
	}

	@Test(priority = 13, dataProvider = "TestDataForDeleteDesignation", dataProviderClass = DataProvidersForDesignationFolder.class, enabled = false)
	public void verify_Delete_Designation_With_Admin_Authorization(String designationName) {
		test = BaseTest.extent.createTest("Delete designation with valid and invalid data and with authorization");

		Response response = Responses.deleteRequestWithAuthorizationAndQueryParameter("designation", designationName,
				LoginEmployeeAPITestCases.authToken, APIEndpoints.deleteDesignationEndpoint);

		String responseBody = response.getBody().asPrettyString();

		BaseTest.test_Method_Logs_With_Query_Parameter("delete designation with admin authorization",
				APIEndpoints.deleteDesignationEndpoint, designationName, response);

		if (responseBody.equals("[]")) {
			BodyValidation.response204Validation(response);
		} else if (response.getStatusCode() == 403) {
			BodyValidation.responseValidation(response, "Forbidden", 403);
		} else if (!designations.contains(designationName)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			int contentLength = responseBody.length();
			BodyValidation.responseValidation(response, 200, String.valueOf(contentLength));
			assertEquals(responseBody, "Designation deleted Successfully");

			verify_Get_All_Designation_API_With_Authorization("after deleted new designation");
		}
	}

	public void verify_Get_All_Designation_API_With_Authorization(String message) {
		test = BaseTest.extent.createTest("Get All designations with authorization");

		response = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllDesignationsEndpoint);

		BodyValidation.responseValidation(response, 200);

		BaseTest.test_Method_Logs("get all designation", APIEndpoints.getAllDesignationsEndpoint, response);

		designationIds = response.jsonPath().getList("designationId");
		log.info("List of designation Ids " + message + " are => " + designationIds);

		designations = response.jsonPath().getList("designation");
		log.info("List of designation names " + message + " are => " + designations + "\n");

		newCreatedDesignationId = response.jsonPath().getInt("max { it.designationId }.designationId");
		log.info("New created Designation Id " + message + " is => " + newCreatedDesignationId);

		newCreatedDesignation = response.jsonPath()
				.getString("find { it.designationId == " + newCreatedDesignationId + " }.designation");
		log.info("New created Designation " + message + " is => " + newCreatedDesignation + "\n");
	}
}

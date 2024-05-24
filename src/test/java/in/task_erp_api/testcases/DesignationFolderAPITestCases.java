package in.task_erp_api.testcases;

import java.util.*;

import org.apache.logging.log4j.*;
import static org.testng.Assert.*;
import org.testng.annotations.*;

import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.biencaps.erp.pojos.*;
import in.task_erp_api.bodyValidations.*;
import in.task_erp_api.endpoints.*;
import in.task_erp_api.payloads.*;
import in.task_erp_api.responses.*;
import in.task_erp_api.utilities.*;
import io.restassured.response.Response;

public class DesignationFolderAPITestCases extends BaseTest {
	public static final Logger log = LogManager.getLogger(DesignationFolderAPITestCases.class);
	public static int newCreatedDesignationId;
	public static String newCreatedDesignation;

	public static List<String> designations;
	public static List<Integer> designationIds;

	private Response response;
	private Random random = new Random();

	@Test(priority = 1)
	public void verify_Add_Designation_Without_Authorization() {
		test = BaseTest.extent.createTest("Add designation without authorization");

		String requestPayload = DesignationFolderPayloads.addDesignationPayload("Designation", 3);

		Response response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.addDesignationEndpoint);

		BodyValidation.response401Validation(response);
		test.log(Status.INFO, "Status code for add designation is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for add designation is => " + response.getBody().asPrettyString());
	}

	@Test(priority = 2)
	public void verify_Get_All_Designation_By_Department_Without_Authorization() {
		test = BaseTest.extent.createTest("Get all designations by department without authorization");

		String requestPayload = DesignationFolderPayloads.getAllDesignationsByDepartmentPayload(10);

		Response response = Responses.postRequestWithoutAuthorization(requestPayload,
				APIEndpoints.getAllDesignationsEndpoint);

		BodyValidation.response401Validation(response);
		test.log(Status.INFO, "Status code for get all designations by department is => " + response.getStatusCode());
		test.log(Status.INFO,
				"Response for get all designations by department is => " + response.getBody().asPrettyString());
	}

	@Test(priority = 3)
	public void verify_Get_All_Designation_Without_Authorization() {
		test = BaseTest.extent.createTest("Get all designations without authorization");

		Response response = Responses.getRequestWithoutAuthorization(APIEndpoints.getAllDesignationsEndpoint);

		BodyValidation.response401Validation(response);
		test.log(Status.INFO, "Status code for get all designations is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for get all designations is => " + response.getBody().asPrettyString());
	}

	@Test(priority = 4)
	public void verify_Update_Designation_Without_Authorization() throws Throwable {
		test = BaseTest.extent.createTest("Update designation without authorization");

		Response getDesignationResponse = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllDesignationsEndpoint);

		// Creating object instance
		ObjectMapper objectMapper = new ObjectMapper();

		// Getting get all departments API response
		Response getDepartmentResponse = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllDepartmentsEndpoint);

		// reading value for use in update designation
		List<DepartmentPojo> departments = objectMapper.readValue(getDepartmentResponse.getBody().asPrettyString(),
				objectMapper.getTypeFactory().constructCollectionType(List.class, DepartmentPojo.class));

		String requestPayload = DesignationFolderPayloads.updateDesignationWithMaxIdPayload(
				getDesignationResponse.getBody().asPrettyString(), 7, "Designation", departments);

		response = Responses.putRequestWithoutAuthorization(requestPayload, APIEndpoints.updateDesignationEndpoint);

		BodyValidation.response401Validation(response);
		test.log(Status.INFO, "Status code for update designation is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for update designation is => " + response.getBody().asPrettyString());
	}

	@Test(priority = 5)
	public void verify_Delete_Designation_Without_Authorization() {
		test = BaseTest.extent.createTest("Delete designation without authorization");

		Response response = Responses.deleteRequestWithoutAuthorizationAndQueryParameter("designation", "Designation",
				APIEndpoints.deleteDesignationEndpoint);

		BodyValidation.response401Validation(response);
		test.log(Status.INFO, "Status code for delete designation is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for delete designation is => " + response.getBody().asPrettyString());
	}

	@Test(priority = 6)
	public void verify_Get_All_Designation_With_Authorization() {
		verify_Get_All_Designation_API_With_Authorization("before add new designation");
	}

	@Test(priority = 7, dataProvider = "TestDataForAddDesignation", dataProviderClass = DataProvidersForDesignationFolder.class, enabled = false)
	public void verify_Add_Designation_With_Authorization(String designationName, int departmentId) {
		String requestPayload = DesignationFolderPayloads.addDesignationPayload(designationName, departmentId);

		Response response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.addDesignationEndpoint);

		if (designationName.isBlank()) {
			BodyValidation.response400Validation(response);
		} else if (!DepartmentFolderAPITestCases.departmentIds.contains(departmentId)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else if (designations.contains(designationName)) {
			BodyValidation.responseValidation(response, "Conflict", 409);
		} else {
			BodyValidation.responseValidation(response, 201);

			verify_Get_All_Designation_API_With_Authorization("after added new designation");

			assertEquals(newCreatedDesignation, designationName);
		}
	}

	@Test(priority = 8)
	public void verify_Get_All_Designation_By_Department_With_Authorization() {
		// Getting get all departments API response
		Response getDepartmentResponse = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllDepartmentsEndpoint);

		List<Integer> departmentIds = getDepartmentResponse.jsonPath().getList("departmentId");
		int randomIndexForDepartmentId = random.nextInt(departmentIds.size());
		int fakeDepartmentId = departmentIds.get(randomIndexForDepartmentId);

		String requestPayload = DesignationFolderPayloads.getAllDesignationsByDepartmentPayload(fakeDepartmentId);

		Response response = Responses.postRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllDesignationByDepartmentEndpoint);

		if (response.getStatusCode() == 404) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else {
			BodyValidation.responseValidation(response, 200);
		}
	}

	@Test(priority = 9, dataProvider = "TestDataForUpdateDesignation", dataProviderClass = DataProvidersForDesignationFolder.class, enabled = false)
	public void verify_Update_Designation_With_Authorization(int designationId, String designationName)
			throws Throwable {
		test = BaseTest.extent.createTest("Update designation with valid and invalid data and with authorization");

		Response getDesignationResponse = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllDesignationsEndpoint);

		// Creating object instance
		ObjectMapper objectMapper = new ObjectMapper();

		// Getting get all departments API response
		Response getDepartmentResponse = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllDepartmentsEndpoint);

		// reading value for use in update designation
		List<DepartmentPojo> departments = objectMapper.readValue(getDepartmentResponse.getBody().asPrettyString(),
				objectMapper.getTypeFactory().constructCollectionType(List.class, DepartmentPojo.class));

		String requestPayload = DesignationFolderPayloads.updateDesignationWithMaxIdPayload(
				getDesignationResponse.getBody().asPrettyString(), designationId, designationName, departments);

		Response response = Responses.putRequestWithAuthorization(requestPayload, LoginEmployeeAPITestCases.authToken,
				APIEndpoints.updateDesignationEndpoint);

		String responseBody = response.getBody().asPrettyString();
		test.log(Status.INFO, "Request paylaod for update designation is => " + designationName);
		test.log(Status.INFO, "Response body for update designation is => " + response.getBody().asPrettyString());
		test.log(Status.INFO, "Status code for update designation is => " + response.getStatusCode());

		if (designationName.isBlank()) {
			BodyValidation.response400Validation(response);
		} else if (!designationIds.contains(designationId)) {
			BodyValidation.responseValidation(response, "Not Found", 404);
		} else if (designations.contains(designationName)) {
			BodyValidation.responseValidation(response, "Conflict", 409);
		} else {
			int contentLength = responseBody.length();
			BodyValidation.responseValidation(response, 200, String.valueOf(contentLength));
			assertEquals(responseBody, "Updated Successfully");

			verify_Get_All_Designation_API_With_Authorization("after updated new designation");

			assertEquals(newCreatedDesignation, designationName);
		}
	}

	@Test(priority = 10, dataProvider = "TestDataForDeleteDesignation", dataProviderClass = DataProvidersForDesignationFolder.class, enabled = false)
	public void verify_Delete_Designation_With_Authorization(String designationName) {
		test = BaseTest.extent.createTest("Delete designation with valid and invalid data and with authorization");

		Response response = Responses.deleteRequestWithAuthorizationAndQueryParameter("designation", designationName,
				LoginEmployeeAPITestCases.authToken, APIEndpoints.deleteDesignationEndpoint);

		String responseBody = response.getBody().asPrettyString();
		test.log(Status.INFO, "Request paylaod for delete designation is => " + designationName);
		test.log(Status.INFO, "Response body for delete designation is => " + response.getBody().asPrettyString());
		test.log(Status.INFO, "Status code for delete designation is => " + response.getStatusCode());

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
		test.log(Status.INFO, "Status code for get all designations is => " + response.getStatusCode());
		test.log(Status.INFO, "Response for get all designations is => " + response.getBody().asPrettyString());

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

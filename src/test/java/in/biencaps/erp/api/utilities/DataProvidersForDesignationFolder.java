package in.biencaps.erp.api.utilities;

import java.util.*;
import org.testng.annotations.*;

import in.biencaps.erp.api.endpoints.APIEndpoints;
import in.biencaps.erp.api.responses.Responses;
import in.biencaps.erp.api.testcases.*;
import io.restassured.response.Response;

public class DataProvidersForDesignationFolder {
	private static Random random = new Random();

	@DataProvider(name = "TestDataForAddDesignation")
	public Object[][] testDataForAddDesignation() {
		int randomIndexForDesignationName = random.nextInt(DesignationFolderAPITestCases.designations.size());
		String randomDesignationName = DesignationFolderAPITestCases.designations.get(randomIndexForDesignationName);

		// Getting get all departments API response
		Response getDepartmentResponse = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllDepartmentsEndpoint);

		List<Integer> departmentIds = getDepartmentResponse.jsonPath().getList("departmentId");

		int randomIndexForDepartmentId = random.nextInt(departmentIds.size());
		int randomDepartmentId = departmentIds.get(randomIndexForDepartmentId);

		int invalidDepartmentId = DataGeneratorForAPI.generateFakeNumberWithRange(20, 50);

		return new Object[][] {
				// Entering empty string for only designationName field
				{ "", randomDepartmentId },
				// Entering valid data for all fields
				{ DataGeneratorForAPI.generateFakeDesignation(), randomDepartmentId },
				// Entering already exist designationName for only designationName field
				{ randomDesignationName, randomDepartmentId },
				// Entering invalid departmentId for only departmentId field
				{ randomDesignationName, invalidDepartmentId } };
	}

	@DataProvider(name = "TestDataForUpdateDesignation")
	public Object[][] testDataForUpdateDesignation() {
		int invalidDesignationId = DataGeneratorForAPI.generateFakeNumberWithRange(
				DesignationFolderAPITestCases.newCreatedDesignationId + 10,
				DesignationFolderAPITestCases.newCreatedDesignationId + 50);

		int randomIndexForDesignationName = random.nextInt(DesignationFolderAPITestCases.designations.size());
		String randomDesignationName = DesignationFolderAPITestCases.designations.get(randomIndexForDesignationName);

		int departmentId = 6;
		String departmentName = "Mobile";
		int departmentLevel = 5;
		String departmentColor = "rust";
		String departmentColorCode = "#F3CEA9";

		int invalidDepartmentId = DataGeneratorForAPI.generateFakeNumberWithRange(50, 100);

		return new Object[][] {
				// Entering empty string for only designationName field
				{ DesignationFolderAPITestCases.newCreatedDesignationId, "", departmentId, departmentName,
						departmentLevel, departmentColor, departmentColorCode },
				// Entering valid data for all fields
				{ DesignationFolderAPITestCases.newCreatedDesignationId, DataGeneratorForAPI.generateFakeDesignation(),
						departmentId, departmentName, departmentLevel, departmentColor, departmentColorCode },
				// Entering already exist designationName for only designationName field
				{ DesignationFolderAPITestCases.newCreatedDesignationId, randomDesignationName, departmentId,
						departmentName, departmentLevel, departmentColor, departmentColorCode },
				// Entering invalid departmentId for only departmentId field
				{ DesignationFolderAPITestCases.newCreatedDesignationId, DataGeneratorForAPI.generateFakeDesignation(),
						invalidDepartmentId, departmentName, departmentLevel, departmentColor, departmentColorCode },
				// Entering invalid designationId for only deisgnationId field
				{ invalidDesignationId, DataGeneratorForAPI.generateFakeDesignation(), departmentId, departmentName,
						departmentLevel, departmentColor, departmentColorCode } };
	}

	@DataProvider(name = "TestDataForDeleteDesignation")
	public Object[][] testDataForDeleteDesignation() {
		int randomIndexForDesignationName = random.nextInt(DesignationFolderAPITestCases.designations.size());
		String randomDesignationName = DesignationFolderAPITestCases.designations.get(randomIndexForDesignationName);

		return new Object[][] {
				// Entering invalid designationName for only designationName field
				{ DataGeneratorForAPI.generateFakeDesignation() },
				// Entering already mapped designationName for designationName field
				{ randomDesignationName },
				// Entering valid date for all fields
				{ DesignationFolderAPITestCases.newCreatedDesignation } };
	}

	@DataProvider(name = "TestDataForGetAllDesignationByDepartment")
	public Object[][] testDataForGetAllDesignationByDepartment() {
		// Getting get all departments API response
		Response getDepartmentResponse = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getAllDepartmentsEndpoint);

		List<Integer> departmentIds = getDepartmentResponse.jsonPath().getList("departmentId");
		int randomIndexForDepartmentId = random.nextInt(departmentIds.size());
		int validDepartmentId = departmentIds.get(randomIndexForDepartmentId);

		int invalidDepartmentId = DataGeneratorForAPI.generateFakeNumberWithRange(50, 100);

		return new Object[][] {
				// Entering invalid departmentId for only departmentId field
				{ invalidDepartmentId },
				// Entering valid date for all fields
				{ validDepartmentId } };
	}
}

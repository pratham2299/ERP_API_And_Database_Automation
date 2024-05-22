package in.task_erp_api.utilities;

import java.util.*;
import org.testng.annotations.*;

import in.task_erp_api.testcases.*;

public class DataProvidersForDesignationFolder {
	private static Random random = new Random();

	@DataProvider(name = "TestDataForAddDesignation")
	public Object[][] testDataForAddDesignation() {
		String fakeDesignation = DataGeneratorForAPI.generateFakeDesignation();

		int randomIndexForDesignationName = random.nextInt(DesignationFolderAPITestCases.designations.size());
		String randomDesignationName = DesignationFolderAPITestCases.designations.get(randomIndexForDesignationName);

		int randomIndexForDepartmentId = random.nextInt(DepartmentFolderAPITestCases.departmentIds.size());
		int randomDepartmentId = DepartmentFolderAPITestCases.departmentIds.get(randomIndexForDepartmentId);

		int invalidDepartmentId = DataGeneratorForAPI.generateFakeNumberWithRange(20, 50);

		return new Object[][] { { "", randomDepartmentId }, { fakeDesignation, randomDepartmentId },
				{ randomDesignationName, randomDepartmentId }, { randomDesignationName, invalidDepartmentId } };
	}

	@DataProvider(name = "TestDataForUpdateDesignation")
	public Object[][] testDataForUpdateDesignation() {
		String fakeDesignation = DataGeneratorForAPI.generateFakeDesignation();

		int invalidDesignationId = DataGeneratorForAPI.generateFakeNumberWithRange(
				DesignationFolderAPITestCases.newCreatedDesignationId + 10,
				DesignationFolderAPITestCases.newCreatedDesignationId + 50);

		int randomIndexForDesignationName = random.nextInt(DesignationFolderAPITestCases.designations.size());
		String randomDesignationName = DesignationFolderAPITestCases.designations.get(randomIndexForDesignationName);

		return new Object[][] { { DesignationFolderAPITestCases.newCreatedDesignationId, "" },
				{ DesignationFolderAPITestCases.newCreatedDesignationId, fakeDesignation },
				{ DesignationFolderAPITestCases.newCreatedDesignationId, randomDesignationName },
				{ invalidDesignationId, fakeDesignation } };
	}

	@DataProvider(name = "TestDataForDeleteDesignation")
	public Object[][] testDataForDeleteDesignation() {
		String fakeDesignation = DataGeneratorForAPI.generateFakeDesignation();

		int randomIndexForDesignationName = random.nextInt(DesignationFolderAPITestCases.designations.size());
		String randomDesignationName = DesignationFolderAPITestCases.designations.get(randomIndexForDesignationName);

		return new Object[][] { { fakeDesignation }, { randomDesignationName },
				{ DesignationFolderAPITestCases.newCreatedDesignation } };
	}
}

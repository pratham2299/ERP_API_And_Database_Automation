package in.biencaps.erp.api.utilities;

import java.util.*;
import org.testng.annotations.*;

import in.biencaps.erp.api.testcases.*;

public class DataProvidersForDepartmentFolder {
	private static Random random = new Random();

	@DataProvider(name = "TestDataForAddDepartment")
	public Object[][] testDataForAddDesignation() {
		int validDepartmentLevel = DataGeneratorForAPI.generateFakeNumberWithRange(10, 15);

		int randomIndexForDepartmentName = random.nextInt(DepartmentFolderAPITestCases.departments.size());
		String randomDepartmentName = DepartmentFolderAPITestCases.departments.get(randomIndexForDepartmentName);

		int randomIndexForDepartmentLevel = random.nextInt(DepartmentFolderAPITestCases.departmentLevels.size());
		int randomDepartmentLevel = DepartmentFolderAPITestCases.departmentLevels.get(randomIndexForDepartmentLevel);

		int randomIndexForDepartmentColor = random.nextInt(DepartmentFolderAPITestCases.departmentColors.size());
		String randomDepartmentColor = DepartmentFolderAPITestCases.departmentColors.get(randomIndexForDepartmentColor);

		int randomIndexForDepartmentColorCode = random
				.nextInt(DepartmentFolderAPITestCases.departmentColorCodes.size());
		String randomDepartmentColorCode = DepartmentFolderAPITestCases.departmentColorCodes
				.get(randomIndexForDepartmentColorCode);

		return new Object[][] {
				// Entering valid data for all fields
				{ DataGeneratorForAPI.generateFakeDepartment(), validDepartmentLevel,
						DataGeneratorForAPI.generateFakeColor(), DataGeneratorForAPI.generateFakeColorCode() },
				// Entering empty string for only departmentName field
				{ "", validDepartmentLevel, DataGeneratorForAPI.generateFakeColor(),
						DataGeneratorForAPI.generateFakeColorCode() },
				// Entering already exist departmentName for only departmentName field
				{ randomDepartmentName, validDepartmentLevel, DataGeneratorForAPI.generateFakeColor(),
						DataGeneratorForAPI.generateFakeColorCode() },
				// Entering already exist departmentLevel for only departmentLevel field
				{ DataGeneratorForAPI.generateFakeDepartment(), randomDepartmentLevel,
						DataGeneratorForAPI.generateFakeColor(), DataGeneratorForAPI.generateFakeColorCode() },
				// Entering empty string for only departmentColor field
				{ DataGeneratorForAPI.generateFakeDepartment(), validDepartmentLevel, "",
						DataGeneratorForAPI.generateFakeColorCode() },
				// Entering already exist departmentColor for only departmentColor field
				{ DataGeneratorForAPI.generateFakeDepartment(), validDepartmentLevel, randomDepartmentColor,
						DataGeneratorForAPI.generateFakeColorCode() },
				// Entering empty string for only departmentColorCode field
				{ DataGeneratorForAPI.generateFakeDepartment(), validDepartmentLevel,
						DataGeneratorForAPI.generateFakeColor(), "" },
				// Entering already exist departmentColorCode for only departmentColorCode field
				{ DataGeneratorForAPI.generateFakeDepartment(), validDepartmentLevel,
						DataGeneratorForAPI.generateFakeColor(), randomDepartmentColorCode } };
	}

	@DataProvider(name = "TestDataForUpdateDepartment")
	public Object[][] testDataForUpdateDepartment() {
		int validDepartmentLevel = DataGeneratorForAPI.generateFakeNumberWithRange(16, 20);
		int invalidDepartmentId = DataGeneratorForAPI.generateFakeNumberWithRange(
				DepartmentFolderAPITestCases.newCreatedDepartmentId + 10,
				DepartmentFolderAPITestCases.newCreatedDepartmentId + 50);

		int randomIndexForDepartmentName = random.nextInt(DepartmentFolderAPITestCases.departments.size());
		String randomDepartmentName = DepartmentFolderAPITestCases.departments.get(randomIndexForDepartmentName);

		int randomIndexForDepartmentLevel = random.nextInt(DepartmentFolderAPITestCases.departmentLevels.size());
		int randomDepartmentLevel = DepartmentFolderAPITestCases.departmentLevels.get(randomIndexForDepartmentLevel);

		int randomIndexForDepartmentColor = random.nextInt(DepartmentFolderAPITestCases.departmentColors.size());
		String randomDepartmentColor = DepartmentFolderAPITestCases.departmentColors.get(randomIndexForDepartmentColor);

		int randomIndexForDepartmentColorCode = random
				.nextInt(DepartmentFolderAPITestCases.departmentColorCodes.size());
		String randomDepartmentColorCode = DepartmentFolderAPITestCases.departmentColorCodes
				.get(randomIndexForDepartmentColorCode);

		return new Object[][] {
				// Entering valid data for all fields
				{ DepartmentFolderAPITestCases.newCreatedDepartmentId, DataGeneratorForAPI.generateFakeDepartment(),
						validDepartmentLevel, DataGeneratorForAPI.generateFakeColor(),
						DataGeneratorForAPI.generateFakeColorCode() },
				// Entering invalid departmentId for only departmentId field
				{ invalidDepartmentId, DataGeneratorForAPI.generateFakeDepartment(), validDepartmentLevel,
						DataGeneratorForAPI.generateFakeColor(), DataGeneratorForAPI.generateFakeColorCode() },
				// Entering empty string for only departmentName field
				{ DepartmentFolderAPITestCases.newCreatedDepartmentId, "", validDepartmentLevel,
						DataGeneratorForAPI.generateFakeColor(), DataGeneratorForAPI.generateFakeColorCode() },
				// Entering already exist departmentName for only departmentName field
				{ DepartmentFolderAPITestCases.newCreatedDepartmentId, randomDepartmentName, validDepartmentLevel,
						DataGeneratorForAPI.generateFakeColor(), DataGeneratorForAPI.generateFakeColorCode() },
				// Entering already exist departmentLevel for only departmentLevel field
				{ DepartmentFolderAPITestCases.newCreatedDepartmentId, DataGeneratorForAPI.generateFakeDepartment(),
						randomDepartmentLevel, DataGeneratorForAPI.generateFakeColor(),
						DataGeneratorForAPI.generateFakeColorCode() },
				// Entering empty string for only departmentColor field
				{ DepartmentFolderAPITestCases.newCreatedDepartmentId, DataGeneratorForAPI.generateFakeDepartment(),
						validDepartmentLevel, "", DataGeneratorForAPI.generateFakeColorCode() },
				// Entering already exist departmentColor for only departmentColor field
				{ DepartmentFolderAPITestCases.newCreatedDepartmentId, DataGeneratorForAPI.generateFakeDepartment(),
						validDepartmentLevel, randomDepartmentColor, DataGeneratorForAPI.generateFakeColorCode() },
				// Entering empty string for only departmentColorCode field
				{ DepartmentFolderAPITestCases.newCreatedDepartmentId, DataGeneratorForAPI.generateFakeDepartment(),
						validDepartmentLevel, DataGeneratorForAPI.generateFakeColor(), "" },
				// Entering already exist departmentColorCode for only departmentColorCode field
				{ DepartmentFolderAPITestCases.newCreatedDepartmentId, DataGeneratorForAPI.generateFakeDepartment(),
						validDepartmentLevel, DataGeneratorForAPI.generateFakeColor(), randomDepartmentColorCode } };
	}

	@DataProvider(name = "TestDataForDeleteDepartment")
	public Object[][] testDataForDeleteDepartment() {
		int invalidDepartmentId = DataGeneratorForAPI.generateFakeNumberWithRange(
				DepartmentFolderAPITestCases.newCreatedDepartmentId + 10,
				DepartmentFolderAPITestCases.newCreatedDepartmentId + 50);

		int randomIndexForDepartmentId = random.nextInt(DepartmentFolderAPITestCases.departmentIds.size());
		int randomDepartmentId = DepartmentFolderAPITestCases.departmentIds.get(randomIndexForDepartmentId);

		return new Object[][] {
				// Entering invalid departmentId for only departmentId field
				{ invalidDepartmentId },
				// Entering already mapped departmentId for only departmentId field
				{ randomDepartmentId },
				// Entering valid data for departmentId field
				{ DepartmentFolderAPITestCases.newCreatedDepartmentId } };
	}
}

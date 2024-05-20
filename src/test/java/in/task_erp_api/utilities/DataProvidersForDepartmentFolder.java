package in.task_erp_api.utilities;

import java.util.*;
import org.testng.annotations.*;

import in.task_erp_api.testcases.*;

public class DataProvidersForDepartmentFolder {
	private static Random random = new Random();

	@DataProvider(name = "TestDataForAddDepartment")
	public Object[][] testDataForAddDesignation() {
		String fakeDepartment = DataGeneratorForAPI.generateFakeDepartment();
		int fakeDepartmentLevel = DataGeneratorForAPI.generateFakeNumberWithRange(10, 20);
		String fakeDepartmentColor = DataGeneratorForAPI.generateFakeColor();
		String fakeDepartmentColorCode = DataGeneratorForAPI.generateFakeColorCode();

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

		return new Object[][] { { fakeDepartment, fakeDepartmentLevel, fakeDepartmentColor, fakeDepartmentColorCode },
				{ fakeDepartment, randomDepartmentLevel, fakeDepartmentColor, fakeDepartmentColorCode },
				{ fakeDepartment, fakeDepartmentLevel, "", fakeDepartmentColorCode },
				{ fakeDepartment, fakeDepartmentLevel, randomDepartmentColor, fakeDepartmentColorCode },
				{ fakeDepartment, fakeDepartmentLevel, fakeDepartmentColor, "" },
				{ fakeDepartment, fakeDepartmentLevel, fakeDepartmentColor, randomDepartmentColorCode },
				{ "", fakeDepartmentLevel, fakeDepartmentColor, fakeDepartmentColorCode },
				{ randomDepartmentName, fakeDepartmentLevel, fakeDepartmentColor, fakeDepartmentColorCode } };
	}

	@DataProvider(name = "TestDataForUpdateDepartment")
	public Object[][] testDataForUpdateDepartment() {
		String fakeDepartment = DataGeneratorForAPI.generateFakeDepartment();
		int fakeDepartmentLevel = DataGeneratorForAPI.generateFakeNumberWithRange(10, 20);
		String fakeDepartmentColor = DataGeneratorForAPI.generateFakeColor();
		String fakeDepartmentColorCode = DataGeneratorForAPI.generateFakeColorCode();
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
				{ DepartmentFolderAPITestCases.newCreatedDepartmentId, fakeDepartment, fakeDepartmentLevel,
						fakeDepartmentColor, fakeDepartmentColorCode },
				{ DepartmentFolderAPITestCases.newCreatedDepartmentId, fakeDepartment, randomDepartmentLevel,
						fakeDepartmentColor, fakeDepartmentColorCode },
				{ DepartmentFolderAPITestCases.newCreatedDepartmentId, "", fakeDepartmentLevel, fakeDepartmentColor,
						fakeDepartmentColorCode },
				{ DepartmentFolderAPITestCases.newCreatedDepartmentId, randomDepartmentName, fakeDepartmentLevel,
						fakeDepartmentColor, fakeDepartmentColorCode },
				{ DepartmentFolderAPITestCases.newCreatedDepartmentId, fakeDepartment, fakeDepartmentLevel, "",
						fakeDepartmentColorCode },
				{ DepartmentFolderAPITestCases.newCreatedDepartmentId, fakeDepartment, fakeDepartmentLevel,
						randomDepartmentColor, fakeDepartmentColorCode },
				{ DepartmentFolderAPITestCases.newCreatedDepartmentId, fakeDepartment, fakeDepartmentLevel,
						fakeDepartmentColor, "" },
				{ DepartmentFolderAPITestCases.newCreatedDepartmentId, fakeDepartment, fakeDepartmentLevel,
						fakeDepartmentColor, randomDepartmentColorCode },
				{ invalidDepartmentId, fakeDepartment, fakeDepartmentLevel, fakeDepartmentColor,
						fakeDepartmentColorCode } };
	}

	@DataProvider(name = "TestDataForDeleteDepartment")
	public Object[][] testDataForDeleteDepartment() {
		int invalidDepartmentId = DataGeneratorForAPI.generateFakeNumberWithRange(
				DepartmentFolderAPITestCases.newCreatedDepartmentId + 10,
				DepartmentFolderAPITestCases.newCreatedDepartmentId + 50);

		int randomIndexForDepartmentId = random.nextInt(DepartmentFolderAPITestCases.departmentIds.size());
		int randomDepartmentId = DepartmentFolderAPITestCases.departmentIds.get(randomIndexForDepartmentId);

		return new Object[][] { { invalidDepartmentId }, { randomDepartmentId },
				{ DepartmentFolderAPITestCases.newCreatedDepartmentId } };
	}
}

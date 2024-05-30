package in.biencaps.erp.api.utilities;

import java.util.*;
import org.testng.annotations.*;

import in.biencaps.erp.api.testcases.*;

public class DataProvidersForRoleFolder {
	private static Random random = new Random();

	@DataProvider(name = "TestDataForAddRole")
	public Object[][] testDataForAddRole() {
		int randomIndexForRoleName = random.nextInt(RoleFolderAPITestCases.roles.size());
		String randomRoleName = RoleFolderAPITestCases.roles.get(randomIndexForRoleName);

		int randomIndexForRoleLevel = random.nextInt(RoleFolderAPITestCases.roleLevels.size());
		int randomRoleLevel = RoleFolderAPITestCases.roleLevels.get(randomIndexForRoleLevel);

		return new Object[][] {
				// Entering valid data for all fields
				{ DataGeneratorForAPI.generateFakeRole(), DataGeneratorForAPI.generateFakeNumberWithRange(10, 15) },
				// Entering already exist roleLevel for only roleLevel field
				{ DataGeneratorForAPI.generateFakeRole(), randomRoleLevel },
				// Entering empty string for for only roleName field
				{ "", DataGeneratorForAPI.generateFakeNumberWithRange(10, 15) },
				// Entering already exist roleLevel for only roleLevel field
				{ randomRoleName, DataGeneratorForAPI.generateFakeNumberWithRange(10, 15) }, };
	}

	@DataProvider(name = "TestDataForUpdateRole")
	public Object[][] testDataForUpdateRole() {
		int invalidRoleId = DataGeneratorForAPI.generateFakeNumberWithRange(
				RoleFolderAPITestCases.newCreatedRoleId + 10, RoleFolderAPITestCases.newCreatedRoleId + 50);

		int randomIndexForRoleName = random.nextInt(RoleFolderAPITestCases.roles.size());
		String randomRoleName = RoleFolderAPITestCases.roles.get(randomIndexForRoleName);

		int randomIndexForRoleLevel = random.nextInt(RoleFolderAPITestCases.roleLevels.size());
		int randomRoleLevel = RoleFolderAPITestCases.roleLevels.get(randomIndexForRoleLevel);

		return new Object[][] {
				// Entering valid data for all fields
				{ RoleFolderAPITestCases.newCreatedRoleId, DataGeneratorForAPI.generateFakeRole(),
						DataGeneratorForAPI.generateFakeNumberWithRange(16, 20) },
				// Entering already exist roleLevel for only roleLevel field
				{ RoleFolderAPITestCases.newCreatedRoleId, DataGeneratorForAPI.generateFakeRole(), randomRoleLevel },
				// Entering empty string for for only roleName field
				{ RoleFolderAPITestCases.newCreatedRoleId, "",
						DataGeneratorForAPI.generateFakeNumberWithRange(16, 20) },
				// Entering already exist roleLevel for only roleLevel field
				{ RoleFolderAPITestCases.newCreatedRoleId, randomRoleName,
						DataGeneratorForAPI.generateFakeNumberWithRange(16, 20) },
				// Entering invalid roleId for only roleId field
				{ invalidRoleId, DataGeneratorForAPI.generateFakeRole(),
						DataGeneratorForAPI.generateFakeNumberWithRange(16, 20) }, };
	}

	@DataProvider(name = "TestDataForDeleteRole")
	public Object[][] testDataForDeleteRole() {
//		int randomIndexForRoleId = random.nextInt(RoleFolderAPITestCases.roleIds.size());
//		int randomRoleId = RoleFolderAPITestCases.roleIds.get(randomIndexForRoleId);

		int invalidRoleId = DataGeneratorForAPI.generateFakeNumberWithRange(
				RoleFolderAPITestCases.newCreatedRoleId + 10, RoleFolderAPITestCases.newCreatedRoleId + 50);

		return new Object[][] { { invalidRoleId }, { RoleFolderAPITestCases.newCreatedRoleId },
//				{ randomRoleId } 
		};
	}

	@DataProvider(name = "TestDataForGetRoleByLevel")
	public Object[][] testDataForGetRoleByLevel() {
		int randomIndexForRoleLevel = random.nextInt(RoleFolderAPITestCases.roleLevels.size());
		int randomRoleLevel = RoleFolderAPITestCases.roleLevels.get(randomIndexForRoleLevel);

		int invalidRoleLevel = DataGeneratorForAPI.generateFakeNumberWithRange(50, 100);

		return new Object[][] { { invalidRoleLevel }, { randomRoleLevel } };
	}
}

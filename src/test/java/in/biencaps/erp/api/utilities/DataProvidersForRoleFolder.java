package in.biencaps.erp.api.utilities;

import java.util.*;
import org.testng.annotations.*;
import com.github.javafaker.*;

import in.biencaps.erp.api.testcases.*;

public class DataProvidersForRoleFolder {
	private static Faker faker = new Faker();
	private static Random random = new Random();

	@DataProvider(name = "TestDataForAddRole")
	public Object[][] testDataForAddRole() {
		int randomIndexForRoleName = random.nextInt(RoleFolderAPITestCases.roles.size());
		String randomRoleName = RoleFolderAPITestCases.roles.get(randomIndexForRoleName);

		int randomIndexForRoleLevel = random.nextInt(RoleFolderAPITestCases.roleLevels.size());
		int randomRoleLevel = RoleFolderAPITestCases.roleLevels.get(randomIndexForRoleLevel);

		return new Object[][] { { faker.country().capital(), faker.number().numberBetween(10, 20) },
				{ faker.country().capital(), randomRoleLevel }, { "", faker.number().numberBetween(10, 20) },
				{ randomRoleName, faker.number().numberBetween(10, 20) }, };
	}

	@DataProvider(name = "TestDataForUpdateRole")
	public Object[][] testDataForUpdateRole() {
		int randomIndexForRoleName = random.nextInt(RoleFolderAPITestCases.roles.size());
		String randomRoleName = RoleFolderAPITestCases.roles.get(randomIndexForRoleName);

		int randomIndexForRoleLevel = random.nextInt(RoleFolderAPITestCases.roleLevels.size());
		int randomRoleLevel = RoleFolderAPITestCases.roleLevels.get(randomIndexForRoleLevel);

		return new Object[][] {
				{ RoleFolderAPITestCases.newCreatedRoleId, faker.country().capital(),
						faker.number().numberBetween(10, 20) },
				{ RoleFolderAPITestCases.newCreatedRoleId, faker.country().capital(), randomRoleLevel },
				{ RoleFolderAPITestCases.newCreatedRoleId, "", faker.number().numberBetween(10, 20) },
				{ RoleFolderAPITestCases.newCreatedRoleId, randomRoleName, faker.number().numberBetween(10, 20) },
				{ faker.number().numberBetween(RoleFolderAPITestCases.newCreatedRoleId + 10,
						RoleFolderAPITestCases.newCreatedRoleId + 50), faker.country().capital(),
						faker.number().numberBetween(10, 20) }, };
	}

	@DataProvider(name = "TestDataForDeleteRole")
	public Object[][] testDataForDeleteRole() {
//		int randomIndexForRoleId = random.nextInt(RoleFolderAPITestCases.roleIds.size());
//		int randomRoleId = RoleFolderAPITestCases.roleIds.get(randomIndexForRoleId);

		return new Object[][] { { faker.number().numberBetween(50, 100) }, { RoleFolderAPITestCases.newCreatedRoleId },
//				{ randomRoleId } 
		};
	}

	@DataProvider(name = "TestDataForGetRoleByLevel")
	public Object[][] testDataForGetRoleByLevel() {
		int randomIndexForRoleLevel = random.nextInt(RoleFolderAPITestCases.roleLevels.size());
		int randomRoleLevel = RoleFolderAPITestCases.roleLevels.get(randomIndexForRoleLevel);

		return new Object[][] { { faker.number().numberBetween(50, 100) }, { randomRoleLevel } };
	}
}

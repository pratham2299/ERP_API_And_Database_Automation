package in.task_erp_api.utilities;

import java.util.*;
import org.testng.annotations.*;
import com.github.javafaker.*;

import in.task_erp_api.testcases.*;

public class DataProvidersForDesignationFolder {
	private static Faker faker = new Faker();
	private static Random random = new Random();

	@DataProvider(name = "TestDataForAddDesignation")
	public Object[][] testDataForAddDesignation() {
		int randomIndexForDesignationName = random.nextInt(DesignationFolderAPITestCases.designations.size());
		String randomDesignationName = DesignationFolderAPITestCases.designations.get(randomIndexForDesignationName);

		return new Object[][] { { faker.company().profession() }, { "" }, { randomDesignationName } };
	}

	@DataProvider(name = "TestDataForUpdateDesignation")
	public Object[][] testDataForUpdateDesignation() {
		int randomIndexForDesignationName = random.nextInt(DesignationFolderAPITestCases.designations.size());
		String randomDesignationName = DesignationFolderAPITestCases.designations.get(randomIndexForDesignationName);

		return new Object[][] { { DesignationFolderAPITestCases.newCreatedDesignationId, faker.company().profession() },
				{ DesignationFolderAPITestCases.newCreatedDesignationId, "" },
				{ DesignationFolderAPITestCases.newCreatedDesignationId, randomDesignationName },
				{ faker.number().numberBetween(50, 100), faker.company().profession() } };
	}

	@DataProvider(name = "TestDataForDeleteDesignation")
	public Object[][] testDataForDeleteDesignation() {
		int randomIndexForDesignationName = random.nextInt(DesignationFolderAPITestCases.designations.size());
		String randomDesignationName = DesignationFolderAPITestCases.designations.get(randomIndexForDesignationName);

		return new Object[][] { { faker.dog().breed() }, { randomDesignationName },
				{ DesignationFolderAPITestCases.newCreatedDesignation } };
	}
}

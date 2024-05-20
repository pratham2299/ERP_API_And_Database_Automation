package in.task_erp_api.utilities;

import java.util.Random;

import org.testng.annotations.DataProvider;

import com.github.javafaker.Faker;

import in.task_erp_api.testcases.*;

public class DataProvidersForMilestoneFolder {
	private static Faker faker = new Faker();
	private static Random random = new Random();

	@DataProvider(name = "TestDataForGetAllMilestones")
	public Object[][] testDataForGetAllMilestones() {
		int randomIndexForProjectId = random.nextInt(ProjectFolderAPITestCases.projectIds.size());
		int randomProjectId = ProjectFolderAPITestCases.projectIds.get(randomIndexForProjectId);

		return new Object[][] { { faker.number().numberBetween(50, 100) }, { randomProjectId } };
	}
}

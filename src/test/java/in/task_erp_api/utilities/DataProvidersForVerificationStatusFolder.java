package in.task_erp_api.utilities;

import java.util.Random;

import org.testng.annotations.DataProvider;

import com.github.javafaker.Faker;

import in.task_erp_api.testcases.*;

public class DataProvidersForVerificationStatusFolder {
	private static Faker faker = new Faker();
	private static Random random = new Random();

	@DataProvider(name = "TestDataForAddVerificationStatus")
	public Object[][] testDataForAddVerificationStatus() {
		int randomIndexForVerificationStatusName = random
				.nextInt(VerificationStatusFolderAPITestCases.verificationStatuses.size());
		String randomVerificationStatusName = VerificationStatusFolderAPITestCases.verificationStatuses
				.get(randomIndexForVerificationStatusName);

		int randomIndexForVerificationLevel = random
				.nextInt(VerificationStatusFolderAPITestCases.verificationLevels.size());
		int randomVerificationLevel = VerificationStatusFolderAPITestCases.verificationLevels
				.get(randomIndexForVerificationLevel);

		int randomIndexForVerificationColor = random
				.nextInt(VerificationStatusFolderAPITestCases.verificationColors.size());
		String randomVerificationColor = VerificationStatusFolderAPITestCases.verificationColors
				.get(randomIndexForVerificationColor);

		int randomIndexForVerificationColorCode = random
				.nextInt(VerificationStatusFolderAPITestCases.verificationColorCodes.size());
		String randomVerificationColorCode = VerificationStatusFolderAPITestCases.verificationColorCodes
				.get(randomIndexForVerificationColorCode);

		return new Object[][] {
				{ faker.food().fruit(), faker.number().numberBetween(10, 20), faker.color().name(),
						faker.color().hex(true) },
				{ faker.food().fruit(), randomVerificationLevel, faker.color().name(), faker.color().hex(true) },
				{ faker.food().fruit(), faker.number().numberBetween(10, 20), "", faker.color().hex(true) },
				{ faker.food().fruit(), faker.number().numberBetween(10, 20), randomVerificationColor,
						faker.color().hex(true) },
				{ faker.food().fruit(), faker.number().numberBetween(10, 20), faker.color().name(), "" },
				{ faker.food().fruit(), faker.number().numberBetween(10, 20), faker.color().name(),
						randomVerificationColorCode },
				{ "", faker.number().numberBetween(10, 20), faker.color().name(), faker.color().hex(true) },
				{ randomVerificationStatusName, faker.number().numberBetween(10, 20), faker.color().name(),
						faker.color().hex(true) } };
	}

	@DataProvider(name = "TestDataForUpdateVerificationStatus")
	public Object[][] testDataForUpdateVerificationStatus() {
		int randomIndexForVerificationStatusName = random
				.nextInt(VerificationStatusFolderAPITestCases.verificationStatuses.size());
		String randomVerificationStatusName = VerificationStatusFolderAPITestCases.verificationStatuses
				.get(randomIndexForVerificationStatusName);

		int randomIndexForVerificationLevel = random
				.nextInt(VerificationStatusFolderAPITestCases.verificationLevels.size());
		int randomVerificationLevel = VerificationStatusFolderAPITestCases.verificationLevels
				.get(randomIndexForVerificationLevel);

		int randomIndexForVerificationColor = random
				.nextInt(VerificationStatusFolderAPITestCases.verificationColors.size());
		String randomVerificationColor = VerificationStatusFolderAPITestCases.verificationColors
				.get(randomIndexForVerificationColor);

		int randomIndexForVerificationColorCode = random
				.nextInt(VerificationStatusFolderAPITestCases.verificationColorCodes.size());
		String randomVerificationColorCode = VerificationStatusFolderAPITestCases.verificationColorCodes
				.get(randomIndexForVerificationColorCode);

		return new Object[][] {
				{ VerificationStatusFolderAPITestCases.newCreatedVerificationStatusId, faker.food().fruit(),
						faker.number().numberBetween(10, 20), faker.color().name(), faker.color().hex(true) },
				{ VerificationStatusFolderAPITestCases.newCreatedVerificationStatusId, faker.food().fruit(),
						randomVerificationLevel, faker.color().name(), faker.color().hex(true) },
				{ VerificationStatusFolderAPITestCases.newCreatedVerificationStatusId, "",
						faker.number().numberBetween(10, 20), faker.color().name(), faker.color().hex(true) },
				{ VerificationStatusFolderAPITestCases.newCreatedVerificationStatusId, randomVerificationStatusName,
						faker.number().numberBetween(10, 20), faker.color().name(), faker.color().hex(true) },
				{ VerificationStatusFolderAPITestCases.newCreatedVerificationStatusId, faker.food().fruit(),
						faker.number().numberBetween(10, 20), "", faker.color().hex(true) },
				{ VerificationStatusFolderAPITestCases.newCreatedVerificationStatusId, faker.food().fruit(),
						faker.number().numberBetween(10, 20), randomVerificationColor, faker.color().hex(true) },
				{ VerificationStatusFolderAPITestCases.newCreatedVerificationStatusId, faker.food().fruit(),
						faker.number().numberBetween(10, 20), faker.color().name(), "" },
				{ VerificationStatusFolderAPITestCases.newCreatedVerificationStatusId, faker.food().fruit(),
						faker.number().numberBetween(10, 20), faker.color().name(), randomVerificationColorCode },
				{ faker.number().numberBetween(VerificationStatusFolderAPITestCases.newCreatedVerificationStatusId + 10,
						VerificationStatusFolderAPITestCases.newCreatedVerificationStatusId + 50), faker.food().fruit(),
						faker.number().numberBetween(10, 20), faker.color().name(), faker.color().hex(true) } };
	}

	@DataProvider(name = "TestDataForDeleteVerificationStatus")
	public Object[][] testDataForDeleteVerificationStatus() {
		int randomIndexForVerificationStatusId = random
				.nextInt(VerificationStatusFolderAPITestCases.verificationStatusIds.size());
		int randomVerificationStatusId = VerificationStatusFolderAPITestCases.verificationStatusIds
				.get(randomIndexForVerificationStatusId);

		return new Object[][] { { faker.number().numberBetween(50, 100) },
				{ VerificationStatusFolderAPITestCases.newCreatedVerificationStatusId },
				{ randomVerificationStatusId } };
	}
}

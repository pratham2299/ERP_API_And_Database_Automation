package in.biencaps.erp.api.utilities;

import java.util.*;
import org.testng.annotations.*;

import in.biencaps.erp.api.testcases.*;

public class DataProvidersForPriorityFolder {
	private static Random random = new Random();

	@DataProvider(name = "TestDataForAddPriority")
	public Object[][] testDataForAddPriority() {
		int validPriorityLevel = DataGeneratorForAPI.generateFakeNumberWithRange(10, 15);

		int randomIndexForPriorityName = random.nextInt(PriorityFolderAPITestCases.priorities.size());
		String randomPriorityName = PriorityFolderAPITestCases.priorities.get(randomIndexForPriorityName);

		int randomIndexForPriorityLevel = random.nextInt(PriorityFolderAPITestCases.priorityLevels.size());
		int randomPriorityLevel = PriorityFolderAPITestCases.priorityLevels.get(randomIndexForPriorityLevel);

		int randomIndexForPriorityColor = random.nextInt(PriorityFolderAPITestCases.priorityColors.size());
		String randomPriorityColor = PriorityFolderAPITestCases.priorityColors.get(randomIndexForPriorityColor);

		int randomIndexForPriorityColorCode = random.nextInt(PriorityFolderAPITestCases.priorityColorCodes.size());
		String randomPriorityColorCode = PriorityFolderAPITestCases.priorityColorCodes
				.get(randomIndexForPriorityColorCode);

		return new Object[][] {
				// Entering valid data for all fields
				{ DataGeneratorForAPI.generateFakePriority(), validPriorityLevel, DataGeneratorForAPI.generateFakeColor(),
						DataGeneratorForAPI.generateFakeColorCode() },
				// Entering empty string for only priority field
				{ "", validPriorityLevel, DataGeneratorForAPI.generateFakeColor(), DataGeneratorForAPI.generateFakeColorCode() },
				// Entering already exist priority for only priority field
				{ randomPriorityName, validPriorityLevel, DataGeneratorForAPI.generateFakeColor(),
						DataGeneratorForAPI.generateFakeColorCode() },
				// Entering already exist priorityLevel for only priorityLevel field
				{ DataGeneratorForAPI.generateFakePriority(), randomPriorityLevel, DataGeneratorForAPI.generateFakeColor(),
						DataGeneratorForAPI.generateFakeColorCode() },
				// Entering empty string for only priorityColor field
				{ DataGeneratorForAPI.generateFakePriority(), validPriorityLevel, "",
						DataGeneratorForAPI.generateFakeColorCode() },
				// Entering already exist priorityColor for only priorityColor field
				{ DataGeneratorForAPI.generateFakePriority(), validPriorityLevel, randomPriorityColor,
						DataGeneratorForAPI.generateFakeColorCode() },
				// Entering empty string for only priorityColorCode field
				{ DataGeneratorForAPI.generateFakePriority(), validPriorityLevel, DataGeneratorForAPI.generateFakeColor(), "" },
				// Entering already exist priorityColorCode for only priorityColorCode field
				{ DataGeneratorForAPI.generateFakePriority(), validPriorityLevel, DataGeneratorForAPI.generateFakeColor(),
						randomPriorityColorCode }, };
	}

	@DataProvider(name = "TestDataForUpdatePriority")
	public Object[][] testDataForUpdatePriority() {
		int validPriorityLevel = DataGeneratorForAPI.generateFakeNumberWithRange(16, 20);

		int invalidPriorityId = DataGeneratorForAPI.generateFakeNumberWithRange(
				PriorityFolderAPITestCases.newCreatedPriorityId + 10,
				PriorityFolderAPITestCases.newCreatedPriorityId + 50);

		int randomIndexForPriorityName = random.nextInt(PriorityFolderAPITestCases.priorities.size());
		String randomPriorityName = PriorityFolderAPITestCases.priorities.get(randomIndexForPriorityName);

		int randomIndexForPriorityLevel = random.nextInt(PriorityFolderAPITestCases.priorityLevels.size());
		int randomPriorityLevel = PriorityFolderAPITestCases.priorityLevels.get(randomIndexForPriorityLevel);

		int randomIndexForPriorityColor = random.nextInt(PriorityFolderAPITestCases.priorityColors.size());
		String randomPriorityColor = PriorityFolderAPITestCases.priorityColors.get(randomIndexForPriorityColor);

		int randomIndexForPriorityColorCode = random.nextInt(PriorityFolderAPITestCases.priorityColorCodes.size());
		String randomPriorityColorCode = PriorityFolderAPITestCases.priorityColorCodes
				.get(randomIndexForPriorityColorCode);

		return new Object[][] {
				// Entering valid data for all fields
				{ PriorityFolderAPITestCases.newCreatedPriorityId, DataGeneratorForAPI.generateFakePriority(),
						validPriorityLevel, DataGeneratorForAPI.generateFakeColor(), DataGeneratorForAPI.generateFakeColorCode() },
				// Entering invalid priorityId for only priorityId field
				{ invalidPriorityId, DataGeneratorForAPI.generateFakePriority(), validPriorityLevel, DataGeneratorForAPI.generateFakeColor(),
						DataGeneratorForAPI.generateFakeColorCode() },
				// Entering empty string for only priority field
				{ PriorityFolderAPITestCases.newCreatedPriorityId, "", validPriorityLevel, DataGeneratorForAPI.generateFakeColor(),
						DataGeneratorForAPI.generateFakeColorCode() },
				// Entering already exist priority for only priority field
				{ PriorityFolderAPITestCases.newCreatedPriorityId, randomPriorityName, validPriorityLevel,
						DataGeneratorForAPI.generateFakeColor(), DataGeneratorForAPI.generateFakeColorCode() },
				// Entering already exist priorityLevel for only priorityLevel field
				{ PriorityFolderAPITestCases.newCreatedPriorityId, DataGeneratorForAPI.generateFakePriority(),
						randomPriorityLevel, DataGeneratorForAPI.generateFakeColor(), DataGeneratorForAPI.generateFakeColorCode() },
				// Entering empty string for only priorityColor field
				{ PriorityFolderAPITestCases.newCreatedPriorityId, DataGeneratorForAPI.generateFakePriority(),
						validPriorityLevel, "", DataGeneratorForAPI.generateFakeColorCode() },
				// Entering already exist priorityColor for only priorityColor field
				{ PriorityFolderAPITestCases.newCreatedPriorityId, DataGeneratorForAPI.generateFakePriority(),
						validPriorityLevel, randomPriorityColor, DataGeneratorForAPI.generateFakeColorCode() },
				// Entering empty string for only priorityColorCode field
				{ PriorityFolderAPITestCases.newCreatedPriorityId, DataGeneratorForAPI.generateFakePriority(),
						validPriorityLevel, DataGeneratorForAPI.generateFakeColor(), "" },
				// Entering already exist priorityColorCode for only priorityColorCode field
				{ PriorityFolderAPITestCases.newCreatedPriorityId, DataGeneratorForAPI.generateFakePriority(),
						validPriorityLevel, DataGeneratorForAPI.generateFakeColor(), randomPriorityColorCode } };
	}

	@DataProvider(name = "TestDataForDeletePriority")
	public Object[][] testDataForDeletePriority() {
		int randomIndexForPriorityName = random.nextInt(PriorityFolderAPITestCases.priorities.size());
		String randomPriorityName = PriorityFolderAPITestCases.priorities.get(randomIndexForPriorityName);

		return new Object[][] {
				// Entering invalid priority for only priorityName field
				{ DataGeneratorForAPI.generateFakePriority() },
				// Entering valid priority for only priority field
				{ PriorityFolderAPITestCases.newCreatedPriority },
				// Entering already mapped priority for priorityName field
				{ randomPriorityName } };
	}
}

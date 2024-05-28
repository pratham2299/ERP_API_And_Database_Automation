package in.biencaps.erp.api.utilities;

import java.util.*;
import org.testng.annotations.*;

import in.biencaps.erp.api.testcases.*;

public class DataProvidersForPriorityFolder {
	private static Random random = new Random();

	@DataProvider(name = "TestDataForAddPriority")
	public Object[][] testDataForAddPriority() {
		int validPriorityLevel = DataGeneratorForAPI.generateFakeNumberWithRange(10, 20);
		String validPriorityColor = DataGeneratorForAPI.generateFakeColor();
		String validPriorityColorCode = DataGeneratorForAPI.generateFakeColorCode();

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
				{ DataGeneratorForAPI.generateFakePriority(), validPriorityLevel, validPriorityColor,
						validPriorityColorCode },
				// Entering empty string for only priority field
				{ "", validPriorityLevel, validPriorityColor, validPriorityColorCode },
				// Entering already exist priority for only priority field
				{ randomPriorityName, validPriorityLevel, validPriorityColor, validPriorityColorCode },
				// Entering already exist priorityLevel for only priorityLevel field
				{ DataGeneratorForAPI.generateFakePriority(), randomPriorityLevel, validPriorityColor,
						validPriorityColorCode },
				// Entering empty string for only priorityColor field
				{ DataGeneratorForAPI.generateFakePriority(), validPriorityLevel, "", validPriorityColorCode },
				// Entering already exist priorityColor for only priorityColor field
				{ DataGeneratorForAPI.generateFakePriority(), validPriorityLevel, randomPriorityColor,
						validPriorityColorCode },
				// Entering empty string for only priorityColorCode field
				{ DataGeneratorForAPI.generateFakePriority(), validPriorityLevel, validPriorityColor, "" },
				// Entering already exist priorityColorCode for only priorityColorCode field
				{ DataGeneratorForAPI.generateFakePriority(), validPriorityLevel, validPriorityColor,
						randomPriorityColorCode }, };
	}

	@DataProvider(name = "TestDataForUpdatePriority")
	public Object[][] testDataForUpdatePriority() {
		int validPriorityLevel = DataGeneratorForAPI.generateFakeNumberWithRange(10, 20);
		String validPriorityColor = DataGeneratorForAPI.generateFakeColor();
		String validPriorityColorCode = DataGeneratorForAPI.generateFakeColorCode();
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
						validPriorityLevel, validPriorityColor, validPriorityColorCode },
				// Entering invalid priorityId for only priorityId field
				{ invalidPriorityId, DataGeneratorForAPI.generateFakePriority(), validPriorityLevel, validPriorityColor,
						validPriorityColorCode },
				// Entering empty string for only priority field
				{ PriorityFolderAPITestCases.newCreatedPriorityId, "", validPriorityLevel, validPriorityColor,
						validPriorityColorCode },
				// Entering already exist priority for only priority field
				{ PriorityFolderAPITestCases.newCreatedPriorityId, randomPriorityName, validPriorityLevel,
						validPriorityColor, validPriorityColorCode },
				// Entering already exist priorityLevel for only priorityLevel field
				{ PriorityFolderAPITestCases.newCreatedPriorityId, DataGeneratorForAPI.generateFakePriority(),
						randomPriorityLevel, validPriorityColor, validPriorityColorCode },
				// Entering empty string for only priorityColor field
				{ PriorityFolderAPITestCases.newCreatedPriorityId, DataGeneratorForAPI.generateFakePriority(),
						validPriorityLevel, "", validPriorityColorCode },
				// Entering already exist priorityColor for only priorityColor field
				{ PriorityFolderAPITestCases.newCreatedPriorityId, DataGeneratorForAPI.generateFakePriority(),
						validPriorityLevel, randomPriorityColor, validPriorityColorCode },
				// Entering empty string for only priorityColorCode field
				{ PriorityFolderAPITestCases.newCreatedPriorityId, DataGeneratorForAPI.generateFakePriority(),
						validPriorityLevel, validPriorityColor, "" },
				// Entering already exist priorityColorCode for only priorityColorCode field
				{ PriorityFolderAPITestCases.newCreatedPriorityId, DataGeneratorForAPI.generateFakePriority(),
						validPriorityLevel, validPriorityColor, randomPriorityColorCode } };
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

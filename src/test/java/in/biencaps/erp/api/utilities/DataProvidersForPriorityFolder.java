package in.biencaps.erp.api.utilities;

import java.util.*;
import org.testng.annotations.*;

import in.biencaps.erp.api.testcases.*;

public class DataProvidersForPriorityFolder {
	private static Random random = new Random();

	@DataProvider(name = "TestDataForAddPriority")
	public Object[][] testDataForAddPriority() {
		String fakePriority = DataGeneratorForAPI.generateFakePriority();
		int fakePriorityLevel = DataGeneratorForAPI.generateFakeNumberWithRange(10, 20);
		String fakePriorityColor = DataGeneratorForAPI.generateFakeColor();
		String fakePriorityColorCode = DataGeneratorForAPI.generateFakeColorCode();

		int randomIndexForPriorityName = random.nextInt(PriorityFolderAPITestCases.priorities.size());
		String randomPriorityName = PriorityFolderAPITestCases.priorities.get(randomIndexForPriorityName);

		int randomIndexForPriorityLevel = random.nextInt(PriorityFolderAPITestCases.priorityLevels.size());
		int randomPriorityLevel = PriorityFolderAPITestCases.priorityLevels.get(randomIndexForPriorityLevel);

		int randomIndexForPriorityColor = random.nextInt(PriorityFolderAPITestCases.priorityColors.size());
		String randomPriorityColor = PriorityFolderAPITestCases.priorityColors.get(randomIndexForPriorityColor);

		int randomIndexForPriorityColorCode = random.nextInt(PriorityFolderAPITestCases.priorityColorCodes.size());
		String randomPriorityColorCode = PriorityFolderAPITestCases.priorityColorCodes
				.get(randomIndexForPriorityColorCode);

		return new Object[][] { { fakePriority, fakePriorityLevel, fakePriorityColor, fakePriorityColorCode },
				{ fakePriority, randomPriorityLevel, fakePriorityColor, fakePriorityColorCode },
				{ fakePriority, fakePriorityLevel, "", fakePriorityColorCode },
				{ fakePriority, fakePriorityLevel, randomPriorityColor, fakePriorityColorCode },
				{ fakePriority, fakePriorityLevel, fakePriorityColor, "" },
				{ fakePriority, fakePriorityLevel, fakePriorityColor, randomPriorityColorCode },
				{ "", fakePriorityLevel, fakePriorityColor, fakePriorityColorCode },
				{ randomPriorityName, fakePriorityLevel, fakePriorityColor, fakePriorityColorCode } };
	}

	@DataProvider(name = "TestDataForUpdatePriority")
	public Object[][] testDataForUpdatePriority() {
		String fakePriority = DataGeneratorForAPI.generateFakePriority();
		int fakePriorityLevel = DataGeneratorForAPI.generateFakeNumberWithRange(10, 20);
		String fakePriorityColor = DataGeneratorForAPI.generateFakeColor();
		String fakePriorityColorCode = DataGeneratorForAPI.generateFakeColorCode();
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
				{ PriorityFolderAPITestCases.newCreatedPriorityId, fakePriority, fakePriorityLevel, fakePriorityColor,
						fakePriorityColorCode },
				{ PriorityFolderAPITestCases.newCreatedPriorityId, fakePriority, randomPriorityLevel, fakePriorityColor,
						fakePriorityColorCode },
				{ PriorityFolderAPITestCases.newCreatedPriorityId, "", fakePriorityLevel, fakePriorityColor,
						fakePriorityColorCode },
				{ PriorityFolderAPITestCases.newCreatedPriorityId, randomPriorityName, fakePriorityLevel,
						fakePriorityColor, fakePriorityColorCode },
				{ PriorityFolderAPITestCases.newCreatedPriorityId, fakePriority, fakePriorityLevel, "",
						fakePriorityColorCode },
				{ PriorityFolderAPITestCases.newCreatedPriorityId, fakePriority, fakePriorityLevel, randomPriorityColor,
						fakePriorityColorCode },
				{ PriorityFolderAPITestCases.newCreatedPriorityId, fakePriority, fakePriorityLevel, fakePriorityColor,
						"" },
				{ PriorityFolderAPITestCases.newCreatedPriorityId, fakePriority, fakePriorityLevel, fakePriorityColor,
						randomPriorityColorCode },
				{ invalidPriorityId, fakePriority, fakePriorityLevel, fakePriorityColor, fakePriorityColorCode } };
	}

	@DataProvider(name = "TestDataForDeletePriority")
	public Object[][] testDataForDeletePriority() {
		String fakePriority = DataGeneratorForAPI.generateFakePriority();

		int randomIndexForPriorityName = random.nextInt(PriorityFolderAPITestCases.priorities.size());
		String randomPriorityName = PriorityFolderAPITestCases.priorities.get(randomIndexForPriorityName);

		return new Object[][] { { fakePriority }, { PriorityFolderAPITestCases.newCreatedPriority },
				{ randomPriorityName } };
	}
}

package in.biencaps.erp.api.utilities;

import java.util.*;
import org.testng.annotations.*;

import in.biencaps.erp.api.testcases.*;

public class DataProvidersForStatusFolder {
	private static Random random = new Random();

	@DataProvider(name = "TestDataForAddStatus")
	public Object[][] testDataForAddStatus() {
		int validStatusLevel = DataGeneratorForAPI.generateFakeNumberWithRange(10, 20);
		String validStatusColor = DataGeneratorForAPI.generateFakeColor();
		String validStatusColorCode = DataGeneratorForAPI.generateFakeColorCode();

		int randomIndexForStatusName = random.nextInt(StatusFolderAPITestCases.statuses.size());
		String randomStatusName = StatusFolderAPITestCases.statuses.get(randomIndexForStatusName);

		int randomIndexForStatusLevel = random.nextInt(StatusFolderAPITestCases.statusLevels.size());
		int randomStatusLevel = StatusFolderAPITestCases.statusLevels.get(randomIndexForStatusLevel);

		int randomIndexForStatusColor = random.nextInt(StatusFolderAPITestCases.statusColors.size());
		String randomStatusColor = StatusFolderAPITestCases.statusColors.get(randomIndexForStatusColor);

		int randomIndexForStatusColorCode = random.nextInt(StatusFolderAPITestCases.statusColorCodes.size());
		String randomStatusColorCode = StatusFolderAPITestCases.statusColorCodes.get(randomIndexForStatusColorCode);

		return new Object[][] {
				// Entering valid data for all fields
				{ DataGeneratorForAPI.generateFakeStatus(), validStatusLevel, validStatusColor, validStatusColorCode },
				// Entering already exist status for only status field
				{ randomStatusName, validStatusLevel, validStatusColor, validStatusColorCode },
				// Entering empty string for only status field
				{ "", validStatusLevel, validStatusColor, validStatusColorCode },
				// Entering already exist statusLevel for only statusLevel field
				{ DataGeneratorForAPI.generateFakeStatus(), randomStatusLevel, validStatusColor, validStatusColorCode },
				// Entering empty string for only statusColor field
				{ DataGeneratorForAPI.generateFakeStatus(), validStatusLevel, "", validStatusColorCode },
				// Entering already exist statusColor for only statusColor field
				{ DataGeneratorForAPI.generateFakeStatus(), validStatusLevel, randomStatusColor, validStatusColorCode },
				// Entering empty string for only statusColorCode field
				{ DataGeneratorForAPI.generateFakeStatus(), validStatusLevel, validStatusColor, "" },
				// Entering already exist statusColorCode for only statusColorCode field
				{ DataGeneratorForAPI.generateFakeStatus(), validStatusLevel, validStatusColor,
						randomStatusColorCode } };
	}

	@DataProvider(name = "TestDataForUpdateStatus")
	public Object[][] testDataForUpdateStatus() {
		int validStatusLevel = DataGeneratorForAPI.generateFakeNumberWithRange(10, 20);
		String validStatusColor = DataGeneratorForAPI.generateFakeColor();
		String validStatusColorCode = DataGeneratorForAPI.generateFakeColorCode();
		int invalidStatusId = DataGeneratorForAPI.generateFakeNumberWithRange(
				StatusFolderAPITestCases.newCreatedStatusId + 10, StatusFolderAPITestCases.newCreatedStatusId + 50);

		int randomIndexForStatusName = random.nextInt(StatusFolderAPITestCases.statuses.size());
		String randomStatusName = StatusFolderAPITestCases.statuses.get(randomIndexForStatusName);

		int randomIndexForStatusLevel = random.nextInt(StatusFolderAPITestCases.statusLevels.size());
		int randomStatusLevel = StatusFolderAPITestCases.statusLevels.get(randomIndexForStatusLevel);

		int randomIndexForStatusColor = random.nextInt(StatusFolderAPITestCases.statusColors.size());
		String randomStatusColor = StatusFolderAPITestCases.statusColors.get(randomIndexForStatusColor);

		int randomIndexForStatusColorCode = random.nextInt(StatusFolderAPITestCases.statusColorCodes.size());
		String randomStatusColorCode = StatusFolderAPITestCases.statusColorCodes.get(randomIndexForStatusColorCode);

		return new Object[][] {
				// Entering valid data for all fields
				{ StatusFolderAPITestCases.newCreatedStatusId, DataGeneratorForAPI.generateFakeStatus(),
						validStatusLevel, validStatusColor, validStatusColorCode },
				// Entering invalid statusId for only statusId field
				{ invalidStatusId, DataGeneratorForAPI.generateFakeStatus(), validStatusLevel, validStatusColor,
						validStatusColorCode },
				// Entering already exist status level for only statusLevel field
				{ StatusFolderAPITestCases.newCreatedStatusId, DataGeneratorForAPI.generateFakeStatus(),
						randomStatusLevel, validStatusColor, validStatusColorCode },
				// Entering empty string for only status field
				{ StatusFolderAPITestCases.newCreatedStatusId, "", validStatusLevel, validStatusColor,
						validStatusColorCode },
				// Entering already exist status for only status field
				{ StatusFolderAPITestCases.newCreatedStatusId, randomStatusName, validStatusLevel, validStatusColor,
						validStatusColorCode },
				// Entering empty string for only statusColor field
				{ StatusFolderAPITestCases.newCreatedStatusId, DataGeneratorForAPI.generateFakeStatus(),
						validStatusLevel, "", validStatusColorCode },
				// Entering already exist statusColor for only statusColor field
				{ StatusFolderAPITestCases.newCreatedStatusId, DataGeneratorForAPI.generateFakeStatus(),
						validStatusLevel, randomStatusColor, validStatusColorCode },
				// Entering empty string for only statusColorCode field
				{ StatusFolderAPITestCases.newCreatedStatusId, DataGeneratorForAPI.generateFakeStatus(),
						validStatusLevel, validStatusColor, "" },
				// Entering already exist statusColorCode for only statusColorCode field
				{ StatusFolderAPITestCases.newCreatedStatusId, DataGeneratorForAPI.generateFakeStatus(),
						validStatusLevel, validStatusColor, randomStatusColorCode } };
	}

	@DataProvider(name = "TestDataForDeleteStatus")
	public Object[][] testDataForDeleteStatus() {
		int randomIndexForStatusName = random.nextInt(StatusFolderAPITestCases.statuses.size());
		String randomStatusName = StatusFolderAPITestCases.statuses.get(randomIndexForStatusName);

		return new Object[][] {
				// Entering invalid status for only statusName field
				{ DataGeneratorForAPI.generateFakeStatus() },
				// Entering valid status for statusName field
				{ StatusFolderAPITestCases.newCreatedStatus },
				// Entering already mapped status for statusName field
				{ randomStatusName } };
	}
}

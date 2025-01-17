package in.biencaps.erp.api.utilities;

import java.util.*;
import org.testng.annotations.*;

import in.biencaps.erp.api.testcases.*;

public class DataProvidersForStatusFolder {
	private static Random random = new Random();

	@DataProvider(name = "TestDataForAddStatus")
	public Object[][] testDataForAddStatus() {
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
				{ DataGeneratorForAPI.generateFakeStatus(), DataGeneratorForAPI.generateFakeNumberWithRange(10, 15),
						DataGeneratorForAPI.generateFakeColor(), DataGeneratorForAPI.generateFakeColorCode() },
				// Entering already exist status for only status field
				{ randomStatusName, DataGeneratorForAPI.generateFakeNumberWithRange(10, 15),
						DataGeneratorForAPI.generateFakeColor(), DataGeneratorForAPI.generateFakeColorCode() },
				// Entering empty string for only status field
				{ "", DataGeneratorForAPI.generateFakeNumberWithRange(10, 15), DataGeneratorForAPI.generateFakeColor(),
						DataGeneratorForAPI.generateFakeColorCode() },
				// Entering already exist statusLevel for only statusLevel field
				{ DataGeneratorForAPI.generateFakeStatus(), randomStatusLevel, DataGeneratorForAPI.generateFakeColor(),
						DataGeneratorForAPI.generateFakeColorCode() },
				// Entering empty string for only statusColor field
				{ DataGeneratorForAPI.generateFakeStatus(), DataGeneratorForAPI.generateFakeNumberWithRange(10, 15), "",
						DataGeneratorForAPI.generateFakeColorCode() },
				// Entering already exist statusColor for only statusColor field
				{ DataGeneratorForAPI.generateFakeStatus(), DataGeneratorForAPI.generateFakeNumberWithRange(10, 15),
						randomStatusColor, DataGeneratorForAPI.generateFakeColorCode() },
				// Entering empty string for only statusColorCode field
				{ DataGeneratorForAPI.generateFakeStatus(), DataGeneratorForAPI.generateFakeNumberWithRange(10, 15),
						DataGeneratorForAPI.generateFakeColor(), "" },
				// Entering already exist statusColorCode for only statusColorCode field
				{ DataGeneratorForAPI.generateFakeStatus(), DataGeneratorForAPI.generateFakeNumberWithRange(10, 15),
						DataGeneratorForAPI.generateFakeColor(), randomStatusColorCode } };
	}

	@DataProvider(name = "TestDataForUpdateStatus")
	public Object[][] testDataForUpdateStatus() {
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
						DataGeneratorForAPI.generateFakeNumberWithRange(16, 20),
						DataGeneratorForAPI.generateFakeColor(), DataGeneratorForAPI.generateFakeColorCode() },
				// Entering invalid statusId for only statusId field
				{ invalidStatusId, DataGeneratorForAPI.generateFakeStatus(),
						DataGeneratorForAPI.generateFakeNumberWithRange(16, 20),
						DataGeneratorForAPI.generateFakeColor(), DataGeneratorForAPI.generateFakeColorCode() },
				// Entering already exist status level for only statusLevel field
				{ StatusFolderAPITestCases.newCreatedStatusId, DataGeneratorForAPI.generateFakeStatus(),
						randomStatusLevel, DataGeneratorForAPI.generateFakeColor(),
						DataGeneratorForAPI.generateFakeColorCode() },
				// Entering empty string for only status field
				{ StatusFolderAPITestCases.newCreatedStatusId, "",
						DataGeneratorForAPI.generateFakeNumberWithRange(16, 20),
						DataGeneratorForAPI.generateFakeColor(), DataGeneratorForAPI.generateFakeColorCode() },
				// Entering already exist status for only status field
				{ StatusFolderAPITestCases.newCreatedStatusId, randomStatusName,
						DataGeneratorForAPI.generateFakeNumberWithRange(16, 20),
						DataGeneratorForAPI.generateFakeColor(), DataGeneratorForAPI.generateFakeColorCode() },
				// Entering empty string for only statusColor field
				{ StatusFolderAPITestCases.newCreatedStatusId, DataGeneratorForAPI.generateFakeStatus(),
						DataGeneratorForAPI.generateFakeNumberWithRange(16, 20), "",
						DataGeneratorForAPI.generateFakeColorCode() },
				// Entering already exist statusColor for only statusColor field
				{ StatusFolderAPITestCases.newCreatedStatusId, DataGeneratorForAPI.generateFakeStatus(),
						DataGeneratorForAPI.generateFakeNumberWithRange(16, 20), randomStatusColor,
						DataGeneratorForAPI.generateFakeColorCode() },
				// Entering empty string for only statusColorCode field
				{ StatusFolderAPITestCases.newCreatedStatusId, DataGeneratorForAPI.generateFakeStatus(),
						DataGeneratorForAPI.generateFakeNumberWithRange(16, 20),
						DataGeneratorForAPI.generateFakeColor(), "" },
				// Entering already exist statusColorCode for only statusColorCode field
				{ StatusFolderAPITestCases.newCreatedStatusId, DataGeneratorForAPI.generateFakeStatus(),
						DataGeneratorForAPI.generateFakeNumberWithRange(16, 20),
						DataGeneratorForAPI.generateFakeColor(), randomStatusColorCode } };
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

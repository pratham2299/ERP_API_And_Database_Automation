package in.biencaps.erp.api.utilities;

import java.util.*;
import org.testng.annotations.*;

import in.biencaps.erp.api.testcases.*;

public class DataProvidersForStatusFolder {
	private static Random random = new Random();

	@DataProvider(name = "TestDataForAddStatus")
	public Object[][] testDataForAddStatus() {
		String fakeStatus = DataGeneratorForAPI.generateFakeStatus();
		int fakeStatusLevel = DataGeneratorForAPI.generateFakeNumberWithRange(10, 20);
		String fakeStatusColor = DataGeneratorForAPI.generateFakeColor();
		String fakeStatusColorCode = DataGeneratorForAPI.generateFakeColorCode();

		int randomIndexForStatusName = random.nextInt(StatusFolderAPITestCases.statuses.size());
		String randomStatusName = StatusFolderAPITestCases.statuses.get(randomIndexForStatusName);

		int randomIndexForStatusLevel = random.nextInt(StatusFolderAPITestCases.statusLevels.size());
		int randomStatusLevel = StatusFolderAPITestCases.statusLevels.get(randomIndexForStatusLevel);

		int randomIndexForStatusColor = random.nextInt(StatusFolderAPITestCases.statusColors.size());
		String randomStatusColor = StatusFolderAPITestCases.statusColors.get(randomIndexForStatusColor);

		int randomIndexForStatusColorCode = random.nextInt(StatusFolderAPITestCases.statusColorCodes.size());
		String randomStatusColorCode = StatusFolderAPITestCases.statusColorCodes.get(randomIndexForStatusColorCode);

		return new Object[][] { { fakeStatus, fakeStatusLevel, fakeStatusColor, fakeStatusColorCode },
//				{ fakeStatus, randomStatusLevel, fakeStatusColor, fakeStatusColorCode },
				{ fakeStatus, fakeStatusLevel, "", fakeStatusColorCode },
				{ fakeStatus, fakeStatusLevel, randomStatusColor, fakeStatusColorCode },
				{ fakeStatus, fakeStatusLevel, fakeStatusColor, "" },
				{ fakeStatus, fakeStatusLevel, fakeStatusColor, randomStatusColorCode },
				{ "", fakeStatusLevel, fakeStatusColor, fakeStatusColorCode },
				{ randomStatusName, fakeStatusLevel, fakeStatusColor, fakeStatusColorCode } };
	}

	@DataProvider(name = "TestDataForUpdateStatus")
	public Object[][] testDataForUpdateStatus() {
		String fakeStatus = DataGeneratorForAPI.generateFakeStatus();
		int fakeStatusLevel = DataGeneratorForAPI.generateFakeNumberWithRange(10, 20);
		String fakeStatusColor = DataGeneratorForAPI.generateFakeColor();
		String fakeStatusColorCode = DataGeneratorForAPI.generateFakeColorCode();
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
				{ StatusFolderAPITestCases.newCreatedStatusId, fakeStatus, fakeStatusLevel, fakeStatusColor,
						fakeStatusColorCode },
				{ StatusFolderAPITestCases.newCreatedStatusId, fakeStatus, randomStatusLevel, fakeStatusColor,
						fakeStatusColorCode },
				{ StatusFolderAPITestCases.newCreatedStatusId, "", fakeStatusLevel, fakeStatusColor,
						fakeStatusColorCode },
				{ StatusFolderAPITestCases.newCreatedStatusId, randomStatusName, fakeStatusLevel, fakeStatusColor,
						fakeStatusColorCode },
				{ StatusFolderAPITestCases.newCreatedStatusId, fakeStatus, fakeStatusLevel, "", fakeStatusColorCode },
				{ StatusFolderAPITestCases.newCreatedStatusId, fakeStatus, fakeStatusLevel, randomStatusColor,
						fakeStatusColorCode },
				{ StatusFolderAPITestCases.newCreatedStatusId, fakeStatus, fakeStatusLevel, fakeStatusColor, "" },
				{ StatusFolderAPITestCases.newCreatedStatusId, fakeStatus, fakeStatusLevel, fakeStatusColor,
						randomStatusColorCode },
				{ invalidStatusId, fakeStatus, fakeStatusLevel, fakeStatusColor, fakeStatusColorCode } };
	}

	@DataProvider(name = "TestDataForDeleteStatus")
	public Object[][] testDataForDeleteStatus() {
		String fakeStatus = DataGeneratorForAPI.generateFakeStatus();

		int randomIndexForStatusName = random.nextInt(StatusFolderAPITestCases.statuses.size());
		String randomStatusName = StatusFolderAPITestCases.statuses.get(randomIndexForStatusName);

		return new Object[][] { { fakeStatus }, { StatusFolderAPITestCases.newCreatedStatus }, { randomStatusName } };
	}
}

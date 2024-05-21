package in.task_erp_api.utilities;

import java.util.*;
import org.testng.annotations.DataProvider;
import in.task_erp_api.testcases.*;

public class DataProvidersForVerificationStatusFolder {
	private static Random random = new Random();

	@DataProvider(name = "TestDataForAddVerificationStatus")
	public Object[][] testDataForAddVerificationStatus() {
		String fakeVerificationStatus = DataGeneratorForAPI.generateFakeStatus();
		int fakeVerificationLevel = DataGeneratorForAPI.generateFakeNumberWithRange(10, 20);
		String fakeVerificationColor = DataGeneratorForAPI.generateFakeColor();
		String fakeVerificationColorCode = DataGeneratorForAPI.generateFakeColorCode();

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
				{ fakeVerificationStatus, fakeVerificationLevel, fakeVerificationColor, fakeVerificationColorCode },
				{ fakeVerificationStatus, randomVerificationLevel, fakeVerificationColor, fakeVerificationColorCode },
				{ fakeVerificationStatus, fakeVerificationLevel, "", fakeVerificationColorCode },
				{ fakeVerificationStatus, fakeVerificationLevel, randomVerificationColor, fakeVerificationColorCode },
				{ fakeVerificationStatus, fakeVerificationLevel, fakeVerificationColor, "" },
				{ fakeVerificationStatus, fakeVerificationLevel, fakeVerificationColor, randomVerificationColorCode },
				{ "", fakeVerificationLevel, fakeVerificationColor, fakeVerificationColorCode },
				{ randomVerificationStatusName, fakeVerificationLevel, fakeVerificationColor,
						fakeVerificationColorCode } };
	}

	@DataProvider(name = "TestDataForUpdateVerificationStatus")
	public Object[][] testDataForUpdateVerificationStatus() {
		String fakeVerificationStatus = DataGeneratorForAPI.generateFakeStatus();
		int fakeVerificationLevel = DataGeneratorForAPI.generateFakeNumberWithRange(10, 20);
		String fakeVerificationColor = DataGeneratorForAPI.generateFakeColor();
		String fakeVerificationColorCode = DataGeneratorForAPI.generateFakeColorCode();

		int invalidVerificationStatusId = DataGeneratorForAPI.generateFakeNumberWithRange(
				VerificationStatusFolderAPITestCases.newCreatedVerificationStatusId + 10,
				VerificationStatusFolderAPITestCases.newCreatedVerificationStatusId + 50);

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
				{ VerificationStatusFolderAPITestCases.newCreatedVerificationStatusId, fakeVerificationStatus,
						fakeVerificationLevel, fakeVerificationColor, fakeVerificationColorCode },
				{ VerificationStatusFolderAPITestCases.newCreatedVerificationStatusId, fakeVerificationStatus,
						randomVerificationLevel, fakeVerificationColor, fakeVerificationColorCode },
				{ VerificationStatusFolderAPITestCases.newCreatedVerificationStatusId, "", fakeVerificationLevel,
						fakeVerificationColor, fakeVerificationColorCode },
				{ VerificationStatusFolderAPITestCases.newCreatedVerificationStatusId, randomVerificationStatusName,
						fakeVerificationLevel, fakeVerificationColor, fakeVerificationColorCode },
				{ VerificationStatusFolderAPITestCases.newCreatedVerificationStatusId, fakeVerificationStatus,
						fakeVerificationLevel, "", fakeVerificationColorCode },
				{ VerificationStatusFolderAPITestCases.newCreatedVerificationStatusId, fakeVerificationStatus,
						fakeVerificationLevel, randomVerificationColor, fakeVerificationColorCode },
				{ VerificationStatusFolderAPITestCases.newCreatedVerificationStatusId, fakeVerificationStatus,
						fakeVerificationLevel, fakeVerificationColor, "" },
				{ VerificationStatusFolderAPITestCases.newCreatedVerificationStatusId, fakeVerificationStatus,
						fakeVerificationLevel, fakeVerificationColor, randomVerificationColorCode },
				{ invalidVerificationStatusId, fakeVerificationStatus, fakeVerificationLevel, fakeVerificationColor,
						fakeVerificationColorCode } };
	}

	@DataProvider(name = "TestDataForDeleteVerificationStatus")
	public Object[][] testDataForDeleteVerificationStatus() {
		int invalidVerificationStatusId = DataGeneratorForAPI.generateFakeNumberWithRange(
				VerificationStatusFolderAPITestCases.newCreatedVerificationStatusId + 10,
				VerificationStatusFolderAPITestCases.newCreatedVerificationStatusId + 50);

//		int randomIndexForVerificationStatusId = random
//				.nextInt(VerificationStatusFolderAPITestCases.verificationStatusIds.size());
//		int randomVerificationStatusId = VerificationStatusFolderAPITestCases.verificationStatusIds
//				.get(randomIndexForVerificationStatusId);

		return new Object[][] { { invalidVerificationStatusId },
				{ VerificationStatusFolderAPITestCases.newCreatedVerificationStatusId }, { 3 } };
	}
}

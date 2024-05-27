package in.biencaps.erp.api.utilities;

import java.util.*;
import org.testng.annotations.DataProvider;

import in.biencaps.erp.api.testcases.*;

public class DataProvidersForVerificationFolder {
	private static Random random = new Random();

	@DataProvider(name = "TestDataForAddVerification")
	public Object[][] testDataForAddVerification() {
		String fakeVerification = DataGeneratorForAPI.generateFakeStatus();
		int fakeVerificationLevel = DataGeneratorForAPI.generateFakeNumberWithRange(10, 20);
		String fakeVerificationColor = DataGeneratorForAPI.generateFakeColor();
		String fakeVerificationColorCode = DataGeneratorForAPI.generateFakeColorCode();

		int randomIndexForVerificationName = random.nextInt(VerificationFolderAPITestCases.verifications.size());
		String randomVerificationName = VerificationFolderAPITestCases.verifications
				.get(randomIndexForVerificationName);

		int randomIndexForVerificationLevel = random.nextInt(VerificationFolderAPITestCases.verificationLevels.size());
		int randomVerificationLevel = VerificationFolderAPITestCases.verificationLevels
				.get(randomIndexForVerificationLevel);

		int randomIndexForVerificationColor = random.nextInt(VerificationFolderAPITestCases.verificationColors.size());
		String randomVerificationColor = VerificationFolderAPITestCases.verificationColors
				.get(randomIndexForVerificationColor);

		int randomIndexForVerificationColorCode = random
				.nextInt(VerificationFolderAPITestCases.verificationColorCodes.size());
		String randomVerificationColorCode = VerificationFolderAPITestCases.verificationColorCodes
				.get(randomIndexForVerificationColorCode);

		return new Object[][] {
				{ fakeVerification, fakeVerificationLevel, fakeVerificationColor, fakeVerificationColorCode },
				{ fakeVerification, randomVerificationLevel, fakeVerificationColor, fakeVerificationColorCode },
				{ fakeVerification, fakeVerificationLevel, "", fakeVerificationColorCode },
				{ fakeVerification, fakeVerificationLevel, randomVerificationColor, fakeVerificationColorCode },
				{ fakeVerification, fakeVerificationLevel, fakeVerificationColor, "" },
				{ fakeVerification, fakeVerificationLevel, fakeVerificationColor, randomVerificationColorCode },
				{ "", fakeVerificationLevel, fakeVerificationColor, fakeVerificationColorCode },
				{ randomVerificationName, fakeVerificationLevel, fakeVerificationColor, fakeVerificationColorCode } };
	}

	@DataProvider(name = "TestDataForUpdateVerification")
	public Object[][] testDataForUpdateVerification() {
		String fakeVerification = DataGeneratorForAPI.generateFakeStatus();
		int fakeVerificationLevel = DataGeneratorForAPI.generateFakeNumberWithRange(10, 20);
		String fakeVerificationColor = DataGeneratorForAPI.generateFakeColor();
		String fakeVerificationColorCode = DataGeneratorForAPI.generateFakeColorCode();

		int invalidVerificationId = DataGeneratorForAPI.generateFakeNumberWithRange(
				VerificationFolderAPITestCases.newCreatedVerificationId + 10,
				VerificationFolderAPITestCases.newCreatedVerificationId + 50);

		int randomIndexForVerificationName = random.nextInt(VerificationFolderAPITestCases.verifications.size());
		String randomVerificationName = VerificationFolderAPITestCases.verifications
				.get(randomIndexForVerificationName);

		int randomIndexForVerificationLevel = random.nextInt(VerificationFolderAPITestCases.verificationLevels.size());
		int randomVerificationLevel = VerificationFolderAPITestCases.verificationLevels
				.get(randomIndexForVerificationLevel);

		int randomIndexForVerificationColor = random.nextInt(VerificationFolderAPITestCases.verificationColors.size());
		String randomVerificationColor = VerificationFolderAPITestCases.verificationColors
				.get(randomIndexForVerificationColor);

		int randomIndexForVerificationColorCode = random
				.nextInt(VerificationFolderAPITestCases.verificationColorCodes.size());
		String randomVerificationColorCode = VerificationFolderAPITestCases.verificationColorCodes
				.get(randomIndexForVerificationColorCode);

		return new Object[][] {
				{ VerificationFolderAPITestCases.newCreatedVerificationId, fakeVerification, fakeVerificationLevel,
						fakeVerificationColor, fakeVerificationColorCode },
				{ VerificationFolderAPITestCases.newCreatedVerificationId, fakeVerification, randomVerificationLevel,
						fakeVerificationColor, fakeVerificationColorCode },
				{ VerificationFolderAPITestCases.newCreatedVerificationId, "", fakeVerificationLevel,
						fakeVerificationColor, fakeVerificationColorCode },
				{ VerificationFolderAPITestCases.newCreatedVerificationId, randomVerificationName,
						fakeVerificationLevel, fakeVerificationColor, fakeVerificationColorCode },
				{ VerificationFolderAPITestCases.newCreatedVerificationId, fakeVerification, fakeVerificationLevel, "",
						fakeVerificationColorCode },
				{ VerificationFolderAPITestCases.newCreatedVerificationId, fakeVerification, fakeVerificationLevel,
						randomVerificationColor, fakeVerificationColorCode },
				{ VerificationFolderAPITestCases.newCreatedVerificationId, fakeVerification, fakeVerificationLevel,
						fakeVerificationColor, "" },
				{ VerificationFolderAPITestCases.newCreatedVerificationId, fakeVerification, fakeVerificationLevel,
						fakeVerificationColor, randomVerificationColorCode },
				{ invalidVerificationId, fakeVerification, fakeVerificationLevel, fakeVerificationColor,
						fakeVerificationColorCode } };
	}

	@DataProvider(name = "TestDataForDeleteVerification")
	public Object[][] testDataForDeleteVerification() {
		int invalidVerificationId = DataGeneratorForAPI.generateFakeNumberWithRange(
				VerificationFolderAPITestCases.newCreatedVerificationId + 10,
				VerificationFolderAPITestCases.newCreatedVerificationId + 50);

//		int randomIndexForVerificationId = random
//				.nextInt(VerificationFolderAPITestCases.verificationIds.size());
//		int randomVerificationId = VerificationFolderAPITestCases.verificationIds
//				.get(randomIndexForVerificationId);

		return new Object[][] { { invalidVerificationId }, { VerificationFolderAPITestCases.newCreatedVerificationId },
				{ 3 } };
	}
}

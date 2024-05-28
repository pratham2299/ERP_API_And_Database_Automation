package in.biencaps.erp.api.utilities;

import java.util.*;
import org.testng.annotations.DataProvider;

import in.biencaps.erp.api.testcases.*;

public class DataProvidersForVerificationFolder {
	private static Random random = new Random();

	@DataProvider(name = "TestDataForAddVerification")
	public Object[][] testDataForAddVerification() {
		int validVerificationLevel = DataGeneratorForAPI.generateFakeNumberWithRange(10, 15);
		String validVerificationColor = DataGeneratorForAPI.generateFakeColor();
		String validVerificationColorCode = DataGeneratorForAPI.generateFakeColorCode();

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
				{ DataGeneratorForAPI.generateFakeStatus(), validVerificationLevel, validVerificationColor,
						validVerificationColorCode },
				{ "", validVerificationLevel, validVerificationColor, validVerificationColorCode },
				{ randomVerificationName, validVerificationLevel, validVerificationColor, validVerificationColorCode },
				{ DataGeneratorForAPI.generateFakeStatus(), randomVerificationLevel, validVerificationColor,
						validVerificationColorCode },
				{ DataGeneratorForAPI.generateFakeStatus(), validVerificationLevel, "", validVerificationColorCode },
				{ DataGeneratorForAPI.generateFakeStatus(), validVerificationLevel, randomVerificationColor,
						validVerificationColorCode },
				{ DataGeneratorForAPI.generateFakeStatus(), validVerificationLevel, validVerificationColor, "" },
				{ DataGeneratorForAPI.generateFakeStatus(), validVerificationLevel, validVerificationColor,
						randomVerificationColorCode } };
	}

	@DataProvider(name = "TestDataForUpdateVerification")
	public Object[][] testDataForUpdateVerification() {
		int validVerificationLevel = DataGeneratorForAPI.generateFakeNumberWithRange(16, 20);
		String validVerificationColor = DataGeneratorForAPI.generateFakeColor();
		String validVerificationColorCode = DataGeneratorForAPI.generateFakeColorCode();

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
				{ VerificationFolderAPITestCases.newCreatedVerificationId, DataGeneratorForAPI.generateFakeStatus(),
						validVerificationLevel, validVerificationColor, validVerificationColorCode },
				{ invalidVerificationId, DataGeneratorForAPI.generateFakeStatus(), validVerificationLevel,
						validVerificationColor, validVerificationColorCode },
				{ VerificationFolderAPITestCases.newCreatedVerificationId, DataGeneratorForAPI.generateFakeStatus(),
						randomVerificationLevel, validVerificationColor, validVerificationColorCode },
				{ VerificationFolderAPITestCases.newCreatedVerificationId, "", validVerificationLevel,
						validVerificationColor, validVerificationColorCode },
				{ VerificationFolderAPITestCases.newCreatedVerificationId, randomVerificationName,
						validVerificationLevel, validVerificationColor, validVerificationColorCode },
				{ VerificationFolderAPITestCases.newCreatedVerificationId, DataGeneratorForAPI.generateFakeStatus(),
						validVerificationLevel, "", validVerificationColorCode },
				{ VerificationFolderAPITestCases.newCreatedVerificationId, DataGeneratorForAPI.generateFakeStatus(),
						validVerificationLevel, randomVerificationColor, validVerificationColorCode },
				{ VerificationFolderAPITestCases.newCreatedVerificationId, DataGeneratorForAPI.generateFakeStatus(),
						validVerificationLevel, validVerificationColor, "" },
				{ VerificationFolderAPITestCases.newCreatedVerificationId, DataGeneratorForAPI.generateFakeStatus(),
						validVerificationLevel, validVerificationColor, randomVerificationColorCode } };
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

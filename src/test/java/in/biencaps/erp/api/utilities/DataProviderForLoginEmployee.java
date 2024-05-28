package in.biencaps.erp.api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviderForLoginEmployee {
	@DataProvider(name = "TestDataForLoginEmployee")
	public Object[][] testDataForLoginEmployee() {
		String validUserId = Constants.adminUserId;
		String validPassword = Constants.adminPassword;

		int user_id_last_digits = DataGeneratorForAPI.generateFakeNumberWithRange(100, 150);
		String invalidUserId = "INC0" + String.valueOf(user_id_last_digits);

		String invalidPassword = DataGeneratorForAPI.generateFakePassword();

		return new Object[][] { { invalidUserId, invalidPassword }, { validUserId, invalidPassword },
				{ invalidUserId, invalidPassword }, { validUserId, validPassword } };
	}
}

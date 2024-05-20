package in.task_erp_api.utilities;

import java.util.Random;

import org.testng.annotations.*;

import com.github.javafaker.Faker;

import in.task_erp_api.testcases.NotificationFolderAPITestCases;

public class DataProvidersForNotificationFolder {
	private Random random = new Random();
	private Faker faker = new Faker();

	@DataProvider(name = "TestDataForGetUnreadNotificationCount")
	public Object[][] testDataForGetUnreadNotificationCount() {
		int user_id_last_digits = faker.number().numberBetween(100, 150);
		String invalidUserId = "BIE0" + String.valueOf(user_id_last_digits);

		return new Object[][] { { "BIE018" }, { invalidUserId } };
	}

	@DataProvider(name = "TestDataForUpdateNotificationReadStatus")
	public Object[][] testDataForUpdateNotificationReadStatus() {
		System.out.println(NotificationFolderAPITestCases.notificationIds);
		int randomIndexForNotificationId = random.nextInt(NotificationFolderAPITestCases.notificationIds.size());
		String randomNotificationId = NotificationFolderAPITestCases.notificationIds.get(randomIndexForNotificationId);
		System.out.println(randomNotificationId);

		int user_id_last_digits = faker.number().numberBetween(100, 150);
		String invalidUserId = "BIE0" + String.valueOf(user_id_last_digits);

		return new Object[][] { { randomNotificationId, "BIE018" }, { randomNotificationId, invalidUserId },
				{ randomNotificationId, "" }, { String.valueOf(faker.number().numberBetween(5000, 10000)), "BIE018" } };
	}
}

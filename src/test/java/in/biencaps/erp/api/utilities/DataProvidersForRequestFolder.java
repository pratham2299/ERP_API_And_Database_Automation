package in.biencaps.erp.api.utilities;

import java.util.Random;

import org.testng.annotations.DataProvider;

import com.github.javafaker.Faker;

import in.biencaps.erp.api.testcases.EmployeeFolderAPITestCases;
import in.biencaps.erp.api.testcases.RequestFolderAPITestCases;

public class DataProvidersForRequestFolder {
	private static Random random = new Random();
	private static Faker faker = new Faker();

	@DataProvider(name = "TestDataForGetAllRequestsAnalytic")
	public Object[][] testDataForGetAllRequestsAnalytic() {
		int randomIndexForEmployeeId = random.nextInt(EmployeeFolderAPITestCases.employeeIds.size());
		int randomEmployeeId = EmployeeFolderAPITestCases.employeeIds.get(randomIndexForEmployeeId);

		return new Object[][] { { randomEmployeeId }, { faker.number().numberBetween(50, 100) } };
	}

	@DataProvider(name = "TestDataForGetAllRequests")
	public Object[][] testDataForGetAllRequests() {
		int randomIndexForRequestStatus = random.nextInt(RequestFolderAPITestCases.requestStatuses.size());
		String randomRequestStatus = RequestFolderAPITestCases.requestStatuses.get(randomIndexForRequestStatus);

		return new Object[][] { { faker.cat().breed() }, { randomRequestStatus } };
	}

	@DataProvider(name = "TestDataForGetAllRequestsFromAnotherEmployeeForSingleEmployee")
	public Object[][] testDataForGetAllRequestsFromAnotherEmployeeForSingleEmployee() {
		int randomIndexForEmployeeId = random.nextInt(EmployeeFolderAPITestCases.employeeIds.size());
		int randomEmployeeId = EmployeeFolderAPITestCases.employeeIds.get(randomIndexForEmployeeId);

		return new Object[][] { { randomEmployeeId }, { faker.number().numberBetween(50, 100) } };
	}

	@DataProvider(name = "TestDataForGetAllMySentRequests")
	public Object[][] testDataForGetAllMySentRequests() {
		int randomIndexForRequestStatus = random.nextInt(RequestFolderAPITestCases.requestStatuses.size());
		String randomRequestStatus = RequestFolderAPITestCases.requestStatuses.get(randomIndexForRequestStatus);

		int randomIndexForUserId = random.nextInt(EmployeeFolderAPITestCases.userIds.size());
		String randomUserId = EmployeeFolderAPITestCases.userIds.get(randomIndexForUserId);

		int userId = faker.number().numberBetween(100, 150);
		String userID = String.valueOf(userId);
		String fakeInvalidUserId = "BIE0" + userID;

		return new Object[][] { { randomUserId, randomRequestStatus }, { randomUserId, faker.cat().breed() },
				{ "", randomRequestStatus }, { "", faker.cat().breed() }, { fakeInvalidUserId, randomRequestStatus },
				{ fakeInvalidUserId, faker.cat().breed() }, };
	}
}

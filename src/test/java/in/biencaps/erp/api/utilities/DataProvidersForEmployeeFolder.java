package in.biencaps.erp.api.utilities;

import java.util.*;
import org.testng.annotations.*;
import com.github.javafaker.*;

import in.biencaps.erp.api.testcases.*;

public class DataProvidersForEmployeeFolder {
	private static Faker faker = new Faker();
	private static Random random = new Random();

	@DataProvider(name = "TestDataForAddEmployee")
	public Object[][] testDataForAddEmployee() {
		int randomIndexForUserId = random.nextInt(EmployeeFolderAPITestCases.userIds.size());
		String randomUserId = EmployeeFolderAPITestCases.userIds.get(randomIndexForUserId);

		String validUserId = EmployeeFolderAPITestCases.newUserId;
		String employeeFullName = DataGeneratorForAPI.generateFakeFullName();

		int randomIndexForDesignationId = random.nextInt(DesignationFolderAPITestCases.designationIds.size());
		int randomValidDesignationId = DesignationFolderAPITestCases.designationIds.get(randomIndexForDesignationId);

		int randomInvalidDesignationId = faker.number().numberBetween(50, 100);

		int randomIndexForDepartmentId = random.nextInt(DepartmentFolderAPITestCases.departmentIds.size());
		int randomValidDepartmentId = DepartmentFolderAPITestCases.departmentIds.get(randomIndexForDepartmentId);

		int randomInvalidDepartmentId = faker.number().numberBetween(50, 100);

		int roleIds[] = { 3, 4, 5 };
		int randomIndexForRoleId = random.nextInt(roleIds.length);
		int randomValidRoleId = roleIds[randomIndexForRoleId];

		int randomInvalidReportingAuthorityEmpId = faker.number().numberBetween(50, 100);

		int randomInvalidRoleId = faker.number().numberBetween(50, 100);

		String employeePersonalEmail = faker.internet().emailAddress();
		String[] employeeInvalidPersonalEmails = { "example@@gmail.com", "example@.gmail.com", "example@gmail",
				"example@gmail....com" };
		int randomIndexForEmail = random.nextInt(employeeInvalidPersonalEmails.length);
		String randomInvalidEmployeePersonalEmail = employeeInvalidPersonalEmails[randomIndexForEmail];

		String fakeEmployeeMobileNumber1 = DataGeneratorForAPI.generateRandomMobileNumber();
		String invalidMobileNumber1 = DataGeneratorForAPI.generateRandomInvalidDigitMobileNumber(1, 9);
		String invalidMobileNumber2 = DataGeneratorForAPI.generateRandomInvalidDigitMobileNumber(11, 20);
		String employeeJoiningDate = DataGeneratorForAPI.generateRandomFutureDate();

		String fakeOfficeLocation = "Narhe, Pune";

		return new Object[][] {
				{ employeeFullName, validUserId, employeeJoiningDate, "ACTIVE", randomValidDepartmentId,
						randomValidDesignationId, randomValidRoleId, 1, employeePersonalEmail,
						fakeEmployeeMobileNumber1, fakeOfficeLocation },
				{ "", validUserId, employeeJoiningDate, "ACTIVE", randomValidDepartmentId, randomValidDesignationId,
						randomValidRoleId, 1, employeePersonalEmail, fakeEmployeeMobileNumber1, fakeOfficeLocation },
				{ employeeFullName, randomUserId, employeeJoiningDate, "ACTIVE", randomValidDepartmentId,
						randomValidDesignationId, randomValidRoleId, 1, employeePersonalEmail,
						fakeEmployeeMobileNumber1, fakeOfficeLocation },
				{ employeeFullName, validUserId, employeeJoiningDate, "ACTIVE", randomInvalidDepartmentId,
						randomValidDesignationId, randomValidRoleId, 1, employeePersonalEmail,
						fakeEmployeeMobileNumber1, fakeOfficeLocation },
				{ employeeFullName, validUserId, employeeJoiningDate, "ACTIVE", randomValidDepartmentId,
						randomInvalidDesignationId, randomValidRoleId, 1, employeePersonalEmail,
						fakeEmployeeMobileNumber1, fakeOfficeLocation },
				{ employeeFullName, validUserId, employeeJoiningDate, "ACTIVE", randomValidDepartmentId,
						randomValidDesignationId, randomInvalidRoleId, 1, employeePersonalEmail,
						fakeEmployeeMobileNumber1, fakeOfficeLocation },
				{ employeeFullName, validUserId, employeeJoiningDate, "ACTIVE", randomValidDepartmentId,
						randomValidDesignationId, randomValidRoleId, randomInvalidReportingAuthorityEmpId,
						employeePersonalEmail, fakeEmployeeMobileNumber1, fakeOfficeLocation },
				{ employeeFullName, validUserId, employeeJoiningDate, "ACTIVE", randomValidDepartmentId,
						randomValidDesignationId, randomValidRoleId, 1, randomInvalidEmployeePersonalEmail,
						fakeEmployeeMobileNumber1, fakeOfficeLocation },
				{ employeeFullName, validUserId, employeeJoiningDate, "ACTIVE", randomValidDepartmentId,
						randomValidDesignationId, randomValidRoleId, 1, employeePersonalEmail, invalidMobileNumber1,
						fakeOfficeLocation },
				{ employeeFullName, validUserId, employeeJoiningDate, "ACTIVE", randomValidDepartmentId,
						randomValidDesignationId, randomValidRoleId, 1, employeePersonalEmail, invalidMobileNumber2,
						fakeOfficeLocation },
				{ employeeFullName, validUserId, employeeJoiningDate, "ACTIVE", randomValidDepartmentId,
						randomValidDesignationId, randomValidRoleId, 1, employeePersonalEmail,
						fakeEmployeeMobileNumber1, "" }, };
	}

	@DataProvider(name = "TestDataForGetAllEmployees")
	public Object[][] TestDataForGetAllEmployees() {

		return new Object[][] { { LoginEmployeeAPITestCases.authToken } };
	}

	@DataProvider(name = "TestDataForUpdateEmployee")
	public Object[][] testDataForUpdateEmployee() {
		int validEmployeeId = 4;

		int invalidEmployeeId = faker.number().numberBetween(50, 100);

		int randomIndexForDesignationId = random.nextInt(DesignationFolderAPITestCases.designationIds.size());
		int randomValidDesignationId = DesignationFolderAPITestCases.designationIds.get(randomIndexForDesignationId);

		int randomInvalidDesignationId = faker.number().numberBetween(50, 100);

		int randomIndexForDepartmentId = random.nextInt(DepartmentFolderAPITestCases.departmentIds.size());
		int randomValidDepartmentId = DepartmentFolderAPITestCases.departmentIds.get(randomIndexForDepartmentId);

		int randomInvalidDepartmentId = faker.number().numberBetween(50, 100);

		int roleIds[] = { 3, 4, 5 };
		int randomIndexForRoleId = random.nextInt(roleIds.length);
		int randomValidRoleId = roleIds[randomIndexForRoleId];

		int randomInvalidRoleId = faker.number().numberBetween(50, 100);

		String[] employeeInvalidEmails = { "example@@gmail.com", "example@.gmail.com", "example@gmail",
				"example@gmail....com" };
		int randomIndexForEmail = random.nextInt(employeeInvalidEmails.length);
		String randomInvalidEmployeeEmail = employeeInvalidEmails[randomIndexForEmail];

		String[] bloodGroups = { "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" };
		int randomIndexForBloodGroup = random.nextInt(bloodGroups.length);
		String randomBloodGroup = bloodGroups[randomIndexForBloodGroup];

		String invalidMobileNumber1 = DataGeneratorForAPI.generateRandomInvalidDigitMobileNumber(1, 9);
		String invalidMobileNumber2 = DataGeneratorForAPI.generateRandomInvalidDigitMobileNumber(11, 20);

		String fakeOfficeLocation = "Narhe, Pune";

		return new Object[][] {
				{ validEmployeeId, "Prathamesh Dhasade", "2023/11/01", "ACTIVE", randomValidDepartmentId,
						randomValidDesignationId, randomValidRoleId, "9834530434", "9850708611",
						"prathamesh@biencaps.com", "prathameshdhasade99@gmail.com", fakeOfficeLocation,
						randomBloodGroup, "1999/08/22", "Bibwewadi, Pune" },
				{ invalidEmployeeId, "Prathamesh Dhasade", "2023/11/01", "ACTIVE", randomValidDepartmentId,
						randomValidDesignationId, randomValidRoleId, "9834530434", "9850708611",
						"prathamesh@biencaps.com", "prathameshdhasade99@gmail.com", fakeOfficeLocation,
						randomBloodGroup, "1999/08/22", "Bibwewadi, Pune" },
				{ validEmployeeId, "", "2023/11/01", "ACTIVE", randomValidDepartmentId, randomValidDesignationId,
						randomValidRoleId, "9834530434", "9850708611", "prathamesh@biencaps.com",
						"prathameshdhasade99@gmail.com", fakeOfficeLocation, randomBloodGroup, "1999/08/22",
						"Bibwewadi, Pune" },
				{ validEmployeeId, "Prathamesh Dhasade", "", "ACTIVE", randomValidDepartmentId,
						randomValidDesignationId, randomValidRoleId, "9834530434", "9850708611",
						"prathamesh@biencaps.com", "prathameshdhasade99@gmail.com", fakeOfficeLocation,
						randomBloodGroup, "1999/08/22", "Bibwewadi, Pune" },
				{ validEmployeeId, "Prathamesh Dhasade", "2023/11/01", "ACTIVE", randomInvalidDepartmentId,
						randomValidDesignationId, randomValidRoleId, "9834530434", "9850708611",
						"prathamesh@biencaps.com", "prathameshdhasade99@gmail.com", fakeOfficeLocation,
						randomBloodGroup, "1999/08/22", "Bibwewadi, Pune" },
				{ validEmployeeId, "Prathamesh Dhasade", "2023/11/01", "ACTIVE", randomValidDepartmentId,
						randomInvalidDesignationId, randomValidRoleId, "9834530434", "9850708611",
						"prathamesh@biencaps.com", "prathameshdhasade99@gmail.com", fakeOfficeLocation,
						randomBloodGroup, "1999/08/22", "Bibwewadi, Pune" },
				{ validEmployeeId, "Prathamesh Dhasade", "2023/11/01", "ACTIVE", randomValidDepartmentId,
						randomValidDesignationId, randomInvalidRoleId, "9834530434", "9850708611",
						"prathamesh@biencaps.com", "prathameshdhasade99@gmail.com", fakeOfficeLocation,
						randomBloodGroup, "1999/08/22", "Bibwewadi, Pune" },
				{ validEmployeeId, "Prathamesh Dhasade", "2023/11/01", "ACTIVE", randomValidDepartmentId,
						randomValidDesignationId, randomValidRoleId, "", "9850708611", "prathamesh@biencaps.com",
						"prathameshdhasade99@gmail.com", fakeOfficeLocation, randomBloodGroup, "1999/08/22",
						"Bibwewadi, Pune" },
				{ validEmployeeId, "Prathamesh Dhasade", "2023/11/01", "ACTIVE", randomValidDepartmentId,
						randomValidDesignationId, randomValidRoleId, invalidMobileNumber1, "9850708611",
						"prathamesh@biencaps.com", "prathameshdhasade99@gmail.com", fakeOfficeLocation,
						randomBloodGroup, "1999/08/22", "Bibwewadi, Pune" },
				{ validEmployeeId, "Prathamesh Dhasade", "2023/11/01", "ACTIVE", randomValidDepartmentId,
						randomValidDesignationId, randomValidRoleId, invalidMobileNumber2, "9850708611",
						"prathamesh@biencaps.com", "prathameshdhasade99@gmail.com", fakeOfficeLocation,
						randomBloodGroup, "1999/08/22", "Bibwewadi, Pune" },
				{ validEmployeeId, "Prathamesh Dhasade", "2023/11/01", "ACTIVE", randomValidDepartmentId,
						randomValidDesignationId, randomValidRoleId, "9834530434", invalidMobileNumber1,
						"prathamesh@biencaps.com", "prathameshdhasade99@gmail.com", fakeOfficeLocation,
						randomBloodGroup, "1999/08/22", "Bibwewadi, Pune" },
				{ validEmployeeId, "Prathamesh Dhasade", "2023/11/01", "ACTIVE", randomValidDepartmentId,
						randomValidDesignationId, randomValidRoleId, "9834530434", invalidMobileNumber2,
						"prathamesh@biencaps.com", "prathameshdhasade99@gmail.com", fakeOfficeLocation,
						randomBloodGroup, "1999/08/22", "Bibwewadi, Pune" },
				{ validEmployeeId, "Prathamesh Dhasade", "2023/11/01", "ACTIVE", randomValidDepartmentId,
						randomValidDesignationId, randomValidRoleId, "9834530434", "9850708611",
						"prathamesh@biencaps.com", "prathameshdhasade99@gmail.com", "", randomBloodGroup, "1999/08/22",
						"Bibwewadi, Pune" },
				{ validEmployeeId, "Prathamesh Dhasade", "2023/11/01", "ACTIVE", randomValidDepartmentId,
						randomValidDesignationId, randomValidRoleId, "9834530434", "9850708611",
						"prathamesh@biencaps.com", "prathameshdhasade99@gmail.com", fakeOfficeLocation,
						randomBloodGroup, "1999/08/22", "Bibwewadi, Pune" },
				{ validEmployeeId, "Prathamesh Dhasade", "2023/11/01", "ACTIVE", randomValidDepartmentId,
						randomValidDesignationId, randomValidRoleId, "9834530434", "9850708611",
						"prathamesh@biencaps.com", "prathameshdhasade99@gmail.com", fakeOfficeLocation,
						randomBloodGroup, "1999/08/22", "Bibwewadi, Pune" },
				{ validEmployeeId, "Prathamesh Dhasade", "2023/11/01", "ACTIVE", randomValidDepartmentId,
						randomValidDesignationId, randomValidRoleId, "9834530434", "9850708611",
						"prathamesh@biencaps.com", "prathameshdhasade99@gmail.com", fakeOfficeLocation,
						randomBloodGroup, "1999/08/22", "Bibwewadi, Pune" },
				{ validEmployeeId, "Prathamesh Dhasade", "2023/11/01", "ACTIVE", randomValidDepartmentId,
						randomValidDesignationId, randomValidRoleId, "9834530434", "9850708611",
						randomInvalidEmployeeEmail, "prathameshdhasade99@gmail.com", fakeOfficeLocation,
						randomBloodGroup, "1999/08/22", "Bibwewadi, Pune" },
				{ validEmployeeId, "Prathamesh Dhasade", "2023/11/01", "ACTIVE", randomValidDepartmentId,
						randomValidDesignationId, randomValidRoleId, "9834530434", "9850708611",
						"prathamesh@biencaps.com", randomInvalidEmployeeEmail, fakeOfficeLocation, randomBloodGroup,
						"1999/08/22", "Bibwewadi, Pune" } };
	}

	@DataProvider(name = "TestDataForGetAssignedTaskInfoByRole")
	public Object[][] testDataForGetAssignedTaskInfoByRole() {
		int randomIndexForRoleName = random.nextInt(RoleFolderAPITestCases.roles.size());
		String randomRoleName = RoleFolderAPITestCases.roles.get(randomIndexForRoleName);

		String randomValidFormattedDate = DataGeneratorForAPI.generateRandomDateForGetAssignedTaskInfo("yyyy-MM-dd");
		String randomInvalidFormattedDate = DataGeneratorForAPI.generateRandomDateForGetAssignedTaskInfo("MM-yyyy-dd");

		return new Object[][] { { randomRoleName, randomValidFormattedDate },
				{ randomRoleName, randomInvalidFormattedDate }, { faker.nation().language(), randomValidFormattedDate },
				{ faker.nation().language(), randomInvalidFormattedDate }, };
	}

	@DataProvider(name = "TestDataForUpdatePassword")
	public Object[][] testDataForUpdatePassword() {
		String validUserId = Constants.employeeUserId;
		String oldPassword = Constants.employeePassword;
		String newPassword = faker.internet().password(5, 10);
		String confirmPassword = newPassword;

		int user_id_last_digits = faker.number().numberBetween(100, 150);
		String invalidUserId = "INC0" + String.valueOf(user_id_last_digits);

		return new Object[][] { { validUserId, oldPassword, "Pass@123", "Pass@123" },
				{ "", oldPassword, newPassword, confirmPassword },
				{ invalidUserId, oldPassword, newPassword, confirmPassword },
				{ validUserId, "", newPassword, confirmPassword },
				{ validUserId, "Pratham@123", newPassword, confirmPassword },
				{ validUserId, oldPassword, "", confirmPassword }, { "INC018", oldPassword, newPassword, "" },
				{ validUserId, oldPassword, newPassword, faker.internet().domainName() } };
	}

	@DataProvider(name = "TestDataForGetEncryptedEmail")
	public Object[][] testDataForGetEncryptedEmail() {
		int randomIndexForUserId = random.nextInt(EmployeeFolderAPITestCases.userIds.size());
		String randomUserId = EmployeeFolderAPITestCases.userIds.get(randomIndexForUserId);

		int user_id_last_digits = faker.number().numberBetween(100, 150);
		String invalidUserId = "INC0" + String.valueOf(user_id_last_digits);

		return new Object[][] { { randomUserId }, { invalidUserId } };
	}

	@DataProvider(name = "TestDataForAddForgotPassword")
	public Object[][] testDataForAddForgotPassword() {
		int randomIndexForUserId = random.nextInt(EmployeeFolderAPITestCases.userIds.size());
		String randomUserId = EmployeeFolderAPITestCases.userIds.get(randomIndexForUserId);

		int user_id_last_digits = faker.number().numberBetween(100, 150);
		String invalidUserId = "INC0" + String.valueOf(user_id_last_digits);

		return new Object[][] { { randomUserId }, { invalidUserId } };
	}

	@DataProvider(name = "TestDataForAddToken")
	public Object[][] testDataForAddToken() {
		String token = Constants.gmailKey;

		int randomIndexForUserId = random.nextInt(EmployeeFolderAPITestCases.userIds.size());
		String randomUserId = EmployeeFolderAPITestCases.userIds.get(randomIndexForUserId);

		int user_id_last_digits = faker.number().numberBetween(100, 150);
		String invalidUserId = "INC0" + String.valueOf(user_id_last_digits);

		return new Object[][] { { token, randomUserId }, { token, invalidUserId }, { "", randomUserId }, };
	}

	@DataProvider(name = "TestDataForSearchEmployeeInLevel")
	public Object[][] testDataForSearchEmployeeInLevel() {
		int randomIndexForEmployeeName = random.nextInt(EmployeeFolderAPITestCases.employeeFullNames.size());
		String randomEmployeeName = EmployeeFolderAPITestCases.employeeFullNames.get(randomIndexForEmployeeName);

		int randomIndexForRoleName = random.nextInt(RoleFolderAPITestCases.roles.size());
		String randomRoleName = RoleFolderAPITestCases.roles.get(randomIndexForRoleName);

		String randomValidFormattedDate = DataGeneratorForAPI.getCurrentDate("yyyy-MM-dd");
		String randomInvalidFormattedDate = DataGeneratorForAPI.getCurrentDate("MM-yyyy-dd");

		return new Object[][] { { randomEmployeeName, randomRoleName, randomValidFormattedDate },
				{ randomEmployeeName, faker.company().industry(), randomValidFormattedDate },
				{ randomEmployeeName, "", randomValidFormattedDate },
				{ randomEmployeeName, randomRoleName, randomInvalidFormattedDate },
				{ randomEmployeeName, randomRoleName, "" },
				{ faker.name().firstName(), randomRoleName, randomValidFormattedDate } };
	}
}

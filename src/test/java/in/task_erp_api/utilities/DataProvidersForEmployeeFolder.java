package in.task_erp_api.utilities;

import java.util.*;
import org.testng.annotations.*;
import com.github.javafaker.*;

import in.task_erp_api.testcases.*;

public class DataProvidersForEmployeeFolder {
	private static Faker faker = new Faker();
	private static Random random = new Random();

	@DataProvider(name = "TestDataForAddEmployee")
	public Object[][] testDataForAddEmployee() {
		int user_id_last_digits = faker.number().numberBetween(100, 150);
		String userId = "INC0" + String.valueOf(user_id_last_digits);
		String employeeFullName = faker.name().fullName();

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

		String employeePersonalEmail = faker.internet().emailAddress();
		String[] employeeInvalidPersonalEmails = { "example@@gmail.com", "example@.gmail.com", "example@gmail",
				"example@gmail....com" };
		int randomIndexForEmail = random.nextInt(employeeInvalidPersonalEmails.length);
		String randomInvalidEmployeePersonalEmail = employeeInvalidPersonalEmails[randomIndexForEmail];

		String fakeEmployeeMobileNumber1 = DataGeneratorForAPI.generateRandomMobileNumber();
		String employeeJoiningDate = DataGeneratorForAPI.generateRandomFutureDate();
		String fakeEmployeeMobileNumber2 = DataGeneratorForAPI.generateRandomMobileNumber();
		String employeeDOB = DataGeneratorForAPI.generateRandomDateRangeForDOB("yyyy-MM-dd");

		String[] bloodGroups = { "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" };
		int randomIndexForBloodGroup = random.nextInt(bloodGroups.length);
		String randomBloodGroup = bloodGroups[randomIndexForBloodGroup];

		String fakeEmployeeAddress = faker.address().fullAddress();

		return new Object[][] {
				{ userId, employeeFullName, "ACTIVE", randomValidDesignationId, randomValidDepartmentId,
						randomValidRoleId, employeePersonalEmail, fakeEmployeeMobileNumber1, employeeJoiningDate,
						fakeEmployeeMobileNumber2, employeeDOB, randomBloodGroup, fakeEmployeeAddress },
				{ "", employeeFullName, "ACTIVE", randomValidDesignationId, randomValidDepartmentId, randomValidRoleId,
						employeePersonalEmail, fakeEmployeeMobileNumber1, employeeJoiningDate,
						fakeEmployeeMobileNumber2, employeeDOB, randomBloodGroup, fakeEmployeeAddress },
				{ "INC012", employeeFullName, "ACTIVE", randomValidDesignationId, randomValidDepartmentId,
						randomValidRoleId, employeePersonalEmail, fakeEmployeeMobileNumber1, employeeJoiningDate,
						fakeEmployeeMobileNumber2, employeeDOB, randomBloodGroup, fakeEmployeeAddress },
				{ userId, "", "ACTIVE", randomValidDesignationId, randomValidDepartmentId, randomValidRoleId,
						employeePersonalEmail, fakeEmployeeMobileNumber1, employeeJoiningDate,
						fakeEmployeeMobileNumber2, employeeDOB, randomBloodGroup, fakeEmployeeAddress },
				{ userId, employeeFullName, "ACTIVE", randomInvalidDesignationId, randomValidDepartmentId,
						randomValidRoleId, employeePersonalEmail, fakeEmployeeMobileNumber1, employeeJoiningDate,
						fakeEmployeeMobileNumber2, employeeDOB, randomBloodGroup, fakeEmployeeAddress },
				{ userId, employeeFullName, "ACTIVE", randomValidDesignationId, randomInvalidDepartmentId,
						randomValidRoleId, employeePersonalEmail, fakeEmployeeMobileNumber1, employeeJoiningDate,
						fakeEmployeeMobileNumber2, employeeDOB, randomBloodGroup, fakeEmployeeAddress },
				{ userId, employeeFullName, "ACTIVE", randomValidDesignationId, randomValidDepartmentId,
						randomInvalidRoleId, employeePersonalEmail, fakeEmployeeMobileNumber1, employeeJoiningDate,
						fakeEmployeeMobileNumber2, employeeDOB, randomBloodGroup, fakeEmployeeAddress },
				{ userId, employeeFullName, "ACTIVE", randomValidDesignationId, randomValidDepartmentId,
						randomValidRoleId, "", fakeEmployeeMobileNumber1, employeeJoiningDate,
						fakeEmployeeMobileNumber2, employeeDOB, randomBloodGroup, fakeEmployeeAddress },
				{ userId, employeeFullName, "ACTIVE", randomValidDesignationId, randomValidDepartmentId,
						randomValidRoleId, randomInvalidEmployeePersonalEmail, fakeEmployeeMobileNumber1,
						employeeJoiningDate, fakeEmployeeMobileNumber2, employeeDOB, randomBloodGroup,
						fakeEmployeeAddress },
				{ userId, employeeFullName, "ACTIVE", randomValidDesignationId, randomValidDepartmentId,
						randomValidRoleId, employeePersonalEmail, fakeEmployeeMobileNumber1, "",
						fakeEmployeeMobileNumber2, employeeDOB, randomBloodGroup, fakeEmployeeAddress } };
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

		return new Object[][] {
				{ validEmployeeId, "Prathamesh Dhasade", "9834530434", "ACTIVE", "9850708611", randomBloodGroup,
						"Narhe, Pune", "2023/11/01", randomValidRoleId, randomValidDesignationId,
						randomValidDepartmentId, "Bibwewadi, Pune", "1999/08/22", "prathamesh@biencaps.com",
						"prathameshdhasade99@gmail.com" },
				{ invalidEmployeeId, "Prathamesh Dhasade", "9834530434", "ACTIVE", "9850708611", randomBloodGroup,
						"Narhe, Pune", "2023/11/01", randomValidRoleId, randomValidDesignationId,
						randomValidDepartmentId, "Bibwewadi, Pune", "1999/08/22", "prathamesh@biencaps.com",
						"prathameshdhasade99@gmail.com" },
				{ validEmployeeId, "", "9834530434", "ACTIVE", "9850708611", randomBloodGroup, "Narhe, Pune",
						"2023/11/01", randomValidRoleId, randomValidDesignationId, randomValidDepartmentId,
						"Bibwewadi, Pune", "1999/08/22", "prathamesh@biencaps.com", "prathameshdhasade99@gmail.com" },
				{ validEmployeeId, "Prathamesh Dhasade", "", "ACTIVE", "9850708611", randomBloodGroup, "Narhe, Pune",
						"2023/11/01", randomValidRoleId, randomValidDesignationId, randomValidDepartmentId,
						"Bibwewadi, Pune", "1999/08/22", "prathamesh@biencaps.com", "prathameshdhasade99@gmail.com" },
				{ validEmployeeId, "Prathamesh Dhasade", "98345", "ACTIVE", "9850708611", randomBloodGroup,
						"Narhe, Pune", "2023/11/01", randomValidRoleId, randomValidDesignationId,
						randomValidDepartmentId, "Bibwewadi, Pune", "1999/08/22", "prathamesh@biencaps.com",
						"prathameshdhasade99@gmail.com" },
				{ validEmployeeId, "Prathamesh Dhasade", "98345304340000000", "ACTIVE", "9850708611", randomBloodGroup,
						"Narhe, Pune", "2023/11/01", randomValidRoleId, randomValidDesignationId,
						randomValidDepartmentId, "Bibwewadi, Pune", "1999/08/22", "prathamesh@biencaps.com",
						"prathameshdhasade99@gmail.com" },
				{ validEmployeeId, "Prathamesh Dhasade", "9834530434", "ACTIVE", "985", randomBloodGroup, "Narhe, Pune",
						"2023/11/01", randomValidRoleId, randomValidDesignationId, randomValidDepartmentId,
						"Bibwewadi, Pune", "1999/08/22", "prathamesh@biencaps.com", "prathameshdhasade99@gmail.com" },
				{ validEmployeeId, "Prathamesh Dhasade", "9834530434", "ACTIVE", "98507086110000000000",
						randomBloodGroup, "Narhe, Pune", "2023/11/01", randomValidRoleId, randomValidDesignationId,
						randomValidDepartmentId, "Bibwewadi, Pune", "1999/08/22", "prathamesh@biencaps.com",
						"prathameshdhasade99@gmail.com" },
				{ validEmployeeId, "Prathamesh Dhasade", "9834530434", "ACTIVE", "9850708611", randomBloodGroup, "",
						"2023/11/01", randomValidRoleId, randomValidDesignationId, randomValidDepartmentId,
						"Bibwewadi, Pune", "1999/08/22", "prathamesh@biencaps.com", "prathameshdhasade99@gmail.com" },
				{ validEmployeeId, "Prathamesh Dhasade", "9834530434", "ACTIVE", "9850708611", randomBloodGroup,
						"Narhe, Pune", "", randomValidRoleId, randomValidDesignationId, randomValidDepartmentId,
						"Bibwewadi, Pune", "1999/08/22", "prathamesh@biencaps.com", "prathameshdhasade99@gmail.com" },
				{ validEmployeeId, "Prathamesh Dhasade", "9834530434", "ACTIVE", "9850708611", randomBloodGroup,
						"Narhe, Pune", "2023/11/01", randomInvalidRoleId, randomValidDesignationId,
						randomValidDepartmentId, "Bibwewadi, Pune", "1999/08/22", "prathamesh@biencaps.com",
						"prathameshdhasade99@gmail.com" },
				{ validEmployeeId, "Prathamesh Dhasade", "9834530434", "ACTIVE", "9850708611", randomBloodGroup,
						"Narhe, Pune", "2023/11/01", randomValidRoleId, randomInvalidDesignationId,
						randomValidDepartmentId, "Bibwewadi, Pune", "1999/08/22", "prathamesh@biencaps.com",
						"prathameshdhasade99@gmail.com" },
				{ validEmployeeId, "Prathamesh Dhasade", "9834530434", "ACTIVE", "9850708611", randomBloodGroup,
						"Narhe, Pune", "2023/11/01", randomValidRoleId, randomValidDesignationId,
						randomInvalidDepartmentId, "Bibwewadi, Pune", "1999/08/22", "prathamesh@biencaps.com",
						"prathameshdhasade99@gmail.com" },
				{ validEmployeeId, "Prathamesh Dhasade", "9834530434", "ACTIVE", "9850708611", randomBloodGroup,
						"Narhe, Pune", "2023/11/01", randomValidRoleId, randomValidDesignationId,
						randomValidDepartmentId, "Bibwewadi, Pune", "1999/08/22", randomInvalidEmployeeEmail,
						"prathameshdhasade99@gmail.com" },
				{ validEmployeeId, "Prathamesh Dhasade", "9834530434", "ACTIVE", "9850708611", randomBloodGroup,
						"Narhe, Pune", "2023/11/01", randomValidRoleId, randomValidDesignationId,
						randomValidDepartmentId, "Bibwewadi, Pune", "1999/08/22", "prathamesh@biencaps.com",
						randomInvalidEmployeeEmail } };
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
		String oldPassword = LoginEmployeeAPITestCases.password;
		String newPassword = faker.internet().password(5, 10);
		String confirmPassword = newPassword;

		int user_id_last_digits = faker.number().numberBetween(100, 150);
		String invalidUserId = "INC0" + String.valueOf(user_id_last_digits);

		return new Object[][] { { "INC018", oldPassword, "Pass@123", "Pass@123" },
				{ "", oldPassword, newPassword, confirmPassword },
				{ invalidUserId, oldPassword, newPassword, confirmPassword },
				{ "INC018", "", newPassword, confirmPassword },
				{ "INC018", "Pratham@123", newPassword, confirmPassword },
				{ "INC018", oldPassword, "", confirmPassword }, { "INC018", oldPassword, newPassword, "" },
				{ "INC018", oldPassword, newPassword, faker.internet().domainName() } };
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

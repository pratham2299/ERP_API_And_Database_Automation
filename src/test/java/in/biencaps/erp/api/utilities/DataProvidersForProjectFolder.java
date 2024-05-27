package in.biencaps.erp.api.utilities;

import java.util.*;
import org.testng.annotations.*;
import com.github.javafaker.*;

import in.biencaps.erp.api.testcases.*;

public class DataProvidersForProjectFolder {
	private static Random random = new Random();
	private static Faker faker = new Faker();

	@DataProvider(name = "TestDataForAddProject")
	public Object[][] testDataForAddProject() {
		String fakeProjectName = DataGeneratorForAPI.generateFakeProjet();

		int randomIndexForProjectName = random.nextInt(ProjectFolderAPITestCases.projectNames.size());
		String randomProjectName = ProjectFolderAPITestCases.projectNames.get(randomIndexForProjectName);

		String validProjectStartDate = DataGeneratorForAPI.generateRandomValidProjectStartDate();
		String validProjectEndDate = DataGeneratorForAPI.generateRandomProjectEndDate();

		String invalidProjectStartDate = DataGeneratorForAPI.generateRandomInvalidProjectStartDate();
		String invalidProjectEndDate1 = validProjectStartDate;
		String invalidProjectEndDate2 = DataGeneratorForAPI.getCurrentDate("yyyy-MM-dd");

		int randomIndexForProjectManagerEmployeeId = random
				.nextInt(ProjectFolderAPITestCases.projectManagerEmployeeIds.size());
		int randomProjectManagerEmployeeId = ProjectFolderAPITestCases.projectManagerEmployeeIds
				.get(randomIndexForProjectManagerEmployeeId);

		int invalidProjectManager = DataGeneratorForAPI.generateFakeNumberWithRange(50, 100);

		int invalidProjectStatus = DataGeneratorForAPI.generateFakeNumberWithRange(50, 100);

		int randomIndexForProjectPriorityId = random.nextInt(PriorityFolderAPITestCases.priorityIds.size());
		int randomProjectPriorityId = PriorityFolderAPITestCases.priorityIds.get(randomIndexForProjectPriorityId);

		int invalidProjectPriority = DataGeneratorForAPI.generateFakeNumberWithRange(50, 100);

		int randomIndexForProjectDepartmentId1 = random.nextInt(DepartmentFolderAPITestCases.departmentIds.size());
		int randomProjectDepartmentId1 = DepartmentFolderAPITestCases.departmentIds
				.get(randomIndexForProjectDepartmentId1);

		int randomIndexForProjectDepartmentId2 = random.nextInt(DepartmentFolderAPITestCases.departmentIds.size());
		int randomProjectDepartmentId2 = DepartmentFolderAPITestCases.departmentIds
				.get(randomIndexForProjectDepartmentId2);

		int invalidProjectDepartment = DataGeneratorForAPI.generateFakeNumberWithRange(50, 100);

		int randomIndexForProjectEmployeeId1 = random.nextInt(EmployeeFolderAPITestCases.employeeIds.size());
		int randomProjectEmployeeId1 = EmployeeFolderAPITestCases.employeeIds.get(randomIndexForProjectEmployeeId1);

		int randomIndexForProjectEmployeeId2 = random.nextInt(EmployeeFolderAPITestCases.employeeIds.size());
		int randomProjectEmployeeId2 = EmployeeFolderAPITestCases.employeeIds.get(randomIndexForProjectEmployeeId2);

		int invalidProjectEmployee = DataGeneratorForAPI.generateFakeNumberWithRange(50, 100);

		return new Object[][] {
				{ fakeProjectName, validProjectStartDate, validProjectEndDate, randomProjectManagerEmployeeId, 1,
						randomProjectPriorityId, randomProjectDepartmentId1, randomProjectDepartmentId2,
						randomProjectEmployeeId1, randomProjectEmployeeId2 },
				{ "", validProjectStartDate, validProjectEndDate, randomProjectManagerEmployeeId, 1,
						randomProjectPriorityId, randomProjectDepartmentId1, randomProjectDepartmentId2,
						randomProjectEmployeeId1, randomProjectEmployeeId2 },
				{ randomProjectName, validProjectStartDate, validProjectEndDate, randomProjectManagerEmployeeId, 1,
						randomProjectPriorityId, randomProjectDepartmentId1, randomProjectDepartmentId2,
						randomProjectEmployeeId1, randomProjectEmployeeId2 },
				{ fakeProjectName, "", validProjectEndDate, randomProjectManagerEmployeeId, 1, randomProjectPriorityId,
						randomProjectDepartmentId1, randomProjectDepartmentId1, randomProjectEmployeeId2,
						randomProjectEmployeeId2 },
				{ fakeProjectName, invalidProjectStartDate, validProjectEndDate, randomProjectManagerEmployeeId, 1,
						randomProjectPriorityId, randomProjectDepartmentId1, randomProjectDepartmentId2,
						randomProjectEmployeeId1, randomProjectEmployeeId2 },
				{ fakeProjectName, validProjectStartDate, "", randomProjectManagerEmployeeId, 1,
						randomProjectPriorityId, randomProjectDepartmentId1, randomProjectDepartmentId2,
						randomProjectEmployeeId1, randomProjectEmployeeId2 },
				{ fakeProjectName, validProjectStartDate, invalidProjectEndDate1, randomProjectManagerEmployeeId, 1,
						randomProjectPriorityId, randomProjectDepartmentId1, randomProjectDepartmentId2,
						randomProjectEmployeeId1, randomProjectEmployeeId2 },
				{ fakeProjectName, validProjectStartDate, invalidProjectEndDate2, randomProjectManagerEmployeeId, 1,
						randomProjectPriorityId, randomProjectDepartmentId1, randomProjectDepartmentId2,
						randomProjectEmployeeId1, randomProjectEmployeeId2 },
				{ fakeProjectName, validProjectStartDate, validProjectEndDate, invalidProjectManager, 1,
						randomProjectPriorityId, randomProjectDepartmentId1, randomProjectDepartmentId2,
						randomProjectEmployeeId1, randomProjectEmployeeId2 },
				{ fakeProjectName, validProjectStartDate, validProjectEndDate, randomProjectManagerEmployeeId,
						invalidProjectStatus, randomProjectPriorityId, randomProjectDepartmentId1,
						randomProjectDepartmentId2, randomProjectEmployeeId1, randomProjectEmployeeId2 },
				{ fakeProjectName, validProjectStartDate, validProjectEndDate, randomProjectManagerEmployeeId, 1,
						invalidProjectPriority, randomProjectDepartmentId1, randomProjectDepartmentId2,
						randomProjectEmployeeId1, randomProjectEmployeeId2 },
				{ fakeProjectName, validProjectStartDate, validProjectEndDate, randomProjectManagerEmployeeId, 1,
						randomProjectPriorityId, invalidProjectDepartment, randomProjectDepartmentId2,
						randomProjectEmployeeId1, randomProjectEmployeeId2 },
				{ fakeProjectName, validProjectStartDate, validProjectEndDate, randomProjectManagerEmployeeId, 1,
						randomProjectPriorityId, randomProjectDepartmentId1, invalidProjectDepartment,
						randomProjectEmployeeId1, randomProjectEmployeeId2 },
				{ fakeProjectName, validProjectStartDate, validProjectEndDate, randomProjectManagerEmployeeId, 1,
						randomProjectPriorityId, randomProjectDepartmentId1, randomProjectDepartmentId2,
						invalidProjectEmployee, randomProjectEmployeeId2 },
				{ fakeProjectName, validProjectStartDate, validProjectEndDate, randomProjectManagerEmployeeId, 1,
						randomProjectPriorityId, randomProjectDepartmentId1, randomProjectDepartmentId2,
						randomProjectEmployeeId1, invalidProjectEmployee } };
	}

	@DataProvider(name = "TestDataForUpdateProject")
	public Object[][] testDataForUpdateProject() {
		int invalidProjectId = DataGeneratorForAPI.generateFakeNumberWithRange(
				ProjectFolderAPITestCases.newCreatedProjectId + 10, ProjectFolderAPITestCases.newCreatedProjectId + 50);

		String fakeProjectName = DataGeneratorForAPI.generateFakeProjet();

		int randomIndexForProjectName = random.nextInt(ProjectFolderAPITestCases.projectNames.size());
		String randomProjectName = ProjectFolderAPITestCases.projectNames.get(randomIndexForProjectName);

		String validProjectStartDate = DataGeneratorForAPI.generateRandomValidProjectStartDate();
		String validProjectEndDate = DataGeneratorForAPI.generateRandomProjectEndDate();

		String invalidProjectStartDate = DataGeneratorForAPI.generateRandomInvalidProjectStartDate();
		String invalidProjectEndDate1 = validProjectStartDate;
		String invalidProjectEndDate2 = DataGeneratorForAPI.getCurrentDate("yyyy-MM-dd");

		int randomIndexForProjectManagerEmployeeId = random
				.nextInt(ProjectFolderAPITestCases.projectManagerEmployeeIds.size());
		int randomProjectManagerEmployeeId = ProjectFolderAPITestCases.projectManagerEmployeeIds
				.get(randomIndexForProjectManagerEmployeeId);

		int randomIndexForProjectPriorityId = random.nextInt(PriorityFolderAPITestCases.priorityIds.size());
		int randomProjectPriorityId = PriorityFolderAPITestCases.priorityIds.get(randomIndexForProjectPriorityId);

		int randomIndexForProjectDepartmentId1 = random.nextInt(DepartmentFolderAPITestCases.departmentIds.size());
		int randomProjectDepartmentId1 = DepartmentFolderAPITestCases.departmentIds
				.get(randomIndexForProjectDepartmentId1);

		int randomIndexForProjectDepartmentId2 = random.nextInt(DepartmentFolderAPITestCases.departmentIds.size());
		int randomProjectDepartmentId2 = DepartmentFolderAPITestCases.departmentIds
				.get(randomIndexForProjectDepartmentId2);

		int invalidProjectDepartment = DataGeneratorForAPI.generateFakeNumberWithRange(50, 100);

		int randomIndexForProjectEmployeeId1 = random.nextInt(EmployeeFolderAPITestCases.employeeIds.size());
		int randomProjectEmployeeId1 = EmployeeFolderAPITestCases.employeeIds.get(randomIndexForProjectEmployeeId1);

		int randomIndexForProjectEmployeeId2 = random.nextInt(EmployeeFolderAPITestCases.employeeIds.size());
		int randomProjectEmployeeId2 = EmployeeFolderAPITestCases.employeeIds.get(randomIndexForProjectEmployeeId2);

		int invalidProjectEmployee = DataGeneratorForAPI.generateFakeNumberWithRange(50, 100);

		return new Object[][] {
				{ ProjectFolderAPITestCases.newCreatedProjectId, fakeProjectName, validProjectStartDate,
						validProjectEndDate, randomProjectManagerEmployeeId, 1, randomProjectPriorityId,
						randomProjectDepartmentId1, randomProjectDepartmentId2, randomProjectEmployeeId1,
						randomProjectEmployeeId2 },
				{ invalidProjectId, fakeProjectName, validProjectStartDate, validProjectEndDate,
						randomProjectManagerEmployeeId, 1, randomProjectPriorityId, randomProjectDepartmentId1,
						randomProjectDepartmentId2, randomProjectEmployeeId1, randomProjectEmployeeId2 },
				{ ProjectFolderAPITestCases.newCreatedProjectId, "", validProjectStartDate, validProjectEndDate,
						randomProjectManagerEmployeeId, 1, randomProjectPriorityId, randomProjectDepartmentId1,
						randomProjectDepartmentId2, randomProjectEmployeeId1, randomProjectEmployeeId2 },
				{ ProjectFolderAPITestCases.newCreatedProjectId, randomProjectName, validProjectStartDate,
						validProjectEndDate, randomProjectManagerEmployeeId, 1, randomProjectPriorityId,
						randomProjectDepartmentId1, randomProjectDepartmentId2, randomProjectEmployeeId1,
						randomProjectEmployeeId2 },
				{ ProjectFolderAPITestCases.newCreatedProjectId, fakeProjectName, "", validProjectEndDate,
						randomProjectManagerEmployeeId, 1, randomProjectPriorityId, randomProjectDepartmentId1,
						randomProjectDepartmentId2, randomProjectEmployeeId1, randomProjectEmployeeId2 },
				{ ProjectFolderAPITestCases.newCreatedProjectId, fakeProjectName, invalidProjectStartDate,
						validProjectEndDate, randomProjectManagerEmployeeId, 1, randomProjectPriorityId,
						randomProjectDepartmentId1, randomProjectDepartmentId2, randomProjectEmployeeId1,
						randomProjectEmployeeId2 },
				{ ProjectFolderAPITestCases.newCreatedProjectId, fakeProjectName, validProjectStartDate, "",
						randomProjectManagerEmployeeId, 1, randomProjectPriorityId, randomProjectDepartmentId1,
						randomProjectDepartmentId2, randomProjectEmployeeId1, randomProjectEmployeeId2 },
				{ ProjectFolderAPITestCases.newCreatedProjectId, fakeProjectName, validProjectStartDate,
						invalidProjectEndDate1, randomProjectManagerEmployeeId, 1, randomProjectPriorityId,
						randomProjectDepartmentId1, randomProjectDepartmentId2, randomProjectEmployeeId1,
						randomProjectEmployeeId2 },
				{ ProjectFolderAPITestCases.newCreatedProjectId, fakeProjectName, validProjectStartDate,
						invalidProjectEndDate2, randomProjectManagerEmployeeId, 1, randomProjectPriorityId,
						randomProjectDepartmentId1, randomProjectDepartmentId2, randomProjectEmployeeId1,
						randomProjectEmployeeId2 },
				{ ProjectFolderAPITestCases.newCreatedProjectId, fakeProjectName, validProjectStartDate,
						validProjectEndDate, faker.number().numberBetween(50, 100), 1, randomProjectPriorityId,
						randomProjectDepartmentId1, randomProjectDepartmentId2, randomProjectEmployeeId1,
						randomProjectEmployeeId2 },
				{ ProjectFolderAPITestCases.newCreatedProjectId, fakeProjectName, validProjectStartDate,
						validProjectEndDate, randomProjectManagerEmployeeId, faker.number().numberBetween(10, 20),
						randomProjectPriorityId, randomProjectDepartmentId1, randomProjectDepartmentId2,
						randomProjectEmployeeId1, randomProjectEmployeeId2 },
				{ ProjectFolderAPITestCases.newCreatedProjectId, fakeProjectName, validProjectStartDate,
						validProjectEndDate, randomProjectManagerEmployeeId, 1, faker.number().numberBetween(50, 100),
						randomProjectDepartmentId1, randomProjectDepartmentId2, randomProjectEmployeeId1,
						randomProjectEmployeeId2 },
				{ ProjectFolderAPITestCases.newCreatedProjectId, fakeProjectName, validProjectStartDate,
						validProjectEndDate, randomProjectManagerEmployeeId, 1, randomProjectPriorityId,
						invalidProjectDepartment, randomProjectDepartmentId2, randomProjectEmployeeId1,
						randomProjectEmployeeId2 },
				{ ProjectFolderAPITestCases.newCreatedProjectId, fakeProjectName, validProjectStartDate,
						validProjectEndDate, randomProjectManagerEmployeeId, 1, randomProjectPriorityId,
						randomProjectDepartmentId1, invalidProjectDepartment, randomProjectEmployeeId1,
						randomProjectEmployeeId2 },
				{ ProjectFolderAPITestCases.newCreatedProjectId, fakeProjectName, validProjectStartDate,
						validProjectEndDate, randomProjectManagerEmployeeId, 1, randomProjectPriorityId,
						randomProjectDepartmentId1, randomProjectDepartmentId2, invalidProjectEmployee,
						randomProjectEmployeeId2 },
				{ ProjectFolderAPITestCases.newCreatedProjectId, fakeProjectName, validProjectStartDate,
						validProjectEndDate, randomProjectManagerEmployeeId, 1, randomProjectPriorityId,
						randomProjectDepartmentId1, randomProjectDepartmentId1, randomProjectEmployeeId1,
						invalidProjectEmployee } };
	}

	@DataProvider(name = "TestDataForGetAllProjectsByDepartment")
	public Object[][] testDataForGetAllProjectsByDepartment() {
		int randomIndexForDepartmentName = random.nextInt(DepartmentFolderAPITestCases.departments.size());
		String randomDepartmentName = DepartmentFolderAPITestCases.departments.get(randomIndexForDepartmentName);

		String invalidDepartment = DataGeneratorForAPI.generateFakeDepartment();

		return new Object[][] { { randomDepartmentName }, { invalidDepartment } };
	}

	@DataProvider(name = "TestDataForGetAllProjectsForAnUserId")
	public Object[][] testDataForGetAllProjectsForAnUserId() {
		int randomIndexForUserId = random.nextInt(EmployeeFolderAPITestCases.userIds.size());
		String randomUserId = EmployeeFolderAPITestCases.userIds.get(randomIndexForUserId);

		int userId = DataGeneratorForAPI.generateFakeNumberWithRange(50, 100);
		String userID = String.valueOf(userId);
		String fakeInvalidUserId = "INC0" + userID;

		return new Object[][] { { randomUserId }, { fakeInvalidUserId } };
	}

	@DataProvider(name = "TestDataForGetAllTasksForProject")
	public Object[][] testDataForGetAllTasksForProject() {
		int randomIndexForProjectId = random.nextInt(ProjectFolderAPITestCases.projectIds.size());
		int randomProjectId = ProjectFolderAPITestCases.projectIds.get(randomIndexForProjectId);

		int invalidProjectId = DataGeneratorForAPI.generateFakeNumberWithRange(
				ProjectFolderAPITestCases.newCreatedProjectId + 10, ProjectFolderAPITestCases.newCreatedProjectId + 50);

		return new Object[][] { { randomProjectId, }, { invalidProjectId } };
	}

	@DataProvider(name = "TestDataForGetAllTasksForProjectSearch")
	public Object[][] testDataForGetAllTasksForProjectSearch() {
		int randomIndexForProjectId = random.nextInt(ProjectFolderAPITestCases.projectIds.size());
		int randomProjectId = ProjectFolderAPITestCases.projectIds.get(randomIndexForProjectId);

		int invalidProjectId = DataGeneratorForAPI.generateFakeNumberWithRange(
				ProjectFolderAPITestCases.newCreatedProjectId + 10, ProjectFolderAPITestCases.newCreatedProjectId + 50);

		String validKey = "";

		return new Object[][] { { randomProjectId, }, { invalidProjectId } };
	}

	@DataProvider(name = "TestDataForDeleteProject")
	public Object[][] testDataForDeleteProject() {
		int randomIndexForProjectId = random.nextInt(ProjectFolderAPITestCases.projectIds.size());
		int randomProjectId = ProjectFolderAPITestCases.projectIds.get(randomIndexForProjectId);

		int validProjectId = ProjectFolderAPITestCases.newCreatedProjectId;

		int invalidProjectId = DataGeneratorForAPI.generateFakeNumberWithRange(
				ProjectFolderAPITestCases.newCreatedProjectId + 10, ProjectFolderAPITestCases.newCreatedProjectId + 50);

		String validPassword = Constants.employeePassword;
		String invalidPassword = DataGeneratorForAPI.generateFakePassword();

		return new Object[][] { { validProjectId, validPassword }, { invalidProjectId, validPassword },
				{ validProjectId, invalidPassword }, { invalidProjectId, invalidPassword },
				{ randomProjectId, validPassword } };
	}
}

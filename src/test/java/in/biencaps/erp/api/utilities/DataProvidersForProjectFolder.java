package in.biencaps.erp.api.utilities;

import java.util.*;
import org.testng.annotations.*;

import in.biencaps.erp.api.testcases.*;

public class DataProvidersForProjectFolder {
	private static Random random = new Random();

	@DataProvider(name = "TestDataForAddProject")
	public Object[][] testDataForAddProject() {
		int randomIndexForProjectName = random.nextInt(ProjectFolderAPITestCases.projectNames.size());
		String randomProjectName = ProjectFolderAPITestCases.projectNames.get(randomIndexForProjectName);

		String validProjectStartDate = DataGeneratorForAPI.generateRandomValidProjectStartDate();
		String validProjectEndDate = DataGeneratorForAPI.generateRandomProjectEndDate();

		String invalidProjectEndDate = DataGeneratorForAPI.generateRandomInvalidProjectEndDate();
		String invalidProjectStartDate1 = DataGeneratorForAPI.getCurrentDate("yyyy-MM-dd");
		String invalidProjectEndDate2 = DataGeneratorForAPI.getCurrentDate("yyyy-MM-dd");

		int randomIndexForProjectManagerEmployeeId = random
				.nextInt(ProjectFolderAPITestCases.projectManagerEmployeeIds.size());
		int randomProjectManagerEmployeeId = ProjectFolderAPITestCases.projectManagerEmployeeIds
				.get(randomIndexForProjectManagerEmployeeId);

		int invalidProjectManager = DataGeneratorForAPI.generateFakeNumberWithRange(50, 100);

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
				// Entering empty string for only projectName field
				{ "", validProjectStartDate, validProjectEndDate, randomProjectManagerEmployeeId, 1,
						randomProjectPriorityId, randomProjectDepartmentId1, randomProjectDepartmentId2,
						randomProjectEmployeeId1, randomProjectEmployeeId2 },
				// Entering empty string for only projectStartDate field
				{ DataGeneratorForAPI.generateFakeProjet(), "", validProjectEndDate, randomProjectManagerEmployeeId, 1,
						randomProjectPriorityId, randomProjectDepartmentId1, randomProjectDepartmentId1,
						randomProjectEmployeeId2, randomProjectEmployeeId2 },
				// Entering projectStartDate as current date and projectEndDate as past date for
				// only projectStartDate and projectEndDate field
				{ DataGeneratorForAPI.generateFakeProjet(), invalidProjectStartDate1, invalidProjectEndDate,
						randomProjectManagerEmployeeId, 1, randomProjectPriorityId, randomProjectDepartmentId1,
						randomProjectDepartmentId2, randomProjectEmployeeId1, randomProjectEmployeeId2 },
				// Entering empty string for only projectEndDate field
				{ DataGeneratorForAPI.generateFakeProjet(), validProjectStartDate, "", randomProjectManagerEmployeeId,
						1, randomProjectPriorityId, randomProjectDepartmentId1, randomProjectDepartmentId2,
						randomProjectEmployeeId1, randomProjectEmployeeId2 },
				// Entering projectEndDate as current date and projectStartDate as past date for
				// only projectStartDate and projectEndDate field
				{ DataGeneratorForAPI.generateFakeProjet(), validProjectStartDate, invalidProjectEndDate2,
						randomProjectManagerEmployeeId, 1, randomProjectPriorityId, randomProjectDepartmentId1,
						randomProjectDepartmentId2, randomProjectEmployeeId1, randomProjectEmployeeId2 },
				// Entering invalid projectManager employee Id for only projectManager field
				{ DataGeneratorForAPI.generateFakeProjet(), validProjectStartDate, validProjectEndDate,
						invalidProjectManager, 1, randomProjectPriorityId, randomProjectDepartmentId1,
						randomProjectDepartmentId2, randomProjectEmployeeId1, randomProjectEmployeeId2 },
				// Entering invalid projectPriority Id for only projectPriorityId field
				{ DataGeneratorForAPI.generateFakeProjet(), validProjectStartDate, validProjectEndDate,
						randomProjectManagerEmployeeId, 1, invalidProjectPriority, randomProjectDepartmentId1,
						randomProjectDepartmentId2, randomProjectEmployeeId1, randomProjectEmployeeId2 },
				// Entering invalid projectDepartment Id for only projectDepartmentId1 field
				{ DataGeneratorForAPI.generateFakeProjet(), validProjectStartDate, validProjectEndDate,
						randomProjectManagerEmployeeId, 1, randomProjectPriorityId, invalidProjectDepartment,
						randomProjectDepartmentId2, randomProjectEmployeeId1, randomProjectEmployeeId2 },
				// Entering invalid projectDepartment Id for only projectDepartmentId2 field
				{ DataGeneratorForAPI.generateFakeProjet(), validProjectStartDate, validProjectEndDate,
						randomProjectManagerEmployeeId, 1, randomProjectPriorityId, randomProjectDepartmentId1,
						invalidProjectDepartment, randomProjectEmployeeId1, randomProjectEmployeeId2 },
				// Entering invalid projectEmployee Id for only projectEmployeeId1 field
				{ DataGeneratorForAPI.generateFakeProjet(), validProjectStartDate, validProjectEndDate,
						randomProjectManagerEmployeeId, 1, randomProjectPriorityId, randomProjectDepartmentId1,
						randomProjectDepartmentId2, invalidProjectEmployee, randomProjectEmployeeId2 },
				// Entering invalid projectEmployee Id for only projectEmployeeId2 field
				{ DataGeneratorForAPI.generateFakeProjet(), validProjectStartDate, validProjectEndDate,
						randomProjectManagerEmployeeId, 1, randomProjectPriorityId, randomProjectDepartmentId1,
						randomProjectDepartmentId2, randomProjectEmployeeId1, invalidProjectEmployee },
				// Entering valid data for all field
				{ DataGeneratorForAPI.generateFakeProjet(), validProjectStartDate, validProjectEndDate,
						randomProjectManagerEmployeeId, 1, randomProjectPriorityId, randomProjectDepartmentId1,
						randomProjectDepartmentId2, randomProjectEmployeeId1, randomProjectEmployeeId2 },
				// Entering already exist projectName for projectName field
				{ randomProjectName, validProjectStartDate, validProjectEndDate, randomProjectManagerEmployeeId, 1,
						randomProjectPriorityId, randomProjectDepartmentId1, randomProjectDepartmentId2,
						randomProjectEmployeeId1, randomProjectEmployeeId2 } };
	}

	@DataProvider(name = "TestDataForUpdateProject")
	public Object[][] testDataForUpdateProject() {
		int invalidProjectId = DataGeneratorForAPI.generateFakeNumberWithRange(
				ProjectFolderAPITestCases.newCreatedProjectId + 10, ProjectFolderAPITestCases.newCreatedProjectId + 50);

		int randomIndexForProjectName = random.nextInt(ProjectFolderAPITestCases.projectNames.size());
		String randomProjectName = ProjectFolderAPITestCases.projectNames.get(randomIndexForProjectName);

		String validProjectStartDate = DataGeneratorForAPI.generateRandomValidProjectStartDate();
		String validProjectEndDate = DataGeneratorForAPI.generateRandomProjectEndDate();

		String invalidProjectEndDate = DataGeneratorForAPI.generateRandomInvalidProjectEndDate();
		String invalidProjectStartDate1 = DataGeneratorForAPI.getCurrentDate("yyyy-MM-dd");
		String invalidProjectEndDate2 = DataGeneratorForAPI.getCurrentDate("yyyy-MM-dd");

		int randomIndexForProjectManagerEmployeeId = random
				.nextInt(ProjectFolderAPITestCases.projectManagerEmployeeIds.size());
		int randomProjectManagerEmployeeId = ProjectFolderAPITestCases.projectManagerEmployeeIds
				.get(randomIndexForProjectManagerEmployeeId);

		int invalidProjectManager = DataGeneratorForAPI.generateFakeNumberWithRange(50, 100);

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
				// Entering invalid projectId for only projectId field
				{ invalidProjectId, DataGeneratorForAPI.generateFakeProjet(), validProjectStartDate,
						validProjectEndDate, randomProjectManagerEmployeeId, 1, randomProjectPriorityId,
						randomProjectDepartmentId1, randomProjectDepartmentId2, randomProjectEmployeeId1,
						randomProjectEmployeeId2 },
				// Entering empty string for only projectName field
				{ ProjectFolderAPITestCases.newCreatedProjectId, "", validProjectStartDate, validProjectEndDate,
						randomProjectManagerEmployeeId, 1, randomProjectPriorityId, randomProjectDepartmentId1,
						randomProjectDepartmentId2, randomProjectEmployeeId1, randomProjectEmployeeId2 },
				// Entering empty string for only projectStartDate field
				{ ProjectFolderAPITestCases.newCreatedProjectId, DataGeneratorForAPI.generateFakeProjet(), "",
						validProjectEndDate, randomProjectManagerEmployeeId, 1, randomProjectPriorityId,
						randomProjectDepartmentId1, randomProjectDepartmentId2, randomProjectEmployeeId1,
						randomProjectEmployeeId2 },
				// Entering projectStartDate as current date and projectEndDate as past date for
				// only projectStartDate and projectEndDate field
				{ ProjectFolderAPITestCases.newCreatedProjectId, DataGeneratorForAPI.generateFakeProjet(),
						invalidProjectStartDate1, invalidProjectEndDate, randomProjectManagerEmployeeId, 1,
						randomProjectPriorityId, randomProjectDepartmentId1, randomProjectDepartmentId2,
						randomProjectEmployeeId1, randomProjectEmployeeId2 },
				// Entering empty string for only projectEndDate field
				{ ProjectFolderAPITestCases.newCreatedProjectId, DataGeneratorForAPI.generateFakeProjet(),
						validProjectStartDate, "", randomProjectManagerEmployeeId, 1, randomProjectPriorityId,
						randomProjectDepartmentId1, randomProjectDepartmentId2, randomProjectEmployeeId1,
						randomProjectEmployeeId2 },
				// Entering projectEndDate as current date and projectStartDate as past date for
				// only projectStartDate and projectEndDate field
				{ ProjectFolderAPITestCases.newCreatedProjectId, DataGeneratorForAPI.generateFakeProjet(),
						validProjectStartDate, invalidProjectEndDate2, randomProjectManagerEmployeeId, 1,
						randomProjectPriorityId, randomProjectDepartmentId1, randomProjectDepartmentId2,
						randomProjectEmployeeId1, randomProjectEmployeeId2 },
				// Entering invalid projectManager employee Id for only projectManager field
				{ ProjectFolderAPITestCases.newCreatedProjectId, DataGeneratorForAPI.generateFakeProjet(),
						validProjectStartDate, validProjectEndDate, invalidProjectManager, 1, randomProjectPriorityId,
						randomProjectDepartmentId1, randomProjectDepartmentId2, randomProjectEmployeeId1,
						randomProjectEmployeeId2 },
				// Entering invalid projectPriority Id for only projectPriorityId field
				{ ProjectFolderAPITestCases.newCreatedProjectId, DataGeneratorForAPI.generateFakeProjet(),
						validProjectStartDate, validProjectEndDate, randomProjectManagerEmployeeId, 1,
						invalidProjectPriority, randomProjectDepartmentId1, randomProjectDepartmentId2,
						randomProjectEmployeeId1, randomProjectEmployeeId2 },
				// Entering invalid projectDepartment Id for only projectDepartmentId1 field
				{ ProjectFolderAPITestCases.newCreatedProjectId, DataGeneratorForAPI.generateFakeProjet(),
						validProjectStartDate, validProjectEndDate, randomProjectManagerEmployeeId, 1,
						randomProjectPriorityId, invalidProjectDepartment, randomProjectDepartmentId2,
						randomProjectEmployeeId1, randomProjectEmployeeId2 },
				// Entering invalid projectDepartment Id for only projectDepartmentId2 field
				{ ProjectFolderAPITestCases.newCreatedProjectId, DataGeneratorForAPI.generateFakeProjet(),
						validProjectStartDate, validProjectEndDate, randomProjectManagerEmployeeId, 1,
						randomProjectPriorityId, randomProjectDepartmentId1, invalidProjectDepartment,
						randomProjectEmployeeId1, randomProjectEmployeeId2 },
				// Entering invalid projectEmployee Id for only projectEmployeeId1 field
				{ ProjectFolderAPITestCases.newCreatedProjectId, DataGeneratorForAPI.generateFakeProjet(),
						validProjectStartDate, validProjectEndDate, randomProjectManagerEmployeeId, 1,
						randomProjectPriorityId, randomProjectDepartmentId1, randomProjectDepartmentId2,
						invalidProjectEmployee, randomProjectEmployeeId2 },
				// Entering invalid projectEmployee Id for only projectEmployeeId2 field
				{ ProjectFolderAPITestCases.newCreatedProjectId, DataGeneratorForAPI.generateFakeProjet(),
						validProjectStartDate, validProjectEndDate, randomProjectManagerEmployeeId, 1,
						randomProjectPriorityId, randomProjectDepartmentId1, randomProjectDepartmentId1,
						randomProjectEmployeeId1, invalidProjectEmployee },
				// Entering valid data for all field
				{ ProjectFolderAPITestCases.newCreatedProjectId, DataGeneratorForAPI.generateFakeProjet(),
						validProjectStartDate, validProjectEndDate, randomProjectManagerEmployeeId, 1,
						randomProjectPriorityId, randomProjectDepartmentId1, randomProjectDepartmentId2,
						randomProjectEmployeeId1, randomProjectEmployeeId2 },
				// Entering already exist projectName for projectName field
				{ ProjectFolderAPITestCases.newCreatedProjectId, randomProjectName, validProjectStartDate,
						validProjectEndDate, randomProjectManagerEmployeeId, 1, randomProjectPriorityId,
						randomProjectDepartmentId1, randomProjectDepartmentId2, randomProjectEmployeeId1,
						randomProjectEmployeeId2 } };
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

		return new Object[][] { { randomProjectId, }, { invalidProjectId } };
	}

	@DataProvider(name = "TestDataForDeleteProject")
	public Object[][] testDataForDeleteProject() {
		int randomIndexForProjectId = random.nextInt(ProjectFolderAPITestCases.projectIds.size());
		int randomProjectId = ProjectFolderAPITestCases.projectIds.get(randomIndexForProjectId);

		int validProjectId = ProjectFolderAPITestCases.newCreatedProjectId;

		int invalidProjectId = DataGeneratorForAPI.generateFakeNumberWithRange(
				ProjectFolderAPITestCases.newCreatedProjectId + 10, ProjectFolderAPITestCases.newCreatedProjectId + 50);

		String validPassword = Constants.adminPassword;
		String invalidPassword = DataGeneratorForAPI.generateFakePassword();

		return new Object[][] { { invalidProjectId, validPassword }, { validProjectId, invalidPassword },
				{ invalidProjectId, invalidPassword }, { randomProjectId, validPassword },
				{ validProjectId, validPassword } };
	}
}

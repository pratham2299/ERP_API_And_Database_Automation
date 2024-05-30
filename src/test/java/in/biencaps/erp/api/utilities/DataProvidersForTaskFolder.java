package in.biencaps.erp.api.utilities;

import java.util.*;

import org.testng.annotations.*;
import com.github.javafaker.*;

import in.biencaps.erp.api.testcases.*;

public class DataProvidersForTaskFolder {
	private Faker faker = new Faker();
	private Random random = new Random();

	@DataProvider(name = "TestDataForAddSelfTask")
	public Object[][] testDataForAddSelfTask() {
		int validEmployeeId = 4;
		int validTaskOwner = 4;
		int validStatusId = 1;

		String validTaskScheduleDate = DataGeneratorForAPI.getCurrentDate("yyyy-MM-dd");
		String invalidTaskScheduleDate = DataGeneratorForAPI.getCurrentDate("MM-yyyy-dd");

		int randomIndexForPriorityId = random.nextInt(PriorityFolderAPITestCases.priorityIds.size());
		int randomPriorityId = PriorityFolderAPITestCases.priorityIds.get(randomIndexForPriorityId);

		int randomIndexForProjectId = random.nextInt(ProjectFolderAPITestCases.projectIds.size());
		int randomProjectId = ProjectFolderAPITestCases.projectIds.get(randomIndexForProjectId);

		int randomIndexForTagId = random.nextInt(DepartmentFolderAPITestCases.departmentIds.size());
		int randomTagId = DepartmentFolderAPITestCases.departmentIds.get(randomIndexForTagId);

		return new Object[][] {
				{ DataGeneratorForAPI.generateFakeTaskTitle(), validEmployeeId, validTaskScheduleDate, randomPriorityId,
						validStatusId, randomProjectId, validTaskOwner, randomTagId },
				{ DataGeneratorForAPI.generateFakeTaskTitle(), faker.number().numberBetween(50, 100),
						validTaskScheduleDate, randomPriorityId, 1, randomProjectId, 4, randomTagId },
				{ DataGeneratorForAPI.generateFakeTaskTitle(), 4, "", randomPriorityId, 1, randomProjectId, 4,
						randomTagId },
				{ DataGeneratorForAPI.generateFakeTaskTitle(), 4, invalidTaskScheduleDate, randomPriorityId, 1,
						randomProjectId, 4, randomTagId },
				{ DataGeneratorForAPI.generateFakeTaskTitle(), 4, validTaskScheduleDate,
						faker.number().numberBetween(PriorityFolderAPITestCases.newCreatedPriorityId + 10,
								PriorityFolderAPITestCases.newCreatedPriorityId + 50),
						1, randomProjectId, 4, randomTagId },
				{ DataGeneratorForAPI.generateFakeTaskTitle(), 4, validTaskScheduleDate, randomPriorityId,
						faker.number().numberBetween(StatusFolderAPITestCases.newCreatedStatusId + 10,
								StatusFolderAPITestCases.newCreatedStatusId + 50),
						randomProjectId, 4, randomTagId },
				{ DataGeneratorForAPI.generateFakeTaskTitle(), 4, validTaskScheduleDate, randomPriorityId, 1,
						faker.number().numberBetween(50, 100), 4, randomTagId },
				{ DataGeneratorForAPI.generateFakeTaskTitle(), 4, validTaskScheduleDate, randomPriorityId, 1,
						randomProjectId, faker.number().numberBetween(50, 100), randomTagId },
				{ DataGeneratorForAPI.generateFakeTaskTitle(), 4, validTaskScheduleDate, randomPriorityId, 1,
						randomProjectId, 4,
						faker.number().numberBetween(DepartmentFolderAPITestCases.newCreatedDepartmentId + 10,
								DepartmentFolderAPITestCases.newCreatedDepartmentId + 50) } };
	}

	@DataProvider(name = "TestDataForGetAllTasksForDay")
	public Object[][] testDataForGetAllTasksForDay() {
		return new Object[][] { { 4, DataGeneratorForAPI.getCurrentDate("MM-yyyy-dd") }, { 4, "" },
				{ 4, DataGeneratorForAPI.getCurrentDate("yyyy-MM-dd") },
				{ faker.number().numberBetween(100, 150), DataGeneratorForAPI.getCurrentDate("yyyy-MM-dd") } };
	}

	@DataProvider(name = "TestDataForGetSingleTask")
	public Object[][] testDataForGetSingleTask() {
		int randomIndexForTaskId = random.nextInt(TaskFolderAPITestCases.taskIds.size());
		int randomTaskId = TaskFolderAPITestCases.taskIds.get(randomIndexForTaskId);

		return new Object[][] { { randomTaskId }, { faker.number().numberBetween(100, 500) },
				{ faker.number().numberBetween(1000, 2000) } };
	}

	@DataProvider(name = "TestDataForGetAllTasksForMonth")
	public Object[][] testDataForGetAllTasksForMonth() {
		int currentYear = DataGeneratorForAPI.generateCurrentYearInInteger();
		int currentMonth = DataGeneratorForAPI.generateCurrentMonthInInteger();
		int validEmployeeId = 4;
		int invalidEmployeeId = DataGeneratorForAPI.generateFakeNumberWithRange(50, 100);

		return new Object[][] { { currentYear, currentMonth, validEmployeeId },
				{ currentYear, currentMonth, invalidEmployeeId } };
	}

	@DataProvider(name = "TestDataForGetAllTasksForWeek")
	public Object[][] testDataForGetAllTasksForWeek() {
		int currentYear = DataGeneratorForAPI.generateCurrentYearInInteger();
		int currentMonth = DataGeneratorForAPI.generateCurrentMonthInInteger();
		int currentWeekNumberInMonth = DataGeneratorForAPI.generateCurrentWeekOfCurrentMonthInInteger();
		int validEmployeeId = 4;
		int invalidEmployeeId = DataGeneratorForAPI.generateFakeNumberWithRange(50, 100);

		return new Object[][] { { currentYear, currentMonth, currentWeekNumberInMonth, validEmployeeId },
				{ currentYear, currentMonth, currentWeekNumberInMonth, invalidEmployeeId } };
	}

	@DataProvider(name = "TestDataForGetTasksInfoForEmployeeByRole")
	public Object[][] testDataForGetTasksInfoForEmployeeByRole() {
		int randomIndexForRole = random.nextInt(RoleFolderAPITestCases.roles.size());
		String randomRole = RoleFolderAPITestCases.roles.get(randomIndexForRole);

		String randomValidDate = DataGeneratorForAPI.generateRandomDateRangeForTask("yyyy-MM-dd");
		String randomInvalidDate = "2024-" + faker.number().numberBetween(13, 31) + "-"
				+ faker.number().numberBetween(1, 31);

		return new Object[][] { { randomRole, randomValidDate }, { randomRole, randomInvalidDate },
				{ faker.animal().name(), randomValidDate }, { faker.animal().name(), randomInvalidDate }, };
	}

	@DataProvider(name = "TestDataForUpdateSelfTask")
	public Object[][] testDataForUpdateSelfTask() {
		int randomIndexForSelfTaskId = random.nextInt(TaskFolderAPITestCases.taskIds.size());
		int randomSelfTaskId = TaskFolderAPITestCases.taskIds.get(randomIndexForSelfTaskId);

		int randomIndexForVerificationStatusId = random.nextInt(VerificationFolderAPITestCases.verificationIds.size());
		int randomTaskVerificationStatusId = VerificationFolderAPITestCases.verificationIds
				.get(randomIndexForVerificationStatusId);

		int randomIndexForPriorityId = random.nextInt(PriorityFolderAPITestCases.priorityIds.size());
		int randomTaskPriorityId = PriorityFolderAPITestCases.priorityIds.get(randomIndexForPriorityId);

		int randomIndexForStatusId = random.nextInt(StatusFolderAPITestCases.statusIds.size() - 1);
		int randomTaskStatusId = StatusFolderAPITestCases.statusIds.get(randomIndexForStatusId);

		int randomIndexForProjectId = random.nextInt(ProjectFolderAPITestCases.projectIds.size());
		int randomTaskProjectId = ProjectFolderAPITestCases.projectIds.get(randomIndexForProjectId);

//		int randomIndexForTagId = random.nextInt(TagFolderAPITestCases.tagIds.size());
//		int randomTaskTagId = TagFolderAPITestCases.tagIds.get(randomIndexForTagId);

		int sizeOfTagIdsList = DepartmentFolderAPITestCases.departmentIds.size();
		int lastTagIdFromTagIdsList = DepartmentFolderAPITestCases.departmentIds.get(sizeOfTagIdsList - 1);
		List<Integer> validTagIds = DataGeneratorForAPI
				.generateRandomArrayValues(DepartmentFolderAPITestCases.departmentIds);

		List<Integer> fakeTagIds = Arrays
				.asList(faker.number().numberBetween((lastTagIdFromTagIdsList) + 10, (lastTagIdFromTagIdsList) + 50));
		System.out.println("Invalid tag Ids: " + fakeTagIds);
		List<Integer> invalidTagIds = DataGeneratorForAPI.generateRandomArrayValues(fakeTagIds);

		String validTaskScheduleDate = DataGeneratorForAPI.getCurrentDate("yyyy-MM-dd");
		String invalidTaskScheduleDate = DataGeneratorForAPI.getCurrentDate("MM-yyyy-dd");

		String validTaskDueDate = DataGeneratorForAPI.getCurrentDatePlusDays("yyyy-MM-dd",
				faker.number().numberBetween(1, 5));
		String invalidTaskDueDate = DataGeneratorForAPI.getCurrentDatePlusDays("MM-yyyy-dd",
				faker.number().numberBetween(1, 5));

		return new Object[][] {
				{ randomSelfTaskId, 4, 0, randomTaskVerificationStatusId, randomTaskPriorityId, randomTaskStatusId,
						randomTaskProjectId, "https://www.gitlab.com", faker.food().dish(), validTagIds,
						validTaskScheduleDate, validTaskDueDate },
				{ randomSelfTaskId, faker.number().numberBetween(50, 100), 0, randomTaskVerificationStatusId,
						randomTaskPriorityId, randomTaskStatusId, randomTaskProjectId, "https://www.gitlab.com",
						faker.food().dish(), validTagIds, validTaskScheduleDate, validTaskDueDate },
				{ randomSelfTaskId, 4, 0, faker.number().numberBetween(50, 100), randomTaskPriorityId,
						randomTaskStatusId, randomTaskProjectId, "https://www.gitlab.com", faker.food().dish(),
						validTagIds, validTaskScheduleDate, validTaskDueDate },
				{ randomSelfTaskId, 4, 0, randomTaskVerificationStatusId, faker.number().numberBetween(50, 100),
						randomTaskStatusId, randomTaskProjectId, "https://www.gitlab.com", faker.food().dish(),
						validTagIds, validTaskScheduleDate, validTaskDueDate },
				{ randomSelfTaskId, 4, 0, randomTaskVerificationStatusId, randomTaskPriorityId,
						faker.number().numberBetween(50, 100), randomTaskProjectId, "https://www.gitlab.com",
						faker.food().dish(), validTagIds, validTaskScheduleDate, validTaskDueDate },
				{ randomSelfTaskId, 4, 0, randomTaskVerificationStatusId, randomTaskPriorityId, randomTaskStatusId,
						faker.number().numberBetween(50, 100), "https://www.gitlab.com", faker.food().dish(),
						validTagIds, validTaskScheduleDate, validTaskDueDate },
				{ randomSelfTaskId, 4, 0, randomTaskVerificationStatusId, randomTaskPriorityId, randomTaskStatusId,
						randomTaskProjectId, "", faker.food().dish(), validTagIds, validTaskScheduleDate,
						validTaskDueDate },
				{ randomSelfTaskId, 4, 0, randomTaskVerificationStatusId, randomTaskPriorityId, randomTaskStatusId,
						randomTaskProjectId, "https://www.gitlab.com", "", validTagIds, validTaskScheduleDate,
						validTaskDueDate },
				{ randomSelfTaskId, 4, 0, randomTaskVerificationStatusId, randomTaskPriorityId, randomTaskStatusId,
						randomTaskProjectId, "https://www.gitlab.com", faker.food().dish(), invalidTagIds,
						validTaskScheduleDate, validTaskDueDate },
				{ randomSelfTaskId, 4, 0, randomTaskVerificationStatusId, randomTaskPriorityId, randomTaskStatusId,
						randomTaskProjectId, "https://www.gitlab.com", faker.food().dish(), validTagIds, "",
						validTaskDueDate },
				{ randomSelfTaskId, 4, 0, randomTaskVerificationStatusId, randomTaskPriorityId, randomTaskStatusId,
						randomTaskProjectId, "https://www.gitlab.com", faker.food().dish(), validTagIds,
						invalidTaskScheduleDate, validTaskDueDate },
				{ randomSelfTaskId, 4, 0, randomTaskVerificationStatusId, randomTaskPriorityId, randomTaskStatusId,
						randomTaskProjectId, "https://www.gitlab.com", faker.food().dish(), validTagIds,
						validTaskScheduleDate, "" },
				{ randomSelfTaskId, 4, 0, randomTaskVerificationStatusId, randomTaskPriorityId, randomTaskStatusId,
						randomTaskProjectId, "https://www.gitlab.com", faker.food().dish(), validTagIds,
						validTaskScheduleDate, invalidTaskDueDate },
				{ faker.number().numberBetween(TaskFolderAPITestCases.newCreatedTaskId + 100,
						TaskFolderAPITestCases.newCreatedTaskId + 500), 4, 0, randomTaskVerificationStatusId,
						randomTaskPriorityId, randomTaskStatusId, randomTaskProjectId, "https://www.gitlab.com",
						faker.food().dish(), validTagIds, validTaskScheduleDate, validTaskDueDate } };
	}

	@DataProvider(name = "TestDataForUpdateSelfMultipleTask")
	public Object[][] testDataForUpdateSelfMultipleTask() {
//		int randomIndexForSelfTaskId = random.nextInt(TaskFolderAPITestCases.taskIds.size());
//		int randomSelfTaskId = TaskFolderAPITestCases.taskIds.get(randomIndexForSelfTaskId);

		int sizeOfTaskIdsList = TaskFolderAPITestCases.taskIds.size();
		int lastTaskIdFromTaskIdsList = TaskFolderAPITestCases.taskIds.get(sizeOfTaskIdsList - 1);
		List<Integer> validTaskIds = DataGeneratorForAPI.generateRandomArrayValues(TaskFolderAPITestCases.taskIds);
		System.out.println(validTaskIds);

		List<Integer> fakeTaskIds = Arrays
				.asList(faker.number().numberBetween(lastTaskIdFromTaskIdsList + 100, lastTaskIdFromTaskIdsList + 500));
		List<Integer> invalidtaskIds = DataGeneratorForAPI.generateRandomArrayValues(fakeTaskIds);

		int randomIndexForVerificationStatusId = random.nextInt(VerificationFolderAPITestCases.verificationIds.size());
		int randomTaskVerificationStatusId = VerificationFolderAPITestCases.verificationIds
				.get(randomIndexForVerificationStatusId);

		int randomIndexForPriorityId = random.nextInt(PriorityFolderAPITestCases.priorityIds.size());
		int randomTaskPriorityId = PriorityFolderAPITestCases.priorityIds.get(randomIndexForPriorityId);

		int randomIndexForStatusId = random.nextInt(StatusFolderAPITestCases.statusIds.size() - 2);
		int randomTaskStatusId = StatusFolderAPITestCases.statusIds.get(randomIndexForStatusId);

		int randomIndexForProjectId = random.nextInt(ProjectFolderAPITestCases.projectIds.size());
		int randomTaskProjectId = ProjectFolderAPITestCases.projectIds.get(randomIndexForProjectId);

//		int randomIndexForTagId = random.nextInt(TagFolderAPITestCases.tagIds.size());
//		int randomTaskTagId = TagFolderAPITestCases.tagIds.get(randomIndexForTagId);

		int sizeOfTagIdsList = DepartmentFolderAPITestCases.departmentIds.size();
		int lastTagIdFromTagIdsList = DepartmentFolderAPITestCases.departmentIds.get(sizeOfTagIdsList - 1);
		List<Integer> validTagIds = DataGeneratorForAPI
				.generateRandomArrayValues(DepartmentFolderAPITestCases.departmentIds);

		List<Integer> fakeTagIds = Arrays
				.asList(faker.number().numberBetween((lastTagIdFromTagIdsList) + 10, (lastTagIdFromTagIdsList) + 50));
		System.out.println("Invalid tag Ids: " + fakeTagIds);
		List<Integer> invalidTagIds = DataGeneratorForAPI.generateRandomArrayValues(fakeTagIds);

		String validTaskScheduleDate = DataGeneratorForAPI.getCurrentDate("yyyy-MM-dd");
		String invalidTaskScheduleDate = DataGeneratorForAPI.getCurrentDate("MM-yyyy-dd");

		String validTaskDueDate = DataGeneratorForAPI.getCurrentDatePlusDays("yyyy-MM-dd",
				faker.number().numberBetween(1, 5));
		String invalidTaskDueDate = DataGeneratorForAPI.getCurrentDatePlusDays("MM-yyyy-dd",
				faker.number().numberBetween(1, 5));

		return new Object[][] {
				{ validTaskIds, 4, 0, randomTaskVerificationStatusId, randomTaskPriorityId, randomTaskStatusId,
						randomTaskProjectId, "https://www.gitlab.com", faker.food().dish(), validTagIds,
						validTaskScheduleDate, validTaskDueDate },
				{ validTaskIds, faker.number().numberBetween(50, 100), 0, randomTaskVerificationStatusId,
						randomTaskPriorityId, randomTaskStatusId, randomTaskProjectId, "https://www.gitlab.com",
						faker.food().dish(), validTagIds, validTaskScheduleDate, validTaskDueDate },
				{ validTaskIds, 4, 0, faker.number().numberBetween(50, 100), randomTaskPriorityId, randomTaskStatusId,
						randomTaskProjectId, "https://www.gitlab.com", faker.food().dish(), validTagIds,
						validTaskScheduleDate, validTaskDueDate },
				{ validTaskIds, 4, 0, randomTaskVerificationStatusId, faker.number().numberBetween(50, 100),
						randomTaskStatusId, randomTaskProjectId, "https://www.gitlab.com", faker.food().dish(),
						validTagIds, validTaskScheduleDate, validTaskDueDate },
				{ validTaskIds, 4, 0, randomTaskVerificationStatusId, randomTaskPriorityId,
						faker.number().numberBetween(50, 100), randomTaskProjectId, "https://www.gitlab.com",
						faker.food().dish(), validTagIds, validTaskScheduleDate, validTaskDueDate },
				{ validTaskIds, 4, 0, randomTaskVerificationStatusId, randomTaskPriorityId, randomTaskStatusId,
						faker.number().numberBetween(50, 100), "https://www.gitlab.com", faker.food().dish(),
						validTagIds, validTaskScheduleDate, validTaskDueDate },
				{ validTaskIds, 4, 0, randomTaskVerificationStatusId, randomTaskPriorityId, randomTaskStatusId,
						randomTaskProjectId, "", faker.food().dish(), validTagIds, validTaskScheduleDate,
						validTaskDueDate },
				{ validTaskIds, 4, 0, randomTaskVerificationStatusId, randomTaskPriorityId, randomTaskStatusId,
						randomTaskProjectId, "https://www.gitlab.com", "", validTagIds, validTaskScheduleDate,
						validTaskDueDate },
				{ validTaskIds, 4, 0, randomTaskVerificationStatusId, randomTaskPriorityId, randomTaskStatusId,
						randomTaskProjectId, "https://www.gitlab.com", faker.food().dish(), invalidTagIds,
						validTaskScheduleDate, validTaskDueDate },
				{ validTaskIds, 4, 0, randomTaskVerificationStatusId, randomTaskPriorityId, randomTaskStatusId,
						randomTaskProjectId, "https://www.gitlab.com", faker.food().dish(), validTagIds, "",
						validTaskDueDate },
				{ validTaskIds, 4, 0, randomTaskVerificationStatusId, randomTaskPriorityId, randomTaskStatusId,
						randomTaskProjectId, "https://www.gitlab.com", faker.food().dish(), validTagIds,
						invalidTaskScheduleDate, validTaskDueDate },
				{ validTaskIds, 4, 0, randomTaskVerificationStatusId, randomTaskPriorityId, randomTaskStatusId,
						randomTaskProjectId, "https://www.gitlab.com", faker.food().dish(), validTagIds,
						validTaskScheduleDate, "" },
				{ validTaskIds, 4, 0, randomTaskVerificationStatusId, randomTaskPriorityId, randomTaskStatusId,
						randomTaskProjectId, "https://www.gitlab.com", faker.food().dish(), validTagIds,
						validTaskScheduleDate, invalidTaskDueDate },
				{ invalidtaskIds, 4, 0, randomTaskVerificationStatusId, randomTaskPriorityId, randomTaskStatusId,
						randomTaskProjectId, "https://www.gitlab.com", faker.food().dish(), validTagIds,
						validTaskScheduleDate, validTaskDueDate } };
	}

	@DataProvider(name = "TestDataForDuplicateSelfTask")
	public Object[][] testDataForDuplicateSelfTask() {
//		int randomIndexForSelfTaskId = random.nextInt(TaskFolderAPITestCases.taskIds.size());
//		int randomSelfTaskId = TaskFolderAPITestCases.taskIds.get(randomIndexForSelfTaskId);

		int sizeOfTaskIdsList = TaskFolderAPITestCases.taskIds.size();
		int lastTaskIdFromTaskIdsList = TaskFolderAPITestCases.taskIds.get(sizeOfTaskIdsList - 1);
		List<Integer> validTaskIds = DataGeneratorForAPI.generateRandomArrayValues(TaskFolderAPITestCases.taskIds);
		System.out.println(validTaskIds);

		List<Integer> fakeTaskIds = Arrays
				.asList(faker.number().numberBetween(lastTaskIdFromTaskIdsList + 100, lastTaskIdFromTaskIdsList + 500));
		List<Integer> invalidtaskIds = DataGeneratorForAPI.generateRandomArrayValues(fakeTaskIds);

		String validTaskScheduleDate = DataGeneratorForAPI.getCurrentDate("yyyy-MM-dd");
		String invalidTaskScheduleDate = DataGeneratorForAPI.getCurrentDate("MM-yyyy-dd");

		return new Object[][] { { validTaskIds, 4, validTaskScheduleDate },
				{ invalidtaskIds, 4, validTaskScheduleDate },
				{ validTaskIds, faker.number().numberBetween(50, 100), validTaskScheduleDate }, { validTaskIds, 4, "" },
				{ validTaskIds, 4, invalidTaskScheduleDate } };
	}

	@DataProvider(name = "TestDataForUpdateAnotherEmployeeAssignedTaskUsingTaskVerificationStatusField")
	public Object[][] testDataForUpdateAnotherEmployeeAssignedTaskUsingTaskVerificationStatusField() {
		int randomIndexForSelfTaskId = random.nextInt(TaskFolderAPITestCases.taskIds.size());
		int randomSelfTaskId = TaskFolderAPITestCases.taskIds.get(randomIndexForSelfTaskId);

		int randomIndexForVerificationStatusId = random.nextInt(VerificationFolderAPITestCases.verificationIds.size());
		int randomTaskVerificationStatusId = VerificationFolderAPITestCases.verificationIds
				.get(randomIndexForVerificationStatusId);

		return new Object[][] { { randomSelfTaskId, 4, randomTaskVerificationStatusId },
				{ faker.number().numberBetween(TaskFolderAPITestCases.newCreatedTaskId + 100,
						TaskFolderAPITestCases.newCreatedTaskId + 500), 4, randomTaskVerificationStatusId },
				{ randomSelfTaskId, 4, faker.number().numberBetween(50, 100) } };
	}

	@DataProvider(name = "TestDataForUpdateAnotherEmployeeAssignedTaskUsingTaskPriorityField")
	public Object[][] testDataForUpdateAnotherEmployeeAssignedTaskUsingTaskPriorityField() {
		int randomIndexForSelfTaskId = random.nextInt(TaskFolderAPITestCases.taskIds.size());
		int randomSelfTaskId = TaskFolderAPITestCases.taskIds.get(randomIndexForSelfTaskId);

		int randomIndexForPriorityId = random.nextInt(PriorityFolderAPITestCases.priorityIds.size());
		int randomTaskPriorityId = PriorityFolderAPITestCases.priorityIds.get(randomIndexForPriorityId);

		return new Object[][] { { randomSelfTaskId, 4, randomTaskPriorityId },
				{ faker.number().numberBetween(TaskFolderAPITestCases.newCreatedTaskId + 100,
						TaskFolderAPITestCases.newCreatedTaskId + 500), 4, randomTaskPriorityId },
				{ randomSelfTaskId, 4, faker.number().numberBetween(50, 100) } };
	}

	@DataProvider(name = "TestDataForUpdateAnotherEmployeeAssignedTaskUsingTaskStatusField")
	public Object[][] testDataForUpdateAnotherEmployeeAssignedTaskUsingTaskStatusField() {
		int randomIndexForSelfTaskId = random.nextInt(TaskFolderAPITestCases.taskIds.size());
		int randomSelfTaskId = TaskFolderAPITestCases.taskIds.get(randomIndexForSelfTaskId);

		int randomIndexForStatusId = random.nextInt(StatusFolderAPITestCases.statusIds.size());
		int randomTaskStatusId = StatusFolderAPITestCases.statusIds.get(randomIndexForStatusId);

		return new Object[][] { { randomSelfTaskId, 4, randomTaskStatusId },
				{ faker.number().numberBetween(TaskFolderAPITestCases.newCreatedTaskId + 100,
						TaskFolderAPITestCases.newCreatedTaskId + 500), 4, randomTaskStatusId },
				{ randomSelfTaskId, 4, faker.number().numberBetween(50, 100) } };
	}

	@DataProvider(name = "TestDataForUpdateAnotherEmployeeAssignedTaskUsingTaskProjectField")
	public Object[][] testDataForUpdateAnotherEmployeeAssignedTaskUsingTaskProjectField() {
		int randomIndexForSelfTaskId = random.nextInt(TaskFolderAPITestCases.taskIds.size());
		int randomSelfTaskId = TaskFolderAPITestCases.taskIds.get(randomIndexForSelfTaskId);

		int randomIndexForProjectId = random.nextInt(ProjectFolderAPITestCases.projectIds.size());
		int randomTaskProjectId = ProjectFolderAPITestCases.projectIds.get(randomIndexForProjectId);

		return new Object[][] { { randomSelfTaskId, 4, randomTaskProjectId },
				{ faker.number().numberBetween(TaskFolderAPITestCases.newCreatedTaskId + 100,
						TaskFolderAPITestCases.newCreatedTaskId + 500), 4, randomTaskProjectId },
				{ randomSelfTaskId, 4, faker.number().numberBetween(50, 100) } };
	}

	@DataProvider(name = "TestDataForUpdateAnotherEmployeeAssignedTaskUsingTaskTagField")
	public Object[][] testDataForUpdateAnotherEmployeeAssignedTaskUsingTaskTagField() {
		int randomIndexForSelfTaskId = random.nextInt(TaskFolderAPITestCases.taskIds.size());
		int randomSelfTaskId = TaskFolderAPITestCases.taskIds.get(randomIndexForSelfTaskId);

		int randomIndexForTagId = random.nextInt(DepartmentFolderAPITestCases.departmentIds.size());
		int randomTaskTagId = DepartmentFolderAPITestCases.departmentIds.get(randomIndexForTagId);

		return new Object[][] { { randomSelfTaskId, 4, randomTaskTagId },
				{ faker.number().numberBetween(TaskFolderAPITestCases.newCreatedTaskId + 100,
						TaskFolderAPITestCases.newCreatedTaskId + 500), 4, randomTaskTagId },
				{ randomSelfTaskId, 4, faker.number().numberBetween(50, 100) } };
	}

	@DataProvider(name = "TestDataForUpdateAnotherEmployeeAssignedTaskUsingTaskCommentField")
	public Object[][] testDataForUpdateAnotherEmployeeAssignedTaskUsingTaskCommentField() {
		int randomIndexForSelfTaskId = random.nextInt(TaskFolderAPITestCases.taskIds.size());
		int randomSelfTaskId = TaskFolderAPITestCases.taskIds.get(randomIndexForSelfTaskId);

		return new Object[][] { { randomSelfTaskId, 4, faker.dog().breed() },
				{ faker.number().numberBetween(TaskFolderAPITestCases.newCreatedTaskId + 100,
						TaskFolderAPITestCases.newCreatedTaskId + 500), 4, faker.dog().breed() },
				{ randomSelfTaskId, 4, "" } };
	}

	@DataProvider(name = "TestDataForUpdateAnotherEmployeeAssignedTaskUsingTaskGitLinkField")
	public Object[][] testDataForUpdateAnotherEmployeeAssignedTaskUsingTaskGitLinkField() {
		int randomIndexForSelfTaskId = random.nextInt(TaskFolderAPITestCases.taskIds.size());
		int randomSelfTaskId = TaskFolderAPITestCases.taskIds.get(randomIndexForSelfTaskId);

		return new Object[][] { { randomSelfTaskId, 4, faker.dog().breed() },
				{ faker.number().numberBetween(TaskFolderAPITestCases.newCreatedTaskId + 100,
						TaskFolderAPITestCases.newCreatedTaskId + 500), 4, faker.dog().breed() },
				{ randomSelfTaskId, 4, "" } };
	}

	@DataProvider(name = "TestDataForUpdateAnotherEmployeeAssignedTaskUsingTaskScheduleDateField")
	public Object[][] TestDataForUpdateAnotherEmployeeAssignedTaskUsingTaskScheduleDateField() {
		int randomIndexForSelfTaskId = random.nextInt(TaskFolderAPITestCases.taskIds.size());
		int randomSelfTaskId = TaskFolderAPITestCases.taskIds.get(randomIndexForSelfTaskId);

		String validTaskScheduleDate = DataGeneratorForAPI.getCurrentDate("yyyy-MM-dd");
		String invalidTaskScheduleDate = DataGeneratorForAPI.getCurrentDate("MM-yyyy-dd");

		return new Object[][] { { randomSelfTaskId, 4, validTaskScheduleDate },
				{ faker.number().numberBetween(TaskFolderAPITestCases.newCreatedTaskId + 100,
						TaskFolderAPITestCases.newCreatedTaskId + 500), 4, validTaskScheduleDate },
				{ randomSelfTaskId, 4, "" }, { randomSelfTaskId, 4, invalidTaskScheduleDate } };
	}

	@DataProvider(name = "TestDataForUpdateAnotherEmployeeAssignedTaskUsingTaskDueDateField")
	public Object[][] testDataForUpdateAnotherEmployeeAssignedTaskUsingTaskDueDateField() {
		int randomIndexForSelfTaskId = random.nextInt(TaskFolderAPITestCases.taskIds.size());
		int randomSelfTaskId = TaskFolderAPITestCases.taskIds.get(randomIndexForSelfTaskId);

		String validTaskDueDate = DataGeneratorForAPI.getCurrentDatePlusDays("yyyy-MM-dd",
				faker.number().numberBetween(1, 5));
		String invalidTaskDueDate = DataGeneratorForAPI.getCurrentDatePlusDays("MM-yyyy-dd",
				faker.number().numberBetween(1, 5));

		return new Object[][] { { randomSelfTaskId, 4, validTaskDueDate },
				{ faker.number().numberBetween(TaskFolderAPITestCases.newCreatedTaskId + 100,
						TaskFolderAPITestCases.newCreatedTaskId + 500), 4, validTaskDueDate },
				{ randomSelfTaskId, 4, "" }, { randomSelfTaskId, 4, invalidTaskDueDate } };
	}

	@DataProvider(name = "TestDataForDeleteSelfTask")
	public Object[][] testDataForDeleteSelfTask() {
		int randomIndexForSelfTaskId = random.nextInt(TaskFolderAPITestCases.taskIds.size());
		int randomSelfTaskId = TaskFolderAPITestCases.taskIds.get(randomIndexForSelfTaskId);

		return new Object[][] {
				{ faker.number().numberBetween(TaskFolderAPITestCases.newCreatedTaskId + 10,
						TaskFolderAPITestCases.newCreatedTaskId + 50), 4 },
				{ faker.number().numberBetween(TaskFolderAPITestCases.newCreatedTaskId + 10,
						TaskFolderAPITestCases.newCreatedTaskId + 50), faker.number().numberBetween(500, 1000) },
				{ randomSelfTaskId, 4 }, { randomSelfTaskId, faker.number().numberBetween(500, 1000) } };
	}

	@DataProvider(name = "TestDataForAssignTask")
	public Object[][] testDataForAssignTask() {
		String validTaskScheduleDate = DataGeneratorForAPI.getCurrentDate("yyyy-MM-dd");
		String invalidTaskScheduleDate = DataGeneratorForAPI.getCurrentDate("MM-yyyy-dd");

		int randomIndexForEmployeeId = random.nextInt(EmployeeFolderAPITestCases.employeeIds.size());
		int randomEmployeeId = EmployeeFolderAPITestCases.employeeIds.get(randomIndexForEmployeeId);

		int randomIndexForPriorityId = random.nextInt(PriorityFolderAPITestCases.priorityIds.size());
		int randomPriorityId = PriorityFolderAPITestCases.priorityIds.get(randomIndexForPriorityId);

		int randomIndexForProjectId = random.nextInt(ProjectFolderAPITestCases.projectIds.size());
		int randomProjectId = ProjectFolderAPITestCases.projectIds.get(randomIndexForProjectId);

		int randomIndexForTagId = random.nextInt(DepartmentFolderAPITestCases.departmentIds.size());
		int randomTagId = DepartmentFolderAPITestCases.departmentIds.get(randomIndexForTagId);

		return new Object[][] {
				{ DataGeneratorForAPI.generateFakeTaskTitle(), 4, DataGeneratorForAPI.generateFakeTaskTitle(),
						validTaskScheduleDate, randomPriorityId, 1, randomProjectId, 4, randomTagId },
				{ DataGeneratorForAPI.generateFakeTaskTitle(), faker.number().numberBetween(50, 100),
						DataGeneratorForAPI.generateFakeTaskTitle(), validTaskScheduleDate, randomPriorityId, 1,
						randomProjectId, 4, randomTagId },
				{ DataGeneratorForAPI.generateFakeTaskTitle(), 4, DataGeneratorForAPI.generateFakeTaskTitle(), "",
						randomPriorityId, 1, randomProjectId, 4, randomTagId },
				{ DataGeneratorForAPI.generateFakeTaskTitle(), 4, DataGeneratorForAPI.generateFakeTaskTitle(),
						invalidTaskScheduleDate, randomPriorityId, 1, randomProjectId, 4, randomTagId },
				{ DataGeneratorForAPI.generateFakeTaskTitle(), 4, DataGeneratorForAPI.generateFakeTaskTitle(),
						validTaskScheduleDate,
						faker.number().numberBetween(PriorityFolderAPITestCases.newCreatedPriorityId + 10,
								PriorityFolderAPITestCases.newCreatedPriorityId + 50),
						1, randomProjectId, 4, randomTagId },
				{ DataGeneratorForAPI.generateFakeTaskTitle(), 4, DataGeneratorForAPI.generateFakeTaskTitle(),
						validTaskScheduleDate, randomPriorityId,
						faker.number().numberBetween(StatusFolderAPITestCases.newCreatedStatusId + 10,
								StatusFolderAPITestCases.newCreatedStatusId + 50),
						randomProjectId, 4, randomTagId },
				{ DataGeneratorForAPI.generateFakeTaskTitle(), 4, DataGeneratorForAPI.generateFakeTaskTitle(),
						validTaskScheduleDate, randomPriorityId, 1, faker.number().numberBetween(50, 100), 4,
						randomTagId },
				{ DataGeneratorForAPI.generateFakeTaskTitle(), 4, DataGeneratorForAPI.generateFakeTaskTitle(),
						validTaskScheduleDate, randomPriorityId, 1, randomProjectId,
						faker.number().numberBetween(50, 100), randomTagId },
				{ DataGeneratorForAPI.generateFakeTaskTitle(), 4, DataGeneratorForAPI.generateFakeTaskTitle(),
						validTaskScheduleDate, randomPriorityId, 1, randomProjectId, 4,
						faker.number().numberBetween(DepartmentFolderAPITestCases.newCreatedDepartmentId + 10,
								DepartmentFolderAPITestCases.newCreatedDepartmentId + 50) } };
	}
}

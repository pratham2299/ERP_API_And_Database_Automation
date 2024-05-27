package in.biencaps.erp.api.utilities;

import java.util.*;
import org.testng.annotations.*;
import com.github.javafaker.*;

import in.biencaps.erp.api.testcases.*;

public class DataProvidersForRegularTaskFolder {
	private static Faker faker = new Faker();
	private static Random random = new Random();

	@DataProvider(name = "TestDataForAddRegularTask")
	public Object[][] testDataForAddRegularTask() {
		String fakeRegularTask = DataGeneratorForAPI.generateFakeTaskTitle();
		int invalidEmployeeId = DataGeneratorForAPI.generateFakeNumberWithRange(100, 200);

		int randomIndexForRegularTaskName = random.nextInt(RegularTaskFolderAPITestCases.regularTasks.size());
		String randomRegularTaskName = RegularTaskFolderAPITestCases.regularTasks.get(randomIndexForRegularTaskName);

		int randomIndexForEmployeeId = random.nextInt(EmployeeFolderAPITestCases.employeeIds.size());
		int randomEmployeeId = EmployeeFolderAPITestCases.employeeIds.get(randomIndexForEmployeeId);

		return new Object[][] { { fakeRegularTask, 1 }, { fakeRegularTask, randomEmployeeId }, { "", randomEmployeeId },
				{ randomRegularTaskName, 1 }, { fakeRegularTask, invalidEmployeeId } };
	}

	@DataProvider(name = "TestDataForUpdateRegularTask")
	public Object[][] testDataForUpdateRegularTask() {
		String fakeRegularTask = DataGeneratorForAPI.generateFakeTaskTitle();

		return new Object[][] { { faker.number().numberBetween(1, 20), fakeRegularTask },
				{ faker.number().numberBetween(1, 20), "" },
				{ RegularTaskFolderAPITestCases.newCreatedRegularTaskId, fakeRegularTask },
				{ RegularTaskFolderAPITestCases.newCreatedRegularTaskId, "" },
				{ faker.number().numberBetween(RegularTaskFolderAPITestCases.newCreatedRegularTaskId + 10,
						RegularTaskFolderAPITestCases.newCreatedRegularTaskId + 50), fakeRegularTask },
				{ faker.number().numberBetween(RegularTaskFolderAPITestCases.newCreatedRegularTaskId + 10,
						RegularTaskFolderAPITestCases.newCreatedRegularTaskId + 50), "" } };
	}

	@DataProvider(name = "TestDataForDeleteRegularTask")
	public Object[][] testDataForDeleteRegularTask() {
		return new Object[][] { { faker.number().numberBetween(1, 20) },
				{ faker.number().numberBetween(RegularTaskFolderAPITestCases.newCreatedRegularTaskId + 10,
						RegularTaskFolderAPITestCases.newCreatedRegularTaskId + 50) },
				{ RegularTaskFolderAPITestCases.newCreatedRegularTaskId } };
	}
}

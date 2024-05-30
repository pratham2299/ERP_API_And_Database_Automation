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
		int invalidEmployeeId = DataGeneratorForAPI.generateFakeNumberWithRange(100, 200);

		int randomIndexForRegularTaskName = random.nextInt(RegularTaskFolderAPITestCases.regularTasks.size());
		String randomRegularTaskName = RegularTaskFolderAPITestCases.regularTasks.get(randomIndexForRegularTaskName);

		int randomIndexForEmployeeId = random.nextInt(EmployeeFolderAPITestCases.employeeIds.size());
		int randomEmployeeId = EmployeeFolderAPITestCases.employeeIds.get(randomIndexForEmployeeId);

		return new Object[][] { { DataGeneratorForAPI.generateFakeTaskTitle(), 1 },
				{ DataGeneratorForAPI.generateFakeTaskTitle(), randomEmployeeId }, { "", randomEmployeeId },
				{ randomRegularTaskName, 1 }, { DataGeneratorForAPI.generateFakeTaskTitle(), invalidEmployeeId } };
	}

	@DataProvider(name = "TestDataForUpdateRegularTask")
	public Object[][] testDataForUpdateRegularTask() {
		return new Object[][] { { faker.number().numberBetween(1, 20), DataGeneratorForAPI.generateFakeTaskTitle() },
				{ faker.number().numberBetween(1, 20), "" },
				{ RegularTaskFolderAPITestCases.newCreatedRegularTaskId, DataGeneratorForAPI.generateFakeTaskTitle() },
				{ RegularTaskFolderAPITestCases.newCreatedRegularTaskId, "" },
				{ faker.number().numberBetween(RegularTaskFolderAPITestCases.newCreatedRegularTaskId + 10,
						RegularTaskFolderAPITestCases.newCreatedRegularTaskId + 50),
						DataGeneratorForAPI.generateFakeTaskTitle() },
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

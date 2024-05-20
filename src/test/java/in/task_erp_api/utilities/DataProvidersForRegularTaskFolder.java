package in.task_erp_api.utilities;

import java.util.*;
import org.testng.annotations.*;
import com.github.javafaker.*;

import in.task_erp_api.testcases.*;

public class DataProvidersForRegularTaskFolder {
	private static Faker faker = new Faker();
	private static Random random = new Random();

	@DataProvider(name = "TestDataForAddRegularTask")
	public Object[][] testDataForAddRegularTask() {
//		String employeeId = (String) context.getAttribute("currentEmployeeId");
//		int fakeEmpId = Integer.parseInt(employeeId);

		int randomIndexForEmployeeId = random.nextInt(EmployeeFolderAPITestCases.employeeIds.size());
		int randomEmployeeId = EmployeeFolderAPITestCases.employeeIds.get(randomIndexForEmployeeId);

		return new Object[][] { { faker.book().author(), 26 }, { faker.book().author(), randomEmployeeId },
				{ "", randomEmployeeId }, { faker.book().author(), faker.number().numberBetween(100, 200) } };
	}

	@DataProvider(name = "TestDataForUpdateRegularTask")
	public Object[][] testDataForUpdateRegularTask() {
		return new Object[][] { { faker.number().numberBetween(1, 20), faker.book().author() },
				{ faker.number().numberBetween(1, 20), "" },
				{ RegularTaskFolderAPITestCases.newCreatedRegularTaskId, faker.book().author() },
				{ RegularTaskFolderAPITestCases.newCreatedRegularTaskId, "" },
				{ faker.number().numberBetween(RegularTaskFolderAPITestCases.newCreatedRegularTaskId + 10,
						RegularTaskFolderAPITestCases.newCreatedRegularTaskId + 50), faker.book().author() },
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

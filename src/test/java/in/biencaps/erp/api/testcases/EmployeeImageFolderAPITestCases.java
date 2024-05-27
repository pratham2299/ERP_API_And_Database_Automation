package in.biencaps.erp.api.testcases;

import java.io.File;
import java.util.*;

import org.apache.logging.log4j.*;
import org.testng.annotations.*;

import in.biencaps.erp.api.bodyValidations.*;
import in.biencaps.erp.api.endpoints.*;
import in.biencaps.erp.api.responses.*;

import static org.hamcrest.Matchers.*;
import io.restassured.response.*;

public class EmployeeImageFolderAPITestCases {
	public static final Logger log = LogManager.getLogger(EmployeeImageFolderAPITestCases.class);
	private Response response;
	private Random random = new Random();

	@Test(priority = 1)
	public void verifyAddEmployeeImageWithoutAuthorization() {
		File fileToUpload = new File("C:\\Users\\Lenovo\\Pictures\\Saved Pictures\\c++ logo.png");
		response = Responses.postRequestWithoutAuthorization(APIEndpoints.addEmployeeImageEndpoint, fileToUpload);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 2)
	public void verifyGetEmployeeImageWithoutAuthorization() {
		response = Responses.getRequestWithoutAuthorization(APIEndpoints.getEmployeeImageEndpoint);

		BodyValidation.response401Validation(response);
	}

	@Test(priority = 3)
	public void verifyAddEmployeeImageWithAuthorization() {
		String[] employeeImages = { "C:\\Users\\Lenovo\\Pictures\\Saved Pictures\\ruby on rails logo.png",
				"C:\\Users\\Lenovo\\Pictures\\Saved Pictures\\bugs.jpg",
				"C:\\Users\\Lenovo\\Pictures\\Saved Pictures\\java logo.png",
				"C:\\Users\\Lenovo\\Pictures\\Saved Pictures\\python logo.png",
				"C:\\Users\\Lenovo\\Pictures\\Saved Pictures\\html & css logo.png",
				"C:\\Users\\Lenovo\\Pictures\\Saved Pictures\\c logo.png",
				"C:\\Users\\Lenovo\\Pictures\\Saved Pictures\\c++ logo.png",
				"C:\\Users\\Lenovo\\Pictures\\Saved Pictures\\photo.jpg" };

		int randomIndexForfileNameInput = random.nextInt(employeeImages.length);
		String randomfileNameInput = employeeImages[randomIndexForfileNameInput];

		File fileToUpload = new File(randomfileNameInput);
		response = Responses.postRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.addEmployeeImageEndpoint, fileToUpload);

		System.out.println(response.getBody().asPrettyString());

		BodyValidation.responseValidation(response, 200);
	}

	@Test(priority = 4)
	public void verifyGetEmployeeImageWithAuthorization() {
		response = Responses.getRequestWithAuthorization(LoginEmployeeAPITestCases.authToken,
				APIEndpoints.getEmployeeImageEndpoint);

		System.out.println(response.getBody().asPrettyString());

		BodyValidation.responseValidation(response, 200);

		response.then().body("empId", equalTo("26")).body("empName", equalTo("Prathamesh Dhasade"));
	}
}

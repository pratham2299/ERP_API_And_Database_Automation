package in.biencaps.erp.api.payloads;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.biencaps.erp.api.pojos.*;

public class RegularTaskFolderPayloads {
	// Create ObjectMapper instance
	static ObjectMapper objectMapper = new ObjectMapper();

	public static String addRegularTaskPayload(String fakeRegularTaskName, int employeeId) {
		RegularTaskPojo regularTaskObj = new RegularTaskPojo();
		regularTaskObj.setRegularTaskName(fakeRegularTaskName);

		EmployeePojo employeeObj = new EmployeePojo();
		employeeObj.setEmpId(employeeId);
		regularTaskObj.setEmployee(employeeObj);

		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(regularTaskObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Regular task object to JSON", e);
		}
	}

	public static String updateRegularTaskPayload(int regularTaskId, String fakeRegularTaskName) {
		RegularTaskPojo regularTaskObj = new RegularTaskPojo();
		regularTaskObj.setRegularTaskId(regularTaskId);
		regularTaskObj.setRegularTaskName(fakeRegularTaskName);

		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(regularTaskObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Regular task object to JSON", e);
		}
	}
}

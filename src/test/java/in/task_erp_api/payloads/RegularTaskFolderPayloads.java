package in.task_erp_api.payloads;

import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import in.biencaps.erp.pojos.*;

public class RegularTaskFolderPayloads {
	private static Gson gson = new Gson();

	public static String giveRegularTaskPayloadForAddRegularTask(String fakeRegularTaskName, int employeeId) {
		HashMap<String, Object> regularTaskMap = new HashMap<>();
		regularTaskMap.put("regularTaskName", fakeRegularTaskName);

		HashMap<String, Object> employeeMap = new HashMap<>();
		employeeMap.put("empId", employeeId);

		regularTaskMap.put("employee", employeeMap);

		String payload = gson.toJson(regularTaskMap);
		return payload;
	}

	public static String giveRegularTaskPayloadForUpdateRegularTask(int regularTaskId, String fakeRegularTaskName) {
		HashMap<String, Object> regularTaskMap = new HashMap<>();
		regularTaskMap.put("regularTaskId", regularTaskId);
		regularTaskMap.put("regularTaskName", fakeRegularTaskName);

		String payload = gson.toJson(regularTaskMap);
		return payload;
	}

	// Create ObjectMapper instance
	static ObjectMapper objectMapper = new ObjectMapper();

	public static String addRegularTaskPayload(String fakeRegularTaskName, int employeeId) {
		RegularTaskPojo regularTaskObj = new RegularTaskPojo();
		regularTaskObj.setRegularTaskName(fakeRegularTaskName);

		EmployeePojo employeeObj = new EmployeePojo();
		employeeObj.setEmpId(employeeId);
		regularTaskObj.setEmployee(employeeObj);

		try {
			return objectMapper.writeValueAsString(regularTaskObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Regular task object to JSON", e);
		}
	}

	public static String updateRegularTaskPayload(int regularTaskId, String fakeRegularTaskName) {
		RegularTaskPojo regularTaskObj = new RegularTaskPojo();
		regularTaskObj.setRegularTaskId(regularTaskId);
		regularTaskObj.setRegularTaskName(fakeRegularTaskName);

		try {
			return objectMapper.writeValueAsString(regularTaskObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Regular task object to JSON", e);
		}
	}
}

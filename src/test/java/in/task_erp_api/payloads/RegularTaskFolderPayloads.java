package in.task_erp_api.payloads;

import java.util.HashMap;

import com.google.gson.Gson;

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
}

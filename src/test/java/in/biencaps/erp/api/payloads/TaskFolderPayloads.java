package in.biencaps.erp.api.payloads;

import java.util.*;

import com.google.gson.Gson;

public class TaskFolderPayloads {
	private static Gson gson = new Gson();

	public static String giveTaskPayloadForAddTask(String taskTitle, int employeeId, String taskComment,
			String taskScheduleDate, int taskPriority, int taskStatus, int taskProject, int taskOwner, int taskTag) {
		HashMap<String, Object> taskMap = new HashMap<>();
		taskMap.put("taskTitle", Arrays.asList(taskTitle));
		taskMap.put("employee", Arrays.asList(employeeId));
		taskMap.put("taskComment", taskComment);
		taskMap.put("taskScheduleDate", taskScheduleDate);
		taskMap.put("taskPriority", taskPriority);
		taskMap.put("taskStatus", taskStatus);
		taskMap.put("taskProject", taskProject);
		taskMap.put("taskOwner", taskOwner);
		taskMap.put("taskTags", Arrays.asList(taskTag));

		String payload = gson.toJson(taskMap);
		return payload;
	}

	public static String giveTaskPayloadForSearchTaskForMonth(String key, int employeeId, int year, int month) {
		HashMap<String, Object> taskMap = new HashMap<>();
		taskMap.put("key", key);
		taskMap.put("empId", employeeId);
		taskMap.put("year", year);
		taskMap.put("month", month);

		String payload = gson.toJson(taskMap);
		return payload;
	}

	public static String giveTaskPayloadForGetAllTasksForDay(int employeeId, String taskDate) {
		HashMap<String, Object> taskMap = new HashMap<>();
		taskMap.put("empId", employeeId);
		taskMap.put("date", taskDate);

		String payload = gson.toJson(taskMap);
		return payload;
	}

	public static String giveTaskPayloadForSearchTaskForWeek(String key, int employeeId, int year, int month,
			int weekNumber) {
		HashMap<String, Object> taskMap = new HashMap<>();
		taskMap.put("key", key);
		taskMap.put("empId", employeeId);
		taskMap.put("year", year);
		taskMap.put("month", month);
		taskMap.put("weekNumber", weekNumber);

		String payload = gson.toJson(taskMap);
		return payload;
	}

	public static String giveTaskPayloadForSearchTaskForDay(String key, int employeeId, String date) {
		HashMap<String, Object> taskMap = new HashMap<>();
		taskMap.put("key", key);
		taskMap.put("empId", employeeId);
		taskMap.put("date", date);

		String payload = gson.toJson(taskMap);
		return payload;
	}

	public static String giveTaskPayloadForGetAllTasksForMonth(int year, int month, int employeeId) {
		HashMap<String, Object> taskMap = new HashMap<>();
		taskMap.put("year", year);
		taskMap.put("month", month);
		taskMap.put("empId", employeeId);

		String payload = gson.toJson(taskMap);
		return payload;
	}

	public static String giveTaskPayloadForGetAllTasksForWeek(int year, int month, int weekNumber, int employeeId) {
		HashMap<String, Object> taskMap = new HashMap<>();
		taskMap.put("year", year);
		taskMap.put("month", month);
		taskMap.put("weekNumber", weekNumber);
		taskMap.put("empId", employeeId);

		String payload = gson.toJson(taskMap);
		return payload;
	}

	public static String giveTaskPayloadForDuplicateTask(List<Integer> taskIds, int employeeId, String taskDate) {
		HashMap<String, Object> taskMap = new HashMap<>();
		taskMap.put("task", taskIds);
		taskMap.put("empId", employeeId);
		taskMap.put("date", taskDate);

		String payload = gson.toJson(taskMap);
		return payload;
	}

	public static String giveTaskPayloadForUpdateTask(int taskId, int employeeId, int taskDone,
			int taskVerificationStatus, int taskStatus, int taskPriority, int taskProject, String taskGitLink,
			String taskComment, List<Integer> taskTagIds, String taskScheduleDate, String taskDueDate) {
		HashMap<String, Object> taskMap = new HashMap<>();
		taskMap.put("taskId", taskId);
		taskMap.put("empId", employeeId);
		taskMap.put("taskDone", taskDone);
		taskMap.put("taskVerificationStatus", taskVerificationStatus);
		taskMap.put("taskPriority", taskPriority);
		taskMap.put("taskStatus", taskStatus);
		taskMap.put("taskProject", taskProject);
		taskMap.put("taskGitLink", taskGitLink);
		taskMap.put("taskComment", taskComment);
		taskMap.put("taskTags", taskTagIds);
		taskMap.put("taskScheduleDate", taskScheduleDate);
		taskMap.put("taskDueDate", taskDueDate);

		String payload = gson.toJson(taskMap);
		return payload;
	}

	public static String giveTaskPayloadForUpdateTask(int taskId, int employeeId, String payloadKey, int payloadValue) {
		HashMap<String, Object> taskMap = new HashMap<>();
		taskMap.put("taskId", taskId);
		taskMap.put("empId", employeeId);
		taskMap.put(payloadKey, payloadValue);

		String payload = gson.toJson(taskMap);
		return payload;
	}

	public static String giveTaskPayloadForUpdateTask(int taskId, int employeeId, String payloadKey, int payloadValue1,
			int payloadValue2) {
		HashMap<String, Object> taskMap = new HashMap<>();
		taskMap.put("taskId", taskId);
		taskMap.put("empId", employeeId);
		taskMap.put(payloadKey, Arrays.asList(payloadValue1, payloadValue2));

		String payload = gson.toJson(taskMap);
		return payload;
	}

	public static String giveTaskPayloadForUpdateMultipleTasks(List<Integer> taskIds, int employeeId, int taskDone,
			int taskVerificationStatus, int taskPriority, int taskStatus, int taskProject, String taskGitLink,
			String taskComment, List<Integer> taskTagIds, String taskScheduleDate, String taskDueDate) {
		HashMap<String, Object> taskMap = new HashMap<>();
		taskMap.put("taskId", taskIds);
		taskMap.put("empId", employeeId);
		taskMap.put("taskDone", taskDone);
		taskMap.put("taskVerificationStatus", taskVerificationStatus);
		taskMap.put("taskPriority", taskPriority);
		taskMap.put("taskStatus", taskStatus);
		taskMap.put("taskProject", taskProject);
		taskMap.put("taskGitLink", taskGitLink);
		taskMap.put("taskComment", taskComment);
		taskMap.put("taskTags", taskTagIds);
		taskMap.put("taskScheduleDate", taskScheduleDate);
		taskMap.put("taskDueDate", taskDueDate);

		String payload = gson.toJson(taskMap);
		return payload;
	}

	public static String giveTaskPayloadForDeleteTask(int taskId, int employeeId) {
		HashMap<String, Object> taskMap = new HashMap<>();
		taskMap.put("task", Arrays.asList(taskId));
		taskMap.put("empId", employeeId);

		String payload = gson.toJson(taskMap);
		return payload;
	}
}

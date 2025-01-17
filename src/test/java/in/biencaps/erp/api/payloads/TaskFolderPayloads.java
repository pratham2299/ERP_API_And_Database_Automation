package in.biencaps.erp.api.payloads;

import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import in.biencaps.erp.api.pojos.*;

public class TaskFolderPayloads {
	private static Gson gson = new Gson();
	static ObjectMapper objectMapper = new ObjectMapper();

	public static String addTaskPayload(String taskTitle, int employeeId, String taskScheduleDate, int taskPriority,
			int taskStatus, int taskProject, int taskOwner, int taskTag) {
		TaskPojo taskObj = new TaskPojo();
		taskObj.setTaskTitle(Arrays.asList(taskTitle));
		taskObj.setEmployee(Arrays.asList(employeeId));
		taskObj.setTaskStatus(taskStatus);
		taskObj.setTaskPriority(taskPriority);
		taskObj.setTaskProject(taskProject);
		taskObj.setTaskScheduleDate(taskScheduleDate);
		taskObj.setTaskOwner(taskOwner);
		taskObj.setTaskTags(Arrays.asList(taskTag));

		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(taskObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Task object to JSON", e);
		}
	}

	public static String giveTaskPayloadForSearchTaskForMonth(String key, int employeeId, int year, int month) {
		TaskPojo taskObj = new TaskPojo();
		taskObj.setKey(key);
		taskObj.setEmpId(employeeId);
		taskObj.setYear(year);
		taskObj.setMonth(month);

		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(taskObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Task object to JSON", e);
		}
	}

	public static String employeeIdAndDatePayload(int employeeId, String taskDate) {
		TaskPojo taskObj = new TaskPojo();
		taskObj.setEmpId(employeeId);
		taskObj.setDate(taskDate);

		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(taskObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Task object to JSON", e);
		}
	}

	public static String giveTaskPayloadForSearchTaskForWeek(String key, int employeeId, int year, int month,
			int weekNumber) {
		TaskPojo taskObj = new TaskPojo();
		taskObj.setKey(key);
		taskObj.setEmpId(employeeId);
		taskObj.setYear(year);
		taskObj.setMonth(month);
		taskObj.setWeekNumber(weekNumber);

		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(taskObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Task object to JSON", e);
		}
	}

	public static String giveTaskPayloadForSearchTaskForDay(String key, int employeeId, String date) {
		TaskPojo taskObj = new TaskPojo();
		taskObj.setKey(key);
		taskObj.setEmpId(employeeId);
		taskObj.setDate(date);

		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(taskObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Task object to JSON", e);
		}
	}

	public static String giveTaskPayloadForGetAllTasksForMonth(int year, int month, int employeeId) {
		TaskPojo taskObj = new TaskPojo();
		taskObj.setEmpId(employeeId);
		taskObj.setYear(year);
		taskObj.setMonth(month);

		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(taskObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Task object to JSON", e);
		}
	}

	public static String giveTaskPayloadForGetAllTasksForWeek(int year, int month, int weekNumber, int employeeId) {
		TaskPojo taskObj = new TaskPojo();
		taskObj.setEmpId(employeeId);
		taskObj.setYear(year);
		taskObj.setMonth(month);
		taskObj.setWeekNumber(weekNumber);

		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(taskObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Task object to JSON", e);
		}
	}

	public static String giveTaskPayloadForDuplicateTask(List<Integer> taskIds, int employeeId, String taskDate) {
		TaskPojo taskObj = new TaskPojo();
		taskObj.setTask(taskIds);
		taskObj.setEmpId(employeeId);
		taskObj.setDate(taskDate);

		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(taskObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Task object to JSON", e);
		}
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

	public static String multipleTasksShiftPayload(int taskId, String taskSceduleDate) {
		TaskPojo taskObj = new TaskPojo();
		taskObj.setTask(Arrays.asList(taskId));
		taskObj.setTaskScheduleDate(taskSceduleDate);

		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(taskObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Task object to JSON", e);
		}
	}

	public static String transferTaskPayload(int employeeId, int taskId, String date, String loggedInUser) {
		TaskPojo taskObj = new TaskPojo();
		taskObj.setEmpId(employeeId);
		taskObj.setTask(Arrays.asList(taskId));
		taskObj.setTaskScheduleDate(date);
		taskObj.setLoggedInUsr(loggedInUser);

		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(taskObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Task object to JSON", e);
		}
	}

	public static String giveTaskPayloadForDeleteTask(int taskId, int employeeId) {
		TaskPojo taskObj = new TaskPojo();
		taskObj.setTask(Arrays.asList(taskId));
		taskObj.setEmpId(employeeId);

		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(taskObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Task object to JSON", e);
		}
	}
}

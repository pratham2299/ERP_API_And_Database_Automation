package in.task_erp_api.payloads;

import java.util.*;

import com.google.gson.Gson;

public class ProjectFolderPayloads {
	private static Gson gson = new Gson();

	public static String giveProjectPayloadForGetSingleProjectByProjectId(int projectId) {
		Map<String, Object> projectMap = new HashMap<>();
		projectMap.put("projectId", projectId);

		String payload = gson.toJson(projectMap);
		return payload;
	}

	public static String giveProjectPayloadForAddProject(String projectName, String projectStartDate,
			String projectEndDate, int projectManagerEmployeeId, int projectStatusId, int projectPriorityId,
			int projectDepartmentId1, int projectDepartmentId2, int projectEmployeeId1, int projectEmployeeId2) {
		String requestPayload = "{\r\n" + "  \"projectName\": \"" + projectName + "\",\r\n"
				+ "  \"projectStartDate\": \"" + projectStartDate + "\",\r\n" + "  \"projectEndDate\": \""
				+ projectEndDate + "\",\r\n" + "  \"projectManager\": {\r\n" + "    \"empId\": "
				+ projectManagerEmployeeId + "\r\n" + "  },\r\n" + "  \"projectStatus\": {\r\n"
				+ "    \"projectStatusId\": " + projectStatusId + "\r\n" + "  },\r\n" + "  \"projectPriority\": {\r\n"
				+ "    \"priorityId\": " + projectPriorityId + "\r\n" + "  },\r\n" + "  \"projectDepartments\": [\r\n"
				+ "    {\r\n" + "      \"departmentId\": " + projectDepartmentId1 + "\r\n" + "    },\r\n" + "    {\r\n"
				+ "      \"departmentId\": " + projectDepartmentId2 + "\r\n" + "    }\r\n" + "  ],\r\n"
				+ "  \"projectEmployees\": [\r\n" + "    {\r\n" + "      \"empId\": " + projectEmployeeId1 + "\r\n"
				+ "    },\r\n" + "    {\r\n" + "      \"empId\": " + projectEmployeeId2 + "\r\n" + "    }\r\n"
				+ "  ]\r\n" + "}\r\n" + "";

		return requestPayload;
	}

	public static String giveProjectPayloadForUpdateProject(int projectId, String projectName, String projectStartDate,
			String projectEndDate, int projectManagerEmployeeId, int projectStatusId, int projectPriorityId,
			int projectDepartmentId1, int projectDepartmentId2, int projectEmployeeId1, int projectEmployeeId2) {
		String requestPayload = "{\r\n" + "    \"projectId\" : " + projectId + ",\r\n" + "    \"projectName\" : \""
				+ projectName + "\",\r\n" + "    \"projectStartDate\" : \"" + projectStartDate + "\",\r\n"
				+ "    \"projectEndDate\" : \"" + projectEndDate + "\",\r\n" + "    \"projectManager\" : {\r\n"
				+ "        \"empId\" : " + projectManagerEmployeeId + "\r\n" + "    },\r\n"
				+ "    \"projectDepartments\" : [\r\n" + "        {\r\n" + "            \"departmentId\" : "
				+ projectDepartmentId1 + "\r\n" + "        },\r\n" + "        {\r\n" + "            \"departmentId\" : "
				+ projectDepartmentId2 + "\r\n" + "        }\r\n" + "    ],\r\n" + "    \"projectEmployees\" : [\r\n"
				+ "        {\r\n" + "            \"empId\" : " + projectEmployeeId1 + "\r\n" + "        },\r\n"
				+ "        {\r\n" + "            \"empId\" : " + projectEmployeeId2 + "\r\n" + "        }\r\n"
				+ "    ],\r\n" + "    \"projectPriority\" : {\r\n" + "\r\n" + "        \"priorityId\" : "
				+ projectPriorityId + "\r\n" + "    },\r\n" + "    \"projectStatus\" : {\r\n" + "\r\n"
				+ "        \"projectStatusId\" : " + projectStatusId + "\r\n" + "    }\r\n" + "}\r\n" + "";

		return requestPayload;
	}
}

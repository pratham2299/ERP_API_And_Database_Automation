package in.task_erp_api.payloads;

import java.util.*;

import com.google.gson.Gson;

public class EmployeeFolderPayloads {
	private static Gson gson = new Gson();

	public static String giveEmployeePayloadForAddEmployee(String userId, String employeeFullName,
			String employeeStatus, int designationId, int departmentId, int roleId, String employeePersonalEmail,
			String employeeMobileNumber1, String employeeJoiningDate, String employeeMobileNumber2, String employeeDOB,
			String employeeBloodGroup, String employeeAddress) {
		HashMap<String, Object> employeeMap = new HashMap<String, Object>();
		employeeMap.put("userId", userId);
		employeeMap.put("empFullName", employeeFullName);
		employeeMap.put("empStatus", employeeStatus);

		// Designation
		Map<String, Integer> designation = new HashMap<>();
		designation.put("designationId", designationId);
		employeeMap.put("designation", designation);

		// Department
		Map<String, Integer> department = new HashMap<>();
		department.put("departmentId", departmentId);
		employeeMap.put("department", department);

		// Role
		List<Map<String, Integer>> roleList = new ArrayList<>();
		Map<String, Integer> role = new HashMap<>();
		role.put("roleId", roleId);
		roleList.add(role);
		employeeMap.put("role", roleList);

		// Reporting Authorities
		List<Map<String, Integer>> reportingAuthoritiesList = new ArrayList<>();
		Map<String, Integer> reportingAuthority1 = new HashMap<>();
		reportingAuthority1.put("empId", 8);
		reportingAuthoritiesList.add(reportingAuthority1);
		Map<String, Integer> reportingAuthority2 = new HashMap<>();
		reportingAuthority2.put("empId", 14);
		reportingAuthoritiesList.add(reportingAuthority2);
		employeeMap.put("reportingAuthorities", reportingAuthoritiesList);

		employeeMap.put("empEmailPersonal", employeePersonalEmail);
		employeeMap.put("empMobile1", employeeMobileNumber1);
		employeeMap.put("empOfficeLocation", "Pune");
		employeeMap.put("empJoiningDate", employeeJoiningDate);
		employeeMap.put("empMobile2", employeeMobileNumber2);
		employeeMap.put("empDOB", employeeDOB);
		employeeMap.put("empBloodGroup", employeeBloodGroup);
		employeeMap.put("empAddress", employeeAddress);

		String payload = gson.toJson(employeeMap);
		return payload;
	}

	public static String giveEmployeePayloadForUpdateEmployee(int employeeId, String employeeFullName,
			String employeeMobileNumber1, String employeeStatus, String employeeMobileNumber2,
			String employeeBloodGroup, String employeeOfficeLocation, String employeeJoiningDate, int roleId,
			int designationId, int departmentId, String employeeAddress, String employeeDOB,
			String employeeEmailOfficial, String employeePersonalEmail) {
		String requestPayload = "{\r\n" + "    \"empId\" : " + employeeId + ",\r\n" + "    \"empFullName\" : \""
				+ employeeFullName + "\", \r\n" + "    \"empMobile1\" : \"" + employeeMobileNumber1
				+ "\",    			\r\n" + "    \"status\" : \"" + employeeStatus + "\",        		\r\n"
				+ "    \"empMobile2\" : \"" + employeeMobileNumber2 + "\",          				\r\n"
				+ "    \"empBloodGroup\" : \"" + employeeBloodGroup + "\",      \r\n" + "    \"empOfficeLocation\" : \""
				+ employeeOfficeLocation + "\",   \r\n" + "    \"empJoiningDate\" : \"" + employeeJoiningDate
				+ "\",      \r\n" + "    \"reportingAuthority\" : [\r\n" + "        {\r\n"
				+ "            \"empId\" : 14\r\n" + "        },\r\n" + "        {\r\n"
				+ "            \"empId\" : 8\r\n" + "        }\r\n" + "    ],                       \r\n"
				+ "    \"role\" : [\r\n" + "        {\r\n" + "            \"roleId\" : " + roleId + "\r\n"
				+ "        }\r\n" + "    ],               \r\n" + "    \"designation\" : {\r\n"
				+ "        \"designationId\" : " + designationId + "\r\n" + "    },       \r\n"
				+ "    \"department\" : { \r\n" + "        \"departmentId\" : " + departmentId + "\r\n"
				+ "    },         \r\n" + "    \"empAddress\" : \"" + employeeAddress + "\",       \r\n"
				+ "    \"empDOB\" : \"" + employeeDOB + "\",        \r\n" + "    \"empEmailOfficial\" : \""
				+ employeeEmailOfficial + "\",     \r\n" + "    \"empEmailPersonal\" : \"" + employeePersonalEmail
				+ "\"\r\n" + "}\r\n" + "";

		return requestPayload;
	}

	public static String giveEmployeePayloadForGetSingleEmployee(String fakeUserId) {
		HashMap<String, Object> employeeMap = new HashMap<String, Object>();
		employeeMap.put("userId", fakeUserId);

		String payload = gson.toJson(employeeMap);
		return payload;
	}

	public static String giveEmployeePayloadForGetAssignee(int roleLevel) {
		HashMap<String, Object> employeeMap = new HashMap<String, Object>();
		employeeMap.put("roleLevel", roleLevel);

		String payload = gson.toJson(employeeMap);
		return payload;
	}

	public static String giveEmployeePayloadForUpdatePassword(String loginId, String oldPassword, String newPassword,
			String confirmPassword) {
		HashMap<String, Object> employeeMap = new HashMap<>();
		employeeMap.put("loginId", loginId);
		employeeMap.put("oldPassword", oldPassword);
		employeeMap.put("newPassword", newPassword);
		employeeMap.put("confirmPassword", confirmPassword);

		String payload = gson.toJson(employeeMap);
		return payload;
	}

	public static String giveEmployeePayloadForSearchEmployeeInLevel(String key, String role, String date) {
		HashMap<String, Object> employeeMap = new HashMap<>();
		employeeMap.put("key", key);
		employeeMap.put("role", role);
		employeeMap.put("date", date);

		String payload = gson.toJson(employeeMap);
		return payload;
	}

	public static String giveEmployeePayloadForAddToken(String token, String userId) {
		HashMap<String, Object> employeeMap = new HashMap<>();
		employeeMap.put("token", token);
		employeeMap.put("userId", userId);

		String payload = gson.toJson(employeeMap);
		return payload;
	}
}

package in.biencaps.erp.api.payloads;

import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.biencaps.erp.api.pojos.*;

public class EmployeeFolderPayloads {
	static ObjectMapper objectMapper = new ObjectMapper();

	public static String addEmployeePayload(String employeeFullName, String userId, String employeeJoiningDate,
			String employeeStatus, int departmentId, int designationId, int roleId, int reportingAuthorityEmpId,
			String employeePersonalEmail, String employeeMobileNumber1, String employeeOfficeLocation) {
		EmployeePojo employeeObj = new EmployeePojo();
		employeeObj.setEmpFullName(employeeFullName);
		employeeObj.setUserId(userId);
		employeeObj.setEmpJoiningDate(employeeJoiningDate);
		employeeObj.setEmpStatus(employeeStatus);

		// Department
		EmployeePojo.Department departmentObj = new EmployeePojo.Department();
		departmentObj.setDepartmentId(departmentId);
		employeeObj.setDepartment(departmentObj);

		// Designation
		EmployeePojo.Designation designationObj = new EmployeePojo.Designation();
		designationObj.setDesignationId(designationId);
		employeeObj.setDesignation(designationObj);

		// Role
		EmployeePojo.Role roleObj = new EmployeePojo.Role();
		roleObj.setRoleId(roleId);
		employeeObj.setRole(Arrays.asList(roleObj));

		// Reporting Authorities
		List<EmployeePojo.ReportingAuthority> reportingAuthorities = new ArrayList<>();
		EmployeePojo.ReportingAuthority reportingAuthorityObj = new EmployeePojo.ReportingAuthority();
		reportingAuthorityObj.setEmpId(reportingAuthorityEmpId);
		reportingAuthorities.add(reportingAuthorityObj);

		employeeObj.setReportingAuthorities(reportingAuthorities);
		employeeObj.setEmpEmailPersonal(employeePersonalEmail);
		employeeObj.setEmpMobile1(employeeMobileNumber1);
		employeeObj.setEmpOfficeLocation(employeeOfficeLocation);

		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(employeeObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Employee object to JSON", e);
		}
	}

	public static String updateEmployeePayload(int employeeId, String employeeFullName, String employeeJoiningDate,
			String employeeStatus, int departmentId, int designationId, int roleId, String employeeMobileNumber1,
			String employeeMobileNumber2, String employeeEmailOfficial, String employeePersonalEmail,
			String employeeOfficeLocation, String employeeBloodGroup, String employeeDOB, String employeeAddress) {
		EmployeePojo employeeObj = new EmployeePojo();
		employeeObj.setEmpId(employeeId);
		employeeObj.setEmpFullName(employeeFullName);
		employeeObj.setEmpJoiningDate(employeeJoiningDate);
		employeeObj.setEmpStatus(employeeStatus);

		// Department
		EmployeePojo.Department departmentObj = new EmployeePojo.Department();
		departmentObj.setDepartmentId(departmentId);
		employeeObj.setDepartment(departmentObj);

		// Designation
		EmployeePojo.Designation designationObj = new EmployeePojo.Designation();
		designationObj.setDesignationId(designationId);
		employeeObj.setDesignation(designationObj);

		// Role
		EmployeePojo.Role roleObj = new EmployeePojo.Role();
		roleObj.setRoleId(roleId);
		employeeObj.setRole(Arrays.asList(roleObj));

		employeeObj.setEmpMobile1(employeeMobileNumber1);
		employeeObj.setEmpMobile2(employeeMobileNumber2);
		employeeObj.setEmpEmailOfficial(employeeEmailOfficial);

		employeeObj.setEmpEmailPersonal(employeePersonalEmail);
		employeeObj.setEmpOfficeLocation(employeeOfficeLocation);
		employeeObj.setEmpBloodGroup(employeeBloodGroup);
		employeeObj.setEmpDOB(employeeDOB);
		employeeObj.setEmpAddress(employeeAddress);

		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(employeeObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Employee object to JSON", e);
		}
	}

	public static String getSingleEmployeePayload(String fakeUserId) {
		EmployeePojo employeeObj = new EmployeePojo();
		employeeObj.setUserId(fakeUserId);

		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(employeeObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Employee object to JSON", e);
		}
	}

	public static String getAssigneePayload(int roleLevel) {
		RolePojo roleObj = new RolePojo();
		roleObj.setRoleLevel(roleLevel);

		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(roleObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Role object to JSON", e);
		}
	}

	public static String getTaskOwnersPayload(int roleLevel) {
		RolePojo roleObj = new RolePojo();
		roleObj.setRoleLevel(roleLevel);

		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(roleObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Role object to JSON", e);
		}
	}

	public static String updatePasswordPayload(String loginId, String oldPassword, String newPassword,
			String confirmPassword) {
		EmployeePojo employeeObj = new EmployeePojo();
		employeeObj.setLoginId(loginId);
		employeeObj.setOldPassword(oldPassword);
		employeeObj.setNewPassword(newPassword);
		employeeObj.setConfirmPassword(confirmPassword);

		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(employeeObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Employee object to JSON", e);
		}
	}

	public static String giveEmployeePayloadForSearchEmployeeInLevel(String key, String role, String date) {
		EmployeePojo employeeObj = new EmployeePojo();
		employeeObj.setKey(key);
		employeeObj.setRoleName(role);
		employeeObj.setDate(date);

		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(employeeObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Employee object to JSON", e);
		}
	}

	public static String giveEmployeePayloadForAddToken(String token, String userId) {
		EmployeePojo employeeObj = new EmployeePojo();
		employeeObj.setToken(token);
		employeeObj.setUserId(userId);

		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(employeeObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Employee object to JSON", e);
		}
	}
}

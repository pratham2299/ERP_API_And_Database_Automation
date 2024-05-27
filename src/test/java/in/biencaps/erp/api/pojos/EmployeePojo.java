package in.biencaps.erp.api.pojos;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class EmployeePojo {
	private int empId;
	private String userId;
	private String empFullName;
	private String empStatus;
	private Designation designation;
	private Department department;
	private List<Role> role;
	private List<ReportingAuthority> reportingAuthorities;
	private String empEmailOfficial;
	private String empEmailPersonal;
	private String empMobile1;
	private String empOfficeLocation;
	private String empJoiningDate;
	private String empMobile2; // optional
	private String empDOB; // optional
	private String empBloodGroup; // optional
	private String empAddress; // optional
	private String loginId;
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	private String key;
	private String roleName;
	private String date;
	private String token;

	public EmployeePojo() {
	}

	// Getters and setters

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpFullName() {
		return empFullName;
	}

	public void setEmpFullName(String empFullName) {
		this.empFullName = empFullName;
	}

	public String getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Role> getRole() {
		return role;
	}

	public void setRole(List<Role> role) {
		this.role = role;
	}

	public List<ReportingAuthority> getReportingAuthorities() {
		return reportingAuthorities;
	}

	public void setReportingAuthorities(List<ReportingAuthority> reportingAuthorities) {
		this.reportingAuthorities = reportingAuthorities;
	}

	public String getEmpEmailOfficial() {
		return empEmailOfficial;
	}

	public void setEmpEmailOfficial(String empEmailOfficial) {
		this.empEmailOfficial = empEmailOfficial;
	}

	public String getEmpEmailPersonal() {
		return empEmailPersonal;
	}

	public void setEmpEmailPersonal(String empEmailPersonal) {
		this.empEmailPersonal = empEmailPersonal;
	}

	public String getEmpMobile1() {
		return empMobile1;
	}

	public void setEmpMobile1(String empMobile1) {
		this.empMobile1 = empMobile1;
	}

	public String getEmpOfficeLocation() {
		return empOfficeLocation;
	}

	public void setEmpOfficeLocation(String empOfficeLocation) {
		this.empOfficeLocation = empOfficeLocation;
	}

	public String getEmpJoiningDate() {
		return empJoiningDate;
	}

	public void setEmpJoiningDate(String empJoiningDate) {
		this.empJoiningDate = empJoiningDate;
	}

	public String getEmpMobile2() {
		return empMobile2;
	}

	public void setEmpMobile2(String empMobile2) {
		this.empMobile2 = empMobile2;
	}

	public String getEmpDOB() {
		return empDOB;
	}

	public void setEmpDOB(String empDOB) {
		this.empDOB = empDOB;
	}

	public String getEmpBloodGroup() {
		return empBloodGroup;
	}

	public void setEmpBloodGroup(String empBloodGroup) {
		this.empBloodGroup = empBloodGroup;
	}

	public String getEmpAddress() {
		return empAddress;
	}

	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	// Inner classes for nested objects
	public static class Designation {
		private int designationId;

		// Getters and setters
		public int getDesignationId() {
			return designationId;
		}

		public void setDesignationId(int designationId) {
			this.designationId = designationId;
		}
	}

	public static class Department {
		private int departmentId;

		// Getters and setters
		public int getDepartmentId() {
			return departmentId;
		}

		public void setDepartmentId(int departmentId) {
			this.departmentId = departmentId;
		}
	}

	public static class Role {
		private int roleId;

		// Getters and setters
		public int getRoleId() {
			return roleId;
		}

		public void setRoleId(int roleId) {
			this.roleId = roleId;
		}
	}

	public static class ReportingAuthority {
		private int empId;

		// Getters and setters
		public int getEmpId() {
			return empId;
		}

		public void setEmpId(int empId) {
			this.empId = empId;
		}
	}
}

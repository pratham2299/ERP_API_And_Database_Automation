package in.biencaps.erp.pojos;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class EmployeePojo {
	private int empId;
	private String userId;
	private String empFullName;
	private String empStatus;
	private DesignationPojo designation;
	private DepartmentPojo department;
	private List<RolePojo> role;
	private List<ReportingAuthorityPojo> reportingAuthorities;
	private String empEmailOfficial;
	private String empEmailPersonal;
	private String empMobile1;
	private String empMobile2; // Optional
	private String empOfficeLocation;
	private String empJoiningDate;
	private String empDOB; // Optional
	private String empBloodGroup; // Optional
	private String empAddress; // Optional
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

	// Getters and Setters
	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	// Getters and setters
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public DesignationPojo getDesignation() {
		return designation;
	}

	public void setDesignation(DesignationPojo designation) {
		this.designation = designation;
	}

	public DepartmentPojo getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentPojo department) {
		this.department = department;
	}

	public List<RolePojo> getRole() {
		return role;
	}

	public void setRole(List<RolePojo> role) {
		this.role = role;
	}

	public List<ReportingAuthorityPojo> getReportingAuthorities() {
		return reportingAuthorities;
	}

	public void setReportingAuthorities(List<ReportingAuthorityPojo> reportingAuthorities) {
		this.reportingAuthorities = reportingAuthorities;
	}

	public String getEmpEmailPersonal() {
		return empEmailPersonal;
	}

	public void setEmpEmailPersonal(String empEmailPersonal) {
		this.empEmailPersonal = empEmailPersonal;
	}

	public String getEmpEmailOfficial() {
		return empEmailOfficial;
	}

	public void setEmpEmailOfficial(String empEmailOfficial) {
		this.empEmailOfficial = empEmailOfficial;
	}

	public String getEmpMobile1() {
		return empMobile1;
	}

	public void setEmpMobile1(String empMobile1) {
		this.empMobile1 = empMobile1;
	}

	public String getEmpMobile2() {
		return empMobile2;
	}

	public void setEmpMobile2(String empMobile2) {
		this.empMobile2 = empMobile2;
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
}

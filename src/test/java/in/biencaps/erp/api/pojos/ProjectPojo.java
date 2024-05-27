package in.biencaps.erp.api.pojos;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ProjectPojo {
	private int projectId;
	private String projectName;
	private String projectStartDate;
	private String projectEndDate;
	private ProjectManager projectManager;
	private ProjectStatus projectStatus;
	private ProjectPriority projectPriority;
	private List<Department> projectDepartments;
	private List<Employee> projectEmployees;
	private String key;
	private String password;

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	// Getters and setters
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectStartDate() {
		return projectStartDate;
	}

	public void setProjectStartDate(String projectStartDate) {
		this.projectStartDate = projectStartDate;
	}

	public String getProjectEndDate() {
		return projectEndDate;
	}

	public void setProjectEndDate(String projectEndDate) {
		this.projectEndDate = projectEndDate;
	}

	public ProjectManager getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(ProjectManager projectManager) {
		this.projectManager = projectManager;
	}

	public ProjectStatus getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(ProjectStatus projectStatus) {
		this.projectStatus = projectStatus;
	}

	public ProjectPriority getProjectPriority() {
		return projectPriority;
	}

	public void setProjectPriority(ProjectPriority projectPriority) {
		this.projectPriority = projectPriority;
	}

	public List<Department> getProjectDepartments() {
		return projectDepartments;
	}

	public void setProjectDepartments(List<Department> projectDepartments) {
		this.projectDepartments = projectDepartments;
	}

	public List<Employee> getProjectEmployees() {
		return projectEmployees;
	}

	public void setProjectEmployees(List<Employee> projectEmployees) {
		this.projectEmployees = projectEmployees;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// Inner classes for nested objects
	public static class ProjectManager {
		private int empId;

		// Getters and setters
		public int getEmpId() {
			return empId;
		}

		public void setEmpId(int empId) {
			this.empId = empId;
		}
	}

	public static class ProjectStatus {
		private int projectStatusId;

		// Getters and setters
		public int getProjectStatusId() {
			return projectStatusId;
		}

		public void setProjectStatusId(int projectStatusId) {
			this.projectStatusId = projectStatusId;
		}
	}

	public static class ProjectPriority {
		private int priorityId;

		// Getters and setters
		public int getPriorityId() {
			return priorityId;
		}

		public void setPriorityId(int priorityId) {
			this.priorityId = priorityId;
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

	public static class Employee {
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

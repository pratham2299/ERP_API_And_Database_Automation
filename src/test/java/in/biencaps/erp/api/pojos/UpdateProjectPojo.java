package in.biencaps.erp.api.pojos;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)

public class UpdateProjectPojo {
	private int projectId;
	private String projectName;
	private String projectStartDate;
	private String projectEndDate;
	private Integer projectManager;
	private Integer projectStatus;
	private List<Integer> projectDepartments;
	private List<Integer> projectEmployees;
	private Integer projectPriority;

	// Getters and setters
	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

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

	public Integer getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(Integer projectManager) {
		this.projectManager = projectManager;
	}

	public Integer getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(Integer projectStatus) {
		this.projectStatus = projectStatus;
	}

	public List<Integer> getProjectDepartments() {
		return projectDepartments;
	}

	public void setProjectDepartments(List<Integer> projectDepartments) {
		this.projectDepartments = projectDepartments;
	}

	public List<Integer> getProjectEmployees() {
		return projectEmployees;
	}

	public void setProjectEmployees(List<Integer> projectEmployees) {
		this.projectEmployees = projectEmployees;
	}

	public Integer getProjectPriority() {
		return projectPriority;
	}

	public void setProjectPriority(Integer projectPriority) {
		this.projectPriority = projectPriority;
	}
}

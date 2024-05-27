package in.biencaps.erp.api.payloads;

import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.biencaps.erp.api.pojos.*;

public class ProjectFolderPayloads {
	static ObjectMapper objectMapper = new ObjectMapper();
	static Random random = new Random();

	public static String getSingleProjectByProjectIdPayload(int projectId) {
		ProjectPojo projectObj = new ProjectPojo();
		projectObj.setProjectId(projectId);

		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(projectObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Project object to JSON", e);
		}
	}

	public static String addProjectPayload(String projectName, String projectStartDate, String projectEndDate,
			int projectManagerEmployeeId, int projectStatusId, int projectPriorityId, int projectDepartment1,
			int projectDepartment2, int projectEmployee1, int projectEmployee2) {
		// Project object creation
		ProjectPojo projectObj = new ProjectPojo();
		projectObj.setProjectName(projectName);
		projectObj.setProjectStartDate(projectStartDate);
		projectObj.setProjectEndDate(projectEndDate);

		// Project manager object creation
		ProjectPojo.ProjectManager manager = new ProjectPojo.ProjectManager();
		manager.setEmpId(projectManagerEmployeeId);
		projectObj.setProjectManager(manager);

		// Project status object creation
		ProjectPojo.ProjectStatus projectStatus = new ProjectPojo.ProjectStatus();
		projectStatus.setProjectStatusId(projectStatusId);
		projectObj.setProjectStatus(projectStatus);

		// Project priority object creation
		ProjectPojo.ProjectPriority projectPriority = new ProjectPojo.ProjectPriority();
		projectPriority.setPriorityId(projectPriorityId);
		projectObj.setProjectPriority(projectPriority);

		// Project departments object creation
		List<ProjectPojo.Department> departments = new ArrayList<>();
		ProjectPojo.Department department1 = new ProjectPojo.Department();
		department1.setDepartmentId(projectDepartment1);
		departments.add(department1);

		ProjectPojo.Department department2 = new ProjectPojo.Department();
		department2.setDepartmentId(projectDepartment2);
		departments.add(department2);

		projectObj.setProjectDepartments(departments);

		List<ProjectPojo.Employee> employees = new ArrayList<>();
		ProjectPojo.Employee employee1 = new ProjectPojo.Employee();
		employee1.setEmpId(projectEmployee1);
		employees.add(employee1);

		ProjectPojo.Employee employee2 = new ProjectPojo.Employee();
		employee2.setEmpId(projectEmployee2);
		employees.add(employee2);

		projectObj.setProjectEmployees(employees);

		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(projectObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Project object to JSON", e);
		}
	}

	public static String updateProjectPayload(int projectId, String projectName, String projectStartDate,
			String projectEndDate, int projectManagerEmployeeId, int projectStatusId, int projectPriorityId,
			int projectDepartment1, int projectDepartment2, int projectEmployee1, int projectEmployee2) {
		// Project object creation
		UpdateProjectPojo projectObj = new UpdateProjectPojo();
		projectObj.setProjectId(projectId);
		projectObj.setProjectName(projectName);
		projectObj.setProjectStartDate(projectStartDate);
		projectObj.setProjectEndDate(projectEndDate);

		// Project manager
		projectObj.setProjectManager(projectManagerEmployeeId);

		projectObj.setProjectStatus(projectStatusId);

		// Project priority
		projectObj.setProjectPriority(projectPriorityId);

		// Project departments
		projectObj.setProjectDepartments(Arrays.asList(projectDepartment1, projectDepartment2));

		projectObj.setProjectEmployees(Arrays.asList(projectEmployee1, projectEmployee2));

		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(projectObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Project object to JSON", e);
		}
	}

	public static String getAllProjectAssigneePayload(List<Integer> departmentForProject) {
		DepartmentPojo departmentObj = new DepartmentPojo();
		departmentObj.setDepartment(departmentForProject);

		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(departmentObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Project object to JSON", e);
		}
	}

	public static String getAllProjectsForAnUserIdPayload(String userId) {
		EmployeePojo employeeObj = new EmployeePojo();
		employeeObj.setUserId(userId);

		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(employeeObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Project object to JSON", e);
		}
	}

	public static String getAllTasksForProjectPayload(int projectId) {
		ProjectPojo projectObj = new ProjectPojo();
		projectObj.setProjectId(projectId);

		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(projectObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Project object to JSON", e);
		}
	}

	public static String getAllTasksForProjectSearchPayload(int projectId, String key) {
		ProjectPojo projectObj = new ProjectPojo();
		projectObj.setProjectId(projectId);
		projectObj.setKey(key);

		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(projectObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Project object to JSON", e);
		}
	}

	public static String deleteProjectPayload(int projectId, String password) {
		ProjectPojo projectObj = new ProjectPojo();
		projectObj.setProjectId(projectId);
		projectObj.setPassword(password);

		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(projectObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Project object to JSON", e);
		}
	}
}

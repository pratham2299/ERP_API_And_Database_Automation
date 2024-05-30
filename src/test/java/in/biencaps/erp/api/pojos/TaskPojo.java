package in.biencaps.erp.api.pojos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class TaskPojo {
	private int taskId;
	private int empId;
	private List<Integer> employee;
	private List<String> taskTitle;
	private String taskDone;
	private int taskVerificationStatus;
	private int taskPriority;
	private int taskStatus;
	private int taskProject;
	private String taskGitLink;
	private String taskComment;
	private List<Integer> taskTags;
	private String taskScheduleDate;
	private String taskDueDate;
	private String taskTimeFrom;
	private String taskTimeTo;
	private int taskOwner;
	private String date;
	private List<Integer> task;
	private String loggedInUsr;
	private String key;
	private int year;
	private int month;
	private int weekNumber;

	public TaskPojo() {
	}

	// Getters and setters
	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getTaskOwner() {
		return taskOwner;
	}

	public void setTaskOwner(int taskOwner) {
		this.taskOwner = taskOwner;
	}

	public List<Integer> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Integer> employee) {
		this.employee = employee;
	}

	public List<String> getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(List<String> taskTitle) {
		this.taskTitle = taskTitle;
	}

	public String getTaskDone() {
		return taskDone;
	}

	public void setTaskDone(String taskDone) {
		this.taskDone = taskDone;
	}

	public int getTaskVerificationStatus() {
		return taskVerificationStatus;
	}

	public void setTaskVerificationStatus(int taskVerificationStatus) {
		this.taskVerificationStatus = taskVerificationStatus;
	}

	public int getTaskPriority() {
		return taskPriority;
	}

	public void setTaskPriority(int taskPriority) {
		this.taskPriority = taskPriority;
	}

	public int getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(int taskStatus) {
		this.taskStatus = taskStatus;
	}

	public int getTaskProject() {
		return taskProject;
	}

	public void setTaskProject(int taskProject) {
		this.taskProject = taskProject;
	}

	public String getTaskGitLink() {
		return taskGitLink;
	}

	public void setTaskGitLink(String taskGitLink) {
		this.taskGitLink = taskGitLink;
	}

	public String getTaskComment() {
		return taskComment;
	}

	public void setTaskComment(String taskComment) {
		this.taskComment = taskComment;
	}

	public List<Integer> getTaskTags() {
		return taskTags;
	}

	public void setTaskTags(List<Integer> taskTags) {
		this.taskTags = taskTags;
	}

	public String getTaskScheduleDate() {
		return taskScheduleDate;
	}

	public void setTaskScheduleDate(String taskScheduleDate) {
		this.taskScheduleDate = taskScheduleDate;
	}

	public String getTaskDueDate() {
		return taskDueDate;
	}

	public void setTaskDueDate(String taskDueDate) {
		this.taskDueDate = taskDueDate;
	}

	public String getTaskTimeFrom() {
		return taskTimeFrom;
	}

	public void setTaskTimeFrom(String taskTimeFrom) {
		this.taskTimeFrom = taskTimeFrom;
	}

	public String getTaskTimeTo() {
		return taskTimeTo;
	}

	public void setTaskTimeTo(String taskTimeTo) {
		this.taskTimeTo = taskTimeTo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<Integer> getTask() {
		return task;
	}

	public void setTask(List<Integer> task) {
		this.task = task;
	}

	public String getLoggedInUsr() {
		return loggedInUsr;
	}

	public void setLoggedInUsr(String loggedInUsr) {
		this.loggedInUsr = loggedInUsr;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getWeekNumber() {
		return weekNumber;
	}

	public void setWeekNumber(int weekNumber) {
		this.weekNumber = weekNumber;
	}

}

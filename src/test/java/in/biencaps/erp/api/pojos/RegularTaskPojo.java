package in.biencaps.erp.api.pojos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class RegularTaskPojo {
	private int regularTaskId;
	private String regularTaskName;
	private EmployeePojo employee;

	// Default constructor
	public RegularTaskPojo() {
	}

	// Getters and Setters
	public int getRegularTaskId() {
		return regularTaskId;
	}

	public void setRegularTaskId(int regularTaskId) {
		this.regularTaskId = regularTaskId;
	}

	public String getRegularTaskName() {
		return regularTaskName;
	}

	public void setRegularTaskName(String regularTaskName) {
		this.regularTaskName = regularTaskName;
	}

	public EmployeePojo getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeePojo employee) {
		this.employee = employee;
	}
}

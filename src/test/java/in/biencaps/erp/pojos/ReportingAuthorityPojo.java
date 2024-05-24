package in.biencaps.erp.pojos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ReportingAuthorityPojo {
	private int empId;

	// Default constructor
	public ReportingAuthorityPojo() {
	}

	// Getters and setters
	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}
}

package in.biencaps.erp.pojos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class DepartmentPojo {
	private int departmentId;

	private String departmentName;

	private int departmentLevel;

	private String departmentColor;

	private String departmentColorCode;

	// Default constructor
	public DepartmentPojo() {
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public int getDepartmentLevel() {
		return departmentLevel;
	}

	public void setDepartmentLevel(int departmentLevel) {
		this.departmentLevel = departmentLevel;
	}

	public String getDepartmentColor() {
		return departmentColor;
	}

	public void setDepartmentColor(String departmentColor) {
		this.departmentColor = departmentColor;
	}

	public String getDepartmentColorCode() {
		return departmentColorCode;
	}

	public void setDepartmentColorCode(String departmentColorCode) {
		this.departmentColorCode = departmentColorCode;
	}
}

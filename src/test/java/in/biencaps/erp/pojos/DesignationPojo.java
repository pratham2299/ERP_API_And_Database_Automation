package in.biencaps.erp.pojos;

public class DesignationPojo {
	private int designationId;
	private String designation;
	private Department department;
	private DepartmentPojo randomDepartment;

	// Getters and Setters
	public int getDesignationId() {
		return designationId;
	}

	public void setDesignationId(int designationId) {
		this.designationId = designationId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public DepartmentPojo getDepartmentpojo() {
		return randomDepartment;
	}

	public void setDepartmentPojo(DepartmentPojo departmentPojo) {
		this.randomDepartment = departmentPojo;
	}

	public static class Department {
		private int departmentId;
		private String departmentName;
		private int departmentLevel;
		private String departmentColor;
		private String departmentColorCode;

		// Getters and Setters
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
}

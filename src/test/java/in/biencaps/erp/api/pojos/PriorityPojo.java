package in.biencaps.erp.api.pojos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class PriorityPojo {
	private int priorityId;

	private String priority;

	private int priorityLevel;

	private String priorityColor;

	private String priorityColorCode;

	// Default constructor
	public PriorityPojo() {
	}

	// Parameterized constructor
	public PriorityPojo(String priority, int priorityLevel, String priorityColor, String priorityColorCode) {
		this.priority = priority;
		this.priorityLevel = priorityLevel;
		this.priorityColor = priorityColor;
		this.priorityColorCode = priorityColorCode;
	}

	public int getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(int priorityId) {
		this.priorityId = priorityId;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public int getPriorityLevel() {
		return priorityLevel;
	}

	public void setPriorityLevel(int priorityLevel) {
		this.priorityLevel = priorityLevel;
	}

	public String getPriorityColor() {
		return priorityColor;
	}

	public void setPriorityColor(String priorityColor) {
		this.priorityColor = priorityColor;
	}

	public String getPriorityColorCode() {
		return priorityColorCode;
	}

	public void setPriorityColorCode(String priorityColorCode) {
		this.priorityColorCode = priorityColorCode;
	}

}

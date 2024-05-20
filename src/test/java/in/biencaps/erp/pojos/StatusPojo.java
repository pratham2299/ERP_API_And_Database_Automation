package in.biencaps.erp.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatusPojo {
	@JsonProperty("statusId")
	private int statusId;

	@JsonProperty("status")
	private String status;

	@JsonProperty("statusLevel")
	private int statusLevel;

	@JsonProperty("statusColor")
	private String statusColor;

	@JsonProperty("statusColorCode")
	private String statusColorCode;

	// Default constructor
	public StatusPojo() {
	}

	// Parameterized constructor
	public StatusPojo(String status, int statusLevel, String statusColor, String statusColorCode) {
		this.status = status;
		this.statusLevel = statusLevel;
		this.statusColor = statusColor;
		this.statusColorCode = statusColorCode;
	}

	// Getters and Setters
	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getStatusLevel() {
		return statusLevel;
	}

	public void setStatusLevel(int statusLevel) {
		this.statusLevel = statusLevel;
	}

	public String getStatusColor() {
		return statusColor;
	}

	public void setStatusColor(String statusColor) {
		this.statusColor = statusColor;
	}

	public String getStatusColorCode() {
		return statusColorCode;
	}

	public void setStatusColorCode(String statusColorCode) {
		this.statusColorCode = statusColorCode;
	}
}
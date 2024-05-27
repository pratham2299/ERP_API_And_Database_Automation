package in.biencaps.erp.api.pojos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class StatusPojo {
	private int statusId;

	private String status;

	private int statusLevel;

	private String statusColor;

	private String statusColorCode;

	// Default constructor
	public StatusPojo() {
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
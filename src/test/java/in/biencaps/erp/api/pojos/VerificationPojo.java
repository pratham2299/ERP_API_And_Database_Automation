package in.biencaps.erp.api.pojos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class VerificationPojo {
	private int verificationStatusId;
	private String verificationStatus;
	private int verificationLevel;
	private String verificationColor;
	private String verificationColorCode;

	// Default constructor
	public VerificationPojo() {
	}

	public int getVerificationStatusId() {
		return verificationStatusId;
	}

	public void setVerificationStatusId(int verificationStatusId) {
		this.verificationStatusId = verificationStatusId;
	}

	public String getVerificationStatus() {
		return verificationStatus;
	}

	public void setVerificationStatus(String verificationStatus) {
		this.verificationStatus = verificationStatus;
	}

	public int getVerificationLevel() {
		return verificationLevel;
	}

	public void setVerificationLevel(int verificationLevel) {
		this.verificationLevel = verificationLevel;
	}

	public String getVerificationColor() {
		return verificationColor;
	}

	public void setVerificationColor(String verificationColor) {
		this.verificationColor = verificationColor;
	}

	public String getVerificationColorCode() {
		return verificationColorCode;
	}

	public void setVerificationColorCode(String verificationColorCode) {
		this.verificationColorCode = verificationColorCode;
	}
}

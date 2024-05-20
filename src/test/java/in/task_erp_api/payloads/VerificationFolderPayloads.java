package in.task_erp_api.payloads;

import java.util.*;

import com.google.gson.Gson;

public class VerificationFolderPayloads {
	private static Gson gson = new Gson();

	public static String giveVerificationStatusPayloadForAddVerificationStatus(String verificationStatus,
			int verificationLevel, String verificationStatusColor, String verificationStatusColorCode) {
		HashMap<String, Object> verificationStatusMap = new HashMap<>();
		verificationStatusMap.put("verificationStatus", verificationStatus);
		verificationStatusMap.put("verificationLevel", verificationLevel);
		verificationStatusMap.put("verificationColor", verificationStatusColor);
		verificationStatusMap.put("verificationColorCode", verificationStatusColorCode);

		String payload = gson.toJson(verificationStatusMap);
		return payload;
	}

	public static String giveVerificationStatusPayloadForUpdateVerificationStatus(int verificationStatusId,
			String verificationStatus, int verificationLevel, String verificationStatusColor,
			String verificationStatusColorCode) {
		HashMap<String, Object> verificationStatusMap = new HashMap<>();
		verificationStatusMap.put("verificationStatusId", verificationStatusId);
		verificationStatusMap.put("verificationStatus", verificationStatus);
		verificationStatusMap.put("verificationLevel", verificationLevel);
		verificationStatusMap.put("verificationColor", verificationStatusColor);
		verificationStatusMap.put("verificationColorCode", verificationStatusColorCode);

		String payload = gson.toJson(verificationStatusMap);
		return payload;
	}
}

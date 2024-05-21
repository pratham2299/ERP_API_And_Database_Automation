package in.task_erp_api.payloads;

import java.util.*;
import java.util.function.Supplier;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.biencaps.erp.pojos.*;

public class VerificationFolderPayloads {
	private static ObjectMapper objectMapper = new ObjectMapper();

	public static String addVerificationPayload(String verificationName, int verificationLevel,
			String verificationColor, String verificationColorCode) {
		VerificationPojo VerificationObj = new VerificationPojo();

		// Update the fields of the object with the maximum VerificationId
		VerificationObj.setVerificationStatus(verificationName);
		VerificationObj.setVerificationLevel(verificationLevel);
		VerificationObj.setVerificationColor(verificationColor);
		VerificationObj.setVerificationColorCode(verificationColorCode);
		try {
			return objectMapper.writeValueAsString(VerificationObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert verification object to JSON", e);
		}
	}

	public static String updateVerificationWithMaxIdPayload(String jsonResponse, int newVerificationId,
			String newVerificationName, int newVerificationLevel, String newVerificationColor,
			String newVerificationColorCode) throws Throwable {
		// Deserialize JSON array to List<Verification>
		List<VerificationPojo> VerificationList = objectMapper.readValue(jsonResponse,
				new TypeReference<List<VerificationPojo>>() {
				});

		// Find the object with the maximum VerificationId
		// Find the object with the maximum PriorityId
		VerificationPojo maxVerificationIdObject = VerificationList.stream().max(new Comparator<VerificationPojo>() {
			@Override
			public int compare(VerificationPojo s1, VerificationPojo s2) {
				return Integer.compare(s1.getVerificationStatusId(), s2.getVerificationStatusId());
			}
		}).orElseThrow(new Supplier<Throwable>() {
			@Override
			public Throwable get() {
				return new RuntimeException("No Verification found");
			}
		});

		// Update the fields of the object with the maximum VerificationId
		maxVerificationIdObject.setVerificationStatusId(newVerificationId);
		maxVerificationIdObject.setVerificationStatus(newVerificationName);
		maxVerificationIdObject.setVerificationLevel(newVerificationLevel);
		maxVerificationIdObject.setVerificationColor(newVerificationColor);
		maxVerificationIdObject.setVerificationColorCode(newVerificationColorCode);

		// Serialize the updated list back to JSON
		return objectMapper.writeValueAsString(VerificationList);
	}

}

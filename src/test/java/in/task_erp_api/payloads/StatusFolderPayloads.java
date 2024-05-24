package in.task_erp_api.payloads;

import java.util.*;
import java.util.function.Supplier;

import com.fasterxml.jackson.core.type.*;
import com.fasterxml.jackson.databind.*;

import in.biencaps.erp.pojos.*;

public class StatusFolderPayloads {
	// Create ObjectMapper instance
	static ObjectMapper objectMapper = new ObjectMapper();

	public static String addStatusPayload(String status, int statusLevel, String statusColor, String statusColorCode) {
		StatusPojo statusObj = new StatusPojo();
		statusObj.setStatus(status);
		statusObj.setStatusLevel(statusLevel);
		statusObj.setStatusColor(statusColor);
		statusObj.setStatusColorCode(statusColorCode);

		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(statusObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Status object to JSON", e);
		}
	}

	public static String updateStatusWithMaxIdPayload(String jsonResponse, int newStatusId, String newStatus,
			int newStatusLevel, String newStatusColor, String newStatusColorCode) throws Throwable {
		// Deserialize JSON array to List<Status>
		List<StatusPojo> statusList = objectMapper.readValue(jsonResponse, new TypeReference<List<StatusPojo>>() {
		});

		// Find the object with the maximum statusId
		StatusPojo maxStatusIdObject = statusList.stream().max(new Comparator<StatusPojo>() {
			@Override
			public int compare(StatusPojo s1, StatusPojo s2) {
				return Integer.compare(s1.getStatusId(), s2.getStatusId());
			}
		}).orElseThrow(new Supplier<Throwable>() {
			@Override
			public Throwable get() {
				return new RuntimeException("No status found");
			}
		});

		// Update the fields of the object with the maximum statusId
		maxStatusIdObject.setStatusId(newStatusId);
		maxStatusIdObject.setStatus(newStatus);
		maxStatusIdObject.setStatusLevel(newStatusLevel);
		maxStatusIdObject.setStatusColor(newStatusColor);
		maxStatusIdObject.setStatusColorCode(newStatusColorCode);

		// Serialize the updated list back to JSON
		return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(statusList);
	}
}

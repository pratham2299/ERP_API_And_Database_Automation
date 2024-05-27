package in.biencaps.erp.api.payloads;

import java.util.*;
import java.util.function.Supplier;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.biencaps.erp.api.pojos.PriorityPojo;

public class PriorityFolderPayloads {
	private static ObjectMapper objectMapper = new ObjectMapper();

	public static String addPriorityPayload(String priority, int priorityLevel, String priorityColor,
			String priorityColorCode) {
		PriorityPojo priorityObj = new PriorityPojo(priority, priorityLevel, priorityColor, priorityColorCode);
		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(priorityObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Priority object to JSON", e);
		}
	}

	public static String updatePriorityWithMaxIdPayload(String jsonResponse, int newPriorityId, String newPriority,
			int newPriorityLevel, String newPriorityColor, String newPriorityColorCode) throws Throwable {
		// Deserialize JSON array to List<Priority>
		List<PriorityPojo> priorityList = objectMapper.readValue(jsonResponse, new TypeReference<List<PriorityPojo>>() {
		});

		// Find the object with the maximum PriorityId
		PriorityPojo maxPriorityIdObject = priorityList.stream().max(new Comparator<PriorityPojo>() {
			@Override
			public int compare(PriorityPojo s1, PriorityPojo s2) {
				return Integer.compare(s1.getPriorityId(), s2.getPriorityId());
			}
		}).orElseThrow(new Supplier<Throwable>() {
			@Override
			public Throwable get() {
				return new RuntimeException("No Priority found");
			}
		});

		// Update the fields of the object with the maximum PriorityId
		maxPriorityIdObject.setPriorityId(newPriorityId);
		maxPriorityIdObject.setPriority(newPriority);
		maxPriorityIdObject.setPriorityLevel(newPriorityLevel);
		maxPriorityIdObject.setPriorityColor(newPriorityColor);
		maxPriorityIdObject.setPriorityColorCode(newPriorityColorCode);

		// Serialize the updated list back to JSON
		return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(priorityList);
	}
}

package in.task_erp_api.payloads;

import java.util.*;
import java.util.function.Supplier;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import in.biencaps.erp.pojos.*;

public class DepartmentFolderPayloads {
	private static Gson gson = new Gson();
	private static ObjectMapper objectMapper = new ObjectMapper();

	public static String addDepartmentPayload(String designationName, int designationLevel, String designationColor,
			String designationColorCode) {
		DepartmentPojo departmentObj = new DepartmentPojo();

		// Update the fields of the object with the maximum departmentId
		departmentObj.setDepartmentName(designationName);
		departmentObj.setDepartmentLevel(designationLevel);
		departmentObj.setDepartmentColor(designationColor);
		departmentObj.setDepartmentColorCode(designationColorCode);
		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(departmentObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Department object to JSON", e);
		}
	}

	public static String updateDepartmentWithMaxIdPayload(String jsonResponse, int newDepartmentId,
			String newDepartmentName, int newDepartmentLevel, String newDepartmentColor, String newDepartmentColorCode)
			throws Throwable {
		// Deserialize JSON array to List<Department>
		List<DepartmentPojo> departmentList = objectMapper.readValue(jsonResponse,
				new TypeReference<List<DepartmentPojo>>() {
				});

		// Find the object with the maximum departmentId
		// Find the object with the maximum PriorityId
		DepartmentPojo maxDepartmentIdObject = departmentList.stream().max(new Comparator<DepartmentPojo>() {
			@Override
			public int compare(DepartmentPojo s1, DepartmentPojo s2) {
				return Integer.compare(s1.getDepartmentId(), s2.getDepartmentId());
			}
		}).orElseThrow(new Supplier<Throwable>() {
			@Override
			public Throwable get() {
				return new RuntimeException("No Department found");
			}
		});

		// Update the fields of the object with the maximum departmentId
		maxDepartmentIdObject.setDepartmentId(newDepartmentId);
		maxDepartmentIdObject.setDepartmentName(newDepartmentName);
		maxDepartmentIdObject.setDepartmentLevel(newDepartmentLevel);
		maxDepartmentIdObject.setDepartmentColor(newDepartmentColor);
		maxDepartmentIdObject.setDepartmentColorCode(newDepartmentColorCode);

		// Serialize the updated list back to JSON
		return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(departmentList);
	}

	public static String giveDepartmentPayloadForGetAssignee(int roleLevel) {
		HashMap<String, Integer> departmentMap = new HashMap<>();
		departmentMap.put("roleLevel", roleLevel);

		String payload = gson.toJson(departmentMap);
		return payload;
	}
}

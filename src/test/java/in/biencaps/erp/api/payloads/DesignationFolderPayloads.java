package in.biencaps.erp.api.payloads;

import java.util.*;
import java.util.function.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.biencaps.erp.api.pojos.*;

public class DesignationFolderPayloads {
	// Create ObjectMapper instance
	static ObjectMapper objectMapper = new ObjectMapper();

	public static String addDesignationPayload(String designation, int departmentId) {
		// Create the inner department entity
		DesignationPojo.Department departmentEntity = new DesignationPojo.Department();
		departmentEntity.setDepartmentId(departmentId);

		// Create the main designation object
		DesignationPojo designationObj = new DesignationPojo();
		designationObj.setDesignation(designation);
		designationObj.setDepartment(departmentEntity);
		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(designationObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Designation object to JSON", e);
		}
	}

	public static String updateDesignationWithMaxIdPayload(String jsonResponse, final int newDesignationId,
			final String newDesignation, final int newDepartmentId, final String newDepartmentName,
			final int newDepartmentLevel, final String newDepartmentColor, final String newDepartmentColorCode)
			throws Throwable {

		// Deserialize JSON array to List<Designation>
		List<DesignationPojo> designationList = objectMapper.readValue(jsonResponse,
				new TypeReference<List<DesignationPojo>>() {
				});

		// Find the designation with the maximum designationId
		Optional<DesignationPojo> maxDesignation = designationList.stream().max(new Comparator<DesignationPojo>() {
			@Override
			public int compare(DesignationPojo d1, DesignationPojo d2) {
				return Integer.compare(d1.getDesignationId(), d2.getDesignationId());
			}
		});

		// Update the values of the max designation if present
		maxDesignation.ifPresent(new Consumer<DesignationPojo>() {
			@Override
			public void accept(DesignationPojo designationList) {
				designationList.setDesignationId(newDesignationId);
				designationList.setDesignation(newDesignation);
				DesignationPojo.Department updatedDepartment = new DesignationPojo.Department();
				updatedDepartment.setDepartmentId(newDepartmentId);
				updatedDepartment.setDepartmentName(newDepartmentName);
				updatedDepartment.setDepartmentLevel(newDepartmentLevel);
				updatedDepartment.setDepartmentColor(newDepartmentColor);
				updatedDepartment.setDepartmentColorCode(newDepartmentColorCode);
				designationList.setDepartment(updatedDepartment);
			}
		});

		// Serialize the updated list back to JSON
		return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(designationList);
	}

	public static String getAllDesignationsByDepartmentPayload(int fakeDepartmentId) {
		DepartmentPojo departmentObj = new DepartmentPojo();
		departmentObj.setDepartmentId(fakeDepartmentId);

		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(departmentObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Department object to JSON", e);
		}
	}
}

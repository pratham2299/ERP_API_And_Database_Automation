package in.biencaps.erp.api.payloads;

import java.util.*;
import java.util.function.Supplier;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.biencaps.erp.api.pojos.*;

public class DesignationFolderPayloads {
	private static Random random = new Random();

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

	public static String updateDesignationWithMaxIdPayload(String jsonResponse, int newDesignationId,
			String newDesignation, List<DepartmentPojo> departments) throws Throwable {

		// Deserialize JSON array to List<Designation>
		List<DesignationPojo> DesignationList = objectMapper.readValue(jsonResponse,
				new TypeReference<List<DesignationPojo>>() {
				});

		// Find the object with the maximum DesignationId
		DesignationPojo maxDesignationIdObject = DesignationList.stream().max(new Comparator<DesignationPojo>() {
			@Override
			public int compare(DesignationPojo s1, DesignationPojo s2) {
				return Integer.compare(s1.getDesignationId(), s2.getDesignationId());
			}
		}).orElseThrow(new Supplier<Throwable>() {
			@Override
			public Throwable get() {
				return new RuntimeException("No Designation found");
			}
		});

		// Update the fields of the object with the maximum DesignationId
		maxDesignationIdObject.setDesignationId(newDesignationId);
		maxDesignationIdObject.setDesignation(newDesignation);

		DepartmentPojo randomDepartment = departments.get(random.nextInt(departments.size()));
		maxDesignationIdObject.setDepartmentPojo(randomDepartment);

		// Serialize the updated list back to JSON
		return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(DesignationList);
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

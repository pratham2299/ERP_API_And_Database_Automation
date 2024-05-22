package in.task_erp_api.payloads;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.biencaps.erp.pojos.RolePojo;

public class RoleFolderPayloads {
	// Create ObjectMapper instance
	static ObjectMapper objectMapper = new ObjectMapper();

	public static String addRolePayload(String role, int roleLevel) {
		RolePojo roleObj = new RolePojo();
		roleObj.setRole(role);
		roleObj.setRoleLevel(roleLevel);

		try {
			return objectMapper.writeValueAsString(roleObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Role object to JSON", e);
		}
	}

	public static String updateRoleWithMaxIdPayload(int roleId, String role, int roleLevel) throws Throwable {
		RolePojo roleObj = new RolePojo();
		roleObj.setRoleId(roleId);
		roleObj.setRole(role);
		roleObj.setRoleLevel(roleLevel);

		try {
			return objectMapper.writeValueAsString(roleObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Role object to JSON", e);
		}
	}

	public static String deleteRolePayload(int roleId) {
		RolePojo roleObj = new RolePojo();
		roleObj.setRoleId(roleId);

		try {
			return objectMapper.writeValueAsString(roleObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Role object to JSON", e);
		}
	}
}

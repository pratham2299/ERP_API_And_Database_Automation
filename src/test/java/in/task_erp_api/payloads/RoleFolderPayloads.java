package in.task_erp_api.payloads;

import java.util.HashMap;

import com.google.gson.Gson;

public class RoleFolderPayloads {
	private static Gson gson = new Gson();

	public static String giveRolePayloadForAddRole(String fakeRole, int fakeRoleLevel) {
		HashMap<String, Object> roleMap = new HashMap<>();
		roleMap.put("role", fakeRole);
		roleMap.put("roleLevel", fakeRoleLevel);

		String payload = gson.toJson(roleMap);
		return payload;
	}

	public static String giveRolePayloadForUpdateRole(int fakeRoleId, String fakeRole, int fakeRoleLevel) {
		HashMap<String, Object> roleMap = new HashMap<>();
		roleMap.put("roleId", fakeRoleId);
		roleMap.put("role", fakeRole);
		roleMap.put("roleLevel", fakeRoleLevel);

		String payload = gson.toJson(roleMap);
		return payload;
	}

	public static String giveRolePayloadForUpdateRole(int fakeRoleId, int fakeRoleLevel) {
		HashMap<String, Object> roleMap = new HashMap<>();
		roleMap.put("roleId", fakeRoleId);
		roleMap.put("roleLevel", fakeRoleLevel);

		String payload = gson.toJson(roleMap);
		return payload;
	}

	public static String giveRolePayloadForDeleteRole(int fakeRoleId) {
		HashMap<String, Integer> roleMap = new HashMap<>();
		roleMap.put("roleId", fakeRoleId);

		String payload = gson.toJson(roleMap);
		return payload;
	}
}

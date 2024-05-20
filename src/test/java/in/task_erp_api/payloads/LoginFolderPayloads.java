package in.task_erp_api.payloads;

import java.util.HashMap;

import com.google.gson.Gson;

public class LoginFolderPayloads {
	private static Gson gson = new Gson();

	public static String loginEmployee(String loginId, String password) {
		HashMap<String, Object> loginEmployeeMap = new HashMap<>();
		loginEmployeeMap.put("loginId", loginId);
		loginEmployeeMap.put("password", password);

		String payload = gson.toJson(loginEmployeeMap);
		return payload;
	}
}

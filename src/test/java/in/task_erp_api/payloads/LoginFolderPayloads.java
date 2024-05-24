package in.task_erp_api.payloads;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.biencaps.erp.pojos.LoginPojo;

public class LoginFolderPayloads {
	static ObjectMapper objectMapper = new ObjectMapper();

	public static String loginEmployeePayload(String loginId, String password) {
		LoginPojo loginObj = new LoginPojo();
		loginObj.setLoginId(loginId);
		loginObj.setPassword(password);

		try {
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(loginObj);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert Login object to JSON", e);
		}
	}
}

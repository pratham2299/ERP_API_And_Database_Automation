package in.biencaps.erp.api.payloads;

import java.util.HashMap;

import com.google.gson.Gson;

public class RequestFolderPayloads {
	private static Gson gson = new Gson();

	public static String giveRolePayloadForGetAllRequestsAnalytic(int employeeId) {
		HashMap<String, Object> requestMap = new HashMap<>();
		requestMap.put("empId", employeeId);

		String payload = gson.toJson(requestMap);
		return payload;
	}

	public static String giveRolePayloadForGetAllRequestsFromAnotherEmployeeForSingleEmployee(int employeeId) {
		HashMap<String, Object> requestMap = new HashMap<>();
		requestMap.put("empId", employeeId);

		String payload = gson.toJson(requestMap);
		return payload;
	}

	public static String giveRolePayloadForGetAllMySentRequests(String userId, String requestStatus) {
		HashMap<String, Object> requestMap = new HashMap<>();
		requestMap.put("userId", userId);
		requestMap.put("status", requestStatus);

		String payload = gson.toJson(requestMap);
		return payload;
	}

	public static String giveRolePayloadForUpdateRequestStatus(int requestId, int employeeId, int statusId) {
		HashMap<String, Object> requestMap = new HashMap<>();
		requestMap.put("requestId", requestId);
		requestMap.put("empId", employeeId);
		requestMap.put("statusId", statusId);

		String payload = gson.toJson(requestMap);
		return payload;
	}
}

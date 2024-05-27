package in.biencaps.erp.api.payloads;

import java.util.*;

import com.google.gson.Gson;

public class NotificationFolderPayloads {
	private static Gson gson = new Gson();

	public static String giveNotificationPayloadForUpdateNotificationReadStatus(String notificationId, String userId) {
		HashMap<String, Object> notificationMap = new HashMap<>();
		notificationMap.put("notificationId", notificationId);
		notificationMap.put("userId", userId);

		String payload = gson.toJson(notificationMap);
		return payload;
	}

}

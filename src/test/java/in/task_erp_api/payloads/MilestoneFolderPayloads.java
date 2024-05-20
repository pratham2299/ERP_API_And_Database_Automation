package in.task_erp_api.payloads;

import java.util.HashMap;

import com.google.gson.Gson;

public class MilestoneFolderPayloads {
	private static Gson gson = new Gson();

	public static String givemilestonePayloadForAddmilestone(int projectId) {
		HashMap<String, Object> milestoneMap = new HashMap<>();
		milestoneMap.put("projectId", projectId);

		String payload = gson.toJson(milestoneMap);
		return payload;
	}
}

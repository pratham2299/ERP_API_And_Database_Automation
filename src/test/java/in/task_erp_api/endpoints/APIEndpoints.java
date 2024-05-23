package in.task_erp_api.endpoints;

public interface APIEndpoints {
	String baseURL = "https://erp.biencaps.in:10004/erp";

	// Login Employee folder endpoints
	String loginEmployeeEndpoint = baseURL + "/auth/login";

	// Logs folder endpoints
	String getAllUserLogsEndpoint = baseURL + "/task/user/logs/get/all";

	// Status folder endpoints
	String addStatusEndpoint = baseURL + "/task/status/add";
	String getAllStatusesEndpoint = baseURL + "/task/status/get/all";
	String updateStatusEndpoint = baseURL + "/task/status/update";
	String deleteStatusEndpoint = baseURL + "/task/status/delete/single";

	// Priority folder endpoints
	String addPriorityEndpoint = baseURL + "/task/priority/add";
	String getAllPrioritiesEndpoint = baseURL + "/task/priority/get/all";
	String updatePriorityEndpoint = baseURL + "/task/priority/update";
	String deletePriorityEndpoint = baseURL + "/task/priority/delete/single";

	// Designation folder endpoints
	String addDesignationEndpoint = baseURL + "/employee/designation/add";
	String getAllDesignationsEndpoint = baseURL + "/employee/designation/all";
	String updateDesignationEndpoint = baseURL + "/employee/designation/update";
	String deleteDesignationEndpoint = baseURL + "/employee/designation/delete";
	String getAllDesignationByDepartmentEndpoint = baseURL + "/employee/designation/dept";

	// Role folder endpoints
	String addRoleEndpoint = baseURL + "/employee/role/add";
	String getAllRolesEndpoint = baseURL + "/employee/role/all";
	String updateRoleEndpoint = baseURL + "/employee/role/update";
	String deleteRoleEndpoint = baseURL + "/employee/role/delete";
	String getAllRolesByLevelEndpoint = baseURL + "/employee/role/level/";

	// Department folder endpoints
	String addDepartmentEndpoint = baseURL + "/employee/department/add";
	String getAllDepartmentsEndpoint = baseURL + "/employee/department/get/all";
	String updateDepartmentEndpoint = baseURL + "/employee/department/update";
	String deleteDepartmentEndpoint = baseURL + "/employee/department/delete";
	String getAllEmployeesByDesignationNameEndpoint = baseURL + "/employee/get/designation";
	String getAllEmployeesByDepartmentNameEndpoint = baseURL + "/employee/get/department/";
	String getUserIdEndpoint = baseURL + "/employee/get/userId";
	String getAllHigherAuthoritiesEndpoint = baseURL + "/employee/get/higher/authority";
	String getAllAssigneesEndpoint = baseURL + "/employee/get/assignee";

	// Employee folder endpoints
	String addEmployeeEndpoint = baseURL + "/employee/add";
	String getAllEmployeesEndpoint = baseURL + "/employee/get/all";
	String updateEmployeeEndpoint = baseURL + "/employee/update";
	String updatePasswordEndpoint = baseURL + "/employee/update/user/cred";
	String getSingleEmployeeEndpoint = baseURL + "/employee/single";
	String getTaskOwnersEndpoint = baseURL + "/employee/get/task-owners";
	String getAllActiveUsersInfoEndpoint = baseURL + "/employee/get/active";
	String getAllEmployeesIdAndNameEndpoint = baseURL + "/employee/all";
	String getAssignedTaskInfoByRoleEndpoint = baseURL + "/employee/get/assigned/task/info/";
	String getEncryptedEmailEndpoint = baseURL + "/employee/get-mail-encrypted";
	String getSearchEmployeeInLevelEndpoint = baseURL + "/employee/level/search/get ";
	String addForgotPasswordEndpoint = baseURL + "/employee/forget-pass";
	String addTokenForWebEndpoint = baseURL + "/employee/add/key";
	String addTokenForMobileEndpoint = baseURL + "/employee/mobile/token";

	// Regular Task folder endpoints
	String addRegularTaskEndpoint = baseURL + "/task/regular/add";
	String getAllRegularTaskEndpoint = baseURL + "/task/regular/get";
	String updateRegularTaskEndpoint = baseURL + "/task/regular/update";
	String deleteRegularTaskEndpoint = baseURL + "/task/regular/delete/";

	// Project folder endpoints
	String addProjectEndpoint = baseURL + "/project/add";
	String getAllProjectsEndpoint = baseURL + "/project/get/all";
	String updateProjectEndpoint = baseURL + "/project/update";
	String getAllProjectForAnUserIdEndpoint = baseURL + "/project/get/all/by/";
	String getAllProjectAssigneeEndpoint = baseURL + "/project/get/assignee";
	String getAllProjectManagersEndpoint = baseURL + "/project/get/managers";
	String getAllProjectStatusEndpoint = baseURL + "/project/get/status";
	String getAllProjectByDepartmentEndpoint = baseURL + "/project/get/all/dept";
	String getSingleProjectByProjectIdEndpoint = baseURL + "/project/get";

	// Task folder endpoints
	String addTaskEndpoint = baseURL + "/task/add";
	String updateMultipleTaskEndpoint = baseURL + "/task/multiple/update";
	String updateTaskEndpoint = baseURL + "/task/update";
	String deleteTaskEndpoint = baseURL + "/task/delete";
	String duplicateTaskEndpoint = baseURL + "/task/duplicate";
	String getAllTasksForDayEndpoint = baseURL + "/task/card/get/day";
	String searchTaskEndpoint = baseURL + "/task/search";
	String getSingleTaskEndpoint = baseURL + "/task/get/single/";
	String getAllTasksForMonthEndpoint = baseURL + "/task/get/by/month";
	String getAllTasksForWeekEndpoint = baseURL + "/task/get/by/week";
	String getTaskInfoForEmployeeByRoleEndpoint = baseURL + "/employee/get/assigned/task/info/";
	String getAllTasksForDayByDueDateEndpoint = baseURL + "/task/get/by/due/date";
	String getAllTasksForDayByStatusEndpoint = baseURL + "/task/get/by/status/level";
	String getAllTasksForDayByPriorityEndpoint = baseURL + "/task/get/by/priority/level";

	// Verification status folder endpoints
	String addVerificationStatusEndpoint = baseURL + "/task/verification/add";
	String getAllVerificationStatusesEndpoint = baseURL + "/task/verification/get/all";
	String updateVerificationStatusEndpoint = baseURL + "/task/verification/update";
	String deleteVerificationStatusEndpoint = baseURL + "/task/verification/delete";

	// Request folder endpoints
	String getAllRequestStatusEndpoint = baseURL + "/task/req/status/all";
	String getAllRequestsEndpoint = baseURL + "/task/req/get/all";
	String getAllRequestAnalyticsEndpoint = baseURL + "/task/req/analytics";
	String getAllRequestsFromAnotherEmployeeForSingleEmployeeEndpoint = baseURL + "/task/req/from/single";
	String getAllMySentRequestsEndpoint = baseURL + "/task/req/my/all";
	String updateRequestStatusEndpoint = baseURL + "/task/req/status/change";
	String searchRequestByEmployeeNameEndpoint = baseURL + "/task/search/request";

	// Employee Image folder endpoints
	String addEmployeeImageEndpoint = baseURL + "/attach/files/emp/image/add";
	String getEmployeeImageEndpoint = baseURL + "/attach/files/emp/image";

	// Notification folder endpoints
	String getMyNotificationsEndpoint = baseURL + "/notification/logs/get";
	String getUnreadNotificationCountEndpoint = baseURL + "/notification/logs/get/unread-count";
	String updateNotificationReadStatusEndpoint = baseURL + "/notification/logs/update/read";

	// Milestone folder endpoints
	String addMilestoneEndpoint = baseURL + "/project/milestone/add";
	String getAllMilestonesEndpoint = baseURL + "/project/milestone/get/all";
	String updateMilestoneEndpoint = baseURL + "/project/milestone/update";
	String deleteMilestoneEndpoint = baseURL + "/project/milestone/delete";
}

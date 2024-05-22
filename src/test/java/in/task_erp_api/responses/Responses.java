package in.task_erp_api.responses;

import static io.restassured.RestAssured.*;

import java.io.File;

import io.restassured.http.*;
import io.restassured.response.*;

public class Responses {
	public static Response response;

	public static Response postRequestWithoutAuthorization(String requestPayload, String endpoint) {
		response = given().contentType(ContentType.JSON).body(requestPayload).when().post(endpoint);
		return response;
	}

	public static Response postRequestWithoutAuthorizationAndOneQueryParameter(String endpoint,
			String queryParameterKey, String queryParameterValue) {
		response = given().queryParam(queryParameterKey, queryParameterValue).when().post(endpoint);
		return response;
	}

	public static Response postRequestWithoutAuthorization(String endpoint, File fileName) {
		response = given().multiPart(fileName).when().post(endpoint);
		return response;
	}

	public static Response getRequestWithoutAuthorization(String endpoint) {
		response = when().get(endpoint);
		return response;
	}

	public static Response getRequestWithoutAuthorizationAndPathParameter(String endpoint, int pathParameter) {
		response = when().get(endpoint + pathParameter);
		return response;
	}

	public static Response getRequestWithoutAuthorizationAndPathParameter(String endpoint, String pathParameter) {
		response = when().get(endpoint + pathParameter);
		return response;
	}

	public static Response getRequestWithoutAuthorizationAndOneQueryParameter(String endpoint, String queryParameterKey,
			int queryParameterValue) {
		response = given().queryParam(queryParameterKey, queryParameterValue).when().get(endpoint);
		return response;
	}

	public static Response getRequestWithoutAuthorizationAndOneQueryParameter(String endpoint, String queryParameterKey,
			String queryParameterValue) {
		response = given().queryParam(queryParameterKey, queryParameterValue).when().get(endpoint);
		return response;
	}

	public static Response getRequestWithoutAuthorizationPathParameterAndOneQueryParameter(String endpoint,
			String pathParameter, String queryParameterKey, String queryParameterValue) {
		response = given().queryParam(queryParameterKey, queryParameterValue).when().get(endpoint + pathParameter);
		return response;
	}

	public static Response getRequestWithoutAuthorizationAndTwoQueryParameter(String endpoint,
			String queryParameterKey1, int queryParameterValue1, String queryParameterKey2,
			String queryParameterValue2) {
		response = given().queryParam(queryParameterKey1, queryParameterValue1)
				.queryParam(queryParameterKey2, queryParameterValue2).when().get(endpoint);
		return response;
	}

	public static Response getRequestWithoutAuthorizationAndThreeQueryParameter(String endpoint,
			String queryParameterKey1, int queryParameterValue1, String queryParameterKey2, int queryParameterValue2,
			String queryParameterKey3, String queryParameterValue3) {
		response = given().queryParam(queryParameterKey1, queryParameterValue1)
				.queryParam(queryParameterKey2, queryParameterValue2)
				.queryParam(queryParameterKey3, queryParameterValue3).when().get(endpoint);
		return response;
	}

	public static Response putRequestWithoutAuthorization(String requestPayload, String endpoint) {
		response = given().contentType(ContentType.JSON).body(requestPayload).when().put(endpoint);
		return response;
	}

	public static Response deleteRequestWithoutAuthorizationAndPathParameter(String endpoint, int pathParameter) {
		response = when().delete(endpoint + pathParameter);
		return response;
	}

	public static Response deleteRequestWithoutAuthorizationAndPathParameter(String endpoint, String pathParameter) {
		response = when().delete(endpoint + pathParameter);
		return response;
	}

	public static Response deleteRequestWithoutAuthorizationAndQueryParameter(String queryParameterKey,
			String queryParameterValue, String endpoint) {
		response = given().queryParam(queryParameterKey, queryParameterValue).when().delete(endpoint);
		return response;
	}

	public static Response deleteRequestWithoutAuthorizationAndQueryParameter(String queryParameterKey,
			int queryParameterValue, String endpoint) {
		response = given().queryParam(queryParameterKey, queryParameterValue).when().delete(endpoint);
		return response;
	}

	public static Response deleteRequestWithoutAuthorizationAndPayload(String requestPayload, String endpoint) {
		response = given().contentType(ContentType.JSON).body(requestPayload).when().delete(endpoint);
		return response;
	}

	public static Response postRequestWithAuthorization(String requestPayload, String authToken, String endpoint) {
		response = given().header("Authorization", "Bearer " + authToken).contentType(ContentType.JSON)
				.body(requestPayload).when().post(endpoint);
		return response;
	}

	public static Response postRequestWithAuthorizationAndOneQueryParameter(String authToken, String endpoint,
			String queryParameterKey, String queryParameterValue) {
		response = given().header("Authorization", "Bearer " + authToken)
				.queryParam(queryParameterKey, queryParameterValue).when().post(endpoint);
		return response;
	}

	public static Response postRequestWithAuthorization(String authToken, String endpoint, File fileName) {
		response = given().header("Authorization", "Bearer " + authToken).multiPart(fileName).when().post(endpoint);
		return response;
	}

	public static Response getRequestWithAuthorization(String authToken, String endpoint) {
		response = given().header("Authorization", "Bearer " + authToken).when().get(endpoint);
		return response;
	}

	public static Response getRequestWithAuthorizationAndPathParameter(String authToken, String endpoint,
			int pathParameter) {
		response = given().header("Authorization", "Bearer " + authToken).when().get(endpoint + pathParameter);
		return response;
	}

	public static Response getRequestWithAuthorizationAndPathParameter(String authToken, String endpoint,
			String pathParameter) {
		response = given().header("Authorization", "Bearer " + authToken).when().get(endpoint + pathParameter);
		return response;
	}

	public static Response getRequestWithAuthorizationAndOneQueryParameter(String authToken, String endpoint,
			String queryParameterKey, int queryParameterValue) {
		response = given().header("Authorization", "Bearer " + authToken)
				.queryParam(queryParameterKey, queryParameterValue).when().get(endpoint);
		return response;
	}

	public static Response getRequestWithAuthorizationAndOneQueryParameter(String authToken, String endpoint,
			String queryParameterKey, String queryParameterValue) {
		response = given().header("Authorization", "Bearer " + authToken)
				.queryParam(queryParameterKey, queryParameterValue).when().get(endpoint);
		return response;
	}

	public static Response getRequestWithAuthorizationPathParameterAndOneQueryParameter(String authToken,
			String endpoint, String pathParameter, String queryParameterKey, String queryParameterValue) {
		response = given().header("Authorization", "Bearer " + authToken)
				.queryParam(queryParameterKey, queryParameterValue).when().get(endpoint + pathParameter);
		return response;
	}

	public static Response getRequestWithAuthorizationAndTwoQueryParameter(String authToken, String endpoint,
			String queryParameterKey1, int queryParameterValue1, String queryParameterKey2,
			String queryParameterValue2) {
		response = given().header("Authorization", "Bearer " + authToken)
				.queryParam(queryParameterKey1, queryParameterValue1)
				.queryParam(queryParameterKey2, queryParameterValue2).when().get(endpoint);
		return response;
	}

	public static Response getRequestWithAuthorizationAndThreeQueryParameter(String authToken, String endpoint,
			String queryParameterKey1, int queryParameterValue1, String queryParameterKey2, int queryParameterValue2,
			String queryParameterKey3, String queryParameterValue3) {
		response = given().header("Authorization", "Bearer " + authToken)
				.queryParam(queryParameterKey1, queryParameterValue1)
				.queryParam(queryParameterKey2, queryParameterValue2)
				.queryParam(queryParameterKey3, queryParameterValue3).when().get(endpoint);
		return response;
	}

	public static Response putRequestWithAuthorization(String requestPayload, String authToken, String endpoint) {
		response = given().header("Authorization", "Bearer " + authToken).header("Authorization", "Bearer " + authToken)
				.contentType(ContentType.JSON).body(requestPayload).when().put(endpoint);
		return response;
	}

	public static Response deleteRequestWithAuthorizationAndPathParameter(String authToken, String endpoint,
			int pathParameter) {
		response = given().header("Authorization", "Bearer " + authToken).when().delete(endpoint + pathParameter);
		return response;
	}

	public static Response deleteRequestWithAuthorizationAndPathParameter(String authToken, String endpoint,
			String pathParameter) {
		response = given().header("Authorization", "Bearer " + authToken).when().delete(endpoint + pathParameter);
		return response;
	}

	public static Response deleteRequestWithAuthorizationAndQueryParameter(String queryParameterKey,
			String queryParameterValue, String authToken, String endpoint) {
		response = given().header("Authorization", "Bearer " + authToken)
				.queryParam(queryParameterKey, queryParameterValue).when().delete(endpoint);
		return response;
	}

	public static Response deleteRequestWithAuthorizationAndQueryParameter(String queryParameterKey,
			int queryParameterValue, String authToken, String endpoint) {
		response = given().header("Authorization", "Bearer " + authToken)
				.queryParam(queryParameterKey, queryParameterValue).when().delete(endpoint);
		return response;
	}

	public static Response deleteRequestWithAuthorizationAndPayload(String requestPayload, String authToken,
			String endpoint) {
		response = given().header("Authorization", "Bearer " + authToken).contentType(ContentType.JSON)
				.body(requestPayload).when().delete(endpoint);
		return response;
	}
}

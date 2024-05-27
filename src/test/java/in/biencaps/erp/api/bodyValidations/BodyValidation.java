package in.biencaps.erp.api.bodyValidations;

import static org.hamcrest.Matchers.*;

import io.restassured.response.*;

public class BodyValidation {
	// For 200 and 201 status code
	public static void responseValidation(Response response, int statusCode) {
		response.then().statusCode(statusCode).header("X-Content-Type-Options", "nosniff")
				.header("X-XSS-Protection", "0")
				.header("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate").header("Pragma", "no-cache")
				.header("Expires", "0").header("X-Frame-Options", "DENY").header("Content-Type", "application/json")
				.header("Transfer-Encoding", "chunked").header("Keep-Alive", "timeout=60")
				.header("Connection", "keep-alive");
	}

	// For 200 status code and for only content length
	public static void responseValidation(Response response, int statusCode, String contentLength) {
		response.then().statusCode(statusCode).header("X-Content-Type-Options", "nosniff")
				.header("X-XSS-Protection", "0")
				.header("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate").header("Pragma", "no-cache")
				.header("Expires", "0").header("X-Frame-Options", "DENY")
				.header("Content-Type", "text/plain;charset=UTF-8").header("Content-Length", contentLength)
				.header("Keep-Alive", "timeout=60").header("Connection", "keep-alive");
	}

	// For 204 status code
	public static void response204Validation(Response response) {
		response.then().statusCode(204).header("X-Content-Type-Options", "nosniff").header("X-XSS-Protection", "0")
				.header("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate").header("Pragma", "no-cache")
				.header("Expires", "0").header("X-Frame-Options", "DENY").header("Keep-Alive", "timeout=60")
				.header("Connection", "keep-alive");
	}

	// For only 400 status code
	public static void response400Validation(Response response) {
		response.then().statusCode(400).header("X-Content-Type-Options", "nosniff").header("X-XSS-Protection", "0")
				.header("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate").header("Pragma", "no-cache")
				.header("Expires", "0").header("X-Frame-Options", "DENY").header("Content-Type", "application/json")
				.header("Transfer-Encoding", "chunked").header("Connection", "close")
				.body("error", equalTo("Bad Request"));
	}

	// For 401 status code
	public static void response401Validation(Response response) {
		response.then().statusCode(401).header("X-Content-Type-Options", "nosniff").header("X-XSS-Protection", "0")
				.header("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate").header("Pragma", "no-cache")
				.header("Expires", "0").header("X-Frame-Options", "DENY").header("Content-Type", "application/json")
				.header("Transfer-Encoding", "chunked").header("Keep-Alive", "timeout=60")
				.header("Connection", "keep-alive").body("status", equalTo(401)).body("error", equalTo("Unauthorized"));
	}

	// For 403,404, 409, 422 status code
	public static void responseValidation(Response response, String error, int statusCode) {
		response.then().statusCode(statusCode).header("X-Content-Type-Options", "nosniff")
				.header("X-XSS-Protection", "0")
				.header("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate").header("Pragma", "no-cache")
				.header("Expires", "0").header("X-Frame-Options", "DENY").header("Content-Type", "application/json")
				.header("Transfer-Encoding", "chunked").header("Keep-Alive", "timeout=60")
				.header("Connection", "keep-alive").body("status", equalTo(statusCode)).body("error", equalTo(error));
	}

	// For 403,404, 409, 422 status code
	public static void responseValidation(int statusCode, Response response, String error) {
		response.then().statusCode(statusCode).header("X-Content-Type-Options", "nosniff")
				.header("X-XSS-Protection", "0")
				.header("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate").header("Pragma", "no-cache")
				.header("Expires", "0").header("X-Frame-Options", "DENY")
				.header("Content-Type", "application/problem+json").header("Transfer-Encoding", "chunked")
				.header("Keep-Alive", "timeout=60").header("Connection", "keep-alive")
				.body("status", equalTo(statusCode)).body("error", equalTo(error));
	}
}

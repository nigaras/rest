package rest.webapi.laptopbag.helpers;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.port;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class RestHelper {
	static {
		baseURI = "http://localhost";
		port = 8085;
		basePath = "/laptop-bag/webapi/api";
	}

	public static String buildUri(Methods methods) throws URISyntaxException {

		String apiMethod = null;
		switch (methods) {
		case PING:
			apiMethod = "/ping/world";
			break;
		case FIND:
			apiMethod = "/find/";
			break;
		case ALL:
			apiMethod = "/all";
			break;
		case DELETE:
			apiMethod = "/delete";
			break;
		case ADD:
			apiMethod = "/add";
			break;
		default:
			throw new RuntimeException("Invalid API method");
		}
		return new String(apiMethod);
	}

	public enum Methods {
		PING, FIND, ALL, DELETE, ADD
	}

	public static Map<String, String> getHeaders(HeaderType type) {

		Map<String, String> headers = new HashMap<>();

		switch (type) {
		case JSON:
			headers.put("Content-Type", "application/json");
			headers.put("Accept", "application/json");
			break;
		case XML:
			headers.put("Content-Type", "application/xml");
			headers.put("Accept", "application/xml");
			break;
		default:
			throw new RuntimeException("Invalid Header Type");
		}
		return headers;
	}

	public enum HeaderType {
		JSON, XML
	}

}

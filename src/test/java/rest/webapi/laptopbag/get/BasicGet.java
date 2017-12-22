package rest.webapi.laptopbag.get;

import static io.restassured.RestAssured.when;

public class BasicGet {

	static String pingURL = "http://localhost:8085/laptop-bag/webapi/api/ping/Nigara";

	public static void main(String[] args) {
		/**
		 * First commit When i perform GET request to:
		 * http://localhost:8085/laptop-bag/webapi/api/ping/Nigara
		 */
		when().get(pingURL);
		/**
		 * When i perform GET request to:
		 * http://localhost:8085/laptop-bag/webapi/api/ping/Nigara It should return Hi!
		 * Nigara
		 */
		String greeting = when().get(pingURL).asString();
		System.out.println(greeting);

		// Getting status code from webService
		int statusCode = when().get(pingURL).thenReturn().statusCode();
		System.out.println("Status code: " + statusCode);

	}

}

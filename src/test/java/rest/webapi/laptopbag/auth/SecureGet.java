package rest.webapi.laptopbag.auth;

import static io.restassured.RestAssured.authentication;
import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;
import static io.restassured.RestAssured.preemptive;
import static io.restassured.RestAssured.with;

import java.net.URI;
import java.net.URISyntaxException;

import org.hamcrest.Matchers;
import org.junit.Test;

import io.restassured.http.ContentType;

public class SecureGet {

	/*
	 * 1. Authorization using a request header 2. RestAssured methods and variables
	 */

	@Test()
	public void getAllWithAuthorization() throws URISyntaxException {
		URI secureAll = new URI("http://localhost:8085/laptop-bag/webapi/secure/all");
		with().accept(ContentType.JSON).when().get(secureAll).then().assertThat().statusCode(401);

		with().header("Authorization", "Basic YWRtaW46d2VsY29tZQ==").and().accept(ContentType.JSON).when()
				.get(secureAll).then().statusCode(200);
	}

	@Test
	public void getAllWithAuthoRestAssuredMethod() throws URISyntaxException {
		URI secureAll = new URI("http://localhost:8085/laptop-bag/webapi/secure/all");
		/*
		 * auth()
		 */
		given().auth().preemptive().basic("admin", "welcome").when().get(secureAll).then().assertThat()
				.statusCode(Matchers.anyOf(Matchers.equalTo(200), Matchers.equalTo(204)));

	}

	@Test
	public void AuthorizationUsingEnvVariable() {
		baseURI = "http://localhost";
		port = 8085;
		basePath = "/laptop-bag/webapi/secure";
		authentication = preemptive().basic("admin", "welcome");

		given().accept(ContentType.JSON).when().get("/all").then().statusCode(200);
	}
}

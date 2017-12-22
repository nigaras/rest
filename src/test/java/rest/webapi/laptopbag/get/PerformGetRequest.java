package rest.webapi.laptopbag.get;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PerformGetRequest {

	/**
	 * When I perform GET request to ping method Then I can see status code should
	 * be 200 And Message should be Hi! Name
	 * 
	 * @throws URISyntaxException
	 */

	@Test
	public void testPingMethod() throws URISyntaxException {
		URI pingURI = new URI("http://localhost:8085/laptop-bag/webapi/api/ping/Nigara");
		Response response = when().get(pingURI);
		assertEquals(200, response.statusCode());
		assertEquals("Hi! Nigara", response.body().asString());
	}

	/**
	 * When I perform GET request to ping method without argument Then I can see
	 * status code should be 400
	 * 
	 * @throws URISyntaxException
	 */

	@Test
	public void testPingWithNoArg() throws URISyntaxException {
		URI pingURI = new URI("http://localhost:8085/laptop-bag/webapi/api/ping");
		Response response = when().get(pingURI);
		assertEquals(404, response.statusCode());
	}

	/*
	 * When I perform GET request to all api method Then I can see status code
	 * should be 200
	 */

	@Test // GIEVN, WHEN, THEN, AND, GET, ACCEPT, ASSERTTHAT (Keywords)
	public void testAllMethod() throws URISyntaxException {
		URI allURI = new URI("http://localhost:8085/laptop-bag/webapi/api/all");
		Response response = given().accept(ContentType.JSON).when().get(allURI);
		assertEquals(200, response.getStatusCode());
		System.out.println(response.body().asString());
		// 2nd way with build in assertion and body return
		given().accept(ContentType.JSON).when().get(allURI).then().assertThat().statusCode(200).and().assertThat()
				.contentType(ContentType.JSON);
	}

	/*
	 * Scenario: Get laptop info by using find method and id Given Accept type is
	 * JSON When status code should be 200 And ID should be 100
	 */

	@Test
	public void testFindIdMethod() throws URISyntaxException {
		URI findURI = new URI("http://localhost:8085/laptop-bag/webapi/api/find/100");
		given().accept(ContentType.JSON).when().get(findURI).then().assertThat().statusCode(200).and().assertThat()
				.body("Id", equalTo(100)).and().assertThat().body("BrandName", equalTo("Dell"));
	}

	/*
	 * Scenario: Features list content Given Accept type is When I perform GET
	 * request with id 100 Then status code should be 200 Features should be 3 items
	 * And Features should contain following: "16GB RAM", "500GB SSD", "i7 CPU"
	 */
	@Test
	public void testFeatureList() throws URISyntaxException {
		URI findURI = new URI("http://localhost:8085/laptop-bag/webapi/api/find/100");
		given().accept(ContentType.JSON).when().get(findURI).then().assertThat().statusCode(200).and().assertThat()
				.body("Features.Feature", hasSize(3)).and().assertThat()
				.body("Features.Feature", hasItems("16GB RAM", "500GB SSD", "i7 CPU"));
	}

	@Test
	public void testUsingJsonPath() throws URISyntaxException {
		URI findURI = new URI("http://localhost:8085/laptop-bag/webapi/api/find/100");
		String body = given().accept(ContentType.JSON).when().get(findURI).thenReturn().body().asString();
		JsonPath json = new JsonPath(body);
		System.out.println(json.getString("BrandName"));
		System.out.println(json.getInt("Id"));
		System.out.println(json.getString("LaptopName"));
		List<String> features = json.getList("Features.Feature");
		List<String> expFeatures = Arrays.asList("16GB RAM", "500GB SSD", "i7 CPU");

		// I dont care about order
		assertTrue(expFeatures.containsAll(features));
		// I care about order
		assertEquals(expFeatures, features);
		System.out.println(features.toString());
	}

}

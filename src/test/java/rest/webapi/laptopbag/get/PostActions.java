package rest.webapi.laptopbag.get;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.testng.Assert.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import rest.webapi.laptopbag.helpers.RestHelper;
import rest.webapi.laptopbag.helpers.RestHelper.HeaderType;
import rest.webapi.laptopbag.helpers.RestHelper.Methods;

public class PostActions {

	String id = (int) (1000 * (Math.random())) + "";
	String jsonBody = "{" + "\"BrandName\": \"Apple\"," + "\"Features\": {" + "\"Feature\": [\"8GB RAM\","
			+ "\"1TB Hard Drive\"]" + "}," + "\"Id\": " + id + "," + "\"LaptopName\": \"MacBook Pro\"" + "}";

	/*
	 * Gievn Accept type is Json and Content-type is Json When I perform post Action
	 * Then status code is 200 And Id is same as my post
	 */

	@Test
	public void addALaptop() throws URISyntaxException {
		URI addUri = new URI(RestHelper.buildUri(Methods.ADD));
		System.out.println(id);

		given().accept(ContentType.JSON).contentType(ContentType.JSON).and().body(jsonBody).when().post(addUri).then()
				.assertThat().statusCode(200).and().assertThat().body("Id", equalTo(Integer.parseInt(id)));
	}

	@Test
	public void PostWithHeaders() throws URISyntaxException {
		URI addUri = new URI(RestHelper.buildUri(Methods.ADD));

		// Map<String, String> headers = new HashMap<>();
		// headers.put("Content-Type", "application/json");
		// headers.put("Accept", "application/json");

		with().headers(RestHelper.getHeaders(HeaderType.JSON)).body(jsonBody).when().post(addUri).then().assertThat()
				.body("Features.Feature", hasItems("8GB RAM", "1TB Hard Drive"));

		JsonPath jsonPath = new JsonPath(
				with().headers(RestHelper.getHeaders(HeaderType.JSON)).body(jsonBody).when().post(addUri).toString());
		List<String> expFeatures = Arrays.asList("8GB RAM", "1TB Hard Drive");
		assertTrue(jsonPath.getList("Features.Feature").containsAll(expFeatures));
	}

}

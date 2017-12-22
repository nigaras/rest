package rest.webapi.laptopbag.get;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.testng.Assert.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import rest.webapi.laptopbag.helpers.RestHelper;
import rest.webapi.laptopbag.helpers.RestHelper.Methods;

public class Task {
	String json = null;
	JsonPath jsonBody = null;

	@Test
	public void JsonPathSamples() throws URISyntaxException {
		String ping = RestHelper.buildUri(Methods.PING);
		System.out.println(when().get(ping).asString());

		/*
		 * Given accept type is Json When I perform GET request using ALL method Then I
		 * can see all IDs
		 */
		json = given().accept(ContentType.JSON).when().get(RestHelper.buildUri(Methods.ALL)).andReturn().body()
				.asString();
		// parsing Json String into Json Path object
		// So that I can easily navigate through objects in Json
		jsonBody = new JsonPath(json);

		// Get all Ids
		List<Integer> ids = jsonBody.getList("Id");
		System.out.println(ids);

		// Get all BrandNames into a list
		List<Integer> brands = jsonBody.getList("BrandName");
		System.out.println(brands);

		// Get all Features for all Laptop
		List<String> features = jsonBody.getList("Features.Feature");
		System.out.println(features);

		// Get all laptops whose Id is less than 300
		List<String> laptopNames = jsonBody.getList("findAll{it.Id<300}.LaptopName");
		System.out.println(laptopNames);

		// Get all Ids for laptops whose BrandName is Apple
		List<Integer> laptopIds = jsonBody.getList("findAll{it.BrandName=='Mac'}.Id");
		System.out.println(laptopIds);

	}

	@Test
	public void XmlPathSamples() throws URISyntaxException {

		URI uri = new URI("http://localhost:8085/laptop-bag/webapi/api/all");
		// Get all laptops in Xml format
		Response response = given().accept(ContentType.XML).contentType(ContentType.XML).when()
				.get(RestHelper.buildUri(Methods.ALL));

		// List<String> laptopnName = jsonb
		assertEquals(200, response.statusCode());
		XmlPath xml = new XmlPath(response.body().asString());

		List<String> brands = xml.getList("laptopDetailss.Laptop.BrandName");
		System.out.println(brands);
	}

}

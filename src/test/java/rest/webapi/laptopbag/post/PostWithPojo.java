package rest.webapi.laptopbag.post;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import io.restassured.http.ContentType;
import rest.webapi.laptopbag.helpers.PojoHelper;
import rest.webapi.laptopbag.helpers.RestHelper;
import rest.webapi.laptopbag.helpers.RestHelper.HeaderType;
import rest.webapi.laptopbag.helpers.RestHelper.Methods;
import rest.webapi.laptopbag.pojos.Features;
import rest.webapi.laptopbag.pojos.Laptop;

public class PostWithPojo {

	/*
	 * Given Headers are type Json And laptop object When I perform Post action Then
	 * status code is 200 "16GB RAM", "500GB SSD", "i7 CPU"
	 */

	@Test
	public void PostActionUsingPOJO() throws URISyntaxException {
		URI postURI = new URI(RestHelper.buildUri(Methods.ADD));
		Laptop laptop = new Laptop();
		laptop.setBrandName("ASUS");
		laptop.setId(333);
		laptop.setLaptopName("ZenBook");

		List<String> laptopFeatures = Arrays.asList("32GB RAM", "500GB SSD", "i7 CPU");
		Features features = new Features();
		features.setFeature(laptopFeatures);
		laptop.setFeatures(features);

		given().headers(RestHelper.getHeaders(HeaderType.JSON)).and().body(laptop).when().post(postURI).then()
				.assertThat().statusCode(200);
	}

	@Test // DE-SERIALIZATION
	public void PostActionUsingPOJO2() throws URISyntaxException {
		URI postURI = new URI(RestHelper.buildUri(Methods.ADD));
		Laptop laptop = new Laptop();
		laptop.setBrandName("HP");
		laptop.setId(444);
		laptop.setLaptopName("Envy");

		List<String> laptopFeatures = Arrays.asList("16GB RAM", "255GB SSD", "i8 CPU");
		Features features = new Features();
		features.setFeature(laptopFeatures);
		laptop.setFeatures(features);

		Laptop returnLaptop = given().headers(RestHelper.getHeaders(HeaderType.JSON)).and().body(laptop).when()
				.post(postURI).thenReturn().body().as(Laptop.class);

		System.out.println(returnLaptop.getBrandName());
		assertTrue(returnLaptop.getId() == 444);
		assertEquals("Envy", returnLaptop.getLaptopName());

		List<String> featuress = returnLaptop.getFeatures().getFeature();
		System.out.println(featuress);

	}

	@Test
	public void postUsingPojoHelper() throws URISyntaxException {

		URI postURI = new URI(RestHelper.buildUri(Methods.ADD));
		Laptop laptopPost = PojoHelper.buildNewLaptop("Apple", "Mac Pro", "16GB Ram, 500GB SSD, Touch bar");

		given().headers(RestHelper.getHeaders(HeaderType.JSON)).and().body(laptopPost).when().post(postURI).then()
				.assertThat().statusCode(200);

		/*
		 * Perform GET method to find/[id] api method then de-serialize the return Json
		 * Then ensure that laptop details from POST action match the laptop details
		 * that was returned from GET (find) action
		 */

		int id = laptopPost.getId();
		URI findURI = new URI(RestHelper.buildUri(Methods.FIND) + id);

		Laptop retLaptop = with().accept(ContentType.JSON).when().get(findURI).thenReturn().body().as(Laptop.class);

		// with().accept(ContentType.JSON).when().get(findURI).thenReturn().body().prettyPrint();

		assertEquals(laptopPost.getBrandName(), retLaptop.getBrandName());
		assertEquals(laptopPost.getLaptopName(), retLaptop.getLaptopName());
		assertEquals(laptopPost.getId(), retLaptop.getId());

		assertEquals(laptopPost.getFeatures().getFeature(), retLaptop.getFeatures().getFeature());
	}

}

package rest.webapi.laptopbag.helpers;

import java.util.Arrays;
import java.util.Random;

import rest.webapi.laptopbag.pojos.Features;
import rest.webapi.laptopbag.pojos.Laptop;

public class PojoHelper {

	public static Laptop buildNewLaptop(String BrandName, String LaptopName, String FeaturesOfLaptop) {

		/*
		 * 1. Accepts 3 args: BrandName, LaptopName, FeaturesOfLaptop 2. ID is
		 * internally ramdom generated 3. Using argument data and Id build a new Laptop
		 * object then return from a method
		 */

		Laptop laptop = new Laptop();
		laptop.setBrandName(LaptopName);
		laptop.setLaptopName(LaptopName);

		Random random = new Random();
		int id = random.nextInt(999);
		laptop.setId(id);

		// List<String> laptopFeatures = Arrays.asList(FeaturesOfLaptop);
		// Features features = new Features();
		// features.setFeature(Arrays.asList(FeaturesOfLaptop.split(",")));
		// laptop.setFeatures(features);

		// Features features = new Features(Arrays.asList(FeaturesOfLaptop.split(",")));
		// laptop.setFeatures(features);

		laptop.setFeatures(new Features(Arrays.asList(FeaturesOfLaptop.split(","))));

		return laptop;
	}
}

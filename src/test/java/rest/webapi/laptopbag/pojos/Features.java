package rest.webapi.laptopbag.pojos;

import java.util.ArrayList;
import java.util.List;

public class Features {

	public Features() {
	}

	public Features(List<String> feature) {
		Feature = feature;
	}

	private List<String> Feature = new ArrayList<>();

	public List<String> getFeature() {
		return Feature;
	}

	public void setFeature(List<String> feature) {
		Feature = feature;
	}
}

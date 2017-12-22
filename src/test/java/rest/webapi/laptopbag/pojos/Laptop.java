package rest.webapi.laptopbag.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Laptop {
	private String BrandName;
	private int Id;
	private String LaptopName;

	@SerializedName("Features")
	@Expose
	private Features features;

	public Features getFeatures() {
		return features;
	}

	public void setFeatures(Features features) {
		this.features = features;
	}

	public String getBrandName() {
		return BrandName;
	}

	public void setBrandName(String brandName) {
		BrandName = brandName;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getLaptopName() {
		return LaptopName;
	}

	public void setLaptopName(String laptopName) {
		LaptopName = laptopName;
	}

}

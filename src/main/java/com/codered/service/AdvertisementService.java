package com.codered.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "adService")
@ApplicationScoped

public class AdvertisementService {

	private final static String[] name;

	private final static String[] description;

	static {
		name = new String[10];
		name[0] = "Black";
		name[1] = "White";
		name[2] = "Green";
		name[3] = "Red";
		name[4] = "Blue";
		name[5] = "Orange";
		name[6] = "Silver";
		name[7] = "Yellow";
		name[8] = "Brown";
		name[9] = "Maroon";

		description = new String[10];
		description[0] = "BMW";
		description[1] = "Mercedes";
		description[2] = "Volvo";
		description[3] = "Audi";
		description[4] = "Renault";
		description[5] = "Fiat";
		description[6] = "Volkswagen";
		description[7] = "Honda";
		description[8] = "Jaguar";
		description[9] = "Ford";
	}

	public List<Advertisement> createAds(int size) {
		List<Advertisement> list = new ArrayList<Advertisement>();
		for (int i = 0; i < size; i++) {
			list.add(new Advertisement(getRandomName(), getRandomDesc()));
		}
		return list;
	}

	private String getRandomName() {
		return name[(int) (Math.random() * 10)];
	}

	private String getRandomDesc() {
		return description[(int) (Math.random() * 10)];
	}

	public int getRandomPrice() {
		return (int) (Math.random() * 100000);
	}

	public boolean getRandomSoldState() {
		return (Math.random() > 0.5) ? true : false;
	}

	public List<String> getColors() {
		return Arrays.asList(name);
	}

	public List<String> getBrands() {
		return Arrays.asList(description);
	}
}
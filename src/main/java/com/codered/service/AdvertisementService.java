package com.codered.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.codered.rest.CodeRedServices;

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

	public List<Advertisement> createAds() {
		List<Advertisement> list = new CodeRedServices().getRecentAdsByRequestType(1);
		return list;
	}
	
	public List<Advertisement> getNeedAds() {
		List<Advertisement> list = new CodeRedServices().getRecentAdsByRequestType(2);
		return list;
	}
	
	public Advertisement getNeedAds(String id) {
		Advertisement adObj = new CodeRedServices().getAdDetails(id);
		return adObj;
	}
	public List<Advertisement> createAdmingAds() {
		List<Advertisement> list = new CodeRedServices().getAdListForAdmin();
		return list;
	}
	
	public List<Advertisement> testData() {
		List<Advertisement> list = new ArrayList<Advertisement>();
		Advertisement ad = null;
		for(int i=1; i <=5; i++) {
			ad = new Advertisement();
			ad.setAdminMapOid(""+i);
			ad.setTitle("titillle");;
			list.add(ad);
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

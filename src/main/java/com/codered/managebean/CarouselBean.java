package com.codered.managebean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.codered.service.Advertisement;
import com.codered.service.AdvertisementService;
@ManagedBean(name = "carouselBean")
public class CarouselBean {
	private List<Advertisement> advertisementList;

	private Advertisement selectedAdvertisement;

	@ManagedProperty(value = "#{adService}")
	private AdvertisementService service;

	@PostConstruct
    public void init() {
        advertisementList = service.createAds();
        System.out.println("test" + advertisementList);
    }

	public List<Advertisement> getAdvertisementList() {
		return advertisementList;
	}
	
	public AdvertisementService getService() {
		return service;
	}

	public void setService(AdvertisementService service) {
		this.service = service;
	}

	public void setAdvertisementList(List<Advertisement> advertisementList) {
		this.advertisementList = advertisementList;
	}

	public Advertisement getSelectedAdvertisement() {
		return selectedAdvertisement;
	}

	public void setSelectedAdvertisement(Advertisement selectedAdvertisement) {
		this.selectedAdvertisement = selectedAdvertisement;
	}

}

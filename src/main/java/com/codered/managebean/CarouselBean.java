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
	
	private List<Advertisement> needAdvertisementList;

	private Advertisement selectedAdvertisement;

	@ManagedProperty(value = "#{adService}")
	private AdvertisementService service;

	@PostConstruct
    public void init() {
        advertisementList = service.createAds();
        needAdvertisementList = service.getNeedAds();
        System.out.println("test" + advertisementList);
    }

	public void getDetails() {
		System.out.println("Selected ID =========>" + selectedAdvertisement);
		if(selectedAdvertisement !=null)
			selectedAdvertisement = service.getNeedAds(selectedAdvertisement.getAdminMapOid());
	}
	
	
	public List<Advertisement> getAdvertisementList() {
		return advertisementList;
	}
	
	public List<Advertisement> getNeedAdvertisementList() {
		return needAdvertisementList;
	}

	public void setNeedAdvertisementList(List<Advertisement> needAdvertisementList) {
		this.needAdvertisementList = needAdvertisementList;
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

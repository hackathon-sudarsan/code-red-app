package com.codered.managebean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.codered.service.Advertisement;
import com.codered.service.AdvertisementService;

@ManagedBean(name = "dtSelectionView")
public class SelectionView implements Serializable {
	private Advertisement selectedAdv;
	private List<Advertisement> adList;

	private List<Advertisement> selectedAdList;

	@ManagedProperty("#{adService}")
	private AdvertisementService adService;

	@PostConstruct
	public void init() {
		adList = adService.createAdmingAds();
	}

	
	public void approveAd() {
		System.out.println("approveAd==============>" + selectedAdList);
	}
	public Advertisement getSelectedAdv() {
		return selectedAdv;
	}

	public void setSelectedAdv(Advertisement selectedAdv) {
		this.selectedAdv = selectedAdv;
	}

	public List<Advertisement> getAdList() {
		return adList;
	}

	public void setAdList(List<Advertisement> adList) {
		this.adList = adList;
	}

	public AdvertisementService getAdService() {
		return adService;
	}

	public void setAdService(AdvertisementService adService) {
		this.adService = adService;
	}
	

	public List<Advertisement> getSelectedAdList() {
		return selectedAdList;
	}

	public void setSelectedAdList(List<Advertisement> selectedAdList) {
		this.selectedAdList = selectedAdList;
	}
	
	

	@Override
	public String toString() {
		return "SelectionView [selectedAdv=" + selectedAdv + ", adList=" + adList + ", selectedAdList=" + selectedAdList
				+ ", adService=" + adService + "]";
	}

}

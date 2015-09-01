package com.codered.managebean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.codered.service.Advertisement;
import com.codered.service.AdvertisementService;

@ManagedBean
public class SearchView {

	private String searchStr;
	private String category;
	
	private  List<Advertisement> searchList;

	@ManagedProperty("#{adService}")
	private AdvertisementService adService;
	

	public void getSearchResults() {
		System.out.println("INSIDE SEARCH RESULTS");
		searchList = adService.searchResults(searchStr, category);
		System.out.println(" SEARCH RESULTS" + searchList);
	}
	
	public void getSearchResults(String categFilter) {
		System.out.println(" cate ======> "+categFilter);
		//searchList = adService.searchResults(searchStr, category);
		searchList = adService.searchResults(null, categFilter);
		System.out.println(" SEARCH RESULTS" + searchList);
	}
	
	public String getSearchStr() {
		return searchStr;
	}

	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	

	public List<Advertisement> getSearchList() {
		return searchList;
	}

	public void setSearchList(List<Advertisement> searchList) {
		this.searchList = searchList;
	}

	public AdvertisementService getAdService() {
		return adService;
	}

	public void setAdService(AdvertisementService adService) {
		this.adService = adService;
	}

	@Override
	public String toString() {
		return "SearchView [searchStr=" + searchStr + ", category=" + category
				+ "]";
	}

}
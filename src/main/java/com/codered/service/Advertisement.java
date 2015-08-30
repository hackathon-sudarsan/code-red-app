package com.codered.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.codered.managebean.UserProfile;
import com.codered.rest.CodeRedServices;

@ManagedBean
public class Advertisement extends UserProfile {

	private String categroy;
	private String requestType;
	private String title;
	private String description;
	private float price;
	private int primaryKey;

	public Advertisement() {

	}
	private Map<String, String> categoryMap;
	@PostConstruct
	public void init() {
		categoryMap = new HashMap<String, String>();
		categoryMap.put("USA", "USA");
		categoryMap.put("Germany", "Germany");
		categoryMap.put("Brazil", "Brazil");
	}

	public Advertisement(String title, String desc) {
		this.title = title;
		this.description = desc;
	}

	public Map<String, String> getCategoryMap() {
		return categoryMap;
	}

	public void setCategoryMap(Map<String, String> categoryMap) {
		this.categoryMap = categoryMap;
	}

	public String getCategroy() {
		return categroy;
	}

	public void setCategroy(String categroy) {
		this.categroy = categroy;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public void createAd() {
		try {
			ELContext el = FacesContext.getCurrentInstance().getELContext();
			Advertisement adObj = (Advertisement) FacesContext.getCurrentInstance().getApplication().getELResolver()
					.getValue(el, null, "advertisement");

			adObj.setVzId("V492749");
			adObj.setFirstName("Jayaraj");
			adObj.setLastName("Mani");
			System.out.println("CAlling store procedure" + adObj);
			new CodeRedServices().manageAd(adObj);
			System.out.println("Done with store procedure");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PrimeFaces Rocks."));
			System.out.println("Inside Post Add" + adObj.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Advertisement [categroy=" + categroy + ", requestType=" + requestType + ", title=" + title
				+ ", description=" + description + ", price=" + price + ", primaryKey=" + primaryKey + "]"
				+ "UserProfile [firstName=" + firstName + ", lastName=" + lastName + ", vzId=" + vzId + ", phone="
				+ phone + ", email=" + email + "]";
	}

}

package com.codered.service;

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.codered.managebean.MySqlBean;
import com.codered.managebean.UserProfile;
import com.codered.rest.CodeRedServices;

@ManagedBean
public class Advertisement extends UserProfile {

	private String categroy;
	private String requestType;
	private String title;
	private String description;
	private float price;

	public Advertisement() {

	}
	public Advertisement(String title, String desc) {
		this.title = title;
		this.description = desc;
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

	public void createAd() {
		try {
			ELContext el = FacesContext.getCurrentInstance().getELContext();
			Advertisement adObj = (Advertisement) FacesContext.getCurrentInstance().getApplication().getELResolver()
					.getValue(el, null, "advertisement");

			System.out.println("CAlling store procedure");
			new CodeRedServices().manageAd(adObj);
			System.out.println("Done with store procedure");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PrimeFaces Rocks."));
			System.out.println("Inside Post Add" + adObj.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

package com.codered.service;

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class Advertisement {
	private String name;
	private String phone;
	private String emailId;
	private String price;
	private String description;

	public Advertisement() {

	}

	public Advertisement(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void createAd() {
		try {
			ELContext el = FacesContext.getCurrentInstance().getELContext();
			Advertisement adObj = (Advertisement) FacesContext.getCurrentInstance().getApplication().getELResolver()
					.getValue(el, null, "advertisement");
			
			
			
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PrimeFaces Rocks."));
			System.out.println("Inside Post Add" + adObj.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Advertisement [name=" + name + ", phone=" + phone + ", emailId=" + emailId + ", price=" + price
				+ ", description=" + description + "]";
	}

}

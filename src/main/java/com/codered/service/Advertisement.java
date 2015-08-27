package com.codered.service;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Advertisement {
	private String name;
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

	@Override
	public String toString() {
		return "Advertisement [name=" + name + ", description=" + description + "]";
	}
	
	

}

package com.codered;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "editor")
public class EditorBean {

	private String value = "This editor is provided by Hackathon guys";

	public String getValue() {
		System.out.println("testest1213131");
		MySqlDataSource.getConnection();
		return value;
	}

	public void setValue(String value) {
		System.out.println("testest");
		this.value = value;
	}
}
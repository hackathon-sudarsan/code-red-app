package com.codered.managebean;

public class UserProfile {

	private String firstName;
	private String lastName;
	private String vzId;
	private int phone;
	private String email;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getVzId() {
		return vzId;
	}

	public void setVzId(String vzId) {
		this.vzId = vzId;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserProfile [firstName=" + firstName + ", lastName=" + lastName + ", vzId=" + vzId + ", phone=" + phone
				+ ", email=" + email + "]";
	}

}

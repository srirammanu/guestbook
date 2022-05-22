package com.guestbook.entity;

import javax.validation.constraints.NotBlank;

public class LoginForm {

	@NotBlank(message = "Please enter emailIId")
	private String emailId;

	@NotBlank(message="Please enter password")
	private String password;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginForm [emailId=" + emailId + ", password=" + password + "]";
	}
}

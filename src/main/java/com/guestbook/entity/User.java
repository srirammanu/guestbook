package com.guestbook.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "emailId"))

public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;

	@Column(nullable = false)
	private String emailId;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String contactNo;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String role;

	@OneToMany(mappedBy = "userId")
	private List<Feedback> feedbackList;

	public User() {

	}
	
	public User(Long userId){
		this.userId = userId;
	}

	public User(String emailId, String name, String contactNo, String password, String role) {
		super();
		this.emailId = emailId;
		this.name = name;
		this.contactNo = contactNo;
		this.password = password;
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", emailId=" + emailId + ", name=" + name + ", contactNo=" + contactNo
				+ ", password=" + password + ", role=" + role + "]";
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}

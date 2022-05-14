package com.guestbook.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@Entity
@Table
@NamedNativeQuery(name = "User.findUserByName", query = "select u from User u where u.name = ?")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer mobileNo;

	@Column
	private String role;

	public User() {

	}

	public User(String name, Integer mobileNo, String role) {
		super();
		this.name = name;
		this.mobileNo = mobileNo;
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Integer mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", mobileNo=" + mobileNo + ", role=" + role + "]";
	}

}

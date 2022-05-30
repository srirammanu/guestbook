package com.guestbook.cache;

import org.springframework.stereotype.Component;

import com.guestbook.entity.User;

@Component
public class DataProvider {
	
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

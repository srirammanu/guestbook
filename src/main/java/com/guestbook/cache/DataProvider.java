package com.guestbook.cache;

import org.springframework.stereotype.Component;

import com.guestbook.entity.User;

/**
 * Data provider class to set the data and fetch all over the application
 * @author DELL
 *
 */
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

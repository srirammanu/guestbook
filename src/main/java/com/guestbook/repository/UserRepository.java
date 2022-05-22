package com.guestbook.repository;

import java.util.List;

import com.guestbook.entity.User;

public interface UserRepository {
	
	List<User> findUserByEmail(String email);
	
	List<User> validateUser(String email, String password);
	
	User saveUser(User user);

}

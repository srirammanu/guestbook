package com.guestbook.service;

import org.springframework.stereotype.Service;

import com.guestbook.entity.User;

@Service
public interface UserService {
	
	User saveUser(User user);
	
	boolean validateUser(String userName, String password);
	
	User findUserById(Long id);
	
	User findUserByName(String userName);
	
}

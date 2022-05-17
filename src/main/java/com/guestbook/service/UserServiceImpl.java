package com.guestbook.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.guestbook.entity.User;
import com.guestbook.repository.UserRepository;


public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public boolean validateUser(String userName, String password) {
		return false;
	}

	@Override
	public User findUserById(Long userId) {
		Optional<User> findById = userRepository.findById(userId);
		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public User findUserByName(String userName) {
		return userRepository.findUserByName(userName);
	}

}

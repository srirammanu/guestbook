package com.guestbook.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.guestbook.entity.User;
import com.guestbook.exception.InvalidCredentialException;
import com.guestbook.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User saveUser(User user) {
		return userRepository.saveUser(user);
	}

	@Override
	public boolean validateUser(String email, String password) {
		List<User> user =  userRepository.validateUser(email, password);
		if (!CollectionUtils.isEmpty(user)) {
			return true;
		} else {
			throw new InvalidCredentialException("User credentials are not valid");
		}
	}

	@Override
	public User findUserByEmail(String email) {
		List<User> user = userRepository.findUserByEmail(email);

		if (!CollectionUtils.isEmpty(user)) {
			return user.get(0);
		} else {
			return null;
		}
	}

}

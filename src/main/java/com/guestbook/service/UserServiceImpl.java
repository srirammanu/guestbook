package com.guestbook.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.guestbook.entity.User;
import com.guestbook.exception.InvalidCredentialException;
import com.guestbook.repository.UserRepository;


/**
 * This service class provides the functionality for the user specific operations
 * @author DELL
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	/**
	 * Save the user
	 */
	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	/**
	 * Validate the user with provided user email and password
	 */
	@Override
	public boolean validateUser(String email, String password) {
		List<User> user =  userRepository.validateUser(email, password);
		if (!CollectionUtils.isEmpty(user)) {
			return true;
		} else {
			throw new InvalidCredentialException("User credentials are not valid");
		}
	}

	/**
	 * Find the user with the provided email
	 */
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

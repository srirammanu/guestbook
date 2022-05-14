package com.guestbook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guestbook.entity.User;
import com.guestbook.exception.UserNotFoundException;
import com.guestbook.service.UserService;
import com.sun.istack.NotNull;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register_user")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());

		return "registration_form";
	}

	@PostMapping("/process_register")
	public String processRegister(User user) {

		userService.saveUser(user);

		return "register_success";
	}

	@PostMapping("/login")
	public void login(@PathVariable("name") @NotNull String userName, @PathVariable @NotNull String password,
			Model model) {

		User user = userService.findUserByName(userName);
		if (user != null) {
			boolean validateUser = userService.validateUser(userName, password);
			if (validateUser) {

			} else {

			}
		} else {
			throw new UserNotFoundException("User does not exist");
		}

	}

}

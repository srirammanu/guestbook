package com.guestbook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guestbook.entity.Feedback;
import com.guestbook.entity.User;
import com.guestbook.exception.InvalidCredentialException;
import com.guestbook.exception.UserNotFoundException;
import com.guestbook.service.FeedbackService;
import com.guestbook.service.UserService;
import com.sun.istack.NotNull;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	FeedbackService feedBackService;

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
	public String login(@PathVariable("name") @NotNull String userName, @PathVariable @NotNull String password,
			Model model) {

		User user = userService.findUserByName(userName);
		if (user != null) {
			boolean validateUser = userService.validateUser(userName, password);
			if (validateUser) {
				List<Feedback> feedbackList = feedBackService.getFeedbackList(user, false);
				model.addAttribute("feedbackList", feedbackList);

				return "feedbackList";
			} else {
				throw new InvalidCredentialException("User Credentials are not valid");
			}
		} else {
			throw new UserNotFoundException("User does not exist");
		}
	}

}

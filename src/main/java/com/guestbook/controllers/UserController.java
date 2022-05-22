package com.guestbook.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guestbook.entity.Feedback;
import com.guestbook.entity.LoginForm;
import com.guestbook.entity.User;
import com.guestbook.exception.UserNotFoundException;
import com.guestbook.service.FeedbackService;
import com.guestbook.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	FeedbackService feedBackService;

	/**
	 * This API will open the registration form for the user
	 * 
	 * @param model
	 * @return
	 */
	@PostMapping("/register_user")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());

		return "registration_form";
	}

	/**
	 * This API will be used to register the user. Once user will be registered
	 * he/she will be redirected to the feedback form page
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/process_register")
	public String processRegister(@RequestBody @Valid User user, BindingResult result) {

		User existUser = userService.findUserByEmail(user.getEmailId());

		if (existUser != null) {
			result.rejectValue("user.getEmailId()", null, "User already exists with same Email");
		}

		if (result.hasErrors()) {
			return "registration_form";
		}

		userService.saveUser(user);

		return "register_success";
	}

	/**
	 * This API will be used to login the user it will validate the user after
	 * login will land on the feedback form page will fetch the feedback list
	 * for the user and show the list
	 * 
	 * @param userName
	 * @param password
	 * @param model
	 * @return
	 */
	@GetMapping("/login")
	public String login(@RequestBody @Valid LoginForm loginForm, Model model) {

		User user = userService.findUserByEmail(loginForm.getEmailId());

		if (user == null) {
			throw new UserNotFoundException("User does not exist");
		}

		userService.validateUser(loginForm.getEmailId(), loginForm.getPassword());

		List<Feedback> feedbackList = feedBackService.getFeedbackList(user, false);
		model.addAttribute("feedbackList", feedbackList);

		return "feedbackList";

	}

}

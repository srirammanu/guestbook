package com.guestbook.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.guestbook.cache.DataProvider;
import com.guestbook.entity.Feedback;
import com.guestbook.entity.LoginForm;
import com.guestbook.entity.User;
import com.guestbook.exception.UserNotFoundException;
import com.guestbook.service.FeedbackService;
import com.guestbook.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	FeedbackService feedBackService;

	@Autowired
	DataProvider dataProvider;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	/**
	 * This API will open the registration form for the user
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/register_user")
	public String showRegistrationForm(Model model) {
		
		LOGGER.info("showRegistrationForm api of UserController");
		model.addAttribute("user", new User());

		return "registration_form";
	}

	/**
	 * This API will be used to register the user. Once user will be registered
	 * he/she will be redirected to the feedback form page
	 * 
	 * @param user
	 *            
	 * @return
	 */
	@PostMapping("/process_register")
	public String processRegister(@Valid User user, BindingResult result, Model model) {
		LOGGER.info("Registering the user with EmailId: ", user.getEmailId());

		User existUser = userService.findUserByEmail(user.getEmailId());

		if (existUser != null) {
			result.rejectValue("user.getEmailId()", null, "User already exists with same Email");
		}

		if (result.hasErrors()) {
			return "registration_form";
		}

		userService.saveUser(user);
		LOGGER.info("User saved successfully ");
		model.addAttribute("loginForm", new LoginForm());

		return "login_form";
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
	@GetMapping("/process_login")
	public String login(@Valid LoginForm loginForm, Model model) {

		LOGGER.info("Login the user with EmailId: ", loginForm.getEmailId());

		User user = userService.findUserByEmail(loginForm.getEmailId());

		if (user == null) {
			LOGGER.info("User does not exists with emailId: ", loginForm.getEmailId());
			throw new UserNotFoundException("User does not exist");
		}

		userService.validateUser(loginForm.getEmailId(), loginForm.getPassword());

		List<Feedback> feedbackList = feedBackService.getFeedbackList(user, false);

		LOGGER.info("Feedback List {} of the user {} : ", feedbackList, user.getEmailId());

		model.addAttribute("feedbackList", feedbackList);
		model.addAttribute("userName", user.getName());

		dataProvider.setUser(user);

		return "feedback_List";

	}

}

package com.guestbook.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.guestbook.entity.LoginForm;

/**
 * This is controller class for Homepage for the guestbook. 
 * It will open the homepage as the login form where user can login or register new user
 * 
 * @author DELL
 *
 */
@Controller
public class GuestbookController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GuestbookController.class);

	
	/**
	 * This API will open the login form as a homepage
	 * @param loginForm
	 * @param model
	 * @return
	 */
	@GetMapping("/")
	public String showHomePage(LoginForm loginForm, Model model) {
		LOGGER.info("Showing the home/login page of GuestbookController");
		model.addAttribute("loginForm", new LoginForm());
		return "login_form";
	}

}

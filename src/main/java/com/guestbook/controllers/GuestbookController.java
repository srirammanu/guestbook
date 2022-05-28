package com.guestbook.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.guestbook.entity.LoginForm;

/**
 * This is controller class for Feedback. It provides the service related to
 * feedback which can be performed
 * 
 * @author DELL
 *
 */
@Controller
public class GuestbookController {

	@GetMapping("/")
	public String showHomePage(LoginForm loginForm, Model model) {

		model.addAttribute("loginForm", new LoginForm());
		return "login_form";
	}

//	@GetMapping("/")
//	public String showHomePage(LoginForm loginForm) {
//
//		return "login_form";
//	}
}

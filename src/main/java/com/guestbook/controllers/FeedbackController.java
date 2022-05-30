package com.guestbook.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.guestbook.cache.DataProvider;
import com.guestbook.entity.Feedback;
import com.guestbook.entity.User;
import com.guestbook.service.FeedbackService;

/**
 * This is controller class for Feedback. It provides the service related to
 * feedback which can be performed
 * 
 * @author DELL
 *
 */
@Controller
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;
	
	@Autowired
	DataProvider dataProvider;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackController.class);
	
	/**
	 * This API is responsible to open the feedback form when user can add and save
	 * @param model
	 * @return
	 */
	@GetMapping("/new_feedback")
	public String saveFeedback(Model model) {
		
		LOGGER.info("new_feedback API to open the feedback form");
		
		model.addAttribute("feedback", new Feedback());

		return "feedback_form";
	}

	/**
	 * This API is responsible to save the feedback
	 * After saving the feedback it will show the feedback list for the user
	 * 
	 * @param feedback
	 * @return
	 */
	@PostMapping("/savefeedback")
	public String saveFeedback(Feedback feedback, @RequestParam("image") MultipartFile image, Model model) {
		
		LOGGER.info("savefeedback API to save the feedback data");
		
		feedbackService.saveFeedback(feedback, image);
		
		User user = dataProvider.getUser();
		
		List<Feedback> feedbackList = feedbackService.getFeedbackList(user, false);

		LOGGER.info("Feedback List {} of the user {} : ", feedbackList, user.getEmailId());

		model.addAttribute("feedbackList", feedbackList);
		model.addAttribute("userName", user.getName());

		return "feedback_list";
	}

	/**
	 * This API will be used to approve the feedback, Only admin can approve the
	 * feedback
	 * 
	 * @param feedback
	 * @return
	 */
	@PutMapping("/approve")
	public ResponseEntity<Boolean> approveFeedback(Feedback feedback) {
		
		LOGGER.info("approve API to approve the feedback data by the user ", feedback.getUserId());

		boolean isApproved = feedbackService.approveFeedback(feedback);

		return new ResponseEntity<>(isApproved, HttpStatus.OK);
	}

	/**
	 * This API will be used to delete the feedback, Only admin can delete the
	 * feedback
	 * 
	 * @param feedback
	 * @return
	 */
	@DeleteMapping("/delete")
	public void deleteEntry(Feedback feedback) {
		
		LOGGER.info("delete API to delete the feedback data by the user ", feedback.getUserId());
		
		feedbackService.removeFeedback(feedback);
	}

}

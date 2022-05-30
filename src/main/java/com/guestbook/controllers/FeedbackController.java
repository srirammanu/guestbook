package com.guestbook.controllers;

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

import com.guestbook.entity.Feedback;
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
	
	@GetMapping("/new_feedback")
	public String saveFeedback(Model model) {
		model.addAttribute("feedback", new Feedback());

		return "feedback_form";
	}

	/**
	 * This API is responsible to save the feedback
	 * 
	 * @param feedback
	 * @return
	 */
	@PostMapping("/save_feedback")
	public ResponseEntity<Feedback> saveEntry(Feedback feedback, @RequestParam("image") MultipartFile image) {
		Feedback savedFeedback = feedbackService.saveFeedback(feedback, image);

		return new ResponseEntity<>(savedFeedback, HttpStatus.CREATED);
	}

	@PostMapping("/savefeedback")
	public String saveFeedback(Feedback feedback, @RequestParam("image") MultipartFile image) {
		feedbackService.saveFeedback(feedback, image);

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
		feedbackService.removeFeedback(feedback);
	}

}

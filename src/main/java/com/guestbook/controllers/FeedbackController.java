package com.guestbook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guestbook.entity.Feedback;
import com.guestbook.service.FeedbackService;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;

	@PostMapping("/saveEntry")
	public ResponseEntity<Feedback> saveEntry(Feedback feedback) {
		Feedback savedFeedback = feedbackService.saveFeedback(feedback);

		return new ResponseEntity<Feedback>(savedFeedback, HttpStatus.CREATED);
	}

	@PutMapping("/approve")
	public ResponseEntity<Boolean> approveFeedback(Feedback feedback) {

		boolean isApproved = feedbackService.approveFeedback(feedback);

		return new ResponseEntity<Boolean>(isApproved, HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public void deleteEntry(Feedback feedback) {
		feedbackService.revmoeFeedback(feedback);
	}

}

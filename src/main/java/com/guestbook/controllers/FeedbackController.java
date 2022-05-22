package com.guestbook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
@RestController
@RequestMapping("/feedback")
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;

	/**
	 * This API is responsible to save the feedback
	 * 
	 * @param feedback
	 * @return
	 */
	@PostMapping("/save")
	public ResponseEntity<Feedback> saveEntry(@RequestBody Feedback feedback,
			@RequestParam("image") MultipartFile image) {
		Feedback savedFeedback = feedbackService.saveFeedback(feedback, image);

		return new ResponseEntity<>(savedFeedback, HttpStatus.CREATED);
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
		feedbackService.revmoeFeedback(feedback);
	}

}

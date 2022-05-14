package com.guestbook.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.guestbook.entity.Feedback;
import com.guestbook.entity.User;
import com.guestbook.repository.FeedbackRepository;

public class FeedbackServiceImpl implements FeedbackService{
	
	@Autowired
	FeedbackRepository feedbackRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(FeedbackServiceImpl.class);

	@Override
	public Feedback saveFeedback(Feedback feedback) {
		logger.info("saving feedback for user :", feedback.getCreateBy());
		return feedbackRepository.save(feedback);
	}

	@Override
	public List<Feedback> getFeedbackList(User user) {
		logger.info("Fetch feedback for user :", user.getName());
		return feedbackRepository.findFeedbackForUser(user.getName());
	}

	@Override
	public boolean approveFeedback(Feedback feedback) {
		
		return false;
	}

	@Override
	public boolean revmoeFeedback(Feedback feedback) {
		feedbackRepository.delete(feedback);
		return true;
	}

	@Override
	public Feedback editFeedback(Feedback feedback) {
		feedbackRepository.save(feedback);
		return null;
	}

}

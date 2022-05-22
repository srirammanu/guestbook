package com.guestbook.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.guestbook.entity.Feedback;
import com.guestbook.entity.User;

@Repository
public interface FeedbackRepository {
	
	List<Feedback> findFeedbackForUser(User user);
	
	List<Feedback> findAllFeedback();
	
	Feedback saveFeedback(Feedback feedback);
	
	boolean removeFeedback(Feedback feedback);
	
}

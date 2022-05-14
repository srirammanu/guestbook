package com.guestbook.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.guestbook.entity.Feedback;

@Repository
public interface FeedbackRepository extends CrudRepository<Feedback, Long>{
	
	boolean approveFeedback(Feedback feedback);
	
	List<Feedback> findFeedbackForUser(String userName);
	
}

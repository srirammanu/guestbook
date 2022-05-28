package com.guestbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.guestbook.entity.Feedback;
import com.guestbook.entity.User;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long>{
	
	@Query(value = "select s from Feedback s where s.userId = :userId")
	List<Feedback> findFeedbackForUser(@Param("userId") User user);
	
	@Query(value = "select s from Feedback s")
	List<Feedback> findAllFeedback();
	
}

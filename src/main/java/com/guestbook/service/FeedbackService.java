package com.guestbook.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.guestbook.entity.Feedback;
import com.guestbook.entity.User;

@Service
public interface FeedbackService {

	Feedback saveFeedback(Feedback feedback, MultipartFile image);

	List<Feedback> getFeedbackList(User user, boolean isRemovedAlso);

	boolean approveFeedback(Feedback feedback);

	boolean revmoeFeedback(Feedback feedback);

	Feedback editFeedback(Feedback feedback);

}

package com.guestbook.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.guestbook.constants.Roles;
import com.guestbook.entity.Feedback;
import com.guestbook.entity.User;
import com.guestbook.repository.FeedbackRepository;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	FeedbackRepository feedbackRepository;

	private static final Logger logger = LoggerFactory.getLogger(FeedbackServiceImpl.class);

	@Override
	public Feedback saveFeedback(Feedback feedback, MultipartFile image) {
		logger.info("saving feedback for user :", feedback.getUserId());

		if (image != null) {
			String fileName = StringUtils.cleanPath(image.getOriginalFilename());
			feedback.setFeedbackImg(fileName);

			String uploadDir = "user-photos/" + feedback.getUserId().getUserId();
			saveFile(fileName, uploadDir, image);
			try {
				feedback.setFileimage(image.getBytes());
			} catch (IOException ie) {
				logger.error("Error in converting image to byte array", ie);
			}
		}
		feedbackRepository.save(feedback);

		return feedback;
	}

	private void saveFile(String fileName, String uploadDir, MultipartFile image) {
		Path uploadPath = Paths.get(uploadDir);

		if (!Files.exists(uploadPath)) {
			try (InputStream inputStream = image.getInputStream()) {
				Files.createDirectory(uploadPath);

				Path filePath = uploadPath.resolve(fileName);
				Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

			} catch (IOException e) {
				logger.error("Error in saving image", e);
			}
		}
	}

	@Override
	public List<Feedback> getFeedbackList(User user, boolean isRemovedAlso) {
		logger.info("Fetch feedback for user :", user.getName());
		List<Feedback> feedbackList;
		if (Roles.ADMIN.toString().equals(user.getRole())) {
			feedbackList = feedbackRepository.findAllFeedback();
		} else {
			feedbackList = feedbackRepository.findFeedbackForUser(user);
		}
		return feedbackList;
	}

	@Override
	public boolean approveFeedback(Feedback feedback) {
		User user = feedback.getUserId();
		if (Roles.ADMIN.toString().equals(user.getRole())) {
			feedback.setFeedbackApproved(true);
			feedbackRepository.save(feedback);
			return true;
		} else {
			throw new RuntimeException("Only Admin User can approve the entry");
		}

	}

	@Override
	public boolean removeFeedback(Feedback feedback) {
		User user = feedback.getUserId();
		if (Roles.ADMIN.toString().equals(user.getRole())) {
			feedbackRepository.delete(feedback);
			return true;
		} else {
			throw new RuntimeException("Only Admin User can delete the entry");
		}
	}

	@Override
	public Feedback editFeedback(Feedback feedback) {
		User user = feedback.getUserId();
		if (Roles.ADMIN.toString().equals(user.getRole())) {
			feedbackRepository.save(feedback);
			return feedback;
		} else {
			throw new RuntimeException("Only Admin User can update the entry");
		}
	}

}

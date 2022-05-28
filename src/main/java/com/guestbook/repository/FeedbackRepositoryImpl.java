//package com.guestbook.repository;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import javax.persistence.TypedQuery;
//
//import org.springframework.stereotype.Repository;
//
//import com.guestbook.entity.Feedback;
//import com.guestbook.entity.User;
//
//@Repository
//public class FeedbackRepositoryImpl implements FeedbackRepository {
//
//	@PersistenceContext
//	private EntityManager entityManager;
//
//	@Override
//	public Feedback saveFeedback(Feedback feedback) {
//		entityManager.persist(feedback);
//		return feedback;
//	}
//
//	@Override
//	public List<Feedback> findFeedbackForUser(User user) {
//		
//		Query query = entityManager.createNamedQuery("Feedback.findFeedbackForUser");
//		
//		query.setParameter("userId", user);
//		
//		return query.getResultList();
//	}
//
//	@Override
//	public List<Feedback> findAllFeedback() {
//		String sql = "SELECT u FROM Feedback u";
//		TypedQuery<Feedback> query = entityManager.createQuery(sql, Feedback.class);
//
//		return query.getResultList();
//	}
//
//	@Override
//	public boolean removeFeedback(Feedback feedback) {
//		entityManager.remove(feedback);
//		return true;
//	}
//
//}

package com.guestbook.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.guestbook.entity.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<User> findUserByEmail(String email) {
		String sql = "SELECT u FROM User u WHERE u.emailId=:id";
		TypedQuery<User> query = entityManager.createQuery(sql, User.class);
		query.setParameter("id", email);

		return query.getResultList();
	}

	@Override
	public List<User> validateUser(String email, String password) {
		String sql = "SELECT u FROM User u WHERE u.emailId=:id and password = :password";
		TypedQuery<User> query = entityManager.createQuery(sql, User.class);
		query.setParameter("id", email);
		query.setParameter("password", password);

		return query.getResultList();
	}

	@Override
	public User saveUser(User user) {
		entityManager.persist(user);
		return user;
	}

}

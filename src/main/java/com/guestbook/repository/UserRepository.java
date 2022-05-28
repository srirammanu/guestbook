package com.guestbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.guestbook.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query(value = "SELECT u FROM User u where u.emailId = :email")
	List<User> findUserByEmail(@Param("email") String emailId);
	
	@Query(value = "SELECT u FROM User u where u.emailId = :email and password = :password")
	List<User> validateUser(@Param("email") String emailId, @Param("password") String password);
	
}

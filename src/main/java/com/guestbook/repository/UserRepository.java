package com.guestbook.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.guestbook.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	User findUserByName(String name);

}

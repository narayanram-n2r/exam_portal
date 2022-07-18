
package com.group7.wipro.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.group7.wipro.model.User;

public interface UserDAO extends MongoRepository<User, String> {
	public User findByEmail(String email);
}

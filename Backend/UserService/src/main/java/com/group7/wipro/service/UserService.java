package com.group7.wipro.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group7.wipro.dao.UserDAO;
import com.group7.wipro.exception.UserAlreadyExistsException;
import com.group7.wipro.exception.UserNotFoundException;
import com.group7.wipro.model.User;

@Service
public class UserService {
	@Autowired
	UserDAO dao;
	
	public User addUser(User u)throws UserAlreadyExistsException {
			User existing = dao.findByEmail(u.getEmail());
			if(existing!=null) {
				throw new UserAlreadyExistsException("User with email "+u.getEmail()+" already exists");
			}
			u.setUserAddedDate(new Date());
			System.out.println(u);
			return dao.save(u);
	}
	
	public List<User> getAllUsers(){
		return dao.findAll();
	}
	public User validate(String email, String password) throws UserNotFoundException {
		// TODO Auto-generated method stub
		User user = dao.findByEmailAndPassword(email,password);
		if(user==null) {
			throw new UserNotFoundException("User doesn't exists!! Credentials entered are invalid");
		}
		return user;
	}
	public User updateUser(User user, String email) throws UserNotFoundException{
		User user1 = dao.findByEmail(email);
		if(user1==null){
			throw new UserNotFoundException("User doesn't exists");
		}
		user1.setFirstName(user.getFirstName());
		user1.setLastName(user.getLastName());
		user1.setCity(user.getCity());
		user1.setMobile(user.getMobile());
		user1.setPassword(user.getPassword());
		return dao.save(user1);
	}
}

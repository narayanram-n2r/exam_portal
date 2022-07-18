package com.group7.wipro.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import com.group7.wipro.exception.UserAlreadyExistsException;
import com.group7.wipro.exception.UserNotFoundException;
import com.group7.wipro.model.User;
import com.group7.wipro.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	
	String jwtToken;
	
	@PostMapping("/signup")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> addUser(@RequestBody User user){
		try {
			User u = service.addUser(user);
			return new ResponseEntity<User>(u, HttpStatus.CREATED);
		} catch (UserAlreadyExistsException e) {
			return new ResponseEntity<String>((e.getMessage()), HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping
	public ResponseEntity<?> getUser(){
		List<User> userList=service.getAllUsers();
		if(userList !=null) {
			return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Failure", HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/login")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> login(@RequestBody User user, HttpSession session){
		try {
			User u = service.validate(user.getEmail(), user.getPassword());
			if(!u.getEmail().equals(user.getEmail())) {
				return new ResponseEntity<String>("User does not exists", HttpStatus.CONFLICT);
			}
			session.setAttribute("name", u.getFirstName());
			jwtToken=generateToken(u.getFirstName());
			return new ResponseEntity<User>(u, HttpStatus.OK);	
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>((e.getMessage()), HttpStatus.CONFLICT);
		}
	}
	@GetMapping("/logout")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> logout(HttpSession session){
		if((session!=null)&& session.getAttribute("name")!=null) {
			session.invalidate(); // destroying the session
			return new ResponseEntity<String>("Logged Out Successfuly", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Failure", HttpStatus.CONFLICT);
	}
	
	@PostMapping("/update/{email}")
	@CrossOrigin(origins="http://localhost:4200")
	public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String email)throws UserNotFoundException{
		try {
			User user1 = service.updateUser(user,email);
			return new ResponseEntity<User>(user1, HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<String>((e.getMessage()), HttpStatus.CONFLICT);
		}
	}
	
	public String generateToken(String customerName) {
		String token=Jwts.builder()
				.setSubject(customerName)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+200000))
				.signWith(SignatureAlgorithm.HS256, "secretKey")
				.compact();
		System.out.println("Token "+token);
		return token;
	}
}

package com.group7.wipro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group7.wipro.exception.ArticleAlreadyExistsException;
import com.group7.wipro.exception.ArticleNotFoundException;
import com.group7.wipro.model.Article;
import com.group7.wipro.model.User;
import com.group7.wipro.service.FavouritesService;


@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.DELETE})

public class FavouritesController {

	@Autowired
	private FavouritesService service;

	
	@PostMapping("/{emailId}/article")
//	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> addArticle(@RequestBody Article article,@PathVariable String emailId) throws ArticleAlreadyExistsException {
		System.out.println(article);
		if(emailId!=null) {
			try {
				User user1 = service.saveArticleToFavourite(article, emailId);
				System.out.println(article);
				if(user1.getEmail()!=null) {
					System.out.println(article);
					return new ResponseEntity<User>(user1, HttpStatus.CREATED);
				}
				else {
					System.out.println("Username not found...may be its null");
				}
				
			} catch (Exception e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

			}
		}
		return null;
	}
	
	@DeleteMapping("/{emailId}/{id}")
//	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> deleteFromList(@PathVariable int id, @PathVariable String emailId)
			throws ArticleNotFoundException {
		try {
			User user1 = service.deleteArticleFromFavorite(id, emailId);
			return new ResponseEntity<User>(user1, HttpStatus.OK);
		} catch (ArticleNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);

		}
	}
	
	@GetMapping("/fav/{email}")
//	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getArticleList(@PathVariable String email) throws ArticleNotFoundException {
		try {
			List<Article> articleList = service.getArticlesList(email);
			return new ResponseEntity(articleList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

}

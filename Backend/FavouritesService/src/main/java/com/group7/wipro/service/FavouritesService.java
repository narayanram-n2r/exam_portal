package com.group7.wipro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group7.wipro.exception.ArticleAlreadyExistsException;
import com.group7.wipro.exception.ArticleNotFoundException;
import com.group7.wipro.model.Article;
import com.group7.wipro.model.User;
import com.group7.wipro.repository.UserDAO;

@Service
public class FavouritesService {
	@Autowired
	UserDAO repository;
	
	public User saveArticleToFavourite(Article article, String email)throws ArticleAlreadyExistsException {
		User u = repository.findByEmail(email);
		System.out.println(email);
		if(u==null) {
			u = new User(email, new ArrayList<Article>());
			System.out.println(email);
		}
		List<Article> articleList = u.getArticleList();
		if(articleList!=null) {
			for(Article a: articleList) {
				if(a.getTitle().equals(article.getTitle())) {
					throw new ArticleAlreadyExistsException("Article is already in your favourites list");
				}
			}

			System.out.println(email);
			articleList.add(article);
			repository.save(u);
		}

		System.out.println(email);
		return u;
	}
	public User deleteArticleFromFavorite(int id, String emailId) throws ArticleNotFoundException {
		User user1 = repository.findByEmail(emailId);
		int indexnum = 0;
		List<Article> articleList = user1.getArticleList();

		if (articleList != null && articleList.size() > 0) {
			for (Article t : articleList) {
				indexnum++;
				if (t.getArticleId()==id) {
					articleList.remove(indexnum - 1);
					user1.setArticleList(articleList);
					repository.save(user1);
					break;
				}
			}
		}

		else {
			throw new ArticleNotFoundException("Article does not exists");
		}
		return user1;
	}
	public List<Article> getArticlesList(String email) throws Exception {
		User user1 = repository.findByEmail(email);
		return user1.getArticleList();
	}
}

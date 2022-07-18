package com.group7.wipro.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Article {
	@Id
	private int articleId;
	@JsonProperty("source")
	private Source source;
	@JsonProperty("author")
	private String author;
	@JsonProperty("title")
	private String title;
	@JsonProperty("url")
	private String url;
	@JsonProperty("urlToImage")
	private String urlToImage;
	@JsonProperty("publishedAt")
	private String publishedAt;
	@JsonProperty("description")
	private String description;
	@JsonProperty("content")
	private String content;
	
	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Article(Source source, String author, String title, String url, String urlToImage, String publishedAt, String description, String content) {
		this.source = source;
		this.author = author;
		this.title = title;
		this.url = url;
		this.urlToImage = urlToImage;
		this.publishedAt = publishedAt;
		this.description = description;
		this.content = content;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlToImage() {
		return urlToImage;
	}

	public void setUrlToImage(String urlToImage) {
		this.urlToImage = urlToImage;
	}

	public String getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(String publishedAt) {
		this.publishedAt = publishedAt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Article{" +
				"articleId=" + articleId +
				", source=" + source +
				", author='" + author + '\'' +
				", title='" + title + '\'' +
				", url='" + url + '\'' +
				", urlToImage='" + urlToImage + '\'' +
				", publishedAt='" + publishedAt + '\'' +
				", description='" + description + '\'' +
				", content='" + content + '\'' +
				'}';
	}
}
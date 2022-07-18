package com.group7.wipro.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Source {
	
	@JsonProperty("id")
	private String id;
	@JsonProperty("name")
	private String name;
	public String getId() {
		return id;
	}
	public Source() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Source(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Source [id=" + id + ", name=" + name + "]";
	}
}

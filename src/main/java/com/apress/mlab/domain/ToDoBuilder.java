package com.apress.mlab.domain;


public class ToDoBuilder {
	private static ToDoBuilder instance = new ToDoBuilder();
	private String id = null;
	private String description = "";
	private String username = "";
	private String password = "";

	private ToDoBuilder() {
	}

	public static ToDoBuilder create() {
		return instance;
	}

	public ToDoBuilder withDescription(String description, String username, String password) {
		this.description = description;
		this.username = username;
		this.password = password;
		return instance;
	}

	public ToDoBuilder withId(String id) {
		this.id = id;
		return instance;
	}

	public ToDo build() {
		ToDo result = new ToDo(this.description, this.username, this.password);
		if (id != null)
			result.setId(id);
		return result;
	}
}
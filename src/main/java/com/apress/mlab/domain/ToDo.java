package com.apress.mlab.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Document
public class ToDo {

	@NotNull
	@Id
	private String id;
	@NotNull
	@NotBlank
	private String description;
	@NotNull
	@NotBlank
	private String username;

	@NotNull
	@NotBlank
	private String password;
	
	private LocalDateTime created;
	private LocalDateTime modified;
	private boolean completed;

	public ToDo() {
		LocalDateTime date = LocalDateTime.now();
		this.id = UUID.randomUUID().toString();
		this.created = date;
		this.modified = date;
	}

	public ToDo(String description, String username, String password) {
		this();
		this.description = description;
		this.username = username;
		this.password = password;
	}
}
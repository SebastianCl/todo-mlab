package com.apress.mlab.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Document
public class ProfileUser {

	@NotNull
	@Id
	private String id;
	
	@NotNull
	@NotBlank
	private String username;

	@NotNull
	@NotBlank
	private String password;	
	

	public ProfileUser() {		
		this.id = UUID.randomUUID().toString();		
	}

	public ProfileUser(String username, String password) {
		this();		
		this.username = username;
		this.password = password;
	}
}
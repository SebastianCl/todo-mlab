package com.apress.mlab.domain;

public class ProfileUserBuilder {

	private static ProfileUserBuilder instance = new ProfileUserBuilder();
	private String id = null;
	private String username = "";
	private String password = "";

	private ProfileUserBuilder() {
	}

	public static ProfileUserBuilder create() {
		return instance;
	}

	public ProfileUserBuilder withArgs(String username, String password) {
		this.username = username;
		this.password = password;
		return instance;
	}

	public ProfileUserBuilder withId(String id) {
		this.id = id;
		return instance;
	}

	public ProfileUser build() {
		ProfileUser result = new ProfileUser(this.username, this.password);
		if (id != null)
			result.setId(id);
		return result;
	}
}
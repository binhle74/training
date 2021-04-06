package com.terralogic.sample.payload;

import lombok.Getter;

public class SigninRequest {
	@Getter
	private String usernameOrEmail;
	
	@Getter
	private String password;
}

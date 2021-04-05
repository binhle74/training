package com.terralogic.sample.payload;

import lombok.Getter;

@Getter
public class SignupRequest {
	private String name;
	
	private String username;
	
	private String email;
	
	private String password;
}

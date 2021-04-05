package com.terralogic.sample.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class SignupResponse {
	@Getter @Setter
	private boolean success;
	
	@Getter @Setter
	private String message;
}

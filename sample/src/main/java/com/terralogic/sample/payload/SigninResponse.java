package com.terralogic.sample.payload;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class SigninResponse {
	@Getter @Setter
	@NonNull
	private String accessToken;
	
	@Getter
	private String tokenType = "Bearer";
}

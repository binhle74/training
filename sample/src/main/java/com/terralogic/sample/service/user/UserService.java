package com.terralogic.sample.service.user;

import com.terralogic.sample.model.User;
import com.terralogic.sample.payload.SignupRequest;

public interface UserService {
	boolean existsByUsername(String username);
	
	boolean existsByEmail(String email);
	
	User createUser(SignupRequest signupRequest);
}

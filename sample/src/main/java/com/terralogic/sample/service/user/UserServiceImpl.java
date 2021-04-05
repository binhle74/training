package com.terralogic.sample.service.user;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.terralogic.sample.model.Role;
import com.terralogic.sample.model.User;
import com.terralogic.sample.payload.SignupRequest;
import com.terralogic.sample.repository.RoleRepository;
import com.terralogic.sample.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}
	
	@Override
	public User createUser(SignupRequest signupRequest) {
		User user = new User(signupRequest.getName(), signupRequest.getUsername(),
				this.passwordEncoder.encode(signupRequest.getPassword()), signupRequest.getEmail());
		
		Role role = this.roleRepository.findByName("ROLE_USER")
				.orElseThrow(() -> new RuntimeException("User role not found"));
		user.setRoles(Collections.singleton(role));

		return this.userRepository.save(user);
	}

}

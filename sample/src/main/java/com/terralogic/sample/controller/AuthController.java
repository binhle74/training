package com.terralogic.sample.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.terralogic.sample.model.User;
import com.terralogic.sample.payload.SigninRequest;
import com.terralogic.sample.payload.SigninResponse;
import com.terralogic.sample.payload.SignupRequest;
import com.terralogic.sample.payload.SignupResponse;
import com.terralogic.sample.service.JwtTokenProvider;
import com.terralogic.sample.service.user.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private UserService userService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticate(@RequestBody SigninRequest signinRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signinRequest.getUsernameOrEmail(), signinRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = tokenProvider.generateToken(authentication);
		return ResponseEntity.ok().body(new SigninResponse(token));
	}

	@PutMapping("/signup")
	public ResponseEntity<?> register(@RequestBody SignupRequest signupRequest) {
		if (userService.existsByUsername(signupRequest.getUsername())) {
			return new ResponseEntity<SignupResponse>(new SignupResponse(false, "Username is already taken"),
					HttpStatus.BAD_REQUEST);
		}

		if (userService.existsByEmail(signupRequest.getEmail())) {
			return new ResponseEntity<SignupResponse>(new SignupResponse(false, "Email is already taken"),
					HttpStatus.BAD_REQUEST);
		}

		User user = this.userService.createUser(signupRequest);
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}")
				.buildAndExpand(user.getUsername()).toUri();

		return ResponseEntity.created(location).body(new SignupResponse(true, "User registered successfully"));
	}
}

package com.terralogic.sample.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.terralogic.sample.model.User;
import com.terralogic.sample.payload.UserPrincipal;
import com.terralogic.sample.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		User user = this.userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(
				() -> new UsernameNotFoundException("User not found with username or email: " + usernameOrEmail));
		return UserPrincipal.create(user);
	}

	public UserDetails loadUserById(Long id) {
		User user = this.userRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
		return UserPrincipal.create(user);
	}
}

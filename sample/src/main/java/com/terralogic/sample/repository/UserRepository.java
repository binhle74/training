package com.terralogic.sample.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.terralogic.sample.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsernameOrEmail(String username, String email);
	
	boolean existsByUsername(String username);
	
	boolean existsByEmail(String email);
}

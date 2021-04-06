package com.terralogic.sample.payload;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.terralogic.sample.model.User;

import lombok.Getter;

public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Getter
	private Long id;

	private String name;

	private String username;

	@JsonIgnore
	private String password;

	@JsonIgnore
	private String email;

	public static UserPrincipal create(User user) {
		UserPrincipal userPrincipal = new UserPrincipal();
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		
		userPrincipal.id = user.getId();
		userPrincipal.name = user.getName();
		userPrincipal.username = user.getUsername();
		userPrincipal.password = user.getPassword();
		userPrincipal.email = user.getEmail();
		userPrincipal.authorities = authorities;
		
		return userPrincipal;
	}

	private Collection<? extends GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}

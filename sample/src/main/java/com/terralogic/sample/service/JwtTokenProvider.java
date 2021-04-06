package com.terralogic.sample.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.terralogic.sample.payload.UserPrincipal;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {
	private final static Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

	@Value("${auth.jwt.secret}")
	private String jwtSecret;

	@Value("${auth.jwt.expiration-ms}")
	private Long jwtExpirationMs;

	public String generateToken(Authentication authentication) {
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		Date now = new Date();
		Date exp = new Date(now.getTime() + jwtExpirationMs);
		return Jwts.builder().setSubject(String.valueOf(userPrincipal.getId())).setIssuedAt(now).setExpiration(exp)
				.signWith(SignatureAlgorithm.HS256, jwtSecret).compact();
	}

	public Long getUserIdFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

	public boolean isValidToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid signature");
		} catch (MalformedJwtException | IllegalArgumentException e) {
			logger.error("Invalid token");
		} catch (ExpiredJwtException e) {
			logger.error("Expired token");
		} catch (UnsupportedJwtException e) {
			logger.error("Unsupported token");
		}
		return false;
	}
}

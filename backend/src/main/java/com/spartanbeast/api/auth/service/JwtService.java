package com.spartanbeast.api.auth.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.spartanbeast.api.auth.entity.AppUser;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {

	private final JwtEncoder jwtEncoder;

	@Value("${app.security.jwt.issuer}")
	private String issuer;

	@Value("${app.security.jwt.expiration-minutes}")
	private long expirationMinutes;

	public TokenResult generateToken(AppUser user) {

		Instant now = Instant.now();

		Instant expiresAt = now.plus(expirationMinutes, ChronoUnit.MINUTES);

		JwtClaimsSet claims = JwtClaimsSet.builder().issuer(issuer).issuedAt(now).expiresAt(expiresAt)
				.subject(user.getEmail()).claim("roles", List.of(user.getRole().name())).build();

		Jwt jwt = jwtEncoder.encode(JwtEncoderParameters.from(claims));

		return new TokenResult(jwt.getTokenValue(), expiresAt);
	}

	public record TokenResult(String token, Instant expiresAt) {
	}
}
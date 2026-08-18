package com.spartanbeast.api.auth.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.spartanbeast.api.auth.dto.LoginRequest;
import com.spartanbeast.api.auth.dto.LoginResponse;
import com.spartanbeast.api.auth.entity.AppUser;
import com.spartanbeast.api.auth.repository.AppUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final AuthenticationManager authenticationManager;
	private final AppUserRepository userRepository;
	private final JwtService jwtService;

	public LoginResponse login(LoginRequest request) {

		String email = request.email().trim().toLowerCase();

		authenticationManager
				.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(email, request.password()));

		AppUser user = userRepository.findByEmailIgnoreCase(email).orElseThrow();

		JwtService.TokenResult token = jwtService.generateToken(user);

		return new LoginResponse(token.token(), "Bearer", token.expiresAt(), user.getEmail(), user.getRole());
	}
}
package com.spartanbeast.api.auth.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;

import com.spartanbeast.api.auth.dto.LoginRequest;
import com.spartanbeast.api.auth.dto.LoginResponse;
import com.spartanbeast.api.auth.entity.AppUser;
import com.spartanbeast.api.auth.entity.UserRole;
import com.spartanbeast.api.auth.repository.AppUserRepository;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

	@Mock
	private AuthenticationManager authenticationManager;

	@Mock
	private AppUserRepository userRepository;

	@Mock
	private JwtService jwtService;

	private AuthService authService;

	@BeforeEach
	void setUp() {

		authService = new AuthService(authenticationManager, userRepository, jwtService);
	}

	@Test
	void shouldLoginSuccessfully() {

		// GIVEN
		LoginRequest request = new LoginRequest("ADMIN@SPARTANBEAST.CO", "password");

		AppUser user = AppUser.builder().id(1L).email("admin@spartanbeast.co").passwordHash("fake-hash")
				.role(UserRole.ADMIN).active(true).build();

		Instant expiration = Instant.now().plusSeconds(3600);

		when(userRepository.findByEmailIgnoreCase("admin@spartanbeast.co")).thenReturn(Optional.of(user));

		when(jwtService.generateToken(user)).thenReturn(new JwtService.TokenResult("fake-jwt-token", expiration));

		// WHEN
		LoginResponse response = authService.login(request);

		// THEN
		assertEquals("fake-jwt-token", response.accessToken());

		assertEquals("Bearer", response.tokenType());

		assertEquals("admin@spartanbeast.co", response.email());

		assertEquals(UserRole.ADMIN, response.role());

		verify(authenticationManager).authenticate(any());
	}

	@Test
	void shouldRejectInvalidCredentials() {

		// GIVEN
		LoginRequest request = new LoginRequest("admin@spartanbeast.co", "wrong-password");

		when(authenticationManager.authenticate(any())).thenThrow(new BadCredentialsException("Bad credentials"));

		// WHEN + THEN
		assertThrows(BadCredentialsException.class, () -> authService.login(request));
	}
}
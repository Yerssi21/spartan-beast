package com.spartanbeast.api.auth.dto;

import java.time.Instant;

import com.spartanbeast.api.auth.entity.UserRole;

public record LoginResponse(

		String accessToken,

		String tokenType,

		Instant expiresAt,

		String email,

		UserRole role

) {
}
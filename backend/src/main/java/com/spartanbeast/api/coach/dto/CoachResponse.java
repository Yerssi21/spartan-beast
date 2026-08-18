package com.spartanbeast.api.coach.dto;

import java.time.Instant;
import java.util.List;

public record CoachResponse(

		Long id, String name, String slug, String role, String bio, String imageUrl, String instagramUrl,
		boolean active, Integer displayOrder, List<String> specialties, Instant createdAt, Instant updatedAt

) {
}
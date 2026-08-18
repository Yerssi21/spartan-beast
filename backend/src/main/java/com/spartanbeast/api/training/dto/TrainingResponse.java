package com.spartanbeast.api.training.dto;

import java.time.Instant;

public record TrainingResponse(

		Long id, String name, String slug, String shortDescription, String description, String imageUrl, String icon,
		boolean active, Integer displayOrder, Instant createdAt, Instant updatedAt

) {
}
package com.spartanbeast.api.coach.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record CoachRequest(

		@NotBlank(message = "El nombre del entrenador es obligatorio") @Size(max = 120) String name,

		@NotBlank(message = "El slug es obligatorio") @Size(max = 140) String slug,

		@NotBlank(message = "El cargo del entrenador es obligatorio") @Size(max = 120) String role,

		@Size(max = 1000) String bio,

		@Size(max = 500) String imageUrl,

		@Size(max = 500) String instagramUrl,

		boolean active,

		@NotNull(message = "El orden es obligatorio") @PositiveOrZero(message = "El orden no puede ser negativo") Integer displayOrder,

		List<@NotBlank(message = "La especialidad no puede estar vacía") @Size(max = 100) String> specialties

) {
}
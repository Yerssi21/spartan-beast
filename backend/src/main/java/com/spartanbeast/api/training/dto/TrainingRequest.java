package com.spartanbeast.api.training.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record TrainingRequest(

		@NotBlank(message = "El nombre del entrenamiento es obligatorio") @Size(max = 120, message = "El nombre no puede superar los 120 caracteres") String name,

		@NotBlank(message = "El slug es obligatorio") @Size(max = 140, message = "El slug no puede superar los 140 caracteres") String slug,

		@NotBlank(message = "La descripción corta es obligatoria") @Size(max = 250, message = "La descripción corta no puede superar los 250 caracteres") String shortDescription,

		@Size(max = 3000, message = "La descripción no puede superar los 3000 caracteres") String description,

		@Size(max = 500, message = "La URL de la imagen no puede superar los 500 caracteres") String imageUrl,

		@Size(max = 80, message = "El icono no puede superar los 80 caracteres") String icon,

		boolean active,

		@NotNull(message = "El orden es obligatorio") @PositiveOrZero(message = "El orden no puede ser negativo") Integer displayOrder

) {
}
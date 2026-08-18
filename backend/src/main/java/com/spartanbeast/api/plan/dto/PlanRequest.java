package com.spartanbeast.api.plan.dto;

import java.math.BigDecimal;
import java.util.List;

import com.spartanbeast.api.plan.entity.PlanDuration;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record PlanRequest(

		@NotBlank(message = "El nombre del plan es obligatorio") @Size(max = 100, message = "El nombre no puede superar los 100 caracteres") String name,

		@NotBlank(message = "El slug es obligatorio") @Size(max = 120, message = "El slug no puede superar los 120 caracteres") String slug,

		@Size(max = 500, message = "La descripción no puede superar los 500 caracteres") String description,

		@NotNull(message = "El precio es obligatorio") @DecimalMin(value = "0.0", inclusive = true, message = "El precio no puede ser negativo") BigDecimal price,

		@NotNull(message = "La duración es obligatoria") PlanDuration duration,

		boolean highlighted,

		boolean active,

		@NotNull(message = "El orden es obligatorio") @PositiveOrZero(message = "El orden no puede ser negativo") Integer displayOrder,

		List<@NotBlank(message = "La característica no puede estar vacía") String> features

) {
}
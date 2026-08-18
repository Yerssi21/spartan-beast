package com.spartanbeast.api.plan.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import com.spartanbeast.api.plan.entity.PlanDuration;

public record PlanResponse(

		Long id,

		String name,

		String slug,

		String description,

		BigDecimal price,

		PlanDuration duration,

		boolean highlighted,

		boolean active,

		Integer displayOrder,

		List<String> features,

		Instant createdAt,

		Instant updatedAt

) {
}
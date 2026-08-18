package com.spartanbeast.api.schedule.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record ScheduleRequest(

		@NotNull(message = "El día de la semana es obligatorio") DayOfWeek dayOfWeek,

		LocalTime openingTime,

		LocalTime closingTime,

		boolean closed,

		boolean active,

		@NotNull(message = "El orden es obligatorio") @PositiveOrZero(message = "El orden no puede ser negativo") Integer displayOrder

) {
}
package com.spartanbeast.api.schedule.dto;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalTime;

public record ScheduleResponse(

		Long id, DayOfWeek dayOfWeek, LocalTime openingTime, LocalTime closingTime, boolean closed, boolean active,
		Integer displayOrder, Instant createdAt, Instant updatedAt

) {
}
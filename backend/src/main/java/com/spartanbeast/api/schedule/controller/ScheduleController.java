package com.spartanbeast.api.schedule.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spartanbeast.api.schedule.dto.ScheduleResponse;
import com.spartanbeast.api.schedule.service.ScheduleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Horarios", description = "Horarios públicos de Spartan Beast")
@RestController
@RequestMapping("/api/v1/schedules")
@RequiredArgsConstructor
public class ScheduleController {

	private final ScheduleService scheduleService;

	@Operation(summary = "Obtener horarios activos")
	@GetMapping
	public ResponseEntity<List<ScheduleResponse>> findAllActive() {

		return ResponseEntity.ok(scheduleService.findAllActive());
	}
}
package com.spartanbeast.api.coach.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spartanbeast.api.coach.dto.CoachResponse;
import com.spartanbeast.api.coach.service.CoachService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Entrenadores", description = "Consulta pública del equipo Spartan Beast")
@RestController
@RequestMapping("/api/v1/coaches")
@RequiredArgsConstructor
public class CoachController {

	private final CoachService coachService;

	@Operation(summary = "Obtener entrenadores activos")
	@GetMapping
	public ResponseEntity<List<CoachResponse>> findAllActive() {

		return ResponseEntity.ok(coachService.findAllActive());
	}

	@Operation(summary = "Obtener entrenador por slug")
	@GetMapping("/{slug}")
	public ResponseEntity<CoachResponse> findBySlug(@PathVariable String slug) {

		return ResponseEntity.ok(coachService.findActiveBySlug(slug));
	}
}
package com.spartanbeast.api.training.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spartanbeast.api.training.dto.TrainingResponse;
import com.spartanbeast.api.training.service.TrainingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Entrenamientos", description = "Consulta pública de entrenamientos de Spartan Beast")
@RestController
@RequestMapping("/api/v1/trainings")
@RequiredArgsConstructor
public class TrainingController {

	private final TrainingService trainingService;

	@Operation(summary = "Obtener entrenamientos activos")
	@GetMapping
	public ResponseEntity<List<TrainingResponse>> findAllActive() {

		return ResponseEntity.ok(trainingService.findAllActive());
	}

	@Operation(summary = "Obtener entrenamiento por slug")
	@GetMapping("/{slug}")
	public ResponseEntity<TrainingResponse> findBySlug(@PathVariable String slug) {

		return ResponseEntity.ok(trainingService.findBySlug(slug));
	}
}
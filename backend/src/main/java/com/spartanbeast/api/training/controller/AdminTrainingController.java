package com.spartanbeast.api.training.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spartanbeast.api.training.dto.TrainingRequest;
import com.spartanbeast.api.training.dto.TrainingResponse;
import com.spartanbeast.api.training.service.TrainingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Administración de entrenamientos", description = "Gestión administrativa de entrenamientos")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/v1/admin/trainings")
@RequiredArgsConstructor
public class AdminTrainingController {

	private final TrainingService trainingService;

	@Operation(summary = "Obtener todos los entrenamientos")
	@GetMapping
	public ResponseEntity<List<TrainingResponse>> findAll() {

		return ResponseEntity.ok(trainingService.findAll());
	}

	@Operation(summary = "Obtener entrenamiento por ID")
	@GetMapping("/{id}")
	public ResponseEntity<TrainingResponse> findById(@PathVariable Long id) {

		return ResponseEntity.ok(trainingService.findById(id));
	}

	@Operation(summary = "Crear entrenamiento")
	@PostMapping
	public ResponseEntity<TrainingResponse> create(@Valid @RequestBody TrainingRequest request) {

		TrainingResponse createdTraining = trainingService.create(request);

		URI location = URI.create("/api/v1/admin/trainings/" + createdTraining.id());

		return ResponseEntity.created(location).body(createdTraining);
	}

	@Operation(summary = "Actualizar entrenamiento")
	@PutMapping("/{id}")
	public ResponseEntity<TrainingResponse> update(@PathVariable Long id, @Valid @RequestBody TrainingRequest request) {

		return ResponseEntity.ok(trainingService.update(id, request));
	}

	@Operation(summary = "Eliminar entrenamiento")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {

		trainingService.delete(id);

		return ResponseEntity.noContent().build();
	}
}
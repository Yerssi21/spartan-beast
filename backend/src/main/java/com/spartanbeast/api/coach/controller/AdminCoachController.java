package com.spartanbeast.api.coach.controller;

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

import com.spartanbeast.api.coach.dto.CoachRequest;
import com.spartanbeast.api.coach.dto.CoachResponse;
import com.spartanbeast.api.coach.service.CoachService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Administración de entrenadores", description = "Gestión administrativa del equipo Spartan Beast")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/v1/admin/coaches")
@RequiredArgsConstructor
public class AdminCoachController {

	private final CoachService coachService;

	@Operation(summary = "Obtener todos los entrenadores")
	@GetMapping
	public ResponseEntity<List<CoachResponse>> findAll() {

		return ResponseEntity.ok(coachService.findAll());
	}

	@Operation(summary = "Obtener entrenador por ID")
	@GetMapping("/{id}")
	public ResponseEntity<CoachResponse> findById(@PathVariable Long id) {

		return ResponseEntity.ok(coachService.findById(id));
	}

	@Operation(summary = "Crear entrenador")
	@PostMapping
	public ResponseEntity<CoachResponse> create(@Valid @RequestBody CoachRequest request) {

		CoachResponse createdCoach = coachService.create(request);

		URI location = URI.create("/api/v1/admin/coaches/" + createdCoach.id());

		return ResponseEntity.created(location).body(createdCoach);
	}

	@Operation(summary = "Actualizar entrenador")
	@PutMapping("/{id}")
	public ResponseEntity<CoachResponse> update(@PathVariable Long id, @Valid @RequestBody CoachRequest request) {

		return ResponseEntity.ok(coachService.update(id, request));
	}

	@Operation(summary = "Eliminar entrenador")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {

		coachService.delete(id);

		return ResponseEntity.noContent().build();
	}
}
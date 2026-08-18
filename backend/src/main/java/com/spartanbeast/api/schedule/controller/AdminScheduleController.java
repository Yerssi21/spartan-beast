package com.spartanbeast.api.schedule.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spartanbeast.api.schedule.dto.ScheduleRequest;
import com.spartanbeast.api.schedule.dto.ScheduleResponse;
import com.spartanbeast.api.schedule.service.ScheduleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Administración de horarios", description = "Gestión de horarios de Spartan Beast")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/v1/admin/schedules")
@RequiredArgsConstructor
public class AdminScheduleController {

	private final ScheduleService scheduleService;

	@Operation(summary = "Obtener todos los horarios")
	@GetMapping
	public ResponseEntity<List<ScheduleResponse>> findAll() {
		return ResponseEntity.ok(scheduleService.findAll());
	}

	@Operation(summary = "Obtener horario por ID")
	@GetMapping("/{id}")
	public ResponseEntity<ScheduleResponse> findById(@PathVariable Long id) {
		return ResponseEntity.ok(scheduleService.findById(id));
	}

	@Operation(summary = "Crear horario")
	@PostMapping
	public ResponseEntity<ScheduleResponse> create(@Valid @RequestBody ScheduleRequest request) {

		ScheduleResponse created = scheduleService.create(request);

		URI location = URI.create("/api/v1/admin/schedules/" + created.id());

		return ResponseEntity.created(location).body(created);
	}

	@Operation(summary = "Actualizar horario")
	@PutMapping("/{id}")
	public ResponseEntity<ScheduleResponse> update(@PathVariable Long id, @Valid @RequestBody ScheduleRequest request) {

		return ResponseEntity.ok(scheduleService.update(id, request));
	}

	@Operation(summary = "Eliminar horario")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {

		scheduleService.delete(id);

		return ResponseEntity.noContent().build();
	}
}
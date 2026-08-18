package com.spartanbeast.api.plan.controller;

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

import com.spartanbeast.api.plan.dto.PlanRequest;
import com.spartanbeast.api.plan.dto.PlanResponse;
import com.spartanbeast.api.plan.service.PlanService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Administración de planes", description = "Operaciones administrativas para gestionar los planes de Spartan Beast")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/v1/admin/plans")
@RequiredArgsConstructor
public class AdminPlanController {

	private final PlanService planService;

	@Operation(summary = "Obtener todos los planes")
	@GetMapping
	public ResponseEntity<List<PlanResponse>> findAll() {

		return ResponseEntity.ok(planService.findAll());
	}

	@Operation(summary = "Obtener un plan por ID")
	@GetMapping("/{id}")
	public ResponseEntity<PlanResponse> findById(@PathVariable Long id) {

		return ResponseEntity.ok(planService.findById(id));
	}

	@Operation(summary = "Crear un plan", description = "Crea un nuevo plan de entrenamiento con sus características")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Plan creado correctamente"),
			@ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content),
			@ApiResponse(responseCode = "409", description = "Ya existe un plan con ese slug", content = @Content) })
	@PostMapping
	public ResponseEntity<PlanResponse> create(@Valid @RequestBody PlanRequest request) {

		PlanResponse createdPlan = planService.create(request);

		URI location = URI.create("/api/v1/admin/plans/" + createdPlan.id());

		return ResponseEntity.created(location).body(createdPlan);
	}

	@Operation(summary = "Actualizar un plan")
	@PutMapping("/{id}")
	public ResponseEntity<PlanResponse> update(@PathVariable Long id, @Valid @RequestBody PlanRequest request) {

		return ResponseEntity.ok(planService.update(id, request));
	}

	@Operation(summary = "Eliminar un plan")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {

		planService.delete(id);

		return ResponseEntity.noContent().build();
	}
}
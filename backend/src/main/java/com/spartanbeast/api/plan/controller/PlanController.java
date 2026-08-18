package com.spartanbeast.api.plan.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spartanbeast.api.plan.dto.PlanResponse;
import com.spartanbeast.api.plan.service.PlanService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Planes", description = "Consulta pública de los planes disponibles de Spartan Beast")
@RestController
@RequestMapping("/api/v1/plans")
@RequiredArgsConstructor
public class PlanController {

	private final PlanService planService;

	@Operation(summary = "Obtener planes disponibles", description = "Devuelve únicamente los planes activos ordenados para mostrarlos en la web")
	@GetMapping
	public ResponseEntity<List<PlanResponse>> findAllActive() {

		return ResponseEntity.ok(planService.findAllActive());
	}

	@Operation(summary = "Obtener plan por slug")
	@GetMapping("/{slug}")
	public ResponseEntity<PlanResponse> findBySlug(@PathVariable String slug) {

		return ResponseEntity.ok(planService.findBySlug(slug));
	}
}
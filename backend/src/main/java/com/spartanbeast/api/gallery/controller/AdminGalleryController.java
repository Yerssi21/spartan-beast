package com.spartanbeast.api.gallery.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spartanbeast.api.gallery.dto.GalleryItemRequest;
import com.spartanbeast.api.gallery.dto.GalleryItemResponse;
import com.spartanbeast.api.gallery.service.GalleryItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Administración de galería", description = "Gestión del contenido multimedia de Spartan Beast")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/v1/admin/gallery")
@RequiredArgsConstructor
public class AdminGalleryController {

	private final GalleryItemService galleryService;

	@Operation(summary = "Obtener todos los elementos")
	@GetMapping
	public ResponseEntity<List<GalleryItemResponse>> findAll() {

		return ResponseEntity.ok(galleryService.findAll());
	}

	@Operation(summary = "Obtener elemento por ID")
	@GetMapping("/{id}")
	public ResponseEntity<GalleryItemResponse> findById(@PathVariable Long id) {

		return ResponseEntity.ok(galleryService.findById(id));
	}

	@Operation(summary = "Crear elemento")
	@PostMapping
	public ResponseEntity<GalleryItemResponse> create(@Valid @RequestBody GalleryItemRequest request) {

		GalleryItemResponse created = galleryService.create(request);

		URI location = URI.create("/api/v1/admin/gallery/" + created.id());

		return ResponseEntity.created(location).body(created);
	}

	@Operation(summary = "Actualizar elemento")
	@PutMapping("/{id}")
	public ResponseEntity<GalleryItemResponse> update(@PathVariable Long id,
			@Valid @RequestBody GalleryItemRequest request) {

		return ResponseEntity.ok(galleryService.update(id, request));
	}

	@Operation(summary = "Eliminar elemento")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {

		galleryService.delete(id);

		return ResponseEntity.noContent().build();
	}
}
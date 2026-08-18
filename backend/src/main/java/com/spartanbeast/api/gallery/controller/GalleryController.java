package com.spartanbeast.api.gallery.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spartanbeast.api.gallery.dto.GalleryItemResponse;
import com.spartanbeast.api.gallery.service.GalleryItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Galería", description = "Contenido multimedia público de Spartan Beast")
@RestController
@RequestMapping("/api/v1/gallery")
@RequiredArgsConstructor
public class GalleryController {

	private final GalleryItemService galleryService;

	@Operation(summary = "Obtener galería activa")
	@GetMapping
	public ResponseEntity<List<GalleryItemResponse>> findAllActive() {

		return ResponseEntity.ok(galleryService.findAllActive());
	}

	@Operation(summary = "Obtener contenido destacado")
	@GetMapping("/featured")
	public ResponseEntity<List<GalleryItemResponse>> findFeatured() {

		return ResponseEntity.ok(galleryService.findFeatured());
	}
}
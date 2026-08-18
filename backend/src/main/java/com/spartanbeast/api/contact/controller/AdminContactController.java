package com.spartanbeast.api.contact.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spartanbeast.api.contact.dto.ContactResponse;
import com.spartanbeast.api.contact.dto.ContactStatusRequest;
import com.spartanbeast.api.contact.entity.ContactStatus;
import com.spartanbeast.api.contact.service.ContactService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Administración de contactos", description = "Gestión de mensajes recibidos desde la web")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/v1/admin/contact-messages")
@RequiredArgsConstructor
public class AdminContactController {

	private final ContactService contactService;

	@Operation(summary = "Obtener todos los mensajes")
	@GetMapping
	public ResponseEntity<List<ContactResponse>> findAll(@RequestParam(required = false) ContactStatus status) {

		if (status != null) {
			return ResponseEntity.ok(contactService.findByStatus(status));
		}

		return ResponseEntity.ok(contactService.findAll());
	}

	@Operation(summary = "Obtener mensaje por ID")
	@GetMapping("/{id}")
	public ResponseEntity<ContactResponse> findById(@PathVariable Long id) {

		return ResponseEntity.ok(contactService.findById(id));
	}

	@Operation(summary = "Cambiar estado del mensaje")
	@PatchMapping("/{id}/status")
	public ResponseEntity<ContactResponse> updateStatus(@PathVariable Long id,
			@Valid @RequestBody ContactStatusRequest request) {

		return ResponseEntity.ok(contactService.updateStatus(id, request.status()));
	}

	@Operation(summary = "Eliminar mensaje")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {

		contactService.delete(id);

		return ResponseEntity.noContent().build();
	}
}
package com.spartanbeast.api.contact.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spartanbeast.api.contact.dto.ContactRequest;
import com.spartanbeast.api.contact.dto.ContactResponse;
import com.spartanbeast.api.contact.service.ContactService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Contacto", description = "Formulario público de contacto de Spartan Beast")
@RestController
@RequestMapping("/api/v1/contact")
@RequiredArgsConstructor
public class ContactController {

	private final ContactService contactService;

	@Operation(summary = "Enviar mensaje de contacto", description = "Registra una nueva solicitud enviada desde la web")
	@PostMapping
	public ResponseEntity<ContactResponse> create(@Valid @RequestBody ContactRequest request) {

		ContactResponse created = contactService.create(request);

		URI location = URI.create("/api/v1/admin/contact-messages/" + created.id());

		return ResponseEntity.created(location).body(created);
	}
}
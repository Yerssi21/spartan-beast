package com.spartanbeast.api.contact.dto;

import java.time.Instant;

import com.spartanbeast.api.contact.entity.ContactStatus;

public record ContactResponse(

		Long id, String name, String email, String phone, String interest, String message, ContactStatus status,
		Instant createdAt, Instant updatedAt

) {
}
package com.spartanbeast.api.contact.dto;

import com.spartanbeast.api.contact.entity.ContactStatus;

import jakarta.validation.constraints.NotNull;

public record ContactStatusRequest(

		@NotNull(message = "El estado es obligatorio") ContactStatus status

) {
}
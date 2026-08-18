package com.spartanbeast.api.contact.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ContactRequest(

		@NotBlank(message = "El nombre es obligatorio") @Size(max = 120) String name,

		@NotBlank(message = "El correo es obligatorio") @Email(message = "El correo electrónico no es válido") @Size(max = 180) String email,

		@Size(max = 30) String phone,

		@Size(max = 150) String interest,

		@NotBlank(message = "El mensaje es obligatorio") @Size(max = 1500, message = "El mensaje no puede superar los 1500 caracteres") String message

) {
}
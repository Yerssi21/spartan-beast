package com.spartanbeast.api.gallery.dto;

import com.spartanbeast.api.gallery.entity.MediaType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record GalleryItemRequest(

		@Size(max = 150) String title,

		@NotNull(message = "El tipo de contenido es obligatorio") MediaType mediaType,

		@NotBlank(message = "La URL del contenido es obligatoria") @Size(max = 600) String mediaUrl,

		@Size(max = 600) String thumbnailUrl,

		@Size(max = 250) String altText,

		boolean featured,

		boolean active,

		@NotNull(message = "El orden es obligatorio") @PositiveOrZero(message = "El orden no puede ser negativo") Integer displayOrder

) {
}
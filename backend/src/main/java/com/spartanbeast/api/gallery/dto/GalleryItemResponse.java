package com.spartanbeast.api.gallery.dto;

import java.time.Instant;

import com.spartanbeast.api.gallery.entity.MediaType;

public record GalleryItemResponse(

		Long id, String title, MediaType mediaType, String mediaUrl, String thumbnailUrl, String altText,
		boolean featured, boolean active, Integer displayOrder, Instant createdAt, Instant updatedAt

) {
}
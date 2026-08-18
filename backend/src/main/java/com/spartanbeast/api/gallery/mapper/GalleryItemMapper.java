package com.spartanbeast.api.gallery.mapper;

import org.springframework.stereotype.Component;

import com.spartanbeast.api.gallery.dto.GalleryItemRequest;
import com.spartanbeast.api.gallery.dto.GalleryItemResponse;
import com.spartanbeast.api.gallery.entity.GalleryItem;

@Component
public class GalleryItemMapper {

	public GalleryItem toEntity(GalleryItemRequest request) {

		return GalleryItem.builder().title(request.title()).mediaType(request.mediaType()).mediaUrl(request.mediaUrl())
				.thumbnailUrl(request.thumbnailUrl()).altText(request.altText()).featured(request.featured())
				.active(request.active()).displayOrder(request.displayOrder()).build();
	}

	public GalleryItemResponse toResponse(GalleryItem item) {

		return new GalleryItemResponse(item.getId(), item.getTitle(), item.getMediaType(), item.getMediaUrl(),
				item.getThumbnailUrl(), item.getAltText(), item.isFeatured(), item.isActive(), item.getDisplayOrder(),
				item.getCreatedAt(), item.getUpdatedAt());
	}

	public void updateEntity(GalleryItem item, GalleryItemRequest request) {

		item.setTitle(request.title());
		item.setMediaType(request.mediaType());
		item.setMediaUrl(request.mediaUrl());
		item.setThumbnailUrl(request.thumbnailUrl());
		item.setAltText(request.altText());
		item.setFeatured(request.featured());
		item.setActive(request.active());
		item.setDisplayOrder(request.displayOrder());
	}
}
package com.spartanbeast.api.gallery.service;

import java.util.List;

import com.spartanbeast.api.gallery.dto.GalleryItemRequest;
import com.spartanbeast.api.gallery.dto.GalleryItemResponse;

public interface GalleryItemService {

	GalleryItemResponse create(GalleryItemRequest request);

	List<GalleryItemResponse> findAll();

	List<GalleryItemResponse> findAllActive();

	List<GalleryItemResponse> findFeatured();

	GalleryItemResponse findById(Long id);

	GalleryItemResponse update(Long id, GalleryItemRequest request);

	void delete(Long id);
}
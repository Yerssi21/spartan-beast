package com.spartanbeast.api.gallery.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spartanbeast.api.common.exception.GalleryItemNotFoundException;
import com.spartanbeast.api.gallery.dto.GalleryItemRequest;
import com.spartanbeast.api.gallery.dto.GalleryItemResponse;
import com.spartanbeast.api.gallery.entity.GalleryItem;
import com.spartanbeast.api.gallery.mapper.GalleryItemMapper;
import com.spartanbeast.api.gallery.repository.GalleryItemRepository;
import com.spartanbeast.api.gallery.service.GalleryItemService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class GalleryItemServiceImpl implements GalleryItemService {

	private final GalleryItemRepository galleryRepository;
	private final GalleryItemMapper galleryMapper;

	@Override
	public GalleryItemResponse create(GalleryItemRequest request) {

		GalleryItem item = galleryMapper.toEntity(request);

		GalleryItem savedItem = galleryRepository.save(item);

		return galleryMapper.toResponse(savedItem);
	}

	@Override
	@Transactional(readOnly = true)
	public List<GalleryItemResponse> findAll() {

		return galleryRepository.findAll().stream().map(galleryMapper::toResponse).toList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<GalleryItemResponse> findAllActive() {

		return galleryRepository.findAllByActiveTrueOrderByDisplayOrderAsc().stream().map(galleryMapper::toResponse)
				.toList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<GalleryItemResponse> findFeatured() {

		return galleryRepository.findAllByActiveTrueAndFeaturedTrueOrderByDisplayOrderAsc().stream()
				.map(galleryMapper::toResponse).toList();
	}

	@Override
	@Transactional(readOnly = true)
	public GalleryItemResponse findById(Long id) {

		return galleryMapper.toResponse(findEntityById(id));
	}

	@Override
	public GalleryItemResponse update(Long id, GalleryItemRequest request) {

		GalleryItem item = findEntityById(id);

		galleryMapper.updateEntity(item, request);

		GalleryItem updated = galleryRepository.save(item);

		return galleryMapper.toResponse(updated);
	}

	@Override
	public void delete(Long id) {

		galleryRepository.delete(findEntityById(id));
	}

	private GalleryItem findEntityById(Long id) {

		return galleryRepository.findById(id).orElseThrow(() -> new GalleryItemNotFoundException(id));
	}
}
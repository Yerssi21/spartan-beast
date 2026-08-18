package com.spartanbeast.api.gallery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spartanbeast.api.gallery.entity.GalleryItem;

public interface GalleryItemRepository extends JpaRepository<GalleryItem, Long> {

	List<GalleryItem> findAllByActiveTrueOrderByDisplayOrderAsc();

	List<GalleryItem> findAllByActiveTrueAndFeaturedTrueOrderByDisplayOrderAsc();
}
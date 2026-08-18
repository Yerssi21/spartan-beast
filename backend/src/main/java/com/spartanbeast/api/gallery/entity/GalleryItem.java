package com.spartanbeast.api.gallery.entity;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "gallery_items")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GalleryItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title", length = 150)
	private String title;

	@Enumerated(EnumType.STRING)
	@Column(name = "media_type", nullable = false, length = 20)
	private MediaType mediaType;

	@Column(name = "media_url", nullable = false, length = 600)
	private String mediaUrl;

	@Column(name = "thumbnail_url", length = 600)
	private String thumbnailUrl;

	@Column(name = "alt_text", length = 250)
	private String altText;

	@Column(name = "featured", nullable = false)
	private boolean featured;

	@Column(name = "active", nullable = false)
	private boolean active;

	@Column(name = "display_order", nullable = false)
	private Integer displayOrder;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private Instant createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private Instant updatedAt;
}
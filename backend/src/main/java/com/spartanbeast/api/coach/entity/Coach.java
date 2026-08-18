package com.spartanbeast.api.coach.entity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "coaches")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coach {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false, length = 120)
	private String name;

	@Column(name = "slug", nullable = false, unique = true, length = 140)
	private String slug;

	@Column(name = "role", nullable = false, length = 120)
	private String role;

	@Column(name = "bio", length = 1000)
	private String bio;

	@Column(name = "image_url", length = 500)
	private String imageUrl;

	@Column(name = "instagram_url", length = 500)
	private String instagramUrl;

	@Column(name = "active", nullable = false)
	private boolean active;

	@Column(name = "display_order", nullable = false)
	private Integer displayOrder;

	@Builder.Default
	@OneToMany(mappedBy = "coach", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@OrderBy("displayOrder ASC")
	private List<CoachSpecialty> specialties = new ArrayList<>();

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private Instant createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private Instant updatedAt;
}
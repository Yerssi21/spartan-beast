package com.spartanbeast.api.coach.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spartanbeast.api.coach.entity.Coach;

public interface CoachRepository extends JpaRepository<Coach, Long> {

	Optional<Coach> findBySlug(String slug);

	Optional<Coach> findBySlugAndActiveTrue(String slug);

	List<Coach> findAllByActiveTrueOrderByDisplayOrderAsc();
}
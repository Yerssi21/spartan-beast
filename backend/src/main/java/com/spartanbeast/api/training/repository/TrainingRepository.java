package com.spartanbeast.api.training.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spartanbeast.api.training.entity.Training;

public interface TrainingRepository extends JpaRepository<Training, Long> {

	Optional<Training> findBySlug(String slug);

	List<Training> findAllByActiveTrueOrderByDisplayOrderAsc();

}
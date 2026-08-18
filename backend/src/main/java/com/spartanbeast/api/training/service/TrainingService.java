package com.spartanbeast.api.training.service;

import java.util.List;

import com.spartanbeast.api.training.dto.TrainingRequest;
import com.spartanbeast.api.training.dto.TrainingResponse;

public interface TrainingService {

	TrainingResponse create(TrainingRequest request);

	List<TrainingResponse> findAll();

	List<TrainingResponse> findAllActive();

	TrainingResponse findById(Long id);

	TrainingResponse findBySlug(String slug);

	TrainingResponse update(Long id, TrainingRequest request);

	void delete(Long id);
}
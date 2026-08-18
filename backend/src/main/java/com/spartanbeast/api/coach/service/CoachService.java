package com.spartanbeast.api.coach.service;

import java.util.List;

import com.spartanbeast.api.coach.dto.CoachRequest;
import com.spartanbeast.api.coach.dto.CoachResponse;

public interface CoachService {

	CoachResponse create(CoachRequest request);

	List<CoachResponse> findAll();

	List<CoachResponse> findAllActive();

	CoachResponse findById(Long id);

	CoachResponse findActiveBySlug(String slug);

	CoachResponse update(Long id, CoachRequest request);

	void delete(Long id);
}
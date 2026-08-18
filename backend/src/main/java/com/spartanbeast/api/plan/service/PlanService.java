package com.spartanbeast.api.plan.service;

import java.util.List;

import com.spartanbeast.api.plan.dto.PlanRequest;
import com.spartanbeast.api.plan.dto.PlanResponse;

public interface PlanService {

	PlanResponse create(PlanRequest request);

	List<PlanResponse> findAll();

	List<PlanResponse> findAllActive();

	PlanResponse findById(Long id);

	PlanResponse findBySlug(String slug);

	PlanResponse update(Long id, PlanRequest request);

	void delete(Long id);
}
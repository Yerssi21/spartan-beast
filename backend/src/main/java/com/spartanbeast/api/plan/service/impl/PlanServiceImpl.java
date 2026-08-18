package com.spartanbeast.api.plan.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spartanbeast.api.common.exception.DuplicateSlugException;
import com.spartanbeast.api.common.exception.PlanNotFoundException;
import com.spartanbeast.api.plan.dto.PlanRequest;
import com.spartanbeast.api.plan.dto.PlanResponse;
import com.spartanbeast.api.plan.entity.Plan;
import com.spartanbeast.api.plan.mapper.PlanMapper;
import com.spartanbeast.api.plan.repository.PlanRepository;
import com.spartanbeast.api.plan.service.PlanService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PlanServiceImpl implements PlanService {

	private final PlanRepository planRepository;
	private final PlanMapper planMapper;

	@Override
	public PlanResponse create(PlanRequest request) {

		validateSlug(request.slug(), null);

		Plan plan = planMapper.toEntity(request);

		Plan savedPlan = planRepository.save(plan);

		return planMapper.toResponse(savedPlan);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PlanResponse> findAll() {

		return planRepository.findAll().stream().map(planMapper::toResponse).toList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<PlanResponse> findAllActive() {

		return planRepository.findAllByActiveTrueOrderByDisplayOrderAsc().stream().map(planMapper::toResponse).toList();
	}

	@Override
	@Transactional(readOnly = true)
	public PlanResponse findById(Long id) {

		Plan plan = findEntityById(id);

		return planMapper.toResponse(plan);
	}

	@Override
	@Transactional(readOnly = true)
	public PlanResponse findBySlug(String slug) {

		Plan plan = planRepository.findBySlug(slug).orElseThrow(() -> new PlanNotFoundException(slug));

		return planMapper.toResponse(plan);
	}

	@Override
	public PlanResponse update(Long id, PlanRequest request) {

		Plan plan = findEntityById(id);

		validateSlug(request.slug(), id);

		planMapper.updateEntity(plan, request);

		Plan updatedPlan = planRepository.save(plan);

		return planMapper.toResponse(updatedPlan);
	}

	@Override
	public void delete(Long id) {

		Plan plan = findEntityById(id);

		planRepository.delete(plan);
	}

	private Plan findEntityById(Long id) {

		return planRepository.findById(id).orElseThrow(() -> new PlanNotFoundException(id));
	}

	private void validateSlug(String slug, Long currentPlanId) {

		planRepository.findBySlug(slug)
				.filter(existingPlan -> currentPlanId == null || !existingPlan.getId().equals(currentPlanId))
				.ifPresent(existingPlan -> {
					throw new DuplicateSlugException("un plan", slug);
				});
	}
}
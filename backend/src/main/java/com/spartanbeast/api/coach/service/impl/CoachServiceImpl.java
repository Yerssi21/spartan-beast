package com.spartanbeast.api.coach.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spartanbeast.api.coach.dto.CoachRequest;
import com.spartanbeast.api.coach.dto.CoachResponse;
import com.spartanbeast.api.coach.entity.Coach;
import com.spartanbeast.api.coach.mapper.CoachMapper;
import com.spartanbeast.api.coach.repository.CoachRepository;
import com.spartanbeast.api.coach.service.CoachService;
import com.spartanbeast.api.common.exception.CoachNotFoundException;
import com.spartanbeast.api.common.exception.DuplicateSlugException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CoachServiceImpl implements CoachService {

	private final CoachRepository coachRepository;
	private final CoachMapper coachMapper;

	@Override
	public CoachResponse create(CoachRequest request) {

		validateSlug(request.slug(), null);

		Coach coach = coachMapper.toEntity(request);

		Coach savedCoach = coachRepository.save(coach);

		return coachMapper.toResponse(savedCoach);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CoachResponse> findAll() {

		return coachRepository.findAll().stream().map(coachMapper::toResponse).toList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<CoachResponse> findAllActive() {

		return coachRepository.findAllByActiveTrueOrderByDisplayOrderAsc().stream().map(coachMapper::toResponse)
				.toList();
	}

	@Override
	@Transactional(readOnly = true)
	public CoachResponse findById(Long id) {

		return coachMapper.toResponse(findEntityById(id));
	}

	@Override
	@Transactional(readOnly = true)
	public CoachResponse findActiveBySlug(String slug) {

		Coach coach = coachRepository.findBySlugAndActiveTrue(slug).orElseThrow(() -> new CoachNotFoundException(slug));

		return coachMapper.toResponse(coach);
	}

	@Override
	public CoachResponse update(Long id, CoachRequest request) {

		Coach coach = findEntityById(id);

		validateSlug(request.slug(), id);

		coachMapper.updateEntity(coach, request);

		Coach updatedCoach = coachRepository.save(coach);

		return coachMapper.toResponse(updatedCoach);
	}

	@Override
	public void delete(Long id) {

		Coach coach = findEntityById(id);

		coachRepository.delete(coach);
	}

	private Coach findEntityById(Long id) {

		return coachRepository.findById(id).orElseThrow(() -> new CoachNotFoundException(id));
	}

	private void validateSlug(String slug, Long currentCoachId) {

		coachRepository.findBySlug(slug)
				.filter(existingCoach -> currentCoachId == null || !existingCoach.getId().equals(currentCoachId))
				.ifPresent(existingCoach -> {
					throw new DuplicateSlugException("un entrenador", slug);
				});
	}
}
package com.spartanbeast.api.training.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spartanbeast.api.common.exception.DuplicateSlugException;
import com.spartanbeast.api.common.exception.TrainingNotFoundException;
import com.spartanbeast.api.training.dto.TrainingRequest;
import com.spartanbeast.api.training.dto.TrainingResponse;
import com.spartanbeast.api.training.entity.Training;
import com.spartanbeast.api.training.mapper.TrainingMapper;
import com.spartanbeast.api.training.repository.TrainingRepository;
import com.spartanbeast.api.training.service.TrainingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TrainingServiceImpl implements TrainingService {

	private final TrainingRepository trainingRepository;
	private final TrainingMapper trainingMapper;

	@Override
	public TrainingResponse create(TrainingRequest request) {

		validateSlug(request.slug(), null);

		Training training = trainingMapper.toEntity(request);

		Training savedTraining = trainingRepository.save(training);

		return trainingMapper.toResponse(savedTraining);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TrainingResponse> findAll() {

		return trainingRepository.findAll().stream().map(trainingMapper::toResponse).toList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<TrainingResponse> findAllActive() {

		return trainingRepository.findAllByActiveTrueOrderByDisplayOrderAsc().stream().map(trainingMapper::toResponse)
				.toList();
	}

	@Override
	@Transactional(readOnly = true)
	public TrainingResponse findById(Long id) {

		return trainingMapper.toResponse(findEntityById(id));
	}

	@Override
	@Transactional(readOnly = true)
	public TrainingResponse findBySlug(String slug) {

		Training training = trainingRepository.findBySlug(slug).orElseThrow(() -> new TrainingNotFoundException(slug));

		return trainingMapper.toResponse(training);
	}

	@Override
	public TrainingResponse update(Long id, TrainingRequest request) {

		Training training = findEntityById(id);

		validateSlug(request.slug(), id);

		trainingMapper.updateEntity(training, request);

		Training updatedTraining = trainingRepository.save(training);

		return trainingMapper.toResponse(updatedTraining);
	}

	@Override
	public void delete(Long id) {

		Training training = findEntityById(id);

		trainingRepository.delete(training);
	}

	private Training findEntityById(Long id) {

		return trainingRepository.findById(id).orElseThrow(() -> new TrainingNotFoundException(id));
	}

	private void validateSlug(String slug, Long currentTrainingId) {

		trainingRepository.findBySlug(slug).filter(
				existingTraining -> currentTrainingId == null || !existingTraining.getId().equals(currentTrainingId))
				.ifPresent(existingTraining -> {
					throw new DuplicateSlugException("un entrenamiento", slug);
				});
	}
}
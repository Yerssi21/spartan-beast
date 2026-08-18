package com.spartanbeast.api.schedule.service.impl;

import java.time.DayOfWeek;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spartanbeast.api.common.exception.DuplicateScheduleDayException;
import com.spartanbeast.api.common.exception.ScheduleNotFoundException;
import com.spartanbeast.api.schedule.dto.ScheduleRequest;
import com.spartanbeast.api.schedule.dto.ScheduleResponse;
import com.spartanbeast.api.schedule.entity.Schedule;
import com.spartanbeast.api.schedule.mapper.ScheduleMapper;
import com.spartanbeast.api.schedule.repository.ScheduleRepository;
import com.spartanbeast.api.schedule.service.ScheduleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

	private final ScheduleRepository scheduleRepository;
	private final ScheduleMapper scheduleMapper;

	@Override
	public ScheduleResponse create(ScheduleRequest request) {

		validateDay(request.dayOfWeek(), null);
		validateTimes(request);

		Schedule schedule = scheduleMapper.toEntity(request);

		return scheduleMapper.toResponse(scheduleRepository.save(schedule));
	}

	@Override
	@Transactional(readOnly = true)
	public List<ScheduleResponse> findAll() {

		return scheduleRepository.findAll().stream().map(scheduleMapper::toResponse).toList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<ScheduleResponse> findAllActive() {

		return scheduleRepository.findAllByActiveTrueOrderByDisplayOrderAsc().stream().map(scheduleMapper::toResponse)
				.toList();
	}

	@Override
	@Transactional(readOnly = true)
	public ScheduleResponse findById(Long id) {

		return scheduleMapper.toResponse(findEntityById(id));
	}

	@Override
	public ScheduleResponse update(Long id, ScheduleRequest request) {

		Schedule schedule = findEntityById(id);

		validateDay(request.dayOfWeek(), id);
		validateTimes(request);

		scheduleMapper.updateEntity(schedule, request);

		return scheduleMapper.toResponse(scheduleRepository.save(schedule));
	}

	@Override
	public void delete(Long id) {

		scheduleRepository.delete(findEntityById(id));
	}

	private Schedule findEntityById(Long id) {

		return scheduleRepository.findById(id).orElseThrow(() -> new ScheduleNotFoundException(id));
	}

	private void validateDay(DayOfWeek day, Long currentScheduleId) {

		scheduleRepository.findByDayOfWeek(day)
				.filter(existing -> currentScheduleId == null || !existing.getId().equals(currentScheduleId))
				.ifPresent(existing -> {
					throw new DuplicateScheduleDayException(day);
				});
	}

	private void validateTimes(ScheduleRequest request) {

		if (request.closed()) {
			return;
		}

		if (request.openingTime() == null || request.closingTime() == null) {

			throw new IllegalArgumentException("Un día abierto debe tener hora de apertura y cierre");
		}

		if (!request.closingTime().isAfter(request.openingTime())) {

			throw new IllegalArgumentException("La hora de cierre debe ser posterior a la hora de apertura");
		}
	}
}
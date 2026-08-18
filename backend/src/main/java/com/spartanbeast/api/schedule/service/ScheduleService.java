package com.spartanbeast.api.schedule.service;

import java.util.List;

import com.spartanbeast.api.schedule.dto.ScheduleRequest;
import com.spartanbeast.api.schedule.dto.ScheduleResponse;

public interface ScheduleService {

	ScheduleResponse create(ScheduleRequest request);

	List<ScheduleResponse> findAll();

	List<ScheduleResponse> findAllActive();

	ScheduleResponse findById(Long id);

	ScheduleResponse update(Long id, ScheduleRequest request);

	void delete(Long id);
}
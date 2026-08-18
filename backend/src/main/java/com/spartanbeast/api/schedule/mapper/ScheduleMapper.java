package com.spartanbeast.api.schedule.mapper;

import org.springframework.stereotype.Component;

import com.spartanbeast.api.schedule.dto.ScheduleRequest;
import com.spartanbeast.api.schedule.dto.ScheduleResponse;
import com.spartanbeast.api.schedule.entity.Schedule;

@Component
public class ScheduleMapper {

	public Schedule toEntity(ScheduleRequest request) {

		return Schedule.builder().dayOfWeek(request.dayOfWeek()).openingTime(request.openingTime())
				.closingTime(request.closingTime()).closed(request.closed()).active(request.active())
				.displayOrder(request.displayOrder()).build();
	}

	public ScheduleResponse toResponse(Schedule schedule) {

		return new ScheduleResponse(schedule.getId(), schedule.getDayOfWeek(), schedule.getOpeningTime(),
				schedule.getClosingTime(), schedule.isClosed(), schedule.isActive(), schedule.getDisplayOrder(),
				schedule.getCreatedAt(), schedule.getUpdatedAt());
	}

	public void updateEntity(Schedule schedule, ScheduleRequest request) {

		schedule.setDayOfWeek(request.dayOfWeek());
		schedule.setOpeningTime(request.openingTime());
		schedule.setClosingTime(request.closingTime());
		schedule.setClosed(request.closed());
		schedule.setActive(request.active());
		schedule.setDisplayOrder(request.displayOrder());
	}
}
package com.spartanbeast.api.schedule.repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spartanbeast.api.schedule.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

	Optional<Schedule> findByDayOfWeek(DayOfWeek dayOfWeek);

	List<Schedule> findAllByActiveTrueOrderByDisplayOrderAsc();
}
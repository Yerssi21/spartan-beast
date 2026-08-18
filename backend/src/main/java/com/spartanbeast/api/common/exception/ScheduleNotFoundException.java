package com.spartanbeast.api.common.exception;

public class ScheduleNotFoundException extends RuntimeException {

	public ScheduleNotFoundException(Long id) {
		super("No se encontró el horario con id: " + id);
	}
}
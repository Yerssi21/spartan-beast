package com.spartanbeast.api.common.exception;

import java.time.DayOfWeek;

public class DuplicateScheduleDayException extends RuntimeException {

	public DuplicateScheduleDayException(DayOfWeek day) {
		super("Ya existe un horario configurado para el día: " + day);
	}
}
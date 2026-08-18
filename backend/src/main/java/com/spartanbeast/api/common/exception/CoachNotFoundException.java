package com.spartanbeast.api.common.exception;

public class CoachNotFoundException extends RuntimeException {

	public CoachNotFoundException(Long id) {
		super("No se encontró el entrenador con id: " + id);
	}

	public CoachNotFoundException(String slug) {
		super("No se encontró el entrenador con slug: " + slug);
	}
}
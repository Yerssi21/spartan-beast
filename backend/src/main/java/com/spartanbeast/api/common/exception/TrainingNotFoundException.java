package com.spartanbeast.api.common.exception;

public class TrainingNotFoundException extends RuntimeException {

	public TrainingNotFoundException(Long id) {
		super("No se encontró el entrenamiento con id: " + id);
	}

	public TrainingNotFoundException(String slug) {
		super("No se encontró el entrenamiento con slug: " + slug);
	}
}
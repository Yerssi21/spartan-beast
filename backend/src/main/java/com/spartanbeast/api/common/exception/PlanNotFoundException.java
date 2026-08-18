package com.spartanbeast.api.common.exception;

public class PlanNotFoundException extends RuntimeException {

	public PlanNotFoundException(Long id) {
		super("No se encontró el plan con id: " + id);
	}

	public PlanNotFoundException(String slug) {
		super("No se encontró el plan con slug: " + slug);
	}
}
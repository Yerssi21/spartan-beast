package com.spartanbeast.api.common.exception;

public class GalleryItemNotFoundException extends RuntimeException {

	public GalleryItemNotFoundException(Long id) {
		super("No se encontró el elemento de galería con id: " + id);
	}
}
package com.spartanbeast.api.common.exception;

public class DuplicateSlugException extends RuntimeException {

	public DuplicateSlugException(String resource, String slug) {
		super("Ya existe " + resource + " con el slug: " + slug);
	}
}
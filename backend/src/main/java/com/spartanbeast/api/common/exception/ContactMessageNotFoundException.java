package com.spartanbeast.api.common.exception;

public class ContactMessageNotFoundException extends RuntimeException {

	public ContactMessageNotFoundException(Long id) {
		super("No se encontró el mensaje de contacto con id: " + id);
	}
}
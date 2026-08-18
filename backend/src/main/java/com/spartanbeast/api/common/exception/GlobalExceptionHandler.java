package com.spartanbeast.api.common.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.security.core.AuthenticationException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(PlanNotFoundException.class)
	public ResponseEntity<ApiError> handlePlanNotFound(PlanNotFoundException exception, HttpServletRequest request) {

		ApiError error = new ApiError(Instant.now(), HttpStatus.NOT_FOUND.value(),
				HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage(), request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(DuplicateSlugException.class)
	public ResponseEntity<ApiError> handleDuplicateSlug(DuplicateSlugException exception, HttpServletRequest request) {

		ApiError error = new ApiError(Instant.now(), HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase(),
				exception.getMessage(), request.getRequestURI());

		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException exception,
			HttpServletRequest request) {

		String message = exception.getBindingResult().getFieldErrors().stream().findFirst()
				.map(error -> error.getField() + ": " + error.getDefaultMessage()).orElse("Datos inválidos");

		ApiError error = new ApiError(Instant.now(), HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.getReasonPhrase(), message, request.getRequestURI());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(TrainingNotFoundException.class)
	public ResponseEntity<ApiError> handleTrainingNotFound(TrainingNotFoundException exception,
			HttpServletRequest request) {

		ApiError error = new ApiError(Instant.now(), HttpStatus.NOT_FOUND.value(),
				HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage(), request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(CoachNotFoundException.class)
	public ResponseEntity<ApiError> handleCoachNotFound(CoachNotFoundException exception, HttpServletRequest request) {

		ApiError error = new ApiError(Instant.now(), HttpStatus.NOT_FOUND.value(),
				HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage(), request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(ScheduleNotFoundException.class)
	public ResponseEntity<ApiError> handleScheduleNotFound(ScheduleNotFoundException exception,
			HttpServletRequest request) {

		ApiError error = new ApiError(Instant.now(), HttpStatus.NOT_FOUND.value(),
				HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage(), request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(DuplicateScheduleDayException.class)
	public ResponseEntity<ApiError> handleDuplicateScheduleDay(DuplicateScheduleDayException exception,
			HttpServletRequest request) {

		ApiError error = new ApiError(Instant.now(), HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase(),
				exception.getMessage(), request.getRequestURI());

		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ApiError> handleIllegalArgument(IllegalArgumentException exception,
			HttpServletRequest request) {

		ApiError error = new ApiError(Instant.now(), HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.getReasonPhrase(), exception.getMessage(), request.getRequestURI());

		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(ContactMessageNotFoundException.class)
	public ResponseEntity<ApiError> handleContactNotFound(ContactMessageNotFoundException exception,
			HttpServletRequest request) {

		ApiError error = new ApiError(Instant.now(), HttpStatus.NOT_FOUND.value(),
				HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage(), request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(GalleryItemNotFoundException.class)
	public ResponseEntity<ApiError> handleGalleryItemNotFound(GalleryItemNotFoundException exception,
			HttpServletRequest request) {

		ApiError error = new ApiError(Instant.now(), HttpStatus.NOT_FOUND.value(),
				HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage(), request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ApiError> handleAuthentication(AuthenticationException exception,
			HttpServletRequest request) {

		ApiError error = new ApiError(Instant.now(), HttpStatus.UNAUTHORIZED.value(),
				HttpStatus.UNAUTHORIZED.getReasonPhrase(), "Credenciales inválidas", request.getRequestURI());

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}
}
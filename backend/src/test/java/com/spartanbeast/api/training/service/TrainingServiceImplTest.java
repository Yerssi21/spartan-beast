package com.spartanbeast.api.training.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.spartanbeast.api.common.exception.DuplicateSlugException;
import com.spartanbeast.api.training.dto.TrainingRequest;
import com.spartanbeast.api.training.dto.TrainingResponse;
import com.spartanbeast.api.training.entity.Training;
import com.spartanbeast.api.training.mapper.TrainingMapper;
import com.spartanbeast.api.training.repository.TrainingRepository;
import com.spartanbeast.api.training.service.impl.TrainingServiceImpl;

@ExtendWith(MockitoExtension.class)
class TrainingServiceImplTest {

	@Mock
	private TrainingRepository trainingRepository;

	private TrainingMapper trainingMapper;

	private TrainingServiceImpl trainingService;

	@BeforeEach
	void setUp() {

		trainingMapper = new TrainingMapper();

		trainingService = new TrainingServiceImpl(trainingRepository, trainingMapper);
	}

	@Test
	void shouldCreateTrainingSuccessfully() {

		// GIVEN
		TrainingRequest request = createRequest();

		when(trainingRepository.findBySlug("entrenamiento-funcional")).thenReturn(Optional.empty());

		when(trainingRepository.save(any(Training.class))).thenAnswer(invocation -> {

			Training training = invocation.getArgument(0);

			training.setId(1L);

			return training;
		});

		// WHEN
		TrainingResponse response = trainingService.create(request);

		// THEN
		assertEquals(1L, response.id());

		assertEquals("Entrenamiento Funcional", response.name());

		assertEquals("entrenamiento-funcional", response.slug());

		assertEquals(1, response.displayOrder());

		verify(trainingRepository).save(any(Training.class));
	}

	@Test
	void shouldRejectDuplicatedSlug() {

		// GIVEN
		TrainingRequest request = createRequest();

		Training existing = Training.builder().id(2L).slug("entrenamiento-funcional").build();

		when(trainingRepository.findBySlug("entrenamiento-funcional")).thenReturn(Optional.of(existing));

		// WHEN + THEN
		assertThrows(DuplicateSlugException.class, () -> trainingService.create(request));

		verify(trainingRepository, never()).save(any(Training.class));
	}

	private TrainingRequest createRequest() {

		return new TrainingRequest("Entrenamiento Funcional", "entrenamiento-funcional",
				"Potencia fuerza, resistencia y movilidad.",
				"Entrenamientos orientados al rendimiento físico integral.", "/images/training/functional.webp",
				"dumbbell", true, 1);
	}
}
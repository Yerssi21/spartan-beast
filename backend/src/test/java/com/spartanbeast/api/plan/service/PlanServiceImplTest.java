package com.spartanbeast.api.plan.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.spartanbeast.api.common.exception.DuplicateSlugException;
import com.spartanbeast.api.common.exception.PlanNotFoundException;
import com.spartanbeast.api.plan.dto.PlanRequest;
import com.spartanbeast.api.plan.dto.PlanResponse;
import com.spartanbeast.api.plan.entity.Plan;
import com.spartanbeast.api.plan.entity.PlanDuration;
import com.spartanbeast.api.plan.mapper.PlanMapper;
import com.spartanbeast.api.plan.repository.PlanRepository;
import com.spartanbeast.api.plan.service.impl.PlanServiceImpl;

@ExtendWith(MockitoExtension.class)
class PlanServiceImplTest {

	@Mock
	private PlanRepository planRepository;

	private PlanMapper planMapper;

	private PlanServiceImpl planService;

	@BeforeEach
	void setUp() {

		planMapper = new PlanMapper();

		planService = new PlanServiceImpl(planRepository, planMapper);
	}

	@Test
	void shouldCreatePlanSuccessfully() {

		// GIVEN
		PlanRequest request = createRequest();

		when(planRepository.findBySlug("plan-mensual")).thenReturn(Optional.empty());

		when(planRepository.save(any(Plan.class))).thenAnswer(invocation -> {

			Plan plan = invocation.getArgument(0);

			plan.setId(1L);

			return plan;
		});

		// WHEN
		PlanResponse response = planService.create(request);

		// THEN
		assertEquals(1L, response.id());
		assertEquals("Plan Mensual", response.name());
		assertEquals("plan-mensual", response.slug());
		assertEquals(new BigDecimal("140000"), response.price());

		assertEquals(PlanDuration.MONTHLY, response.duration());

		assertTrue(response.active());
		assertTrue(response.highlighted());

		assertEquals(4, response.features().size());

		verify(planRepository).save(any(Plan.class));
	}

	@Test
	void shouldThrowExceptionWhenSlugAlreadyExists() {

		// GIVEN
		PlanRequest request = createRequest();

		Plan existingPlan = Plan.builder().id(99L).name("Otro plan").slug("plan-mensual").build();

		when(planRepository.findBySlug("plan-mensual")).thenReturn(Optional.of(existingPlan));

		// WHEN + THEN
		assertThrows(DuplicateSlugException.class, () -> planService.create(request));

		verify(planRepository, never()).save(any(Plan.class));
	}

	@Test
	void shouldThrowExceptionWhenPlanDoesNotExist() {

		// GIVEN
		when(planRepository.findById(999L)).thenReturn(Optional.empty());

		// WHEN + THEN
		assertThrows(PlanNotFoundException.class, () -> planService.findById(999L));
	}

	private PlanRequest createRequest() {

		return new PlanRequest("Plan Mensual", "plan-mensual", "Entrena sin límites durante todo el mes",
				new BigDecimal("140000"), PlanDuration.MONTHLY, true, true, 1,
				List.of("Entrenamiento funcional", "Acompañamiento", "Acceso a clases", "Comunidad Spartan"));
	}
}
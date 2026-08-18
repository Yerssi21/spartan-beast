package com.spartanbeast.api.security;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;

import org.springframework.context.annotation.Import;

import org.springframework.http.MediaType;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import org.springframework.test.web.servlet.MockMvc;

import com.spartanbeast.api.common.config.SecurityConfig;

import com.spartanbeast.api.contact.controller.ContactController;
import com.spartanbeast.api.contact.dto.ContactRequest;
import com.spartanbeast.api.contact.dto.ContactResponse;
import com.spartanbeast.api.contact.entity.ContactStatus;
import com.spartanbeast.api.contact.service.ContactService;

import com.spartanbeast.api.plan.controller.AdminPlanController;
import com.spartanbeast.api.plan.controller.PlanController;
import com.spartanbeast.api.plan.service.PlanService;

@WebMvcTest(controllers = { PlanController.class, AdminPlanController.class, ContactController.class })
@Import(SecurityConfig.class)
@TestPropertySource(properties = { "app.security.jwt.secret=MDEyMzQ1Njc4OTAxMjM0NTY3ODkwMTIzNDU2Nzg5MDE=",
		"app.security.jwt.issuer=spartan-beast-api", "app.cors.allowed-origin=http://localhost:4200" })
class SecurityIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private PlanService planService;

	@MockitoBean
	private ContactService contactService;

	@MockitoBean
	private UserDetailsService userDetailsService;

	@Test
	void publicPlansShouldBeAccessibleWithoutAuthentication() throws Exception {

		when(planService.findAllActive()).thenReturn(List.of());

		mockMvc.perform(get("/api/v1/plans")).andExpect(status().isOk()).andExpect(jsonPath("$").isArray());
	}

	@Test
	void adminEndpointShouldReturn401WithoutJwt() throws Exception {

		mockMvc.perform(get("/api/v1/admin/plans")).andExpect(status().isUnauthorized());
	}

	@Test
	void adminEndpointShouldReturn403WithoutAdminRole() throws Exception {

		mockMvc.perform(get("/api/v1/admin/plans").with(jwt().authorities(new SimpleGrantedAuthority("ROLE_USER"))))
				.andExpect(status().isForbidden());
	}

	@Test
	void adminEndpointShouldBeAccessibleWithAdminJwt() throws Exception {

		when(planService.findAll()).thenReturn(List.of());

		mockMvc.perform(get("/api/v1/admin/plans").with(jwt().authorities(new SimpleGrantedAuthority("ROLE_ADMIN"))))
				.andExpect(status().isOk()).andExpect(jsonPath("$").isArray());
	}

	@Test
	void publicContactShouldAllowMessageWithoutAuthentication() throws Exception {

		Instant now = Instant.parse("2026-08-18T12:00:00Z");

		ContactResponse response = new ContactResponse(1L, "Laura Gómez", "laura@example.com", "+57 300 123 4567",
				"Entrenamiento personalizado", "Quiero información sobre precios.", ContactStatus.NEW, now, now);

		when(contactService.create(any(ContactRequest.class))).thenReturn(response);

		String body = """
				{
				  "name": "Laura Gómez",
				  "email": "laura@example.com",
				  "phone": "+57 300 123 4567",
				  "interest": "Entrenamiento personalizado",
				  "message": "Quiero información sobre precios."
				}
				""";

		mockMvc.perform(post("/api/v1/contact").contentType(MediaType.APPLICATION_JSON).content(body))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.status").value("NEW")).andExpect(jsonPath("$.email").value("laura@example.com"));
	}
}
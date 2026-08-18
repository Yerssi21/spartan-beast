package com.spartanbeast.api.contact.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;

import org.springframework.context.annotation.Import;

import org.springframework.http.MediaType;

import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import org.springframework.test.web.servlet.MockMvc;

import com.spartanbeast.api.common.config.SecurityConfig;
import com.spartanbeast.api.contact.dto.ContactRequest;
import com.spartanbeast.api.contact.service.ContactService;

@WebMvcTest(controllers = ContactController.class)
@Import(SecurityConfig.class)
@TestPropertySource(properties = { "app.security.jwt.secret=MDEyMzQ1Njc4OTAxMjM0NTY3ODkwMTIzNDU2Nzg5MDE=",
		"app.security.jwt.issuer=spartan-beast-api", "app.cors.allowed-origin=http://localhost:4200" })
class ContactControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private ContactService contactService;

	@MockitoBean
	private UserDetailsService userDetailsService;

	@Test
	void shouldReturn400WhenEmailIsInvalid() throws Exception {

		String body = """
				{
				  "name": "Laura Gómez",
				  "email": "correo-invalido",
				  "phone": "+57 300 123 4567",
				  "interest": "Entrenamiento personalizado",
				  "message": "Quiero información sobre precios."
				}
				""";

		mockMvc.perform(post("/api/v1/contact").contentType(MediaType.APPLICATION_JSON).content(body))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.status").value(400))
				.andExpect(jsonPath("$.error").value("Bad Request"))
				.andExpect(jsonPath("$.path").value("/api/v1/contact"));

		verify(contactService, never()).create(any(ContactRequest.class));
	}

	@Test
	void shouldReturn400WhenNameIsBlank() throws Exception {

		String body = """
				{
				  "name": "",
				  "email": "laura@example.com",
				  "phone": "+57 300 123 4567",
				  "interest": "Entrenamiento personalizado",
				  "message": "Quiero información sobre precios."
				}
				""";

		mockMvc.perform(post("/api/v1/contact").contentType(MediaType.APPLICATION_JSON).content(body))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.status").value(400));

		verify(contactService, never()).create(any(ContactRequest.class));
	}

	@Test
	void shouldReturn400WhenMessageIsBlank() throws Exception {

		String body = """
				{
				  "name": "Laura Gómez",
				  "email": "laura@example.com",
				  "phone": "+57 300 123 4567",
				  "interest": "Entrenamiento personalizado",
				  "message": ""
				}
				""";

		mockMvc.perform(post("/api/v1/contact").contentType(MediaType.APPLICATION_JSON).content(body))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.status").value(400));

		verify(contactService, never()).create(any(ContactRequest.class));
	}
}
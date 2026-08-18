package com.spartanbeast.api.auth.bootstrap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.spartanbeast.api.auth.entity.AppUser;
import com.spartanbeast.api.auth.entity.UserRole;
import com.spartanbeast.api.auth.repository.AppUserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class AdminInitializer implements ApplicationRunner {

	private final AppUserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Value("${app.bootstrap.admin.email:}")
	private String adminEmail;

	@Value("${app.bootstrap.admin.password:}")
	private String adminPassword;

	@Override
	public void run(ApplicationArguments args) {

		if (adminEmail.isBlank() || adminPassword.isBlank()) {

			log.warn("ADMIN_EMAIL o ADMIN_PASSWORD no configurados. " + "No se creará administrador inicial.");

			return;
		}

		String normalizedEmail = adminEmail.trim().toLowerCase();

		if (userRepository.existsByEmailIgnoreCase(normalizedEmail)) {

			return;
		}

		AppUser admin = AppUser.builder().email(normalizedEmail).passwordHash(passwordEncoder.encode(adminPassword))
				.role(UserRole.ADMIN).active(true).build();

		userRepository.save(admin);

		log.info("Administrador inicial creado correctamente.");
	}
}
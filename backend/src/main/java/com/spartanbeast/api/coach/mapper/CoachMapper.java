package com.spartanbeast.api.coach.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.spartanbeast.api.coach.dto.CoachRequest;
import com.spartanbeast.api.coach.dto.CoachResponse;
import com.spartanbeast.api.coach.entity.Coach;
import com.spartanbeast.api.coach.entity.CoachSpecialty;

@Component
public class CoachMapper {

	public Coach toEntity(CoachRequest request) {

		Coach coach = Coach.builder().name(request.name()).slug(request.slug()).role(request.role()).bio(request.bio())
				.imageUrl(request.imageUrl()).instagramUrl(request.instagramUrl()).active(request.active())
				.displayOrder(request.displayOrder()).build();

		coach.setSpecialties(createSpecialties(request.specialties(), coach));

		return coach;
	}

	public CoachResponse toResponse(Coach coach) {

		List<String> specialties = coach.getSpecialties().stream().map(CoachSpecialty::getName).toList();

		return new CoachResponse(coach.getId(), coach.getName(), coach.getSlug(), coach.getRole(), coach.getBio(),
				coach.getImageUrl(), coach.getInstagramUrl(), coach.isActive(), coach.getDisplayOrder(), specialties,
				coach.getCreatedAt(), coach.getUpdatedAt());
	}

	public void updateEntity(Coach coach, CoachRequest request) {

		coach.setName(request.name());
		coach.setSlug(request.slug());
		coach.setRole(request.role());
		coach.setBio(request.bio());
		coach.setImageUrl(request.imageUrl());
		coach.setInstagramUrl(request.instagramUrl());
		coach.setActive(request.active());
		coach.setDisplayOrder(request.displayOrder());

		coach.getSpecialties().clear();

		coach.getSpecialties().addAll(createSpecialties(request.specialties(), coach));
	}

	private List<CoachSpecialty> createSpecialties(List<String> specialtyNames, Coach coach) {

		if (specialtyNames == null) {
			return new ArrayList<>();
		}

		List<CoachSpecialty> specialties = new ArrayList<>();

		for (int i = 0; i < specialtyNames.size(); i++) {

			CoachSpecialty specialty = CoachSpecialty.builder().coach(coach).name(specialtyNames.get(i)).displayOrder(i)
					.build();

			specialties.add(specialty);
		}

		return specialties;
	}
}
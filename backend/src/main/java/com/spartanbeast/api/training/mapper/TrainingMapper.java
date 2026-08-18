package com.spartanbeast.api.training.mapper;

import org.springframework.stereotype.Component;

import com.spartanbeast.api.training.dto.TrainingRequest;
import com.spartanbeast.api.training.dto.TrainingResponse;
import com.spartanbeast.api.training.entity.Training;

@Component
public class TrainingMapper {

	public Training toEntity(TrainingRequest request) {

		return Training.builder().name(request.name()).slug(request.slug()).shortDescription(request.shortDescription())
				.description(request.description()).imageUrl(request.imageUrl()).icon(request.icon())
				.active(request.active()).displayOrder(request.displayOrder()).build();
	}

	public TrainingResponse toResponse(Training training) {

		return new TrainingResponse(training.getId(), training.getName(), training.getSlug(),
				training.getShortDescription(), training.getDescription(), training.getImageUrl(), training.getIcon(),
				training.isActive(), training.getDisplayOrder(), training.getCreatedAt(), training.getUpdatedAt());
	}

	public void updateEntity(Training training, TrainingRequest request) {

		training.setName(request.name());
		training.setSlug(request.slug());
		training.setShortDescription(request.shortDescription());
		training.setDescription(request.description());
		training.setImageUrl(request.imageUrl());
		training.setIcon(request.icon());
		training.setActive(request.active());
		training.setDisplayOrder(request.displayOrder());
	}
}
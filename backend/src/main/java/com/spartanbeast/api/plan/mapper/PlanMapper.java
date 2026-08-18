package com.spartanbeast.api.plan.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.spartanbeast.api.plan.dto.PlanRequest;
import com.spartanbeast.api.plan.dto.PlanResponse;
import com.spartanbeast.api.plan.entity.Plan;
import com.spartanbeast.api.plan.entity.PlanFeature;

@Component
public class PlanMapper {

	public Plan toEntity(PlanRequest request) {

		Plan plan = Plan.builder().name(request.name()).slug(request.slug()).description(request.description())
				.price(request.price()).duration(request.duration()).highlighted(request.highlighted())
				.active(request.active()).displayOrder(request.displayOrder()).build();

		List<PlanFeature> features = createFeatures(request.features(), plan);

		plan.setFeatures(features);

		return plan;
	}

	public PlanResponse toResponse(Plan plan) {

		List<String> features = plan.getFeatures().stream().map(PlanFeature::getDescription).toList();

		return new PlanResponse(plan.getId(), plan.getName(), plan.getSlug(), plan.getDescription(), plan.getPrice(),
				plan.getDuration(), plan.isHighlighted(), plan.isActive(), plan.getDisplayOrder(), features,
				plan.getCreatedAt(), plan.getUpdatedAt());
	}

	public void updateEntity(Plan plan, PlanRequest request) {

		plan.setName(request.name());
		plan.setSlug(request.slug());
		plan.setDescription(request.description());
		plan.setPrice(request.price());
		plan.setDuration(request.duration());
		plan.setHighlighted(request.highlighted());
		plan.setActive(request.active());
		plan.setDisplayOrder(request.displayOrder());

		plan.getFeatures().clear();

		plan.getFeatures().addAll(createFeatures(request.features(), plan));
	}

	private List<PlanFeature> createFeatures(List<String> featureDescriptions, Plan plan) {

		if (featureDescriptions == null) {
			return new ArrayList<>();
		}

		List<PlanFeature> features = new ArrayList<>();

		for (int i = 0; i < featureDescriptions.size(); i++) {

			PlanFeature feature = PlanFeature.builder().plan(plan).description(featureDescriptions.get(i))
					.displayOrder(i).build();

			features.add(feature);
		}

		return features;
	}
}
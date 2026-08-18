package com.spartanbeast.api.plan.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spartanbeast.api.plan.entity.Plan;

public interface PlanRepository extends JpaRepository<Plan, Long> {

	Optional<Plan> findBySlug(String slug);

	boolean existsBySlug(String slug);

	List<Plan> findAllByActiveTrueOrderByDisplayOrderAsc();

}
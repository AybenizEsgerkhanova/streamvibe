package com.aybeniz.streamvibe.repository;

import com.aybeniz.streamvibe.entity.PricingPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PricingPlanRepository extends JpaRepository<PricingPlan, Integer> {
}
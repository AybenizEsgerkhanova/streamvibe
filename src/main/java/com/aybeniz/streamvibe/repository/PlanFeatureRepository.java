package com.aybeniz.streamvibe.repository;

import com.aybeniz.streamvibe.entity.PlanFeature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanFeatureRepository extends JpaRepository<PlanFeature, Integer> {

    List<PlanFeature> findByPlan_IdOrderByOrderNumberAsc(Integer planId);
}
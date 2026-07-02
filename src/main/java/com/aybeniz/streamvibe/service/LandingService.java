package com.aybeniz.streamvibe.service;

import com.aybeniz.streamvibe.dto.response.*;
import com.aybeniz.streamvibe.entity.Genre;
import com.aybeniz.streamvibe.mapper.LandingMapper;
import com.aybeniz.streamvibe.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LandingService {

    private final GenreRepository genreRepository;
    private final FaqRepository faqRepository;
    private final PricingPlanRepository pricingPlanRepository;
    private final DeviceRepository deviceRepository;
    private final LandingMapper landingMapper;
    private final PlanFeatureRepository planFeatureRepository;


    public List<GenreResponse> getGenres(String type) {
        List<Genre> genres;

        if (type == null || type.isBlank() || type.equalsIgnoreCase("all")) {
            genres = genreRepository.findAll();
        } else {
            genres = genreRepository.findByType(type.toLowerCase());
        }

        return genres.stream()
                .map(landingMapper::toGenreResponse)
                .toList();
    }

    public List<FaqResponse> getFaqs() {
        return faqRepository.findAllByOrderByOrderNumberAsc()
                .stream()
                .map(landingMapper::toFaqResponse)
                .toList();
    }

    public List<PlanResponse> getPlans(String billing) {

        boolean yearly = billing != null
                && billing.equalsIgnoreCase("yearly");

        return pricingPlanRepository.findAll()
                .stream()
                .map(plan -> {

                    List<PlanFeatureResponse> features =
                            planFeatureRepository
                                    .findByPlan_IdOrderByOrderNumberAsc(plan.getId())
                                    .stream()
                                    .map(feature -> new PlanFeatureResponse(
                                            feature.getFeatureName(),
                                            feature.getFeatureValue(),
                                            feature.getOrderNumber()
                                    ))
                                    .toList();

                    return landingMapper.toPlanResponse(
                            plan,
                            yearly,
                            features
                    );
                })
                .toList();
    }
    public List<DeviceResponse> getDevices() {
        return deviceRepository.findAll()
                .stream()
                .map(landingMapper::toDeviceResponse)
                .toList();
    }
}
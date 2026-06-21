package com.aybeniz.streamvibe.service;

import com.aybeniz.streamvibe.dto.response.DeviceResponse;
import com.aybeniz.streamvibe.dto.response.FaqResponse;
import com.aybeniz.streamvibe.dto.response.GenreResponse;
import com.aybeniz.streamvibe.dto.response.PlanResponse;
import com.aybeniz.streamvibe.entity.Genre;
import com.aybeniz.streamvibe.mapper.LandingMapper;
import com.aybeniz.streamvibe.repository.DeviceRepository;
import com.aybeniz.streamvibe.repository.FaqRepository;
import com.aybeniz.streamvibe.repository.GenreRepository;
import com.aybeniz.streamvibe.repository.PricingPlanRepository;
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
                .map(plan -> landingMapper.toPlanResponse(plan, yearly))
                .toList();
    }

    public List<DeviceResponse> getDevices() {
        return deviceRepository.findAll()
                .stream()
                .map(landingMapper::toDeviceResponse)
                .toList();
    }
}
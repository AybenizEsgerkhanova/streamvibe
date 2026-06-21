package com.aybeniz.streamvibe.controller;

import com.aybeniz.streamvibe.dto.response.ApiResponse;
import com.aybeniz.streamvibe.dto.response.DeviceResponse;
import com.aybeniz.streamvibe.dto.response.FaqResponse;
import com.aybeniz.streamvibe.dto.response.GenreResponse;
import com.aybeniz.streamvibe.dto.response.PlanResponse;
import com.aybeniz.streamvibe.service.LandingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Landing Page API", description = "Endpoints for StreamVibe landing page sections")
public class LandingController {

    private final LandingService landingService;

    @GetMapping("/genres")
    @Operation(summary = "Get genres", description = "Returns genres. Supports type=all, movie, or show.")
    public ApiResponse<List<GenreResponse>> getGenres(@RequestParam(defaultValue = "all") String type) {
        return ApiResponse.ok(landingService.getGenres(type));
    }

    @GetMapping("/faqs")
    @Operation(summary = "Get FAQs", description = "Returns FAQ items ordered by order number.")
    public ApiResponse<List<FaqResponse>> getFaqs() {
        return ApiResponse.ok(landingService.getFaqs());
    }

    @GetMapping("/plans")
    @Operation(summary = "Get pricing plans", description = "Returns plans. Supports billing=monthly or yearly.")
    public ApiResponse<List<PlanResponse>> getPlans(@RequestParam(defaultValue = "monthly") String billing) {
        return ApiResponse.ok(landingService.getPlans(billing));
    }

    @GetMapping("/devices")
    @Operation(summary = "Get supported devices", description = "Returns supported devices.")
    public ApiResponse<List<DeviceResponse>> getDevices() {
        return ApiResponse.ok(landingService.getDevices());
    }
}
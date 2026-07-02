package com.aybeniz.streamvibe.controller;

import com.aybeniz.streamvibe.dto.request.SubscribeRequest;
import com.aybeniz.streamvibe.dto.response.*;
import com.aybeniz.streamvibe.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
@Tag(name = "Subscriptions API", description = "Subscription endpoints")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping("/plans")
    @Operation(summary = "Get pricing plans with features")
    public ResponseEntity<ApiResponse<List<PlanResponse>>> plans(
            @RequestParam(value = "billing", required = false) String billing
    ) {
        List<PlanResponse> response = subscriptionService.getPlans(billing);

        return ResponseEntity.ok(
                ApiResponse.ok(response)
        );
    }

    @PostMapping("/subscribe")
    @Operation(summary = "Subscribe to plan")
    public ResponseEntity<ApiResponse<SubscriptionResponse>> subscribe(
            HttpServletRequest request,
            @RequestBody SubscribeRequest subscribeRequest
    ) {
        Integer userId = (Integer) request.getAttribute("userId");

        SubscriptionResponse response = subscriptionService.subscribe(userId, subscribeRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Subscription activated successfully", response));
    }

    @GetMapping("/my")
    @Operation(summary = "Get my active subscription")
    public ResponseEntity<ApiResponse<MySubscriptionResponse>> my(
            HttpServletRequest request
    ) {
        Integer userId = (Integer) request.getAttribute("userId");

        MySubscriptionResponse response = subscriptionService.getMySubscription(userId);

        return ResponseEntity.ok(
                ApiResponse.ok(response)
        );
    }

    @DeleteMapping("/cancel")
    @Operation(summary = "Cancel my subscription")
    public ResponseEntity<ApiResponse<Object>> cancel(
            HttpServletRequest request
    ) {
        Integer userId = (Integer) request.getAttribute("userId");

        subscriptionService.cancelSubscription(userId);

        return ResponseEntity.ok(
                ApiResponse.ok("Subscription cancelled successfully", null)
        );
    }
}
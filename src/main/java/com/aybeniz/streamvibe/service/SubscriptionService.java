package com.aybeniz.streamvibe.service;

import com.aybeniz.streamvibe.dto.request.SubscribeRequest;
import com.aybeniz.streamvibe.dto.response.*;
import com.aybeniz.streamvibe.entity.*;
import com.aybeniz.streamvibe.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final PricingPlanRepository pricingPlanRepository;
    private final PlanFeatureRepository planFeatureRepository;
    private final UserRepository userRepository;
    private final UserSubscriptionRepository userSubscriptionRepository;
    private final SubscriptionHistoryRepository subscriptionHistoryRepository;

    public List<PlanResponse> getPlans(String billing) {
        String billingCycle = billing == null || billing.isBlank()
                ? "monthly"
                : billing.trim().toLowerCase();

        if (!billingCycle.equals("monthly") && !billingCycle.equals("yearly")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid billing cycle");
        }

        return pricingPlanRepository.findAll()
                .stream()
                .map(plan -> mapPlan(plan, billingCycle))
                .toList();
    }

    @Transactional
    public SubscriptionResponse subscribe(Integer userId, SubscribeRequest request) {
        if (request == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body is required");
        }

        if (request.getPlanId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Plan id is required");
        }

        String billingCycle = request.getBillingCycle() == null
                ? ""
                : request.getBillingCycle().trim().toLowerCase();

        if (!billingCycle.equals("monthly") && !billingCycle.equals("yearly")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid billing cycle");
        }

        PricingPlan plan = pricingPlanRepository.findById(request.getPlanId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plan not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token"));

        boolean hasActiveSubscription = userSubscriptionRepository.existsByUser_IdAndStatusIn(
                userId,
                List.of("active", "trial")
        );

        if (hasActiveSubscription) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "You already have an active subscription"
            );
        }

        boolean isTrial = Boolean.TRUE.equals(request.getIsTrial());

        LocalDateTime startedAt = LocalDateTime.now();
        LocalDateTime expiresAt;

        if (isTrial) {
            expiresAt = startedAt.plusDays(7);
        } else if (billingCycle.equals("monthly")) {
            expiresAt = startedAt.plusDays(30);
        } else {
            expiresAt = startedAt.plusDays(365);
        }

        UserSubscription subscription = new UserSubscription();
        subscription.setUser(user);
        subscription.setPlan(plan);
        subscription.setBillingCycle(billingCycle);
        subscription.setTrial(isTrial);
        subscription.setStatus(isTrial ? "trial" : "active");
        subscription.setStartedAt(startedAt);
        subscription.setExpiresAt(expiresAt);

        UserSubscription saved = userSubscriptionRepository.save(subscription);

        return new SubscriptionResponse(
                saved.getId(),
                saved.getPlan().getId(),
                saved.getBillingCycle(),
                saved.getTrial(),
                saved.getStatus(),
                saved.getStartedAt(),
                saved.getExpiresAt()
        );
    }

    public MySubscriptionResponse getMySubscription(Integer userId) {
        UserSubscription subscription = userSubscriptionRepository
                .findByUser_IdAndStatusIn(userId, List.of("active", "trial"))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No active subscription"
                ));

        PricingPlan plan = subscription.getPlan();

        return new MySubscriptionResponse(
                subscription.getId(),
                subscription.getBillingCycle(),
                subscription.getTrial(),
                subscription.getStatus(),
                subscription.getStartedAt(),
                subscription.getExpiresAt(),
                new SimplePlanResponse(
                        plan.getId(),
                        plan.getName(),
                        plan.getDescription(),
                        plan.getPrice(subscription.getBillingCycle())
                )
        );
    }

    @Transactional
    public void cancelSubscription(Integer userId) {
        UserSubscription subscription = userSubscriptionRepository
                .findByUser_IdAndStatusIn(userId, List.of("active", "trial"))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No active subscription"
                ));

        SubscriptionHistory history = new SubscriptionHistory();
        history.setUser(subscription.getUser());
        history.setPlan(subscription.getPlan());
        history.setBillingCycle(subscription.getBillingCycle());
        history.setTrial(subscription.getTrial());
        history.setStatus("cancelled");
        history.setStartedAt(subscription.getStartedAt());
        history.setExpiredAt(LocalDateTime.now());

        subscriptionHistoryRepository.save(history);

        userSubscriptionRepository.delete(subscription);
    }

    private PlanResponse mapPlan(PricingPlan plan, String billingCycle) {
        List<PlanFeatureResponse> features = planFeatureRepository
                .findByPlan_IdOrderByOrderNumberAsc(plan.getId())
                .stream()
                .map(feature -> new PlanFeatureResponse(
                        feature.getFeatureName(),
                        feature.getFeatureValue(),
                        feature.getOrderNumber()
                ))
                .toList();

        return new PlanResponse(
                plan.getId(),
                plan.getName(),
                plan.getDescription(),
                plan.getPrice(billingCycle),                plan.getIsPopular(),
                billingCycle,
                features
        );
    }
}
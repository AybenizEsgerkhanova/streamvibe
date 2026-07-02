package com.aybeniz.streamvibe.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class SubscriptionResponse {

    private Integer id;

    @JsonProperty("plan_id")
    private Integer planId;

    @JsonProperty("billing_cycle")
    private String billingCycle;

    @JsonProperty("is_trial")
    private Boolean trial;

    private String status;

    @JsonProperty("started_at")
    private LocalDateTime startedAt;

    @JsonProperty("expires_at")
    private LocalDateTime expiresAt;
}
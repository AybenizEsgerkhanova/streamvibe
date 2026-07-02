package com.aybeniz.streamvibe.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscribeRequest {

    private Integer planId;
    private String billingCycle;
    private Boolean isTrial;
}
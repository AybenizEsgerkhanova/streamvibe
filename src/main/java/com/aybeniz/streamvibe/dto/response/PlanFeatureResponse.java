package com.aybeniz.streamvibe.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PlanFeatureResponse {

    @JsonProperty("feature_name")
    private String featureName;

    @JsonProperty("feature_value")
    private String featureValue;

    @JsonProperty("order_number")
    private Integer orderNumber;
}
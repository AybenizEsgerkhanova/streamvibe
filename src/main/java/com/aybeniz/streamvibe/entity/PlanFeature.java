package com.aybeniz.streamvibe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "plan_features")
public class PlanFeature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "plan_id", nullable = false)
    private PricingPlan plan;

    @Column(name = "feature_name", nullable = false, length = 100)
    private String featureName;

    @Column(name = "feature_value", nullable = false, length = 255)
    private String featureValue;

    @Column(name = "order_number", nullable = false)
    private Integer orderNumber;
}
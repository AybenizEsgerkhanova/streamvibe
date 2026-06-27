package com.aybeniz.streamvibe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "content_id")
    private Integer contentId;

    @Column(name = "reviewer_name")
    private String reviewerName;

    @Column(name = "reviewer_location")
    private String reviewerLocation;

    private BigDecimal rating;

    @Column(name = "review_text", columnDefinition = "TEXT")
    private String reviewText;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
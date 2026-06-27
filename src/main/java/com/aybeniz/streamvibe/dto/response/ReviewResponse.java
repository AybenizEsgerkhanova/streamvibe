package com.aybeniz.streamvibe.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewResponse {

    private Integer id;
    private String reviewerName;
    private String reviewerLocation;
    private BigDecimal rating;
    private String reviewText;
    private String createdAt;
}

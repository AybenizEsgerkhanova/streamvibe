package com.aybeniz.streamvibe.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class SimplePlanResponse {

    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
}
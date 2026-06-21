package com.aybeniz.streamvibe.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContentListResponse {

    private Integer id;
    private String title;
    private String posterUrl;
    private BigDecimal imdbRating;
    private BigDecimal streamvibeRating;
    private Integer releaseYear;
    private String type;


}
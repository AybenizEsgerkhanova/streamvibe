package com.aybeniz.streamvibe.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContentDetailResponse {

    private Integer id;
    private String title;
    private String description;
    private String posterUrl;
    private String backgroundUrl;
    private String trailerUrl;
    private String type;
    private Integer releaseYear;
    private BigDecimal imdbRating;
    private BigDecimal streamvibeRating;

    private List<GenreItemResponse> genres;
    private List<String> languages;

    private List<PersonResponse> cast;
    private List<PersonResponse> directors;
    private List<PersonResponse> music;
}
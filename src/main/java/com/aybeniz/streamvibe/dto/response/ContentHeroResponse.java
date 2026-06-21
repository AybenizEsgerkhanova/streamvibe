package com.aybeniz.streamvibe.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ContentHeroResponse {

    private Integer id;
    private String title;
    private String description;
    private String backgroundUrl;
    private String trailerUrl;
    private String type;
}
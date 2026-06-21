package com.aybeniz.streamvibe.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GenreResponse {
    private Integer id;
    private String name;
    private String slug;
    private String type;
    private String coverImage1;
    private String coverImage2;
    private String coverImage3;
    private String coverImage4;
}
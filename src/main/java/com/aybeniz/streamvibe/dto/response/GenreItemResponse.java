package com.aybeniz.streamvibe.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenreItemResponse {

    private Integer id;
    private String name;
    private String slug;
}
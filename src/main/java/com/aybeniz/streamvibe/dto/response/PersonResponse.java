package com.aybeniz.streamvibe.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonResponse {

    private Integer id;
    private String name;
    private String avatarUrl;
    private String nationality;
    private String characterName;
}
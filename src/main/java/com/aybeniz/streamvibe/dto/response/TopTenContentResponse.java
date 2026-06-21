package com.aybeniz.streamvibe.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TopTenContentResponse {

    private Integer id;
    private String title;
    private String posterUrl;
    private Integer topTenRank;
}
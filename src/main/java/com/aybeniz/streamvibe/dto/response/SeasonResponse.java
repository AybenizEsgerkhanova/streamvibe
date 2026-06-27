package com.aybeniz.streamvibe.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeasonResponse {

    private Integer id;
    private Integer seasonNumber;
    private Integer episodeCount;
    private String title;
    private List<EpisodeResponse> episodes;
}
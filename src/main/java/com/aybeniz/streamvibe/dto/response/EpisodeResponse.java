package com.aybeniz.streamvibe.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EpisodeResponse {

    private Integer id;
    private Integer episodeNumber;
    private String title;
    private String description;
    private String thumbnailUrl;
    private Integer durationMinutes;
}
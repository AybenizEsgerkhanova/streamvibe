package com.aybeniz.streamvibe.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ContactResponse {

    private Integer id;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;
}
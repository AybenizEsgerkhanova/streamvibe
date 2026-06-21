package com.aybeniz.streamvibe.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FaqResponse {
    private Integer id;
    private String question;
    private String answer;
    private Integer orderNumber;
}
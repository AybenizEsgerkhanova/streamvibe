package com.aybeniz.streamvibe.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeviceResponse {
    private Integer id;
    private String name;
    private String description;
    private String iconName;
}
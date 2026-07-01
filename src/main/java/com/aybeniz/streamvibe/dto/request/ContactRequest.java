package com.aybeniz.streamvibe.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneCountryCode;
    private String phoneNumber;
    private String message;
}
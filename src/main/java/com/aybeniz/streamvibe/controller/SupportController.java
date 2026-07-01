package com.aybeniz.streamvibe.controller;

import com.aybeniz.streamvibe.dto.request.ContactRequest;
import com.aybeniz.streamvibe.dto.response.ApiResponse;
import com.aybeniz.streamvibe.dto.response.ContactResponse;
import com.aybeniz.streamvibe.dto.response.FaqResponse;
import com.aybeniz.streamvibe.service.SupportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/support")
@RequiredArgsConstructor
@Tag(name = "Support API", description = "Support page endpoints")
public class SupportController {

    private final SupportService supportService;

    @PostMapping("/contact")
    @Operation(summary = "Send contact message")
    public ResponseEntity<ApiResponse<ContactResponse>> contact(
            @RequestBody ContactRequest request
    ) {
        ContactResponse response = supportService.createContactMessage(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Your message has been sent successfully", response));
    }

    @GetMapping("/faqs")
    public ResponseEntity<ApiResponse<List<FaqResponse>>> faqs() {
        List<FaqResponse> response = supportService.getFaqs();

        return ResponseEntity.ok(
                ApiResponse.ok(response)
        );
    }
}
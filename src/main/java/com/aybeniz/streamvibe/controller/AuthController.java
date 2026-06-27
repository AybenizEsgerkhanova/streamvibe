package com.aybeniz.streamvibe.controller;

import com.aybeniz.streamvibe.dto.request.LoginRequest;
import com.aybeniz.streamvibe.dto.request.RegisterRequest;
import com.aybeniz.streamvibe.dto.response.ApiResponse;
import com.aybeniz.streamvibe.dto.response.AuthResponse;
import com.aybeniz.streamvibe.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Auth API", description = "Register and Login endpoints")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "Register user")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Registration successful", response));
    }

    @PostMapping("/login")
    @Operation(summary = "Login user")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);

        return ResponseEntity
                .ok(ApiResponse.ok("Login successful", response));
    }
}
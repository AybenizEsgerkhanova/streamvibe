package com.aybeniz.streamvibe.service;

import com.aybeniz.streamvibe.dto.request.LoginRequest;
import com.aybeniz.streamvibe.dto.request.RegisterRequest;
import com.aybeniz.streamvibe.dto.response.AuthResponse;
import com.aybeniz.streamvibe.dto.response.UserResponse;
import com.aybeniz.streamvibe.entity.RefreshToken;
import com.aybeniz.streamvibe.entity.User;
import com.aybeniz.streamvibe.repository.RefreshTokenRepository;
import com.aybeniz.streamvibe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HexFormat;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Value("${REFRESH_TOKEN_EXPIRES_DAYS:7}")
    private Long refreshTokenExpiresDays;

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        validateRegisterRequest(request);

        String username = request.getUsername().trim();
        String email = request.getEmail().trim().toLowerCase();
        String password = request.getPassword();

        if (userRepository.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use");
        }

        if (userRepository.existsByUsername(username)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already taken");
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPasswordHash(passwordEncoder.encode(password));
        user.setActive(true);

        User savedUser = userRepository.save(user);

        String refreshToken = createRefreshToken(savedUser);

        return buildAuthResponse(savedUser, refreshToken);
    }

    @Transactional
    public AuthResponse login(LoginRequest request) {
        validateLoginRequest(request);

        String email = request.getEmail().trim().toLowerCase();
        String password = request.getPassword();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> unauthorized());

        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw unauthorized();
        }

        if (Boolean.FALSE.equals(user.getActive())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Account is deactivated");
        }

        refreshTokenRepository.deleteByUser_Id(user.getId());

        String refreshToken = createRefreshToken(user);

        return buildAuthResponse(user, refreshToken);
    }

    private void validateRegisterRequest(RegisterRequest request) {
        if (request == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body is required");
        }

        String username = safe(request.getUsername()).trim();
        String email = safe(request.getEmail()).trim();
        String password = safe(request.getPassword());

        if (username.isBlank() || username.length() < 3 || username.length() > 20) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username must be between 3 and 20 characters");
        }

        if (email.isBlank() || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid email format");
        }

        if (password.length() < 8) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password must be at least 8 characters");
        }
    }

    private void validateLoginRequest(LoginRequest request) {
        if (request == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body is required");
        }

        if (safe(request.getEmail()).trim().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is required");
        }

        if (safe(request.getPassword()).isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is required");
        }
    }

    private ResponseStatusException unauthorized() {
        return new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
    }

    private String createRefreshToken(User user) {
        String token = generateRandomToken();

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setToken(token);
        refreshToken.setExpiresAt(LocalDateTime.now().plusDays(refreshTokenExpiresDays));

        refreshTokenRepository.save(refreshToken);

        return token;
    }

    private String generateRandomToken() {
        byte[] bytes = new byte[64];
        new SecureRandom().nextBytes(bytes);
        return HexFormat.of().formatHex(bytes);
    }

    private AuthResponse buildAuthResponse(User user, String refreshToken) {
        UserResponse userResponse = new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt()
        );

        String accessToken = jwtService.generateAccessToken(user);

        return new AuthResponse(
                userResponse,
                accessToken,
                refreshToken,
                jwtService.getAccessTokenExpiresInSeconds()
        );
    }

    private String safe(String value) {
        return value == null ? "" : value;
    }
}
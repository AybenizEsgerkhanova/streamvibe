package com.aybeniz.streamvibe.service;

import com.aybeniz.streamvibe.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${JWT_SECRET}")
    private String jwtSecret;

    @Value("${JWT_EXPIRES_IN:15m}")
    private String jwtExpiresIn;

    public String generateAccessToken(User user) {
        long expiresInSeconds = getAccessTokenExpiresInSeconds();

        Date now = new Date();
        Date expiration = new Date(now.getTime() + expiresInSeconds * 1000);

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("email", user.getEmail());
        claims.put("username", user.getUsername());

        return Jwts.builder()
                .claims(claims)
                .subject(user.getEmail())
                .issuedAt(now)
                .expiration(expiration)
                .signWith(getSigningKey())
                .compact();
    }

    public long getAccessTokenExpiresInSeconds() {
        return parseDurationToSeconds(jwtExpiresIn);
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    private long parseDurationToSeconds(String value) {
        String normalized = value.trim().toLowerCase();

        if (normalized.matches("\\d+")) {
            return Long.parseLong(normalized);
        }

        long amount = Long.parseLong(normalized.substring(0, normalized.length() - 1));
        char unit = normalized.charAt(normalized.length() - 1);

        return switch (unit) {
            case 's' -> amount;
            case 'm' -> amount * 60;
            case 'h' -> amount * 60 * 60;
            case 'd' -> amount * 24 * 60 * 60;
            default -> 900;
        };
    }
    public Integer getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.get("userId", Integer.class);
    }

    public boolean isTokenValid(String token) {
        try {
            getUserIdFromToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
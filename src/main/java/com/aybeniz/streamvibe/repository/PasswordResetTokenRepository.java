package com.aybeniz.streamvibe.repository;

import com.aybeniz.streamvibe.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {
}
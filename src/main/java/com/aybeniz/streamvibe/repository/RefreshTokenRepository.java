package com.aybeniz.streamvibe.repository;

import com.aybeniz.streamvibe.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {

    @Transactional
    void deleteByUser_Id(Integer userId);
}
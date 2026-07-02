package com.aybeniz.streamvibe.repository;

import com.aybeniz.streamvibe.entity.UserSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, Integer> {

    boolean existsByUser_IdAndStatusIn(Integer userId, java.util.List<String> statuses);

    Optional<UserSubscription> findByUser_IdAndStatusIn(Integer userId, java.util.List<String> statuses);
}
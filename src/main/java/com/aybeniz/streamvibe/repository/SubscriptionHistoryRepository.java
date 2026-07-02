package com.aybeniz.streamvibe.repository;

import com.aybeniz.streamvibe.entity.SubscriptionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionHistoryRepository extends JpaRepository<SubscriptionHistory, Integer> {
}
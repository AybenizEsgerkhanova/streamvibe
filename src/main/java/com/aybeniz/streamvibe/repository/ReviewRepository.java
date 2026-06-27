package com.aybeniz.streamvibe.repository;

import com.aybeniz.streamvibe.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findByContentIdOrderByCreatedAtDesc(Integer contentId);
}
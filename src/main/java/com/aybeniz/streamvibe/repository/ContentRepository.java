package com.aybeniz.streamvibe.repository;

import com.aybeniz.streamvibe.entity.Content;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Integer> {

    List<Content> findByIsFeaturedTrue();

    @Query(value = """
            SELECT *
            FROM content
            WHERE type = CAST(:type AS content_type)
              AND top_ten_rank IS NOT NULL
            ORDER BY top_ten_rank ASC
            """, nativeQuery = true)
    List<Content> findTopTenByType(@Param("type") String type);

    @Query(value = """
            SELECT *
            FROM content
            WHERE type = CAST(:type AS content_type)
              AND (
                    (:filter = 'trending' AND is_trending = true)
                 OR (:filter = 'new-release' AND is_new_release = true)
                 OR (:filter = 'must-watch' AND is_must_watch = true)
              )
            ORDER BY created_at DESC
            """, nativeQuery = true)
    List<Content> findByTypeAndFilter(
            @Param("type") String type,
            @Param("filter") String filter,
            Pageable pageable
    );
}
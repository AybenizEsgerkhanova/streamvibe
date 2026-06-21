package com.aybeniz.streamvibe.repository;

import com.aybeniz.streamvibe.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

    @Query(value = "SELECT * FROM genres WHERE type = CAST(:type AS genre_type)", nativeQuery = true)
    List<Genre> findByType(@Param("type") String type);
}
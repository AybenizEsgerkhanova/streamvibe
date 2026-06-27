package com.aybeniz.streamvibe.repository;

import com.aybeniz.streamvibe.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpisodeRepository extends JpaRepository<Episode, Integer> {

    List<Episode> findBySeasonIdOrderByEpisodeNumberAsc(Integer seasonId);
}
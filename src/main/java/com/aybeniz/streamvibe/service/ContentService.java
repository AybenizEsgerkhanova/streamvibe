package com.aybeniz.streamvibe.service;

import com.aybeniz.streamvibe.dto.response.*;
import com.aybeniz.streamvibe.entity.Content;
import com.aybeniz.streamvibe.mapper.ContentMapper;
import com.aybeniz.streamvibe.mapper.LandingMapper;
import com.aybeniz.streamvibe.repository.ContentRepository;
import com.aybeniz.streamvibe.repository.GenreRepository;
import com.aybeniz.streamvibe.repository.ReviewRepository;
import com.aybeniz.streamvibe.repository.SeasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;
    private final GenreRepository genreRepository;
    private final SeasonRepository seasonRepository;
    private final ReviewRepository reviewRepository;
    private final ContentMapper contentMapper;
    private final LandingMapper landingMapper;

    public List<ContentHeroResponse> getHeroContent() {
        return contentRepository.findByIsFeaturedTrue()
                .stream()
                .map(contentMapper::toHeroResponse)
                .toList();
    }

    public List<GenreResponse> getGenresByType(String type) {
        validateType(type);

        return genreRepository.findByType(type.toLowerCase())
                .stream()
                .map(landingMapper::toGenreResponse)
                .toList();
    }

    public List<TopTenContentResponse> getTopTen(String type) {
        validateType(type);

        return contentRepository.findTopTenByType(type.toLowerCase())
                .stream()
                .map(contentMapper::toTopTenResponse)
                .toList();
    }

    public List<ContentListResponse> getContent(String type, String filter, int limit) {
        validateType(type);
        validateFilter(filter);

        int safeLimit = limit <= 0 ? 10 : limit;

        return contentRepository.findByTypeAndFilter(
                        type.toLowerCase(),
                        filter.toLowerCase(),
                        PageRequest.of(0, safeLimit)
                )
                .stream()
                .map(contentMapper::toContentListResponse)
                .toList();
    }

    public ContentDetailResponse getContentDetail(Integer id) {
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Content not found"));

        return contentMapper.toDetailResponse(content);
    }

    public List<SeasonResponse> getSeasons(Integer id) {
        contentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Content not found"));

        return seasonRepository.findByContentIdOrderBySeasonNumberAsc(id)
                .stream()
                .map(contentMapper::toSeasonResponse)
                .toList();
    }

    public List<ReviewResponse> getReviews(Integer id) {
        contentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Content not found"));

        return reviewRepository.findByContentIdOrderByCreatedAtDesc(id)
                .stream()
                .map(contentMapper::toReviewResponse)
                .toList();
    }

    private void validateType(String type) {
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("Type parameter is required");
        }

        if (!type.equalsIgnoreCase("movie") && !type.equalsIgnoreCase("show")) {
            throw new IllegalArgumentException("Type must be either movie or show");
        }
    }

    private void validateFilter(String filter) {
        if (filter == null || filter.isBlank()) {
            throw new IllegalArgumentException("Filter parameter is required");
        }

        if (!filter.equalsIgnoreCase("trending")
                && !filter.equalsIgnoreCase("new-release")
                && !filter.equalsIgnoreCase("must-watch")) {
            throw new IllegalArgumentException("Filter must be trending, new-release, or must-watch");
        }
    }
}
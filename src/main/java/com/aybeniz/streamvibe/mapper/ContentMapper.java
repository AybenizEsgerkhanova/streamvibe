package com.aybeniz.streamvibe.mapper;

import com.aybeniz.streamvibe.dto.response.*;
import com.aybeniz.streamvibe.entity.Content;
import com.aybeniz.streamvibe.entity.Episode;
import com.aybeniz.streamvibe.entity.Review;
import com.aybeniz.streamvibe.entity.Season;
import com.aybeniz.streamvibe.repository.EpisodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContentMapper {
    private final EpisodeRepository episodeRepository;


    public SeasonResponse toSeasonResponse(Season season) {
        return SeasonResponse.builder()
                .id(season.getId())
                .seasonNumber(season.getSeasonNumber())
                .episodeCount(season.getEpisodeCount())
                .title(season.getTitle())
                .episodes(
                        episodeRepository.findBySeasonIdOrderByEpisodeNumberAsc(season.getId())
                                .stream()
                                .map(this::toEpisodeResponse)
                                .toList()
                )
                .build();
    }

    public EpisodeResponse toEpisodeResponse(Episode episode) {
        return EpisodeResponse.builder()
                .id(episode.getId())
                .episodeNumber(episode.getEpisodeNumber())
                .title(episode.getTitle())
                .description(episode.getDescription())
                .thumbnailUrl(episode.getThumbnailUrl())
                .durationMinutes(episode.getDurationMinutes())
                .build();
    }

    public ReviewResponse toReviewResponse(Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .reviewerName(review.getReviewerName())
                .reviewerLocation(review.getReviewerLocation())
                .rating(review.getRating())
                .reviewText(review.getReviewText())
                .createdAt(review.getCreatedAt().toString())
                .build();
    }
    public ContentHeroResponse toHeroResponse(Content content) {
        return new ContentHeroResponse(
                content.getId(),
                content.getTitle(),
                content.getDescription(),
                content.getBackgroundUrl(),
                content.getTrailerUrl(),
                content.getType()
        );
    }

    public TopTenContentResponse toTopTenResponse(Content content) {
        return new TopTenContentResponse(
                content.getId(),
                content.getTitle(),
                content.getPosterUrl(),
                content.getTopTenRank()
        );
    }

    public ContentListResponse toContentListResponse(Content content) {
        return new ContentListResponse(
                content.getId(),
                content.getTitle(),
                content.getPosterUrl(),
                content.getImdbRating(),
                content.getStreamvibeRating(),
                content.getReleaseYear(),
                content.getType()
        );
    }
    public ContentDetailResponse toDetailResponse(Content content) {

        return ContentDetailResponse.builder()
                .id(content.getId())
                .title(content.getTitle())
                .description(content.getDescription())
                .posterUrl(content.getPosterUrl())
                .backgroundUrl(content.getBackgroundUrl())
                .trailerUrl(content.getTrailerUrl())
                .type(content.getType())
                .releaseYear(content.getReleaseYear())
                .imdbRating(content.getImdbRating())
                .streamvibeRating(content.getStreamvibeRating())
                .build();
    }
}
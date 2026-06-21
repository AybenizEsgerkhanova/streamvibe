package com.aybeniz.streamvibe.mapper;

import com.aybeniz.streamvibe.dto.response.ContentHeroResponse;
import com.aybeniz.streamvibe.dto.response.ContentListResponse;
import com.aybeniz.streamvibe.dto.response.TopTenContentResponse;
import com.aybeniz.streamvibe.entity.Content;
import org.springframework.stereotype.Component;

@Component
public class ContentMapper {

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
}
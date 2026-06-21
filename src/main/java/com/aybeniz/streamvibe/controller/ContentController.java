package com.aybeniz.streamvibe.controller;

import com.aybeniz.streamvibe.dto.response.ApiResponse;
import com.aybeniz.streamvibe.dto.response.ContentHeroResponse;
import com.aybeniz.streamvibe.dto.response.ContentListResponse;
import com.aybeniz.streamvibe.dto.response.GenreResponse;
import com.aybeniz.streamvibe.dto.response.TopTenContentResponse;
import com.aybeniz.streamvibe.service.ContentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/content")
@RequiredArgsConstructor
@Tag(
        name = "Movies & Shows API",
        description = "Endpoints for Movies and Shows page sections"
)
public class ContentController {

    private final ContentService contentService;

    @GetMapping("/hero")
    @Operation(
            summary = "Get hero content",
            description = "Returns featured content for the hero slider."
    )
    public ApiResponse<List<ContentHeroResponse>> getHeroContent() {
        return ApiResponse.ok(contentService.getHeroContent());
    }

    @GetMapping("/genres")
    @Operation(
            summary = "Get content genres",
            description = "Returns genres by required type parameter. Supported values: movie, show."
    )
    public ApiResponse<List<GenreResponse>> getGenresByType(
            @RequestParam String type
    ) {
        return ApiResponse.ok(contentService.getGenresByType(type));
    }

    @GetMapping("/top-ten")
    @Operation(
            summary = "Get top ten content",
            description = "Returns Top 10 content by type. Supported values: movie, show."
    )
    public ApiResponse<List<TopTenContentResponse>> getTopTen(
            @RequestParam String type
    ) {
        return ApiResponse.ok(contentService.getTopTen(type));
    }

    @GetMapping
    @Operation(
            summary = "Get filtered content",
            description = "Returns content by type and filter. type: movie/show. filter: trending, new-release, must-watch."
    )
    public ApiResponse<List<ContentListResponse>> getContent(
            @RequestParam String type,
            @RequestParam String filter,
            @RequestParam(defaultValue = "10") int limit
    ) {
        return ApiResponse.ok(contentService.getContent(type, filter, limit));
    }
}
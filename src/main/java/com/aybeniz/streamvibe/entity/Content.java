package com.aybeniz.streamvibe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "content")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "poster_url")
    private String posterUrl;

    @Column(name = "background_url")
    private String backgroundUrl;

    @Column(name = "trailer_url")
    private String trailerUrl;

    private String type;

    @Column(name = "release_year")
    private Integer releaseYear;

    @Column(name = "imdb_rating")
    private BigDecimal imdbRating;

    @Column(name = "streamvibe_rating")
    private BigDecimal streamvibeRating;

    @Column(name = "is_trending")
    private Boolean isTrending;

    @Column(name = "is_new_release")
    private Boolean isNewRelease;

    @Column(name = "is_must_watch")
    private Boolean isMustWatch;

    @Column(name = "is_featured")
    private Boolean isFeatured;

    @Column(name = "top_ten_rank")
    private Integer topTenRank;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
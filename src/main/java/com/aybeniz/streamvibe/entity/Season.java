package com.aybeniz.streamvibe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "seasons")
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "content_id")
    private Integer contentId;

    @Column(name = "season_number")
    private Integer seasonNumber;

    @Column(name = "episode_count")
    private Integer episodeCount;

    private String title;
}
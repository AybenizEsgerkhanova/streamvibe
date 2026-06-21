package com.aybeniz.streamvibe.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String slug;

    @Column(name = "type")
    private String type;

    @Column(name = "cover_image_1")
    private String coverImage1;

    @Column(name = "cover_image_2")
    private String coverImage2;

    @Column(name = "cover_image_3")
    private String coverImage3;

    @Column(name = "cover_image_4")
    private String coverImage4;
}
package com.example.x3.MovieManagementApp.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "movies")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Movies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "movie_name")
    private String movieName;

    @Column(name = "release_year")
    private int releaseYear;

    @Column(name = "movie_length")
    private int movieLength;

    @Column(name = "rating")
    private double rating;
}

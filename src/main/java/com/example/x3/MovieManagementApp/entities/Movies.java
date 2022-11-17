package com.example.x3.MovieManagementApp.entities;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    private long id;

    @Column(name = "movie_name", length = 100)
    private String movieName;

    @Column(name = "release_year")
    private int releaseYear;

    @Column(name = "movie_length")
    private int movieLength;

    @Column(name = "rating")
    private double rating;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.MERGE})
    @JoinTable(name = "movie_genres",
            joinColumns = {
                @JoinColumn(name = "movie_id", referencedColumnName = "id", nullable = false, updatable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "genre_id", referencedColumnName = "id", nullable = false, updatable = false)}
    )
    @ToString.Exclude
    private Set<Genres> genres = new HashSet<>();

}

package com.example.x3.MovieManagementApp.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.Entity;
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

    @Column(name = "movie_name", nullable = false, length = 100)
    private String movieName;

    @Column(name = "release_year", nullable = false)
    private int releaseYear;

    @Column(name = "movie_length", nullable = false)
    private int movieLength;

    @Column(name = "rating")
    private double rating;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "movie_genres",
            joinColumns = {
                @JoinColumn(name = "movie_id", referencedColumnName = "id", nullable = false, updatable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "genre_id", referencedColumnName = "id", nullable = false, updatable = false)}
    )
    @JsonManagedReference
    @ToString.Exclude
    private Set<Genres> genres = new HashSet<>();

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonManagedReference
    @ToString.Exclude
    private Set<Ratings> ratings = new HashSet<>();

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonManagedReference
    @ToString.Exclude
    private Set<MovieComments> movieComments = new HashSet<>();

    public void addGenre(Genres genre) {
        if (genres == null) {
            genres = new HashSet<>();
        }

        genres.add(genre);
    }

}

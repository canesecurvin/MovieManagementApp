package com.example.x3.MovieManagementApp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "genres")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Genres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, name = "genre", length = 20)
    private String genre;

    @ManyToMany(mappedBy = "genres", fetch = FetchType.LAZY)
    @JsonBackReference
    @ToString.Exclude
    private Set<Movies> movies = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genres)) return false;
        Genres genres = (Genres) o;
        return id.equals(genres.id) && genre.equals(genres.genre) && Objects.equals(movies, genres.movies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, genre, movies);
    }
}

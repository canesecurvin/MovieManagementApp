package com.example.x3.MovieManagementApp.repositories;

import com.example.x3.MovieManagementApp.entities.Genres;
import com.example.x3.MovieManagementApp.entities.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genres, Long> {

    Optional<Genres> findByGenre(String genre);

}

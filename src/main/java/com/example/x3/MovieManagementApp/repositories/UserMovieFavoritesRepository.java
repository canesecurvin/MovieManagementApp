package com.example.x3.MovieManagementApp.repositories;

import com.example.x3.MovieManagementApp.entities.Movies;
import com.example.x3.MovieManagementApp.entities.UserMovieFavorites;
import com.example.x3.MovieManagementApp.entities.UserMovieFavoritesPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserMovieFavoritesRepository extends JpaRepository<UserMovieFavorites, UserMovieFavoritesPK> {

    List<UserMovieFavorites> findAllByMovieId(long movieId);

    List<UserMovieFavorites> findAllByUserId(long userId);

    Optional<UserMovieFavorites> findByUserIdAndMovieId(long userId, long movieId);

    List<Movies> findAllMovieFavoritesByUserId(long userId);
}

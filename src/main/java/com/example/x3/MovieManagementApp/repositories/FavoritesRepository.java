package com.example.x3.MovieManagementApp.repositories;

import com.example.x3.MovieManagementApp.entities.Favorites;
import com.example.x3.MovieManagementApp.entities.Movies;
import com.example.x3.MovieManagementApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoritesRepository extends JpaRepository<Favorites, Long> {
    List<Favorites> findAllByUserId(Long userId);

    Optional<Favorites> findByUserAndMovie(User user, Movies movie);

    List<Favorites> deleteDistinctByUserAndMovie(User user, Movies movie);
}
